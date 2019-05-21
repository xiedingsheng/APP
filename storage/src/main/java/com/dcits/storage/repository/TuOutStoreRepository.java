package com.dcits.storage.repository;

import com.dcits.storage.model.TuOutStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 出库单Repository接口
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Repository
public interface TuOutStoreRepository extends JpaRepository<TuOutStore, String>, JpaSpecificationExecutor<TuOutStore> {

}
