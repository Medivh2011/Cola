package com.medivh.latte.core.delegates.web.event;


import com.medivh.latte.core.utils.LatteLogger;

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
