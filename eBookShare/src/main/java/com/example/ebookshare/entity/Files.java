package com.example.ebookshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("books")
public class Files {
    @TableId(value = "bookid", type = IdType.AUTO)
    private Integer bookId;
    private String bookname;
    private String type;
    private Long size;
    private String url;
    private String md5;
    private Boolean isdelete;
    private Boolean enable;
}
