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
@Data
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
	/**sku*/
	@Excel(name = "sku", width = 15)
    @ApiModelProperty(value = "sku")
    private String sku;
	/**project*/
	@Excel(name = "project", width = 15)
    @ApiModelProperty(value = "project")
    private String project;
	/**productName*/
	@Excel(name = "productName", width = 15)
    @ApiModelProperty(value = "productName")
    private String productName;
	/**supplier*/
	@Excel(name = "supplier", width = 15)
    @ApiModelProperty(value = "supplier")
    private String supplier;
	/**paramData*/
	@Excel(name = "paramData", width = 15)
    @ApiModelProperty(value = "paramData")
    private String paramData;
	/**description*/
	@Excel(name = "description", width = 15)
    @ApiModelProperty(value = "description")
    private String description;
	/**photo*/
	@Excel(name = "photo", width = 15)
    private transient String photoString;

    private byte[] photo;

    public byte[] getPhoto(){
        if(photoString==null){
            return null;
        }
        try {
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
}
