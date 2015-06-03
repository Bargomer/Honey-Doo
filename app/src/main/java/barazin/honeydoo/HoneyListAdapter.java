package barazin.honeydoo;

/**
 * Created by Jose on 5/10/2015.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;
import java.util.List;

public class HoneyListAdapter extends ArrayAdapter<HoneyList> {

    private Context context;
    private List<HoneyList> honeyLists;

    public HoneyListAdapter(Context context, java.util.List<HoneyList> honeyLists) {
        super(context, R.layout.activity_list_item, honeyLists);
        this.context = context;
        this.honeyLists = honeyLists;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_list_item, parent, false);

//        //for task icon
//        ImageView taskImageView = (ImageView) view.findViewById(R.dra);
//        taskImageView.setImageResource(honeyLists.get(position).getHoneyIcon());



        TextView honeyPoints = (TextView) view.findViewById(R.id.displayPoints);
        honeyPoints.setText(honeyLists.get(position).getHoneyPoints() + " points");

        TextView displayDescription = (TextView) view.findViewById(R.id.displayDescription);
        displayDescription.setText(honeyLists.get(position).getTaskId());

        //Button for completing a task
        Button honeyButton = (Button) view.findViewById(R.id.honeyButton);
        honeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoneyListAdapter.this.notifyDataSetChanged();
            }
        });

        //Button for not completing a task
        Button dooButton = (Button) view.findViewById(R.id.dooButton);
        dooButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoneyListAdapter.this.notifyDataSetChanged();
            }
        });

            return view;
    }
}
