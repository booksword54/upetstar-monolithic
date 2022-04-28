package com.superb.upetstar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.superb.upetstar.pojo.entity.SearchWord;
import com.superb.upetstar.pojo.entity.User;

import java.util.List;

/**
 * @author hym
 * @description
 */
public interface ISearchWordService extends IService<SearchWord> {
    /**
     * 更新关键词查询次数
     *
     * @param word
     */
    void updateByWord(String word);

    /**
     * 显示热门搜索记录
     *
     * @return
     */
    List<String> getHotWords();
}
