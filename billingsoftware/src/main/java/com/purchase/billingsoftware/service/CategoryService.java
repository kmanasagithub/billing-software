package com.purchase.billingsoftware.service;

import com.purchase.billingsoftware.dto.CategoryRequest;
import com.purchase.billingsoftware.dto.CategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    CategoryResponse add(CategoryRequest request, MultipartFile file) throws IOException;

    List<CategoryResponse> read();

    void delete(String categoryId);
}
