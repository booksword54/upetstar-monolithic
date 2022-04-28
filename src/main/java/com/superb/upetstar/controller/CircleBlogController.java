package com.superb.upetstar.controller;


import com.superb.upetstar.pojo.entity.AdoptFeedback;
import com.superb.upetstar.pojo.response.Result;
import com.superb.upetstar.pojo.entity.CircleBlog;
import com.superb.upetstar.service.ICircleBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hym
 * @since 2022-04-15
 */
@Api(tags = "圈子动态相关")
@RestController
@RequestMapping("/circleBlog")
public class CircleBlogController {

    @Autowired
    private ICircleBlogService circleBlogService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public Result list() {
        return Result.success().data("blogList", circleBlogService.list());
    }


    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody CircleBlog circleBlog) {
        boolean save = circleBlogService.save(circleBlog);
        return save ? Result.success() : Result.fail();
    }

    /**
     * 信息
     */
    @GetMapping("get/{id}")
    public Result get(@PathVariable("id") Integer id) {
        CircleBlog circleBlog = circleBlogService.getById(id);
        return Result.success().data("blog", circleBlog);
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody CircleBlog circleBlog) {
        boolean b = circleBlogService.updateById(circleBlog);
        return b ? Result.success() : Result.fail();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        boolean removeById = circleBlogService.removeById(id);
        return removeById ? Result.success() : Result.fail();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Integer[] ids) {
        boolean removeByIds = circleBlogService.removeByIds(Arrays.asList(ids));
        return removeByIds ? Result.success() : Result.fail();
    }

}
