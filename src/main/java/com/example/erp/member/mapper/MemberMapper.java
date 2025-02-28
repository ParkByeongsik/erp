package com.example.erp.member.mapper;

import com.example.erp.dto.LoginUserInfoDTO;
import com.example.erp.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberDTO login(LoginUserInfoDTO loginuser);
}
