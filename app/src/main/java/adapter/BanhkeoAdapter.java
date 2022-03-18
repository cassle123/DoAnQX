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

public class BanhkeoAdapter extends BaseAdapter{
    Context context;
    ArrayList<Thucpham> arraybanhkeo;

    public BanhkeoAdapter(Context context, ArrayList<Thucpham> arraybanhkeo) {
        this.context = context;
        this.arraybanhkeo = arraybanhkeo;
    }

    @Override
    public int getCount() {
        return arraybanhkeo.size();
    }

    @Override
    public Object getItem(int i) {
        return arraybanhkeo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttenbanhkeo, txtgiabanhkeo, txtmotabanhkeo;
        public ImageView imgbanhkeo;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_banhkeo,null);
            viewHolder.txttenbanhkeo = (TextView) view.findViewById(R.id.textviewtenbanhkeo);
            viewHolder.txtgiabanhkeo = (TextView) view.findViewById(R.id.textviewgiabanhkeo);
            viewHolder.txtmotabanhkeo = (TextView) view.findViewById(R.id.textviewmotabanhkeo);
            viewHolder.imgbanhkeo = (ImageView) view.findViewById(R.id.imageviewbanhkeo);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //Goi lai khuôn
        Thucpham thucpham = (Thucpham) getItem(i);
        viewHolder.txttenbanhkeo.setText(thucpham.getTenthucpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiabanhkeo.setText("Giá: " + decimalFormat.format(thucpham.getGiathucpham()) + " Đ");
        //set so luong dong cho noi dung
        viewHolder.txtmotabanhkeo.setMaxLines(2);
        viewHolder.txtmotabanhkeo.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotabanhkeo.setText(thucpham.getMotathucpham());
        Glide.with(context).load(thucpham.getHinhanhthucpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.warning)
                .into(viewHolder.imgbanhkeo);
        return view;
    }
}
