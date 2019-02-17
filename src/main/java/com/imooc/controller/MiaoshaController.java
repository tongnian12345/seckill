package com.imooc.controller;

import com.imooc.domain.MiaoshaOrder;
import com.imooc.domain.MiaoshaUser;
import com.imooc.domain.OrderInfo;
import com.imooc.service.GoodsService;
import com.imooc.service.MiaoshaService;
import com.imooc.service.OrderService;
import com.imooc.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String miaosha(Model model, MiaoshaUser user, long goodsId) {
        if (null == user) {
            return "login";
        }
        // 判断库存
        GoodsVo goods = goodsService.getGoodsByGoodsId(goodsId);
        if (goods.getStockCount() <= 0) {
            model.addAttribute("msg", "秒杀已经结束");
            return "miaosha_fail";
        }
        // 判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getOrderByUserGoodsId(user.getId(), goodsId);
        if (order != null) {
            model.addAttribute("msg", "你已经秒杀成功了，不可以重复秒杀");
            return "miaosha_fail";
        }
        // 减库存，生成秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }


}
