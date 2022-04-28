package com.superb.upetstar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superb.upetstar.mapper.CActivityMapper;
import com.superb.upetstar.pojo.entity.CActivity;
import com.superb.upetstar.pojo.entity.CActivity;
import com.superb.upetstar.pojo.entity.CUser;
import com.superb.upetstar.service.ICActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hym
 * @since 2022-04-15
 */
@Service
@Transactional
public class CActivityServiceImpl extends ServiceImpl<CActivityMapper, CActivity> implements ICActivityService {

    @Override
    public List<CActivity> listPage(Integer current, Integer limit) {
        Page<CActivity> page = new Page<>(current, limit);
        Page<CActivity> selectPage = baseMapper.selectPage(page, null);
        return selectPage.getRecords();
    }

    @Override
    public List<CActivity> getActivityList(HttpSession session) {
        CUser cUser = (CUser) session.getAttribute("cUser");
        Integer cId = cUser.getCId();
        QueryWrapper<CActivity> cActivityQueryWrapper = new QueryWrapper<>();
        cActivityQueryWrapper.eq("id", cId);
        return baseMapper.selectList(cActivityQueryWrapper);
    }

    @Override
    public List<CActivity> getListByCId(Integer cid) {
        QueryWrapper<CActivity> cActivityQueryWrapper = new QueryWrapper<>();
        cActivityQueryWrapper.eq("community_id", cid);
        return baseMapper.selectList(cActivityQueryWrapper);
    }
}
