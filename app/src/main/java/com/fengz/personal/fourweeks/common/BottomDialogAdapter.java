package com.fengz.personal.fourweeks.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.utils.ImageUtils;

import java.util.List;

/**
 * ios风格选择框 Adapter
 */
public class BottomDialogAdapter extends RecyclerView.Adapter {

    private List<BottomModel> mDataList;
    private AdapterView.OnItemClickListener mLinstener;
    private Context mContext;

    public BottomDialogAdapter(Context context, List<BottomModel> dataList, AdapterView.OnItemClickListener linstener) {
        this.mDataList = dataList;
        mLinstener = linstener;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bottom_dialog, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;

        BottomModel model = mDataList.get(position);
        viewHolder.textName.setText(model.getName());
        if (!TextUtils.isEmpty(model.getUrl())) {
            ImageUtils.loadRadiusImg(mContext, model.getUrl(), 4, viewHolder.img);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinstener.onItemClick(null, viewHolder.itemView, position, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textName;
        private ImageView img;


        public ViewHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.tv_name_item_bottom_dialog);
            img = (ImageView) itemView.findViewById(R.id.img_item_bootom_dialog);
        }
    }
}
