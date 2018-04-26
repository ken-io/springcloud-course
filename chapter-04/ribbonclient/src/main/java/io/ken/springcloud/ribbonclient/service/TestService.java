package io.ken.springcloud.ribbonclient.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "indexError")
    public Object index() {
        return restTemplate.getForObject("http://testservice", String.class);
    }

    public Object plus(int numA, int numB) {
        String url = String.format("http://testservice/plus?numA=%s&numB=%s", numA, numB);
        return restTemplate.getForObject(url, String.class);
    }

    public Object indexError() {
        return "{\"code\": 999,\"message\": \"熔断\"}";
    }
}
