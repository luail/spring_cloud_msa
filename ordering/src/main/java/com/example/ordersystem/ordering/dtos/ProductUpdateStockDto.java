package com.example.ordersystem.ordering.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateStockDto {
    private Long productId;
    private Integer productQuantity;
}
