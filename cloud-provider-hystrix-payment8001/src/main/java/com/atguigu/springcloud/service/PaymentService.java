package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    //正常访问
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_OK,  id:"+id+"\t"+"对对对对对对对对对";
    }

    //fallbackMethod，即兜底的方法，这里Hystrix是用单独线程池做处理，起到隔离的效果
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
            //上面这句表示，在3秒中内都是正常的，即设置自身调用超时时间的峰值，峰值内可以正常运行
    })
    public String paymentInfo_TimeOut(Integer id){
        try{
            TimeUnit.MILLISECONDS.sleep(500);
            //注意：默认Feign客户端只等待1秒钟。
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //int age = 10/0;
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_TimeOut,  id:"+id+"\t"+"timeout对对对对对，耗时1秒中";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_TimeOutHandler,id:" +id+"\t"+"timeout我来兜底，错错错错错错错错错";
    }

    //========服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value="true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value="10000"),//时间窗口期,短路多久后开始尝试恢复
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value="60")//失败率达到多少后跳闸，即十次请求中，有6次失败以上
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("*****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID(); //这是Hutool工具类的唯一标识码
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后再试，哭哭, id: "+ id;
    }
}
