package com.ydj.swaggerdemo.controller;

import com.ydj.swaggerdemo.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/swagger")
@Api(tags = {"swagger 注解使用示例"})
@CrossOrigin
public class TestController {

    @ApiOperation(
            value = "根据用户 ID 和 用户名称获取用户信息"
            , notes = "测试"
            , httpMethod = "POST"
            , produces = "application/json"
            , protocols = "http"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户 id 撒", required = true, paramType = "query", dataType = "int")
            , @ApiImplicitParam(name = "name", value = "用户名称撒", paramType = "query", dataType = "String")
    })
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "request success ~~~", response = User.class),
                    @ApiResponse(code = 200, message = "success")
            }
    )
    @GetMapping(value = "/test")
    public User getUserByIdAndName(Integer id, String name) {
        return new User(id, name);
    }


    @ApiOperation(
            value = "根据用户 ID 称获取用户"
            , notes = "测试"
            , httpMethod = "POST"
            , produces = "application/json"
            , protocols = "http"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户 id", required = true, paramType = "query", dataType = "int")
            , @ApiImplicitParam(name = "name", value = "用户名称", paramType = "query", dataType = "String")
    })
    @ApiResponse(
            code = 200
            , message = "request success ~~~"
            , response = User.class
    )
    @GetMapping(value = "/haha")
    public User getUserById(Integer id, String name) {
        return new User(id, name);
    }

}
