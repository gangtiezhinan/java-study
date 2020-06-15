package com.java.study.javastudy;


import org.apache.juli.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname LogTest
 * @Description
 * @Date 2020/6/4 11:10
 * @Author HXL
 */
@SpringBootTest
public class LogTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());





    @Test
    void test(){
        //日志级别，由低到高  trace<debug<info<warn<error
        //可以更改日志输出级别来决定收到哪个级别的日志信息  去配置文件里面写logging.level...调整级别
        logger.trace("这是跟踪日志......");
        logger.debug("这是debug日志.....");
        //spring boot 默认日志输出级别为 info
        logger.info("这是信息打印日志.........");
        logger.warn("这是冲突告警信息日志......");
        logger.error("这是错误日志......" );
    }






    /**
     *restTemplate  的 post 请求
     * @date 2020/6/5 16:56
     * @author HXL
     * @exception
     * @return void
     */
    @Test
    void restTemplate1(){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.GET;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_JSON);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(null, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<String> response = client.exchange("http://47.110.144.187:8504/road/api/isl/road/deviceStatus/getImeiList", method, requestEntity, String.class);
        System.out.println(response.getBody());
    }

    @Test
    void test3(){






        List<String> allList = Arrays.asList( "861410049815011",
                "861410049800310",
                "861410049886483",
                "861410049700000",
                "861410049886475",
                "861410049800393",
                "861410049801292",
                "861410049837478",
                "861410049790982",
                "861410049789794",
                "861410049790974",
                "861410049799926",
                "861410049800484",
                "861410049800658",
                "861410049814717",
                "861410049849358",
                "861410049817306",
                "861410049813032",
                "861410049790586",
                "861410049821357",
                "861410049875676");


        List<String> nowList =    Arrays.asList("861410049815383","861410049775603","861410049774978","861410049774903");
        //今日没采集上来的
        List<String> diffImeiListallList = allList.stream()
                .filter(i -> new ArrayList<>(nowList)
                        .contains(i))
                .collect(Collectors.toList());
        System.out.println(diffImeiListallList);
    }


    public static void main(String[] args) {
        System.out.println(Instant.now().getEpochSecond());
    }
}
