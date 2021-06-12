package com.example.musicplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicplayer.R;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{

    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalculatedByHeight;


    public MusicListAdapter(Context context,RecyclerView recyclerView){
        mContext=context;
        mRv= recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mItemView=LayoutInflater.from(mContext).inflate(R.layout.item_list_music,parent,false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setRecyclerViewHeight();
        Glide.with(mContext)
                .load("https://img9.doubanio.com/view/group_topic/raw/public/p250046415.jpg")
                .into(holder.ivIcon);

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    /**
     * 1.获取ItemView的高度
     * 2，获得ItemView的数量
     * 3.高度*数量=RecyclerView高度
     */
    private void setRecyclerViewHeight(){
        if (isCalculatedByHeight||mRv==null){
            return;
        }

        isCalculatedByHeight=true;

        RecyclerView.LayoutParams itemViewLp= (RecyclerView.LayoutParams) mItemView.getLayoutParams();
        int itemCount = getItemCount();
        int recyclerViewHeight = itemViewLp.height * itemCount;
        LinearLayout.LayoutParams rvlp = (LinearLayout.LayoutParams) mRv.getLayoutParams();
        rvlp.height=recyclerViewHeight;
        mRv.setLayoutParams(rvlp);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivIcon=itemView.findViewById(R.id.iv_icon);
        }
    }
}
