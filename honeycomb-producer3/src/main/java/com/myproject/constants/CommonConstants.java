package com.myproject.constants;

/**
 * 系统常量
 *
 * @author Hawk
 * @date 2020/4/7
 */
public interface CommonConstants {

    /**
     * 是否返回异常详情
     */
    Boolean SHOW_HTTP_ERROR_DETAIL = true;

    /**
     * 交易状态：等待买家付款
     */
    String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";

    /**
     * 交易状态：未付款交易超时关闭，或支付完成后全额退款，或者主动关闭
     */
    String TRADE_STATUS_CLOSED = "TRADE_CLOSED";

    /**
     * 交易状态：交易支付成功
     */
    String TRADE_STATUS_SUCCESS = "TRADE_SUCCESS";

    /**
     * 交易状态：交易结束，不可退款
     */
    String TRADE_STATUS_FINISHED = "TRADE_FINISHED";

    /**
     * 交易无效：交易无效，未能关联支付宝用户
     */
    String TRADE_STATUS_INVALID = "TRADE_INVALID";

    /**
     * 支付宝
     */
    String PAY_TYPE_ALIPAY = "alipay";

    /**
     * 支付宝PRODUCT_CODE
     */
    String PAY_PRODUCT_CODE = "FAST_INSTANT_TRADE_PAY";

    /**
     * 微信支付
     */
    String PAY_TYPE_WXPAY = "wxpay";

    /**
     * 微信调用成功码
     */
    String WX_SUCCESS_CODE = "SUCCESS";

    /**
     * 微信统一下单地址
     */
    String WX_PAY_UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 微信订单查询地址
     */
    String WX_ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     * 关闭订单地址
     * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
     */
    String WX_ORDER_CLOSE_URL = "https://api.mch.weixin.qq.com/pay/closeorder";

    /**
     * 退款地址
     */
    String WX_PAY_REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**
     * 退款查询地址
     */
    String WX_PAY_REFUND_QUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";

    /**
     * 问号字符
     */
    String  QUESTION_MARK  = "?";

}
