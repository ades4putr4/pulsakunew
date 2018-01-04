package com.example.vv.pulsaku;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class dbtcon extends SQLiteOpenHelper {

    static final private String Db_NAME="PulsaKu";
    static final private String ID="_id";
    static final private int Db_VER=8;

    ////deklarasi nama tabel
    static final private String TB_BARANG="Barang";//tabel barang
    static final private String CREATE_TB_BARANG="create table "+TB_BARANG+"(_id integer primary key autoincrement,nama text,stock integer ,harga  integer);"; //tabel barang
    static final private String TB_TRANSAKSI="Transaksi";//tabel transaksi
    static final private String CREATE_TB_TRANSAKSI="create table "+TB_TRANSAKSI+"(_id integer primary key autoincrement,nama text,harga integer,jumlah integer, total integer);";//tabel transaksi

    Context mycontext;
    SQLiteDatabase myDB;


    public dbtcon(Context context, ) {
        super(context, Db_NAME, null, Db_VER);
        mycontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_BARANG);
        db.execSQL(CREATE_TB_TRANSAKSI);
        Log.i("Database","Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TB_BARANG);
        db.execSQL("drop table if exists "+TB_TRANSAKSI);
        onCreate(db);

    }

    public void insertDataBarang(String s0,int s1,int s2){
        myDB=getWritableDatabase();
        myDB.execSQL("insert into "+TB_BARANG+" (nama,harga,stock) values('"+s0+"','"+s1+"','"+s2+"');");
        Toast.makeText(mycontext,"Data Saved",Toast.LENGTH_LONG).show();
    }
    public Cursor readBarang() {
        myDB=getWritableDatabase();
        String[] columns = new String[]{"_id","nama","harga","stock"};
        Cursor c = myDB.query(TB_BARANG, columns, null, null, null, null, ID + " asc");
        if (c != null) {
            c.moveToFirst();
        }
        return c;
}
