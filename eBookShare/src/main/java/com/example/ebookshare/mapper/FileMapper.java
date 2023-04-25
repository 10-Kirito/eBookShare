package com.example.ebookshare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ebookshare.entity.Files;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FileMapper extends BaseMapper<Files> {

}
