package com.github.chaossss.ishuhui.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

/**
 * Created by chaossss on 2016/1/15.
 */
public class NormalGridView extends GridView {
    private int columnWidth = 0;
    private int horizontalSpacing = 0;
    private boolean isScroll = false;
    private boolean isAutoSetNumColumns = true;

    public NormalGridView(Context context) {
        super(context);
    }

    public NormalGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NormalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        if(isScroll) {
            int gridViewWidth = 0;
            int adapterCount = adapter.getCount();

            if(columnWidth == 0) {
                for(int i = 0 ; i< adapter.getCount();i++) {
                    View rowView = adapter.getView(i,null,null);
                    measureView(rowView);
                    gridViewWidth += rowView.getMeasuredWidth();
                }
            } else {
                gridViewWidth += columnWidth*adapterCount;
            }

            if(isAutoSetNumColumns) {
                gridViewWidth += (this.getPaddingLeft()+ this.getPaddingRight());
                gridViewWidth += this.horizontalSpacing*(adapterCount-1);
                this.setNumColumns(adapterCount);
            }
            this.setLayoutParams(new LinearLayout.LayoutParams(gridViewWidth,LayoutParams.WRAP_CONTENT));
        }
        super.setAdapter(adapter);
    }


    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0,
                0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    @Override
    public void setNumColumns(int numColumns){
        isAutoSetNumColumns = false;

        super.setNumColumns(numColumns);
    }

    @Override
    public void setHorizontalSpacing(int horizontalSpacing) {

        this.horizontalSpacing = horizontalSpacing;

        super.setHorizontalSpacing(horizontalSpacing);
    }

    @Override
    public void setColumnWidth(int columnWidth) {

        this.columnWidth = columnWidth;

        super.setColumnWidth(columnWidth);
    }

    public void setEnableHorizontalScroll(boolean isScroll) {
        this.isScroll = isScroll;
    }
}
