package com.greedyraccoon.fake_image_detector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.greedyraccoon.fake_image_detector.service.ImageDetectionService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class DetectionController {

    private final ImageDetectionService detectionService;

    @Autowired
    public DetectionController(ImageDetectionService detectionService) {
        this.detectionService = detectionService;
    }

    @PostMapping("/detect")
    public ResponseEntity<String> detectFakeImage(@RequestParam("image") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"error\": \"Please upload an image file.\"}");
        }

        try {
            String result = detectionService.analyzeImage(file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}

