package com.jack.lant.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.jack.lant.R;


/**
 * 扩展EditText
 *
 * @author wuao
 * @date 2017.07.04
 * @note 扩展功能
 * 1. 右侧清除按钮 app:drawableClear
 * 2. 右侧明文密文切换按钮 app:drawableCiphertext app:drawablePlaintext
 * 3. 晃动动画 app:showShakeAnimation
 * 4. 提示文字尺寸 app:textSizeHint
 * ---------------------------------------------------------------------------------------------------------------------
 * @modified -
 * @date -
 * @note -
 */
public class ExtendEditText extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {
    /** 隐藏清除按钮常量 */
    public final static int INVISIBLE_CLEAR_BUTTON = 0;
    /** 显示清除按钮 */
    private static final int TAG_CLEAR = 1;
    /** 显示明文密文切换按钮 */
    private static final int TAG_SWITCH = 2;
    /** 提示文字为空时候的尺寸 */
    private final int TEXT_SIZE_HINT_EMPTY = 0;
    /** 清除图标 */
    private Drawable mDrawableClear;
    /** 密文图标 */
    private Drawable mDrawableCiphertext;
    /** 明文图标 */
    private Drawable mDrawablePlaintext;
    /** 标记(1:只显示清除按钮, 2:只显示明文密文切换按钮) */
    private int mTag = 0;
    /** 是否是密文 */
    private boolean mIsCiphertext = true;
    /** 是否有焦点 */
    private boolean mHasFoucs;
    /** 是否图标可见 */
    private boolean mIsVisible;
    /** 是否显示晃动动画(默认true) */
    private boolean mShowShakeAnimation;
    /** 晃动动画 */
    private Animation mShakeAnimation;
    /** 提示文字尺寸 */
    private int mTextSizeHint;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public ExtendEditText(Context context) {
        this(context, null);
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     */
    public ExtendEditText(Context context, AttributeSet attrs) {
        // 不加第三个属性不能在xml里面定义
        this(context, attrs, android.R.attr.editTextStyle);
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     * @param defStyleAttr 样式
     */
    public ExtendEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }

    /**
     * 初始化布局
     *
     * @param context 上下文
     * @param attrs 属性
     */
    private void initView(Context context, AttributeSet attrs) {
        // 获取xml里面定义的资源
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExtendEditText);
        mDrawableClear = typedArray.getDrawable(R.styleable.ExtendEditText_drawableClear);
        mDrawableCiphertext = typedArray.getDrawable(R.styleable.ExtendEditText_drawableCiphertext);
        mDrawablePlaintext = typedArray.getDrawable(R.styleable.ExtendEditText_drawablePlaintext);
        mShowShakeAnimation = typedArray.getBoolean(R.styleable.ExtendEditText_showShakeAnimation, true);
        mTextSizeHint = typedArray.getDimensionPixelOffset(R.styleable.ExtendEditText_textSizeHint, TEXT_SIZE_HINT_EMPTY);

        // 识别扩展类型与抛出异常
        if (mDrawableClear != null) {
            if (mDrawableCiphertext == null && mDrawablePlaintext == null) {
                mTag = TAG_CLEAR;
            } else if (mDrawableCiphertext == null && mDrawablePlaintext != null) {
                throw new RuntimeException("please don't put the \"drawableClean\" and \"drawablePlaintext\" attributes together in xml");
            } else if (mDrawableCiphertext != null && mDrawablePlaintext == null) {
                throw new RuntimeException("please don't put the \"drawableClean\" and \"drawableCiphertext\" attributes together in xml");
            } else {
                throw new RuntimeException("please don't put the \"drawableClean\" and \"drawableCiphertext\" and \"drawablePlaintext\" attributes together "
                        + "in xml");
            }
        } else {
            if (mDrawableCiphertext != null && mDrawablePlaintext != null) {
                mTag = TAG_SWITCH;
            } else if (mDrawableCiphertext != null && mDrawablePlaintext == null) {
                throw new RuntimeException("You might forget to add the \"drawablePlaintext\" attribute in xml");
            } else if (mDrawableCiphertext == null && mDrawablePlaintext != null) {
                throw new RuntimeException("You might forget to add the \"drawableCiphertext\" attribute in xml");
            }
        }

        // 设置提示文字尺寸
        if (mTextSizeHint != TEXT_SIZE_HINT_EMPTY) {
            String hint = getHint().toString();
            SpannableString spannableString = new SpannableString(hint);
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(mTextSizeHint, false);
            spannableString.setSpan(absoluteSizeSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setHint(new SpannedString(spannableString));
        }

        // 初始化晃动动画
        initShakeAnimation();
        // 设置边界
        setBounds();
        // 默认不显示图标
        setDrawableVisible(false);
        // 设置焦点改变的监听
        setOnFocusChangeListener(this);
        // 设置输入框内容发生改变的监听
        addTextChangedListener(this);
    }

    /**
     * 初始化晃动动画
     */
    private void initShakeAnimation() {
        mShakeAnimation = new TranslateAnimation(0, 10, 0, 0);
        // 每秒晃动次数
        mShakeAnimation.setInterpolator(new CycleInterpolator(5));
        mShakeAnimation.setDuration(300);
    }

    /**
     * 设置边界
     *
     * @note setCompoundDrawables之前必须调用setBounds方法, 否则不会显示
     */
    private void setBounds() {
        if (mTag == TAG_CLEAR) {
            if (mDrawableClear != null) {
                mDrawableClear.setBounds(0+30, 0, mDrawableClear.getMinimumWidth()+30, mDrawableClear.getMinimumHeight());
            }
        } else if (mTag == TAG_SWITCH) {
            if (mDrawableCiphertext != null) {
                mDrawableCiphertext.setBounds(0, 0, mDrawableCiphertext.getMinimumWidth(), mDrawableCiphertext.getMinimumHeight());
            }
            if (mDrawablePlaintext != null) {
                mDrawablePlaintext.setBounds(0, 0, mDrawablePlaintext.getMinimumWidth(), mDrawablePlaintext.getMinimumHeight());
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            /*
             * 1. 图标左侧位置
             * 2. 图标右侧位置
             * 3. 当前触摸位置
             *
             * @note 没有考虑竖直方向
             */
            float xIconLeft = getWidth() - getTotalPaddingRight();
            float xIconRight = getWidth() - getPaddingRight();
            float xTouch = event.getX();
            if (xTouch > xIconLeft && xTouch < xIconRight) {
                // 点击了图标
                if (mTag == TAG_CLEAR) {
                    setText("");
                } else if (mTag == TAG_SWITCH) {
                    if (mIsCiphertext) {
                        mIsCiphertext = !mIsCiphertext;
                        // 显示明文
                        setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        postInvalidate();
                        CharSequence charSequence = getText();
                        // 为了保证体验效果，需要保持输入焦点在文本最后一位
                        if (charSequence != null) {
                            Spannable spanText = (Spannable) charSequence;
                            Selection.setSelection(spanText, charSequence.length());
                        }
                    } else {
                        mIsCiphertext = !mIsCiphertext;
                        // 显示密文
                        setTransformationMethod(PasswordTransformationMethod.getInstance());
                        postInvalidate();
                        setSelection(getText().length());
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.d("hasFocus:", hasFocus +"");
        mHasFoucs = hasFocus;
        if (hasFocus) {
            setDrawableVisible(getText().length() > 0);
        } else {
            setDrawableVisible(false);
            setShakeAnimation(getText().length() == 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (true) {
            setDrawableVisible(s.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (sIOnTextChange != null) {
            sIOnTextChange.onTextChange();
        }
    }

    /**
     * 设置图标的显示与隐藏
     *
     * @param isVisible 是否显示
     */
    public void setDrawableVisible(boolean isVisible) {
        mIsVisible = isVisible;
        Drawable drawable = null;
        if (isVisible) {
            if (mTag == TAG_CLEAR) {
                drawable = mDrawableClear;
            } else if (mTag == TAG_SWITCH) {
                if (mIsCiphertext) {
                    drawable = mDrawableCiphertext;
                } else {
                    drawable = mDrawablePlaintext;
                }
            }
        }
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawables(drawables[0], drawables[1], drawable, drawables[3]);
    }

    /**
     * 设置清除图标
     *
     * @param clearId 图标id
     * @return this
     * @note 如果传入0表示去掉图标
     */
    public ExtendEditText setDrawableClear(@DrawableRes int clearId) {
        if (INVISIBLE_CLEAR_BUTTON == clearId) {
            mDrawableClear = null;
        } else {
            mDrawableClear = ContextCompat.getDrawable(getContext(), clearId);
        }

        setBounds();
        setDrawableVisible(mIsVisible);

        return this;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if (!enabled) {
            mDrawableClear = null;
            mDrawablePlaintext = null;
            mDrawableCiphertext = null;
        }

        setBounds();
        setDrawableVisible(mIsVisible);
    }

    /**
     * 设置晃动动画
     *
     * @param showAnima 是否显示动画
     */
    public void setShakeAnimation(boolean showAnima) {
        if (mShowShakeAnimation) {
            if (showAnima) {
                startAnimation(mShakeAnimation);
            }
        }
    }

    public void setOnTextChange(IOnTextChange onTextChangen) {
        sIOnTextChange = onTextChangen;
    }

    private IOnTextChange sIOnTextChange;

    /**
     * 结果接口
     */
    public interface IOnTextChange {
        void onTextChange();
    }
}
