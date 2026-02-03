package com.outfit.service;

import com.outfit.dto.OutfitResponse;
import com.outfit.engine.ProductCache;
import com.outfit.engine.RecommendationEngine;
import com.outfit.model.Product;
import com.outfit.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationService {

    private final ProductRepository productRepository;
    private final ProductCache productCache;
    private final RecommendationEngine engine;

    public RecommendationService(ProductRepository productRepository,
                                 ProductCache productCache,
                                 RecommendationEngine engine) {
        this.productRepository = productRepository;
        this.productCache = productCache;
        this.engine = engine;
    }

    public List<OutfitResponse> generateOutfits(Long baseProductId, double budget) {

        Product base = productRepository.findById(baseProductId)
                .orElseThrow(() -> new RuntimeException("Base product not found"));

        // ✅ Try cache first
        List<Product> tops = productCache.getByCategory("TOP");
        List<Product> bottoms = productCache.getByCategory("BOTTOM");
        List<Product> footwear = productCache.getByCategory("FOOTWEAR");
        List<Product> accessories = productCache.getByCategory("ACCESSORY");

        // ✅ Self-heal: if cache is empty, reload from DB once
        if (tops.isEmpty() && bottoms.isEmpty() && footwear.isEmpty() && accessories.isEmpty()) {
            System.out.println("⚠️ Cache empty. Reloading products from DB...");
            productCache.loadProducts(productRepository.findAll());

            tops = productCache.getByCategory("TOP");
            bottoms = productCache.getByCategory("BOTTOM");
            footwear = productCache.getByCategory("FOOTWEAR");
            accessories = productCache.getByCategory("ACCESSORY");
        }

        System.out.println(
                "DEBUG → tops=" + tops.size()
                        + ", bottoms=" + bottoms.size()
                        + ", footwear=" + footwear.size()
                        + ", accessories=" + accessories.size()
                        + ", baseCategory=" + base.getCategory()
                        + ", budget=" + budget
        );

        if (tops.isEmpty() || bottoms.isEmpty() || footwear.isEmpty() || accessories.isEmpty()) {
            throw new RuntimeException("Not enough products to generate outfits. Need TOP, BOTTOM, FOOTWEAR, ACCESSORY.");
        }

        // 🔥 Generate many, then pick best 5 unique
        int targetCandidates = 250;
        Random random = new Random();

        List<OutfitResponse> results = new ArrayList<>();
        Set<String> seenCombos = new HashSet<>();

        for (int i = 0; i < targetCandidates; i++) {

            Product top = "TOP".equals(base.getCategory()) ? base : pickDistinct(random, tops, base);
            Product bottom = "BOTTOM".equals(base.getCategory()) ? base : pickDistinct(random, bottoms, base);
            Product shoe = "FOOTWEAR".equals(base.getCategory()) ? base : pickDistinct(random, footwear, base);

            Product accessory = "ACCESSORY".equals(base.getCategory())
                    ? base
                    : pickDistinct(random, accessories, base, top, bottom, shoe);

            // ensure all 4 items are unique inside the outfit
            if (hasDuplicateIds(top, bottom, shoe, accessory)) continue;

            // ✅ prevent repeated outfits across results
            String key = top.getId() + "-" + bottom.getId() + "-" + shoe.getId() + "-" + accessory.getId();
            if (!seenCombos.add(key)) continue;

            double total = safePrice(top) + safePrice(bottom) + safePrice(shoe) + safePrice(accessory);
            boolean withinBudget = total <= budget;

            // Score: average compatibility with base (fast & explainable)
            double score = (
                    engine.calculateScore(base, top)
                            + engine.calculateScore(base, bottom)
                            + engine.calculateScore(base, shoe)
                            + engine.calculateScore(base, accessory)
            ) / 4.0;

            results.add(new OutfitResponse(
                    List.of(top, bottom, shoe, accessory),
                    Math.min(score, 1.0),
                    withinBudget,
                    total
            ));
        }

        if (results.isEmpty()) {
            throw new RuntimeException("No outfits generated. Add more products or check category data.");
        }

        // Sort: withinBudget first, then score, then cheaper total
        return results.stream()
                .sorted((a, b) -> {
                    if (a.isWithinBudget() != b.isWithinBudget()) {
                        return Boolean.compare(b.isWithinBudget(), a.isWithinBudget());
                    }
                    int scoreCompare = Double.compare(b.getMatchScore(), a.getMatchScore());
                    if (scoreCompare != 0) return scoreCompare;
                    return Double.compare(a.getTotalPrice(), b.getTotalPrice());
                })
                .limit(5)
                .toList();
    }

    private double safePrice(Product p) {
        return p.getPrice() == null ? 0.0 : p.getPrice();
    }

    private boolean hasDuplicateIds(Product a, Product b, Product c, Product d) {
        return a.getId().equals(b.getId())
                || a.getId().equals(c.getId())
                || a.getId().equals(d.getId())
                || b.getId().equals(c.getId())
                || b.getId().equals(d.getId())
                || c.getId().equals(d.getId());
    }

    /**
     * Pick an item from list that is not equal to any excluded products.
     * Tries multiple times to avoid duplicates.
     */
    private Product pickDistinct(Random random, List<Product> list, Product... excluded) {
        for (int attempts = 0; attempts < 25; attempts++) {
            Product candidate = list.get(random.nextInt(list.size()));
            boolean ok = true;
            for (Product ex : excluded) {
                if (ex != null && candidate.getId().equals(ex.getId())) {
                    ok = false;
                    break;
                }
            }
            if (ok) return candidate;
        }
        // fallback (rare)
        return list.get(0);
    }
}
