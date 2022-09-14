package com.product.product.tests.models;

import com.product.product.models.documents.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseProduct
{
    private Product data;

    private String message;

    private String status;
}
