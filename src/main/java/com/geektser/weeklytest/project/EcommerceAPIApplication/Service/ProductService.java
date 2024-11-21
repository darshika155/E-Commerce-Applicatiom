package com.geektser.weeklytest.project.EcommerceAPIApplication.Service;

import com.geektser.weeklytest.project.EcommerceAPIApplication.Config.S3Service;
import com.geektser.weeklytest.project.EcommerceAPIApplication.Model.Product;
import com.geektser.weeklytest.project.EcommerceAPIApplication.Model.ProductCategory;
import com.geektser.weeklytest.project.EcommerceAPIApplication.Repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    IProductRepo iProductRepo;
    @Autowired
    S3Service s3Service;

    public String addAProduct(Product product, MultipartFile imageFile) throws Exception{
        Path tempFile = Files.createTempFile("product-image-", imageFile.getOriginalFilename());
        Files.write(tempFile, imageFile.getBytes());

        String s3Key = s3Service.uploadFile(tempFile, "images/" + imageFile.getOriginalFilename());

        // Set the S3 key or URL to the product entity
        product.setProductImageUrl(s3Key);  // Or construct full S3 URL if needed

        iProductRepo.save(product);
        return "Product added :) !";
    }

    public List<Product> getProducts(){
       return (List<Product>) iProductRepo.findAll();
    }

    public Optional<Product> getProduct(Integer id){
        return iProductRepo.findById(id);
    }

    public List<Product> getProductByCategory(ProductCategory category){
        return iProductRepo.findByProductCategory(category);
    }

    public String addListOfProducts(List<Product> products){
        iProductRepo.saveAll(products);
        return "Products are added :) !";
    }

    public String deleteProductById(Integer id){
        iProductRepo.deleteById(id);
        return "Product removed !";
    }

}
