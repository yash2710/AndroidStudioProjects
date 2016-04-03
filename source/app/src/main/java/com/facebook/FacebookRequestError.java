// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import com.facebook.internal.Utility;
import java.net.HttpURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.facebook:
//            FacebookServiceException, FacebookException

public final class FacebookRequestError
{

    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    private static final int EC_APP_NOT_INSTALLED = 458;
    private static final int EC_APP_TOO_MANY_CALLS = 4;
    private static final int EC_EXPIRED = 463;
    private static final int EC_INVALID_SESSION = 102;
    private static final int EC_INVALID_TOKEN = 190;
    private static final int EC_PASSWORD_CHANGED = 460;
    private static final int EC_PERMISSION_DENIED = 10;
    private static final Range EC_RANGE_PERMISSION = new Range(200, 299, null);
    private static final int EC_SERVICE_UNAVAILABLE = 2;
    private static final int EC_UNCONFIRMED_USER = 464;
    private static final int EC_UNKNOWN_ERROR = 1;
    private static final int EC_USER_CHECKPOINTED = 459;
    private static final int EC_USER_TOO_MANY_CALLS = 17;
    private static final String ERROR_CODE_FIELD_KEY = "code";
    private static final String ERROR_CODE_KEY = "error_code";
    private static final String ERROR_KEY = "error";
    private static final String ERROR_MESSAGE_FIELD_KEY = "message";
    private static final String ERROR_MSG_KEY = "error_msg";
    private static final String ERROR_REASON_KEY = "error_reason";
    private static final String ERROR_SUB_CODE_KEY = "error_subcode";
    private static final String ERROR_TYPE_FIELD_KEY = "type";
    private static final Range HTTP_RANGE_CLIENT_ERROR = new Range(400, 499, null);
    private static final Range HTTP_RANGE_SERVER_ERROR = new Range(500, 599, null);
    private static final Range HTTP_RANGE_SUCCESS = new Range(200, 299, null);
    public static final int INVALID_ERROR_CODE = -1;
    public static final int INVALID_HTTP_STATUS_CODE = -1;
    private static final int INVALID_MESSAGE_ID;
    private final Object batchRequestResult;
    private final Category category;
    private final HttpURLConnection connection;
    private final int errorCode;
    private final String errorMessage;
    private final String errorType;
    private final FacebookException exception;
    private final JSONObject requestResult;
    private final JSONObject requestResultBody;
    private final int requestStatusCode;
    private final boolean shouldNotifyUser;
    private final int subErrorCode;
    private final int userActionMessageId;

    private FacebookRequestError(int i, int j, int k, String s, String s1, JSONObject jsonobject, JSONObject jsonobject1, 
            Object obj, HttpURLConnection httpurlconnection)
    {
        this(i, j, k, s, s1, jsonobject, jsonobject1, obj, httpurlconnection, null);
    }

