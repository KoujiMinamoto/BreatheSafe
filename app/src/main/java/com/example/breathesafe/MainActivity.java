package com.example.breathesafe;

import android.app.Fragment;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.breathesafe.Adapter.MyAdapter;
import com.example.breathesafe.OnRecyclerItemClickListener;
import com.example.breathesafe.DividerGridItemDecoration;
import com.example.breathesafe.Subject;
import com.example.breathesafe.GlideImageLoader;



import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    private Context mContext = MainActivity.this;
    private TextView mTextMessage;
    private Button btn_for;
    private Button btn_tip;
    private Button btn_sys;
    private Button btn_med;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.carousel)
    Banner carousel;
    private String[] titles = {"for", "sys", "tips", "act","med"};
    private Button btn_homepage;
    private Button btn_prototype;
    private Button btn_setting;
    private Integer[] imageUrl = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,R.drawable.img5};
    private int[] imageIds = {R.drawable.forecast, R.drawable.sys,
            R.drawable.tips, R.drawable.act,R.drawable.med};
    private List<Subject> datas = new ArrayList<>();
    private ItemTouchHelper mItemTouchHelper;
    private MyAdapter myAdapter;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_setting:
                    mTextMessage.setText(R.string.title_setting);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Integer> imgUrls = Arrays.asList(imageUrl);

        Banner banner = (Banner) findViewById(R.id.carousel);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imgUrls);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(6500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

//        initData();
//        //initImgData();
//        initView();

        init();

    }

    private void initData() {
        //初始化data
        for (int i = 0; i < titles.length; i++) {
            datas.add(new Subject(titles[i], imageIds[i]));
        }
    }

    private void init() {

        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        btn_for = findViewById(R.id.forecast);
        btn_tip = findViewById(R.id.tips);
        btn_sys = findViewById(R.id.symptoms);
        btn_med = findViewById(R.id.forecast4);


        //back home page
        /*
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.getMenu().getItem(0).setChecked(true);//设置默认选中的Tab
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment nextFragment = null;
                /*通过判断进行选择Tab之后想要的操作，一般是进行fragment的动态切换
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mTextMessage.setText(R.string.title_home);
                        nextFragment = new MainFragment();
                        return true;

                }
                return false;
            }
        });
        */
        btn_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
                //跳转到主界面，登录成功的状态传递到 MainActivity 中
                startActivity(new Intent(MainActivity.this, Tips.class));



            }
        });
        //go to forecast
        btn_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
                //跳转到主界面，登录成功的状态传递到 MainActivity 中
                startActivity(new Intent(MainActivity.this, Forecast.class));



            }
        });

        btn_sys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
                //跳转到主界面，登录成功的状态传递到 MainActivity 中
                startActivity(new Intent(MainActivity.this, Forecast.class));



            }
        });
        btn_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
                //跳转到主界面，登录成功的状态传递到 MainActivity 中
                startActivity(new Intent(MainActivity.this, RemindActivity.class));



            }
        });

    }


    private void initImgData() {
        List<Integer> imgUrls = Arrays.asList(imageUrl);
        carousel.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        carousel.setImageLoader(new GlideImageLoader());
        //设置图片集合
        carousel.setImages(imgUrls);
        //设置banner动画效果
        carousel.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        carousel.isAutoPlay(true);
        //设置轮播时间
        carousel.setDelayTime(6500);
        //设置指示器位置（当banner模式中有指示器时）
        carousel.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        carousel.start();
    }

    private void initView() {


        final LinearLayoutManager layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLayoutManager(layoutManager);
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        myAdapter = new MyAdapter(datas, mContext);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {

                if (datas.get(vh.getLayoutPosition()).getTitle().equals("for")) {
                    Intent intent = new Intent(MainActivity.this, Forecast.class);
                    startActivity(intent);
                }
                if (datas.get(vh.getLayoutPosition()).getTitle().equals("sys")) {
                    Intent intent = new Intent(MainActivity.this, Tips.class);
                    startActivity(intent);
                }
                if (datas.get(vh.getLayoutPosition()).getTitle().equals("tips")) {
                    Intent intent = new Intent(MainActivity.this, Tips.class);
                    startActivity(intent);
                }
                if (datas.get(vh.getLayoutPosition()).getTitle().equals("act")) {
                    Intent intent = new Intent(MainActivity.this, Tips.class);
                    startActivity(intent);
                }
                if (datas.get(vh.getLayoutPosition()).getTitle().equals("med")) {
                    Intent intent = new Intent(MainActivity.this, Tips.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                //判断被拖拽的是否是前两个，如果不是则执行拖拽
                mItemTouchHelper.startDrag(vh);
                //获取系统震动服务
                Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);//震动70毫秒
                vib.vibrate(70);
            }
        });

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            /**
             * 是否处理滑动事件 以及拖拽和滑动的方向 如果是列表类型的RecyclerView的只存在UP和DOWN，如果是网格类RecyclerView则还应该多有LEFT和RIGHT
             * @param recyclerView
             * @param viewHolder
             * @return
             */
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(datas, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(datas, i, i - 1);
                    }
                }
                myAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            /**
             * 重写拖拽可用
             * @return
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            /**
             * 长按选中Item的时候开始调用
             *
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 手指松开的时候还原
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
            }
        });
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

}
