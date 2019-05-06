package com.fengz.personal.fourweeks.business1.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fengz.personal.fourweeks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 加载菊花框
 *
 * @version v1.0, 18/6/29
 * @auther fengzhen
 */
public class LoadingDialog extends Dialog {
    @BindView(R.id.progress_loading)
    ProgressBar mProgressLoading;
    @BindView(R.id.tv_loading)
    TextView mTvLoading;

    private String mContent;

    public LoadingDialog(@NonNull Context context) {
        super(context,R.style.LoadDialogStyle);
        init(context);
    }

    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        ButterKnife.bind(this, inflate);

        setContentView(inflate);
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(attributes);
        window.setWindowAnimations(R.style.PopWinAnimStyle);
    }

    public void setContent(String msg) {
        mTvLoading.setText(msg);
        mTvLoading.setVisibility(View.VISIBLE);
    }
}
