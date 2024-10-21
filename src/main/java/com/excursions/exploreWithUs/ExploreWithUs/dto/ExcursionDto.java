package com.excursions.exploreWithUs.ExploreWithUs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcursionDto {
    Long id;
    String name;
    String description;
    Double price;
}
