package com.nstop.flow.engine.common;

public class FlowElementType {
    public static final String SEQUENCE_FLOW = "flowline";
    public static final String START_EVENT = "start";
    public static final String END_EVENT = "finish";
    public static final String USER_TASK = "user_task";
    public static final String EXCLUSIVE_GATEWAY = "jugement";

    public static final String GROOVY = "groovy";
    public static final String JAVA_INVOKER = "java_invoker";

    public static final String RDB = "rdb";

    public static final String EXCEPTION = "exc";


    /****************   http  **********************/

    public static final String HTTP_START_EVENT = "http_start";

    public static final String HTTP_END_EVENT = "http_finish";

}
