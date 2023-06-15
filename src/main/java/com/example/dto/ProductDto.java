package com.example.dto;

import com.example.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double costPrice;
    private int currentQuantity;
    private String brand;
    private String width;
    private String height;
    private String depth;
    private String weight;
    private String material;
    private String longDES;
    private Category category;
    private String image;
    private boolean activated;
    private boolean deleted;
}
