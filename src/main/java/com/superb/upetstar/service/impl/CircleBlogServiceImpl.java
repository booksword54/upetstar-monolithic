package com.superb.upetstar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superb.upetstar.mapper.CircleBlogMapper;
import com.superb.upetstar.pojo.entity.CircleBlog;
import com.superb.upetstar.service.ICircleBlogService;
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
public class CircleBlogServiceImpl extends ServiceImpl<CircleBlogMapper, CircleBlog> implements ICircleBlogService {

}
