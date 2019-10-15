package com.id.socketio;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends ArrayAdapter<MessageFormat> {

    private static final String TAG = "Tapu";
    public MessageAdapter(Context context, int resource, List<MessageFormat> objects) {
        super(context, resource, objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public MessageFormat getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(MainActivity.TAG, "getView:");

        MessageFormat message = getItem(position);

        if(TextUtils.isEmpty(message.getMessage())){


            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.user_connected, parent, false);

            TextView messageText = convertView.findViewById(R.id.message_body);

            Log.i(MainActivity.TAG, "getView: is empty ");
            String userConnected = message.getUsername();
            messageText.setText(userConnected);

        }else if(message.getUniqueId().equals(MainActivity.uniqueId)){
            Log.i(MainActivity.TAG, "getView: " + message.getUniqueId() + " " + MainActivity.uniqueId);


            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.my_message, parent, false);
            TextView messageText = convertView.findViewById(R.id.message_body);
            messageText.setText(message.getMessage());

        }else {
            Log.i(MainActivity.TAG, "getView: is not empty");

            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.their_message, parent, false);

            TextView messageText = convertView.findViewById(R.id.message_body);
            TextView usernameText = (TextView) convertView.findViewById(R.id.name);

            messageText.setVisibility(View.VISIBLE);
            usernameText.setVisibility(View.VISIBLE);

            messageText.setText(message.getMessage());
            usernameText.setText(message.getUsername());

            MainActivity.marray.add(new MessageFormat(message.getUniqueId(), message.getUsername() ,message.getMessage()));


            Log.d(TAG, message.getMessage());

        }

        return convertView;
    }

//    public ArrayList<String> getRemoteMessage(){
//        ArrayList <String> messageList = new ArrayList<>();
//        for (int i =0; i<getCount();i++){
//            messageList.add(getItem(i).getMessage());
//        }
//        return messageList;
//    }
}
