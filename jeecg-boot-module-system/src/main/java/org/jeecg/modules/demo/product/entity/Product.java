package org.jeecg.modules.demo.product.entity;

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
 * @Description: product
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */

@TableName("product")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="product对象", description="product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private String id;
    /**season*/
    @Excel(name = "Season", width = 15)
    @ApiModelProperty(value = "season")
    private java.lang.String season;
	/**sku*/
	@Excel(name = "Sku", width = 15)
    @ApiModelProperty(value = "sku")
    private String sku;
	/**project*/
	@Excel(name = "Project", width = 15)
    @ApiModelProperty(value = "project")
    private String project;
	/**productName*/
	@Excel(name = "ProductName", width = 15)
    @ApiModelProperty(value = "productName")
    private String productName;
	/**supplier*/
	@Excel(name = "Supplier", width = 15)
    @ApiModelProperty(value = "supplier")
    private String supplier;
	/**paramData*/
	@Excel(name = "ParamData", width = 15)
    @ApiModelProperty(value = "paramData")
    private String paramData;
	/**description*/
	@Excel(name = "Description", width = 15)
    @ApiModelProperty(value = "description")
    private String description;
	/**photo*/
	@Excel(name = "Photo", width = 15)
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
            System.out.println("pass "+photo);
            return new String(photo,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
	/**created*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "created")
    private Date createTime;
	/**updated*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updated")
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

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhotoString(String photoString) {
        this.photoString = photoString;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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
