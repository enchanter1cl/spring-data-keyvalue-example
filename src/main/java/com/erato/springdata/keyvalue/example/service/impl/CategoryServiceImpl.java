package com.erato.springdata.keyvalue.example.service.impl;

import com.erato.springdata.keyvalue.example.entity.Category;
import com.erato.springdata.keyvalue.example.dao.CategoryDao;
import com.erato.springdata.keyvalue.example.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

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
    public List<Category> queryAll(){
        return categoryDao.queryAll();
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
    
    @Override
    public List<Category> listWithTree() {
        return null;
    }
}
