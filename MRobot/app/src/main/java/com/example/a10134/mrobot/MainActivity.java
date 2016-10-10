package com.example.a10134.mrobot;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a10134.mrobot.Aadpter.Myadpter;
import com.example.a10134.mrobot.Bean.ChatMessage;
import com.example.a10134.mrobot.utils.HttpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mMsgs;
    private Myadpter mAdpter;
    private List<ChatMessage> mDatas;
    private EditText mInputMsg;
    private Button mSendBtn;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //等待接收数据；
            ChatMessage fromMessage = (ChatMessage) msg.obj;
            mDatas.add(fromMessage);
            mAdpter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        
        initDatas();

        initListener();
    }

    private void initListener() {
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("dsasdadasdasdasdasdas");
                final String toMsg = mInputMsg.getText().toString();
                if(TextUtils.isEmpty(toMsg)){
                    Toast.makeText(MainActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                ChatMessage toMessage = new ChatMessage();
                toMessage.setMsg(toMsg);
                toMessage.setDate(new Date());
                toMessage.setType(ChatMessage.Type.OUTCOMING);
                mDatas.add(toMessage);
                mAdpter.notifyDataSetChanged();
                mInputMsg.setText("");

                new Thread(){
                    @Override
                    public void run() {
                        ChatMessage fromMsg = HttpUtils.sendMessage(toMsg);
                        Message msg = Message.obtain();
                        msg.obj = fromMsg;
                        mHandler.sendMessage(msg);
                    }
                }.start();

            }
        });
    }

    private void initDatas() {
        mDatas = new ArrayList<ChatMessage>();
        mDatas.add(new ChatMessage("你好，我是车见车爆胎的喵喵！", ChatMessage.Type.INCOMING,new Date()));

        mAdpter = new Myadpter(this,mDatas);

        mMsgs.setAdapter(mAdpter);
    }

    private void initView() {
        mMsgs = (ListView) findViewById(R.id.id_listView_msg);
        mInputMsg = (EditText) findViewById(R.id.id_input_msg);
        mSendBtn = (Button) findViewById(R.id.id_send_msg);
    }
}
