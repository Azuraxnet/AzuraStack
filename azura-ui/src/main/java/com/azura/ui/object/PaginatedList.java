package com.azura.ui.object;

import com.azura.ui.icon.Icon;

import java.util.ArrayList;
import java.util.List;

public class PaginatedList extends ListWrapper<Icon> {
    private int itemsPerPage;
    private int page = 0;
    public PaginatedList(int itemsPerpage){
        this.itemsPerPage = itemsPerpage;
    }
    public void updateItemsPerPage(int itemsPerPage){
        this.itemsPerPage = itemsPerPage;
        this.page = 0;
    }

    public int getMaxPages(){
        int pages = size() / itemsPerPage;
        int leftOver = size() % itemsPerPage;
        if(leftOver > 0){
            return pages+1;
        }
        if(pages == 0){
            return pages+1;
        }
        return pages;
    }
    public List<Icon> getPage(){
        int startIndex = page * itemsPerPage;
        int endIndex = (page * itemsPerPage) + itemsPerPage;
        List<Icon> icons = new ArrayList<>();
        int index = 0;
        for(int i = startIndex; i < endIndex; i++){
            if(i >= size()) break;
            icons.add(index, get(i));
            index++;
        }
        return icons;
    }
    public int getPageIndex(){
        return page +1;
    }
    public void nextPage(){
        if(getPageIndex() == getMaxPages()){
            page = 0;
        }else{
            page++;
        }
    }
    public void previousPage(){
        if(getPageIndex() == 1){
            page = getMaxPages()-1;
        }else{
            page--;
        }
    }



    public int getItemsPerPage() {
        return itemsPerPage;
    }
}