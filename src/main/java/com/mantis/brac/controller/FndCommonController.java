package com.mantis.brac.controller;


import com.mantis.brac.common.feign.CustomerFeignConfig;
import com.mantis.brac.common.feign.FeignUtil;
import com.mantis.brac.common.feign.NormalFeginClient;
import com.mantis.brac.common.profile.UserProfile;
import com.mantis.brac.common.utils.JsonUtil;
import com.mantis.brac.common.utils.StringUtil;
import com.mantis.brac.dto.FndLookupTypeRequest;
import com.mantis.brac.pojo.User;
import com.mantis.brac.session.BracSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.IOException;
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
    CustomerFeignConfig customerFeignConfig;

    @Autowired
    NormalFeginClient normalFeginClient;


    /**
     * Get查询值列表
     *
     * @param lookupType
     * @return
     */
    @GetMapping(value = "/lookup/get/queryLookupValue")
    public String getQueryFndLookupValues(@RequestParam(value = "lookupType", required = false) String lookupType) {
        String message = "test   Jpa     Message";
        String result = "test   Jpa     Result";
        if (StringUtil.strEmpty(lookupType)) {
            return result;
        }
        return result;
    }

    @DeleteMapping(value = "/lookup/get/queryLookupValueA")
    public String getQueryFndLookupValuesA(@RequestParam(value = "lookupType", required = false) String lookupType) {
        String message = "test   Jpa     Message";
        String result = "test   Jpa     Result";
        if (StringUtil.strEmpty(lookupType)) {
            return result;
        }
        return result;
    }


    /**
     * Post查询值列表
     *
     * @param fndLookupValue
     * @return
     */
    @PostMapping(value = "/lookup/post/queryLookupValue")
    public String postQueryFndLookupValues(HttpServletRequest req, HttpServletResponse httpServletResponse, @Valid @RequestBody FndLookupTypeRequest fndLookupValue, BindingResult bindingResult) {
        String message = "test   Jpa     Message";
        String result = "test   Jpa     Result";
        if (StringUtil.strEmpty(fndLookupValue.getLookupType())) {
            return result;
        }
        return result;
    }


    /**
     * Post查询值列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/lookup/post/queryLookupValue2")
    public String postQueryFndLookupValues(HttpServletRequest req) {
        String message = "test   Jpa     Message";
        String result = "test   Jpa     Result";
        StringBuilder reqJson = JsonUtil.getJson(req);
        UserProfile userProfile = BracSession.getUserProfile();
        System.out.println(userProfile);
        return result;
    }

    /**
     * Post查询值列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/lookup/post/queryLookupValue2A")
    public String postQueryFndLookupValuesA(HttpServletRequest req) {

        String message = "test   Jpa     Message";
        String result = "test   Jpa     Result";
        StringBuilder reqJson = JsonUtil.getJson(req);
        UserProfile userProfile = BracSession.getUserProfile();
        System.out.println(userProfile);
        return result;
    }

    /**
     * Post查询值列表
     *
     * @param req
     * @return
     */
    @PutMapping(value = "/lookup/post/queryLookupValue2B")
    public String postQueryFndLookupValuesB(HttpServletRequest req) {

        String message = "test   Jpa     Message";
        String result = "test   Jpa     Result";
        StringBuilder reqJson = JsonUtil.getJson(req);
        UserProfile userProfile = BracSession.getUserProfile();
        System.out.println(userProfile);
        return result;
    }

    /**
     * Post查询值列表
     *
     * @param req
     * @return
     */
    @PatchMapping(value = "/lookup/post/queryLookupValue2C")
    public String postQueryFndLookupValuesC(HttpServletRequest req) {

        String message = "test   Jpa     Message";
        String result = "test   Jpa     Result";
        StringBuilder reqJson = JsonUtil.getJson(req);
        UserProfile userProfile = BracSession.getUserProfile();
        System.out.println(userProfile);
        return result;
    }

    /**
     * Post查询值列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/lookup/post/queryLookupValue3")
    public String postQueryFndLookupValues(HttpServletRequest req, HttpServletResponse res) {
        //默认Excel名称
        try {
//            response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode("收据单","UTF-8"));
//            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//            response.setCharacterEncoding("UTF-8");
            String resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            BufferedOutputStream out = new BufferedOutputStream(res.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OKIJH";
    }


    @PostMapping(value = "/lookup/customer/thirdService")
    public String postQueryThirdService(HttpServletRequest req) {
        String message = "Message";
        String result = "Result";
        StringBuilder reqJson = JsonUtil.getJson(req);
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("a", "A");
        headerMap.put("a1", "A1");

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("userId", "A");
        requestMap.put("userName", "zero");
        feign.Response requestResult1 = customerFeignConfig.setHeader(headerMap).setUri("http://localhost:9004/check/userValidGet").setRequest(requestMap).get().execute();
        feign.Response requestResult2 = customerFeignConfig.setHeader(headerMap).setUri("http://localhost:9004/check/testAspectUserValidPost").setRequest(requestMap).post().execute();
        System.out.println(FeignUtil.feignResponseBody(requestResult1));
        System.out.println(FeignUtil.feignResponseBody(requestResult2));
        return result;
    }

    @GetMapping(value = "/thirdService")
    public String requestThirdService(HttpServletRequest req) {
        String message = "test   Jpa     Message";
        String result = "test   Jpa     Result";

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("a", "A");
        headerMap.put("a1", "A1");

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("userId", "A");
        requestMap.put("userName", "zero");

        User user = new User();
        user.setUserId("A");
        user.setUserName("zero");

        feign.Response requestResult1 = normalFeginClient.normalGet("A", "zero");

        feign.Response requestResult2 = normalFeginClient.mapGet(headerMap, requestMap);

        feign.Response requestResult3 = normalFeginClient.pojoGet(headerMap, user);

        feign.Response requestResult4 = normalFeginClient.normalPost(requestMap);

        feign.Response requestResult5 = normalFeginClient.pojoPost(headerMap, user);

        System.out.println(FeignUtil.feignResponseBody(requestResult1));
        System.out.println(FeignUtil.feignResponseBody(requestResult2));
        System.out.println(FeignUtil.feignResponseBody(requestResult3));
        System.out.println(FeignUtil.feignResponseBody(requestResult4));
        System.out.println(FeignUtil.feignResponseBody(requestResult5));
        return result;
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


//    @GetMapping(value = "downloadFile")
//    public void downloadFile(@RequestParam(value = "fileName") String fileName, @RequestParam(value = "dDocName") String dDocName, HttpServletResponse response) throws IOException {
//        if (fileName != null) {
//            Map<String, Object> headerMap = new HashMap<>();
//            headerMap.put("a", "A");
//            headerMap.put("a1", "A1");
//
//            Map<String, Object> requestMap = new HashMap<>();
//            requestMap.put("dDocName", dDocName);
//            feign.Response file = feignConfig.setUri("http://wcdev.smec-cn.com:8888/ntkoapp/download").setHeader(headerMap).setRequest(requestMap)
//                    .get().execute();
//            System.out.println(file);
//            feign.Response.Body body = file.body();
//            response.setContentType("application/force-download");
//            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
//            byte[] buffer = new byte[1024];
//            try (InputStream input = body.asInputStream();
//                 BufferedInputStream bis = new BufferedInputStream(input)) {
//                OutputStream os = response.getOutputStream();
//                int i = 0;
//                while ((i = bis.read(buffer)) != -1) {
//                    os.write(buffer, 0, i);
//                    os.flush();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

}
