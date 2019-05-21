package com.dcits.storage.service;

import com.dcits.storage.model.TuOutStore;
import com.dcits.storage.repository.TuOutStoreRepository;
import com.dcits.storage.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

/**
 * 出库单事务层
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Service
public class TuOutStoreService {

    @Autowired
    private TuOutStoreRepository tuOutStoreRepository;

    @Autowired
    private EntityManager entityManager;

    /**
     * 分页查询出库单关联集合
     * @param pageInfo 出库单分页信息实体
     * @return Page<TuOutStore> 分页数据
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public Page<TuOutStore> listTuOutStoreRelate(PageInfo<TuOutStore> pageInfo){
        pageInfo.getParams().put("status", "1");
        return listTuOutStore(pageInfo, Sort.Direction.ASC,"inputTime");
    }

    /**
     * 分页查询出库单确认集合
     * @param pageInfo 出库单分页信息实体
     * @return Page<TuOutStore> 分页数据
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public Page<TuOutStore> listTuOutStoreConfirm(PageInfo<TuOutStore> pageInfo){
        pageInfo.getParams().put("status", "2");
        return listTuOutStore(pageInfo, Sort.Direction.ASC,"inputTime");
    }

    /**
     * 分页查询出库单集合
     * @param pageInfo 出库单分页信息实体
     * @param sort 排序方向
     * @param sortField 出库单分页信息实体
     * @return Page<TuOutStore> 分页数据
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public Page<TuOutStore> listTuOutStore(PageInfo<TuOutStore> pageInfo, Sort.Direction sort, String sortField){
        pageInfo.getParams().put("isDeleted", MessageInfo.IS_DELETED_0);
        PageRequest pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize(), sort, sortField);
        SpecificationCommon<TuOutStore> sp = new SpecificationCommon(SpecificationLikeInfo.TU_OUT_STORE_FIELD_LIKE, pageInfo);
        Page<TuOutStore> listPage = tuOutStoreRepository.findAll(sp, pageable);
        return listPage;
    }

    /**
     * 根据主键查询出库单
     * @param id 出库单主键
     * @return TuOutStore 出库单实体
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public TuOutStore getTuOutStore(String id){
        TuOutStore ent = tuOutStoreRepository.findById(id).get();
        return ent;
    }

    /**
     * 出库关联操作
     * @param outStoreNo 出库单号
     * @param loginName 操作人
     * @return ResultObject 返回结果集
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public ResultObject tuOutStoreRelateAll(String outStoreNo, String loginName){
        StoredProcedureQuery storedProcedureQuery = this.entityManager.createNamedStoredProcedureQuery("PR_OUTSTORE_RELATE");
        storedProcedureQuery.setParameter("OUT_STORE_NO", outStoreNo);
        storedProcedureQuery.setParameter("USER_NAME", loginName);
        storedProcedureQuery.execute();
        String message = storedProcedureQuery.getOutputParameterValue("MESSAGE").toString();
        if(StringUtils.isNotBlank(message)){
            return ResultObject.createInstance(false, message);
        }else{
            return ResultObject.createInstance(true, MessageInfo.OUT_STORE_RELATE_SUCCESS);
        }
    }

    /**
     * 出库确认操作
     * @param outStoreNo 出库单号
     * @param loginName 操作人
     * @return ResultObject 返回结果集
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public ResultObject tuOutStoreConfirm(String outStoreNo, String loginName){
        StoredProcedureQuery storedProcedureQuery = this.entityManager.createNamedStoredProcedureQuery("PR_OUTSTORE_CONFIRM");
        storedProcedureQuery.setParameter("OUT_STORE_NO", outStoreNo);
        storedProcedureQuery.setParameter("USER_NAME", loginName);
        storedProcedureQuery.execute();
        String message = storedProcedureQuery.getOutputParameterValue("MESSAGE").toString();
        if(StringUtils.isNotBlank(message)){
            return ResultObject.createInstance(false, message);
        }else{
            return ResultObject.createInstance(true, MessageInfo.OUT_STORE_CONFIRM_SUCCESS);
        }
    }
}
