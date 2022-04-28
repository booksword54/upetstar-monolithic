package com.superb.upetstar.controller;

import com.superb.upetstar.pojo.response.Result;
import com.superb.upetstar.service.ISearchWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hym
 * @description
 */
@Api(tags = "搜索关键词相关")
@RestController
public class SearchWordController {
    @Resource
    private ISearchWordService searchWordService;

    @ApiOperation("显示热门搜索记录")
    @GetMapping("hotWords")
    public Result hotWords() {
        List<String> hotWords = searchWordService.getHotWords();
        return Result.success().data("hotWords", hotWords);
    }
}
