package com.sergo.wic.google_api;

import java.util.Map;

public abstract class AbstractWebsiteChecker {
    protected abstract boolean APIcall(Map<String, String> values);
    protected abstract boolean check();
}
