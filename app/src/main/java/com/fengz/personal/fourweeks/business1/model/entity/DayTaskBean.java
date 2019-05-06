package com.fengz.personal.fourweeks.business1.model.entity;

import com.fengz.personal.fourweeks.business1.model.anno.DayTaskStatus;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class DayTaskBean {
    @Id(autoincrement = true)
    private Long id;

    // 在整个任务的排列天数
    private int rank;

    // task id
    private Long taskID;

    // 任务完成时间
    private String finishedTime;

    // 创建时间
    private long createDate;

    // 关闭任务时间
    private long closeDate;

    @DayTaskStatus
    private int dayStatus;

    @Generated(hash = 583584763)
    public DayTaskBean(Long id, int rank, Long taskID, String finishedTime,
            long createDate, long closeDate, int dayStatus) {
        this.id = id;
        this.rank = rank;
        this.taskID = taskID;
        this.finishedTime = finishedTime;
        this.createDate = createDate;
        this.closeDate = closeDate;
        this.dayStatus = dayStatus;
    }

    @Generated(hash = 406175250)
    public DayTaskBean() {
    }

    @Override
    public String toString() {
        return "DayTaskBean{" +
                "id=" + id +
                ", rank=" + rank +
                ", taskID=" + taskID +
                ", finishedTime='" + finishedTime + '\'' +
                ", createDate=" + createDate +
                ", closeDate=" + closeDate +
                ", dayStatus=" + dayStatus +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Long getTaskID() {
        return this.taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

    public String getFinishedTime() {
        return this.finishedTime;
    }

    public void setFinishedTime(String finishedTime) {
        this.finishedTime = finishedTime;
    }

    public long getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getCloseDate() {
        return this.closeDate;
    }

    public void setCloseDate(long closeDate) {
        this.closeDate = closeDate;
    }

    public int getDayStatus() {
        return this.dayStatus;
    }

    public void setDayStatus(int dayStatus) {
        this.dayStatus = dayStatus;
    }
}
