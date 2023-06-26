package com.example.ebookshare.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.entity.Products;
import com.example.ebookshare.mapper.ProductsMapper;
import com.example.ebookshare.service.IProductcategoriesService;
import com.example.ebookshare.service.IProductsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhc
 * @since 2023-06-26
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Resource
    private IProductsService productsService;

    @Resource
    private ProductsMapper productsMapper;

    private IProductcategoriesService productcategoriesService;

    //新增和修改
    @PostMapping
    public boolean save(@RequestBody Products products){//@RequestBody将前台josn对象转换为后台的java对象
        //新增或者更新
        return productsService.saveOrUpdate(products);
    }

    @PostMapping("/update")
    public Result save2(@RequestBody Products products){
        //更新
        return Result.success(productsMapper.updateById(products));
    }

    //删除指定deptid的数据
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){  //@PathVariable这个注解表示上面url这个参数，上面花括号里面的id和这个参数id是一一对应的
        return productsService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return productsService.removeByIds(ids);
    }

    //查询所有数据
    @GetMapping
    public List<Products> findAll(){
        //List<Department> all=departmentMapper.findAll();
        return productsService.list();
    }

    @GetMapping("/{id}")
    public Products findeOne(@PathVariable Integer id){
        return productsService.getById(id);
    }

    //分页查询-mybatis-plus的方式进行
    @GetMapping("/page")
    public Page<Products> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam (defaultValue = "")String name){

        Page<Products> page=productsService.findPage(new Page<>(pageNum,pageSize),name);

        return page;
    }

}
