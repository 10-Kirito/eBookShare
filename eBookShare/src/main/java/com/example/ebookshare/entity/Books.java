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

    private  long size;

    private  boolean isdelete;

    private  boolean enable;

    private  String md5;

    private String filename;

    private String url;

    private Integer downloads;


    public Books(Auditbooks auditbooks){
        this.bookname = auditbooks.getBookname();
        this.author = auditbooks.getAuthor();
        this.publisher = auditbooks.getPublisher();
        this.isbn = auditbooks.getIsbn();
        this.description = auditbooks.getDescription();
        this.coverimage = auditbooks.getCoverimage();
        this.category = auditbooks.getCategory();
        this.likes = 0;
        this.favorites = 0;
        this.releasetime = auditbooks.getReleasetime();
        this.type = auditbooks.getType();
        this.size = auditbooks.getSize();
        this.isdelete = false;
        this.enable = true;
        this.md5 = auditbooks.getMd5();
        this.filename = auditbooks.getFilename();
        this.url = auditbooks.getUrl();
    }
    public Books(){

    }
}
