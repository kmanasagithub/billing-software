package com.purchase.billingsoftware.service.impl;

import com.purchase.billingsoftware.dto.CategoryRequest;
import com.purchase.billingsoftware.dto.CategoryResponse;
import com.purchase.billingsoftware.entity.CategoryEntity;
import com.purchase.billingsoftware.repository.CategoryRepository;
import com.purchase.billingsoftware.repository.ItemRepository;
import com.purchase.billingsoftware.service.CategoryService;
import com.purchase.billingsoftware.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final FileUploadService fileUploadService;

    @Override
    public CategoryResponse add(CategoryRequest request, MultipartFile file) throws IOException {
//        FOr S3 uploads
//        String imgUrl = fileUploadService.uploadFile(file);
        String fileName = UUID.randomUUID().toString()+"."+ StringUtils.getFilename(file.getOriginalFilename());
        Path uploadPath = Paths.get("uploads").toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);
        Path targetLocation = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
        String imgUrl = "http://localhost:8080/api/v1.0/uploads/"+fileName;

        CategoryEntity newCategory = convertToEntity(request);
        newCategory.setImgUrl(imgUrl);
        newCategory = categoryRepository.save(newCategory);
        return convertToResponse(newCategory);
    }

    @Override
    public List<CategoryResponse> read() {
        List<CategoryEntity> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> convertToResponse(category))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String categoryId) {
        CategoryEntity existingCategory = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new RuntimeException("Category Not Found: "+categoryId));

       // fileUploadService.deleteFile(existingCategory.getImgUrl());
        String imgUrl = existingCategory.getImgUrl();
        String fileName = imgUrl.substring(imgUrl.lastIndexOf("/")+1);
        Path uploadPath = Paths.get("uploads").toAbsolutePath().normalize();
        Path filePath = uploadPath.resolve(fileName);

        try {
            Files.deleteIfExists(filePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        categoryRepository.delete(existingCategory);
    }

    private CategoryResponse convertToResponse(CategoryEntity newCategory) {

        Integer itemsCount = itemRepository.countByCategoryId(newCategory.getId());

        return CategoryResponse.builder()
                .categoryId(newCategory.getCategoryId())
                .name(newCategory.getName())
                .description(newCategory.getDescription())
                .bgColor(newCategory.getBgColor())
                .imgUrl(newCategory.getImgUrl())
                .items(itemsCount)
                .createdAt(newCategory.getCreatedAt())
                .updatedAt(newCategory.getUpdatedAt())
                .build();
    }

    private CategoryEntity convertToEntity(CategoryRequest request) {
        return CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .build();
    }
}
