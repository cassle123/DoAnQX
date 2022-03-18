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

import adapter.DouongAdapter;
import adapter.MigoiAdapter;
import model.Thucpham;
import ultil.CheckConnection;
import ultil.Server;

public class MiGoiActivity extends AppCompatActivity {

    Toolbar toolbarmigoi;
    ListView lvmigoi;
    MigoiAdapter michaophobunAdapter;
    ArrayList<Thucpham> mangmigoi;
    int idmigoi = 0;
    int page = 1;
    View footerview;
    boolean isLoading = false;
    boolean limitadata = false;
    mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_goi);
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadMoreData() {
        lvmigoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ChiTietThucPham.class);
                intent.putExtra("thongtinthucpham",mangmigoi.get(i));
                startActivity(intent);
            }
        });
        lvmigoi.setOnScrollListener(new AbsListView.OnScrollListener() {
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

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.Duongdandongvat + String.valueOf(page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String Tensp = "";
                int Giasp = 0;
                String Hinhanhsp = "";
                String Mota = "";
                int Idtpdsp = 0;
                if(response != null && response.length() != 2){
                    lvmigoi.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        //đi vào và đọc hết tất cả dữ liệu có trong jsonarray
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Tensp = jsonObject.getString("tentp");
                            Giasp = jsonObject.getInt("giatp");
                            Hinhanhsp = jsonObject.getString("hinhanhtp");
                            Mota = jsonObject.getString("motatp");
                            Idtpdsp = jsonObject.getInt("idthucpham");
                            mangmigoi.add(new Thucpham(id,Tensp,Giasp,Hinhanhsp,Mota,Idtpdsp));
                            michaophobunAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    limitadata = true;
                    lvmigoi.removeFooterView(footerview);
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
                param.put("idthucpham",String.valueOf(idmigoi));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarmigoi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarmigoi.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetIdloaitp() {
        idmigoi = getIntent().getIntExtra("idloaithucpham",-1);
    }

    private void Anhxa() {
        toolbarmigoi = (Toolbar) findViewById(R.id.toolbarmigoi);
        lvmigoi = (ListView) findViewById(R.id.listviewmigoi);
        mangmigoi = new ArrayList<>();
        michaophobunAdapter = new MigoiAdapter(getApplicationContext(),mangmigoi);
        lvmigoi.setAdapter(michaophobunAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar,null);
        mHandler = new mHandler();
    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    lvmigoi.addFooterView(footerview);
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