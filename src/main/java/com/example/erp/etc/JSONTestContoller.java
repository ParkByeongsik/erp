package com.example.erp.etc;

import com.example.erp.board.service.BoardService;
import com.example.erp.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//데이터만 response하는 컨트롤러
//데이터를 response하는 컨트롤러와 화면을 response하는 컨트롤러는 각각의 클래스로 작업하는 것이 일반적
//스프링MVC에 포함된 jackson라이브러리가 자바객체를 자동으로 json데이터로 변환
//                                   -----------------------------------
//                                       리턴타입이 DTO객체 => JSONObject로 변환
//                                       리턴타입이 ArrayList<DTO> => JSONObject들이 저장된 JSONArray를 리턴
@RestController
@RequiredArgsConstructor
public class JSONTestContoller {
    private final BoardService service;
    @GetMapping("/json/getString")
    public String responseString(){
        return "responseString";
    }

    @GetMapping("/json/getJsonObj")
    public BoardDTO responseDTO(){
        System.out.println("restcontroller사용");
        return service.getBoardInfo("5");
    }
    //리턴타입을 List<BoardDTO>로 정의하면 BoardDTO를 JSONObject로 변환하고 이 JSONObject를  JSONArray에 담을 수 있도록
    //자동변환해서 리턴
    @GetMapping("/json/getJsonArr")
    public List<BoardDTO> responseJsonArr(){
        return service.boardList();
    }
}
