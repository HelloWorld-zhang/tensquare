package com.tensquare.sms;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; ;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;
import util.SmsUtil;


import java.util.Map;


@SpringBootApplication
@EnableEurekaClient
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class,args);
    }

    @Component
    @RabbitListener(queues = "sms")
    public class SmsListener{

        @Autowired
        private SmsUtil smsUtil;

        @Value("${aliyun.sms.template_code}")
        private String template_code;//模板编号
        @Value("${aliyun.sms.sign_name}")
         private String sign_name;//签名

        @RabbitHandler
        public void sendSms(Map<String,String> message){
            System.out.println("手机号："+message.get("mobile"));
            System.out.println("验证码："+message.get("code"));

            try {
               smsUtil.sendSms(map.get("mobile"),template_code,sign_name,"{\"number\":\""+ map.get("code") +"\"}");
                }catch(ClientException e){
                     e.printStackTrace();
                 }
        }
    }
}
