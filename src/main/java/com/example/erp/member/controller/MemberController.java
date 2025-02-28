package com.example.erp.member.controller;

import com.example.erp.dto.LoginUserInfoDTO;
import com.example.erp.dto.MemberDTO;
import com.example.erp.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/*
@SessionAttributes("user")에서 user라는 것은 세션에 저장할 어트리뷰트 이름(공유명)
스프링MVC프레임워크 내부에서 컨트롤러에서 Model객체에서 user라는 이름으로 공유된 어트리뷰트를 찾아서
세션에도 공유를 자동으로 해준다.
* */
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@SessionAttributes("user")
public class MemberController {
    private final MemberService memberService;
    //Model에 공유하면 한 번 요청으로 응답이 완료되면 메모리에서 없어지므로 세션이라는 객체에 공유
    //기존방식 - 세션에 공유(HttpSession객체는 HttpServletRequest로 부터 생성된다.)
    @PostMapping("/login")
    public String login(LoginUserInfoDTO loginUserInfo, HttpServletRequest request) {
        System.out.println(loginUserInfo);
        MemberDTO user =  memberService.login(loginUserInfo);
        System.out.println(user);
        String view = "";
        if(user != null) {
            //로그인성공
            System.out.println("로그인성공+++++++");
            //세션을 만들어서 세션에 공유(세션 => 클라이언트 마다 제공되는 서버의 공간,
            //                                세션을 만들어서 세션을 구분할 수 있는 값을 자동으로 부여
            //                                response할때 세션의 구분값을 클라이언트에게 전송
            //                                클라이언트가 매 요청할때 요청객체에 세션을 구분할 수 있는 전달받은 값을 갖고 요청)
            //getSession()를 호출하면 세션을 무조건 만들어서 리턴
            HttpSession session =  request.getSession();
            //
            session.setAttribute("user", user);
            view = "redirect:/index.html";//타임리프
        }else{
            //로그인실패
            view = "redirect:/emp/login";
        }
        return view;
    }
    //로그아웃 - 기존방식
    //매개변수로 HttpSession session을 정의하면 스프링내부에서 사용중인 세션객체를 넘겨준다.
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if(session != null) {
            session.invalidate();//세션객체를 메모리에서 해제
        }
        return "redirect:/index.html";
    }


    //스프링에서 제공되는 기능을 이용해서 로그인 처리하기
    @PostMapping("/spring/login")
    public String springlogin(LoginUserInfoDTO loginUserInfo, Model model) {
        System.out.println("스프링이 제공하는 로그인*********************");
        MemberDTO user =  memberService.login(loginUserInfo);
        String view = "";
        if(user != null) {
            //로그인성공
            System.out.println("로그인성공+++++++");
            model.addAttribute("user", user);
            view = "emp/mypage";//타임리프
        }else{
            //로그인실패
            view = "redirect:/emp/login";
        }
        return view;
    }

    //SessionStatus는 스프링MVC프레임워크 내부에서 세션을 관리하기 위한 객체
    //                내부에서 세션의 상태를 확인할 수 있는 객체
    @GetMapping("/spring/logout")
    public String springlogout(SessionStatus sessionStatus) {
        System.out.println("스프링이 제공하는 기능으로 로그아웃");
        //@SessionAttributes와 함께사용
        sessionStatus.setComplete();//세션에 있는 로그인한 정보객체를 제거 - @SessionAttributes에 정의된 이름의 어트리뷰트
        return "redirect:/index.html";
    }
    //@SessionAttributes로 로그인한 유저정보를 관리하는 경우
    //@ModelAttribute("user")라고 정의하면 모델에 있는 데이터를 매개변수에 매핑할때 사용
    //=> model객체나 세션에 저장되어 있는 어트리뷰트를 메소드의 매개변수로 바인딩하는 경우 사용
    //=> user라는 이름으로 세션에 저장되어 있는 객체가 메소드가 호출되면서 매핑되도록 처리
    @GetMapping("/mypage")
    public String mypage(@ModelAttribute("user") MemberDTO user){
        //로그인한 사용자의 정보
        System.out.println("로그인한 사용자 정보");
        System.out.println(user);
        return "emp/mypage";
    }
    @GetMapping("/view")
    public String show(){
        return "emp/register";
    }
}










