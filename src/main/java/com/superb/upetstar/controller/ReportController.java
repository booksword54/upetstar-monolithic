package com.superb.upetstar.controller;


import com.superb.upetstar.pojo.entity.Report;
import com.superb.upetstar.pojo.response.Result;
import com.superb.upetstar.service.IReportService;
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
@Api(tags = "举报相关")
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private IReportService reportService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public Result list() {
        return Result.success().data("reportList", reportService.list());
    }

    @ApiOperation("分页查询举报信息列表")
    @GetMapping("/listPage/{limit}/{current}")
    public Result listPage(@PathVariable("current") Integer current, @PathVariable("limit") Integer limit) {
        List<Report> reportPage = reportService.listPage(current, limit);
        return Result.success().data("reportPage", reportPage);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody Report report) {
        boolean save = reportService.save(report);
        return save ? Result.success() : Result.fail();
    }

    /**
     * 信息
     */
    @GetMapping("get/{id}")
    public Result get(@PathVariable("id") Integer id) {
        Report report = reportService.getById(id);
        return Result.success().data("report", report);
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody Report report) {
        boolean b = reportService.updateById(report);
        return b ? Result.success() : Result.fail();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        boolean removeById = reportService.removeById(id);
        return removeById ? Result.success() : Result.fail();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Integer[] ids) {
        boolean removeByIds = reportService.removeByIds(Arrays.asList(ids));
        return removeByIds ? Result.success() : Result.fail();
    }

}
