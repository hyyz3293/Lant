package com.jack.lant.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * 随着软键盘弹出和收回,自动滑动的ScrollView
 *
 * @author jack
 * @date 2017.07.04
 * @note Activity需要在清单里添加属性windowSoftInputMode
 * ---------------------------------------------------------------------------------------------------------------------
 * @modified -
 * @date -
 * @note -
 */
public class AutoScrollView extends ScrollView {

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public AutoScrollView(Context context) {
        this(context, null);
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     */
    public AutoScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     * @param defStyleAttr 样式
     */
    public AutoScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        // 添加布局改变监听
        addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    smoothScrollTo(0, getHeight());
                }
            }
        });
    }
}
