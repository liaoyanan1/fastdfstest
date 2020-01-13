package com.example.demo;

import com.example.utils.FdfsUtil;
import org.csource.common.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "index";
    }

    @RequestMapping("/test1")
    @ResponseBody
    public String test1(MultipartFile file) throws IOException, MyException {
        return FdfsUtil.upLoad(file);
    }

    @RequestMapping("/test2")
    public void test2(String path, HttpServletResponse response) throws IOException, MyException {
        byte[] bytes = FdfsUtil.downLoad(path);
        response.getOutputStream().write(bytes);
    }

    @RequestMapping("/test3")
    public String test3(){
        return "test";
    }
}
