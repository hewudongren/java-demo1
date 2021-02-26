//package cn.jwis.qualityworkflow.client;
//
//
//import cn.jwis.qualityworkflow.interceptor.SessionHelper;
//import feign.RequestInterceptor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
///**
// * Created with IntelliJ IDEA.
// *
// * @CreatedBy: YC 2019/4/12 16:03
// * @Description:
// */
//@Slf4j
//public class FoundationFeignConfig {
//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return requestTemplate ->
//        {
//            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            if (attrs == null) {
//            log.error("获取ServletRequest失败");
//            return;
//        }
//
//
//            requestTemplate.header("Content-Type", "application/json");
//            requestTemplate.header("account", SessionHelper.getCurrentUser().getAccount());
//            requestTemplate.header("accesstoken",SessionHelper.getAccessToken());
//            //for publicKey service
//            requestTemplate.header("id", SessionHelper.getCurrentUser().getId());
//
//            /*
//            requestTemplate.header("charset", "UTF-8");
//            requestTemplate.header("accept-encoding", "UTF-8");
//            requestTemplate.header("Accept-Language", "zh-CN");
//            requestTemplate.header("user-agent", "NPI");
//            requestTemplate.header("user", JSON.toJSONString(SessionHelper.getCurrentUser()));
//            requestTemplate.header("token", SessionHelper.getToken());
//            requestTemplate.header("accesstoken", SessionHelper.getAccessToken());
//            */
//        };
//    }
//}