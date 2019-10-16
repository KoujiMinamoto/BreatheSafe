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
        StringBuilder builder2 = new StringBuilder();
        StringBuilder builder3 = new StringBuilder();
        StringBuilder builder4 = new StringBuilder();
        builder.append(item.getHourNum()).append(":").append(item.getMinNum());
        builder2.append(item.getAlarmName());
        builder3.append(item.getDosage());
        builder4.append(item.getForm());
        helper.setText(R.id.tv_medname, "Medication Name："+builder2.toString());
        helper.setText(R.id.tv_dos,"Dosage："+builder3.toString());
        helper.setText(R.id.tv_form,"Form："+builder4.toString());
        helper.setText(R.id.tv_medtime, builder.toString());
        switch (item.getCycleNum()) {
            case 0:
                helper.setText(R.id.tv_count, "Repeat:Everyday");
                break;
            case -1:
                helper.setText(R.id.tv_count, "Repeat:One Time");
                break;
        }
        helper.addOnClickListener(R.id.layout_public);
        helper.addOnLongClickListener(R.id.layout_public);
//        helper.setChecked(R.id.switchButton, item.isEnabled());
//        Switch view = helper.getView(R.id.switchButton);
//        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Toast.makeText(MyApp.getContext(), "" + isChecked, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
