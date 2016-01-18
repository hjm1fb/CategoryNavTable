package com.hjm.android.navtable;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by hongjunmin on 15/6/4
 * @author hongjunmin
 * @modify by hf on 15/7/9， 改为继承RelativeLayout减少布局层次
 */
public class CategoryGroupView extends RelativeLayout implements View.OnClickListener {
    public static final int PARENT_PADDING_IN_DP = 15;//组件在其parent上的padding值
    public static final int COLUM_SUM = 4;//显示4列

    private static final int ITEM_MARGIN_IN_DP = 4;//每个item之间的间距
    private static final int ITEM_HEIGHT_IN_DP = 40;//单行item高度
    private static final int ITEM_COLOR = Color.parseColor("#19000000");//item背景，黑色透明度5%

    private int itemMargin;
    private int mItemHeight;
    private int mItemWidth;
    private CategoryGroup categoryGroup;

    public CategoryGroupView(Context context) {
        super(context);
    }

    public CategoryGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CategoryGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        if (null != categoryGroup && null != v.getTag()) {
            int idx = (Integer) v.getTag();
           //todo
        }
    }

    public void setContent(Activity activity, CategoryGroup categoryGroup){
        removeAllViews();
        this.categoryGroup = categoryGroup;

        itemMargin = Tool.dp2px(getContext(), ITEM_MARGIN_IN_DP);
        mItemHeight = Tool.dp2px(getContext(), ITEM_HEIGHT_IN_DP);
        //根据Activity的实际宽度，计算出单个item的布局宽度
        mItemWidth = (Tool.getScreenWidth(activity) - Tool.dp2px(getContext(), 2 * PARENT_PADDING_IN_DP + 3 * ITEM_MARGIN_IN_DP)) / COLUM_SUM;

        //添加占位1x2格的item，显示categoryGroup父分类
        View itemMain = createCategoryItem(categoryGroup);
        itemMain.setTag(CategoryGroup.SELECT_ALL);
        itemMain.setOnClickListener(this);
        this.addView(itemMain, new LayoutParams(mItemWidth, mItemHeight * 2 + itemMargin));

        //轮循遍历显示categoryGroup父分类所有子分类，按照1x1格占位进行填充显示
        if (null != categoryGroup.getSubCategories() && !categoryGroup.getSubCategories().isEmpty()) {
            int cnt = categoryGroup.getSubCategories().size();
            if (cnt < 6) {
                //不够6格时强行设置未6格，占位3x2
                cnt = 6;
            } else if ((cnt - 6) % 4 > 0) {
                /*//超过6格，不够一行时，补齐不足格格子,效果是出现空白的格子
                cnt = ((cnt - 6) / 4 + 1) * 4 + 6;*/
            }
            View itemSub;
            for(int i=0; i<cnt; i++){
                if (i < categoryGroup.getSubCategories().size()) {
                    itemSub = createSubCategoryItem(categoryGroup.getSubCategories().get(i));
                    itemSub.setTag(i);
                    itemSub.setOnClickListener(this);
                } else {
                    itemSub = createSubCategoryItem(null);
                }
                this.addView(itemSub, generateItemLayoutParams(i));
            }
        } else {//一个栏目都没有的时候，补齐2x3格子
            for (int i = 0; i < 6; i++) {
                this.addView(createSubCategoryItem(null), generateItemLayoutParams(i));
            }
        }
    }

    //创建一个显示父分类的item组件
    private LinearLayout createCategoryItem(CategoryGroup categoryGroup) {
        LinearLayout layout = new LinearLayout(getContext());
        int padding = Tool.dp2px(getContext(), 6);
        layout.setPadding(padding, padding, padding, padding);
//        layout.setBackgroundColor(getResources().getColor(R.color.darker_grey));
        layout.setBackgroundColor(ITEM_COLOR);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(getContext());
        layout.addView(imageView,
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 3)
        );
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//        headImage.setPadding(0, HEAD_TOP_PADDING_IN_DP, 0, 0);
       //todo 按自己喜欢的方式加载图片 BitmapManager.LoadConfig mLoadConfig = new BitmapManager.LoadConfig(R.drawable.ic_hot_default) mLoadConfig.width = CarLocalConstants.IMG_SIZE_SMALLEST BitmapManager.loadBitmap(categoryGroup.getCategory().getImageUrl(), imageView, mLoadConfig);

        TextView textView = new TextView(getContext());
        layout.addView(textView,
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 0, 2)
        );
        textView.setGravity(Gravity.TOP);
        textView.setTextSize(14);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
//        textView.setTextColor(getResources().getColor(R.color.gray_cc));
        textView.setTextColor(Color.parseColor("#999999"));
        textView.setText(categoryGroup.getCategory().getTitle());

        return layout;
    }

    //创建一个显示子分类的item组件
    private TextView createSubCategoryItem(SubCategory subCategory) {
        TextView textView = new TextView(getContext());
//        textView.setBackgroundColor(getResources().getColor(R.color.darker_grey));
        textView.setBackgroundColor(ITEM_COLOR);
        textView.setTextColor(Color.WHITE);
//        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(12);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
        if(null!=subCategory){
            textView.setText(subCategory.getTitle());
            textView.setTag(subCategory);
        }
        return textView;
    }

    //按照item的序号设置不同的布局属性
    private LayoutParams generateItemLayoutParams(int idx){
        LayoutParams layoutParams = new LayoutParams(mItemWidth, mItemHeight);
        if (idx < 6) {//前6个子分类，按2x3的占位放置在父分类item的右侧
            layoutParams.leftMargin = (idx % 3 + 1) * (mItemWidth + itemMargin);
            layoutParams.topMargin = idx / 3 * (mItemHeight + itemMargin);
        } else {//超过6个子分类后，在父分类下方，按每行4列进行填充
            layoutParams.leftMargin = (idx - 6) % 4 * (mItemWidth + itemMargin);
            layoutParams.topMargin = (2 + (idx - 6) / 4) * (mItemHeight + itemMargin);
        }
        return layoutParams;
    }
}