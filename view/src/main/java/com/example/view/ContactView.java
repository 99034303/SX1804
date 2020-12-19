package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ContactView extends RelativeLayout {
    //字母数据源
    private List<String> datas = new ArrayList<>();
    //数组适配器
    private LetterAdapter letterAdapter;
    //联系人布局
    RecyclerView contactView;


    public ContactView(Context context) {
        super(context);
        init(context);
    }

    public ContactView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ContactView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //添加两个RecyclerView
    private void init(final Context context) {
        //左边布局
        View view = LayoutInflater.from(context).inflate(R.layout.contact_view, null);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);


        //获取联系人布局
        contactView = view.findViewById(R.id.contact_view);
        //获取字母布局
        RecyclerView recyclerView = view.findViewById(R.id.right_view);


        //字母布局的数据源
        for (int i = 0; i < 26; i++) {
            datas.add((char) (i + 65) + "");
        }
        //字母布局
        letterAdapter = new LetterAdapter(R.layout.letter_view, datas);
        recyclerView.setAdapter(letterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //字母点击事件
        letterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(context, "" + datas.get(position), Toast.LENGTH_SHORT).show();
            }
        });


        addView(view);
    }



    /**
     * 返回 Recycler布局
     * @return
     */
    public RecyclerView getRecycler(){
        return contactView;
    }


    /**
     * 为联系人布局添加适配器(分组布局)
     *
     * @param adapter
     * @param context
     */
    public void setContactAdapter(BaseSectionQuickAdapter adapter, Context context) {
        contactView.setAdapter(adapter);
        contactView.setLayoutManager(new LinearLayoutManager(context));

        //刷新适配器
        adapter.notifyDataSetChanged();
    }

    /**
     * 为联系人布局添加适配器(多布局)
     * @param adapter
     * @param context
     */
    public void setContactAdapter(BaseMultiItemQuickAdapter adapter, Context context) {
        contactView.setAdapter(adapter);
        contactView.setLayoutManager(new LinearLayoutManager(context));

        //刷新适配器
        adapter.notifyDataSetChanged();
    }


    //字母适配器
    class LetterAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public LetterAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_letter, item);
        }
    }
}
