package com.jack.lant.ui.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class LanEntity implements MultiItemEntity {

    public int receiveType; // 0 = 我  、 1 = 其他

    public int viewType;
    public String content;
    public String time;

    @Override
    public int getItemType() {
        return viewType;
    }
}
