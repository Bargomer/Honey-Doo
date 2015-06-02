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

import com.parse.ParseObject;

import junit.runner.Version;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<List> {

    private Context context;
    private java.util.List<List> list;

    public ListAdapter(Context context, java.util.List<List> list) {
        super(context, R.layout.activity_main_list, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main_list, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.listName);
        textView.setText(list.get(position).getListName());

        return view;
    }
}
