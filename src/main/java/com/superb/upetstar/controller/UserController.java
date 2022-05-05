package com.superb.upetstar.controller;

import com.superb.upetstar.pojo.entity.AdoptApplication;
import com.superb.upetstar.pojo.entity.User;
import com.superb.upetstar.pojo.response.Result;
import com.superb.upetstar.pojo.vo.AdoptRecordVO;
import com.superb.upetstar.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author hym
 * @description
 */
@Api(tags = "用户相关")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public Result list() {
        return Result.success().data("userList", userService.list());
    }

    @ApiOperation("分页查询用户列表")
    @GetMapping("/listPage/{limit}/{current}")
    public Result listPage(@PathVariable("current") Integer current, @PathVariable("limit") Integer limit) {
        List<User> userPage = userService.listPage(current, limit);
        return Result.success().data("userPage", userPage);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @CachePut(value = "userCache", condition = "#result.code=200", keyGenerator = "keyGenerator")
    public Result save(@RequestBody User user) {
        boolean save = userService.save(user);
        return save ? Result.success() : Result.fail();
    }

    /**
     * 根据id查询用户
     */
    @GetMapping("get/{id}")
    public Result get(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        return Result.success().data("user", user);
    }

    /**
     * 渲染当前用户信息
     */
    //@GetMapping("get")
    //public Result get() {
    //    UserInfoVO userInfoVO = userService.get();
    //    return Result.success().data("userInfo", userInfoVO);
    //}

    /**
     * 修改
     */
    @PutMapping("/update")
    @CachePut(value = "userCache", condition = "#result.code=200", keyGenerator = "keyGenerator")
    public Result update(@RequestBody User user) {
        boolean b = userService.updateById(user);
        return b ? Result.success() : Result.fail();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "userCache", beforeInvocation = true, condition = "#result.code=200", keyGenerator = "keyGenerator")
    public Result delete(@PathVariable("id") Integer id) {
        boolean removeById = userService.removeById(id);
        return removeById ? Result.success() : Result.fail();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/deleteBatch")
    @CacheEvict(value = "userCache", beforeInvocation = true, condition = "#result.code=200", keyGenerator = "keyGenerator")
    public Result deleteBatch(@RequestBody String[] ids) {
        boolean removeByIds = userService.removeByIds(Arrays.asList(ids));
        return removeByIds ? Result.success() : Result.fail();
    }

    @ApiOperation("通过id获取领养记录")
    @Cacheable(value = "userCache", keyGenerator = "keyGenerator")
    @GetMapping("/getAdoptRecordById")
    public Result getAdoptRecordById(@RequestParam("id") Integer adoptId) {
        List<AdoptRecordVO> adoptRecordVOS = userService.getAdoptRecordById(adoptId);
        return Result.success().data("adoptRecordVOS", adoptRecordVOS);
    }

    @ApiOperation("通过id获取送养记录")
    @Cacheable(value = "userCache", keyGenerator = "keyGenerator")
    @GetMapping("/getGiveRecordById")
    public Result getGiveRecordById(@RequestParam("id") Integer giveId) {
        List<AdoptRecordVO> adoptRecordVOS = userService.getGiveRecordById(giveId);
        return Result.success().data("adoptRecordVOS", adoptRecordVOS);
    }

    /**
     * 根据用户openId查询用户信息
     * 没有查询到就根据openId注册
     */
    @ApiOperation("根据用户openId查询用户信息")
    @Cacheable(value = "userCache", keyGenerator = "keyGenerator")
    @GetMapping("/getByOpenId")
    public Result getByOpenId(@RequestParam("openId") String openId) {
        User user = userService.getByOpenId(openId);
        return Result.success().data("user", user);
    }

    /**
     * 查询宠物申请列表
     */
    @ApiOperation("查询宠物申请列表")
    @Cacheable(value = "userCache", keyGenerator = "keyGenerator")
    @GetMapping("/getApplyList")
    public Result getApplyList(@RequestParam Integer uid) {
        List<AdoptApplication> adoptApplications = userService.getApplyList(uid);
        return Result.success().data("adoptApplications", adoptApplications);
    }

    @ApiOperation("同意领养宠物")
    @GetMapping("/agreeAdopt")
    public Result agreeAdopt(@RequestParam Integer isAgreed, @RequestParam Integer adoptApplyId) {
        boolean agree = userService.agreeAdopt(isAgreed, adoptApplyId); // 返回用户是否同意 1 不同意 2 同意
        return Result.success().data("agree", agree);
    }

    //@ApiOperation("获取当前用户领养记录")
    //@GetMapping("/getAdoptRecord")
    //public Result getAdoptRecord() {
    //    List<AdoptRecordVO> adoptRecordVOS = userService.getAdoptRecord();
    //    return Result.success().data("adoptRecordVOS", adoptRecordVOS);
    //}

    //@ApiOperation("获取当前用户送养记录")
    //@GetMapping("/getGiveRecord")
    //public Result getGiveRecord() {
    //    List<AdoptRecordVO> adoptRecordVOS = userService.getGiveRecord();
    //    return Result.success().data("adoptRecordVOS", adoptRecordVOS);
    //}

}
