package com.fengz.personal.fourweeks.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * 创建时间：2019/1/3
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：xml控件全局预处理
 */
public class LayoutInflaterConvert implements LayoutInflater.Factory2 {
    @Override
    public View onCreateView(View view, String s, Context context, AttributeSet attributeSet) {
        return onCreateView(s, context, attributeSet);
    }

    @Override
    public View onCreateView(String s, Context context, AttributeSet attributeSet) {
        switch (s) {
            case "TextView":
                // 实际什么都没做  这里可以用来全局替换控件
                return new TextView(context, attributeSet);
        }
        return null;
    }
}
