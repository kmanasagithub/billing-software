package com.purchase.billingsoftware.service;

import com.purchase.billingsoftware.dto.ItemRequest;
import com.purchase.billingsoftware.dto.ItemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemService {

    ItemResponse add(ItemRequest request, MultipartFile file) throws IOException;

    List<ItemResponse> fetchItems();

    void deleteItem(String itemId);
}
