package com.imooc.utils;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5Encryption(String password) {
        return DigestUtils.md5Hex(password);
    }

    public static void main(String[] args) {
        System.out.println(md5Encryption("123456"));
    }


}
