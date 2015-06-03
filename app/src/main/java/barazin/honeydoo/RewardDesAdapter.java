package barazin.honeydoo;

/**
 * Created by Timadeus on 6/2/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Timadeus on 6/2/2015.
 */
public class RewardDesAdapter extends ArrayAdapter<HoneyList> {
    private Context context;
    private java.util.List<HoneyList> list;

    public RewardDesAdapter(Context context, java.util.List<HoneyList> list) {
        super(context, R.layout.activity_reward_description, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_reward_description, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.rewardDescriptionText);
        textView.setText(list.get(position).getRewardDescription());

        return view;
    }
}
