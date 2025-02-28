package com.example.erp.board.mapper;

import com.example.erp.dto.BoardDTO;
import com.example.erp.dto.BoardFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

//xml mapper 파일의 statement를 호출하는 역할
//=> 인터페이스로 만들어 놓으면 스프링내부에서 BoardMapper를 구현하는 구현체를 만들어서 코드를 자동생성
//=> 자동으로 코드를 생성해주려면 @Mapper를 선언해야한다.
//=> 메소드명은 xml파일의 statement에 선언한 id명과 동일해야 한다.
@Mapper
public interface BoardMapper {
    //xml에 정의된 id가 insert인 sql을 실행
    int insert(BoardDTO board);
    List<BoardDTO> selectall();
    List<BoardDTO> categorySelect(String category);
    List<BoardDTO>  dynamicsearch(Map<String,String> map);
    BoardDTO read(String boardNo);
    int update(BoardDTO board);
    //파일insert
    int fileinsert(List<BoardFileDTO> boardFileDTOList);
    List<BoardFileDTO> fileselect(String boardNo);
    BoardFileDTO getfileinfo(String boardFileNo);
}
