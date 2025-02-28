package com.example.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
//response용 DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("member")
public class MemberDTO {
	private String id;
	private String pass;
	private String name;
	private String ssn;
	private String birthday;
	private String marry;
	private String gender;
	private String position;
	private String duty;
	private String classes;
	private Date startday;
	private Date endday;
	private String deptno;
	private String curstate;
	private String zipcode;
	private String addr;
	private String detailaddr;
	private String phonehome;
	private String phoneco;
	private String phonecell;
	private String email;
	private String profilePhoto; //storeFilename저장
	private String deptname; //로그인 사용자의 부서정보
	private String menupath; //로그인 사용자의 메뉴페이지정보
	private String jobCategory;//로그인 사용자의 job분류정보

}