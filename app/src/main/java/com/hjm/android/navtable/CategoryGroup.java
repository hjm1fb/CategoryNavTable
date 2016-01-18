package com.hjm.android.navtable;


import java.io.Serializable;
import java.util.List;

/**
 * * Created by hongjunmin on 15/10/31
 *
 * @author hongjunmin
 */
public class CategoryGroup implements Serializable {

    private static final long serialVersionUID = 47832438;
    public static final String SELECTED_SUBCATEGORY_INDEX = "selectedSubcategoryIndex";
    public static final int SELECT_ALL = -1;

    private Category category;

    List<SubCategory> subCategories;

    public int getTrackType() {
        return trackType;
    }

    public void setTrackType(int trackType) {
        this.trackType = trackType;
    }

    private int trackType;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
        if(subCategories != null){
            for(SubCategory subCategory : subCategories){
                subCategory.setCategoryGroup(this);
            }
        }
    }
}
