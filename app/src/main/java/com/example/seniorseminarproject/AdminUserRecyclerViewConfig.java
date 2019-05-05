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

public class AdminUserRecyclerViewConfig {
    private Context mContext;
    private AdminUserRecyclerViewConfig.AdminUsersAdapter mAdminUsersAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<User> users, List<String> keys){
        mContext = context;
        mAdminUsersAdapter = new AdminUserRecyclerViewConfig.AdminUsersAdapter(users, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAdminUsersAdapter);
    }

    class AdminUserItemView extends RecyclerView.ViewHolder{
        private TextView mAdminUsername;
        private TextView mAdminFirstName;
        private TextView mAdminLastName;
        private TextView mAdminTotalPoints;
        private ImageButton mAdminEditUser;

        private String key;

        public AdminUserItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.admin_user_list_item, parent, false));

            mAdminUsername = (TextView)itemView.findViewById(R.id.adminUsernameTV);
            mAdminFirstName = (TextView)itemView.findViewById(R.id.adminFirstNameTV);
            mAdminLastName = (TextView)itemView.findViewById(R.id.adminLastNameTv);
            mAdminTotalPoints = (TextView)itemView.findViewById(R.id.adminTotalPointsTV);
            mAdminEditUser = (ImageButton) itemView.findViewById(R.id.adminEditUserButton);

            mAdminEditUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, AdminEditUser.class);
                    i.putExtra("key", key);
                    i.putExtra("username", mAdminUsername.getText().toString());
                    i.putExtra("firstName", mAdminFirstName.getText().toString());
                    i.putExtra("lastName", mAdminLastName.getText().toString());
                    i.putExtra("totalPoints", mAdminTotalPoints.getText().toString());

                    mContext.startActivity(i);
                }
            });
        }

        public void bind(User user, String key){
            mAdminUsername.setText(user.getUsername());
            mAdminFirstName.setText(user.getFirstName());
            mAdminLastName.setText(user.getLastName());
            mAdminTotalPoints.setText(user.getPoints());
            this.key = key;
        }
    }

    class AdminUsersAdapter extends RecyclerView.Adapter<AdminUserRecyclerViewConfig.AdminUserItemView>{
        private List<User> mUserList;
        private List<String> mKeys;

        public AdminUsersAdapter(List<User> mUserList, List<String> mKeys){
            this.mUserList = mUserList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public AdminUserRecyclerViewConfig.AdminUserItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
            return new AdminUserRecyclerViewConfig.AdminUserItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull AdminUserRecyclerViewConfig.AdminUserItemView adminUserItemView, int i) {
            adminUserItemView.bind(mUserList.get(i), mKeys.get(i));
        }

        @Override
        public int getItemCount() {
            return mUserList.size();
        }
    }
}
