package com.nstop.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: origindoris
 * @Title: UserInfo
 * @Description:
 * @date: 2022/10/18 14:43
 */
@Data
public class UserInfo implements Serializable {
    private String name;

    private String nick;

    private String empId;

    private String tenantCode;


}
