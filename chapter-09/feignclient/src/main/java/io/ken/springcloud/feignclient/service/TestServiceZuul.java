package io.ken.springcloud.feignclient.service;

import io.ken.springcloud.feignclient.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "zuul")
public interface TestServiceZuul {

    @RequestMapping(value = "/testservice/", method = RequestMethod.GET)
    String indexService();

    @RequestMapping(value = "/testservice/plus", method = RequestMethod.GET)
    Result plusService(@RequestParam(name = "numA") int numA, @RequestParam(name = "numB") int numB);
}
