package com.example.breathesafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.breathesafe.Adapter.ActAdapter;
import com.example.breathesafe.Adapter.AlarmAdapter;
import com.example.breathesafe.DBFolder.ActDB;
import com.example.breathesafe.DBFolder.AlarmDB;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActActivity extends AppCompatActivity {



    @BindView(R.id.clockRecycler)
    RecyclerView clockRecycler;

    private ActAdapter actAdapter;
    //private AlarmAdapter alarmAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionplan);
        ButterKnife.bind(this);
        LitePal.initialize(this);
        //StatusBarUtil.setColor(this, getResources().getColor(R.color.toolBar));
        //setSupportActionBar(toolBar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            toolBarTitle.setText("服药提醒");
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
        initView();
        initDB();
    }

    private void initView() {
        actAdapter = new ActAdapter();
        layoutManager = new LinearLayoutManager(this);
        clockRecycler.setLayoutManager(layoutManager);
        clockRecycler.setAdapter(actAdapter);

    }

    private void initDB() {
        //List<ActDB> actDBS = LitePal.findAll(ActDB.class);
        List<ActDB> actDBS = LitePal.findAll(ActDB.class);
        actAdapter.setNewData(actDBS);
        actAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(RemindActivity.this,UpdateAlarmActivity.class);
//                startActivityForResult(intent,1);
            }
        });

        actAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ActActivity.this, "Test", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @OnClick(R.id.floatingButton)
    public void onViewClicked() {
        Intent intent = new Intent(this, AddActActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            initDB();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in Action Bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
