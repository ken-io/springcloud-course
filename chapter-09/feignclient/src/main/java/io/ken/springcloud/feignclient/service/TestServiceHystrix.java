package io.ken.springcloud.feignclient.service;

import io.ken.springcloud.feignclient.model.Plus;
import io.ken.springcloud.feignclient.model.Result;
import org.springframework.stereotype.Component;

@Component
public class TestServiceHystrix implements TestService {

    @Override
    public String indexService() {
        return "{\"code\": 999,\"message\": \"熔断\"}";
    }

    @Override
    public Result plusService(int numA, int numB) {
        Result result = new Result();
        result.setCode(999);
        result.setMessage("熔断");
        return new Result();
    }

    @Override
    public Result plusabService(Plus plus) {
        Result result = new Result();
        result.setCode(999);
        result.setMessage("熔断");
        return new Result();
    }

    @Override
    public Result plus2Service(Plus plus) {
        Result result = new Result();
        result.setCode(999);
        result.setMessage("熔断");
        return new Result();
    }
}
