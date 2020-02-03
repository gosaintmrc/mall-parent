package com.mall.filter;

import com.mall.util.JwtUtil;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 20:35 2020/2/3
 * @Modified By:
 */
@Component
public class AuthorizationFilter implements GlobalFilter, Ordered {

    static final String AUTHORIZATION_TOKEN = "Authorization";

    @Override
    public Mono<Void> filter(
            final ServerWebExchange exchange, final GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        /**
         * 1 获取令牌信息
         * 2 如果没有令牌，拦截
         * 3 如果有令牌，则校验令牌是否有效
         */
        String token = request.getHeaders().getFirst(AUTHORIZATION_TOKEN);

        boolean hasToken=true;
        if (StringUtils.isEmpty(token)) {
            token = request.getQueryParams().getFirst(AUTHORIZATION_TOKEN);
            hasToken=false;
        }

        if (StringUtils.isEmpty(token)) {
            HttpCookie cookie = request.getCookies().getFirst(AUTHORIZATION_TOKEN);
            if (cookie != null) {
                token = cookie.getValue();
            }
        }

        if (StringUtils.isEmpty(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        try {
            JwtUtil.parseJwt(token);
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        request.mutate().header(AUTHORIZATION_TOKEN,token);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
