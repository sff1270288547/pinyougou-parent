package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.TypeService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by MYLOVE on 2018/9/6.
 */
@RestController
@RequestMapping("type")
public class TypeController {
    @Reference
    private TypeService typeService;

    @RequestMapping("/search")
    public PageResult search(@RequestBody TbTypeTemplate typeTemplate, int page, int rows){
        return typeService.findPage(typeTemplate, page, rows);
    }
    //下拉列表-->
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return typeService.selectOptionList();
    }

    @RequestMapping("/selectSpecList")
    public List<Map> selectSpecList(){
        return typeService.selectSpecList();
    }
    /**
     * 新增分类模版
     * @param typeTemplate
     * @return
     */
    @RequestMapping("insertTypeTemplate")
    public Result insertTypeTemplate(@RequestBody TbTypeTemplate typeTemplate){
        try {
            typeService.insertTypeTemplate(typeTemplate);
            return new Result(true,"新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"新增失败！");
        }
    }
    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            typeService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
    @RequestMapping("/findOne")
    public TbTypeTemplate findOne(Long id){
        return typeService.findOne(id);
    }

    /**
     * 修改
     * @param specification
     * @return
     */
    @RequestMapping("/updateTypeTemplate")
    public Result update(@RequestBody TbTypeTemplate tbTypeTemplate){
        try {
            typeService.update(tbTypeTemplate);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }
}

