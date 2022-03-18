package activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanqx.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ultil.CheckConnection;
import ultil.Server;

public class ThongtinKHActivity extends AppCompatActivity {

    EditText edittenkhachhang, editemail, editsdt, editdiachi;
    Button btnxacnhan, btntrove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_khactivity);
        Anhxa();
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }
    }

    private void EventButton() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edittenkhachhang.getText().toString().trim();
                String sdt = editsdt.getText().toString().trim();
                String email = editemail.getText().toString().trim();
                String diachi = editdiachi.getText().toString().trim();
                //kiểm tra chuỗi khác 0 thì mới đưa dữ liệu lên database
                if(ten.length() > 0 && sdt.length() > 0 && email.length() > 0 && diachi.length() > 0){
                    //đọc và gửi dữ liệu lên cho server
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String madonhang) {
                            Log.d("madonhang",madonhang);
                            if(Integer.parseInt(madonhang) > 0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.Duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1")){
                                            MainActivity.manggiohang.clear();
                                            showDialog_Succees();
                                        }else {
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Đặt hàng bị lỗi!");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for(int i = 0; i < MainActivity.manggiohang.size();i++){
                                            // khởi tạo cho các dữ liệu của các object thực phẩm
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                //những từ khóa trùng với khóa lấy dữ liệu ra trong file php
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("mathucpham",MainActivity.manggiohang.get(i).getIdtp());
                                                jsonObject.put("tenthucpham",MainActivity.manggiohang.get(i).getTentp());
                                                jsonObject.put("giathucpham",MainActivity.manggiohang.get(i).getGiatp());
                                                jsonObject.put("soluongthucpham",MainActivity.manggiohang.get(i).getSoluongtp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap = new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String, String>();
                            // để put dữ liệu lên dùng hasdmap.put và key phải trùng với key bên file php
                            hashMap.put("tenkhachhang",ten);
                            hashMap.put("sodienthoai",sdt);
                            hashMap.put("email",email);
                            hashMap.put("diachi",diachi);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Hãy kiểm tra lại dữ liệu");
                }
            }
        });
    }

    private void showDialog_Succees() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_success, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view);
        Button btn = view.findViewById(R.id.btn_trove);
        AlertDialog dialog = alert.create();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                 startActivity(intent);
                 finish();
                CheckConnection.ShowToast_Short(getApplicationContext(),"Hãy mua sắm tiếp nào <3");
            }
        });
        dialog.show();
    }

    private void Anhxa() {
        edittenkhachhang = findViewById(R.id.edittexttenkhachhang);
        editemail = findViewById(R.id.edittextemail);
        editsdt = findViewById(R.id.edittextsodienthoai);
        editdiachi = findViewById(R.id.edittextdiachi);
        btnxacnhan = findViewById(R.id.buttonxacnhan);
        btntrove = findViewById(R.id.buttontrove);
    }
}