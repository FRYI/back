package org.jeecg.modules.demo.supplier.entity;

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
 * @Description: supplier
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Data
@TableName("supplier")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="supplier对象", description="supplier")
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private String id;
	/**pin*/
	@Excel(name = "Pin", width = 15)
    @ApiModelProperty(value = "pin")
    private String pin;
	/**supplier*/
	@Excel(name = "Supplier", width = 15)
    @ApiModelProperty(value = "supplier")
    private String supplier;
	/**contact*/
	@Excel(name = "Contact", width = 15)
    @ApiModelProperty(value = "contact")
    private String contact;
	/**tel*/
	@Excel(name = "Tel", width = 15)
    @ApiModelProperty(value = "tel")
    private String tel;
	/**email*/
	@Excel(name = "Email", width = 15)
    @ApiModelProperty(value = "email")
    private String email;
	/**website*/
	@Excel(name = "Website", width = 15)
    @ApiModelProperty(value = "website")
    private String website;
	/**city*/
	@Excel(name = "City", width = 15)
    @ApiModelProperty(value = "city")
    private String city;
	/**address*/
	@Excel(name = "Address", width = 15)
    @ApiModelProperty(value = "address")
    private String address;
	/**zipCode*/
	@Excel(name = "Zip Code", width = 15)
    @ApiModelProperty(value = "zipCode")
    private String zipCode;
	/**scope*/
	@Excel(name = "Scope", width = 15)
    @ApiModelProperty(value = "scope")
    private String scope;
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
