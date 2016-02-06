package com.example.hacker_ratty.animationexample;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.podcopic.animationlib.library.AnimationType;
import com.podcopic.animationlib.library.StartSmartAnimation;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private List<String> mAppList;
    OurListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppList = new ArrayList<String>();

        //adding items to list
        for(int i = 0 ; i<20 ;i ++){
            mAppList.add("item "+i);
        }


        listView = (ListView) findViewById(R.id.listView);

        mAdapter = new OurListAdapter();
        listView.setAdapter(mAdapter);

    }


    class OurListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public String getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = View.inflate(MainActivity.this,
                        R.layout.item_list, null);
                new ViewHolder(convertView);
            }
            final ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.name.setText(mAppList.get(position));
            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StartSmartAnimation.startAnimation(holder.item,AnimationType.SlideOutRight,1000,0,true);
                    mAppList.remove(position);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.setAdapter(mAdapter);
                        }
                    },1000);
                }
            });

            return convertView;
        }

        class ViewHolder {
            private TextView name;
            private Button del;
            private View item;

            public ViewHolder(View view) {

                name = (TextView) view.findViewById(R.id.name);
                del = (Button) view.findViewById(R.id.del);
                item = view.findViewById(R.id.item);

                view.setTag(this);
            }
        }
    }


}
