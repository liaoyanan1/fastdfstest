package com.example.demo;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
        System.out.println(1);
        ClientGlobal.initByProperties("fastdfs-client.properties");
        System.out.println("network_timeout="+ClientGlobal.g_network_timeout+"ms");
        System.out.println("charset="+ClientGlobal.g_charset);
        TrackerClient tc = new TrackerClient();
        TrackerServer ts = tc.getTrackerServer();
        if (ts==null){
            System.out.println("getConnection return null");
            return "-1";
        }
        StorageServer storeStorage = tc.getStoreStorage(ts);
        if(storeStorage==null){
            System.out.println("getStoreStorage return null");
            return "-1";
        }
        StorageClient1 storageClient1 = new StorageClient1(ts,storeStorage);
        String path = storageClient1.upload_appender_file1(file.getBytes(),".txt",null);
        return path;
    }
}
