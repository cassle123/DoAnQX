package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.doanqx.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.LoaitpAdapter;
import adapter.ThucphamAdapter;
import model.Giohang;
import model.Loaitp;
import model.Thucpham;
import ultil.CheckConnection;
import ultil.Server;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<Loaitp> mangloaitp;
    LoaitpAdapter loaitpAdapter;
    //tạo biến toàn cục
    int id = 0;
    String tenloaitp = "";
    String hinhanhloaitp = "";
    ArrayList<Thucpham> mangthucpham;
    ThucphamAdapter thucphamAdapter;
    //muốn gọi ra trong tất cả các màn hình khác dùng public static
    public static ArrayList<Giohang> manggiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaiTP();
            GetDuLieuTPMoiNhat();
            CatchOnItemListView();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
            finish();
        }
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

    private void CatchOnItemListView() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent (MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent (MainActivity.this,DongVatActivity.class);
                            intent.putExtra("idloaithucpham",mangloaitp.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent (MainActivity.this,ThucVatActivity.class);
                            intent.putExtra("idloaithucpham",mangloaitp.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent (MainActivity.this,ThucPhamDongHopActivity.class);
                            intent.putExtra("idloaithucpham",mangloaitp.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent (MainActivity.this,SuaUongActivity.class);
                            intent.putExtra("idloaithucpham",mangloaitp.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent (MainActivity.this,DoUongActivity.class);
                            intent.putExtra("idloaithucpham",mangloaitp.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 6:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent (MainActivity.this,MiGoiActivity.class);
                            intent.putExtra("idloaithucpham",mangloaitp.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 7:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent (MainActivity.this,BanhKeoActivity.class);
                            intent.putExtra("idloaithucpham",mangloaitp.get(i).getId());
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 8:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent (MainActivity.this,LienHeActivity.class);
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 9:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent (MainActivity.this,ThongTinActivity.class);
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void GetDuLieuTPMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdanthucphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int ID = 0;
                    String Tenthucpham = "";
                    Integer Giathucpham = 0;
                    String Hinhanhthucpham = "";
                    String Motathucpham = "";
                    int IDThucpham = 0;
                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            Tenthucpham = jsonObject.getString("tentp");
                            Giathucpham = jsonObject.getInt("giatp");
                            Hinhanhthucpham = jsonObject.getString("hinhanhtp");
                            Motathucpham = jsonObject.getString("motatp");
                            IDThucpham = jsonObject.getInt("idthucpham");
                            mangthucpham.add(new Thucpham(ID,Tenthucpham,Giathucpham,Hinhanhthucpham,Motathucpham,IDThucpham));
                            thucphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDuLieuLoaiTP() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdanloaitp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {//bien json tra ve co gia tri
                if(response != null)//co thì thuc hien viec doc du lieu
                {
                    for (int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);//truyen vao vi tri index là bien i
                            //lay du lieu ra
                            id = jsonObject.getInt("id");
                            tenloaitp = jsonObject.getString("tenloaitp");
                            hinhanhloaitp = jsonObject.getString("hinhanhloaitp");
                            //co du dieu va dua vao mang
                            mangloaitp.add(new Loaitp(id,tenloaitp,hinhanhloaitp));
                            //update lai ban ve
                            loaitpAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaitp.add(8,new Loaitp(0,"Liên Hệ", "https://image.flaticon.com/icons/png/512/4726/4726140.png"));
                    mangloaitp.add(9,new Loaitp(0,"Thông Tin", "https://image.flaticon.com/icons/png/512/4727/4727424.png"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>(); //tao mang và cấp phát vùng bộ nhớ
        mangquangcao.add("https://cdn.tgdd.vn/2021/01/content/3-800x500-15.jpg");
        mangquangcao.add("https://airasiacargo.vn/wp-content/uploads/2020/12/Gui-thuc-pham-di-Hong-Kong-tai-Can-Tho.jpg");
        mangquangcao.add("https://vcdn-suckhoe.vnecdn.net/2021/07/26/khai-niem-thuc-pham-ban-1024x6-4506-3603-1627296783.jpg");
        mangquangcao.add("https://files.climatenewsnetwork.net/wp-content/uploads/2019/01/09162656/more-vegetables-less-meat-e1568042847433.jpg");
        mangquangcao.add("https://www.indoindians.com/wp-content/uploads/2017/06/paleo-diet.jpg");
        mangquangcao.add("https://wwwextendaretail.cdn.triggerfish.cloud/uploads/2019/03/fresh-food-management-header_w1000.jpg");
        mangquangcao.add("https://www.bigbasketco.com/wp-content/uploads/good-l-corp-what-americas-best-grocery-stores-have-in-common-1024x768.jpg");
        for(int i = 0; i < mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            //Thuộc tính canh vừa imageview với viewflipper mà ko bị cắt hình ảnh
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        //bắt sự kiên viewflipper tự chạy
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        //Gọi lại cho viewflipper
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {
        toolbar = (Toolbar)  findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewlipper);
        recyclerViewmanhinhchinh = (RecyclerView) findViewById(R.id.recyclerview);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        listViewmanhinhchinh = (ListView) findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mangloaitp = new ArrayList<>();
        mangloaitp.add(0, new Loaitp(0,"Trang Chính","https://image.flaticon.com/icons/png/512/2981/2981297.png"));
        //Bản vẽ
        loaitpAdapter = new LoaitpAdapter(mangloaitp,getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaitpAdapter);
        //lay du lieu thuc pham
        mangthucpham = new ArrayList<>();
        thucphamAdapter = new ThucphamAdapter(getApplicationContext(),mangthucpham);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewmanhinhchinh.setAdapter(thucphamAdapter);
        if(manggiohang != null){

        }else {
            manggiohang = new ArrayList<>();
        }
    }
}