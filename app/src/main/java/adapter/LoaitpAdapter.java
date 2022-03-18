package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doanqx.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import model.Loaitp;

public class LoaitpAdapter extends BaseAdapter {
    ArrayList<Loaitp> arrayListloaitp;
    Context context; //truyền vào 1 màn hình để biết dữ liệu dc vẽ trên màn hình nào

    public LoaitpAdapter(ArrayList<Loaitp> arrayListloaitp, Context context) {
        this.arrayListloaitp = arrayListloaitp;
        this.context = context;
    }

    @Override // Viết lại định nghĩa cho những function này
    public int getCount() {
        return arrayListloaitp.size(); //đưa ra hết các giá trị trong mảng
    }

    @Override
    public Object getItem(int i) {
        return arrayListloaitp.get(i); // lấy ra từng thuộc tính trong giá trị mảng
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    //tạo class con không cần load lại dữ liệu nếu đã có dữ liệu đầu thì lần sau chỉ cần insert
    public class ViewHolder{
        TextView txttenloaitp;
        ImageView imgloaitp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaitp,null);
            viewHolder.txttenloaitp = (TextView) view.findViewById(R.id.textviewloaitp);
            viewHolder.imgloaitp = (ImageView) view.findViewById(R.id.imageviewloaitp);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();// run lan 2 da có giá tri thi chi can get lai
        }
        //lay du lieu tu trong mang theo cai khuon
        Loaitp loaitp = (Loaitp) getItem(i);
        //set lai nhung du lieu da co tren mang
        viewHolder.txttenloaitp.setText(loaitp.getTenloaitp());
        //thu doc duong dan hinh anh UIL tu internet
        Glide.with(context).load(loaitp.getHinhanhloaitp())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.warning)
                .into(viewHolder.imgloaitp);
        return view;
    }
}
