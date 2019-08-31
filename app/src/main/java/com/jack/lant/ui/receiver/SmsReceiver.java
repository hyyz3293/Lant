package com.jack.lant.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.jack.lant.ui.model.Event.EventMessage;

import org.greenrobot.eventbus.EventBus;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        //1、接收短信协议
        Bundle bundle= intent.getExtras(); //Bundle表示MAP套装(键值对)
        //2、通过Bundle取值
        Object[] objs= (Object[]) bundle.get("pdus");
        for (Object obj : objs) {
            //3、获取短信对象
            SmsMessage sms = SmsMessage.createFromPdu((byte[])obj);
            System.out.println("短信联系人："+ sms.getOriginatingAddress());
            System.out.println("短信内容："+ sms.getDisplayMessageBody());
            EventMessage eventMessage = new EventMessage(sms.getOriginatingAddress(), sms.getDisplayMessageBody());
            EventBus.getDefault().post(eventMessage);
            //4、短信拦截（收到短信时，系统会发一个有序广播，默认优先级是500，我们可以设置短信窃听器的广播优先级为1000）
            if(sms.getOriginatingAddress().equals("110")){
                abortBroadcast();//终止广播
            }
        }
    }

}
