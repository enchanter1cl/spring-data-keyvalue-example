package com.erato.springdata.keyvalue.example.service;

import com.erato.springdata.keyvalue.example.entity.Category;
import com.erato.springdata.keyvalue.example.vo.CategoryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 商品三级分类(Category)表服务接口
 *
 * @author zhangyuan
 * @since 2023-02-13 17:14:06
 */
public interface CategoryService {
    
    /**
     * 通过ID查询单条数据
     *
     * @param catId 主键
     * @return 实例对象
     */
    Category queryById(Long catId);
    
    /**
     * 分页查询
     *
     * @param category    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Category> queryByPage(Category category, PageRequest pageRequest);
    
    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category insert(Category category);
    
    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category update(Category category);
    
    /**
     * 通过主键删除数据
     *
     * @param catId 主键
     * @return 是否成功
     */
    boolean deleteById(Long catId);
    
    List<CategoryVo> listWithTree();
    
    List<CategoryVo> queryAllFromDb();
    
    List<CategoryVo> getCategoryFromCache();
}
