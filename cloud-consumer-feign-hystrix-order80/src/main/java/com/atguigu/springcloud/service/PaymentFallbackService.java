package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component//防止springboot容器，扫描不到
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "------PaymentFallbackService fall back-paymentInfo_OK----大家好我是备胎";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "------PaymentFallbackService fall back-paymentInfo_TimeOut----大家好我是备胎";
    }
}
