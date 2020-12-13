package com.example.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION=1;
    public static final String DB_NAME="reg.db";

    public static final String USER_TABLE="reg";

    public static final String COL_ID="id";
    public static final String COL_EMAIL="email";
    public static final String COL_FELNEV="nev";
    public static final String COL_JELSZO="jelszo";
    public static final String COL_FULLNAME="teljes név";


    public DBHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String sql="CREATE TABLE "+USER_TABLE+"("+
            COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COL_EMAIL+" VARCHAR(255) NOT NULL UNIQUE,"+
            COL_FELNEV+ " VARCHAR(255) NOT NULL UNIQUE,"+
            COL_JELSZO+" VARCHAR(255) NOT NULL,"+
            COL_FULLNAME+"VARCHAR(255) NOT NULL"+
            ")";

    db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="DROP TABLE IF EXISTS "+USER_TABLE;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean adatRogzites(String email,String nev,String jelszo, String fullnev){
       SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_EMAIL,email);
        values.put(COL_FELNEV,nev);
        values.put(COL_JELSZO,jelszo);
        values.put(COL_FULLNAME,fullnev);

        long result=db.insert(USER_TABLE,null,values);
        return result!=-1;
    }



    public Cursor adatLekerdezes(){
      //  String nev="' 1 = 1 -- ";
        SQLiteDatabase db= this.getWritableDatabase();
       // db.rawQuery("SELECT * FROM "+TANULO_TABLE+" WHERE nev =?",new String[]{nev});

        return db.query(USER_TABLE,new String[]{COL_ID,COL_EMAIL,COL_FELNEV,COL_JELSZO,COL_FULLNAME},
                null,null,null,null,null);
    }

    public boolean idEllenoriz(String id){
        SQLiteDatabase db= this.getWritableDatabase();
    Cursor result= db.rawQuery("SELECT COUNT(*) FROM "+USER_TABLE+" WHERE "+COL_ID+" = ?",new String[]{id});
    result.moveToFirst();
    return result.getInt(0)==1;
    }

    public boolean adatModositas(String id,String email,String nev,String jelszo, String fullnev){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(COL_EMAIL,email);
        values.put(COL_FELNEV,nev);
        values.put(COL_JELSZO,jelszo);
        values.put(COL_FULLNAME,fullnev);
     return  db.update(USER_TABLE,values,COL_ID+" = ?",new String[]{id})==1;
    }

    public boolean adatTorles(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
       return db.delete(USER_TABLE,COL_ID+" = ?",new String[]{id})==1;
    }
}
