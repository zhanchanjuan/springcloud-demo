package com.myproject.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author shuyi
 * @date 2020/8/14
 */
@Configuration
@ConfigurationProperties(prefix = "pay.alipay")
@Data
public class AliPaySystemConfig {

    /**
     * 默认配置id
     */
    private Long defaultConfigId;

    /**
     * 默认订单超时时间minute
     */
    private Long tradeTimeExpressMinutes;

    /**
     * 支付宝-应用公钥证书
     */
    private String appPublicKeyCertUrl;

    /**
     * 支付宝-根证书
     */
    private String rootCertUrl;
}
