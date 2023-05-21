package com.example.ebookshare.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author sel
 * @since 2023-05-16
 */
@Getter
@Setter
@ApiModel(value = "Relationship对象", description = "")
public class Relationship implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer bookid;

    @ApiModelProperty("是否拥有")
    private Integer isowned;

    @ApiModelProperty("是否收藏")
    private Integer isfavour;

    private Integer userid;

    @ApiModelProperty("是否是上传者")
    private Integer isuploader;

    private Integer score;

    private Integer islike;

}
