package io.ken.springcloud.helloservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping("/")
    public Object index() {
        return "hello service";
    }

    @RequestMapping("/info")
    public Object info() {
        return discoveryClient.getServices();
    }
}
