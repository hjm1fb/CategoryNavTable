package com.hjm.android.navtable;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoryGroupView categoryGroupView = (CategoryGroupView) findViewById(R.id.categoryGroupView);
        CategoryGroup categoryGroup = new CategoryGroup();
        Category category = new Category();
        category.setTitle("音乐");
        List<SubCategory> subCategories = new ArrayList<>();
        for(int i = 0;i<8;i++){
            SubCategory subCategory = new SubCategory();
            subCategory.setTitle("分类"+i);
            subCategories.add(subCategory);
            categoryGroup.setCategory(category);
            categoryGroup.setSubCategories(subCategories);
        }
       categoryGroupView.setContent(this,categoryGroup);
    }

}
