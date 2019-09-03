package com.jack.lant.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.view.View;

import com.jack.lant.R;
import com.jack.lant.base.BaseActivity;

import java.util.List;

import cn.jiguang.imui.chatinput.ChatInputView;
import cn.jiguang.imui.chatinput.listener.OnMenuClickListener;
import cn.jiguang.imui.chatinput.model.FileItem;
import cn.jiguang.imui.messages.MessageList;

public class ChatActivity extends BaseActivity {

    private ChatInputView mChatInputView;
    private MessageList mMsgListView;


    @Override
    protected int getLayoutResource() {
        return R.layout.chat_activity;
    }

    @Override
    public void initView() {
        super.initView();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

        mMsgListView = findViewById(R.id.msg_list);
        mChatInputView = findViewById(R.id.chat_input);
    }

    @Override
    public void initListener() {
        super.initListener();
        mChatInputView.setMenuClickListener(new OnMenuClickListener() {

            @Override
            public boolean onSendTextMessage(CharSequence input) {
                // 输入框输入文字后，点击发送按钮事件
                return false;
            }

            @Override
            public void onSendFiles(List<FileItem> list) {
                // 选中文件或者录制完视频后，点击发送按钮触发此事件
            }

            @Override
            public boolean switchToMicrophoneMode() {
                // 点击语音按钮触发事件，显示录音界面前触发此事件
                // 返回 true 表示使用默认的界面，若返回 false 应该自己实现界面
                return true;
            }

            @Override
            public boolean switchToGalleryMode() {
                // 点击图片按钮触发事件，显示图片选择界面前触发此事件
                // 返回 true 表示使用默认的界面
                return true;
            }

            @Override
            public boolean switchToCameraMode() {
                // 点击拍照按钮触发事件，显示拍照界面前触发此事件
                // 返回 true 表示使用默认的界面
                return true;
            }

            @Override
            public boolean switchToEmojiMode() {
                return false;
            }
        });
    }
}
