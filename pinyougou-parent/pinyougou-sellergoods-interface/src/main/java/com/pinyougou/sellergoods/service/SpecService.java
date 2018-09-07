package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojogroup.Specification;
import entity.PageResult;

import java.util.List;

/**
 * Created by MYLOVE on 2018/9/5.
 */
public interface SpecService {

    PageResult findPageSpec(TbSpecification tbSpecification, int page, int rows);

    List<TbSpecification> findAll();

    void add(Specification specification);

    void delete(Long[] ids);

    Specification findOne(Long id);

    void update(Specification specification);
}
