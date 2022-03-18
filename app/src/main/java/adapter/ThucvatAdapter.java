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

public class ThucvatAdapter extends BaseAdapter {
    Context context;
    ArrayList<Thucpham> arrayThucvat;

    public ThucvatAdapter(Context context, ArrayList<Thucpham> arrayThucvat) {
        this.context = context;
        this.arrayThucvat = arrayThucvat;
    }

    @Override
    public int getCount() {
        return arrayThucvat.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayThucvat.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenthucvat,txtgiathucvat,txtmotathucvat;
        public ImageView imgthucvat;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_thucvat,null);
            viewHolder.txttenthucvat = (TextView) view.findViewById(R.id.textviewtenthucvat);
            viewHolder.txtgiathucvat = (TextView) view.findViewById(R.id.textviewgiathucvat);
            viewHolder.txtmotathucvat = (TextView) view.findViewById(R.id.textviewmotathucvat);
            viewHolder.imgthucvat = (ImageView) view.findViewById(R.id.imageviewthucvat);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //Goi lai khuôn
        Thucpham thucpham = (Thucpham) getItem(i);
        viewHolder.txttenthucvat.setText(thucpham.getTenthucpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiathucvat.setText("Giá: " + decimalFormat.format(thucpham.getGiathucpham()) + " Đ");
        //set so luong dong cho noi dung
        viewHolder.txtmotathucvat.setMaxLines(2);
        viewHolder.txtmotathucvat.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotathucvat.setText(thucpham.getMotathucpham());
        Glide.with(context).load(thucpham.getHinhanhthucpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.warning)
                .into(viewHolder.imgthucvat);
        return view;
    }
}
