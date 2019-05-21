package com.dcits.storage.service;

import com.dcits.storage.model.*;
import com.dcits.storage.repository.*;
import com.dcits.storage.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 入库单关联事务层
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Service
public class TuInStoreRelateService {

    @Autowired
    private TuInStoreRelateRepository tuInStoreRelateRepository;

    @Autowired
    private TuInStoreRelateRecordRepository tuInStoreRelateRecordRepository;

    @Autowired
    private TuInStoreRepository tuInStoreRepository;

    @Autowired
    private SystemLogRepository systemLogRepository;

    @Autowired
    private TuBillDynamicRepository tuBillDynamicRepository;

    /**
     * 入库单关联操作
     * @param value 关联数据数组
     * @return ResultObject 返回结果集
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultObject saveTuInStoreRelate(String[] value) throws Exception{
        String[] valueArray = value[0].split("@");
        String loginName = valueArray[0];
        String inStoreNo = valueArray[1];
        for(int i=1; i<value.length; i++){
            String str = value[i];
            String locationNo = str.split("@")[0];
            String[] barCodeArray = str.split("@")[1].split(",");
            for(String barCode : barCodeArray){
                TuInStoreRelate tuInStoreRelate = new TuInStoreRelate();
                tuInStoreRelate.setId(StringUtils.uuid()).setInStoreNo(inStoreNo).setLocationNo(locationNo).setBarCode(barCode).setRelateStatus("2").setIsDeleted("0").setVersion(1);
                tuInStoreRelateRepository.save(tuInStoreRelate);
                TuInStoreRelateRecord tuInStoreRelateRecord = new TuInStoreRelateRecord();
                tuInStoreRelateRecord.setId(StringUtils.uuid()).setInStoreNo(inStoreNo).setLocationNo(locationNo).setBarCode(barCode).setRelationUser(loginName).setRelationTime(new Date()).setIsDeleted("0").setVersion(1);
                tuInStoreRelateRecordRepository.save(tuInStoreRelateRecord);
            }
        }

        Specification<TuInStore> specification = new Specification<TuInStore>() {
            @Override
            public Predicate toPredicate(Root<TuInStore> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                predicate.add(cb.equal(root.get("inStoreNo"), inStoreNo));
                predicate.add(cb.equal(root.get("isDeleted"), MessageInfo.IS_DELETED_0));
                return cb.and(predicate.toArray(new Predicate[predicate.size()]));
            }
        };
        TuInStore tuInStore = tuInStoreRepository.findOne(specification).get();
        tuInStore.setStatus("2").setRelationUser(loginName).setRelationTime(new Date());
        tuInStoreRepository.saveAndFlush(tuInStore);

        SystemLog systemLog = new SystemLog();
        systemLog.setId(StringUtils.uuid()).setFlag("2").setMessage(MessageInfo.IN_STORE_RELATE_SUCCESS).setSubjectNo(inStoreNo).setInputUser(loginName).setInputTime(new Date()).setIsDeleted("0").setVersion(1);
        systemLogRepository.save(systemLog);

        return ResultObject.createInstance(true, MessageInfo.IN_STORE_RELATE_SUCCESS);
    }

    /**
     * 根据入库单号查询其关联的货物
     * @param inStoreNo 入库单号
     * @return String 结果字符串
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public String listTuInStoreRelateByInStoreNo(String inStoreNo){
        Specification<TuInStoreRelate> specification = new Specification<TuInStoreRelate>() {
            @Override
            public Predicate toPredicate(Root<TuInStoreRelate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                predicateList.add(cb.equal(root.get("inStoreNo"), inStoreNo));
                predicateList.add(cb.equal(root.get("isDeleted"), MessageInfo.IS_DELETED_0));
                Predicate[] predicateArray = new Predicate[predicateList.size()];
                query.where(cb.and(predicateList.toArray(predicateArray)));
                query.orderBy(cb.asc(root.get("locationNo")),cb.asc(root.get("barCode")));
                return query.getRestriction();
            }
        };
        List<TuInStoreRelate> list = tuInStoreRelateRepository.findAll(specification);
        return StringUtils.listToJson(list);
    }

    /**
     * 根据主键查询入库单关联
     * @param id 入库单关联主键
     * @return TuInStoreRelate 入库单关联实体
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public TuInStoreRelate getTuInStoreRelate(String id){
        TuInStoreRelate ent = tuInStoreRelateRepository.findById(id).get();
        return ent;
    }

    /**
     * 入库单确认操作
     * @param value 确认数据数组
     * @return ResultObject 返回结果集
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultObject saveTuInStoreConfirm(String[] value) throws Exception{
        String[] valueArray = value[0].split("@");
        String loginName = valueArray[0];
        String inStoreNo = valueArray[1];
        String billNo = valueArray[2];
        Integer packNoTotal = Integer.parseInt(valueArray[3]);
        Double weightTotal = Double.parseDouble(valueArray[4]);
        Double volumeTotal = Double.parseDouble(valueArray[5]);
        for(int i=1; i<value.length; i++){
            String[] strArray = value[i].split("@");
            String barCode = strArray[0];
            Double weight = "".equals(strArray[1]) ? 0 : Double.parseDouble(strArray[1]);
            String[] sizeArray = strArray[2].split("/");
            Double length = (sizeArray.length<1 || "".equals(sizeArray[0])) ? 0 : Double.parseDouble(sizeArray[0]);
            Double width = (sizeArray.length<2 || "".equals(sizeArray[1])) ? 0 : Double.parseDouble(sizeArray[1]);
            Double height = (sizeArray.length<3 || "".equals(sizeArray[2])) ? 0 : Double.parseDouble(sizeArray[2]);
            Double volume = (sizeArray.length<4 || "".equals(sizeArray[3])) ? 0 : Double.parseDouble(sizeArray[3]);
            Specification<TuInStoreRelate> specificationTuInStoreRelate = new Specification<TuInStoreRelate>() {
                @Override
                public Predicate toPredicate(Root<TuInStoreRelate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> predicate = new ArrayList<>();
                    predicate.add(cb.equal(root.get("inStoreNo"), inStoreNo));
                    predicate.add(cb.equal(root.get("barCode"), barCode));
                    predicate.add(cb.equal(root.get("isDeleted"), MessageInfo.IS_DELETED_0));
                    return cb.and(predicate.toArray(new Predicate[predicate.size()]));
                }
            };
            TuInStoreRelate tuInStoreRelate = tuInStoreRelateRepository.findOne(specificationTuInStoreRelate).get();
            tuInStoreRelate.setLength(length).setWidth(width).setHeight(height).setWeight(weight).setVolume(volume).setRelateStatus("3");
            tuInStoreRelateRepository.saveAndFlush(tuInStoreRelate);
        }

        Specification<TuInStore> specificationTuInStore = new Specification<TuInStore>() {
            @Override
            public Predicate toPredicate(Root<TuInStore> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                predicate.add(cb.equal(root.get("inStoreNo"), inStoreNo));
                predicate.add(cb.equal(root.get("isDeleted"), MessageInfo.IS_DELETED_0));
                return cb.and(predicate.toArray(new Predicate[predicate.size()]));
            }
        };
        TuInStore tuInStore = tuInStoreRepository.findOne(specificationTuInStore).get();
        tuInStore.setStatus("3").setConfirmPackNo(packNoTotal).setConfirmWeight(weightTotal).setConfirmVolume(volumeTotal).setConfirmUser(loginName).setConfirmTime(new Date());
        tuInStoreRepository.saveAndFlush(tuInStore);

        TuBillDynamic tuBillDynamic = new TuBillDynamic();
        tuBillDynamic.setId(StringUtils.uuid()).setInStoreNo(inStoreNo).setBillNo(billNo).setOperationUser(loginName).setOperationTime(new Date()).setRemark(MessageInfo.IN_STORE_CONFIRM_SUCCESS).setIsDeleted("0").setVersion(1);
        tuBillDynamicRepository.save(tuBillDynamic);

        SystemLog systemLog = new SystemLog();
        systemLog.setId(StringUtils.uuid()).setFlag("2").setMessage(MessageInfo.IN_STORE_CONFIRM_SUCCESS).setSubjectNo(inStoreNo).setInputUser(loginName).setInputTime(new Date()).setIsDeleted("0").setVersion(1);
        systemLogRepository.save(systemLog);

        return ResultObject.createInstance(true, MessageInfo.IN_STORE_CONFIRM_SUCCESS);
    }

    /**
     * 判断托盘号是否存在
     * @param inStoreNo 入库单号
     * @param barCode 托盘号
     * @return ResultObject 返回结果集
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public ResultObject isExistBarCode(String inStoreNo, String barCode) {
        Specification<TuInStoreRelate> specificationRelate = new Specification<TuInStoreRelate>() {
            @Override
            public Predicate toPredicate(Root<TuInStoreRelate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                predicate.add(cb.equal(root.get("inStoreNo"), inStoreNo));
                predicate.add(cb.equal(root.get("barCode"), barCode));
                predicate.add(cb.equal(root.get("isDeleted"), MessageInfo.IS_DELETED_0));
                return cb.and(predicate.toArray(new Predicate[predicate.size()]));
            }
        };
        Optional<TuInStoreRelate> tuInStoreRelateOptional = tuInStoreRelateRepository.findOne(specificationRelate);
        if(tuInStoreRelateOptional.isPresent()){
            return ResultObject.createInstance(true, "当前托盘号：" + barCode);
        }else{
            return ResultObject.createInstance(false, MessageInfo.NO_BAR_CODE);
        }
    }

    /**
     * 移库操作
     * @param inStoreNo 入库单号
     * @param locationNo 新库位号
     * @param barCode 托盘号
     * @param loginName 操作人
     * @return ResultObject 返回结果集
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultObject tuInStoreRelateMove(String inStoreNo, String locationNo, String barCode, String loginName) throws Exception{
        Specification<TuInStoreRelate> specificationRelate = new Specification<TuInStoreRelate>() {
            @Override
            public Predicate toPredicate(Root<TuInStoreRelate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                predicate.add(cb.equal(root.get("inStoreNo"), inStoreNo));
                predicate.add(cb.equal(root.get("barCode"), barCode));
                predicate.add(cb.equal(root.get("isDeleted"), MessageInfo.IS_DELETED_0));
                return cb.and(predicate.toArray(new Predicate[predicate.size()]));
            }
        };
        Optional<TuInStoreRelate> tuInStoreRelateOptional = tuInStoreRelateRepository.findOne(specificationRelate);
        if(tuInStoreRelateOptional.isPresent()){
            TuInStoreRelate tuInStoreRelate = tuInStoreRelateOptional.get();
            tuInStoreRelate.setLocationNo(locationNo);
            tuInStoreRelateRepository.saveAndFlush(tuInStoreRelate);

            TuInStoreRelateRecord tuInStoreRelateRecord = new TuInStoreRelateRecord();
            tuInStoreRelateRecord.setId(StringUtils.uuid()).setInStoreNo(inStoreNo).setLocationNo(locationNo).setBarCode(barCode).setRelationUser(loginName).setRelationTime(new Date()).setIsDeleted("0").setVersion(1);
            tuInStoreRelateRecordRepository.save(tuInStoreRelateRecord);

            return ResultObject.createInstance(true, MessageInfo.IN_STORE_MOVE_SUCCESS);
        }else{
            return ResultObject.createInstance(false, MessageInfo.NO_BAR_CODE);
        }
    }
}
