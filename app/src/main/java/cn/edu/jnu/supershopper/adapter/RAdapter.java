package cn.edu.jnu.supershopper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author : mingci
 * @date : 2022/10/319:48
 * @desc :
 */

public abstract class RAdapter<T> extends RecyclerView.Adapter<RViewHolder> {
    protected Context context;
    protected List<T> list;
    private int layoutId;
    private OnItemClickListener<T> mListener = null;

    protected RAdapter(Context context, int layoutId, List<T> list) {
        this(context, layoutId, list, null);
    }

    protected RAdapter(Context context, int layoutId, List<T> list, OnItemClickListener<T> listener) {
        this.context = context;
        this.list = list;
        this.layoutId = layoutId;
        this.mListener = listener;
    }

    //此构造方法只适用于多布局的情况
    protected RAdapter(Context context, List<T> list) {
        this(context, list, null);
    }

    protected RAdapter(Context context, List<T> list, OnItemClickListener<T> listener) {
        this.context = context;
        this.list = list;
        this.mListener = listener;
    }

    @Override
    public int getItemViewType(@LayoutRes int position) {

        return layoutId;
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(viewType, parent, false);
        return new RViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int p) {
        final int position = holder.getAdapterPosition();
        init(holder, list.get(position));
        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(position, list.get(position));
                }
            });
        }
    }

    protected abstract void init(RViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T t);

    }

}
