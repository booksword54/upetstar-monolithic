package com.superb.upetstar.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superb.upetstar.mapper.ReportMapper;
import com.superb.upetstar.pojo.entity.Report;
import com.superb.upetstar.pojo.entity.Report;
import com.superb.upetstar.service.IReportService;
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
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements IReportService {

    @Override
    public List<Report> listPage(Integer current, Integer limit) {
        Page<Report> page = new Page<>(current, limit);
        Page<Report> selectPage = baseMapper.selectPage(page, null);
        return selectPage.getRecords();
    }
}