    private FacebookRequestError(int i, int j, int k, String s, String s1, JSONObject jsonobject, JSONObject jsonobject1, 
            Object obj, HttpURLConnection httpurlconnection, FacebookException facebookexception)
    {
        boolean flag;
        Category category1;
        int l;
        flag = false;
        super();
        requestStatusCode = i;
        errorCode = j;
        subErrorCode = k;
        errorType = s;
        errorMessage = s1;
        requestResultBody = jsonobject;
        requestResult = jsonobject1;
        batchRequestResult = obj;
        connection = httpurlconnection;
        boolean flag1;
        if (facebookexception != null)
        {
            exception = facebookexception;
            flag1 = true;
        } else
        {
            exception = new FacebookServiceException(this, s1);
            flag1 = false;
        }
        if (!flag1) goto _L2; else goto _L1
_L1:
        category1 = Category.CLIENT;
        l = 0;
_L4:
        category = category1;
        userActionMessageId = l;
        shouldNotifyUser = flag;
        return;
_L2:
        if (j == 1 || j == 2)
        {
            category1 = Category.SERVER;
            l = 0;
        } else
        if (j == 4 || j == 17)
        {
            category1 = Category.THROTTLING;
            flag = false;
            l = 0;
        } else
        if (j == 10 || EC_RANGE_PERMISSION.contains(j))
        {
            category1 = Category.PERMISSION;
            l = com.facebook.android.R.string.com_facebook_requesterror_permissions;
            flag = false;
        } else
        if (j == 102 || j == 190)
        {
            if (k == 459 || k == 464)
            {
                category1 = Category.AUTHENTICATION_RETRY;
                l = com.facebook.android.R.string.com_facebook_requesterror_web_login;
                flag = true;
            } else
            {
                category1 = Category.AUTHENTICATION_REOPEN_SESSION;
                if (k == 458 || k == 463)
                {
                    l = com.facebook.android.R.string.com_facebook_requesterror_relogin;
                    flag = false;
                } else
                if (k == 460)
                {
                    l = com.facebook.android.R.string.com_facebook_requesterror_password_changed;
                    flag = false;
                } else
                {
                    l = com.facebook.android.R.string.com_facebook_requesterror_reconnect;
                    flag = true;
                }
            }
        } else
        {
            flag = false;
            l = 0;
            category1 = null;
        }
        if (category1 == null)
        {
            if (HTTP_RANGE_CLIENT_ERROR.contains(i))
            {
                category1 = Category.BAD_REQUEST;
            } else
            if (HTTP_RANGE_SERVER_ERROR.contains(i))
            {
                category1 = Category.SERVER;
            } else
            {
                category1 = Category.OTHER;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public FacebookRequestError(int i, String s, String s1)
    {
        this(-1, i, -1, s, s1, null, null, null, null, null);
    }

    FacebookRequestError(HttpURLConnection httpurlconnection, Exception exception1)
    {
        FacebookException facebookexception;
        if (exception1 instanceof FacebookException)
        {
            facebookexception = (FacebookException)exception1;
        } else
        {
            facebookexception = new FacebookException(exception1);
        }
        this(-1, -1, -1, null, null, null, null, null, httpurlconnection, facebookexception);
    }

    static FacebookRequestError checkResponseAndCreateError(JSONObject jsonobject, Object obj, HttpURLConnection httpurlconnection)
    {
        if (!jsonobject.has("code")) goto _L2; else goto _L1
_L1:
        int i;
        Object obj1;
        i = jsonobject.getInt("code");
        obj1 = Utility.getStringPropertyAsJSON(jsonobject, "body", "FACEBOOK_NON_JSON_RESULT");
        if (obj1 == null) goto _L4; else goto _L3
_L3:
        if (!(obj1 instanceof JSONObject)) goto _L4; else goto _L5
_L5:
        JSONObject jsonobject2 = (JSONObject)obj1;
        if (!jsonobject2.has("error")) goto _L7; else goto _L6
_L6:
        String s1;
        int k;
        String s2;
        int i1;
        JSONObject jsonobject3 = (JSONObject)Utility.getStringPropertyAsJSON(jsonobject2, "error", null);
        s2 = jsonobject3.optString("type", null);
        s1 = jsonobject3.optString("message", null);
        i1 = jsonobject3.optInt("code", -1);
        k = jsonobject3.optInt("error_subcode", -1);
        String s;
        int l;
        boolean flag;
        flag = true;
        l = i1;
        s = s2;
_L16:
        if (!flag) goto _L4; else goto _L8
_L8:
        return new FacebookRequestError(i, l, k, s, s1, jsonobject2, jsonobject, obj, httpurlconnection);
_L7:
        if (!jsonobject2.has("error_code") && !jsonobject2.has("error_msg") && !jsonobject2.has("error_reason")) goto _L10; else goto _L9
_L9:
        int j;
        s = jsonobject2.optString("error_reason", null);
        s1 = jsonobject2.optString("error_msg", null);
        j = jsonobject2.optInt("error_code", -1);
        k = jsonobject2.optInt("error_subcode", -1);
        l = j;
        flag = true;
        continue; /* Loop/switch isn't completed */
_L4:
        if (HTTP_RANGE_SUCCESS.contains(i)) goto _L2; else goto _L11
_L11:
        if (!jsonobject.has("body")) goto _L13; else goto _L12
_L12:
        JSONObject jsonobject1 = (JSONObject)Utility.getStringPropertyAsJSON(jsonobject, "body", "FACEBOOK_NON_JSON_RESULT");
_L14:
        FacebookRequestError facebookrequesterror = new FacebookRequestError(i, -1, -1, null, null, jsonobject1, jsonobject, obj, httpurlconnection);
        return facebookrequesterror;
        JSONException jsonexception;
        jsonexception;
_L2:
        return null;
_L13:
        jsonobject1 = null;
          goto _L14
_L10:
        flag = false;
        l = 0;
        k = 0;
        s = null;
        s1 = null;
        if (true) goto _L16; else goto _L15
_L15:
    }

    public final Object getBatchRequestResult()
    {
        return batchRequestResult;
    }

    public final Category getCategory()
    {
        return category;
    }

    public final HttpURLConnection getConnection()
    {
        return connection;
    }

    public final int getErrorCode()
    {
        return errorCode;
    }

    public final String getErrorMessage()
    {
        if (errorMessage != null)
        {
            return errorMessage;
        } else
        {
            return exception.getLocalizedMessage();
        }
    }

    public final String getErrorType()
    {
        return errorType;
    }

    public final FacebookException getException()
    {
        return exception;
    }

    public final JSONObject getRequestResult()
    {
        return requestResult;
    }

    public final JSONObject getRequestResultBody()
    {
        return requestResultBody;
    }

    public final int getRequestStatusCode()
    {
        return requestStatusCode;
    }

    public final int getSubErrorCode()
    {
        return subErrorCode;
    }

    public final int getUserActionMessageId()
    {
        return userActionMessageId;
    }

    public final boolean shouldNotifyUser()
    {
        return shouldNotifyUser;
    }

    public final String toString()
    {
        return (new StringBuilder("{HttpStatus: ")).append(requestStatusCode).append(", errorCode: ").append(errorCode).append(", errorType: ").append(errorType).append(", errorMessage: ").append(getErrorMessage()).append("}").toString();
    }


    private class Category extends Enum
    {

        private static final Category $VALUES[];
        public static final Category AUTHENTICATION_REOPEN_SESSION;
        public static final Category AUTHENTICATION_RETRY;
        public static final Category BAD_REQUEST;
        public static final Category CLIENT;
        public static final Category OTHER;
        public static final Category PERMISSION;
        public static final Category SERVER;
        public static final Category THROTTLING;

        public static Category valueOf(String s)
        {
            return (Category)Enum.valueOf(com/facebook/FacebookRequestError$Category, s);
        }

        public static Category[] values()
        {
            return (Category[])$VALUES.clone();
        }

        static 
        {
            AUTHENTICATION_RETRY = new Category("AUTHENTICATION_RETRY", 0);
            AUTHENTICATION_REOPEN_SESSION = new Category("AUTHENTICATION_REOPEN_SESSION", 1);
            PERMISSION = new Category("PERMISSION", 2);
            SERVER = new Category("SERVER", 3);
            THROTTLING = new Category("THROTTLING", 4);
            OTHER = new Category("OTHER", 5);
            BAD_REQUEST = new Category("BAD_REQUEST", 6);
            CLIENT = new Category("CLIENT", 7);
            Category acategory[] = new Category[8];
            acategory[0] = AUTHENTICATION_RETRY;
            acategory[1] = AUTHENTICATION_REOPEN_SESSION;
            acategory[2] = PERMISSION;
            acategory[3] = SERVER;
            acategory[4] = THROTTLING;
            acategory[5] = OTHER;
            acategory[6] = BAD_REQUEST;
            acategory[7] = CLIENT;
            $VALUES = acategory;
        }

        private Category(String s, int i)
        {
            super(s, i);
        }
    }


    private class Range
    {

        private final int end;
        private final int start;

        boolean contains(int i)
        {
            return start <= i && i <= end;
        }

        private Range(int i, int j)
        {
            start = i;
            end = j;
        }

        Range(int i, int j, _cls1 _pcls1)
        {
            this(i, j);
        }
    }

}
