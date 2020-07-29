package org.jeecg.modules.demo.client.entity;

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
 * @Description: client
 * @Author: jeecg-boot
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Data
@TableName("client")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="client对象", description="client")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private String id;
	/**client*/
	@Excel(name = "client", width = 15)
    @ApiModelProperty(value = "client")
    private String client;
	/**contact*/
	@Excel(name = "contact", width = 15)
    @ApiModelProperty(value = "contact")
    private String contact;
	/**tel*/
	@Excel(name = "tel", width = 15)
    @ApiModelProperty(value = "tel")
    private String tel;
	/**email*/
	@Excel(name = "email", width = 15)
    @ApiModelProperty(value = "email")
    private String email;
	/**website*/
	@Excel(name = "website", width = 15)
    @ApiModelProperty(value = "website")
    private String website;
	/**city*/
	@Excel(name = "city", width = 15)
    @ApiModelProperty(value = "city")
    private String city;
	/**address*/
	@Excel(name = "address", width = 15)
    @ApiModelProperty(value = "address")
    private String address;
	/**zipCode*/
	@Excel(name = "zipCode", width = 15)
    @ApiModelProperty(value = "zipCode")
    private String zipCode;
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
