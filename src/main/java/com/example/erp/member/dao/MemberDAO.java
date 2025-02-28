package com.example.erp.member.dao;

import com.example.erp.dto.LoginUserInfoDTO;
import com.example.erp.dto.MemberDTO;

import java.util.List;

public interface MemberDAO {
	List<MemberDTO> getTreeEmpList(String deptno);
	int insert(MemberDTO user);
	List<MemberDTO> getMemberList();
	int delete(String id);
	MemberDTO read(String id);
	List<MemberDTO> search(String column, String search,String pass);
	int update(MemberDTO user);
	MemberDTO login(LoginUserInfoDTO loginUser);
	boolean idCheck(String id);
	MemberDTO findById(String id);
}














