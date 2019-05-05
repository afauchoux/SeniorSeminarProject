package com.example.seniorseminarproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class AdminRewardRecyclerViewConfig {
    private Context mContext;
    private AdminRewardRecyclerViewConfig.AdminRewardsAdapter mAdminRewardsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Reward> rewards, List<String> keys){
        mContext = context;
        mAdminRewardsAdapter = new AdminRewardRecyclerViewConfig.AdminRewardsAdapter(rewards, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAdminRewardsAdapter);
    }

    class AdminRewardItemView extends RecyclerView.ViewHolder{
        private TextView mAdminRewardName;
        private TextView mAdminRewardCost;
        private TextView mAdminRewardDescription;
        private ImageButton mAdminEditReward;

        private String key;

        public AdminRewardItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.admin_reward_list_item, parent, false));

            mAdminRewardName = (TextView)itemView.findViewById(R.id.adminRewardNameTV);
            mAdminRewardCost = (TextView)itemView.findViewById(R.id.adminRewardCostTV);
            mAdminRewardDescription = (TextView)itemView.findViewById(R.id.adminRewardDescriptionTV);
            mAdminEditReward = (ImageButton) itemView.findViewById(R.id.adminEditRewardButton);

            mAdminEditReward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, AdminEditReward.class);
                    i.putExtra("key", key);
                    i.putExtra("rewardName", mAdminRewardName.getText().toString());
                    i.putExtra("rewardCost", mAdminRewardCost.getText().toString());
                    i.putExtra("rewardDescription", mAdminRewardDescription.getText().toString());

                    mContext.startActivity(i);
                }
            });
        }

        public void bind(Reward reward, String key){
            mAdminRewardName.setText(reward.getRewardName());
            mAdminRewardCost.setText(reward.getRewardCost());
            mAdminRewardDescription.setText(reward.getRewardDescription());
            this.key = key;
        }
    }

    class AdminRewardsAdapter extends RecyclerView.Adapter<AdminRewardRecyclerViewConfig.AdminRewardItemView>{
        private List<Reward> mRewardList;
        private List<String> mKeys;

        public AdminRewardsAdapter(List<Reward> mRewardList, List<String> mKeys){
            this.mRewardList = mRewardList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public AdminRewardRecyclerViewConfig.AdminRewardItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
            return new AdminRewardRecyclerViewConfig.AdminRewardItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull AdminRewardRecyclerViewConfig.AdminRewardItemView adminRewardItemView, int i) {
            adminRewardItemView.bind(mRewardList.get(i), mKeys.get(i));
        }

        @Override
        public int getItemCount() {
            return mRewardList.size();
        }
    }
}
