package com.example.erp.etc;

import com.example.erp.board.service.BoardService;
import com.example.erp.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/*
   Ajax요청
   Ajax(Asynchronous Javascript and XML)
   => 비동기통신으로 데이터를 요청하는 방법
   => 뷰를 response하는 방식 동기통신
   => javascript에서 데이터를 요청하는 방법(응답데이터는 XML,String,json이나 주로 json)

 */
@RestController
@RequiredArgsConstructor
public class AjaxTestController {
    private final BoardService service;
    @GetMapping(value = "/ajax/ajaxtest01",produces = "application/text;charset=utf-8")
    public String ajaxtest(String id){
        String msg="";
//        MemberDTO user = service.idcheck(id명);
//        if(user!=null){
//            사용불가능아이디
//        }else{
//
//        }
        if(id.equals("bts")){
            msg = "사용불가능아이디";
        }else{
            msg ="사용가능아이디";
        }
        return msg;
    }
}
