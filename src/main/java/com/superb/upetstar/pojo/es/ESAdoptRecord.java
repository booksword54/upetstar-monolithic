package com.superb.upetstar.pojo.es;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author hym
 * @description ES领养记录文档对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "adopt_record", createIndex = true, shards = 1, replicas = 1)
public class ESAdoptRecord {
    @Id
    private Integer id; // 文档id/送养记录id
    @Field(type = FieldType.Integer, index = false)
    private Integer petId; // 关联宠物id
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title; // 送养标题
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String description; // 送养描述
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd", index = false)
    private Date giveTime; // 送养时间
}
