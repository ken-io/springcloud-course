package io.ken.springcloud.feignclient.controller;

import com.netflix.discovery.converters.Auto;
import io.ken.springcloud.feignclient.model.Plus;
import io.ken.springcloud.feignclient.service.TestService;
import io.ken.springcloud.feignclient.service.TestServiceZuul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private TestServiceZuul testServiceZuul;

    @RequestMapping("/")
    public Object index() {
        return "feign client";
    }

    @RequestMapping("/ti")
    public Object ti() {
        return testService.indexService();
    }

    @RequestMapping("/ti-zuul")
    public Object ti_zuul() {
        return testServiceZuul.indexService();
    }

    @RequestMapping("/plus")
    public Object plus(@RequestParam("numa") int numA, @RequestParam("numb") int numB) {
        return testService.plusService(numA, numB);
    }

    @RequestMapping("/plusab")
    public Object plusA(@RequestParam("numa") int numA, @RequestParam("numb") int numB) {
        Plus plus = new Plus();
        plus.setNumA(numA);
        plus.setNumB(numB);
        return testService.plusabService(plus);
    }

    @RequestMapping("/plus2")
    public Object plus2(Plus plus) {
        return testService.plus2Service(plus);
    }


}
