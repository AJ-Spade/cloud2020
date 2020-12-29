package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback="payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result+"consumerrrrrrrrrrrrrrrrrrrr";
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            //设置超时峰值为1.5秒，但是现在服务提供者的服务时间是2s，会进入兜底方法
            //但是注意：默认Feign客户端只等待1秒钟。
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })*/
    @HystrixCommand//默认调用全局的兜底方法
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        int age = 10/0;
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result+"cusumer timeoutttttttttttttttttttttttttttttt";
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙，请10秒钟后再试，或者自己运行出错请检查自己！！！";
    }

    //全局的fallback方法
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试，ssss";
    }
}
