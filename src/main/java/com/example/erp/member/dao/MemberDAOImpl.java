package com.example.erp.member.dao;

import com.example.erp.dto.LoginUserInfoDTO;
import com.example.erp.dto.MemberDTO;
import com.example.erp.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO {
    private final MemberMapper memberMapper;
    @Override
    public List<MemberDTO> getTreeEmpList(String deptno) {
        return List.of();
    }

    @Override
    public int insert(MemberDTO user) {
        return 0;
    }

    @Override
    public List<MemberDTO> getMemberList() {
        return List.of();
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public MemberDTO read(String id) {
        return null;
    }

    @Override
    public List<MemberDTO> search(String column, String search, String pass) {
        return List.of();
    }

    @Override
    public int update(MemberDTO user) {
        return 0;
    }

    @Override
    public MemberDTO login(LoginUserInfoDTO loginUser) {
        return memberMapper.login(loginUser);
    }

    @Override
    public boolean idCheck(String id) {
        return false;
    }

    @Override
    public MemberDTO findById(String id) {
        return null;
    }
}
