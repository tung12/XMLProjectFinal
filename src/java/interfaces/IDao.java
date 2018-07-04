/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ntien
 */
public interface IDao<T,PK extends Serializable> {
    public void saveCrawlerToDB(List<T> T);   
    public boolean validateExist(String parameter);    
}
