package com.superb.upetstar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superb.upetstar.mapper.AdoptRecordMapper;
import com.superb.upetstar.pojo.entity.AdoptRecord;
import com.superb.upetstar.pojo.entity.AdoptRecord;
import com.superb.upetstar.pojo.entity.Pet;
import com.superb.upetstar.pojo.entity.User;
import com.superb.upetstar.pojo.es.ESAdoptRecord;
import com.superb.upetstar.pojo.es.ESPet;
import com.superb.upetstar.pojo.vo.AdoptRecordDetailVO;
import com.superb.upetstar.pojo.vo.AdoptRecordVO;
import com.superb.upetstar.repository.AdoptRecordRepository;
import com.superb.upetstar.repository.PetRepository;
import com.superb.upetstar.service.IAdoptRecordService;
import com.superb.upetstar.service.IPetService;
import com.superb.upetstar.service.ISearchWordService;
import com.superb.upetstar.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hym
 * @since 2022-04-15
 */
@Service
@Transactional
public class AdoptRecordServiceImpl extends ServiceImpl<AdoptRecordMapper, AdoptRecord> implements IAdoptRecordService {

    @Resource
    private IPetService petService;

    @Resource
    private IUserService userService;

    @Override
    public Integer adopt(Integer id, Integer aId) {
        AdoptRecord adoptRecord = baseMapper.selectById(id);
        adoptRecord.setAdoptId(aId);
        return baseMapper.updateById(adoptRecord);
    }

    @Override
    public Integer audit(Integer id, Integer auditId, Integer status) {
        AdoptRecord adoptRecord = baseMapper.selectById(id);
        adoptRecord.setAuditId(auditId);
        return baseMapper.updateById(adoptRecord);
    }

    @Override
    public List<AdoptRecord> listPage(Integer current, Integer limit) {
        Page<AdoptRecord> page = new Page<>(current, limit);
        Page<AdoptRecord> selectPage = baseMapper.selectPage(page, null);
        return selectPage.getRecords();
    }

    @Override
    public AdoptRecordDetailVO getAdoptRecordDetail(Integer id) {
        AdoptRecord adoptRecord = baseMapper.selectById(id);
        Integer petId = adoptRecord.getPetId();
        Integer giveId = adoptRecord.getGiveId();
        Pet pet = petService.getById(petId);
        User user = userService.getById(giveId);

        AdoptRecordDetailVO adoptRecordDetailVO = new AdoptRecordDetailVO();
        if (user != null) {
            adoptRecordDetailVO.setAvatarUrl(user.getAvatarUrl());// 用户头像
            adoptRecordDetailVO.setUsername(user.getUsername()); // 用户名
        }
        if (pet != null) {
            adoptRecordDetailVO.setBreed(pet.getBreed());// 宠物种类
            adoptRecordDetailVO.setAge(pet.getAge());// 宠物年龄
            adoptRecordDetailVO.setSter(pet.getSter());// 宠物绝育信息
            adoptRecordDetailVO.setVac(pet.getVac());// 宠物疫苗认证
            adoptRecordDetailVO.setImages(StringUtils.split(",", pet.getImages()));
            adoptRecordDetailVO.setGender(pet.getGender());// 宠物性别
        }
        if (adoptRecord != null) {
            adoptRecordDetailVO.setAdoptRecordId(adoptRecord.getId());
            adoptRecordDetailVO.setDescription(adoptRecord.getDescription()); // 送养描述
            adoptRecordDetailVO.setGiveTime(adoptRecord.getGiveTime()); // 送养时间
        }
        return adoptRecordDetailVO;
    }

    @Override
    public AdoptRecordDetailVO getGiveRecordDetail(Integer id) {
        return getAdoptRecordDetail(id);
    }

    @Override
    public ESAdoptRecord buildESAdoptRecord(AdoptRecord adoptRecord) {
        if (adoptRecord == null) {
            return null;
        }
        ESAdoptRecord esAdoptRecord = new ESAdoptRecord();
        BeanUtils.copyProperties(adoptRecord, esAdoptRecord);
        return esAdoptRecord;
    }

}
