package com.training.vungoctuan.sqlitedemo.sqlite;

import android.provider.BaseColumns;

/**
 * Created by vungoctuan on 2/12/18.
 */
public final class FeedReaderContract {
    private FeedReaderContract() {
    }

    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}
