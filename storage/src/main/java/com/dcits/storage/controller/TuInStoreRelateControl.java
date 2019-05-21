package com.dcits.storage.controller;

import com.dcits.storage.model.TuInStoreRelate;
import com.dcits.storage.service.TuInStoreRelateService;
import com.dcits.storage.utils.PageInfo;
import com.dcits.storage.utils.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 入库单关联控制层
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Api(tags = "tuInStoreRelateApi", description = "入库单关联操作接口")
@RestController
@RequestMapping("/tuInStoreRelateApi")
public class TuInStoreRelateControl {

    @Autowired
    private TuInStoreRelateService tuInStoreRelateService;

    /**
     * 入库单关联操作
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="入库单关联操作", notes="")
    @RequestMapping(value="/saveTuInStoreRelate", method= RequestMethod.POST)
    public ResultObject saveTuInStoreRelate(@ApiParam(value = "关联数据集合") @RequestBody String[] value) throws Exception{
        return tuInStoreRelateService.saveTuInStoreRelate(value);
    }

    /**
     * 根据入库单号查询其关联的货物
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="根据入库单号查询其关联的货物", notes="")
    @RequestMapping(value="/listTuInStoreRelateByInStoreNo", method= RequestMethod.GET)
    public String listTuInStoreRelateByInStoreNo(@ApiParam(value = "入库单号") @RequestParam String inStoreNo){
        return tuInStoreRelateService.listTuInStoreRelateByInStoreNo(inStoreNo);
    }

    /**
     * 根据主键查询入库单关联
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="根据主键查询入库单关联", notes="")
    @RequestMapping(value="/getTuInStoreRelate", method= RequestMethod.GET)
    public TuInStoreRelate getTuInStoreRelate(@ApiParam(value = "入库单关联主键") @RequestParam String id){
        return tuInStoreRelateService.getTuInStoreRelate(id);
    }

    /**
     * 入库单确认操作
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="入库单确认操作", notes="")
    @RequestMapping(value="/saveTuInStoreConfirm", method= RequestMethod.POST)
    public ResultObject saveTuInStoreConfirm(@ApiParam(value = "确认数据集合") @RequestBody String[] value) throws Exception{
        return tuInStoreRelateService.saveTuInStoreConfirm(value);
    }

    /**
     * 判断托盘号是否存在
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="判断托盘号是否存在", notes="")
    @RequestMapping(value="/isExistBarCode", method= RequestMethod.GET)
    public ResultObject isExistBarCode(@ApiParam(value = "入库单号") @RequestParam String inStoreNo, @ApiParam(value = "托盘号") @RequestParam String barCode) {
        return tuInStoreRelateService.isExistBarCode(inStoreNo, barCode);
    }

    /**
     * 移库操作
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="移库操作", notes="")
    @RequestMapping(value="/tuInStoreRelateMove", method= RequestMethod.GET)
    public ResultObject tuInStoreRelateMove(@ApiParam(value = "入库单号") @RequestParam String inStoreNo, @ApiParam(value = "新库位号") @RequestParam String locationNo,
                                            @ApiParam(value = "托盘号") @RequestParam String barCode, @ApiParam(value = "操作人") @RequestParam String loginName) throws Exception{
        return tuInStoreRelateService.tuInStoreRelateMove(inStoreNo, locationNo, barCode, loginName);
    }
}
