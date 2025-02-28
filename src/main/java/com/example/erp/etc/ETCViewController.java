package com.example.erp.etc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ETCViewController {
    @GetMapping("/json/showpage")
    public String jsonshowpage(){
        return "json/json";
    }

   @GetMapping("/ajax/showpage")
   public String ajaxshowpage(){
       return "json/ajax";
   }
   @GetMapping("/noajax")
   public String noajax(String id, Model model){
        //Ajax를 이용해서 요청하지 않고 일반적인 동기통신으로 요청하는 방식
       String msg="";
       if(id.equals("bts")){
           msg = "사용불가능아이디";
       }else{
           msg ="사용가능아이디";
       }
       model.addAttribute("msg",msg);
       return "json/ajax";// 뷰정보
   }
}
