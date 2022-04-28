package com.superb.upetstar.controller;


import com.superb.upetstar.pojo.entity.AdoptFeedback;
import com.superb.upetstar.pojo.response.Result;
import com.superb.upetstar.pojo.entity.CircleComment;
import com.superb.upetstar.service.ICircleCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hym
 * @since 2022-04-15
 */
@Api(tags = "圈子评论相关")
@RestController
@RequestMapping("/circleComment")
public class CircleCommentController {

    @Autowired
    private ICircleCommentService circleCommentService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public Result list() {
        return Result.success().data("commentList", circleCommentService.list());
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody CircleComment circleComment) {
        boolean save = circleCommentService.save(circleComment);
        return save ? Result.success() : Result.fail();
    }

    /**
     * 信息
     */
    @GetMapping("get/{id}")
    public Result get(@PathVariable("id") Integer id) {
        CircleComment circleComment = circleCommentService.getById(id);
        return Result.success().data("comment", circleComment);
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody CircleComment circleComment) {
        boolean b = circleCommentService.updateById(circleComment);
        return b ? Result.success() : Result.fail();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        boolean removeById = circleCommentService.removeById(id);
        return removeById ? Result.success() : Result.fail();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Integer[] ids) {
        boolean removeByIds = circleCommentService.removeByIds(Arrays.asList(ids));
        return removeByIds ? Result.success() : Result.fail();
    }

}
