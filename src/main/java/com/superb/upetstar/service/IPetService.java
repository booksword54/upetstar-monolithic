package com.superb.upetstar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.superb.upetstar.pojo.entity.AdoptApplication;
import com.superb.upetstar.pojo.entity.Pet;
import com.superb.upetstar.pojo.vo.PetDetailVO;
import com.superb.upetstar.pojo.vo.PetListVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hym
 * @since 2022-04-15
 */
public interface IPetService extends IService<Pet> {

    /**
     * 按照宠物主人id查询宠物列表
     *
     * @param ownerId 宠物主人id
     * @return 宠物列表
     */
    List<Pet> findByOwnerId(Integer ownerId);

    /**
     * 按照宠物主人id查询宠物列表视图列表
     *
     * @param ownerId 宠物主人id
     * @return 宠物列表视图列表
     */
    List<PetListVO> findPetListVOsByOwnerId(Integer ownerId);

    /**
     * 分页查询宠物列表
     *
     * @param current 当前页码
     * @param limit   每页个数
     * @return 宠物列表分页
     */
    List<Pet> listPage(Integer current, Integer limit);

    /**
     * 根据领养申请更新宠物信息
     *
     * @param adoptApplication
     * @return
     */
    int updateByAdoptApply(AdoptApplication adoptApplication);

    /**
     * 根据宠物id查询宠物详情
     *
     * @param id
     * @return
     */
    PetDetailVO findDetailById(Integer id);

    /**
     * 关键字查询宠物id列表
     *
     * @param word
     * @return
     */
    List<Integer> searchPetIds(String word);

}
