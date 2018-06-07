package com.medivh.cola.core.wechat.templates;


import com.medivh.cola.core.wechat.BaseWXEntryActivity;
import com.medivh.cola.core.wechat.ColaWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        ColaWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
