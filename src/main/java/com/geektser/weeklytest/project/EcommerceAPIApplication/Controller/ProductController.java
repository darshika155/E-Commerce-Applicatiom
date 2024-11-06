package com.geektser.weeklytest.project.EcommerceAPIApplication.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geektser.weeklytest.project.EcommerceAPIApplication.Model.Product;
import com.geektser.weeklytest.project.EcommerceAPIApplication.Model.ProductCategory;
import com.geektser.weeklytest.project.EcommerceAPIApplication.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    ProductService productService;

    //Post
    @PostMapping(value = "product", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Create a new product", description = "Creates a new product with the provided details and an image.")
    public String addAProduct(
            @RequestPart("product") String productJson,
            @RequestPart("image") MultipartFile imageFile) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(productJson, Product.class);
        return  productService.addAProduct(product, imageFile);
    }

    @PostMapping("products")
    public String addListOfProducts(@RequestBody List<Product> products) {return productService.addListOfProducts(products);}


    @GetMapping("product/{Id}")
    public Optional<Product> getProduct(@RequestBody Integer Id)
    {
        return productService.getProduct(Id);
    }

    @GetMapping("products")
    public List<Product> getProducts()
    {
        return productService.getProducts();
    }
    @GetMapping("products/{category}")
    public List<Product> getProductsByCategory(@PathVariable ProductCategory category)
    {
        return productService.getProductByCategory(category);
    }
    @DeleteMapping("product/{id}")
    public String deleteProductById(@PathVariable Integer id)
    {
        return productService.deleteProductById(id);
    }


}
