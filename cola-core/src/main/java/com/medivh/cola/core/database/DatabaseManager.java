package com.medivh.cola.core.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;



public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DatabaseManager() {
    }

    public DatabaseManager init(Context context,String dataBaseName) {
        initDao(context,dataBaseName);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private void initDao(Context context ,String dataBaseName) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, dataBaseName+".db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao() {
        return mDao;
    }
}
