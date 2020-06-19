package com.example.myrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mDatas;
    private ArrayList<Integer> mHeights = new ArrayList<>();

    public RecyclerAdapter(Context context, ArrayList<String> datas) {
        mContext = context;
        mDatas = datas;

        if (mDatas.size() > 0) {
            for (int i = 0; i < mDatas.size(); i++) {
                mHeights.add(getRandomHeight());
            }
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (viewType == ITEM_TYPE.ITEM_TYPE_ITEM.ordinal()) {
            return new NormalHolder(inflater.inflate(R.layout.item_layout, parent, false));
        }
        return new SectionHolder(inflater.inflate(R.layout.item_section_layout, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SectionHolder) {
            SectionHolder sectionHolder = (SectionHolder) holder;
            sectionHolder.mSectionTv.setText("第 " + position / SECTION_ITEM_NUM + " 组");
        } else if (holder instanceof NormalHolder) {
            NormalHolder normalHolder = (NormalHolder) holder;
            normalHolder.mTV.setText(mDatas.get(position));
        }
    }

    private int getRandomHeight() {
        int randomHeight = 0;
        do {
            randomHeight = (int) (Math.random() * 300);
        } while (randomHeight == 0);
        randomHeight += 100;
        return randomHeight;
    }

    public static enum ITEM_TYPE {
        ITEM_TYPE_SECTION,
        ITEM_TYPE_ITEM
    }

    private int SECTION_ITEM_NUM = 10;

    @Override
    public int getItemViewType(int position) {
        if (position % SECTION_ITEM_NUM == 0) {
            return ITEM_TYPE.ITEM_TYPE_SECTION.ordinal();
        }
        return ITEM_TYPE.ITEM_TYPE_ITEM.ordinal();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class NormalHolder extends RecyclerView.ViewHolder {

        public TextView mTV;

        public NormalHolder(View itemView) {
            super(itemView);
            mTV = itemView.findViewById(R.id.item_tv);
            mTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mTV.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public class SectionHolder extends RecyclerView.ViewHolder {
        public TextView mSectionTv;

        public SectionHolder(View itemView) {
            super(itemView);
            mSectionTv = itemView.findViewById(R.id.item_section_tv);
        }
    }


}
