package com.superb.upetstar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superb.upetstar.mapper.CircleCommentMapper;
import com.superb.upetstar.pojo.entity.CircleComment;
import com.superb.upetstar.service.ICircleCommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class CircleCommentServiceImpl extends ServiceImpl<CircleCommentMapper, CircleComment> implements ICircleCommentService {

}
