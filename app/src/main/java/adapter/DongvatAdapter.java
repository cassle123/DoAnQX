package adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doanqx.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Thucpham;

public class DongvatAdapter extends BaseAdapter {
    Context context;
    ArrayList<Thucpham> arraydongvat;

    public DongvatAdapter(Context context, ArrayList<Thucpham> arraydongvat) {
        this.context = context;
        this.arraydongvat = arraydongvat;
    }

    @Override
    public int getCount() {
        return arraydongvat.size();
    }

    @Override
    public Object getItem(int i) {
        return arraydongvat.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttendongvat, txtgiadongvat, txtmotadongvat;
        public ImageView imgdongvat;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_dongvat,null);
            viewHolder.txttendongvat = (TextView) view.findViewById(R.id.textviewdongvat);
            viewHolder.txtgiadongvat = (TextView) view.findViewById(R.id.textviewgiadongvat);
            viewHolder.txtmotadongvat = (TextView) view.findViewById(R.id.textviewmotadongvat);
            viewHolder.imgdongvat = (ImageView) view.findViewById(R.id.imageviewdongvat);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //Goi lai khuôn
        Thucpham thucpham = (Thucpham) getItem(i);
        viewHolder.txttendongvat.setText(thucpham.getTenthucpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiadongvat.setText("Giá: " + decimalFormat.format(thucpham.getGiathucpham()) + " Đ");
        //set so luong dong cho noi dung
        viewHolder.txtmotadongvat.setMaxLines(2);
        viewHolder.txtmotadongvat.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotadongvat.setText(thucpham.getMotathucpham());
        Glide.with(context).load(thucpham.getHinhanhthucpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.warning)
                .into(viewHolder.imgdongvat);
        return view;
    }
}
