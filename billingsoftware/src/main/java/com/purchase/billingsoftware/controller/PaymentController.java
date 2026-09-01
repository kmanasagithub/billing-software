package com.purchase.billingsoftware.controller;

import com.purchase.billingsoftware.dto.OrderResponse;
import com.purchase.billingsoftware.dto.PaymentRequest;
import com.purchase.billingsoftware.dto.PaymentVerificationRequest;
import com.purchase.billingsoftware.dto.RazorPayOrderResponse;
import com.purchase.billingsoftware.service.OrderService;
import com.purchase.billingsoftware.service.RazorPayService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final RazorPayService razorPayService;
    private final OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<RazorPayOrderResponse> createRazorpayOrder(@RequestBody PaymentRequest request) throws RazorpayException {
        return ResponseEntity.status(HttpStatus.CREATED).body(razorPayService.createOrder(request.getAmount(),request.getCurrency()));
    }

    @PostMapping("/verify")
    public OrderResponse verifyPayment(@RequestBody PaymentVerificationRequest request) {
        return orderService.verifyPayment(request);
    }
}
