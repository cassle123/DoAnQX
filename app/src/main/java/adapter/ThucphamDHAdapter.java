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

public class ThucphamDHAdapter extends BaseAdapter {
    Context context;
    ArrayList<Thucpham> arrayThucphamdonghop;

    public ThucphamDHAdapter(Context context, ArrayList<Thucpham> arrayThucphamdonghop) {
        this.context = context;
        this.arrayThucphamdonghop = arrayThucphamdonghop;
    }

    @Override
    public int getCount() {
        return arrayThucphamdonghop.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayThucphamdonghop.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenthucphamdonghop,txtgiathucphamdonghop,txtmotathucphamdonghop;
        public ImageView imgthucphamdonghop;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_thucphamdonghop,null);
            viewHolder.txttenthucphamdonghop = (TextView) view.findViewById(R.id.textviewtenthucphamdonghop);
            viewHolder.txtgiathucphamdonghop = (TextView) view.findViewById(R.id.textviewgiathucphamdonghop);
            viewHolder.txtmotathucphamdonghop = (TextView) view.findViewById(R.id.textviewmotathucphamdonghop);
            viewHolder.imgthucphamdonghop = (ImageView) view.findViewById(R.id.imageviewthucphamdonghop);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //Goi lai khuôn
        Thucpham thucpham = (Thucpham) getItem(i);
        viewHolder.txttenthucphamdonghop.setText(thucpham.getTenthucpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiathucphamdonghop.setText("Giá: " + decimalFormat.format(thucpham.getGiathucpham()) + " Đ");
        //set so luong dong cho noi dung
        viewHolder.txtmotathucphamdonghop.setMaxLines(2);
        viewHolder.txtmotathucphamdonghop.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotathucphamdonghop.setText(thucpham.getMotathucpham());
        Glide.with(context).load(thucpham.getHinhanhthucpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.warning)
                .into(viewHolder.imgthucphamdonghop);
        return view;
    }
}
