package com.example.breathesafe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.breathesafe.ActActivity;
import com.example.breathesafe.BaseFolder.MyApp;
import com.example.breathesafe.DBFolder.ActDB;
import com.example.breathesafe.MainActivity;
import com.example.breathesafe.R;
import com.example.breathesafe.Tips;
import android.speech.tts.TextToSpeech.OnInitListener;

import java.util.Locale;

public class ActAdapter extends BaseQuickAdapter<ActDB, BaseViewHolder> {
    public ActAdapter() {
        super(R.layout.item_action, null);
    }
    private Button rzone;
    private Button gzone;
    private Button yzone;
    private TextToSpeech tts;


    @Override
    protected void convert(BaseViewHolder helper, ActDB item) {
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        StringBuilder builder3 = new StringBuilder();
        StringBuilder builder4 = new StringBuilder();
        StringBuilder builder21 = new StringBuilder();
        StringBuilder builder22 = new StringBuilder();
        StringBuilder builder23 = new StringBuilder();
        StringBuilder builder24 = new StringBuilder();
        StringBuilder builder31 = new StringBuilder();
        StringBuilder builder32 = new StringBuilder();
        StringBuilder builder33 = new StringBuilder();
        StringBuilder builder34 = new StringBuilder();
        StringBuilder builder41 = new StringBuilder();
        StringBuilder builder42 = new StringBuilder();
        StringBuilder builder43 = new StringBuilder();
        StringBuilder builder44 = new StringBuilder();
        StringBuilder builder51 = new StringBuilder();
        StringBuilder builder52 = new StringBuilder();
        StringBuilder builder53 = new StringBuilder();
        StringBuilder builder54 = new StringBuilder();
        builder1.append(item.getGreenrename());
        builder2.append(item.getGreenredosage());
        builder3.append(item.getGreenreform());
        builder4.append(item.getGreenrefrequency());
        builder21.append(item.getGreenconname());
        builder22.append(item.getGreencondosage());
        builder23.append(item.getGreenconform());
        builder24.append(item.getGreenconfrequency());
        builder31.append(item.getYellowrename());
        builder32.append(item.getYellowredosage());
        builder33.append(item.getYellowreform());
        builder34.append(item.getYellowrefrequency());
        builder41.append(item.getYellowconname());
        builder42.append(item.getYellowcondosage());
        builder43.append(item.getYellowconform());
        builder44.append(item.getYellowconfrequency());
        builder51.append(item.getRedrename());
        builder52.append(item.getRedredosage());
        builder53.append(item.getRedreform());
        builder54.append(item.getRedrefrequency());
//        helper.setText(R.id.tv_greenredosage, builder2.toString()+" "+builder3.toString());
//        helper.setText(R.id.tv_greenreform,"");
//        helper.setText(R.id.tv_greenrefrequency,builder4.toString());
        helper.setText(R.id.tv_greenrename, builder1.toString()+",  "+builder2.toString()+" "+builder3.toString()+",   "+builder4.toString()+".");
        helper.setText(R.id.tv_greenconname,builder21.toString()+",  "+builder22.toString()+" "+builder23.toString()+",   "+builder24.toString()+".");
//        helper.setText(R.id.tv_greencondosage,builder22.toString()+" "+builder23.toString());
//        helper.setText(R.id.tv_greenconform,"");
//        helper.setText(R.id.tv_greenconfrequency,builder24.toString());
        helper.setText(R.id.tv_yellowrename,builder31.toString()+",  "+builder32.toString()+" "+builder33.toString()+",   "+builder34.toString()+".");
//        helper.setText(R.id.tv_yellowredosage,builder32.toString()+" "+builder33.toString());
//        helper.setText(R.id.tv_yellowreform,"");
//        helper.setText(R.id.tv_yellowrefrequency,builder34.toString());
        helper.setText(R.id.tv_yellowconname,builder41.toString()+",  "+builder42.toString()+" "+builder43.toString()+",   "+builder44.toString()+".");
//        helper.setText(R.id.tv_yellowcondosage,builder42.toString()+" "+builder43.toString());
//        helper.setText(R.id.tv_yellowconform,"");
//        helper.setText(R.id.tv_yellowconfrequency,builder44.toString());
        helper.setText(R.id.tv_redrename,builder51.toString()+",  "+builder52.toString()+" "+builder53.toString()+",   "+builder54.toString()+".");
//        helper.setText(R.id.tv_redredosage,builder52.toString()+" "+builder53.toString());
//        helper.setText(R.id.tv_redreform,"");
//        helper.setText(R.id.tv_redrefrequency,builder54.toString());
//        switch (item.getCycleNum()) {
//            case 0:
//                helper.setText(R.id.tv_count, "everyday");
//                break;
//            case -1:
//                helper.setText(R.id.tv_count, "one time");
//                break;
//        }
        helper.addOnClickListener(R.id.layout_public1);
        helper.addOnLongClickListener(R.id.layout_public1);
        System.out.println("hello");
//        helper.setChecked(R.id.switchButton, item.isEnabled());
//        Switch view = helper.getView(R.id.switchButton);
//        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Toast.makeText(MyApp.getContext(), "" + isChecked, Toast.LENGTH_SHORT).show();
//            }
//        });
        gzone = helper.getView(R.id.gzone);
        rzone = helper.getView(R.id.rzone);
        yzone = helper.getView(R.id.yzone);
        //final String greentext = "Don't panic. Relax. Take  " + builder1.toString()+"dosage is" + builder2.toString() + "form is "+ builder3.toString()+"frequency is"+builder4.toString();
        final String greentext = "Don't panic. Relax. Take  " + builder2.toString()+ builder3.toString() + "of"+ builder1.toString()+"." +"Then relax for two minutes and take  " + builder22.toString()+ builder23.toString() + "of"+ builder21.toString();
        final String yellowtext = "Don't panic. Relax. Take  " + builder32.toString()+ builder33.toString() + "of"+ builder31.toString()+"." +"Then relax for two minutes and take  " + builder42.toString()+ builder43.toString() + "of"+ builder41.toString();
        final String redtext = "Don't panic. Relax. Take  " + builder52.toString()+ builder53.toString() + "of"+ builder51.toString()+"." +"Then relax and go and see your physician  ";
        tts = new TextToSpeech(this.mContext,new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                    tts.setSpeechRate(2);
                }
            }
        });
        gzone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tts.speak(greentext, TextToSpeech.QUEUE_FLUSH, null);
//                Toast.makeText(MyApp.getContext(),"green").show();
            }
        });
        yzone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tts.speak(yellowtext, TextToSpeech.QUEUE_FLUSH, null);
//                Toast.makeText(MyApp.getContext(),"green").show();
            }
        });
        rzone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tts.speak(redtext, TextToSpeech.QUEUE_FLUSH, null);
//                Toast.makeText(MyApp.getContext(),"green").show();
            }
        });
    }
}
