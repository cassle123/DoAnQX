package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doanqx.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import activity.ChiTietThucPham;
import model.Thucpham;
import ultil.CheckConnection;

public class ThucphamAdapter extends RecyclerView.Adapter<ThucphamAdapter.ItemHolder> {
    Context context;
    ArrayList<Thucpham> arraythucpham;

    public ThucphamAdapter(Context context, ArrayList<Thucpham> arraythucpham) {
        this.context = context;
        this.arraythucpham = arraythucpham;
    }

    @NonNull
    @Override
    // giúp khởi tạo lại cái view đã thiết kế layout ở ngoài
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_thucphammoinhat,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    //function hỗ trợ việc get/set những thuộc tính gán lên layout
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Thucpham thucpham = arraythucpham.get(position);
        holder.txttenthucpham.setText(thucpham.getTenthucpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiathucpham.setText("Giá: " + decimalFormat.format(thucpham.getGiathucpham()) + " Đ");
        Glide.with(context).load(thucpham.getHinhanhthucpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.warning)
                .into(holder.imghinhthucpham);
    }

    @Override
    //return ra tất cả giá trị bên trong mảng
    public int getItemCount() {
        return arraythucpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhthucpham;
        public TextView txttenthucpham,txtgiathucpham;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhthucpham = (ImageView) itemView.findViewById(R.id.imageviewthucpham);
            txtgiathucpham = (TextView) itemView.findViewById(R.id.textviewgiathucpham);
            txttenthucpham = (TextView) itemView.findViewById(R.id.textviewtenthucpham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChiTietThucPham.class);
                    intent.putExtra("thongtinthucpham",arraythucpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckConnection.ShowToast_Short(context,arraythucpham.get(getPosition()).getTenthucpham());
                    context.startActivity(intent);
                }
            });
        }
    }
}
