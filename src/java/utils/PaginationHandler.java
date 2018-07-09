/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;

/**
 *
 * @author ntien
 */
public class PaginationHandler<T> {

    private int totalItems;
    private int currentPage;
    private int perOfPage;
    private int totalPage;
    private int maxNavigationPage;

    private List<T> listItems;
    private List<Integer> navigationPages;

    public PaginationHandler(Query query,int page,int perOfPage, int maxNavigationPage) {
    final int pageIndex = page - 1 < 0 ? 0 : page - 1;
 
        int fromRecordIndex = pageIndex * perOfPage;
        int maxRecordIndex = fromRecordIndex + perOfPage;
 
        ScrollableResults resultScroll = query.scroll(ScrollMode.SCROLL_INSENSITIVE );
 
        List<T> results = new ArrayList<T>();
 
        boolean hasResult =   resultScroll.first();
 
        if (hasResult) {                   
             hasResult = resultScroll.scroll(fromRecordIndex); 
            if (hasResult) {
                do {
                    T item = (T) resultScroll.get(0);
                    results.add(item);
                } while (resultScroll.next()//
                        && resultScroll.getRowNumber() >= fromRecordIndex
                        && resultScroll.getRowNumber() < maxRecordIndex);
 
            }
  
            // Go to Last record.
             resultScroll.last();
        }
  
        // Total Records
        this.totalItems = resultScroll.getRowNumber() + 1;
        this.currentPage = pageIndex + 1;
        this.listItems = results;
        this.perOfPage = perOfPage;
 
        if (this.totalItems % this.perOfPage == 0) {
            this.totalPage = this.totalItems / this.perOfPage;
        } else {
            this.totalPage = (this.totalItems / this.perOfPage) + 1;
        }
 
        this.maxNavigationPage = maxNavigationPage;
 
        if (maxNavigationPage < totalPage) {
            this.maxNavigationPage = maxNavigationPage;
        }
        resultScroll.close();
 
        this.calcNavigationPages();
    }
 
    private void calcNavigationPages() {
 
        this.navigationPages = new ArrayList<Integer>();
 
        int current = this.currentPage > this.totalPage ? this.totalPage : this.currentPage;
 
        int begin = current - this.maxNavigationPage / 2;
        int end = current + this.maxNavigationPage / 2;
  
        // The first page
        navigationPages.add(1);
        if (begin > 2) {                
            navigationPages.add(-1);
        }
 
        for (int i = begin; i < end; i++) {
            if (i > 1 && i < this.totalPage) {
                navigationPages.add(i);
            }
        }
        if (end < this.totalPage - 2) {
            navigationPages.add(-1);
        }      
        navigationPages.add(this.totalPage);
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPerOfPage() {
        return perOfPage;
    }

    public void setPerOfPage(int perOfPage) {
        this.perOfPage = perOfPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getMaxNavigationPage() {
        return maxNavigationPage;
    }

    public void setMaxNavigationPage(int maxNavigationPage) {
        this.maxNavigationPage = maxNavigationPage;
    }

    public List<T> getListItems() {
        return listItems;
    }

    public void setListItems(List<T> listItems) {
        this.listItems = listItems;
    }

    public List<Integer> getNavigationPages() {
        return navigationPages;
    }

    public void setNavigationPages(List<Integer> navigationPages) {
        this.navigationPages = navigationPages;
    }
     
        
    

    
}
