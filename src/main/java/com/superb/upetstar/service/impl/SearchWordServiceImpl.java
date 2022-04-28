package com.superb.upetstar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superb.upetstar.mapper.SearchWordMapper;
import com.superb.upetstar.pojo.entity.SearchWord;
import com.superb.upetstar.service.ISearchWordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hym
 * @description
 */
@Service
@Transactional
public class SearchWordServiceImpl extends ServiceImpl<SearchWordMapper, SearchWord> implements ISearchWordService {
    @Override
    public void updateByWord(String word) {
        if (word == null) {
            return;
        }
        QueryWrapper<SearchWord> searchWordQueryWrapper = new QueryWrapper<>();
        searchWordQueryWrapper.eq("word", word);
        SearchWord searchWord = baseMapper.selectOne(searchWordQueryWrapper);
        if (searchWord == null) {     // 没有查过，新增记录
            searchWord.setWord(word).setNumber(1);
            baseMapper.insert(searchWord);
        } else {     // 查询过，更新记录
            searchWord.setNumber(searchWord.getNumber() + 1);
            baseMapper.updateById(searchWord);
        }
    }

    @Override
    public List<String> getHotWords() {
        QueryWrapper<SearchWord> searchWordQueryWrapper = new QueryWrapper<>();
        searchWordQueryWrapper.orderByAsc("number").last("limit 0 10");
        List<SearchWord> searchWords = baseMapper.selectList(searchWordQueryWrapper);
        return searchWords.stream().map(SearchWord::getWord).collect(Collectors.toList());
    }
}
