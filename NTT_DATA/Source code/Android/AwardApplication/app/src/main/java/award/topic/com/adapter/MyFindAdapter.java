package award.topic.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import award.topic.com.awardapplication.R;
import award.topic.com.config.Icon;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by XUEYI on 2015/12/11.
 */
public class MyFindAdapter extends BaseAdapter {
    private Context context;
    private List<Icon> mData;

    public MyFindAdapter(List<Icon> mData, Context context) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_icon, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Icon icon = mData.get(position);
        if (null != icon) {
            Picasso.with(context).load(icon.getId()).into(viewHolder.img);
            viewHolder.txt.setText(icon.getName());
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.img_icon)
        ImageView img;
        @Bind(R.id.txt_icon)
        TextView txt;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

