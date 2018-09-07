package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbTypeTemplate;
import entity.PageResult;

import java.util.List;
import java.util.Map;

/**
 * Created by MYLOVE on 2018/9/6.
 */
public interface TypeService {
    PageResult findPage(TbTypeTemplate typeTemplate, int page, int rows);

    List<Map> selectOptionList();

    List<Map> selectSpecList();

    void insertTypeTemplate(TbTypeTemplate typeTemplate);

    void delete(Long[] ids);

    TbTypeTemplate findOne(Long id);

    void update(TbTypeTemplate tbTypeTemplate);
}
