package com.fengz.personal.fourweeks.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.fengz.personal.fourweeks.business1.model.entity.TaskBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TASK_BEAN".
*/
public class TaskBeanDao extends AbstractDao<TaskBean, Long> {

    public static final String TABLENAME = "TASK_BEAN";

    /**
     * Properties of entity TaskBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Content = new Property(2, String.class, "content", false, "CONTENT");
        public final static Property CreatedData = new Property(3, long.class, "createdData", false, "CREATED_DATA");
        public final static Property LifeData = new Property(4, long.class, "lifeData", false, "LIFE_DATA");
        public final static Property EndData = new Property(5, long.class, "endData", false, "END_DATA");
        public final static Property UsefullTime = new Property(6, String.class, "usefullTime", false, "USEFULL_TIME");
        public final static Property DesiredPersents = new Property(7, double.class, "desiredPersents", false, "DESIRED_PERSENTS");
        public final static Property TaskPersents = new Property(8, double.class, "taskPersents", false, "TASK_PERSENTS");
        public final static Property Award = new Property(9, String.class, "award", false, "AWARD");
        public final static Property Punishment = new Property(10, String.class, "punishment", false, "PUNISHMENT");
        public final static Property Status = new Property(11, int.class, "status", false, "STATUS");
    }


    public TaskBeanDao(DaoConfig config) {
        super(config);
    }
    
    public TaskBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TASK_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"CONTENT\" TEXT," + // 2: content
                "\"CREATED_DATA\" INTEGER NOT NULL ," + // 3: createdData
                "\"LIFE_DATA\" INTEGER NOT NULL ," + // 4: lifeData
                "\"END_DATA\" INTEGER NOT NULL ," + // 5: endData
                "\"USEFULL_TIME\" TEXT," + // 6: usefullTime
                "\"DESIRED_PERSENTS\" REAL NOT NULL ," + // 7: desiredPersents
                "\"TASK_PERSENTS\" REAL NOT NULL ," + // 8: taskPersents
                "\"AWARD\" TEXT," + // 9: award
                "\"PUNISHMENT\" TEXT," + // 10: punishment
                "\"STATUS\" INTEGER NOT NULL );"); // 11: status
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TASK_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TaskBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
        stmt.bindLong(4, entity.getCreatedData());
        stmt.bindLong(5, entity.getLifeData());
        stmt.bindLong(6, entity.getEndData());
 
        String usefullTime = entity.getUsefullTime();
        if (usefullTime != null) {
            stmt.bindString(7, usefullTime);
        }
        stmt.bindDouble(8, entity.getDesiredPersents());
        stmt.bindDouble(9, entity.getTaskPersents());
 
        String award = entity.getAward();
        if (award != null) {
            stmt.bindString(10, award);
        }
 
        String punishment = entity.getPunishment();
        if (punishment != null) {
            stmt.bindString(11, punishment);
        }
        stmt.bindLong(12, entity.getStatus());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TaskBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
        stmt.bindLong(4, entity.getCreatedData());
        stmt.bindLong(5, entity.getLifeData());
        stmt.bindLong(6, entity.getEndData());
 
        String usefullTime = entity.getUsefullTime();
        if (usefullTime != null) {
            stmt.bindString(7, usefullTime);
        }
        stmt.bindDouble(8, entity.getDesiredPersents());
        stmt.bindDouble(9, entity.getTaskPersents());
 
        String award = entity.getAward();
        if (award != null) {
            stmt.bindString(10, award);
        }
 
        String punishment = entity.getPunishment();
        if (punishment != null) {
            stmt.bindString(11, punishment);
        }
        stmt.bindLong(12, entity.getStatus());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TaskBean readEntity(Cursor cursor, int offset) {
        TaskBean entity = new TaskBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // content
            cursor.getLong(offset + 3), // createdData
            cursor.getLong(offset + 4), // lifeData
            cursor.getLong(offset + 5), // endData
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // usefullTime
            cursor.getDouble(offset + 7), // desiredPersents
            cursor.getDouble(offset + 8), // taskPersents
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // award
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // punishment
            cursor.getInt(offset + 11) // status
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TaskBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setContent(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCreatedData(cursor.getLong(offset + 3));
        entity.setLifeData(cursor.getLong(offset + 4));
        entity.setEndData(cursor.getLong(offset + 5));
        entity.setUsefullTime(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDesiredPersents(cursor.getDouble(offset + 7));
        entity.setTaskPersents(cursor.getDouble(offset + 8));
        entity.setAward(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setPunishment(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setStatus(cursor.getInt(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TaskBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TaskBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TaskBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}