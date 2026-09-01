package com.purchase.billingsoftware.controller;

import com.purchase.billingsoftware.dto.CategoryRequest;
import com.purchase.billingsoftware.dto.CategoryResponse;
import com.purchase.billingsoftware.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.thirdparty.jackson.core.JsonProcessingException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryResponse> addCategory(
            @RequestPart("category") String categoryString,
            @RequestPart("file") MultipartFile file
    ) {
        CategoryRequest request;

        try {
            request = objectMapper.readValue(
                    categoryString,
                    CategoryRequest.class
            );
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(categoryService.add(request, file));

        } catch (IOException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid category JSON: " + ex.getMessage(),
                    ex
            );
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> fetchCategories() {
        List<CategoryResponse> categories = categoryService.read();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<Void> removeCategory(@PathVariable String categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }

}
