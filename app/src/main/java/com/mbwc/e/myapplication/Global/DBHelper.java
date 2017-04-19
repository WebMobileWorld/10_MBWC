package com.mbwc.e.myapplication.Global;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.mbwc.e.myapplication.Models.CalendarDataModel;
import com.mbwc.e.myapplication.Models.CirCultModel;
import com.mbwc.e.myapplication.Models.ResultModel;
import com.mbwc.e.myapplication.Models.RootCategory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by E on 10/18/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Mbwc.db";
    public static final String CATEGORIES_TABLE_NAME = "exercises_category";
    public static final String CATEGORIES_COLUMN_ID = "cid";
    public static final String CATEGORIES_COLUMN_CNAME = "cname";
    public static final String CATEGORIES_COLUMN_BOUNSFLAG = "bouns_flag";

    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table exercises_category " +
                        "(cid integer primary key, cname text, bouns_flag text)"
        );
        db.execSQL(
                "create table done_result " +
                        "(rid integer primary key, categoryname text, done_count text, all_count text,done_date date)"
        );
        db.execSQL(
                "create table record_result " +
                        "(record_id integer primary key, weight integer, lbs text, sts text, input_date date)"
        );
        db.execSQL(
                "create table circult_result " +
                        "(cr_id integer primary key, dataname text, dataname_id text, success_flag text, done_date date)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
    public boolean insertCirCult(String dataname, String data_id, String success_flag, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery( "select * from circult_result where done_date='" + date + "' AND dataname='" + dataname + "' AND dataname_id='" + data_id + "'", null );
        int count = res.getCount();
        if (count > 0) {
            Integer cr_id = getCRID(dataname, date);
            ContentValues contentValues = new ContentValues();
            contentValues.put("success_flag", success_flag);
            db.update("circult_result", contentValues, "cr_id = ? ", new String[] { Integer.toString(cr_id) } );
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("dataname", dataname);
            contentValues.put("dataname_id", data_id);
            contentValues.put("success_flag", success_flag);
            contentValues.put("done_date", date);
            db.insert("circult_result", null, contentValues);
        }
        return true;
    }
    public boolean insertCategory(String cid, String cname, String bouns_flag)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cid", Integer.valueOf(cid));
        contentValues.put("cname", cname);
        contentValues.put("bouns_flag", bouns_flag);
        db.insert("exercises_category", null, contentValues);
        return true;
    }
    public boolean insertRecord(Double weight, String date,String lbs, String sts)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery( "select * from record_result where input_date='" + date + "'", null );
        int count = res.getCount();
        if (count > 0 ) {
            Integer rid = getRecordId(date) ;
            ContentValues contentValues = new ContentValues();
            contentValues.put("weight", weight);
            contentValues.put("lbs", lbs);
            contentValues.put("sts", sts);
            db.update("record_result", contentValues, "record_id = ? ", new String[] { Integer.toString(rid) } );
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("weight", weight);
            contentValues.put("input_date", date);
            contentValues.put("lbs", lbs);
            contentValues.put("sts", sts);
            db.insert("record_result", null, contentValues);
        }
        return true;
    }
    public Integer getRecordId(String date){
        Integer rid ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from record_result where input_date='" + date + "'", null  );
        res.moveToFirst();
        rid = res.getInt(res.getColumnIndex("record_id"));
        return rid ;
    }
    public boolean updateCategories (Integer cid, String bouns_flag)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("bouns_flag", bouns_flag);
        db.update("exercises_category", contentValues, "cid = ? ", new String[] { Integer.toString(cid) } );
        return true;
    }
    public ArrayList<RootCategory> getRootCategory(){
        ArrayList<RootCategory> rootCategoryArrayList = new ArrayList<RootCategory>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from exercises_category", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            RootCategory rootCategory = new RootCategory();
            rootCategory.cid = String.valueOf(res.getInt(res.getColumnIndex(CATEGORIES_COLUMN_ID)));
            rootCategory.rootCategoryName = res.getString(res.getColumnIndex(CATEGORIES_COLUMN_CNAME));
            rootCategory.bouns_flag = res.getString(res.getColumnIndex(CATEGORIES_COLUMN_BOUNSFLAG));
            rootCategoryArrayList.add(rootCategory);
            res.moveToNext();
        }
        return rootCategoryArrayList;
    }
    public boolean insertDoneResult(String cname, String done_count, String all_count, String done_date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery( "select * from done_result where done_date='" + done_date + "' AND categoryname='" + cname + "'", null );
        int count = res.getCount();
        if (count > 0) {
            Integer rid = getRID(cname, done_date);
            ContentValues contentValues = new ContentValues();
            contentValues.put("done_count", done_count);
            contentValues.put("all_count", all_count);
            db.update("done_result", contentValues, "rid = ? ", new String[] { Integer.toString(rid) } );
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("categoryname", cname);
            contentValues.put("done_count", done_count);
            contentValues.put("all_count", all_count);
            contentValues.put("done_date", done_date);
            db.insert("done_result", null, contentValues);
        }
        return true;
    }
    public Integer getCount(String selectdate){
        Integer count ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select count(rid) as num_count from done_result where done_date='" + selectdate + "'", null );
        res.moveToFirst();
        count = res.getInt(res.getColumnIndex("num_count"));
        return count;
    }
    public ArrayList<CalendarDataModel> getCalendarData(String select_date){
        ArrayList<CalendarDataModel> calendarDataModelArrayList = new ArrayList<CalendarDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from done_result where done_date ='" + select_date + "' group by categoryname", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            CalendarDataModel calendarDataModel = new CalendarDataModel();
            calendarDataModel.categoryname = res.getString(res.getColumnIndex("categoryname"));
            calendarDataModel.done_count = res.getString(res.getColumnIndex("done_count"));
            calendarDataModel.all_count = res.getString(res.getColumnIndex("all_count"));
            calendarDataModel.done_date = res.getString(res.getColumnIndex("done_date"));
            calendarDataModelArrayList.add(calendarDataModel);
            res.moveToNext();
        }
        return calendarDataModelArrayList;
    }
    public Integer getDoneCount(String select_date, String categoryname){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from circult_result where done_date='" + select_date + "' AND dataname='" + categoryname + "'", null );
        int count = res.getCount();
        return count;
    }
    public Integer getCRID(String categoryname, String selectdate){
        Integer crid ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from circult_result where done_date='" + selectdate + "' and dataname='" + categoryname + "'", null  );
        res.moveToFirst();
        crid = res.getInt(res.getColumnIndex("cr_id"));
        return crid ;
    }
    public Integer getRID(String categoryname, String selectdate){
        Integer rid ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from done_result where done_date='" + selectdate + "' and categoryname='" + categoryname + "'", null  );
        res.moveToFirst();
        rid = res.getInt(res.getColumnIndex("rid"));
        return rid ;
    }
    public ArrayList<String> getDate(){
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from done_result", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getString(res.getColumnIndex("done_date")));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<CirCultModel> getCircults(String date, String dataname){
        ArrayList<CirCultModel> cirCultModelArrayList = new ArrayList<CirCultModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from circult_result where done_date='" + date + "' and dataname='" + dataname + "'", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            CirCultModel cirCultModel = new CirCultModel();
            cirCultModel.data_id = res.getString(res.getColumnIndex("dataname_id"));
            cirCultModel.success_flag = res.getString(res.getColumnIndex("success_flag"));
            cirCultModelArrayList.add(cirCultModel);
            res.moveToNext();
        }
        return cirCultModelArrayList;
    }
    public ArrayList<ResultModel> getWeight(String current_date){
        ArrayList<ResultModel> resultModelArrayList = new ArrayList<ResultModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from record_result order by record_id desc limit 7", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            ResultModel resultModel = new ResultModel();
            resultModel.weight = res.getInt(res.getColumnIndex("weight"));
            resultModel.date = res.getString(res.getColumnIndex("input_date"));
            resultModel.lbs = res.getString(res.getColumnIndex("lbs"));
            resultModel.sts = res.getString(res.getColumnIndex("sts"));
            resultModelArrayList.add(resultModel);
            res.moveToNext();
        }
        return resultModelArrayList;
    }
    public Integer getMax(String current_date){
        Integer max ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select MAX(weight) as weight from record_result where input_date <= '" + current_date + "' order by input_date asc limit 7", null );
        res.moveToFirst();
        max = res.getInt(res.getColumnIndex("weight"));
        res.moveToNext();
        return max;
    }
    public Integer getMin(String current_date){
        Integer min ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select MIN(weight) as weight from record_result where input_date <= '" + current_date + "' order by input_date asc limit 7", null );
        res.moveToFirst();
        min = res.getInt(res.getColumnIndex("weight"));
        res.moveToNext();
        return min;
    }
    public boolean deleteData(String Date, String exercisesname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result_flag ;

        int result = db.delete("done_result", "done_date='" + Date + "' and categoryname='" + exercisesname + "'", null);
        result_flag = result > 0;
        return result_flag;
    }
    public boolean deleteAllData(String Date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result_flag ;

        int result = db.delete("done_result", "done_date='" + Date + "'", null);
        result_flag = result > 0;
        return result_flag;
    }
    public boolean deleteDataContent(String Date, String dataname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result_flag ;

        int result = db.delete("circult_result", "done_date='" + Date + "' and dataname='" + dataname + "'", null);
        result_flag = result > 0;
        return result_flag;
    }

}
