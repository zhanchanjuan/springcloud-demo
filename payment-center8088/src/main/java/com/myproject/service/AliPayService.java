package com.myproject.service;

import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.myproject.domain.alipay.AlipayConfig;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author shuyi
 * @date 2020/8/11
 */
public interface AliPayService {
    /**
     * 新增配置
     *
     * @param config
     * @return
     */
    AlipayConfig addConfig(AlipayConfig config);

    /**
     * 删除配置
     *
     * @param id
     */
    void deleteConfig(Long id);

    /**
     * 修改配置
     *
     * @param alipayConfig 支付宝配置
     * @return AlipayConfig
     */
    AlipayConfig updateConfig(AlipayConfig alipayConfig);

    /**
     * 根据id查询配置
     *
     * @param id
     * @return
     */
    AlipayConfig findConfigById(Long id);

    /**
     * 获取默认配置
     *
     * @return
     */
    AlipayConfig getDefaultConfig();

    /**
     * 获取配置集合
     *
     * @param config
     * @return
     */
    List<AlipayConfig> getConfigList(AlipayConfig config);

    /**
     * 获取分页配置
     *
     * @param commonPageReq
     * @return
     */
//    Page<AlipayConfig> getConfigPage(CommonPage<AlipayConfig> commonPageReq);

    /**
     * 处理来自PC的交易请求
     *
     * @param alipay 支付宝配置
     * @param dto    页面付款参数dto
     * @return String
     * @throws Exception 异常
     */
    String getPcPayUrl(AlipayConfig alipay, AlipayTradePagePayModel dto);

    /**
     * 处理来自手机网页的交易请求
     *
     * @param alipay    支付宝配置
     * @param wapPayDto 付款参数dto
     * @return String
     * @throws Exception 异常
     */
    String getWapPayUrl(AlipayConfig alipay, AlipayTradeWapPayModel wapPayDto);


    /**
     * 获取app支付信息
     *
     * @param alipay
     * @param dto
     * @return
     */
    String getAppOrderInfo(AlipayConfig alipay, AlipayTradePagePayModel dto);

    /**
     * 根据商家订单号获取交易信息
     *
     * @param alipayConfig
     * @param outTradeNo
     * @return
     */
//    AlipayTradeQueryRes getAliTradeByOutTradeNo(AlipayConfig alipayConfig, String outTradeNo);

    /**
     * 关闭交易
     *
     * @param alipayConfig
     * @param outTradeNo
     */
    void closeTrade(AlipayConfig alipayConfig, String outTradeNo);

    /**
     * 退款
     *
     * @param alipayConfig
     * @param outTradeNo   商户订单号
     * @param refundAmount 退款金额
     * @return
     */
    void refund(AlipayConfig alipayConfig, String outTradeNo, Double refundAmount);

    /**
     * 查询退款信息
     *
     * @param alipayConfig
     * @param outTradeNo
     * @return
     */
//    AlipayTradeFastpayRefundQueryResponse getRefundInfoByOutTradeNo(AlipayConfig alipayConfig, String outTradeNo);

    /**
     * 处理阿里通知信息
     *
     * @param request
     * @param alipayConfig
     */
    void execNotify(HttpServletRequest request, AlipayConfig alipayConfig);

    /**
     * 支付成功回调
     *
     * @param request
     * @param alipayConfig
     * @return
     */
    String execPaySuccessReturn(HttpServletRequest request, AlipayConfig alipayConfig);

}
