package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data//自动生成get、set、tostring方法
@AllArgsConstructor //生成的构造方法，全参和空参
@NoArgsConstructor
public class Payment implements Serializable {
    //之所以要实现Serializable，是因为之后分布式部署，可能会用到
    private Long id;//因为数据库中是bigInt，所以这里用Long
    private String serial;

}
