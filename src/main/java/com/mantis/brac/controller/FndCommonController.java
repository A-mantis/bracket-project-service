package com.mantis.brac.controller;


import com.mantis.brac.common.feign.BracFeignConfig;
import com.mantis.brac.common.feign.FeignUtil;
import com.mantis.brac.common.http.Response;
import com.mantis.brac.common.utils.JsonUtil;
import com.mantis.brac.common.utils.StringUtil;
import com.mantis.brac.common.utils.jwt.RSAUtil;
import com.mantis.brac.dto.FndLookupTypeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/4 12:10
 * @history: 1.2020/4/4 created by wei.wang
 */
@RestController
@RequestMapping(value = "/common")
public class FndCommonController {

    private static Logger logger = LoggerFactory.getLogger(FndCommonController.class);

    @Autowired
    BracFeignConfig feignConfig;

//    @Autowired
//    BracFeignClient feignClient;


    /**
     * Get查询值列表
     *
     * @param lookupType
     * @return
     */
    @GetMapping(value = "/lookup/get/queryLookupValue")
    public Response getQueryFndLookupValues(@RequestParam(value = "lookupType", required = false) String lookupType) {
        String message = "test   Jpa     Message";
        String result = "test   Jpa     Result";
        try {
            if (StringUtil.strEmpty(lookupType)) {
                return Response.ok().setData(result);
            }
            return Response.ok(message).setData(result);
        } catch (Exception e) {
            return Response.error(message).setData(result);
        }
    }


    /**
     * Post查询值列表
     *
     * @param fndLookupValue
     * @return
     */
    @PostMapping(value = "/lookup/post/queryLookupValue")
    public Response postQueryFndLookupValues(@RequestBody FndLookupTypeRequest fndLookupValue) {
        String message = "test   Jpa     Message";
        String result = "test   Jpa     Result";
        try {
            if (StringUtil.strEmpty(fndLookupValue.getLookupType())) {
                return Response.ok().setData(result);
            }
            return Response.ok(message).setData(result);
        } catch (Exception e) {
            return Response.error(message).setData(result);
        }
    }


    /**
     * Post查询值列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/lookup/post/queryLookupValue2")
    public Response postQueryFndLookupValues(HttpServletRequest req) {

        String message = "test   Jpa     Message";
        String result = "test   Jpa     Result";
        StringBuilder reqJson = JsonUtil.getJson(req);
        try {
            RSAUtil.jwtTokenInitUserProperty();
            return Response.ok(message).setData(result);
        } catch (Exception e) {
            return Response.error(e.getMessage()).setData(result);
        }
    }

    @PostMapping(value = "/lookup/post/thirdService")
    public Response postQueryThirdService(HttpServletRequest req) {
        String message = "test   Jpa     Message";
        String result = "test   Jpa     Result";
        StringBuilder reqJson = JsonUtil.getJson(req);

        //RSAUtil.jwtTokenInitUserProperty();
//        try {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("a", "A");
        headerMap.put("a1", "A1");

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("userId", "A");
        requestMap.put("userName", "zero");
        feign.Response requestResult = feignConfig.setHeader(headerMap).setUri("http://localhost:9004/check/userValidGet").setRequest(requestMap).get().execute();
        System.out.println(FeignUtil.feignResponseBody(requestResult));
        return Response.ok(message).setData(result);
//        } catch (Exception e) {
//            return Response.error(e.getMessage()).setData(result);
//        }
    }

//    /**
//     * 上传文件
//     *
//     * @param req
//     * @return
//     */
//    @PostMapping(value = "/lookup/post/uploadFile")
//    public Response postQueryUploadFile(HttpServletRequest req) {
//        String message = "test   Jpa     Message";
//        String result = "test   Jpa     Result";
//        StringBuilder reqJson = JsonUtil.getJson(req);
//        try {
//            Map<String, Object> headerMap = new HashMap<>();
//            headerMap.put("a", "A");
//            headerMap.put("a1", "A1");
//            feign.Response requestResult = feignConfig.setHeader(headerMap).setUri("http://localhost:9004/check/userValidGet?userId=a&userName=zero").get().execute();
//            System.out.println(FeignUtil.feignResponseBody(requestResult));
//            return Response.ok(message).setData(result);
//        } catch (Exception e) {
//            return Response.error(e.getMessage()).setData(result);
//        }
//    }

//    /**
//     * 下载文件
//     *
//     * @param req
//     * @return
//     */
//    @PostMapping(value = "/lookup/post/downloadFile")
//    public Response postQueryDownloadFile(HttpServletRequest req) {
//        String message = "test   Jpa     Message";
//        String result = "test   Jpa     Result";
//        StringBuilder reqJson = JsonUtil.getJson(req);
//        try {
//            Map<String, Object> headerMap = new HashMap<>();
//            headerMap.put("a", "A");
//            headerMap.put("a1", "A1");
//            feign.Response requestResult = feignConfig.setHeader(headerMap).setUri("http://localhost:9004/check/userValidGet?userId=a&userName=zero").get().execute();
//            System.out.println(FeignUtil.feignResponseBody(requestResult));
//            return Response.ok(message).setData(result);
//        } catch (Exception e) {
//            return Response.error(e.getMessage()).setData(result);
//        }
//    }


//    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
//    public void downloadFile(HttpServletResponse response){
//        String filePath = "E:\\README.txt";
//        File file = new File(filePath);
//        InputStream in = null;
//        if(file.exists()){
//            try {
//                OutputStream out = response.getOutputStream();
//                in = new FileInputStream(file);
//                byte buffer[] = new byte[1024];
//                int length = 0;
//                while ((length = in.read(buffer)) >= 0){
//                    out.write(buffer,0,length);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if(in != null){
//                    try {
//                        in.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }


    @GetMapping(value = "downloadFile")
    public void downloadFile(@RequestParam(value = "fileName") String fileName, @RequestParam(value = "dDocName") String dDocName, HttpServletResponse response) throws IOException {
        if (fileName != null) {
            Map<String, Object> headerMap = new HashMap<>();
            headerMap.put("a", "A");
            headerMap.put("a1", "A1");

            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("dDocName", dDocName);
            feign.Response file = feignConfig.setUri("http://wcdev.smec-cn.com:8888/ntkoapp/download").setHeader(headerMap).setRequest(requestMap)
                    .get().execute();
            System.out.println(file);
            feign.Response.Body body = file.body();
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
            byte[] buffer = new byte[1024];
            try (InputStream input = body.asInputStream();
                 BufferedInputStream bis = new BufferedInputStream(input)) {
                OutputStream os = response.getOutputStream();
                int i = 0;
                while ((i = bis.read(buffer)) != -1) {
                    os.write(buffer, 0, i);
                    os.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
