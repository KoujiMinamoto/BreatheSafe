package com.example.breathesafe.Adapter;

import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.breathesafe.BaseFolder.MyApp;
import com.example.breathesafe.DBFolder.AlarmDB;
import com.example.breathesafe.R;

public class AlarmAdapter extends BaseQuickAdapter<AlarmDB, BaseViewHolder> {
    public AlarmAdapter() {
        super(R.layout.item_med, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, AlarmDB item) {
        StringBuilder builder = new StringBuilder();
        builder.append(item.getHourNum()).append(":").append(item.getMinNum());
        helper.setText(R.id.tv_medname, builder.toString());
        helper.setText(R.id.tv_medtime, builder.toString());
        switch (item.getCycleNum()) {
            case 0:
                helper.setText(R.id.tv_count, "everyday");
                break;
            case -1:
                helper.setText(R.id.tv_count, "one time");
                break;
        }
        helper.addOnClickListener(R.id.layout_public);
        helper.addOnLongClickListener(R.id.layout_public);
        helper.setChecked(R.id.switchButton, item.isEnabled());
        Switch view = helper.getView(R.id.switchButton);
        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MyApp.getContext(), "" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
