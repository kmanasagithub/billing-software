package com.purchase.billingsoftware.service;

import com.purchase.billingsoftware.dto.RazorPayOrderResponse;
import com.razorpay.RazorpayException;

public interface RazorPayService {

    RazorPayOrderResponse createOrder(Double amount, String currency) throws RazorpayException;

}
