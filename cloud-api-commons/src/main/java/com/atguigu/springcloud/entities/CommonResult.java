package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//前后端分离，所以这里是传给前端的json串
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    //404 not_found
    private Integer code; //状态码，如404，或者200
    private String message; //
    private T data; //返回的具体数据类型或者对象，便于后期直接展示对象数据


    public CommonResult(Integer code, String message){
        this(code,message,null);
    }
}
