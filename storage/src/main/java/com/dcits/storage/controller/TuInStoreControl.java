package com.dcits.storage.controller;

import com.dcits.storage.model.TuInStore;
import com.dcits.storage.service.TuInStoreService;
import com.dcits.storage.utils.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 入库单控制层
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Api(tags = "tuInStoreApi", description = "入库单操作接口")
@RestController
@RequestMapping("/tuInStoreApi")
public class TuInStoreControl {

    @Autowired
    private TuInStoreService tuInStoreService;

    /**
     * 分页查询入库单关联集合
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="分页查询入库单关联集合", notes="")
    @RequestMapping(value="/listTuInStoreRelate", method= RequestMethod.POST)
    public Page<TuInStore> listTuInStoreRelate(@ApiParam(value = "入库单分页信息实体") @RequestBody PageInfo<TuInStore> pageInfo){
        return tuInStoreService.listTuInStoreRelate(pageInfo);
    }

    /**
     * 分页查询入库单确认集合
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="分页查询入库单确认集合", notes="")
    @RequestMapping(value="/listTuInStoreConfirm", method= RequestMethod.POST)
    public Page<TuInStore> listTuInStoreConfirm(@ApiParam(value = "入库单分页信息实体") @RequestBody PageInfo<TuInStore> pageInfo){
        return tuInStoreService.listTuInStoreConfirm(pageInfo);
    }

    /**
     * 分页查询入库单移库集合
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="分页查询入库单移库集合", notes="")
    @RequestMapping(value="/listTuInStoreMove", method= RequestMethod.POST)
    public Page<TuInStore> listTuInStoreMove(@ApiParam(value = "入库单分页信息实体") @RequestBody PageInfo<TuInStore> pageInfo){
        return tuInStoreService.listTuInStoreMove(pageInfo);
    }

    /**
     * 根据主键查询入库单
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="根据主键查询入库单", notes="")
    @RequestMapping(value="/getTuInStore", method= RequestMethod.GET)
    public TuInStore getTuInStore(@ApiParam(value = "入库单主键") @RequestParam String id){
        return tuInStoreService.getTuInStore(id);
    }
}
