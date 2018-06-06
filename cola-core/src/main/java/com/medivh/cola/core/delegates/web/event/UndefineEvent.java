package com.medivh.cola.core.delegates.web.event;


import com.medivh.cola.core.utils.LatteLogger;

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
