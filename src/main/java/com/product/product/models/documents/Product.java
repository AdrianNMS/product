package com.product.product.models.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document("product")
public class Product
{
    @Id
    private String id;
    private String name;
    private Float price;
    private Integer quantity;

}
