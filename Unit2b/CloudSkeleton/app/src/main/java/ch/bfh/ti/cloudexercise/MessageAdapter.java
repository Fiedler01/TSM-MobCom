package ch.bfh.ti.cloudexercise;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

class MessageAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final List<KeyMessagePair> list;

    MessageAdapter(Context context, List<KeyMessagePair> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public KeyMessagePair getItem(int position) {
        /* inverse the order of the list items */
        return list.get(list.size() - position - 1);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getKey().hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        KeyMessagePair pair = getItem(position);
        Message message = pair.getMessage();

        if (view == null) {
            view = inflater.inflate(R.layout.message, parent, false);
        }

        TextView contentText = view.findViewById(R.id.message_content);
        TextView dateText = view.findViewById(R.id.message_date);
        TextView upvotesText = view.findViewById(R.id.message_upvotes);

        contentText.setText(message.getContent());
        dateText.setText(String.valueOf(message.getDate()));
        upvotesText.setText(String.valueOf(message.getUpvotes()));

        return view;
    }
}
