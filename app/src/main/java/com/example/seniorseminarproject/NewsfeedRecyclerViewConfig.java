package com.example.seniorseminarproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class NewsfeedRecyclerViewConfig {
    private Context mContext;
    private NewsfeedRecyclerViewConfig.NewsfeedsAdapter mNewsfeedsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Newsfeed> newsfeeds, List<String> keys){
        mContext = context;
        mNewsfeedsAdapter = new NewsfeedRecyclerViewConfig.NewsfeedsAdapter(newsfeeds, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mNewsfeedsAdapter);
    }

    class NewsfeedItemView extends RecyclerView.ViewHolder{
        private TextView mNewsfeedEvent;
        private TextView mNewsfeedTime;

        private String key;

        public NewsfeedItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.newsfeed_list_item, parent, false));

            mNewsfeedEvent = (TextView)itemView.findViewById(R.id.newsfeedEventTV);
            mNewsfeedTime = (TextView)itemView.findViewById(R.id.newsfeedTimeTV);

        }

        public void bind(Newsfeed newsfeed, String key){
            mNewsfeedEvent.setText(newsfeed.getNewsfeedEvent());
            mNewsfeedTime.setText(newsfeed.getNewsfeedTime());
            this.key = key;
        }
    }

    class NewsfeedsAdapter extends RecyclerView.Adapter<NewsfeedRecyclerViewConfig.NewsfeedItemView>{
        private List<Newsfeed> mNewsfeedList;
        private List<String> mKeys;

        public NewsfeedsAdapter(List<Newsfeed> mNewsfeedList, List<String> mKeys) {
            this.mNewsfeedList = mNewsfeedList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public NewsfeedRecyclerViewConfig.NewsfeedItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new NewsfeedRecyclerViewConfig.NewsfeedItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsfeedRecyclerViewConfig.NewsfeedItemView newsfeedItemView, int i) {
            newsfeedItemView.bind(mNewsfeedList.get(i), mKeys.get(i));
        }

        @Override
        public int getItemCount() {
            return mNewsfeedList.size();
        }
    }
}
