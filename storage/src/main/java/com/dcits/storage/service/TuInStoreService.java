package com.dcits.storage.service;

import com.dcits.storage.model.TuInStore;
import com.dcits.storage.repository.TuInStoreRepository;
import com.dcits.storage.utils.MessageInfo;
import com.dcits.storage.utils.PageInfo;
import com.dcits.storage.utils.SpecificationCommon;
import com.dcits.storage.utils.SpecificationLikeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * 入库单事务层
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Service
public class TuInStoreService {

    @Autowired
    private TuInStoreRepository tuInStoreRepository;

    /**
     * 分页查询入库单关联集合
     * @param pageInfo 入库单分页信息实体
     * @return Page<TuInStore> 分页数据
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public Page<TuInStore> listTuInStoreRelate(PageInfo<TuInStore> pageInfo){
        pageInfo.getParams().put("status", "1");
        return listTuInStore(pageInfo, Sort.Direction.ASC,"inputTime");
    }

    /**
     * 分页查询入库单确认集合
     * @param pageInfo 入库单分页信息实体
     * @return Page<TuInStore> 分页数据
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public Page<TuInStore> listTuInStoreConfirm(PageInfo<TuInStore> pageInfo){
        pageInfo.getParams().put("status", "2");
        return listTuInStore(pageInfo, Sort.Direction.ASC,"inputTime");
    }

    /**
     * 分页查询入库单移库集合
     * @param pageInfo 入库单分页信息实体
     * @return Page<TuInStore> 分页数据
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public Page<TuInStore> listTuInStoreMove(PageInfo<TuInStore> pageInfo){
        pageInfo.getParams().put("@status", "2,3,4");
        return listTuInStore(pageInfo, Sort.Direction.ASC,"inputTime");
    }

    /**
     * 分页查询入库单集合
     * @param pageInfo 入库单分页信息实体
     * @param sort 排序方向
     * @param sortField 入库单分页信息实体
     * @return Page<TuInStore> 分页数据
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public Page<TuInStore> listTuInStore(PageInfo<TuInStore> pageInfo, Sort.Direction sort, String sortField){
        pageInfo.getParams().put("isDeleted", MessageInfo.IS_DELETED_0);
        PageRequest pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize(), sort, sortField);
        SpecificationCommon<TuInStore> sp = new SpecificationCommon(SpecificationLikeInfo.TU_IN_STORE_FIELD_LIKE, pageInfo);
        Page<TuInStore> listPage = tuInStoreRepository.findAll(sp, pageable);
        return listPage;
    }

    /**
     * 根据主键查询入库单
     * @param id 入库单主键
     * @return TuInStore 入库单实体
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public TuInStore getTuInStore(String id){
        TuInStore ent = tuInStoreRepository.findById(id).get();
        return ent;
    }
}
