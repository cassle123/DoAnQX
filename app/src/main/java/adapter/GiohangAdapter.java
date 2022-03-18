package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doanqx.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import activity.GiohangActivity;
import activity.MainActivity;
import model.Giohang;

public class GiohangAdapter extends BaseAdapter {

    Context context;
    ArrayList<Giohang> arraygiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttengiohang,txtgiagiohang;
        public ImageView imggiohang;
        public Button btnminus, btnvalues, btnplus;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //gán dữ liệu cho view trước.
            view = inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txttengiohang = (TextView) view.findViewById(R.id.textviewtengiohang);
            viewHolder.txtgiagiohang = (TextView) view.findViewById(R.id.textviewgiagiohang);
            viewHolder.imggiohang = (ImageView) view.findViewById(R.id.imageviewgiohang);
            viewHolder.btnminus = (Button) view.findViewById(R.id.buttonminus);
            viewHolder.btnvalues = (Button) view.findViewById(R.id.buttonvalues);
            viewHolder.btnplus = (Button) view.findViewById(R.id.buttonplus);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Giohang giohang = (Giohang) getItem(i);
        viewHolder.txttengiohang.setText(giohang.getTentp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGiatp()) + " Đ");
        Glide.with(context).load(giohang.getHinhtp())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.warning)
                .into(viewHolder.imggiohang);
        viewHolder.btnvalues.setText(giohang.getSoluongtp() + "");
        //lấy giá trị sl
        int sl = Integer.parseInt(viewHolder.btnvalues.getText().toString());
        if(sl >= 10){
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }else if(sl <= 1){
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        }else if(sl >= 1){
            viewHolder.btnminus.setVisibility(View.VISIBLE);
            viewHolder.btnplus.setVisibility(View.VISIBLE);
        }
        ViewHolder abc = viewHolder;
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(abc.btnvalues.getText().toString()) + 1;
                int slht = MainActivity.manggiohang.get(i).getSoluongtp();
                long giaht = MainActivity.manggiohang.get(i).getGiatp();
                //gán sl mới nhất vào mảng
                MainActivity.manggiohang.get(i).setSoluongtp(slmoinhat);
                long giamoinhat = (giaht * slmoinhat) / slht;
                //gán giá moi nhat vao mang
                MainActivity.manggiohang.get(i).setGiatp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                abc.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + " Đ");
                //gọi lại noi chua function evenutil
                GiohangActivity.EventUltil();
                if(slmoinhat > 9){
                    abc.btnplus.setVisibility(View.INVISIBLE);
                    abc.btnminus.setVisibility(View.VISIBLE);
                    abc.btnvalues.setText(String.valueOf(slmoinhat));
                }else {
                    abc.btnminus.setVisibility(View.VISIBLE);
                    abc.btnplus.setVisibility(View.VISIBLE);
                    abc.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(abc.btnvalues.getText().toString()) - 1;
                int slht = MainActivity.manggiohang.get(i).getSoluongtp();
                long giaht = MainActivity.manggiohang.get(i).getGiatp();
                MainActivity.manggiohang.get(i).setSoluongtp(slmoinhat);
                long giamoinhat = (giaht * slmoinhat) / slht;
                MainActivity.manggiohang.get(i).setGiatp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                abc.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + " Đ");
                GiohangActivity.EventUltil();
                if(slmoinhat < 2){
                    abc.btnminus.setVisibility(View.INVISIBLE);
                    abc.btnplus.setVisibility(View.VISIBLE);
                    abc.btnvalues.setText(String.valueOf(slmoinhat));
                }else {
                    abc.btnminus.setVisibility(View.VISIBLE);
                    abc.btnplus.setVisibility(View.VISIBLE);
                    abc.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return view;
    }
}
