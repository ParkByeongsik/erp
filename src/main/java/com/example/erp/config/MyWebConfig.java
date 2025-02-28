package com.example.erp.config;

import com.example.erp.common.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    //정적리소스의 경로를 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //특정path로 요청하면 실제 파일이 저장된 위치를 연결해서 리소스를 가져올 수 있도록 처리
        registry.addResourceHandler("/download/**")   //   /download로 하는 모든 요청에 대해서
                .addResourceLocations("file:/C:/fullstack7/upload/") ; //실제위치
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //인터셉터를 등록
        registry.addInterceptor(new LoginInterceptor())
                .order(1)   //우선순위
                .addPathPatterns("/**") //인터셉터를 적용할 요청path(컨트롤러)
                .excludePathPatterns("/index.html","/emp/login","/member/spring/login",
                                    "/board/list","/images/**","/common/css/**");//인터셉터를 제외할 컨트롤러의 path,정적리소스
    }
}










