package award.topic.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import award.topic.com.awardapplication.R;
import award.topic.com.domain.StaffAward;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by XUEYI on 2016/8/12.
 */

public class StaffAdapter  extends RecyclerView.Adapter<StaffAdapter.ViewHolder>  {
    /**
     * ItemClick的回调接口
     *
     * @author zhy
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private StaffAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(StaffAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    private List<StaffAward> mDataset;
    private Context context;

    public StaffAdapter(List<StaffAward> dataset, Context context) {
        super();
        this.context = context;
        mDataset = dataset;
    }


    @Override
    public StaffAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
//        View view = View.inflate(viewGroup.getContext(), R.layout.news_item, null);
        View view = LayoutInflater.from(context).inflate(R.layout.staff_item, viewGroup, false);
        // 创建一个ViewHolder
        StaffAdapter.ViewHolder holder = new StaffAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final StaffAdapter.ViewHolder viewHolder, final int i) {
        StaffAward mData = mDataset.get(i);
        // 绑定数据到ViewHolder上
        viewHolder.staffName.setText(mData.getStaffName());
        viewHolder.staffAccount.setText(mData.getStaffAccount());

        viewHolder.staffTime.setText(mData.getStaffTime());

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

        @Bind(R.id.tv_staffName)
        TextView staffName;
        @Bind(R.id.tv_staffAccount)
        TextView staffAccount;
        @Bind(R.id.tv_staffTime)
        TextView staffTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

