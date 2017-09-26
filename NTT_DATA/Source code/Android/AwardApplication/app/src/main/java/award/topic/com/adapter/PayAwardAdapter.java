package award.topic.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import award.topic.com.awardapplication.R;
import award.topic.com.domain.PayAward;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by XUEYI on 2016/8/14.
 */

public class PayAwardAdapter extends RecyclerView.Adapter<PayAwardAdapter.ViewHolder>  {
    /**
     * ItemClick的回调接口
     *
     * @author zhy
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private PayAwardAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(PayAwardAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    private List<PayAward> mDataset;
    private Context context;

    public PayAwardAdapter(List<PayAward> dataset, Context context) {
        super();
        this.context = context;
        mDataset = dataset;
    }


    @Override
    public PayAwardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
//        View view = View.inflate(viewGroup.getContext(), R.layout.news_item, null);
        View view = LayoutInflater.from(context).inflate(R.layout.pay_item, viewGroup, false);
        // 创建一个ViewHolder
        PayAwardAdapter.ViewHolder holder = new PayAwardAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final PayAwardAdapter.ViewHolder viewHolder, final int i) {
        PayAward mData = mDataset.get(i);
        // 绑定数据到ViewHolder上

        viewHolder.awardName.setText(mData.getAwardName());
        viewHolder.awardPay.setText(mData.getAwardPay());

        viewHolder.awardTime.setText(mData.getAwardTime());
        viewHolder.awardWiped.setText(mData.getAwardWiped());

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

        @Bind(R.id.tv_awardName)
        TextView awardName;
        @Bind(R.id.tv_awardPay)
        TextView awardPay;
        @Bind(R.id.tv_awardWiped)
        TextView awardWiped;

        @Bind(R.id.tv_awardTime)
        TextView  awardTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

