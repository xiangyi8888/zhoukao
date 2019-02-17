package com.example.zhoukao1.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhoukao1.R;
import com.example.zhoukao1.bean.ShopBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;


import java.util.List;

public class ShopDataAdapter extends XRecyclerView.Adapter<ShopDataAdapter.HolderView> {
    private List<ShopBean.shopData> list;
    private Context context;
    public ShopDataAdapter(List<ShopBean.shopData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<ShopBean.shopData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopDataAdapter.HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        HolderView holderView = new HolderView(view);
        return holderView;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopDataAdapter.HolderView holder, int position) {
        Uri uri = Uri.parse(list.get(position).masterPic);
        DraweeController controller=Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.item_image.setController(controller);
        holder.itemtv_name.setText(list.get(position).commodityName);
        holder.itemtv_price.setText(list.get(position).price);
    }

    @Override
    public int getItemCount() {
        return list.size()>0?list.size():0;
    }

    public void addData(List<ShopBean.shopData> result) {
        if(result!=null){
            list.addAll(result);
            notifyDataSetChanged();
        }
    }

    public class HolderView extends RecyclerView.ViewHolder {

        private SimpleDraweeView item_image;
        private TextView itemtv_name;
        private TextView itemtv_price;
        public HolderView(View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.item_image);
            itemtv_name = itemView.findViewById(R.id.itemtv_name);
            itemtv_price = itemView.findViewById(R.id.itemtv_price);
        }
    }
}
