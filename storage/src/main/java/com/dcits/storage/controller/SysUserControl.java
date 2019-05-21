package com.dcits.storage.controller;

import com.dcits.storage.service.SysUserService;
import com.dcits.storage.utils.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Api(tags = "sysUserApi", description = "用户操作接口")
@RestController
@RequestMapping("/sysUserApi")
public class SysUserControl {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录校验
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @ApiOperation(value="登录校验", notes="")
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public ResultObject login(@ApiParam(value = "用户名") @RequestParam String loginName, @ApiParam(value = "密码") @RequestParam String password){
        return sysUserService.login(loginName, password);
    }
}
