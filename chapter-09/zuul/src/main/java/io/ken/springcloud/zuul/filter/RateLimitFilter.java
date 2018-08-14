package io.ken.springcloud.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class RateLimitFilter extends ZuulFilter {

    private static Logger log = Logger.getLogger(RateLimitFilter.class.toString());
    private static RateLimiter rateLimiter = RateLimiter.create(1);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();
            log.info(request.getRequestURI());
            HttpServletResponse response = ctx.getResponse();
            if (!rateLimiter.tryAcquire()) {
//                HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
//                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
//                response.setStatus(httpStatus.value());
//                response.getWriter().append(httpStatus.getReasonPhrase());
//                ctx.setSendZuulResponse(false);
//                throw new ZuulException(
//                        httpStatus.getReasonPhrase(),
//                        httpStatus.value(),
//                        httpStatus.getReasonPhrase()
//                );
                ctx.setSendZuulResponse(false);
                response.setStatus(200);
                response.getWriter().write("{\"code\": 99999,\"message\": \"too many requests.\"}");
            }
        } catch (Exception e) {
            log.warning(e.getMessage());
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }
}
