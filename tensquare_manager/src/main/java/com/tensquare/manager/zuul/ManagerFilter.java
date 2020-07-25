package com.tensquare.manager.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul 过滤器");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        if (request.getMethod().equals("OPTIONS")){
            return  null;
        }
        String url = request.getRequestURI().toString();
        if (url.indexOf("/admin/login")> 0){
            System.out.println("登陆页面"+url);
        }
        String authHeader = (String)request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                if ("admin".equals(claims.get("roles"))) {
                    requestContext.addZuulRequestHeader("Authorization", authHeader);
                    System.out.println("token 验证通过，添加了头信息" + authHeader);
                }
            }
        }
            requestContext.setSendZuulResponse(false);//终止运行
            requestContext.setResponseStatusCode(401);//http状态码
            requestContext.setResponseBody("无权访问");
            requestContext.getResponse().setContentType("text/html;charset=UTF‐8");

            return null;
    }
}
