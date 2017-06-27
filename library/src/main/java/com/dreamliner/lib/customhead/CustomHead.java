package com.dreamliner.lib.customhead;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author chenzj
 * @Title: CustomHead
 * @Description: 类的描述 - 自定义的头部
 * @date 2017/6/27 20:52
 * @email admin@chenzhongjin.cn
 */
public class CustomHead extends RelativeLayout implements View.OnClickListener {

    private RelativeLayout mParentRl;
    private TextView mLeftTv;
    private ImageView mLeftIv;
    private TextView mTitleTv;
    private TextView mSubTitleTv;
    private TextView mRightTv;
    private ImageView mRightIv;
    private View mLineView;

    private int mBackGroundColorRes;
    private String mLeftStr = "";
    private int mLeftRes;
    private String mTitleStr = "";
    private String mSubTitleStr = "";
    private String mRightStr = "";
    private int mRightRes;
    private boolean isShowLine;

    private ToolBarListener mToolBarListener;

    public CustomHead(Context context) {
        this(context, null);
    }

    public CustomHead(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.CustomHead);
        try {
            mBackGroundColorRes = attributes.getResourceId(R.styleable.CustomHead_background_color, 0);
            mLeftStr = attributes.getString(R.styleable.CustomHead_left_text);
            mLeftRes = attributes.getResourceId(R.styleable.CustomHead_left_res, 0);
            mTitleStr = attributes.getString(R.styleable.CustomHead_title_text);
            mSubTitleStr = attributes.getString(R.styleable.CustomHead_sub_title_text);
            mRightStr = attributes.getString(R.styleable.CustomHead_right_text);
            mRightRes = attributes.getResourceId(R.styleable.CustomHead_right_res, 0);
            isShowLine = attributes.getBoolean(R.styleable.CustomHead_isShowLine, false);
        } finally {
            attributes.recycle();
        }

        setupViews();
    }

    private void setupViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.include_custom_head, this);
        mParentRl = (RelativeLayout) findViewById(R.id.custom_toolbar_rl);
        mLeftTv = (TextView) findViewById(R.id.custom_head_left_tv);
        mLeftIv = (ImageView) findViewById(R.id.custom_head_left_iv);
        mTitleTv = (TextView) findViewById(R.id.custom_head_title_tv);
        mSubTitleTv = (TextView) findViewById(R.id.custom_head_subtitle_tv);
        mRightTv = (TextView) findViewById(R.id.custom_head_right_tv);
        mRightIv = (ImageView) findViewById(R.id.custom_head_right_iv);
        mLineView = findViewById(R.id.custom_head_line_view);

        mLeftTv.setOnClickListener(this);
        mLeftIv.setOnClickListener(this);
        mRightTv.setOnClickListener(this);
        mRightIv.setOnClickListener(this);

        refreshView();
    }

    public void refreshView() {
        if (mBackGroundColorRes != 0) {
            mParentRl.setBackgroundColor(mBackGroundColorRes);
        } else {
            mParentRl.setBackgroundColor(getResources().getColor(android.R.color.white));
        }
        if (!TextUtils.isEmpty(mLeftStr)) {
            mLeftTv.setText(mLeftStr);
            mLeftTv.setVisibility(VISIBLE);
            mLeftIv.setVisibility(INVISIBLE);
        } else if (mLeftRes != 0) {
            mLeftIv.setImageResource(mLeftRes);
            mLeftTv.setVisibility(INVISIBLE);
            mLeftIv.setVisibility(VISIBLE);
        } else {
            mLeftTv.setVisibility(INVISIBLE);
            mLeftIv.setVisibility(INVISIBLE);
        }

        if (!TextUtils.isEmpty(mTitleStr)) {
            mTitleTv.setText(mTitleStr);
        }

        if (!TextUtils.isEmpty(mSubTitleStr)) {
            mSubTitleTv.setVisibility(VISIBLE);
            mSubTitleTv.setText(mSubTitleStr);
        } else {
            mSubTitleTv.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(mRightStr)) {
            mRightTv.setText(mRightStr);
            mRightTv.setVisibility(VISIBLE);
            mRightIv.setVisibility(INVISIBLE);
        } else if (mRightRes != 0) {
            mRightIv.setImageResource(mRightRes);
            mRightTv.setVisibility(INVISIBLE);
            mRightIv.setVisibility(VISIBLE);
        } else {
            mRightTv.setVisibility(INVISIBLE);
            mRightIv.setVisibility(INVISIBLE);
        }
        mLineView.setVisibility(isShowLine ? VISIBLE : GONE);
    }

    public void setToolBarListener(ToolBarListener toolBarListener) {
        mToolBarListener = toolBarListener;
    }

    public TextView getLeftTv() {
        return mLeftTv;
    }

    public ImageView getLeftIv() {
        return mLeftIv;
    }

    public TextView getTitleTv() {
        return mTitleTv;
    }

    public TextView getSubTitleTv() {
        return mSubTitleTv;
    }

    public TextView getRightTv() {
        return mRightTv;
    }

    public ImageView getRightIv() {
        return mRightIv;
    }

    public void setLeftStr(String leftStr) {
        mLeftStr = leftStr;
        refreshView();
    }

    public void setLeftRes(int leftRes) {
        mLeftRes = leftRes;
        refreshView();
    }

    public void setTitleStr(String titleStr) {
        mTitleStr = titleStr;
        refreshView();
    }

    public void setSubTitleStr(String subTitleStr) {
        mSubTitleStr = subTitleStr;
        refreshView();
    }

    public void setRightStr(String rightStr) {
        mRightStr = rightStr;
        refreshView();
    }

    public void setRightRes(int rightRes) {
        mRightRes = rightRes;
        refreshView();
    }

    public void setShowLine(boolean showLine) {
        isShowLine = showLine;
        refreshView();
    }

    public boolean isShowLine() {
        return isShowLine;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.custom_head_left_iv || id == R.id.custom_head_left_tv) {
            if (mToolBarListener == null && getContext() instanceof AppCompatActivity) {
                ((AppCompatActivity) getContext()).finish();
            } else {
                mToolBarListener.clickLeft();
            }
        } else if ((id == R.id.custom_head_right_tv || id == R.id.custom_head_right_iv) && mToolBarListener != null) {
            mToolBarListener.clickRight();
        }
    }
}
