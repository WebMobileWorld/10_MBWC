package com.mbwc.e.myapplication.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbwc.e.myapplication.Models.ResultModel;
import com.mbwc.e.myapplication.R;


/**
 * Created by E on 10/21/2016.
 */

public class ResultViewAdapter extends BaseListAdapter {
    public ResultViewAdapter(Context context){
        super(context, R.layout.list_item_weight);
    }
    public View getView(final int postion, View convertView, ViewGroup parent){
        ResultModel resultModel = (ResultModel) m_feedList.get(postion);
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_weight,parent,false);
        }
        TextView date = ViewHolderHelper.get(convertView,R.id.tv_date);
        TextView weight = ViewHolderHelper.get(convertView, R.id.tv_kgs);
        TextView lbs = ViewHolderHelper.get(convertView,R.id.tv_lbs);
        TextView sts = ViewHolderHelper.get(convertView,R.id.tv_sts);
        date.setText(resultModel.date);
        weight.setText("" + resultModel.weight);
        lbs.setText("" + resultModel.lbs);
        sts.setText("" + resultModel.sts);
        return convertView;
    }
}
