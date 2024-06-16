package com.eeezi.ezziblogrestapi.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
}
