package com.zhu.service.impl;

import com.zhu.service.EchoService;

/*
* 服务接口实现
* */
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String ping) {
        return ping != null ? ping + " --> I am ok. " : " I am ok. ";
    }
}
