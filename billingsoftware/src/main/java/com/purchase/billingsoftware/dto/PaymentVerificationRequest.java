package com.purchase.billingsoftware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVerificationRequest {
    private String razorpayOrderId;
    private String razorPaymentId;
    private String razorpaySignature;
    private String orderId;
}
