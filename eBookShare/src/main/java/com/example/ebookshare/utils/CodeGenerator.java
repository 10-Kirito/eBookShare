package com.example.ebookshare.utils;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

//mp代码生成器
public class CodeGenerator {
    public static void main(String[] args) {
      //generate();
        //去掉注释来运行
        //注意：需要手动在Mapper文件里面添加一个@Mapper注解
    }

    private static void generate(){
        FastAutoGenerator.create("jdbc:mysql://124.71.166.37:3306/ebooksys?serverTimezone=GMT%2b8", "root", "root")
                .globalConfig(builder -> {
                    builder.author("sel") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\yijin\\Desktop\\eBookShare\\eBookShare\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.ebookshare") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C:\\Users\\yijin\\Desktop\\eBookShare\\eBookShare\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.controllerBuilder().enableHyphenStyle() //开启驼峰转链字符
                                    .enableRestStyle();         //开启生成RestController控制器
                    builder.addInclude("auditbooks") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板

                .execute();
    }
}
