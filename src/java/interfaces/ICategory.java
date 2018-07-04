/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import java.util.Map;
import jaxb.category.CategoryItem;

/**
 *
 * @author ntien
 */
public interface ICategory {       
	public List<CategoryItem> parseDocumentCategoryByStAX(String document);
        public String getDocumentCategory(String url);
}
