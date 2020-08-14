package com.myproject.controller;

import com.myproject.common.result.Result;
import com.myproject.common.result.ResultUtil;
import com.myproject.domain.alipay.AlipayConfig;
import com.myproject.service.AliPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuyi
 * @date 2020/8/14
 */
@RequestMapping("/aliPay")
@Slf4j
@RestController
@Api(tags = "支付宝支付功能")
@Validated
public class AliPayController {

    private final AliPayService alipayService;


    public AliPayController(AliPayService alipayService) {
        this.alipayService = alipayService;
    }


    @PostMapping("/addConfig")
    @ApiOperation("新增支付宝配置信息")
    public Result<AlipayConfig> addConfig(@Validated(AlipayConfig.add.class) @RequestBody AlipayConfig alipayConfig){
        return ResultUtil.success(200,"支付宝配置信息添加成功",alipayService.addConfig(alipayConfig));
    }
}

