package com.outfit.dto;

import com.outfit.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OutfitResponse {
    private List<Product> items;
    private double matchScore;
    private boolean withinBudget;
    private double totalPrice;
}
