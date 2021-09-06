package com.zhu.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddress01 {
    public static void main(String[] args) throws UnknownHostException {
        // 使用getLocalHost方法为InetAddress创建对象；
        InetAddress add=InetAddress.getLocalHost();//获得本机的InetAddress对象
        System.out.println("IP对象：" + add);
        System.out.println(add.getHostAddress());//返回本机IP地址
        System.out.println(add.getHostName());//输出计算机名
        //根据域名得到InetAddress对象
        add=InetAddress.getByName("www.baidu.com");
        System.out.println("IP对象：" + add);
        System.out.println(add.getHostAddress());//返回百度服务器的IP地址
        System.out.println(add.getHostName());//输出www.baidu.com；
        //根据ip得到InetAddress对象；
        //add=InetAddress.getByName("183.232.231.174");
        add=InetAddress.getByAddress(new byte[] { (byte) 183, (byte)232, (byte)231, (byte)174 });
        System.out.println("IP对象：" + add);
        System.out.println(add.getHostAddress());
        System.out.println(add.getHostName());//如果ip地址存在，并且DNS给你解析就会输出
        //www.baidu.com，不给你解析就会返回这个IP本身；
    }
}
