package com.myproject.domain.alipay;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * 支付宝配置类
 * @date 2020/8/11
 */
@Data
@Entity
@ApiModel(value = "支付宝账号配置")
public class AlipayConfig implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT COMMENT '主键，自动生成'")
    @ApiModelProperty("主键")
    @NotNull(groups = {AlipayConfig.update.class}, message = "id不能为空")
    @Null(groups = {AlipayConfig.add.class}, message = "id自动生成，不可设置")
    private Long id;

    /**
     * 应用ID,APPID，收款账号既是APPID对应支付宝账号
     */
    @Column(name = "app_id", columnDefinition = "VARCHAR(50) COMMENT '应用ID'")
    @ApiModelProperty("应用ID")
    @NotBlank(groups = {AlipayConfig.add.class}, message = "应用ID不能为空")
    private String appId;

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    @Column(name = "private_key", columnDefinition = "text COMMENT '私钥'")
    @ApiModelProperty("商户私钥")
    @NotBlank(groups = {AlipayConfig.add.class}, message = "商户私钥不能为空")
    private String privateKey;

    /**
     * 支付宝公钥
     */
    @Column(name = "public_key", columnDefinition = "text COMMENT '公钥'")
    @ApiModelProperty("支付宝公钥")
    @NotBlank(groups = {AlipayConfig.add.class}, message = "支付宝公钥不能为空")
    private String publicKey;

    /**
     * 签名方式，固定格式
     */
    @Column(name = "sign_type", columnDefinition = "VARCHAR(50) COMMENT '签名方式'")
    @ApiModelProperty("签名方式，固定格式")
    @NotBlank(groups = {AlipayConfig.add.class}, message = "签名方式不能为空")
    private String signType;

    /**
     * 支付宝网关
     */
    @Column(name = "gateway_url", columnDefinition = "VARCHAR(100) COMMENT '支付宝网关'")
    @ApiModelProperty("支付宝网关，一般不会变")
    @NotBlank(groups = {AlipayConfig.add.class}, message = "支付宝网关不能为空")
    private String gatewayUrl;

    /**
     * 编码方式，固定格式--utf-8
     */
    @Column(name = "charset", columnDefinition = "VARCHAR(50) COMMENT '编码格式'")
    @ApiModelProperty("编码方式，固定格式")
    @NotBlank(groups = {AlipayConfig.add.class}, message = "编码方式不能为空")
    private String charset;

    /**
     * 异步通知地址
     */
    @Column(name = "notify_url", columnDefinition = "VARCHAR(200) COMMENT '应用网关'")
    @ApiModelProperty("异步通知地址")
    @NotBlank(groups = {AlipayConfig.add.class}, message = "异步通知地址不能为空")
    private String notifyUrl;

    /**
     * 订单完成后返回的页面
     */
    @Column(name = "return_url", columnDefinition = "VARCHAR(200) COMMENT '授权回调地址'")
    @ApiModelProperty("订单完成后返回的页面")
    @NotBlank(groups = {AlipayConfig.add.class}, message = "回调地址不能为空")
    private String returnUrl;

    /**
     * 数据传输类型，固定格式--json
     */
    @Column(name = "format", columnDefinition = "VARCHAR(50) COMMENT '数据格式'")
    @ApiModelProperty("数据传输类型，固定格式")
    @NotEmpty
    @NotBlank(groups = {AlipayConfig.add.class}, message = "数据传输类型不能为空")
    private String format;

    /**
     * 商户号
     */
    @Column(name = "seller_id", columnDefinition = "VARCHAR(100) COMMENT '商户号'")
    @ApiModelProperty("商户号")
    @NotBlank(groups = {AlipayConfig.add.class}, message = "商户号不能为空")
    private String sellerId;

    /**
     * 商户号
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(1000) COMMENT '备注'")
    @ApiModelProperty("备注")
    private String remark;

    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    @ApiModelProperty(value = "新增时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Null(groups = {AlipayConfig.add.class, AlipayConfig.update.class}, message = "自动生成，不可设置")
    private Date createTime;

    @Column(name = "update_time", columnDefinition = "DATETIME COMMENT '修改时间'")
    @ApiModelProperty(value = "修改时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Null(groups = {AlipayConfig.add.class, AlipayConfig.update.class}, message = "自动生成，不可设置")
    private Date updateTime;

    /**
     * 新增
     */
    public @interface add {
    }

    /**
     * 修改
     */
    public @interface update {
    }

}
