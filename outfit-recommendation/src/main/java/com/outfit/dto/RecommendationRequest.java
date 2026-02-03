package com.outfit.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendationRequest {

    @NotNull
    private Long baseProductId;

    @Min(0)
    private Double maxBudget;
}
