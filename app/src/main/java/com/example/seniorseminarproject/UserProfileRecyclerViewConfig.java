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

public class UserProfileRecyclerViewConfig {
    private Context mContext;
    private UserProfileRecyclerViewConfig.UserProfilesAdapter mUserProfilesAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<User> users, List<String> keys){
        mContext = context;
        mUserProfilesAdapter = new UserProfileRecyclerViewConfig.UserProfilesAdapter(users, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mUserProfilesAdapter);
    }

    class UserProfileItemView extends RecyclerView.ViewHolder{
        private TextView mUsername;
        private TextView mFirstName;
        private TextView mLastName;
        private TextView mTotalPoints;
        private ImageButton mEditProfileButton;

        private String key;

        public UserProfileItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.profile_list_view, parent, false));

            mUsername = (TextView)itemView.findViewById(R.id.profileUsernameTV);
            mFirstName = (TextView)itemView.findViewById(R.id.profileFirstNameTV);
            mLastName = (TextView)itemView.findViewById(R.id.profileLastNameTV);
            mTotalPoints = (TextView)itemView.findViewById(R.id.profileTotalPointsTV);
            mEditProfileButton = (ImageButton) itemView.findViewById(R.id.editProfileButton);

            mEditProfileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, UserEditProfile.class);
                    i.putExtra("key", key);
                    i.putExtra("username", mUsername.getText().toString());
                    i.putExtra("firstName", mFirstName.getText().toString());
                    i.putExtra("lastName", mLastName.getText().toString());
                    i.putExtra("totalPoints", mTotalPoints.getText().toString());

                    mContext.startActivity(i);
                }
            });
        }

        public void bind(User user, String key){
            mUsername.setText(user.getUsername());
            mFirstName.setText(user.getFirstName());
            mLastName.setText(user.getLastName());
            mTotalPoints.setText(user.getPoints());
            this.key = key;
        }
    }

    class UserProfilesAdapter extends RecyclerView.Adapter<UserProfileRecyclerViewConfig.UserProfileItemView>{
        private List<User> mUserList;
        private List<String> mKeys;

        public UserProfilesAdapter(List<User> mUserList, List<String> mKeys){
            this.mUserList = mUserList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public UserProfileRecyclerViewConfig.UserProfileItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
            return new UserProfileRecyclerViewConfig.UserProfileItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull UserProfileRecyclerViewConfig.UserProfileItemView userProfileItemView, int i) {
            userProfileItemView.bind(mUserList.get(i), mKeys.get(i));
        }

        @Override
        public int getItemCount() {
            return mUserList.size();
        }
    }
}
