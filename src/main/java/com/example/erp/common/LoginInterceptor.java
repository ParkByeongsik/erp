package com.example.erp.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

//사용자정의 인터셉터정의
public class LoginInterceptor implements HandlerInterceptor {
    //컨트롤러 호출되기 전에 적용할 내용을 preHandle에 명시
    //true/false
    //true=>컨트롤러요청
    //false=>컨트롤러요청하지 않고 response
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //로그인사용자인 경우 세션에 user라는 이름의 어트리뷰트가 저장되어 있으므로
        //user가 없으면 로그인이 처리되지 않았음을 의미
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        //세션객체를 추출 - 기존세션을 받아올때 사용하는 메소드
        //                기존에 사용하던 세션이 있으면 리턴하고 없으면 null반환
        HttpSession session =  request.getSession(false);
        if(session == null || session.getAttribute("user")==null) {
            //로그인하지 않은 상태
            response.sendRedirect("/erp/emp/login");
            //로그인 하지 않은 사용자는 다음으로 넘어가지 못하도록 fasle리턴
            return false;
        }
        //로그인된 사용자는 다음으로 넘어갈 수 있도록 true리턴
        return true;
    }
}
