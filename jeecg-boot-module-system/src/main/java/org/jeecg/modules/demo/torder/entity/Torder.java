package org.jeecg.modules.demo.torder.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: torder
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Data
@TableName("torder")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="torder对象", description="torder")
public class Torder implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private String id;
	/**ordernumber*/
	@Excel(name = "ordernumber", width = 15)
    @ApiModelProperty(value = "ordernumber")
    private String ordernumber;
	/**supplier*/
	@Excel(name = "supplier", width = 15)
    @ApiModelProperty(value = "supplier")
    private String supplier;
	/**client*/
	@Excel(name = "client", width = 15)
    @ApiModelProperty(value = "client")
    private String client;
	/**chop*/
	@Excel(name = "chop", width = 15)
    @ApiModelProperty(value = "chop")
    private String chop;
	/**product*/
	@Excel(name = "product", width = 15)
    @ApiModelProperty(value = "product")
    private String product;
	/**incoterm*/
	@Excel(name = "incoterm", width = 15)
    @ApiModelProperty(value = "incoterm")
    private String incoterm;
	/**delivery*/
	@Excel(name = "delivery", width = 15)
    @ApiModelProperty(value = "delivery")
    private String delivery;
	/**price*/
	@Excel(name = "price", width = 15)
    @ApiModelProperty(value = "price")
    private String price;
	/**quantity*/
	@Excel(name = "quantity", width = 15)
    @ApiModelProperty(value = "quantity")
    private String quantity;
	/**amountRmb*/
	@Excel(name = "amountRmb", width = 15)
    @ApiModelProperty(value = "amountRmb")
    private String amountRmb;
	/**amountUsd*/
	@Excel(name = "amountUsd", width = 15)
    @ApiModelProperty(value = "amountUsd")
    private String amountUsd;
	/**depositRmb*/
	@Excel(name = "depositRmb", width = 15)
    @ApiModelProperty(value = "depositRmb")
    private String depositRmb;
	/**depositUsd*/
	@Excel(name = "depositUsd", width = 15)
    @ApiModelProperty(value = "depositUsd")
    private String depositUsd;
	/**depositPay*/
	@Excel(name = "depositPay", width = 15)
    @ApiModelProperty(value = "depositPay")
    private String depositPay;
	/**balanceRmb*/
	@Excel(name = "balanceRmb", width = 15)
    @ApiModelProperty(value = "balanceRmb")
    private String balanceRmb;
	/**balanceUsd*/
	@Excel(name = "balanceUsd", width = 15)
    @ApiModelProperty(value = "balanceUsd")
    private String balanceUsd;
	/**balancePay*/
	@Excel(name = "balancePay", width = 15)
    @ApiModelProperty(value = "balancePay")
    private String balancePay;
	/**deliveryDate*/
	@Excel(name = "deliveryDate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "deliveryDate")
    private Date deliveryDate;
	/**deliverySituation*/
	@Excel(name = "deliverySituation", width = 15)
    @ApiModelProperty(value = "deliverySituation")
    private String deliverySituation;
	/**etd*/
	@Excel(name = "etd", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "etd")
    private Date etd;
	/**delay*/
	@Excel(name = "delay", width = 15)
    @ApiModelProperty(value = "delay")
    private String delay;
	/**eta*/
	@Excel(name = "eta", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "eta")
    private Date eta;
	/**status*/
	@Excel(name = "status", width = 15)
    @ApiModelProperty(value = "status")
    private String status;
	/**statusdate*/
	@Excel(name = "statusdate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "statusdate")
    private Date statusdate;
	/**create_time*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "create_time")
    private Date createTime;
	/**update_time*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "update_time")
    private Date updateTime;
}
