package com.superb.upetstar.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author hym
 * @description
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("search_word")
public class SearchWord extends BaseEntity {
    private String word;
    private Integer number;
}
