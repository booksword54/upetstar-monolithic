package com.superb.upetstar.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superb.upetstar.mapper.AdoptFeedbackMapper;
import com.superb.upetstar.pojo.entity.AdoptFeedback;
import com.superb.upetstar.pojo.entity.AdoptFeedback;
import com.superb.upetstar.service.IAdoptFeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hym
 * @since 2022-04-15
 */
@Service
@Transactional
public class AdoptFeedbackServiceImpl extends ServiceImpl<AdoptFeedbackMapper, AdoptFeedback> implements IAdoptFeedbackService {

    @Override
    public List<AdoptFeedback> listPage(Integer current, Integer limit) {
        Page<AdoptFeedback> page = new Page<>(current, limit);
        Page<AdoptFeedback> selectPage = baseMapper.selectPage(page, null);
        return selectPage.getRecords();
    }
}
