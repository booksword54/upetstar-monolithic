package com.superb.upetstar.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author hym
 * @description
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("adopt_record")
public class AdoptRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer giveId; // 送养人id
    private Integer adoptId; // 领养人id
    private Integer auditId; // 审核员id
    private Integer petId; // 宠物id
    private String title; // 领送养标题
    private String description; // 送养描述
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date giveTime; // 送养时间
    private Integer auditStatus; // 审核状态 0未审核 1未通过 2已通过
}
