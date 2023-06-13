package com.nstop.flow.engine.common;

public class Constants {

    public static final int DEFAULT_TIMEOUT = 3000;

    public static final String ELEMENTLIST = "flowElementList";

    public static final class ELEMENT_PROPERTIES {
        public static final String NAME = "name";
        public static final String CONDITION = "conditionsequenceflow";
        public static final String DEFAULT_CONDITION = "defaultConditions";
        public static final String HOOK_INFO_IDS = "hookInfoIds";

        public static final String SCRIPT = "script";
        public static final String _joinContext = "_joinContext";
        public static final String _contextName = "_contextName";

        public static final String _requiredList = "_requiredList";

        public static final String BEAN = "bean";
        public static final String METHOD = "method";
        public static final String PARAMS = "params";
        public static final String DS_NAME = "_dsName";
        public static final String SQL_TYPE = "_sqlType";
        public static final String SQL_SCRIPT = "_sql_script";
        public static final String SQL_PAGE_INDEX = "_sql_page_index";
        public static final String SQL_PAGE_SIZE = "_sql_page_size";
        public static final String _httpRespKeys = "_httpRespKeys";

        public static final String _errorMsg = "_errorMsg";

        public static final String _errorCode = "_errorCode";




    }

    public static final String NODE_INFO_FORMAT = "nodeKey={0}, nodeName={1}, nodeType={2}";
    public static final String NODE_INSTANCE_FORMAT = "nodeKey={0}, nodeName={1}, nodeInstanceId={2}";
    public static final String MODEL_DEFINITION_ERROR_MSG_FORMAT = "message={0}, elementName={1}, elementKey={2}";



    public static final class SYSTEM_CONTEXT_PROPERTIES {
        public static final String HTTP_RESP_KEYS = "_httpRespKeys";

        public static final String ENGINE_TYPE_DATA_KEY = "_engineType";

        public static final String HAS_ERROR = "_hasError";

        public static final String ERROR_MSG = "_errorMsg";

        public static final String ERROR_CODE = "_errorCode";



    }
}
