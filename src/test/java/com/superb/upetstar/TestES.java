package com.superb.upetstar;

import com.superb.upetstar.pojo.entity.AdoptRecord;
import com.superb.upetstar.pojo.entity.Pet;
import com.superb.upetstar.pojo.es.ESAdoptRecord;
import com.superb.upetstar.pojo.es.ESPet;
import com.superb.upetstar.repository.AdoptRecordRepository;
import com.superb.upetstar.repository.PetRepository;
import com.superb.upetstar.service.IAdoptRecordService;
import com.superb.upetstar.service.IPetService;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hym
 * @description
 */
@SpringBootTest
public class TestES {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private IPetService petService;
    @Autowired
    private IAdoptRecordService adoptRecordService;
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private AdoptRecordRepository adoptRecordRepository;
    @Autowired
    private PetRepository petRepository;

    @Test
    public void contextLoads1() {
        //elasticsearchRestTemplate.createIndex(ESPet.class);
        //elasticsearchRestTemplate.putMapping(ESPet.class);
        List<Pet> pets = petService.list();
        List<ESPet> esPets = pets.stream().map(pet -> {
            try {
                return pet.buildESPet();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        petRepository.saveAll(esPets);
    }

    @Test
    public void contextLoads2() {
        //elasticsearchRestTemplate.createIndex(ESAdoptRecord.class);
        //elasticsearchRestTemplate.putMapping(ESAdoptRecord.class);
        List<AdoptRecord> adoptRecords = adoptRecordService.list();
        List<ESAdoptRecord> esAdoptRecords = adoptRecords.stream().map(adoptRecord -> {
            try {
                return adoptRecord.buildESAdoptRecord();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        adoptRecordRepository.saveAll(esAdoptRecords);
    }

    @Test
    public void matchQuery() {
        int page = 0;
        int size = 5;
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "哈士奇"));
        queryBuilder.withPageable(PageRequest.of(page, size));
        Page<ESAdoptRecord> esAdoptRecordPage = adoptRecordRepository.search(queryBuilder.build());
        for (ESAdoptRecord esAdoptRecord : esAdoptRecordPage) {
            System.out.println(esAdoptRecord);
        }
        System.out.println(esAdoptRecordPage.getTotalElements());
    }

    @Test
    public void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("xk_index");
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.index());
    }

    @Test
    public void deleteIndex() {
        elasticsearchRestTemplate.deleteIndex(ESPet.class);
        elasticsearchRestTemplate.deleteIndex(ESAdoptRecord.class);
        // 根据索引名字删除
        //esTemplate.deleteIndex("item1");
    }

    /**
     * 新增一个对象
     */
    @Test
    public void index() {
        ESPet esPet = new ESPet(1, "二毛", "哈士奇犬", "幸福小区", "http://image.baidu.com/13123.jpg");
        petRepository.save(esPet);
    }

    /**
     * 批量新增
     */
    @Test
    public void indexList() {
        List<Pet> pets = petService.list();
        List<ESPet> esPets = pets.stream().map(pet -> {
            try {
                return pet.buildESPet();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        ESPet esPet1 = new ESPet(2, "大毛", "金毛犬", "温馨家园小区", "http://image.baidu.com/13123.jpg");
        ESPet esPet2 = new ESPet(3, "小基", "柯基犬", "和谐小区", "http://image.baidu.com/13123.jpg");
        esPets.add(esPet1);
        esPets.add(esPet2);
        petRepository.saveAll(esPets);
    }

    /**
     * 修改，先删除再新增
     */
    @Test
    public void update() {
        ESPet esPet1 = new ESPet(1, "二哈", "金毛犬", "温馨家园小区", "http://image.baidu.com/13123.jpg");
        petRepository.save(esPet1);
    }

    /**
     * 查询
     */
    @Test
    public void query() {
        // 查找所有
        Iterable<ESPet> all = petRepository.findAll();
        for (ESPet esPet : all) {
            System.out.println(esPet);
        }
        // 分页查找
        Page<ESPet> all1 = petRepository.findAll(PageRequest.of(1, 5));
        for (ESPet esPet : all1) {
            System.out.println(esPet);
        }
        //// 排序
        //Iterable<ESPet> all2 = petRepository.findAll(Sort.by("giveTime").descending());
        //for (ESPet esPet : all2) {
        //    System.out.println(esPet);
        //}
    }

    /**
     * 自定义查询
     */
    @Test
    public void search() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("breed", "猫"));
        Page<ESPet> search = petRepository.search(queryBuilder.build());
        long totalElements = search.getTotalElements();
        System.out.println("total： " + totalElements);
        for (ESPet esPet : search) {
            System.out.println(esPet);
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void testTermQuery() {
        // 查询条件生成器
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.termQuery("breed", "狗"));
        Page<ESPet> search = petRepository.search(builder.build());
        for (ESPet esPet : search) {
            System.out.println(esPet);
        }
    }

    /**
     * 模糊查询
     */
    @Test
    public void testFuzzyQuery() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.fuzzyQuery("breed", "犬"));
        Page<ESPet> search = petRepository.search(nativeSearchQueryBuilder.build());
        for (ESPet esPet : search) {
            System.out.println(esPet);
        }
    }

    /**
     * 组合查询
     */
    @Test
    public void testBooleanQuery() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("address", "小区"))
                .must(QueryBuilders.termQuery("breed", "金毛犬")));
        Page<ESPet> search = petRepository.search(nativeSearchQueryBuilder.build());
        for (ESPet esPet : search) {
            System.out.println(esPet);
        }
    }

    /**
     * 范围查询
     */
    @Test
    public void testRangeQuery() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.rangeQuery("id").from(1).to(3));
        Page<ESPet> search = petRepository.search(nativeSearchQueryBuilder.build());
        for (ESPet esPet : search) {
            System.out.println(esPet);
        }
    }

    /**
     * 排序
     */
    @Test
    public void searchAndSort() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.fuzzyQuery("breed", "犬"));
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.ASC));
        Page<ESPet> search = petRepository.search(nativeSearchQueryBuilder.build());
        long totalElements = search.getTotalElements();
        System.out.println(totalElements);
        for (ESPet esPet : search) {
            System.out.println(esPet);
        }
    }
}
