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

public class SuauongAdapter extends BaseAdapter {
    Context context;
    ArrayList<Thucpham> arraysuauong;

    public SuauongAdapter(Context context, ArrayList<Thucpham> arraysuauong) {
        this.context = context;
        this.arraysuauong = arraysuauong;
    }

    @Override
    public int getCount() {
        return arraysuauong.size();
    }

    @Override
    public Object getItem(int i) {
        return arraysuauong.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttensua, txtgiasua, txtmotasua;
        public ImageView imgsua;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_suauong,null);
            viewHolder.txttensua = (TextView) view.findViewById(R.id.textviewtensua);
            viewHolder.txtgiasua = (TextView) view.findViewById(R.id.textviewgiasua);
            viewHolder.txtmotasua = (TextView) view.findViewById(R.id.textviewmotasua);
            viewHolder.imgsua = (ImageView) view.findViewById(R.id.imageviewsuauong);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //Goi lai khuôn
        Thucpham thucpham = (Thucpham) getItem(i);
        viewHolder.txttensua.setText(thucpham.getTenthucpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiasua.setText("Giá: " + decimalFormat.format(thucpham.getGiathucpham()) + " Đ");
        //set so luong dong cho noi dung
        viewHolder.txtmotasua.setMaxLines(2);
        viewHolder.txtmotasua.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotasua.setText(thucpham.getMotathucpham());
        Glide.with(context).load(thucpham.getHinhanhthucpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.warning)
                .into(viewHolder.imgsua);
        return view;
    }

}
