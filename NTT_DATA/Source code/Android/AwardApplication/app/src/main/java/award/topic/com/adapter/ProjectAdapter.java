package award.topic.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import award.topic.com.awardapplication.R;
import award.topic.com.domain.ProjectAward;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by XUEYI on 2016/8/12.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder>  {
    /**
     * ItemClick的回调接口
     *
     * @author zhy
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    private List<ProjectAward> mDataset;
    private Context context;

    public ProjectAdapter(List<ProjectAward> dataset, Context context) {
        super();
        this.context = context;
        mDataset = dataset;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
//        View view = View.inflate(viewGroup.getContext(), R.layout.news_item, null);
        View view = LayoutInflater.from(context).inflate(R.layout.award_item, viewGroup, false);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        ProjectAward campusNews = mDataset.get(i);
        // 绑定数据到ViewHolder上
    viewHolder.newsTitle.setText(campusNews.getProject());
    viewHolder.newsDateTime.setText(campusNews.getProjectTime());
        viewHolder.newsMoney.setText(campusNews.getProjectAward());


        Picasso.with(context)
                .load(R.mipmap.d)
                .into(viewHolder.newsImage);


    //如果设置了回调，则设置点击事件
    if (mOnItemClickLitener != null) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickLitener.onItemClick(viewHolder.itemView, i);

            }
        });

    }
}

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_newsTitle)
        TextView newsTitle;
        @Bind(R.id.tv_newsDateTime)
        TextView newsDateTime;
        @Bind(R.id.iv_newsImage)
        ImageView newsImage;
        @Bind(R.id.tv_newsMoney)
        TextView  newsMoney;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
