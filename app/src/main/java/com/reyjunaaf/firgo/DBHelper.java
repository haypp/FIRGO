package com.reyjunaaf.firgo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Calendar;

//TODO add date element
    public class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "money.db", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase DB) {
            DB.execSQL("create Table Userdetails(nama TEXT,jenis TEXT, jumlah INT, note Text,date Text)");
            DB.execSQL("create Table total (id INT primary key,masuk INT, keluar INT)");
            DB.execSQL("insert into total (id,masuk, keluar)VALUES(1,0,0)");
        }
        @Override
        public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
            DB.execSQL("drop Table if exists Userdetails");
        }
        public Boolean insertuserdata(String name ,String jennis, String jumlah,String note)
        {
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("nama", name);
            contentValues.put("jenis", jennis);
            contentValues.put("jumlah", jumlah);
            contentValues.put("note", note);
            contentValues.put("date",getdate());
            long result=DB.insert("Userdetails", null, contentValues);
            //setTotal(jennis,Integer.parseInt(jumlah));
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }
        public Boolean updateuserdata(String id, String name, String jumlah,String note, String jenis) {
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("nama", name);
            contentValues.put("jumlah", jumlah);
            contentValues.put("note", note);
            contentValues.put("jenis", jenis);
            contentValues.put("date",getdate());
            Cursor cursor = DB.rawQuery("Select rowid,* from Userdetails where rowid = ?", new String[]{id});
            if (cursor.getCount() > 0) {
                long result = DB.update("Userdetails", contentValues, "rowid=?", new String[]{id});
                cursor.close();
                if (result == -1) {
                    return false;

                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
        public Boolean deletedata (String id,int jumlah,String jenis) {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("Select rowid,* from Userdetails where rowid = ?", new String[]{id});
            if (cursor.getCount() > 0) {
                long result = DB.delete("Userdetails", "rowid=?", new String[]{id});
                cursor.close();
                //DB.close();
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }

        public Cursor getdata ()
        {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("SELECT rowid, * from Userdetails", null);
            return cursor;
        }
        @SuppressLint("Range")
        public int getMasuk() {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = null;
            int empName = 0;
            try {
                cursor = DB.rawQuery("SELECT SUM(jumlah) as jumlah FROM Userdetails WHERE jenis=?", new String[]{"+"});
                if(cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    empName = cursor.getInt(cursor.getColumnIndex("jumlah"));
                }
                return empName;
            }finally {
                cursor.close();
            }
        }
        @SuppressLint("Range")
        public int getKeluar() {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursorq = null;
            int empName = 0;
            try {
                cursorq = DB.rawQuery("SELECT SUM(jumlah) as jumlah FROM Userdetails WHERE jenis=?", new String[]{"-"});
                    if(cursorq.getCount() > 0) {
                        cursorq.moveToFirst();
                        empName = cursorq.getInt(cursorq.getColumnIndex("jumlah"));
                }
                return empName;
            }finally {
                cursorq.close();
            }
        }
        public String getdate(){
            String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
            return mydate;
        }
        public void setTotal(String jenis, int jumlah){
            SQLiteDatabase DB = this.getWritableDatabase();
            if(jenis == "+"){
                ContentValues contentValuesTOT = new ContentValues();
                int juml =jumlah+getMasuk();
                contentValuesTOT.put("masuk", juml);
                DB.update("total", contentValuesTOT, "rowid=?", new String[]{"1"});
            }else if(jenis=="-"){
                ContentValues contentValuesTOT = new ContentValues();
                int juml = jumlah+getKeluar();
                contentValuesTOT.put("keluar", juml);
                DB.update("total", contentValuesTOT, "rowid=?", new String[]{"1"});
            DB.close();
            }
        }
    }