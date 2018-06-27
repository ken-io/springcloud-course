package io.ken.springcloud.testservice.controller;

import io.ken.springcloud.testservice.model.Plus;
import io.ken.springcloud.testservice.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${app.ip}")
    private String address;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/")
    public Object index() {
        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("hello");
        return result;
    }

    @RequestMapping("/plus")
    public Object plus(Plus plus) {
        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("success");
        result.setContent(plus.getNumA() + plus.getNumB());
        return result;
    }

    @RequestMapping("/plus2")
    public Object plus2(@RequestBody Plus plus) {
        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("success");
        result.setContent(plus.getNumA() + plus.getNumB());
        return result;
    }

}
