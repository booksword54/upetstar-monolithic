package com.superb.upetstar.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author hym
 * @description
 */
@Data
@ApiModel("宠物视图对象")
public class PetVO {
    private String name;
    private String breed;
    private String imageId;
    private String vac;
    private String description;
    private String publishTime;
}
