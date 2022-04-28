package com.superb.upetstar.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author hym
 * @description
 */
@Data
@ApiModel("新增宠物视图对象")
public class PetAddVO {
    private String name;
    private String images;
    private String description;
    private Integer age;
    private String gender;
    private String breed;
    private String ster;
    private String vac;
    private String vacImages;
}
