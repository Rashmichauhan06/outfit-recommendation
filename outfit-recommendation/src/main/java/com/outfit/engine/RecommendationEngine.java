package com.outfit.engine;

import com.outfit.model.Product;
import org.springframework.stereotype.Component;

@Component
public class RecommendationEngine {

    public double calculateScore(Product base, Product candidate) {
        double score = 0.0;

        if (base.getStyle().equals(candidate.getStyle())) {
            score += 0.3;
        }

        if (base.getSeason().equals(candidate.getSeason())
                || candidate.getSeason().equals("ALL")) {
            score += 0.2;
        }

        if (base.getOccasion().equals(candidate.getOccasion())) {
            score += 0.3;
        }

        if (isColorCompatible(base.getColor(), candidate.getColor())) {
            score += 0.2;
        }

        return Math.min(score, 1.0);
    }

    private boolean isColorCompatible(String c1, String c2) {
        return c1.equals(c2)
                || c2.equalsIgnoreCase("BLACK")
                || c2.equalsIgnoreCase("WHITE");
    }
}
