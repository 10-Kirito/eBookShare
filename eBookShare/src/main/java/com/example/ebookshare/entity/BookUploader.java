package com.example.ebookshare.entity;

import java.io.Serializable;
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
@ApiModel(value = "BookUploader对象", description = "")
public class BookUploader implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("上传者类型，system表示管理员，user表示普通用户")
    private String uploaderType;

    private Integer userId;

    private Integer bookId;


}
