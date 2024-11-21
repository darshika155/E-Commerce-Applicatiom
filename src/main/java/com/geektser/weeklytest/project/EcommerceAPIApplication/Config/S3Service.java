package com.geektser.weeklytest.project.EcommerceAPIApplication.Config;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.nio.file.Path;

@Service
public class S3Service {
    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(Path filePath, String keyName) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        s3Client.putObject(putObjectRequest, filePath);
        return keyName;  // Or construct the file URL if needed
    }
}
