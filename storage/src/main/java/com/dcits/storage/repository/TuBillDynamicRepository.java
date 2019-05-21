package com.dcits.storage.repository;

import com.dcits.storage.model.TuBillDynamic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 提单物流Repository接口
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Repository
public interface TuBillDynamicRepository extends JpaRepository<TuBillDynamic, String>, JpaSpecificationExecutor<TuBillDynamic> {
}
