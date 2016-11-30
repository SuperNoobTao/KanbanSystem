//package cc.superliar.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Created by shentao on 2016/11/30.
// */
//@RestController
//@RequestMapping("/login")
//public class AccessController {
//
//    @Autowired
//    CurrentUser user;
//
//    @Autowired
//    MappingService ms;
//
//    @Autowired
//    Result result;
//
//    @RequestMapping("/success")
//    Result loginSuccess() {
//        result.put("user", user);
//        result.put("targetUrl", ms.request().getAttribute("targetUrl"));
//        return result;
//    }
//
//    @RequestMapping("/failure")
//    Result loginFailure() {
//        // Bad credentials 用户名或密码错误
//        String error = ms.request().getAttribute("error").toString();
//        result.put("msg", error);
//        return result;
//    }
//
//    @RequestMapping("/user")
//    Result loginUser() {
//        result.put("user", user);
//        return result;
//    }
//
//}
//
