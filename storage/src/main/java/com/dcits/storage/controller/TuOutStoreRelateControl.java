package com.dcits.storage.controller;

import com.dcits.storage.model.TuOutStoreRelate;
import com.dcits.storage.service.TuOutStoreRelateService;
import com.dcits.storage.utils.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 出库单关联控制层
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Api(tags = "tuOutStoreRelateApi", description = "出库单关联操作接口")
@RestController
@RequestMapping("/tuOutStoreRelateApi")
public class TuOutStoreRelateControl {

    @Autowired
    private TuOutStoreRelateService tuOutStoreRelateService;

    /**
     * 根据出库单号查询其关联的货物
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="根据出库单号查询其关联的货物", notes="")
    @RequestMapping(value="/listTuOutStoreRelateByOutStoreNo", method= RequestMethod.GET)
    public String listTuOutStoreRelateByOutStoreNo(@ApiParam(value = "出库单号") @RequestParam String outStoreNo){
        return tuOutStoreRelateService.listTuOutStoreRelateByOutStoreNo(outStoreNo);
    }

    /**
     * 单个出库关联操作
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="单个出库关联操作", notes="")
    @RequestMapping(value="/tuOutStoreRelateSingle", method= RequestMethod.GET)
    public ResultObject tuOutStoreRelateSingle(@ApiParam(value = "出库单号") @RequestParam String outStoreNo, @ApiParam(value = "托盘号") @RequestParam String barCode){
        return tuOutStoreRelateService.tuOutStoreRelateSingle(outStoreNo, barCode);
    }

    /**
     * 单个出库关联取消操作
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="单个出库关联取消操作", notes="")
    @RequestMapping(value="/tuOutStoreRelateCancelSingle", method= RequestMethod.GET)
    public ResultObject tuOutStoreRelateCancelSingle(@ApiParam(value = "出库单号") @RequestParam String outStoreNo, @ApiParam(value = "托盘号") @RequestParam String barCode){
        return tuOutStoreRelateService.tuOutStoreRelateCancelSingle(outStoreNo, barCode);
    }

    /**
     * 根据主键查询出库单关联
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="根据主键查询出库单关联", notes="")
    @RequestMapping(value="/getTuOutStoreRelate", method= RequestMethod.GET)
    public TuOutStoreRelate getTuOutStoreRelate(@ApiParam(value = "出库单关联主键") @RequestParam String id){
        return tuOutStoreRelateService.getTuOutStoreRelate(id);
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
    public ResultObject isExistBarCode(@ApiParam(value = "出库单号") @RequestParam String outStoreNo, @ApiParam(value = "托盘号") @RequestParam String barCode) {
        return tuOutStoreRelateService.isExistBarCode(outStoreNo, barCode);
    }

    /**
     * 反向入库操作
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="反向入库操作", notes="")
    @RequestMapping(value="/tuOutStoreRelateReverse", method= RequestMethod.GET)
    public ResultObject tuOutStoreRelateReverse(@ApiParam(value = "出库单号") @RequestParam String outStoreNo, @ApiParam(value = "新库位号") @RequestParam String locationNo,
                                            @ApiParam(value = "托盘号") @RequestParam String barCode, @ApiParam(value = "操作人") @RequestParam String loginName) throws Exception{
        return tuOutStoreRelateService.tuOutStoreRelateReverse(outStoreNo, locationNo, barCode, loginName);
    }
}
