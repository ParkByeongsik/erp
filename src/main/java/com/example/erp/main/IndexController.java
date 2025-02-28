package com.example.erp.main;

import com.example.erp.board.service.BoardService;
import com.example.erp.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final BoardService service;
    @GetMapping("/index.html")
    public String index(Model model) {
        List<BoardDTO> boardlist = service.findByCategory("게시판");
        model.addAttribute("boardlist", boardlist);
        return "layout/indexLayout";
    }
    @GetMapping("/emp/login")
    public String loginPage() {
        return "emp/login";
    }
}
