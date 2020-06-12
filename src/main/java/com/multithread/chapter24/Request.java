package com.multithread.chapter24;

/**
 * 请求类
 *
 * @author zt1994 2020/6/12 20:53
 */
public class Request {

    private final String business;

    public Request(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "Request{" +
                "business='" + business + '\'' +
                '}';
    }
}
