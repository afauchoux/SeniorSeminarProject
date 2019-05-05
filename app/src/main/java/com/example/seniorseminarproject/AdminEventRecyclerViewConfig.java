package com.example.seniorseminarproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class AdminEventRecyclerViewConfig {
    private Context mContext;
    private AdminEventsAdapter mAdminEventsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Event> events, List<String> keys){
        mContext = context;
        mAdminEventsAdapter = new AdminEventsAdapter(events, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAdminEventsAdapter);
    }

    class AdminEventItemView extends RecyclerView.ViewHolder{
        private TextView mAdminEventName;
        private TextView mAdminEventDate;
        private TextView mAdminEventTime;
        private TextView mAdminEventLocation;
        private TextView mAdminEventDescription;
        private TextView mAdminEventPoints;
        private ImageButton mAdminEditEvent;

        private String key;

        public AdminEventItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.admin_event_list_item, parent, false));

            mAdminEventName = (TextView)itemView.findViewById(R.id.adminEventNameTV);
            mAdminEventDate = (TextView)itemView.findViewById(R.id.adminEventDateTV);
            mAdminEventTime = (TextView)itemView.findViewById(R.id.adminEventTimeTV);
            mAdminEventLocation = (TextView)itemView.findViewById(R.id.adminEventLocationTV);
            mAdminEventDescription = (TextView)itemView.findViewById(R.id.adminEventDescriptionTV);
            mAdminEventPoints = (TextView)itemView.findViewById(R.id.adminEventPointsTV);
            mAdminEditEvent = (ImageButton) itemView.findViewById(R.id.adminEditEventButton);

            mAdminEditEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, AdminEditEvent.class);
                    i.putExtra("key", key);
                    i.putExtra("eventName", mAdminEventName.getText().toString());
                    i.putExtra("eventDate", mAdminEventDate.getText().toString());
                    i.putExtra("eventTime", mAdminEventTime.getText().toString());
                    i.putExtra("eventLocation", mAdminEventLocation.getText().toString());
                    i.putExtra("eventDescription", mAdminEventDescription.getText().toString());
                    i.putExtra("eventPoints", mAdminEventPoints.getText().toString());

                    mContext.startActivity(i);
                }
            });
        }

        public void bind(Event event, String key){
            mAdminEventName.setText(event.getEventName());
            mAdminEventDate.setText(event.getEventDate());
            mAdminEventTime.setText(event.getEventTime());
            mAdminEventLocation.setText(event.getEventLocation());
            mAdminEventDescription.setText(event.getEventDescription());
            mAdminEventPoints.setText(event.getEventPoints());
            this.key = key;
        }
    }

    class AdminEventsAdapter extends RecyclerView.Adapter<AdminEventItemView>{
        private List<Event> mEventList;
        private List<String> mKeys;

        public AdminEventsAdapter(List<Event> mEventList, List<String> mKeys){
            this.mEventList = mEventList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public AdminEventItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
            return new AdminEventItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull AdminEventRecyclerViewConfig.AdminEventItemView adminEventItemView, int i) {
            adminEventItemView.bind(mEventList.get(i), mKeys.get(i));
        }

        @Override
        public int getItemCount() {
            return mEventList.size();
        }
    }

}
