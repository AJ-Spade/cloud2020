package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageService {
    @PostMapping(value="/storage/decrease")//由于是对数据库的写操作，所以是PostMapping，而不是GetMapping
    CommonResult decrease(@RequestParam("productId") Long productId,@RequestParam("count") Integer count);
}
