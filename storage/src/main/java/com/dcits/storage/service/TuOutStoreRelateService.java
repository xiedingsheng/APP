package com.dcits.storage.service;

import com.dcits.storage.model.*;
import com.dcits.storage.repository.*;
import com.dcits.storage.utils.MessageInfo;
import com.dcits.storage.utils.ResultObject;
import com.dcits.storage.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 出库单关联事务层
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Service
public class TuOutStoreRelateService {

    @Autowired
    private TuOutStoreRelateRepository tuOutStoreRelateRepository;

    @Autowired
    private TuOutStoreRelateJoinRepository tuOutStoreRelateJoinRepository;

    @Autowired
    private EntityManager entityManager;

    /**
     * 根据出库单号查询其关联的货物
     * @param outStoreNo 出库单号
     * @return String 结果字符串
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public String listTuOutStoreRelateByOutStoreNo(String outStoreNo){
        List<TuOutStoreRelateJoin> list = tuOutStoreRelateJoinRepository.listTuOutStoreRelateByOutStoreNo(outStoreNo);
        return StringUtils.listToJson(list);
    }

    /**
     * 单个出库关联操作
     * @param outStoreNo 出库单号
     * @param barCode 托盘号
     * @return ResultObject 返回结果集
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public ResultObject tuOutStoreRelateSingle(String outStoreNo, String barCode){
        return tuOutStoreRelateOperate(outStoreNo, barCode, "2", MessageInfo.OUT_STORE_RELATE_SUCCESS);
    }

    /**
     * 单个出库关联取消操作
     * @param outStoreNo 出库单号
     * @param barCode 托盘号
     * @return ResultObject 返回结果集
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public ResultObject tuOutStoreRelateCancelSingle(String outStoreNo, String barCode){
        return tuOutStoreRelateOperate(outStoreNo, barCode, "1", MessageInfo.OUT_STORE_RELATE_CANCEL_SUCCESS);
    }

    public ResultObject tuOutStoreRelateOperate(String outStoreNo, String barCode, String status, String message){
        Specification<TuOutStoreRelate> specification = new Specification<TuOutStoreRelate>() {
            @Override
            public Predicate toPredicate(Root<TuOutStoreRelate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                predicate.add(cb.equal(root.get("outStoreNo"), outStoreNo));
                predicate.add(cb.equal(root.get("barCode"), barCode));
                predicate.add(cb.equal(root.get("isDeleted"), MessageInfo.IS_DELETED_0));
                return cb.and(predicate.toArray(new Predicate[predicate.size()]));
            }
        };
        Optional<TuOutStoreRelate> tuOutStoreRelateOption = tuOutStoreRelateRepository.findOne(specification);
        if(tuOutStoreRelateOption.isPresent()){
            TuOutStoreRelate tuOutStoreRelate = tuOutStoreRelateOption.get();
            tuOutStoreRelate.setRelateStatus(status);
            tuOutStoreRelateRepository.save(tuOutStoreRelate);
            return ResultObject.createInstance(true, message);
        }else{
            return ResultObject.createInstance(false, MessageInfo.NO_OUT_STORE_RELATE_DATA);
        }
    }

    /**
     * 根据主键查询出库单关联
     * @param id 出库单关联主键
     * @return TuOutStoreRelate 出库单关联实体
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public TuOutStoreRelate getTuOutStoreRelate(String id){
        TuOutStoreRelate ent = tuOutStoreRelateRepository.findById(id).get();
        return ent;
    }

    /**
     * 判断托盘号是否存在
     * @param outStoreNo 出库单号
     * @param barCode 托盘号
     * @return ResultObject 返回结果集
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public ResultObject isExistBarCode(String outStoreNo, String barCode) {
        Specification<TuOutStoreRelate> specificationRelate = new Specification<TuOutStoreRelate>() {
            @Override
            public Predicate toPredicate(Root<TuOutStoreRelate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                predicate.add(cb.equal(root.get("outStoreNo"), outStoreNo));
                predicate.add(cb.equal(root.get("barCode"), barCode));
                predicate.add(cb.equal(root.get("isDeleted"), MessageInfo.IS_DELETED_0));
                return cb.and(predicate.toArray(new Predicate[predicate.size()]));
            }
        };
        Optional<TuOutStoreRelate> tuOutStoreRelateOptional = tuOutStoreRelateRepository.findOne(specificationRelate);
        if(tuOutStoreRelateOptional.isPresent()){
            return ResultObject.createInstance(true, "当前托盘号：" + barCode);
        }else{
            return ResultObject.createInstance(false, MessageInfo.NO_BAR_CODE);
        }
    }

    /**
     * 反向入库操作
     * @param outStoreNo 出库单号
     * @param locationNo 新库位号
     * @param barCode 托盘号
     * @param loginName 操作人
     * @return ResultObject 返回结果集
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public ResultObject tuOutStoreRelateReverse(String outStoreNo, String locationNo, String barCode, String loginName){
        StoredProcedureQuery storedProcedureQuery = this.entityManager.createNamedStoredProcedureQuery("PR_OUTSTORE_REVERSE_RELATE");
        storedProcedureQuery.setParameter("OUT_STORE_NO", outStoreNo);
        storedProcedureQuery.setParameter("BAR_CODE", barCode);
        storedProcedureQuery.setParameter("LOCATION_NO", locationNo);
        storedProcedureQuery.setParameter("USER_NAME", loginName);
        storedProcedureQuery.execute();
        return ResultObject.createInstance(true, MessageInfo.OUT_STORE_REVERSE_SUCCESS);
    }
}
