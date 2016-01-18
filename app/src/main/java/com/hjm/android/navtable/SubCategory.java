package com.hjm.android.navtable;


import java.io.Serializable;

/**
 * Created by qzz on 2014/11/22.
 */
public class SubCategory implements Serializable {

    public CategoryGroup getCategoryGroup() {
        return mCategoryGroup;
    }

    public void setCategoryGroup(CategoryGroup categoryGroup) {
        mCategoryGroup = categoryGroup;
    }

    CategoryGroup mCategoryGroup;
    private static final long serialVersionUID=478324918;
    private long id;
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SubCategory(){}
    public SubCategory(int id, String title){
        this.id = id;
        this.title = title;
    }
}
