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

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Thucpham;

public class DouongAdapter extends BaseAdapter {
    Context context;
    ArrayList<Thucpham> arraydouong;

    public DouongAdapter(Context context, ArrayList<Thucpham> arraydouong) {
        this.context = context;
        this.arraydouong = arraydouong;
    }

    @Override
    public int getCount() {
        return arraydouong.size();
    }

    @Override
    public Object getItem(int i) {
        return arraydouong.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttendouong, txtgiadouong, txtmotadouong;
        public ImageView imgdouong;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_douong,null);
            viewHolder.txttendouong = (TextView) view.findViewById(R.id.textviewtendouong);
            viewHolder.txtgiadouong = (TextView) view.findViewById(R.id.textviewgiadouong);
            viewHolder.txtmotadouong = (TextView) view.findViewById(R.id.textviewmotadouong);
            viewHolder.imgdouong = (ImageView) view.findViewById(R.id.imageviewdouong);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //Goi lai khuôn
        Thucpham thucpham = (Thucpham) getItem(i);
        viewHolder.txttendouong.setText(thucpham.getTenthucpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiadouong.setText("Giá: " + decimalFormat.format(thucpham.getGiathucpham()) + " Đ");
        //set so luong dong cho noi dung
        viewHolder.txtmotadouong.setMaxLines(2);
        viewHolder.txtmotadouong.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotadouong.setText(thucpham.getMotathucpham());
        Glide.with(context).load(thucpham.getHinhanhthucpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.warning)
                .into(viewHolder.imgdouong);
        return view;
    }
}
