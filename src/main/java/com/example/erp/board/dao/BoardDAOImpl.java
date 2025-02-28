package com.example.erp.board.dao;

import com.example.erp.board.mapper.BoardMapper;
import com.example.erp.dto.BoardDTO;
import com.example.erp.dto.BoardFileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//DB를 액세스하기 위한 계층
//Mapper의 메소드를 호출
@Repository
@RequiredArgsConstructor
public class BoardDAOImpl implements BoardDAO {
    private final BoardMapper mapper;
    @Override
    public int insert(BoardDTO board) {
        return mapper.insert(board);
    }

    @Override
    public List<BoardDTO> boardList() {
        return mapper.selectall();
    }

    @Override
    public BoardDTO read(String board_no) {
        return mapper.read(board_no);
    }

    @Override
    public int update(BoardDTO board) {
        return mapper.update(board);
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
        List<BoardDTO> list = null;//결과를 저장할 변수
        Map<String,String> map = new HashMap<>();
        map.put("tag",tag);
        map.put("data",data);
        list = mapper.dynamicsearch(map);
        return list;
    }

    @Override
    public List<BoardDTO> findByCategory(String category) {
        return mapper.categorySelect(category);
    }

    @Override
    public int insertFile(List<BoardFileDTO> boardfiledtolist) {

        return mapper.fileinsert(boardfiledtolist);
    }

    @Override
    public List<BoardFileDTO> getFileList(String boardno) {
        return mapper.fileselect(boardno);
    }

    @Override
    public BoardFileDTO getFile(String boardFileno) {
        BoardFileDTO data=   mapper.getfileinfo(boardFileno);
        System.out.println(data);
        return data;
    }
}
