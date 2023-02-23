package com.example.bottom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{
    private List<com.example.bottom.History> mHistoryList;
    private IClickItemHistory mIClickItemHistory;

    public HistoryAdapter(IClickItemHistory iClickItemHistory) {
        this.mIClickItemHistory = iClickItemHistory;
    }

    public interface IClickItemHistory{
        void deleteHistory(com.example.bottom.History history);


    }


    public void setData(List<com.example.bottom.History> list){
        this.mHistoryList = list;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        com.example.bottom.History history = mHistoryList.get(position);
        if (history == null){
            return;
        }
        holder.tvMoney.setText(history.getMoney());
        holder.tvPrice.setText(history.getPrice());
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickItemHistory.deleteHistory(history);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mHistoryList != null){
            return mHistoryList.size();
        }
        return 0;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMoney;
        private TextView tvPrice;
        private ImageButton btn_delete;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMoney = itemView.findViewById(R.id.tv_Money);
            tvPrice = itemView.findViewById(R.id.tv_Price);
            btn_delete = itemView.findViewById(R.id.delete);



        }
    }


}
