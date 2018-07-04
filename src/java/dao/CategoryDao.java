/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigInteger;
import java.util.List;
import jaxb.category.CategoryItem;


/**
 *
 * @author ntien
 */
public class CategoryDao extends BaseDao<CategoryItem, BigInteger> {
    protected Class entityClass;
    public CategoryDao() {
        this.entityClass = CategoryItem.class;
        setTClass(entityClass);
    }
    
    public List<CategoryItem> saveCategory(List<CategoryItem> list){     
        list.stream().forEach(category->{
             if (validateExist(category.getName())) {
                 insert(category);
                System.out.println(CategoryItem.class.getName()+": "+category.getName()+" inserted");
            }
            else{
                System.out.println(CategoryItem.class.getName()+": "+category.getName()+" is exist!");
            }
        });
        
       return getList();
               
    }
}
