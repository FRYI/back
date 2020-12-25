package org.jeecg.modules.demo.torder.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.api.client.json.Json;
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

@TableName("torder")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="torderPic对象", description="torder")
public class TorderAndPic  implements Serializable{
    private static final long serialVersionUID = 1L;


    /**id*/
    @TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private String id;

    @Excel(name = "Photo", width = 40,height = 40)
    private transient String photoString;

    private byte[] photo;
    public byte[] getPhoto(){
        if(photoString==null){
            return null;
        }
        try {
            System.out.println("pass1 "+photoString);
            return photoString.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPhotoString(){
        if(photo==null || photo.length==0){
            return "";
        }
        try {
            System.out.println("passok "+photo);
            return new String(photo,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

//paramData {"color","black"}

    @Excel(name = "Color", width = 15)
    @ApiModelProperty(value = "paramData1")
    private transient String paramData1;

    private transient String paramData;

    public String getParamData1() {
            JSONObject jObject1=new JSONObject(paramData);
            Object color = jObject1.get("color");
        System.out.println(paramData);
        if (color==null){
            color = "na";
        }
        return color.toString();
    }


    /**ordernumber*/
    @Excel(name = "OrderNumber", width = 15)
    @ApiModelProperty(value = "ordernumber")
    private String ordernumber;
    /**supplier*/
    @Excel(name = "Supplier", width = 15)
    @ApiModelProperty(value = "supplier")
    private String supplier;
    /**client*/
    @Excel(name = "Client", width = 15)
    @ApiModelProperty(value = "client")
    private String client;
    /**chop*/
    @Excel(name = "Chop", width = 15)
    @ApiModelProperty(value = "chop")
    private String chop;
    /**season*/
    @Excel(name = "Season", width = 15)
    @ApiModelProperty(value = "season")
    private java.lang.String season;
    /**product*/
    @Excel(name = "Product", width = 15)
    @ApiModelProperty(value = "product")
    private String product;
    /**project*/
    @Excel(name = "Project", width = 15)
    @ApiModelProperty(value = "project")
    private String project;
    /**incoterm*/
    @Excel(name = "Incoterm", width = 15)
    @ApiModelProperty(value = "incoterm")
    private String incoterm;
    /**delivery*/
    @Excel(name = "Delivery", width = 15)
    @ApiModelProperty(value = "delivery")
    private String delivery;
    /**price*/
    @Excel(name = "Price Usd", width = 15)
    @ApiModelProperty(value = "priceUsd")
    private String priceUsd;
    /**quantity*/
    @Excel(name = "Quantity", width = 15)
    @ApiModelProperty(value = "quantity")
    private String quantity;
    /**amountRmb*/
    @Excel(name = "Amount Rmb", width = 15)
    @ApiModelProperty(value = "amountRmb")
    private String amountRmb;
    /**amountUsd*/
    @Excel(name = "Amount Usd", width = 15)
    @ApiModelProperty(value = "amountUsd")
    private String amountUsd;
    /**depositRmb*/
    @Excel(name = "Deposit Rmb", width = 15)
    @ApiModelProperty(value = "depositRmb")
    private String depositRmb;
    /**depositUsd*/
    @Excel(name = "Deposit Usd", width = 15)
    @ApiModelProperty(value = "depositUsd")
    private String depositUsd;
    /**depositPay*/
    @Excel(name = "Deposit Pay", width = 15)
    @ApiModelProperty(value = "depositPay")
    private String depositPay;
    /**balanceRmb*/
    @Excel(name = "Balance Rmb", width = 15)
    @ApiModelProperty(value = "balanceRmb")
    private String balanceRmb;
    /**balanceUsd*/
    @Excel(name = "Balance Usd", width = 15)
    @ApiModelProperty(value = "balanceUsd")
    private String balanceUsd;
    /**balancePay*/
    @Excel(name = "Balance Pay", width = 15)
    @ApiModelProperty(value = "balancePay")
    private String balancePay;
    /**deliveryDate*/
    @Excel(name = "Delivery Date", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "deliveryDate")
    private Date deliveryDate;
    /**deliverySituation*/
    @Excel(name = "Delivery Situation", width = 15)
    @ApiModelProperty(value = "deliverySituation")
    private String deliverySituation;
    /**etd*/
    @Excel(name = "Etd", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "etd")
    private Date etd;
    /**delay*/
    @Excel(name = "Delay", width = 15)
    @ApiModelProperty(value = "delay")
    private String delay;
    /**eta*/
    @Excel(name = "Eta", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "eta")
    private Date eta;
    /**status*/
    @Excel(name = "Status", width = 15)
    @ApiModelProperty(value = "status")
    private String status;
    /**statusdate*/
    @Excel(name = "Status Date", width = 15, format = "yyyy-MM-dd")
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhotoString(String photoString) {
        this.photoString = photoString;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setParamData1(String paramData1) {
        this.paramData1 = paramData1;
    }

    public String getParamData() {
        String p = "{\"color\":\"" +paramData1+"\"}";

        return p;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getChop() {
        return chop;
    }

    public void setChop(String chop) {
        this.chop = chop;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getIncoterm() {
        return incoterm;
    }

    public void setIncoterm(String incoterm) {
        this.incoterm = incoterm;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmountRmb() {
        return amountRmb;
    }

    public void setAmountRmb(String amountRmb) {
        this.amountRmb = amountRmb;
    }

    public String getAmountUsd() {
        return amountUsd;
    }

    public void setAmountUsd(String amountUsd) {
        this.amountUsd = amountUsd;
    }

    public String getDepositRmb() {
        return depositRmb;
    }

    public void setDepositRmb(String depositRmb) {
        this.depositRmb = depositRmb;
    }

    public String getDepositUsd() {
        return depositUsd;
    }

    public void setDepositUsd(String depositUsd) {
        this.depositUsd = depositUsd;
    }

    public String getDepositPay() {
        return depositPay;
    }

    public void setDepositPay(String depositPay) {
        this.depositPay = depositPay;
    }

    public String getBalanceRmb() {
        return balanceRmb;
    }

    public void setBalanceRmb(String balanceRmb) {
        this.balanceRmb = balanceRmb;
    }

    public String getBalanceUsd() {
        return balanceUsd;
    }

    public void setBalanceUsd(String balanceUsd) {
        this.balanceUsd = balanceUsd;
    }

    public String getBalancePay() {
        return balancePay;
    }

    public void setBalancePay(String balancePay) {
        this.balancePay = balancePay;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliverySituation() {
        return deliverySituation;
    }

    public void setDeliverySituation(String deliverySituation) {
        this.deliverySituation = deliverySituation;
    }

    public Date getEtd() {
        return etd;
    }

    public void setEtd(Date etd) {
        this.etd = etd;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusdate() {
        return statusdate;
    }

    public void setStatusdate(Date statusdate) {
        this.statusdate = statusdate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
