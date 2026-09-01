package com.purchase.billingsoftware.controller;

import com.purchase.billingsoftware.dto.ItemRequest;
import com.purchase.billingsoftware.dto.ItemResponse;
import com.purchase.billingsoftware.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.thirdparty.jackson.core.JsonProcessingException;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/admin/items")
    public ResponseEntity<ItemResponse> addItem(@RequestPart("item") String itemString,
                                                @RequestPart("file")MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        ItemRequest itemRequest = null;
        try{
            itemRequest = objectMapper.readValue(itemString,ItemRequest.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.add(itemRequest,file));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error occur while processing the image");
        }

    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemResponse>> readItems() {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.fetchItems());
    }

    @DeleteMapping("/admin/items/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable String itemId) {
        try {
            itemService.deleteItem(itemId);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Item not Found");
        }
    }

}
