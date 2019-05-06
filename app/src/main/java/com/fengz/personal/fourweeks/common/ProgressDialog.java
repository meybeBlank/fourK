package com.fengz.personal.fourweeks.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fengz.personal.fourweeks.R;

/**
 * 创建时间：2019/3/13
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：下载进度展示Dialog
 */
public class ProgressDialog {

    private Context context;
    private ProgressBar progressBar;
    private TextView tvProgress;
    private String title;
    private DialogInterface.OnClickListener nageListener;

    private AlertDialog alertDialog;

    public ProgressDialog(Context context, String title, DialogInterface.OnClickListener nageListener) {
        this.context = context;
        this.title = title;
        this.nageListener = nageListener;
        init();
    }

    private void init() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
        progressBar = layout.findViewById(R.id.progressBar_dialog);
        tvProgress = layout.findViewById(R.id.tv_progress_dialog);
        builder.setTitle(title);
        builder.setNegativeButton("取消", nageListener);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.setView(layout);
    }

    public void setProgress(int current, int total) {
        if (total > 0) {
            progressBar.setProgress(Double.valueOf(current / 1.0 / total * 100).intValue());
            tvProgress.setText(current + "/" + total);
        }
    }

    public void show() {
        alertDialog.show();
    }

    public void dismiss() {
        alertDialog.dismiss();
    }
}
