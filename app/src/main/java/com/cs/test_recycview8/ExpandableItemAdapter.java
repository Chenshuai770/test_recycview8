package com.cs.test_recycview8;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/10/010.
 */

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     *
     * @param data A new list is created out of this one to avoid mutable list
     */


    public static Map<Integer, Boolean> mSelect;
    public static Map<Integer, Boolean> mEnablable;



    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_PERSON = 2;

    public ExpandableItemAdapter(List<MultiItemEntity> data,Map<Integer, Boolean> isSelected ,Map<Integer, Boolean> isEnable ) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1);
        addItemType(TYPE_PERSON, R.layout.item_expandable_lv2);
        Log.d("BBB", "isSelected.size():" + isSelected.size());
        mSelect=isSelected;
        Log.d("BBB", "mSelect.size():" + mSelect.size());
        mEnablable=isEnable;





    }

    @Override
    protected void convert(final BaseViewHolder holder, MultiItemEntity item) {


    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        Log.d("XXX", "mSelect.size():" + mSelect.size());

        switch (holder.getItemViewType()){
            case TYPE_LEVEL_0:
                final Level0Item lv0 = (Level0Item) getData().get(position);
                holder.setText(R.id.tv_title0,lv0.getTitle());
                holder.setImageResource(R.id.iv_arrow0, lv0.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);

                        }
                    }
                });

                break;
            case TYPE_LEVEL_1:
                final Level1Item lv1 = (Level1Item) getData().get(position);
                holder.setText(R.id.tv_title1,lv1.getTitle());

                holder.setImageResource(R.id.iv_arrow1, lv1.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (lv1.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }



                    }
                });



                break;
            case TYPE_PERSON:
                final Person person = (Person) getData().get(position);



                holder.setText(R.id.tv_title2,person.getName());

                final CheckBox mCheck = holder.getView(R.id.checkbox2);
                mCheck.setEnabled(mEnablable.get(position));
                holder.itemView.setEnabled(mEnablable.get(position));
/*
                if (mCharName!=null){
                    for (int i = 0; i < mCharName.size(); i++) {
                        Log.d("XXX", "mCharName:" + mCharName.get(i));
                        if (person.getName().equals(mCharName.get(i))) {
                            mSelect.put(position,true);
                        }
                    }
                }*/



                mCheck.setChecked(mSelect.get(position));

                holder.itemView.setSelected(mSelect.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (mCheck.isChecked()) {
                            mSelect.put(position,false);
                            notifyItemChanged(position);
                        }else {
                            mSelect.put(position,true);
                            notifyItemChanged(position);
                        }



                    }
                });



              /*  holder.setChecked(R.id.checkbox2,mSelect.get(holder.getLayoutPosition()));
                holder.itemView.setSelected(mSelect.get(holder.getLayoutPosition()));

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int cp = getParentPosition(person);
                        mSelect.put(holder.getLayoutPosition(),true);
                        notifyItemChanged(holder.getLayoutPosition());
                    }
                });*/

                break;
        }


    }
}
