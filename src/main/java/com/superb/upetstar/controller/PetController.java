package com.superb.upetstar.controller;


import com.superb.upetstar.pojo.entity.Pet;
import com.superb.upetstar.pojo.response.Result;
import com.superb.upetstar.pojo.vo.PetListVO;
import com.superb.upetstar.service.IPetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hym
 * @since 2022-04-15
 */
@Api(tags = "宠物相关")
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private IPetService petService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public Result list() {
        return Result.success().data("petDetailList", petService.list());
    }

    @ApiOperation("分页查询宠物列表")
    @GetMapping("/listPage/{limit}/{current}")
    public Result listPage(@PathVariable("current") Integer current, @PathVariable("limit") Integer limit) {
        List<Pet> petPage = petService.listPage(current, limit);
        return Result.success().data("petPage", petPage);
    }

    /**
     * 保存
     */
    @ApiOperation("添加宠物")
    @CachePut(value = "petCache", condition = "#result.code=200", keyGenerator = "keyGenerator")
    @PostMapping("/save")
    public Result save(@RequestBody Pet pet) {
        boolean save = petService.save(pet);
        return save ? Result.success() : Result.fail();
    }

    /**
     * 信息
     */
    @ApiOperation("按id查询宠物")
    @GetMapping("get/{id}")
    public Result get(@PathVariable("id") Integer id) {
        Pet pet = petService.getById(id);
        return Result.success().data("pet", pet);
    }

    @ApiOperation("按照宠物主人id查询宠物")
    @Cacheable(value = "petCache", keyGenerator = "keyGenerator")
    @GetMapping("/findByOwnerId")
    public Result findByOwnerId(@RequestParam Integer ownerId) {
        List<Pet> petList = petService.findByOwnerId(ownerId);
        return Result.success().data("petList", petList);
    }

    @ApiOperation("按照宠物主人id查询宠物id和宠物名")
    @Cacheable(value = "petCache", keyGenerator = "keyGenerator")
    @GetMapping("/findPetListVOsByOwnerId")
    public Result findPetListVOsByOwnerId(@RequestParam Integer ownerId) {
        List<PetListVO> petListVOS = petService.findPetListVOsByOwnerId(ownerId);
        return Result.success().data("petListVOS", petListVOS);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @CachePut(value = "petCache", condition = "#result.code=200", keyGenerator = "keyGenerator")
    public Result update(@RequestBody Pet pet) {
        boolean b = petService.updateById(pet);
        return b ? Result.success() : Result.fail();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "petCache", beforeInvocation = true, condition = "#result.code=200", keyGenerator = "keyGenerator")
    public Result delete(@PathVariable("id") Integer id) {
        boolean removeById = petService.removeById(id);
        return removeById ? Result.success() : Result.fail();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/deleteBatch")
    @CacheEvict(value = "petCache", beforeInvocation = true, condition = "#result.code=200", keyGenerator = "keyGenerator")
    public Result deleteBatch(@RequestBody Integer[] ids) {
        boolean removeByIds = petService.removeByIds(Arrays.asList(ids));
        return removeByIds ? Result.success() : Result.fail();
    }


}
