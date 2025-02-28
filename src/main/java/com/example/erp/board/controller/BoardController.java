package com.example.erp.board.controller;

import com.example.erp.board.service.BoardService;
import com.example.erp.board.service.FileUploadService;
import com.example.erp.dto.BoardDTO;
import com.example.erp.dto.BoardFileDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;




@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService service;
    private final FileUploadService fileUploadService;
    //데이터베이스에서 조회한 게시글 목록을 출력
    //전체목록보기와 카테고리별로 조회하는 작업은 비슷한 작업이므로 컨트롤러를 같이 사용
    @GetMapping("/list")
    public String list(Model model,String category) {
        System.out.println("=====================");
        System.out.println("category=>"+category);
        //1. service의 메소드를 호출(비지니스메소드호출)
        List<BoardDTO> boardlist =  service.findByCategory(category);
        //2. select작업은 db에서 받은 결과를 뷰로 넘겨주어야 하므로 데이터를 공유
        model.addAttribute("boardlist", boardlist);
        model.addAttribute("category", category);
        System.out.println(boardlist);
        return "board/boardlist";
    }
    //게시글등록폼을 forward하는 메소드
    @GetMapping("/write")
    public String writePage(){
        return "board/board_write";
    }
    //입력한 데이터를 db에 저장하기
    @PostMapping("/write")
    public String insert(BoardDTO board) throws IOException {
        System.out.println("=========================================");
        System.out.println(board);
       // service.insert(board);
        //DTO에 담긴 MultipartFile객체들을 츠츨
        List<MultipartFile> file =  board.getFiles();
        System.out.println("업로드파일갯수===>"+file.size());
        //파일업로드
        List<BoardFileDTO> boardfilelist =   fileUploadService.uploadFiles(file);
        System.out.println(boardfilelist);
        service.insert(board,boardfilelist);
        //뷰로 forward하지 않고 글쓰기가 완료되면 목록보기로 가기 위해서 컨트롤러로 redirect
        //boardlist.html파일에 출력할 데이터는 컨트롤러를 거쳐야 발생하는 데이터이므로 무조건
        //컨트롤러를 실행한다.
        return "redirect:/board/list?category=all";
    }
    //동적쿼리를 테스트
    //사용자가 select에서 어떤 option을 선택하고 작업하냐에 따라 다른 sql문이 만들어진다.
    //                                                         --------
    //                                                           where절
    @PostMapping("/search")
    public String search(String tag,String search,Model model){
        System.out.println(tag +"-------" +search);
        List<BoardDTO> boardlist =  service.search(tag,search);
        model.addAttribute("boardlist", boardlist);
        return "board/boardlist";
    }
    @GetMapping("/read")
    public String read(String boardNo,String action,Model model){
        //1. 비지니스메소드 호출
        BoardDTO board = service.getBoardInfo(boardNo);
        List<BoardFileDTO> boardfiledtolist = service.getFileList(boardNo);
        System.out.println("==========================");
        System.out.println(boardfiledtolist);
        String view="";
        //2. 데이터공유
        model.addAttribute("board", board);
        model.addAttribute("boardfiledtolist", boardfiledtolist);
        //3. action에 따라서 뷰를 다르게 정의
        if(action.equals("READ")){
            view =  "board/board_read";
        }else{
            view =  "board/board_update";
        }
        return view;
    }
    //업데이트
    @PostMapping("/update")
    public String update(BoardDTO board){
        System.out.println(board);
        service.update(board);
        //수정이 끝나면 게시글목록보기
        return "redirect:/board/list?category=all";
    }
    @GetMapping("/ajax/list")
	@ResponseBody
	public List<BoardDTO> ajaxlist(@RequestParam("category") String category){
		List<BoardDTO> jsonarr = service.findByCategory(category);
		System.out.println(jsonarr+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		return jsonarr;
	}

    @GetMapping("/download/{id}/{board_no}/{boardFileno}")
	public ResponseEntity<UrlResource> downloadFile(@PathVariable("id") String id, @PathVariable("board_no") String board_no,
                                                    @PathVariable("boardFileno") String boardFileno, HttpSession session)
									throws MalformedURLException, FileNotFoundException {
		System.out.println(id+","+board_no+","+boardFileno);
		//1. 파일을 다운로드 하기 위해 디비에 저장된 파일의 정보를 가져오기 - 다운로드를 요청한 파일의 정보
		BoardFileDTO selectfileinfo = service.getFile(boardFileno);
		System.out.println("파일정보:"+selectfileinfo);
		//2. BoardFileDTO객체에서 다운로드할 파일을 실제 파일객체로 변환하는 작업처리 -서버의 경로와 실제 파일정보를 정의

		UrlResource resource =
			new UrlResource("file:"+fileUploadService.getUploadpath(selectfileinfo.getStoreFilename()));
		//3. 파일명(다운로드되는 파일명)에 한글이 있는 경우 오류가 발생되지 않도록 하기 위해서 처리
		String encodedFilename = UriUtils.encode(selectfileinfo.getOriginalFilename(), "UTF-8");
		//4. 파일을 다운로드형식으로 응답하기 위해서 응답정보를 수정 - 응답헤더에 값을 추가(파일다운로드로 인식시키기 위해서)

		String mycontenttype="attachment; filename=\""+encodedFilename+"\"";
		//응답메시지만들기
		//=>response가 정상처리되었다는 것을 인식시키기 위해서 셋팅(200번응답코드를 셋팅)
//		BodyBuilder builder = ResponseEntity.ok();
//		builder.header(HttpHeaders.CONTENT_DISPOSITION, mycontenttype);
//		ResponseEntity<UrlResource> response =  builder.body(resource);
//		return response;
		return ResponseEntity.ok()
				     .header(HttpHeaders.CONTENT_DISPOSITION, mycontenttype)
				     .body(resource);
	}
}













