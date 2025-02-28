package com.example.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
//login Request용
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("logininfo")
public class LoginUserInfoDTO {
    private String id;
    private String pass;
}
