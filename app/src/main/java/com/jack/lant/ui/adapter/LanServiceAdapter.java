package com.jack.lant.ui.adapter;

import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jack.lant.R;
import com.jack.lant.ui.model.LanEntity;

import java.util.List;


/** 服务器app 适配器 */
public class LanServiceAdapter extends BaseMultiItemQuickAdapter<LanEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public LanServiceAdapter(List<LanEntity> data) {
        super(data);
        addItemType(1, R.layout.adapter_lan_service); //灰色间距 懒得去写 直接写个布局

    }

    @Override
    protected void convert(BaseViewHolder helper, LanEntity item) {
        switch (item.viewType) {
            case 1:
                initPublicMessage(helper, item);
                break;
        }
    }

    /**
     *  普通消息
     * @param helper
     * @param item
     */
    private void initPublicMessage(BaseViewHolder helper, LanEntity item) {
        LinearLayout left = helper.getView(R.id.adapter_left);
        LinearLayout right = helper.getView(R.id.adapter_right);
        left.setVisibility(View.GONE);
        right.setVisibility(View.GONE);
        if (item.receiveType == 0) {
            right.setVisibility(View.VISIBLE);
            helper.setText(R.id.adapter_service_content2, item.content);
            helper.setText(R.id.adapter_service_time2, item.time);
        } else {
            left.setVisibility(View.VISIBLE);
            helper.setText(R.id.adapter_service_content, item.content);
            helper.setText(R.id.adapter_service_time, item.time);
        }
    }


//    public LanServiceAdapter() {
//        super(R.layout.adapter_lan_service);
//    }

//    @Override
//    protected void convert(BaseViewHolder helper, LanEntity item) {
//        helper.setText(R.id.adapter_service_content, item.content);
//        helper.setText(R.id.adapter_service_time, item.time);
//    }
}
