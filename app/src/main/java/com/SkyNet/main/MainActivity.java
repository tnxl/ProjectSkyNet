package com.SkyNet.main;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.SkyNet.R;
import com.SkyNet.layout.FindFragment;
import com.SkyNet.layout.HomeFragment;
import com.SkyNet.layout.MineFragment;
import com.SkyNet.layout.VideoFragment;
import com.SkyNet.util.AnimUtil;
import com.SkyNet.util.PageViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";

    private ViewPager viewPager;
    //存放Fragment的容器
    private List<Fragment> list;
    //底部导航栏
    private RadioGroup radioGroup;
    //底部导航栏
    private RadioButton wChatBtn, comBtn, findBtn, mineBtn;
    //TopBar的TextView
    private TextView titleTextView;
    private PageViewAdapter pageViewAdapter;
    //FragmentManager
    private FragmentManager fragmentManager;
    //标题名称
    String titleName[] = new String[]{"首页", "视频", "发现", "我的"};
    //搜索按钮
    private ImageView search;
    //侧滑返回按钮
    private ImageView iv_close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        //获得fragment管理器
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);

        initView();
        initLayout();
        initFragmentList();
        initViewPager();
        addListener();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //Viewpage的页面容器
        list = new ArrayList<>();
        //添加Viewpager适配器
        pageViewAdapter = new PageViewAdapter(fragmentManager, list);

        /**
         * 顶部栏
         */
        iv_open = findViewById(R.id.arrow);
        iv_add = findViewById(R.id.iv_add);
        //页面标题数组
        titleTextView = (TextView) findViewById(R.id.top_title);
        search = (ImageView)findViewById(R.id.iv_search);
        //右上角加号按钮
        mPopupWindow = new PopupWindow(this);
        //动画效果
        animUtil = new AnimUtil();

        /**
         * 底部栏
         */
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        wChatBtn = (RadioButton) findViewById(R.id.radioButton1);
        comBtn = (RadioButton) findViewById(R.id.radioButton2);
        findBtn = (RadioButton) findViewById(R.id.radioButton3);
        mineBtn = (RadioButton) findViewById(R.id.radioButton4);

        /**
         * 侧滑栏
         */
        drawerLayout = (DrawerLayout)findViewById(R.id.main_drawer_layout);
        linearLayout = (LinearLayout)findViewById(R.id.drawer_layout);
        leftlist = (ListView)findViewById(R.id.left_list);
        iv_close = (ImageView)findViewById(R.id.iv_close);
    }

    private void addListener() {
        //右上角添加按钮监听
        iv_add.setOnClickListener(this);
        //给RadioGroup添加监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton radioButtonDemo = findViewById(group.getChildAt(i).getId());
                    radioButtonDemo.setTextColor(Color.parseColor("#aaaaaa"));
                    if (group.getChildAt(i).getId() == checkedId) {
                        RadioButton radioButton = findViewById(group.getChildAt(i).getId());
                        radioButton.setTextColor(Color.parseColor("#11CD6E"));
                        viewPager.setCurrentItem(i);
                        titleTextView.setText(titleName[i]);
//                        break;
                    }
                }
            }
        });
        //搜索按钮添加监听
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"Search",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.START);
            }
        });
    }

    //初始化fragment容器
    private void initFragmentList() {
        Fragment home = new HomeFragment();
        Fragment video = new VideoFragment();
        Fragment find = new FindFragment();
        Fragment mine = new MineFragment();
        list.add(home);
        list.add(video);
        list.add(find);
        list.add(mine);
    }

    private void initViewPager() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                titleTextView.setText(titleName[i]);
                setColor(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }

            private void setColor(int i){
                int name = 0;
                int names[] = new int[]{R.id.radioButton1,R.id.radioButton2 ,
                R.id.radioButton3,R.id.radioButton4};
                for (int j=0;j<4;j++){
                    RadioButton radioButtonDemo = findViewById(names[j]);
                    radioButtonDemo.setTextColor(Color.parseColor("#aaaaaa"));
                }
                name = names[i];
                RadioButton radioButton = findViewById(name);
                radioButton.setTextColor(Color.parseColor("#11CD6E"));
                radioButton.setChecked(true);
            }
        });
        //添加viewpager的适配器
        viewPager.setAdapter(pageViewAdapter);
        viewPager.setCurrentItem(0);
        titleTextView.setText(titleName[0]);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                showPop();
                toggleBright();
                break;
            case R.id.tv_1:
                mPopupWindow.dismiss();
                Toast.makeText(this, tv_1.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_2:
                mPopupWindow.dismiss();
                Toast.makeText(this, tv_2.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_3:
                mPopupWindow.dismiss();
                Toast.makeText(this, tv_3.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_4:
                mPopupWindow.dismiss();
                Toast.makeText(this, tv_4.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_5:
                mPopupWindow.dismiss();
                Toast.makeText(this, tv_5.getText(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    /**
     * Fragment触摸接口
     */
    public interface MyOnTouchListener {
        public boolean onTouch(MotionEvent ev);
    }

    private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<MyOnTouchListener>(10);

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyOnTouchListener listener : onTouchListeners) {
            if(listener != null) {
                listener.onTouch(ev);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    public void registerMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.add(myOnTouchListener);
    }
    public void unregisterMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.remove(myOnTouchListener);
    }


    private ImageView iv_add;
    private TextView tv_1, tv_2, tv_3, tv_4, tv_5;
    private PopupWindow mPopupWindow;

    private AnimUtil animUtil;
    private float bgAlpha = 1f;
    private boolean bright = false;

    private static final long DURATION = 500;
    private static final float START_ALPHA = 0.7f;
    private static final float END_ALPHA = 1f;

    /**
     * 右上角弹窗
     */
    private void showPop() {
        // 设置布局文件
        mPopupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.pop_add, null));
        // 为了避免部分机型不显示，我们需要重新设置一下宽高
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置pop透明效果
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        // 设置pop出入动画
        mPopupWindow.setAnimationStyle(R.style.pop_add);
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        mPopupWindow.setFocusable(true);
        // 设置pop可点击，为false点击事件无效，默认为true
        mPopupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        mPopupWindow.setOutsideTouchable(true);
        // 相对于 + 号正下面，同时可以设置偏移量
        mPopupWindow.showAsDropDown(iv_add, -100, 0);
        // 设置pop关闭监听，用于改变背景透明度
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                toggleBright();
            }
        });

        tv_1 = mPopupWindow.getContentView().findViewById(R.id.tv_1);
        tv_2 = mPopupWindow.getContentView().findViewById(R.id.tv_2);
        tv_3 = mPopupWindow.getContentView().findViewById(R.id.tv_3);
        tv_4 = mPopupWindow.getContentView().findViewById(R.id.tv_4);
        tv_5 = mPopupWindow.getContentView().findViewById(R.id.tv_5);

        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
        tv_4.setOnClickListener(this);
        tv_5.setOnClickListener(this);
    }

    private void toggleBright() {
        // 三个参数分别为：起始值 结束值 时长，那么整个动画回调过来的值就是从0.5f--1f的
        animUtil.setValueAnimator(START_ALPHA, END_ALPHA, DURATION);
        animUtil.addUpdateListener(new AnimUtil.UpdateListener() {
            @Override
            public void progress(float progress) {
                // 此处系统会根据上述三个值，计算每次回调的值是多少，我们根据这个值来改变透明度
                bgAlpha = bright ? progress : (START_ALPHA + END_ALPHA - progress);
                backgroundAlpha(bgAlpha);
            }
        });
        animUtil.addEndListner(new AnimUtil.EndListener() {
            @Override
            public void endUpdate(Animator animator) {
                // 在一次动画结束的时候，翻转状态
                bright = !bright;
            }
        });
        animUtil.startAnimator();
    }

    /**
     * 此方法用于改变背景的透明度，从而达到“变暗”的效果
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        // everything behind this window will be dimmed.
        // 此方法用来设置浮动层，防止部分手机变暗无效
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    //抽屉菜单对象
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout;
    private ImageView iv_open;

    private ListView leftlist;
    private boolean flag;

    public boolean getFlag(){
        return flag;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initLayout(){

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titleName);
        leftlist.setAdapter(adapter1);

        leftlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewPager.setCurrentItem(position);
                drawerLayout.closeDrawer(Gravity.START);
            }
        });

        iv_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        //设置监听DrawerLayout
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDrawerSlide(View arg0, float arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDrawerOpened(View arg0) {
                // TODO Auto-generated method stub
                flag = true;
            }

            @Override
            public void onDrawerClosed(View arg0) {
                // TODO Auto-generated method stub
                flag = false;
            }
        });

        //防止抽屉被击穿
        linearLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //进行判断
                if (MainActivity.this.getFlag()) {
                    //表示抽屉被打开
                    event.setAction(MotionEvent.ACTION_CANCEL); //将所有分发的事件对象都取消掉
                }

                return false;
            }
        });

    }





}
