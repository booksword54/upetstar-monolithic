package com.superb.upetstar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.superb.upetstar.pojo.entity.AdoptRecord;
import com.superb.upetstar.pojo.es.ESAdoptRecord;
import com.superb.upetstar.pojo.vo.AdoptRecordDetailVO;
import com.superb.upetstar.pojo.vo.AdoptRecordVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hym
 * @since 2022-04-15
 */
public interface IAdoptRecordService extends IService<AdoptRecord> {

    /**
     * 领养记录
     *
     * @param id  领养记录id
     * @param aId 领养人id
     * @return
     */
    Integer adopt(Integer id, Integer aId);

    /**
     * 领养审核
     *
     * @param id      领养记录id
     * @param auditId 审核员id
     * @param status  审核状态
     * @return
     */
    Integer audit(Integer id, Integer auditId, Integer status);

    /**
     * 分页查询领养记录列表
     *
     * @param current 当前页
     * @param limit   每页个数
     * @return 领养记录分页
     */
    List<AdoptRecord> listPage(Integer current, Integer limit);

    /**
     * 根据id查询领养记录
     *
     * @param id
     * @return
     */
    AdoptRecordDetailVO getAdoptRecordDetail(Integer id);

    /**
     * 根据送养记录id查询送养记录
     *
     * @param id
     * @return
     */
    AdoptRecordDetailVO getGiveRecordDetail(Integer id);

    /**
     * 实体对象转换为索引对象
     *
     * @param adoptRecord
     * @return
     */
    ESAdoptRecord buildESAdoptRecord(AdoptRecord adoptRecord);
}
