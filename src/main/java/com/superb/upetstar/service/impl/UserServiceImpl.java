package com.superb.upetstar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superb.upetstar.exception.UPetStarException;
import com.superb.upetstar.mapper.UserMapper;
import com.superb.upetstar.pojo.entity.*;
import com.superb.upetstar.pojo.entity.User;
import com.superb.upetstar.pojo.vo.AdoptRecordVO;
import com.superb.upetstar.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hym
 * @since 2022-04-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private IAdoptRecordService adoptRecordService;

    @Resource
    private IPetService petService;

    @Resource
    private IAdoptApplicationService adoptApplicationService;

    @Resource
    private ICommunityService communityService;

    /**
     * 根据openId获取用户
     *
     * @param openId
     * @return
     */
    @Override
    public User getByOpenId(String openId) {
        if (StringUtils.isEmpty(openId)) {
            return null;
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("open_id", openId);
        User user = baseMapper.selectOne(userQueryWrapper);
        if (user == null) { // 没有open_id，注册新用户
            user = new User();
            user.setOpenId(openId);
            baseMapper.insert(user);
        }
        return user;
    }

    /**
     * 根据id查询宠物领养申请列表
     *
     * @param uid
     * @return
     */
    @Override
    public List<AdoptApplication> getApplyList(Integer uid) {
        if (uid == null) {
            return null;
        }
        return adoptApplicationService.getListByGId(uid);
    }

    @Override
    public boolean agreeAdopt(Integer isAgreed, Integer adoptApplyId) {
        if (isAgreed == null || adoptApplyId == null) {
            throw new UPetStarException("请选中领养申请id并选择是否同意");
        }
        return adoptApplicationService.agree(isAgreed, adoptApplyId);
    }

    @Override
    public Community getByUId(Integer userId) {
        if (userId == null) {
            return null;
        }
        User user = baseMapper.selectById(userId);
        return communityService.getById(user.getCommunityId());
    }

    @Override
    public List<User> listPage(Integer current, Integer limit) {
        Page<User> page = new Page<>(current, limit);
        Page<User> selectPage = baseMapper.selectPage(page, null);
        return selectPage.getRecords();
    }

    @Override
    public List<AdoptRecordVO> getAdoptRecordById(Integer adoptId) {
        ArrayList<AdoptRecordVO> adoptRecordVOS = new ArrayList<>();
        if (adoptId == null) {
            return adoptRecordVOS;
        }
        QueryWrapper<AdoptRecord> adoptRecordQueryWrapper = new QueryWrapper<>();
        adoptRecordQueryWrapper.eq("adopt_id", adoptId);
        List<AdoptRecord> list = adoptRecordService.list(adoptRecordQueryWrapper);
        for (AdoptRecord adoptRecord : list) {
            AdoptRecordVO adoptRecordVO = new AdoptRecordVO();
            //    private Integer adoptRecordId; // 领养记录id
            //    private String title; // 领送养标题
            //    private String description; // 送养描述
            //    private Date giveTime; // 送养时间
            adoptRecordVO.setAdoptRecordId(adoptRecord.getId());
            adoptRecordVO.setTitle(adoptRecord.getTitle());
            adoptRecordVO.setDescription(adoptRecord.getDescription());
            adoptRecordVO.setGiveTime(adoptRecord.getGiveTime());
            //    private String address; // 宠物地址
            //    private String image; // 宠物的第一张图
            //    private String breed; // 宠物种类
            Integer petId = adoptRecord.getPetId();
            if (petId == null) { // 宠物id为空，不封装宠物信息
                adoptRecordVOS.add(adoptRecordVO);
                continue;
            }
            // 封装宠物信息
            Pet pet = petService.getById(petId);
            if (pet != null) {
                String address = pet.getAddress();
                String breed = pet.getBreed();
                String images = pet.getImages();
                String firstImg = StringUtils.split(images, ",")[0];
                adoptRecordVO.setAddress(address);
                adoptRecordVO.setBreed(breed);
                adoptRecordVO.setImage(firstImg);
            }
            adoptRecordVOS.add(adoptRecordVO);
        }
        return adoptRecordVOS;
    }

    @Override
    public List<AdoptRecordVO> getGiveRecordById(Integer giveId) {
        ArrayList<AdoptRecordVO> adoptRecordVOS = new ArrayList<>();
        if (giveId == null) {
            return adoptRecordVOS;
        }
        QueryWrapper<AdoptRecord> adoptRecordQueryWrapper = new QueryWrapper<>();
        adoptRecordQueryWrapper.eq("give_id", giveId);
        List<AdoptRecord> list = adoptRecordService.list(adoptRecordQueryWrapper);
        for (AdoptRecord adoptRecord : list) {
            AdoptRecordVO adoptRecordVO = new AdoptRecordVO();
            //    private String title; // 领送养标题
            //    private String description; // 送养描述
            //    private Date giveTime; // 送养时间
            adoptRecordVO.setAdoptRecordId(adoptRecord.getId());
            adoptRecordVO.setTitle(adoptRecord.getTitle());
            adoptRecordVO.setDescription(adoptRecord.getDescription());
            adoptRecordVO.setGiveTime(adoptRecord.getGiveTime());
            //    private String address; // 宠物地址
            //    private String image; // 宠物的第一张图
            //    private String breed; // 宠物种类
            Integer petId = adoptRecord.getPetId();
            if (petId == null) { // 宠物id为空，不封装宠物信息
                adoptRecordVOS.add(adoptRecordVO);
                continue;
            }
            // 封装宠物信息
            Pet pet = petService.getById(petId);
            if (pet != null) {
                String address = pet.getAddress();
                String breed = pet.getBreed();
                String images = pet.getImages();
                String firstImg = StringUtils.split(images, ",")[0];
                adoptRecordVO.setAddress(address);
                adoptRecordVO.setBreed(breed);
                adoptRecordVO.setImage(firstImg);
            }
            adoptRecordVOS.add(adoptRecordVO);
        }
        return adoptRecordVOS;
    }

    //@Override
    //public UserInfoVO get() {
    //    String openId = UPetStarUtil.getOpenId();
    //    User user = getByOpenId(openId);
    //    UserInfoVO userInfoVO = new UserInfoVO();
    //    BeanUtils.copyProperties(user, userInfoVO);
    //    return userInfoVO;
    //}

    //@Override
    //public List<AdoptRecordVO> getAdoptRecord() {
    //    String openId = UPetStarUtil.getOpenId();
    //    User user = getByOpenId(openId);
    //    return getAdoptRecordById(user.getId());
    //}

    //@Override
    //public List<AdoptRecordVO> getGiveRecord() {
    //    String openId = UPetStarUtil.getOpenId();
    //    User user = getByOpenId(openId);
    //    return getGiveRecordById(user.getId());
    //}
}
