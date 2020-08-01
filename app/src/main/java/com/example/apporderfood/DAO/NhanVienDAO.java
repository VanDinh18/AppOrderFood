package com.example.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apporderfood.DTO.NhanVienDTO;
import com.example.apporderfood.Database.CreateDatabase;

public class NhanVienDAO {

    SQLiteDatabase database;

    public NhanVienDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemNhanVien(NhanVienDTO nhanVienDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_NHANVIEN_TENDN, nhanVienDTO.getTENDN());
        contentValues.put(CreateDatabase.TB_NHANVIEN_CMND, nhanVienDTO.getCMND());
        contentValues.put(CreateDatabase.TB_NHANVIEN_GIOITINH, nhanVienDTO.getGIOITINH());
        contentValues.put(CreateDatabase.TB_NHANVIEN_MATKHAU, nhanVienDTO.getMATKHAU());
        contentValues.put(CreateDatabase.TB_NHANVIEN_NGAYSINH, nhanVienDTO.getNGAYSINH());

        return database.insert(CreateDatabase.TB_NHANVIEN, null, contentValues);
    }

    public boolean KiemTraNhanVien(){
        String sql = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN;
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor.getCount() != 0){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean KiemTraDangNhap(String tenDangNhap, String matKhau){
        String sql = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN + " WHERE " + CreateDatabase.TB_NHANVIEN_TENDN + " = '" + tenDangNhap
                + "' AND " + CreateDatabase.TB_NHANVIEN_MATKHAU + " = '" + matKhau + "'";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor.getCount() != 0){
            return true;
        }
        else {
            return false;
        }
    }
}
