package com.myproject.controller;

import com.myproject.common.result.Result;
import com.myproject.common.result.ResultUtil;
import com.myproject.domain.alipay.AlipayConfig;
import com.myproject.service.AliPayService;
import com.myproject.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;

/**
 * @author shuyi
 * @date 2020/8/14
 */

@Slf4j
@RestController
@Api(tags = "支付宝支付功能")
@Validated
@RequestMapping("/aliPay")
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

    @ApiOperation("删除支付宝配置信息")
    @DeleteMapping("/config/{id}")
    public Result deleteConfig(@PathVariable Long id){
        alipayService.deleteConfig(id);
        return ResultUtil.success();
    }

    @ApiOperation("修改支付宝配置信息")
    @DeleteMapping("/config")
    public Result updateConfig(@Validated(AlipayConfig.update.class)@RequestBody AlipayConfig alipayConfig){
        alipayService.updateConfig(alipayConfig);
        return ResultUtil.success("支付宝配置信息修改成功！");

    }
    @ApiOperation("获取支付宝配置信息")
    @GetMapping({"/config", "/config/{id}"})
    @ApiImplicitParam(name = "id", value = "支付宝配置信息id")
    public Result<AlipayConfig> get(@PathVariable(required = false) @DecimalMin(value = "1",message="非法id") Long id){
        if (null == id) {
            return ResultUtil.success(alipayService.getDefaultConfig());
        } else {
            return ResultUtil.success(alipayService.findConfigById(id));
        }
    }







}

