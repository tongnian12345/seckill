package com.imooc.redis;

public class GoodsKey extends BasePrefix {

    private GoodsKey(String prefix) {
        super(prefix);
    }

    public static GoodsKey getGoodsList = new GoodsKey("gl");

}
