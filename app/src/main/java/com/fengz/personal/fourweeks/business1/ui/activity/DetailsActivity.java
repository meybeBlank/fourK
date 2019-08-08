package com.fengz.personal.fourweeks.business1.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.base.mvp.APresenter;
import com.fengz.personal.fourweeks.base.mvp.BaseActivity;
import com.fengz.personal.fourweeks.business1.contract.DetailsContract;
import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;
import com.fengz.personal.fourweeks.business1.presenter.DetailsPresenter;
import com.fengz.personal.fourweeks.business1.ui.adapter.DayTableAdapter;
import com.fengz.personal.fourweeks.utils.DateUtils;

import java.text.DecimalFormat;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailsActivity extends BaseActivity implements DetailsContract.View {

    public static final String EXTRA_TASK_ID = "extra_task_id";

    public static Intent getCallingIntent(@NonNull Context context, long taskId) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_TASK_ID, taskId);
        return intent;
    }

    @BindView(R.id.tv_title_details_act)
    TextView mTvTitleDetailsAct;
    @BindView(R.id.tv_duration_details_act)
    TextView mTvDurationDetailsAct;
    @BindView(R.id.tv_content_details_act)
    TextView mTvContentDetailsAct;
    @BindView(R.id.ll_base_details_act)
    LinearLayout mLlBaseDetailsAct;
    @BindView(R.id.tv_degree_details_act)
    TextView mTvDegreeDetailsAct;
    @BindView(R.id.tv_award_details_act)
    TextView mTvAwardDetailsAct;
    @BindView(R.id.tv_punishment_details_act)
    TextView mTvPunishmentDetailsAct;
    @BindView(R.id.ll_status_details_act)
    LinearLayout mLlStatusDetailsAct;
    @BindView(R.id.recycler_details_act)
    RecyclerView mRecyclerDetailsAct;

    @Inject
    @APresenter
    DetailsPresenter mPresenter;

    private long mTaskId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setTitle("任务详情");

        mTaskId = getIntent().getLongExtra(EXTRA_TASK_ID, -1);
        mPresenter.getTask(mTaskId);
    }

    @Override
    public void setData(TaskBean task) {
        mTvTitleDetailsAct.setText(task.getTitle());
        mTvDurationDetailsAct.setText(DateUtils.parse(task.getCreatedData(), DateUtils.FORMAT_1)
                + " —— "
                + DateUtils.parse(task.getEndData(), DateUtils.FORMAT_1));
        mTvContentDetailsAct.setText(task.getContent());
        mTvDegreeDetailsAct.setText(new DecimalFormat("0.00").format(100 * task.getTaskPersents())
                + "/" + task.getDesiredPersents());
        if (!TextUtils.isEmpty(task.getAward())) {
            mTvAwardDetailsAct.setVisibility(View.VISIBLE);
            mTvAwardDetailsAct.setText(task.getAward());
        } else {
            mTvAwardDetailsAct.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(task.getPunishment())) {
            mTvPunishmentDetailsAct.setVisibility(View.VISIBLE);
            mTvPunishmentDetailsAct.setText(task.getPunishment());
        } else {
            mTvPunishmentDetailsAct.setVisibility(View.GONE);
        }
        // 完成记录
        mRecyclerDetailsAct.setLayoutManager(new GridLayoutManager(this, 7));
        mRecyclerDetailsAct.setAdapter(new DayTableAdapter(task.getList()));
    }
}
