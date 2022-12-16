package com.nstop.data.source.model;

import com.alibaba.fastjson2.JSONObject;
import com.nstop.data.source.common.constant.DeleteFlagConstant;
import com.nstop.common.model.BaseModel;
import com.nstop.data.source.constant.DataSourceConstant;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author: origindoris
 * @Title: DataSource
 * @Description:
 * @date: 2022/10/14 17:13
 */
@Data
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Table(name = DataSourceConstant.SOURCE_TABLE_NAME)
@Proxy(lazy = false)
@Where(clause = DeleteFlagConstant.WHERE)
@SQLDelete(sql = DataSourceConstant.DELETE_SQL)
@SQLDeleteAll(sql = DataSourceConstant.DELETE_SQL)
public class Source extends BaseModel {

    @Column(name = "source_type")
    @NotBlank(message = "数据源类型不能为空！")
    private String sourceType;

    @NotBlank(message = "数据源名称不能为空！")
    @Column(name = "source_name")
    private String sourceName;

    @Column(name = "source_property")
    @Type(type = "json")
    @NotNull(message = "数据源属性不能为空！")
    private JSONObject sourceProperty;

    @Column(name = "source_code")
    private String sourceCode;
}
