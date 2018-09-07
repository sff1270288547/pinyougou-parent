package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.SpecService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by MYLOVE on 2018/9/5.
 */
@Service
public class SpecServiceImpl implements SpecService{
    @Autowired
    private TbSpecificationMapper specMapper;
    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;
    @Override
    public PageResult findPageSpec(TbSpecification tbSpecification, int page, int rows) {
        PageHelper.startPage(page, rows);
        TbSpecificationExample example = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();
        if(tbSpecification!=null){
            if(tbSpecification.getSpecName()!=null && tbSpecification.getSpecName().length()>0){
                criteria.andSpecNameLike("%"+tbSpecification.getSpecName()+"%");
            }
        }
        Page<TbSpecification> p= (Page<TbSpecification>)specMapper.selectByExample(example);
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public List<TbSpecification> findAll() {
        return specMapper.selectByExample(null);
    }

    @Override
    public void add(Specification specification) {
        specMapper.insert(specification.getSpecification());//插入规格
        //循环插入规格选项
        for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){
            specificationOption.setSpecId(specification.getSpecification().getId());//设置规格ID
            specificationOptionMapper.insert(specificationOption);
        }
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            specMapper.deleteByPrimaryKey(id);
            //删除原有的规格选项
            TbSpecificationOptionExample example=new TbSpecificationOptionExample();
            com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);//指定规格ID为条件
            specificationOptionMapper.deleteByExample(example);//删除
        }
    }

    @Override
    public Specification findOne(Long id) {
        //查询规格
        TbSpecification tbSpecification = specMapper.selectByPrimaryKey(id);
        //查询规格选项列表
        TbSpecificationOptionExample example=new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);//根据规格ID查询
        List<TbSpecificationOption> optionList = specificationOptionMapper.selectByExample(example);
        //构建组合实体类返回结果
        Specification spec=new Specification();
        spec.setSpecification(tbSpecification);
        spec.setSpecificationOptionList(optionList);
        return spec;
    }

    @Override
    public void update(Specification specification) {


        //保存修改的规格
        specMapper.updateByPrimaryKey(specification.getSpecification());//保存规格
        //删除原有的规格选项
        TbSpecificationOptionExample example=new TbSpecificationOptionExample();
        com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());//指定规格ID为条件
        specificationOptionMapper.deleteByExample(example);//删除
        //循环插入规格选项
        for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){
            specificationOption.setSpecId(specification.getSpecification().getId());
            specificationOptionMapper.insert(specificationOption);
        }
    }



}
