package com.example.erp.member.service;

import com.example.erp.dto.LoginUserInfoDTO;
import com.example.erp.dto.MemberDTO;
import com.example.erp.member.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    //DB에서 조회한 데이터를 가공
    //뷰이름을 수정
    // /menu/it_menu.jsp => menu/it
    //db에 저장된 데이터를 조회해서 바로 사용하지 않고 가공할 수 있다.
    @Override
    public MemberDTO login(LoginUserInfoDTO loginUser) {
        //LoginUserInfoDTO는 request용 DTO이고
        //MemberDTO는 response용 DTO
        MemberDTO user = memberDAO.login(loginUser);
        //user가 null이 아니면 menupath를 가공해서 뷰의 이름을 변경해서 다시 셋팅
        if(user!=null){
            String menupath = user.getMenupath();
            menupath = menupath.substring(menupath.indexOf("/")+1,
                    menupath.indexOf("_"));
            //잘라낸 문자열이 뷰의 이름이므로 다시setter를 호출해서 저장
            user.setMenupath(menupath);
        }
        System.out.println("+++++++++++++++++++++++++=>"+user);
        return user;
    }
    @Override
    public int insert(MemberDTO user) {
        return 0;
    }

    @Override
    public boolean idCheck(String id) {
        return false;
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


}
