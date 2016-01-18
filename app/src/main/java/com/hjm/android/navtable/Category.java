/*
 * Category.java
 * @include classes:Category;interfaces:Category
 * @version 1.0.0
 * @data 2014-1-15
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.hjm.android.navtable;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author jerry@duotin.com
 * @version 1.0.0
 * @name Category
 * @desc
 * @package com.duotin.fm.model
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 * @history jerrysher 2014-1-15 下午5:13:26
 */
public class Category implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     * id: "73",
title: "幽默风趣",
type: "1"
     */
    private int id;

    private String title;

    private int type;

    private String imageUrl;

    private int dataOrder;

    private boolean isSysCategory;


    private String description;

    // recommend
    public static final int TYPE_ALBUM = 1;

    public static final int TYPE_TRACK = 2;

    public static final int TYPE_TOPIC = 3;

    public static final int TYPE_WEB = 4;

    public static final int TYPE_PODCASTER = 5;

    public static final int TYPE_PODCAST = 6;
    /**
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the programList
     */
    @Override
    public String toString() {
        return "{category_id:" + getId() + ",displayorder:" + getId() + "}";
    }

    /**
     * @return the dataOrder
     */
    public int getDataOrder() {
        return dataOrder;
    }

    /**
     * @param dataOrder the dataOrder to set
     */
    public void setDataOrder(int dataOrder) {
        this.dataOrder = dataOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Category category = (Category) o;

        if (id != category.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
