package com.dcits.storage.controller;

import com.dcits.storage.model.TuOutStore;
import com.dcits.storage.service.TuOutStoreService;
import com.dcits.storage.utils.PageInfo;
import com.dcits.storage.utils.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 出库单控制层
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Api(tags = "tuOutStoreApi", description = "出库单操作接口")
@RestController
@RequestMapping("/tuOutStoreApi")
public class TuOutStoreControl {

    @Autowired
    private TuOutStoreService tuOutStoreService;

    /**
     * 分页查询出库单关联集合
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="分页查询出库单关联集合", notes="")
    @RequestMapping(value="/listTuOutStoreRelate", method= RequestMethod.POST)
    public Page<TuOutStore> listTuOutStoreRelate(@ApiParam(value = "出库单分页信息实体") @RequestBody PageInfo<TuOutStore> pageInfo){
        return tuOutStoreService.listTuOutStoreRelate(pageInfo);
    }

    /**
     * 分页查询出库单确认集合
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="分页查询出库单确认集合", notes="")
    @RequestMapping(value="/listTuOutStoreConfirm", method= RequestMethod.POST)
    public Page<TuOutStore> listTuOutStoreConfirm(@ApiParam(value = "出库单分页信息实体") @RequestBody PageInfo<TuOutStore> pageInfo){
        return tuOutStoreService.listTuOutStoreConfirm(pageInfo);
    }

    /**
     * 根据主键查询出库单
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="根据主键查询出库单", notes="")
    @RequestMapping(value="/getTuOutStore", method= RequestMethod.GET)
    public TuOutStore getTuOutStore(@ApiParam(value = "出库单主键") @RequestParam String id){
        return tuOutStoreService.getTuOutStore(id);
    }

    /**
     * 出库关联操作
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="出库关联操作", notes="")
    @RequestMapping(value="/tuOutStoreRelateAll", method= RequestMethod.GET)
    public ResultObject tuOutStoreRelateAll(@ApiParam(value = "出库单号") @RequestParam String outStoreNo, @ApiParam(value = "操作人") @RequestParam String loginName){
        return tuOutStoreService.tuOutStoreRelateAll(outStoreNo, loginName);
    }

    /**
     * 出库确认操作
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="出库确认操作", notes="")
    @RequestMapping(value="/tuOutStoreConfirm", method= RequestMethod.GET)
    public ResultObject tuOutStoreConfirm(@ApiParam(value = "出库单号") @RequestParam String outStoreNo, @ApiParam(value = "操作人") @RequestParam String loginName){
        return tuOutStoreService.tuOutStoreConfirm(outStoreNo, loginName);
    }
}
