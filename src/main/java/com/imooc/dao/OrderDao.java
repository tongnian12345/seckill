package com.imooc.dao;

import com.imooc.domain.MiaoshaOrder;
import com.imooc.domain.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderDao {

    @Select("select * from miaosha_order where user_id = #{userId} and goods_id = #{goodsId}")
    public MiaoshaOrder getOrderByUserGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id, goods_id, delivery_addr_id, goods_name," +
            " goods_count, goods_price, order_channel, status, create_date)" +
            " values (#{userId}, #{goodsId}, #{deliveryAddrId}, #{goodsName}, #{goodsCount}," +
            " #{goodsPrice}, #{orderChannel}, #{status}, #{createDate})")
    public long insert(OrderInfo orderInfo);

}
