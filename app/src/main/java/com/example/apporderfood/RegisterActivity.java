package com.example.apporderfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.apporderfood.DAO.NhanVienDAO;
import com.example.apporderfood.DTO.NhanVienDTO;
import com.example.apporderfood.FragmentApp.DatePickerFragment;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener{

    EditText edTenDangNhapDK, edMatKhauDK, edNgaySinhDK, edCMNDDK;
    Button btnDongYDK, btnThoatDK;
    RadioGroup rgGioiTinh;
    String sGioiTinh;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        edTenDangNhapDK = (EditText) findViewById(R.id.edTenDangNhapDK);
        edMatKhauDK = (EditText) findViewById(R.id.edMatKhauDK);
        edNgaySinhDK = (EditText) findViewById(R.id.edNgaySinhDK);
        edCMNDDK = (EditText) findViewById(R.id.edCMNDDK);
        btnDongYDK = (Button) findViewById(R.id.btnDongYDK);
        btnThoatDK = (Button) findViewById(R.id.btnThoatDK);
        rgGioiTinh = (RadioGroup) findViewById(R.id.rgGioiTinh);

        btnDongYDK.setOnClickListener(this);
        btnThoatDK.setOnClickListener(this);

        edNgaySinhDK.setOnFocusChangeListener(this);
        nhanVienDAO = new NhanVienDAO(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDongYDK:
                String sTenDangNhap = edTenDangNhapDK.getText().toString();
                String sMatkhau = edMatKhauDK.getText().toString();
                switch (rgGioiTinh.getCheckedRadioButtonId()){
                    case R.id.rdNam:
                        sGioiTinh = "Nam";
                        break;
                    case R.id.rdNu:
                        sGioiTinh = "Nữ";
                        break;
                }
                String sNgaySinh = edNgaySinhDK.getText().toString();
                int sCMND = Integer.parseInt(edCMNDDK.getText().toString());

                if(sTenDangNhap == null || sTenDangNhap.equals("")){
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.baoloitendangnhap), Toast.LENGTH_SHORT).show();
                }
                else  if(sMatkhau == null || sMatkhau.equals("")){
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.baoloimatkhau), Toast.LENGTH_SHORT).show();
                }
                else {
                    NhanVienDTO  nhanVienDTO = new NhanVienDTO();
                    nhanVienDTO.setTENDN(sTenDangNhap);
                    nhanVienDTO.setMATKHAU(sMatkhau);
                    nhanVienDTO.setGIOITINH(sGioiTinh);
                    nhanVienDTO.setNGAYSINH(sNgaySinh);
                    nhanVienDTO.setCMND(sCMND);

                    long check = nhanVienDAO.ThemNhanVien(nhanVienDTO);
                    if(check != 0){
                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btnThoatDK:
                finish();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id){
            case R.id.edNgaySinhDK:
                if(hasFocus){
                    //popup ngay sinh
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getSupportFragmentManager(), "Ngay sinh");
                }
                break;
        }
    }
}