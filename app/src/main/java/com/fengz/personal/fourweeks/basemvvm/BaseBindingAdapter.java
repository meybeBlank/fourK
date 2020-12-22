//package com.fengz.personal.fourweeks.basemvvm;
//
//import android.content.Context;
//import android.databinding.DataBindingUtil;
//import android.databinding.ObservableArrayList;
//import android.databinding.ObservableList;
//import android.databinding.ViewDataBinding;
//import android.support.annotation.IntegerRes;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
///**
// * RecyclerView BaseAdapter
// *
// * @param <B> item数据bean
// * @param <V> item布局bingding
// */
//public abstract class BaseBindingAdapter<B, V extends ViewDataBinding> extends RecyclerView.Adapter<BaseBindingAdapter.BaseViewHolder> {
//    protected Context context;
//    protected ObservableList<B> mData;
//
//    public BaseBindingAdapter(Context context) {
//        this.context = context;
//        this.mData = new ObservableArrayList<>();
//    }
//
//    @NonNull
//    @Override
//    public BaseBindingAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        V dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), getItemLayout(i), viewGroup, false);
//        return new BaseViewHolder(dataBinding.getRoot());
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BaseBindingAdapter.BaseViewHolder viewHolder, int i) {
//        V binding = DataBindingUtil.getBinding(viewHolder.itemView);
//        binding.setVariable(getVariableId(), mData.get(i));
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//
//    @IntegerRes
//    protected abstract int getItemLayout(int type);
//
//    protected abstract int getVariableId();
//
//    public static class BaseViewHolder extends RecyclerView.ViewHolder {
//
//        public BaseViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
//}
