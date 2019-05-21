package com.dcits.storage.repository;

import com.dcits.storage.model.TuOutStoreRelateJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 出库单关联（多表关联）Repository接口
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Repository
public interface TuOutStoreRelateJoinRepository extends JpaRepository<TuOutStoreRelateJoin, String>, JpaSpecificationExecutor<TuOutStoreRelateJoin> {

    /**
     * 根据出库单号查询其关联的货物
     * @param outStoreNo 出库单号
     * @return List<TuOutStoreRelateJoin> 出库关联数据集合
     * @author xieds
     * @date 2019/3/5 9:29
     * @updater xieds
     * @updatedate 2019/3/5 9:29
     */
    @Query(value = "select t.*, s.NAME as RELATE_STATUS_NAME from TU_OUT_STORE_RELATE t left join SYSTEM_TC_CODE s on s.CODE=t.RELATE_STATUS and s.TYPE='4' where t.OUT_STORE_NO=?1 and t.is_deleted='0' order by t.IN_STORE_NO, t.RELATE_STATUS asc" ,nativeQuery = true)
    public List<TuOutStoreRelateJoin> listTuOutStoreRelateByOutStoreNo(String outStoreNo);
}
