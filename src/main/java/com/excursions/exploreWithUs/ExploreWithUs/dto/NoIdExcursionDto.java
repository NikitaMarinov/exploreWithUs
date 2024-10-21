package com.excursions.exploreWithUs.ExploreWithUs.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoIdExcursionDto {
    @NotBlank(message = "Name is required")
    String name;

    @NotBlank(message = "Description is required")
    String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0", message = "Price must be a positive number")
    Double price;
}
