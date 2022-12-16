package com.nstop.data.source.context;

/**
 * @author: origindoris
 * @Title: DataSourceContext
 * @Description:
 * @date: 2022/10/18 14:17
 */
public class DataSourceContext {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * 设置数据源名称
     * @param dataSourceKey
     */
    public static synchronized void setDataSource(String dataSourceKey){
        contextHolder.set(dataSourceKey);
    }

    /**
     * 获取当前数据源名称
     * @return
     */
    public static String getDataSourceKey() {
        return contextHolder.get();
    }

    /**
     * 清空数据源名称，默认使用default
     */
    public static void clearDataSourceKey(){
        contextHolder.remove();
    }
}
