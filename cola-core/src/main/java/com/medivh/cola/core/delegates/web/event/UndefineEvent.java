package com.medivh.cola.core.delegates.web.event;


import com.medivh.cola.core.utils.ColaLogger;

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        ColaLogger.e("UndefineEvent", params);
        return null;
    }
}
