package com.purchase.billingsoftware.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    private String name;
    private String description;
    private String bgColor;
}
