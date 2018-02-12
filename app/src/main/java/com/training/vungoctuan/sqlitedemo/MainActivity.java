package com.training.vungoctuan.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.training.vungoctuan.sqlitedemo.sqlite.FeedReaderContract;
import com.training.vungoctuan.sqlitedemo.sqlite.FeedReaderDbHelper;

import java.util.ArrayList;
import java.util.List;

import static com.training.vungoctuan.sqlitedemo.sqlite.FeedReaderContract.*;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FeedReaderDbHelper mDbHelper;
    private SQLiteDatabase mSQliteDb;
    private Button button_getdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_getdata = findViewById(R.id.button_getdata);
        mDbHelper = new FeedReaderDbHelper(this);
        onInsertDemoData();

        button_getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReadDemoData();
            }
        });
    }

    private void onInsertDemoData() {
        mSQliteDb = mDbHelper.getWritableDatabase();
        long mRow1 = mSQliteDb.insert(
            FeedEntry.TABLE_NAME,
            null,
            mDbHelper.onCreateContentValue("Vu Ngoc Tuan", "Tuan Tuan Tu"));
        Log.d(TAG, "mRow1");
        long mRow2 = mSQliteDb.insert(
            FeedEntry.TABLE_NAME,
            null,
            mDbHelper.onCreateContentValue("Vu Ngoc Tuan 2", "Tuan Tuan Tu 2"));
        Log.d(TAG, "mRow2");
        long mRow3 = mSQliteDb.insert(
            FeedEntry.TABLE_NAME,
            null,
            mDbHelper.onCreateContentValue("Vu Ngoc Tuan 3", "Tuan Tuan Tu 3"));
        Log.d(TAG, "mRow3");
    }

    private void onReadDemoData() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
            FeedEntry._ID,
            FeedEntry.COLUMN_NAME_TITLE,
            FeedEntry.COLUMN_NAME_SUBTITLE
        };
        // Filter results WHERE "title" = 'My Title'
        String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = {"Vu Ngoc"};
        // How you want the results sorted in the resulting Cursor
        String sortOrder =
            FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";
        Cursor cursor = db.query(
            FeedEntry.TABLE_NAME,                     // The table to query
            projection,                               // The columns to return
            null,                                // The columns for the WHERE clause
            null,                            // The values for the WHERE clause
            null,                                     // don't group the rows
            null,                                     // don't filter by row groups
            sortOrder                                 // The sort order
        );
        List itemName = new ArrayList();
        while (cursor.moveToNext()){
            String iName = cursor.getString(
                cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE)
            );
            itemName.add(iName);
            Log.d(TAG, iName);
        }
        cursor.close();
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }
}
