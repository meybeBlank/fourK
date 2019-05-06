package com.fengz.personal.fourweeks.business1.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.base.mvp.APresenter;
import com.fengz.personal.fourweeks.base.mvp.BaseActivity;
import com.fengz.personal.fourweeks.business1.contract.AddContract;
import com.fengz.personal.fourweeks.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间：2019/4/2
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：添加新任务
 */
public class AddActivity extends BaseActivity implements AddContract.View {

    @BindView(R.id.et_title_add_act)
    EditText mEtTitleAddAct;
    @BindView(R.id.tv_optional_add_act)
    TextView mTvOptionalAddAct;
    @BindView(R.id.et_desired_add_act)
    EditText mEtDesiredAddAct;
    @BindView(R.id.et_award_add_act)
    EditText mEtAwardAddAct;
    @BindView(R.id.et_punishment_add_act)
    EditText mEtPunishmentAddAct;
    @BindView(R.id.et_content_add_act)
    EditText mEtContentAddAct;
    @BindView(R.id.fab_add_act)
    FloatingActionButton mFabAddAct;

    @Inject
    @APresenter
    AddContract.Presenter mPresenter;

    public static Intent getCallingIntent(@NonNull Context context) {
        Intent intent = new Intent(context, AddActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("新增目标");
    }

    private void addTask() {
        String title = mEtTitleAddAct.getText().toString();
        String content = mEtContentAddAct.getText().toString();
        if (check(title, content)) {
            mPresenter.addTask(title, content, mTvOptionalAddAct.getText().toString(),
                    mEtDesiredAddAct.getText().toString(), mEtAwardAddAct.getText().toString(),
                    mEtPunishmentAddAct.getText().toString());
        }
    }

    private boolean check(String title, String content) {
        if (TextUtils.isEmpty(title)) {
            Snackbar.make(mFabAddAct, "标题不能为空", Snackbar.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(content)) {
            Snackbar.make(mFabAddAct, "内容不能为空", Snackbar.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @OnClick({R.id.ll_optional_add_act, R.id.fab_add_act})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_optional_add_act:
                // TODO: 2019/4/3 之后再做
                break;
            case R.id.fab_add_act:
                addTask();
                break;
        }
    }

    @Override
    public void addSuccess(boolean success) {
        if (success) {
            ToastUtils.show("添加任务成功！");
            onBackPressed();
        } else {
            ToastUtils.show("添加失败，请重试！");
        }
    }
}
