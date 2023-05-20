package com.example.ebookshare;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class EBookShareApplicationTests {

    @Test
    void contextLoads() {
        String url1 = "http://124.71.166.37:9091/file/b5219fc523c54650abac9b6beadc0539.pdf";
        String url2 = "http://124.71.166.37:9091/file/bookpic/9a3486f2bb7343ce96bbef2f28e7ede8.png";
        String uuid = url1.replaceFirst("http://124.71.166.37:9091/file/", "");
        String picuuid = url2.replaceFirst("http://124.71.166.37:9091/file/bookpic", "");

        System.out.printf(uuid);
        System.out.println(picuuid);


    }

}
