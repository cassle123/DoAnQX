package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapter.ThucvatAdapter;
import model.Thucpham;
import ultil.CheckConnection;
import ultil.Server;

public class ThucVatActivity extends AppCompatActivity {

    Toolbar toolbarthucvat;
    ListView lvthucvat;
    ThucvatAdapter thucvatAdapter;
    ArrayList<Thucpham> mangthucvat;
    int idthucvat = 0;
    int page = 1;
    View footerview;
    boolean isLoading = false;
    boolean limitadata = false;
    mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_vat);
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            Anhxa();
            GetIdloaitp();
            ActionToolbar();
            GetData(page);
            LoadMoreData();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại internet");
            finish();
        }
    }

    private void GetData(int Page) {
        //phương thức gủi lên server qua biến request
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.Duongdandongvat + String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            //thông qua biến response và trả về 1 chuỗi
            public void onResponse(String response) {
                int id = 0;
                String Tenthucvat = "";
                int Giathucvat = 0;
                String Hinhanhthucvat = "";
                String Motathucvat = "";
                int Idtpthucvat = 0;
                if(response != null && response.length() != 2){
                    lvthucvat.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        //đi vào và đọc hết tất cả dữ liệu có trong jsonarray
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Tenthucvat = jsonObject.getString("tentp");
                            Giathucvat = jsonObject.getInt("giatp");
                            Hinhanhthucvat = jsonObject.getString("hinhanhtp");
                            Motathucvat = jsonObject.getString("motatp");
                            Idtpthucvat = jsonObject.getInt("idthucpham");
                            mangthucvat.add(new Thucpham(id,Tenthucvat,Giathucvat,Hinhanhthucvat,Motathucvat,Idtpthucvat));
                            thucvatAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                    limitadata = true;
                    lvthucvat.removeFooterView(footerview);
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Đã hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //truyền vào hashmap là key(key gửi len server) và các giá trị được gửi lên
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("idthucpham",String.valueOf(idthucvat));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void LoadMoreData() {
        lvthucvat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ChiTietThucPham.class);
                intent.putExtra("thongtinthucpham",mangthucvat.get(i));
                startActivity(intent);
            }
        });
        lvthucvat.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if(FirstItem + VisibleItem == TotalItem && TotalItem != 0 && isLoading == false && limitadata == false){
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
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

    private void ActionToolbar() {
        setSupportActionBar(toolbarthucvat);
        //tao 1 nút home cho trở về
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarthucvat.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetIdloaitp() {
        idthucvat = getIntent().getIntExtra("idloaithucpham",-1);
    }

    private void Anhxa() {
        toolbarthucvat = (Toolbar) findViewById(R.id.toolbarthucvat);
        lvthucvat = (ListView) findViewById(R.id.listviewthucvat);
        mangthucvat = new ArrayList<>();
        thucvatAdapter = new ThucvatAdapter(getApplicationContext(),mangthucvat);
        lvthucvat.setAdapter(thucvatAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar,null);
        mHandler = new mHandler();
    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    lvthucvat.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
}