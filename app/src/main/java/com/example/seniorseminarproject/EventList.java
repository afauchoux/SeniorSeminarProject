package com.example.seniorseminarproject;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EventList  extends ArrayAdapter<Event> {
    private Activity context;
    private List<Event> eventList;

    public EventList(Activity context, List<Event> eventList){
        super(context, R.layout.activity_user_schedule, eventList);

        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_user_schedule, null, true);

        TextView eventNameTV = (TextView)listViewItem.findViewById(R.id.eventNameET);
        TextView eventDateTV = (TextView)listViewItem.findViewById(R.id.eventDateET);
        TextView eventTimeTV = (TextView)listViewItem.findViewById(R.id.eventTimeET);
        TextView eventDescriptionTV = (TextView)listViewItem.findViewById(R.id.eventDescriptionTV);
        TextView pointsAwardedTV = (TextView)listViewItem.findViewById(R.id.pointsAwardedTV);

        Event event = eventList.get(position);

        eventNameTV.setText(event.getEventName());
        eventDateTV.setText(event.getEventDate());
        eventTimeTV.setText(event.getEventTime());
        eventDescriptionTV.setText(event.getEventDescription());
        pointsAwardedTV.setText(event.getPointsAwarded());

        return listViewItem;
    }
}
