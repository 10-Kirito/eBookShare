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
 * @since 2023-04-24
 */
@Getter
@Setter
@ApiModel(value = "Books对象", description = "")
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String bookname;

    private String author;

    private String publisher;

    private String isbn;

    private String description;

    private String coverimage;

    private String downloadlink;

    private String category;

    private Integer likes;

    private Integer favorites;

    private LocalDateTime releasetime;


}
