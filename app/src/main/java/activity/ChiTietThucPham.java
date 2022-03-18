package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doanqx.R;

import java.text.DecimalFormat;

import model.Giohang;
import model.Thucpham;

public class ChiTietThucPham extends AppCompatActivity {

    Toolbar toolbarChitiet;
    ImageView imgChitiet;
    TextView txtten,txtgia,txtmota;
    Spinner spinner;
    Button btndatmua;
    int id = 0;
    String TenChitiet = "";
    int GiaChittiet = 0;
    String HinhanhChitiet = "";
    String MotaChitiet = "";
    int Idthucpham = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thuc_pham);
        Anhxa();
        ActionToolbar();
        GetInformation();
        CatchEventSpinner();
        EventButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size() > 0){
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false;
                    for (int i = 0; i < MainActivity.manggiohang.size(); i++){
                        //2 id bằng nhau thì cập nhật số lượng mới
                        if(MainActivity.manggiohang.get(i).getIdtp() == id){
                            MainActivity.manggiohang.get(i).setSoluongtp(MainActivity.manggiohang.get(i).getSoluongtp() + sl);
                            // nếu số lượng vượt quá 10 thì set lại cỡ 10.
                            if(MainActivity.manggiohang.get(i).getSoluongtp() >= 10){
                                MainActivity.manggiohang.get(i).setSoluongtp(10);
                            }
                            MainActivity.manggiohang.get(i).setGiatp(GiaChittiet * MainActivity.manggiohang.get(i).getSoluongtp());
                            exists = true;
                        }
                    }
                    // đi vào vòng lặp mà ko kiếm dc tp trùng id thì ko update data và nó tạo data mới
                    if(exists == false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi = soluong * GiaChittiet;
                        // add giá trị vào trong mảng
                        MainActivity.manggiohang.add(new Giohang(id,TenChitiet,Giamoi,HinhanhChitiet,soluong));
                    }
                }else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * GiaChittiet;
                    MainActivity.manggiohang.add(new Giohang(id,TenChitiet,Giamoi,HinhanhChitiet,soluong));
                }
                Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayadapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayadapter);
    }

    private void GetInformation() {
        Thucpham thucpham = (Thucpham) getIntent().getSerializableExtra("thongtinthucpham");
        id = thucpham.getID();
        TenChitiet = thucpham.getTenthucpham();
        GiaChittiet = thucpham.getGiathucpham();
        HinhanhChitiet = thucpham.getHinhanhthucpham();
        MotaChitiet = thucpham.getMotathucpham();
        Idthucpham = thucpham.getIDThucpham();
        txtten.setText(TenChitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Giá : " + decimalFormat.format(GiaChittiet) + " Đ");
        txtmota.setText(MotaChitiet);
        Glide.with(getApplicationContext()).load(HinhanhChitiet)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.warning)
                .into(imgChitiet);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarChitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarChitiet = (Toolbar) findViewById(R.id.toolbarchitietthucpham);
        imgChitiet = (ImageView) findViewById(R.id.imageviewchitietthucpham);
        txtten = (TextView) findViewById(R.id.textviewtenchitietthucpham);
        txtgia = (TextView) findViewById(R.id.textviewgiachitietthucpham);
        txtmota = (TextView) findViewById(R.id.textviewmotachitietthucpham);
        spinner = (Spinner) findViewById(R.id.spinner);
        btndatmua = (Button) findViewById(R.id.buttondatmua);
    }
}