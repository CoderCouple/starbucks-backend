package com.starbucks.model;

import com.starbucks.dao.PingDao;

public class Main {
    public static void main(final String[] args) {
        PingDao pd = new PingDao();
        Ping p = pd.getPing();
        System.out.println(p.toString());

    }
}
