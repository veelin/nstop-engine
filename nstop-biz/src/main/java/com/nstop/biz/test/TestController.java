package com.nstop.biz.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linziwei
 * @date 2022/12/8
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private LeaveServiceImpl leaveService;

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public String test() {
        leaveService.run();
        return "";
    }
}
