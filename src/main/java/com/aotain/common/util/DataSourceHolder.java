package com.aotain.common.util;

public class DataSourceHolder {
    
    private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();

    public static void setDataSource(String customerType) {
        dataSources.set(customerType);
    }

    public static String getDataSource() {
        return (String) dataSources.get();
    }

    public static void clearDataSource() {
        dataSources.remove();
    }
}