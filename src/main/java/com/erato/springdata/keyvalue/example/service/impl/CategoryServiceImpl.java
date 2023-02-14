package com.erato.springdata.keyvalue.example.service.impl;

import com.erato.springdata.keyvalue.example.entity.Category;
import com.erato.springdata.keyvalue.example.dao.CategoryDao;
import com.erato.springdata.keyvalue.example.service.CategoryService;
import com.erato.springdata.keyvalue.example.vo.CategoryVo;
import org.apache.ibatis.javassist.compiler.ast.Variable;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品三级分类(Category)表服务实现类
 *
 * @author zhangyuan
 * @since 2023-02-13 17:14:06
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;
    
    /**
     * Query all the categories
     * @return list of  categories
     */
    @Override
    public List<CategoryVo> queryAll(){
        // Query all the categories
        List<Category> categories = categoryDao.queryAll();
        List<CategoryVo> categoryVos = categories.stream().map(category -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            return categoryVo;
        }).collect(Collectors.toList());
        return categoryVos;
    }
    
    public List<CategoryVo> listWithTree() {
        //1. Query all the categories
        List<CategoryVo> categoryVos = this.queryAll();
        //2. Assemble into a tree structure
        //2.1 Find all level1 categories
        List<CategoryVo> level1Cats = categoryVos.stream().filter(category -> category.getParentCid() == 0
        ).map(categoryVo -> {
            //2.2 Stuff children
            categoryVo.setChildren(getChildren(categoryVo, categoryVos));
            return categoryVo;
        }).sorted(Comparator.comparingInt(catVo -> catVo.getSort() == null ? 0 : catVo.getSort())
        ).collect(Collectors.toList());
        return level1Cats;
    }
    
    private List<CategoryVo> getChildren(CategoryVo root, List<CategoryVo> all) {
        List<CategoryVo> children = all.stream().filter(category -> category.getParentCid() == root.getCatId()
        ).map(category -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            categoryVo.setChildren(getChildren(categoryVo, all));
            return categoryVo;
        }).sorted(Comparator.comparingInt(CategoryVo -> (CategoryVo.getSort() == null ? 0 : CategoryVo.getSort()))
        ).collect(Collectors.toList());
        return children;
    }
    
    /**
     * 通过ID查询单条数据
     *
     * @param catId 主键
     * @return 实例对象
     */
    @Override
    public Category queryById(Long catId) {
        return this.categoryDao.queryById(catId);
    }
    
    /**
     * 分页查询
     *
     * @param category    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Category> queryByPage(Category category, PageRequest pageRequest) {
        long total = this.categoryDao.count(category);
        return new PageImpl<>(this.categoryDao.queryAllByLimit(category, pageRequest), pageRequest, total);
    }
    
    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category insert(Category category) {
        this.categoryDao.insert(category);
        return category;
    }
    
    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category update(Category category) {
        this.categoryDao.update(category);
        return this.queryById(category.getCatId());
    }
    
    /**
     * 通过主键删除数据
     *
     * @param catId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long catId) {
        return this.categoryDao.deleteById(catId) > 0;
    }
}
