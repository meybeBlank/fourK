package com.fengz.personal.fourweeks.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.common.BottomDialogAdapter;
import com.fengz.personal.fourweeks.common.BottomModel;
import com.fengz.personal.fourweeks.common.ProgressDialog;

import java.util.List;

/**
 * 创建时间：2019/3/13
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：Dialog统一管理类
 */
public class DialogManager {
    private DialogManager() {
    }

    /**
     * 创建时间：2019/3/13
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：创建提示框提示框
     *
     * @param info        展示信息
     * @param title       标题
     * @param negativeTxt 左侧按钮文案
     * @param positiveTxt 右侧按钮文案
     * @param negListener 左侧事件
     * @param posListener 右侧事件
     */
    public static AlertDialog createAlertDialog(Context context,boolean cancelable,
                                                String title, String info,
                                                String positiveTxt, String negativeTxt,
                                                DialogInterface.OnClickListener posListener,
                                                DialogInterface.OnClickListener negListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(info);
        builder.setCancelable(cancelable);
        builder.setPositiveButton(positiveTxt, posListener);
        builder.setNegativeButton(negativeTxt, negListener);
        return builder.create();
    }

    /**
     * 创建时间：2019/3/13
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：进度条展示弹框
     */
    public static ProgressDialog showProgressDialog(Context context, String title,
                                                    final DialogInterface.OnClickListener nageListener) {
        return new ProgressDialog(context, title, nageListener);
    }

    /**
     * iOS底部风格弹框
     */
    public static Dialog showBottonDialog(final Context activity, @NonNull List<BottomModel> items,
                                          final AdapterView.OnItemClickListener listener) {
        View view = UIUtils.inflate(activity, R.layout.dialog_bottom_ios,
                null);
        final Dialog dialog = new Dialog(activity, R.style.transparentDialog);
        dialog.setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        int allHeight = UIUtils.dipToPx(activity, 51 * items.size() + 70);
        int maxHeight = (int) (UIUtils.getScreenHeight(activity) * 0.6);
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.gravity = Gravity.BOTTOM;
        wl.height = allHeight < maxHeight ? allHeight : maxHeight;
        dialog.onWindowAttributesChanged(wl);
        dialog.setCanceledOnTouchOutside(true);

        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recycler_dialog_bottom_ios);
        recycler.setLayoutManager(new LinearLayoutManager(activity));
        recycler.setAdapter(new BottomDialogAdapter(activity, items,
                (parent, view1, position, id) -> {
                    listener.onItemClick(parent, view1, position, id);
                    dialog.dismiss();
                }));
        final TextView view2 = (TextView) view.findViewById(R.id.cancel);
        view2.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
        return dialog;
    }
}
