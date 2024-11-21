package com.geektser.weeklytest.project.EcommerceAPIApplication.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Schema(description = "Name of the product")
    private String productName;

    @Schema(description = "Price of the product")
    private Integer productPrice;

    @Schema(description = "Description of the product")
    private String productDesc;
    @Enumerated(value = EnumType.STRING)
    @Schema(description = "Category of the product")
    private ProductCategory productCategory;

    @Schema(description = "Brand of the product")
    private String productBrand;
    // URL or S3 Key for the image
    private String productImageUrl;

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }
}
/*Product Model:
       id:integer
         name:string
         price:integer
         description:string
       category:string
       brand:string*/