package com.sber.device.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.sber.device.service.abstraction.RemoteFileSystemManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RemoteFileSystemManagerImpl implements RemoteFileSystemManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String bucketName = "vavilov-test-bucket";
    private String region = "eu-north-1";
    private String accessKey = "AKIAWMOTDGSBTA7WR7DA";
    private String secretKey = "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";
    private String objectPrefix = "";
    private String endpoint = "https://s3.eu-north-1.amazonaws.com";
    private String objectPattern = "\\.xlsx$";
    private final String localStoragePath = "src/main/uploads/";

    private AmazonS3 s3client;

    public RemoteFileSystemManagerImpl() {
        init();
    }

    public void init() {
        logger.trace("Initializing AmazonS3 client");
        if (!endpoint.equals("")) {
            s3client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(
                            new AWSStaticCredentialsProvider(
                                    new BasicAWSCredentials(accessKey, secretKey)))
                    .withEndpointConfiguration(
                            new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                    .build();
        } else {
            s3client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(
                            new AWSStaticCredentialsProvider(
                                    new BasicAWSCredentials(accessKey, secretKey)))
                    .withRegion(region)
                    .build();
        }
    }

    @Override
    public void scanFiles() throws IOException {
        logger.info("Scanning for new files...");

        Pattern pattern = Pattern.compile(objectPattern);
        ObjectListing objectListing = s3client.listObjects(bucketName, objectPrefix);

        for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
            if ((os.getKey()).endsWith(".xlsx")) {

                File file = new File(localStoragePath + os.getKey());
                S3ObjectInputStream inputStream = s3client.getObject(bucketName, os.getKey()).getObjectContent();
                FileUtils.copyInputStreamToFile(inputStream, file);
//                changeObjectName(os.getKey());
            }
        }
//        File[] files = new File(localStoragePath).listFiles();
//        if (files != null && files.length > 0) {
//            return Stream.of(files)
//                    .filter(file -> pattern.matcher(file.getName()).matches())
//                    .collect(Collectors.toList());
//        }
//        return new ArrayList<>();
    }

    //s3 storage doesn't support rename function so here's a workaround
    private void changeObjectName(String objectName) {
        s3client.copyObject(bucketName, objectName, bucketName,
                objectName.replace(".xlsx", ".res")
        );
        s3client.deleteObject(bucketName, objectName);
    }
}