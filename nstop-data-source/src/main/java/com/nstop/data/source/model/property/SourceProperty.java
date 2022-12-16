package com.nstop.data.source.model.property;

import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @author: origindoris
 * @Title: SourceProperty
 * @Description:
 * @date: 2022/10/18 10:18
 */
@Data
public class SourceProperty implements Serializable {
    protected String type;


    public void verifyParam(){
        Assert.notNull(type, "数据源类型不能为空！");
    }
}
