package com.nstop.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: origindoris
 * @Title: BaseModel
 * @Description:
 * @date: 2022/10/14 15:56
 */
@Data
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "gmt_create")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date gmtCreate;

    @Column(name = "gmt_modified")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date gmtModified;

    @Column(name = "delete_flag")
    protected Boolean deleteFlag;

    @Column(name = "creator")
    @CreatedBy
    protected String creator;

    @Column(name = "creatorName")
    protected String creatorName;

    @Column(name = "tenant_code")
    private String tenantCode;

    @Column(name = "tenant_name")
    private String tenantName;

}
