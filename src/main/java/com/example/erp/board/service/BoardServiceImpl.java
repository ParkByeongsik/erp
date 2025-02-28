package com.example.erp.board.service;

import com.example.erp.board.dao.BoardDAO;
import com.example.erp.dto.BoardDTO;
import com.example.erp.dto.BoardFileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//BoardDAO의 메소드를 호출
//컨트롤러에서 받은 데이터를 가공해서 DAO로 넘기거나 DAO에서 받은 데이터를 가공해서 컨트롤러로 넘기는 작업
//비니지스로직
//트랜잭션처리
@Service
//@RequiredArgsConstructor는 private final멤버들을 이용해서 생성자를 만들어서 제공
//빈을 찾아서 주입해주는 것까지 자동으로 처리
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardDAO boardDAO;
    //파일업로드와 게시글이 함께 insert되는 게시글등록서비스
    //메소드 하나에서 여러 개의 DAO메소드를 호출하는 경우 트랜잭션처리 해야 한다.
    @Override
    public int insert(BoardDTO board, List<BoardFileDTO> boardfiledtolist) {
        if(boardfiledtolist.size()==0){
            boardDAO.insert(board);
        }else {
            boardDAO.insert(board);//게시글등록
            boardDAO.insertFile(boardfiledtolist);//첨부파일등록
        }
        return 0;
    }

    @Override
    public int insert(BoardDTO board) {
        return boardDAO.insert(board);
    }

    @Override
    public List<BoardDTO> boardList() {
        return boardDAO.boardList();
    }

    @Override
    public BoardDTO getBoardInfo(String board_no) {
        return boardDAO.read(board_no);
    }

    @Override
    public int update(BoardDTO board) {
        return boardDAO.update(board);
    }

    @Override
    public int delete(String board_no) {
        return 0;
    }

    @Override
    public List<BoardDTO> search(String data) {
        return List.of();
    }

    @Override
    public List<BoardDTO> search(String tag, String data) {
        return boardDAO.search(tag, data);
    }

    @Override
    public List<BoardDTO> findByCategory(String category) {
        //category값을 판단해서 dao의 적절한 메소드를 호출
        List<BoardDTO> list = null;
        if(category != null) {
            if(category.equals("all")){
                list = boardDAO.boardList();
            }else{
                list = boardDAO.findByCategory(category);
            }
        }
        return list;
    }

    @Override
    public List<BoardFileDTO> getFileList(String boardno) {
        return boardDAO.getFileList(boardno);
    }

    @Override
    public BoardFileDTO getFile(String boardFileno) {
        return boardDAO.getFile(boardFileno);
    }
}









