package com.outfit.engine;

import com.outfit.model.Product;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductCache {

    private final Map<String, List<Product>> categoryMap = new HashMap<>();

    public void loadProducts(List<Product> products) {
    	 categoryMap.clear();
        for (Product product : products) {
            categoryMap
                .computeIfAbsent(product.getCategory(), k -> new ArrayList<>())
                .add(product);
        }
    }

    public List<Product> getByCategory(String category) {
        return categoryMap.getOrDefault(category, Collections.emptyList());
    }
}
