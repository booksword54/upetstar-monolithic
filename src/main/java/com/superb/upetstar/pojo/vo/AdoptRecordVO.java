package com.superb.upetstar.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author hym
 * @description
 */
@Data
@ApiModel("领送养记录视图对象")
public class AdoptRecordVO {
    private Integer adoptRecordId; // 领养记录id
    private String title; // 标题
    private String description; // 描述
    private String address; // 宠物地址
    private String image; // 宠物的第一张图
    private String breed; // 宠物种类
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date giveTime; // 送养（领养）时间
}
