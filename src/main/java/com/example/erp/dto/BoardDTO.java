package com.example.erp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("board")
public class BoardDTO {
	String boardNo;
	String id;
	Date writeDate;
	String title;
	String content;
	String category;
	List<MultipartFile> files;
}
