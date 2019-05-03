package com.example.seniorseminarproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RewardRecyclerViewConfig {
    private Context mContext;
    private RewardRecyclerViewConfig.RewardsAdapter mRewardsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Reward> rewards, List<String> keys){
        mContext = context;
        mRewardsAdapter = new RewardRecyclerViewConfig.RewardsAdapter(rewards, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mRewardsAdapter);
    }

    class RewardItemView extends RecyclerView.ViewHolder{
        private TextView mRewardName;
        private TextView mRewardDescription;
        private TextView mRewardCost;

        private String key;

        public RewardItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.reward_list_item, parent, false));

            mRewardName = (TextView)itemView.findViewById(R.id.rewardNameTV);
            mRewardCost = (TextView)itemView.findViewById(R.id.rewardCostTV);
            mRewardDescription = (TextView)itemView.findViewById(R.id.rewardDescriptionTV);
        }

        public void bind(Reward reward, String key){
            mRewardName.setText(reward.getRewardName());
            mRewardCost.setText(reward.getRewardCost());
            mRewardDescription.setText(reward.getRewardDescription());
            this.key = key;
        }
    }

    class RewardsAdapter extends RecyclerView.Adapter<RewardRecyclerViewConfig.RewardItemView>{
        private List<Reward> mRewardList;
        private List<String> mKeys;

        public RewardsAdapter(List<Reward> mRewardList, List<String> mKeys) {
            this.mRewardList = mRewardList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public RewardRecyclerViewConfig.RewardItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new RewardRecyclerViewConfig.RewardItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull RewardRecyclerViewConfig.RewardItemView rewardItemView, int i) {
            rewardItemView.bind(mRewardList.get(i), mKeys.get(i));
        }

        @Override
        public int getItemCount() {
            return mRewardList.size();
        }
    }
}
