package com.example.seniorseminarproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewConfig {
    private Context mContext;

    class EventItemView extends RecyclerView.ViewHolder{
        private TextView mEventName;
        private TextView mEventDate;
        private TextView mEventTime;
        private TextView mEventDescription;
        private TextView mEventPoints;

        private String key;

        public EventItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.event_list_item, parent, false));

            mEventName = (TextView)itemView.findViewById(R.id.eventNameTV);
            mEventDate = (TextView)itemView.findViewById(R.id.eventDateTV);
            mEventTime = (TextView)itemView.findViewById(R.id.eventTimeTV);
            mEventDescription = (TextView)itemView.findViewById(R.id.eventDescriptionTV);
            mEventPoints = (TextView)itemView.findViewById(R.id.eventPointsTV);
        }

        public void bind(Event event, String key){
            //mEventName.getText(event.getEventName());

        }
    }
}
