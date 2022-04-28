package com.superb.upetstar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.superb.upetstar.pojo.entity.ActivityApplication;
import com.superb.upetstar.pojo.entity.AdoptApplication;
import com.superb.upetstar.pojo.vo.AdoptApplicationVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hym
 * @since 2022-04-15
 */
public interface IActivityApplicationService extends IService<ActivityApplication> {

    /**
     * 分页查询活动报名列表
     *
     * @param current 当前页
     * @param limit   每页个数
     * @return 报名列表分页
     */
    List<ActivityApplication> listPage(Integer current, Integer limit);
}
