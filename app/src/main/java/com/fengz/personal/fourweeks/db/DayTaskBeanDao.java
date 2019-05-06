package com.fengz.personal.fourweeks.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.fengz.personal.fourweeks.business1.model.entity.DayTaskBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DAY_TASK_BEAN".
*/
public class DayTaskBeanDao extends AbstractDao<DayTaskBean, Long> {

    public static final String TABLENAME = "DAY_TASK_BEAN";

    /**
     * Properties of entity DayTaskBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Rank = new Property(1, int.class, "rank", false, "RANK");
        public final static Property TaskID = new Property(2, Long.class, "taskID", false, "TASK_ID");
        public final static Property FinishedTime = new Property(3, String.class, "finishedTime", false, "FINISHED_TIME");
        public final static Property CreateDate = new Property(4, long.class, "createDate", false, "CREATE_DATE");
        public final static Property CloseDate = new Property(5, long.class, "closeDate", false, "CLOSE_DATE");
        public final static Property DayStatus = new Property(6, int.class, "dayStatus", false, "DAY_STATUS");
    }


    public DayTaskBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DayTaskBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DAY_TASK_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"RANK\" INTEGER NOT NULL ," + // 1: rank
                "\"TASK_ID\" INTEGER," + // 2: taskID
                "\"FINISHED_TIME\" TEXT," + // 3: finishedTime
                "\"CREATE_DATE\" INTEGER NOT NULL ," + // 4: createDate
                "\"CLOSE_DATE\" INTEGER NOT NULL ," + // 5: closeDate
                "\"DAY_STATUS\" INTEGER NOT NULL );"); // 6: dayStatus
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DAY_TASK_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DayTaskBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getRank());
 
        Long taskID = entity.getTaskID();
        if (taskID != null) {
            stmt.bindLong(3, taskID);
        }
 
        String finishedTime = entity.getFinishedTime();
        if (finishedTime != null) {
            stmt.bindString(4, finishedTime);
        }
        stmt.bindLong(5, entity.getCreateDate());
        stmt.bindLong(6, entity.getCloseDate());
        stmt.bindLong(7, entity.getDayStatus());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DayTaskBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getRank());
 
        Long taskID = entity.getTaskID();
        if (taskID != null) {
            stmt.bindLong(3, taskID);
        }
 
        String finishedTime = entity.getFinishedTime();
        if (finishedTime != null) {
            stmt.bindString(4, finishedTime);
        }
        stmt.bindLong(5, entity.getCreateDate());
        stmt.bindLong(6, entity.getCloseDate());
        stmt.bindLong(7, entity.getDayStatus());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DayTaskBean readEntity(Cursor cursor, int offset) {
        DayTaskBean entity = new DayTaskBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // rank
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // taskID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // finishedTime
            cursor.getLong(offset + 4), // createDate
            cursor.getLong(offset + 5), // closeDate
            cursor.getInt(offset + 6) // dayStatus
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DayTaskBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRank(cursor.getInt(offset + 1));
        entity.setTaskID(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setFinishedTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCreateDate(cursor.getLong(offset + 4));
        entity.setCloseDate(cursor.getLong(offset + 5));
        entity.setDayStatus(cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DayTaskBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DayTaskBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DayTaskBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
