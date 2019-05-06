package com.fengz.personal.fourweeks.business1.model.entity;

import com.fengz.personal.fourweeks.business1.model.anno.TaskStatus;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * 创建时间：2019/4/2
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：Task Bean 保存任务信息
 */
@Entity
public class TaskBean {
    @Id(autoincrement = true)
    private Long id;

    // 标题 必填
    private String title;

    // 内容 必填
    private String content;

    // 创建时间 时间戳
    private long createdData;

    // 持续时间 默认4周 28天
    private long lifeData;

    // 结束时间
    private long endData;

    /* 可完成时段 其余时段不能完成任务
     * 日期格式 11：12-12：23
     * 默认时间为全天
     */
    private String usefullTime;

    // 目标完成度
    private double desiredPersents;

    // 任务完成度
    private double taskPersents;

    // 完成任务奖励
    private String award;

    // 任务失败惩罚
    private String punishment;

    /**
     * 任务状态 0：正在进行 1：已经完成 2：手动取消
     * {@link TaskStatus}
     */
    @TaskStatus
    private int status;

    @Transient
    private List<DayTaskBean> list;

    @Generated(hash = 1471116173)
    public TaskBean(Long id, String title, String content, long createdData,
                    long lifeData, long endData, String usefullTime, double desiredPersents,
                    double taskPersents, String award, String punishment, int status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdData = createdData;
        this.lifeData = lifeData;
        this.endData = endData;
        this.usefullTime = usefullTime;
        this.desiredPersents = desiredPersents;
        this.taskPersents = taskPersents;
        this.award = award;
        this.punishment = punishment;
        this.status = status;
    }

    @Generated(hash = 1443476586)
    public TaskBean() {
    }

    public void setList(List<DayTaskBean> list) {
        this.list = list;
    }

    public List<DayTaskBean> getList() {
        return list;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedData() {
        return this.createdData;
    }

    public void setCreatedData(long createdData) {
        this.createdData = createdData;
    }

    public long getLifeData() {
        return this.lifeData;
    }

    public void setLifeData(long lifeData) {
        this.lifeData = lifeData;
    }

    public long getEndData() {
        return this.endData;
    }

    public void setEndData(long endData) {
        this.endData = endData;
    }

    public String getUsefullTime() {
        return this.usefullTime;
    }

    public void setUsefullTime(String usefullTime) {
        this.usefullTime = usefullTime;
    }

    public double getDesiredPersents() {
        return this.desiredPersents;
    }

    public void setDesiredPersents(double desiredPersents) {
        this.desiredPersents = desiredPersents;
    }

    public double getTaskPersents() {
        return this.taskPersents;
    }

    public void setTaskPersents(double taskPersents) {
        this.taskPersents = taskPersents;
    }

    public String getAward() {
        return this.award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getPunishment() {
        return this.punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 是否是有效任务
     */
    public boolean isActiviting() {
        return endData >= System.currentTimeMillis();
    }
}
