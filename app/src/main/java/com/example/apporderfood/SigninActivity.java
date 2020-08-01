package com.example.apporderfood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.apporderfood.DAO.NhanVienDAO;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDongYDN;
    Button btnDangKyDN;
    EditText edTenDangNhapDN;
    EditText edMatKhauDN;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signin);

        btnDongYDN = (Button) findViewById(R.id.btnDongYDN);
        btnDangKyDN = (Button) findViewById(R.id.btnDangKyDN);
        edTenDangNhapDN = (EditText) findViewById(R.id.edTenDangNhapDN);
        edMatKhauDN = (EditText) findViewById(R.id.edMatKhauDN);


        btnDongYDN.setOnClickListener(this);
        btnDangKyDN.setOnClickListener(this);
        nhanVienDAO = new NhanVienDAO(this);

        showButtonAgreeOrRegister();
    }

    private void showButtonAgreeOrRegister(){
        boolean check = nhanVienDAO.KiemTraNhanVien();
        Log.d("test", String.valueOf(check));
        if(check){
            btnDangKyDN.setVisibility(View.GONE);
            btnDongYDN.setVisibility(View.VISIBLE);
        }
        else {
            btnDangKyDN.setVisibility(View.VISIBLE);
            btnDongYDN.setVisibility(View.GONE);
        }
    }

    private void btnDongYDN(){
        String eTenDangNhap = edTenDangNhapDN.getText().toString();
        String eMatKhau = edMatKhauDN.getText().toString();
        boolean check = nhanVienDAO.KiemTraDangNhap(eTenDangNhap, eMatKhau);
        if(check){
            Toast.makeText(SigninActivity.this, getResources().getString(R.string.dangnhapthanhcong), Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(SigninActivity.this, getResources().getString(R.string.dangnhapthatbai), Toast.LENGTH_SHORT).show();
        }
    }
    private void btnDangKyDN(){
        Intent intent = new Intent(SigninActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDongYDN:
                btnDongYDN();
                break;
            case R.id.btnDangKyDN:
                btnDangKyDN();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showButtonAgreeOrRegister();
    }
}
