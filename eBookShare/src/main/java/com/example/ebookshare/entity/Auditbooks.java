package com.example.ebookshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @since 2023-05-04
 */
@Getter
@Setter
@ApiModel(value = "Auditbooks对象", description = "")
public class Auditbooks implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bookid", type = IdType.AUTO)
    private Integer bookid;

    private String bookname;

    private String author;

    private String publisher;

    private String isbn;

    private String description;

    private String coverimage;

    private String category;

    private Integer likes;

    private Integer favorites;

    private LocalDateTime releasetime;

    private String type;

    private Long size;

    private Boolean isdelete;

    private Boolean enable;

    private String md5;

    private String filename;

    private String url;

    private String suggestions;
}
