package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.SpecService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by MYLOVE on 2018/9/5.
 */
@RestController
@RequestMapping("/spec")
public class SpecController {
    @Reference
    private SpecService specService;

    @RequestMapping("/findAll")
    public List<TbSpecification> findAll() {

        return specService.findAll();
    }
    @RequestMapping("/searchSpecification")
    public PageResult searchSpecification(@RequestBody TbSpecification tbSpecification, int page, int rows){
        return specService.findPageSpec(tbSpecification, page, rows);
    }

    /**
     * 增加
     * @param specification
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Specification specification){
        try {
            specService.add(specification);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            specService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }


    @RequestMapping("/findOne")
    public Specification findOne(Long id){
        return specService.findOne(id);
    }
    /**
     * 修改
     * @param specification
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Specification specification){
        try {
            specService.update(specification);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }
}
