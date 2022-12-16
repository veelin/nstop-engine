package com.nstop.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: origindoris
 * @Title: CommonRepository
 * @Description: 公共存储库
 * @date: 2022/10/18 10:10
 */
public interface AbstractCommonRepository<T, ID> extends JpaSpecificationExecutor<T>, JpaRepository<T, ID> {

}
