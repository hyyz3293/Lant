package com.jack.lant.ui.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 手机号扩展EditText
 *
 * @author wuao
 * @date 2018.02.08
 * @note -
 * ---------------------------------------------------------------------------------------------------------------------
 * @modified -
 * @date -
 * @note -
 */
public class PhoneExtendEditText extends ExtendEditText {
    /** 下一个获取焦点 */
    private EditText mEtNextGetFocus;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public PhoneExtendEditText(Context context) {
        this(context, null);
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     */
    public PhoneExtendEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     * @param defStyleAttr 样式
     */
    public PhoneExtendEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString().trim();
                if (!TextUtils.isEmpty(input) && input.length() == 11) {
                    if (mEtNextGetFocus != null) {
                        mEtNextGetFocus.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 设置下一个获取焦点的EditText
     *
     * @param nextGetFocusEditText 下一个获取焦点的EditText
     */
    public void setNextGetFocusEditText(EditText nextGetFocusEditText) {
        mEtNextGetFocus = nextGetFocusEditText;
    }
}
