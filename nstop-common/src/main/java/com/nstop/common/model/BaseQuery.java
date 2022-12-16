package com.nstop.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: origindoris
 * @Title: BaseQuery
 * @Description:
 * @date: 2022/11/18 10:18
 */
@Data
public class BaseQuery implements Serializable {

    private Integer page = 0;

    private Integer size = 10;
}
