package com.example.seniorseminarproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewConfig {
    private Context mContext;
    private EventsAdapter mEventsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Event> events, List<String> keys){
        mContext = context;
        mEventsAdapter = new EventsAdapter(events, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mEventsAdapter);
    }

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
            mEventName.setText(event.getEventName());
            mEventDate.setText(event.getEventDate());
            mEventTime.setText(event.getEventTime());
            mEventDescription.setText(event.getEventDescription());
            mEventPoints.setText(event.getEventPoints());
            this.key = key;
        }
    }

    class EventsAdapter extends RecyclerView.Adapter<EventItemView>{
        private List<Event> mEventList;
        private List<String> mKeys;

        public EventsAdapter(List<Event> mEventList, List<String> mKeys) {
            this.mEventList = mEventList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public EventItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new EventItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull EventItemView eventItemView, int i) {
            eventItemView.bind(mEventList.get(i), mKeys.get(i));
        }

        @Override
        public int getItemCount() {
            return mEventList.size();
        }
    }
}
