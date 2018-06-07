package com.medivh.cola.core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.medivh.cola.core.delegates.web.event.Event;
import com.medivh.cola.core.delegates.web.event.EventManager;
import com.medivh.cola.core.utils.ColaLogger;


final class ColaWebInterface {
    private final WebDelegate DELEGATE;

    private ColaWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static ColaWebInterface create(WebDelegate delegate) {
        return new ColaWebInterface(delegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        ColaLogger.d("WEB_EVENT",params);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
