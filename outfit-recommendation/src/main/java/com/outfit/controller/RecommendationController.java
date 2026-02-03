package com.outfit.controller;

import com.outfit.dto.OutfitResponse;
import com.outfit.dto.RecommendationRequest;
import com.outfit.service.RecommendationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "http://localhost:4200")
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @PostMapping
    public List<OutfitResponse> recommend(
            @Valid @RequestBody RecommendationRequest request
    ) {
        return service.generateOutfits(
                request.getBaseProductId(),
                request.getMaxBudget()
        );
    }
}
