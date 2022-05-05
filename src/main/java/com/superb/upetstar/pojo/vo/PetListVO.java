package com.superb.upetstar.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author hym
 * @description
 */
@Data
@ApiModel("宠物列表视图对象")
@NoArgsConstructor
@AllArgsConstructor
public class PetListVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text; // name
    private Integer value; // id
}
