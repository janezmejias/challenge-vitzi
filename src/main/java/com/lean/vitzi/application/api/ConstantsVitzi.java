package com.lean.vitzi.application.api;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
public class ConstantsVitzi {

    public static final String API_KEY = "x-api-key";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String REQUEST_TYPE = "x-request-type";

    public static final String ANALYTIC_DATASETS = "ANALYTIC_DATASETS";
    public static final String CUSTOMER_DATASET_CONFIG = "CUSTOMER_DATASET_CONFIG";
    public static final String GENERATE_TOKEN = "GENERATE_TOKEN";
    public static final String GET_PAGE = "GET_PAGE";
    public static final String GET_ALL_PAGES = "GET_ALL_PAGES";
    public static final String GET_ALL_DATASETS = "GET_ALL_DATASETS";
    public static final String GET_USER_ID = "GET_USER_ID";

    public static final String GET_APP_ID = "GET_APP_ID";
    public static final String GET_PUBLISHED_PAGES = "GET_PUBLISHED_PAGES";
    public static final String CREATE_PAGE = "CREATE_PAGE";
    public static final String CLONE_PAGE = "CLONE_PAGE";
    public static final String PUT_PAGE = "PUT_PAGE";
    public static final String DELETE_PAGE = "DELETE_PAGE";
    public static final String EXPORT_RESOURCE = "EXPORT_RESOURCE";


    public static final String API_DOMAIN = "domain";
    public static final String FIELD_API_KEY = "api_key";
    public static final String USER_ID = "user_id";

    public static final String USERID = "userid";
    public static final String APPID = "appid";

    public static final String APP_ID = "app_id";
    public static final String CLIENT_ID = "client_id";

    public static final String REQUEST_TYPE_ERROR_MESSAGE = "x-request-type header don't can be null or empty";
    public static final String GENERIC_ERROR_MESSAGE = "Failed call: {}";
    public static final String INVALID_ACCOUNT = "Invalid account";
    public static final String PROPERTY_NOT_FOUND_MESSAGE = "No exist a property call ";
    public static final String COMMON = "Common";
    public static final String EVENT = "Event";

    public static final String ANALYTICS = "ANALYTICS";
    public static final String ANALYTICS_DATASETS = "ANALYTICS_DATASETS";
    public static final String MODE_COMPLETE = "COMPLETE";
    public static final String MODE_SIMPLE = "SIMPLE";
    public static final String MODULE_NOT_SETUP = "Module without configuration";
    public static final String QRVEYID = "qrveyid";
    public static final String QV_TOKEN = "qv_token";
    public static final String TOKEN = "token";
    public static final String DATASET_ID = "dataset_id";
    public static final String PERMISSIONS = "permissions";

    public static final String QRVEY_DATASET_ID = "qrvey_dataset_id";
    public static final String QV_URL_GENERATE_TOKEN = "/devapi/v4/core/login/token";
    public static final String QRVEY_API = "/qrvey-integration";
    public static final String EMPTY_BODY = "{}";
    public static final String GET_USER_ID_PATH = "/devapi/v4/core/user/user_id";
    public static final String GET_APP_ID_VALIDATION_PATH = "/pubapi/v4/app/app_id";
    public static final String KEY_EVENT_DEFAULT = "-2";
    public static final String CATEGORY = "/category";

    private ConstantsVitzi() {
    }
}