package com.zf.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDO {

    private String id;

    private String loginName;

    private String pwd;

    private LocalDateTime loginTime;
}
