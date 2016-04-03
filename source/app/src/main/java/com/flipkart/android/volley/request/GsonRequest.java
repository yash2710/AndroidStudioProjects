// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request;

import android.content.Context;
import android.content.Intent;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.crashlytics.android.Crashlytics;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.log.ApiLogger;
import com.flipkart.android.log.ApiPerfLogger;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.log.LoggerTag;
import com.flipkart.android.log.perf.ApiPerfMetrics;
import com.flipkart.android.notification.GcmUtils;
import com.flipkart.android.response.android.widget.AndroidWidgetResponseWrapper;
import com.flipkart.android.response.appconfig.ImageProfileMatrix;
import com.flipkart.android.response.autoSuggest.AutoSuggestResponse;
import com.flipkart.android.response.baseresponse.BaseResponse;
import com.flipkart.android.response.session.Session;
import com.flipkart.android.response.wrapper.ResponseWrapper;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.volley.HeaderUtils;
import com.flipkart.android.utils.volley.ResponseUtils;
import com.flipkart.android.volley.filters.ThrottledRetryPolicy;
import com.flipkart.android.volley.request.autoSuggest.AutoSuggestRequest;
import com.flipkart.android.volley.request.login.LogoutRequest;
import com.flipkart.logging.FkLogger;
import com.google.mygson.Gson;
import com.google.mygson.JsonIOException;
import com.google.mygson.JsonSyntaxException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public abstract class GsonRequest extends Request
{

    public static final String API_VERSION_2 = "2";
    public static final String API_VERSION_3 = "3";
    public static String BASE_API_URL = "http://mobileapi.flipkart.net";
    public static String BASE_API_URL_SECURE = "https://mobileapi.flipkart.net";
    public static String BASE_WEB_URL = "http://m.flipkart.com";
    public static String BASE_WEB_URL_SECURE = "https://m.flipkart.com";
    private final Type a;
    private final com.android.volley.Response.Listener b;
    private final Map c;
    private AnalyticData d;
    private Map e;
    private Gson f;
    private String g;
    private String h;
    private String i;

    public GsonRequest(int j, String s, Map map, String s1, Type type, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, 
            AnalyticData analyticdata)
    {
        super(j, s, errorlistener);
        f = null;
        g = null;
        a = type;
        b = listener;
        c = map;
        StringUtils.isHttps(s);
        g = s;
        d = analyticdata;
        h = s1;
        f = FlipkartApplication.getGsonInstance();
        a();
        setRetryPolicy(new ThrottledRetryPolicy());
    }

    public GsonRequest(int j, String s, Map map, String s1, Type type, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, 
            String s2)
    {
        super(j, s, errorlistener);
        f = null;
        g = null;
        h = s1;
        a = type;
        b = listener;
        c = map;
        StringUtils.isHttps(s);
        g = s;
        i = s2;
        f = FlipkartApplication.getGsonInstance();
        a();
        setRetryPolicy(new ThrottledRetryPolicy());
    }

    public GsonRequest(int j, String s, Map map, Type type, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(j, s, errorlistener);
        f = null;
        g = null;
        a = type;
        b = listener;
        c = map;
        StringUtils.isHttps(s);
        g = s;
        f = FlipkartApplication.getGsonInstance();
        a();
        setRetryPolicy(new ThrottledRetryPolicy());
    }

    public GsonRequest(int j, String s, Map map, Type type, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, AnalyticData analyticdata)
    {
        super(j, s, errorlistener);
        f = null;
        g = null;
        a = type;
        b = listener;
        c = map;
        StringUtils.isHttps(s);
        g = s;
        d = analyticdata;
        f = FlipkartApplication.getGsonInstance();
        a();
        setRetryPolicy(new ThrottledRetryPolicy());
    }

    private void a()
    {
        e = new HashMap();
        HeaderUtils.addSessionKeysHeader(e);
        HeaderUtils.addAuthKeyHeader(e);
        HeaderUtils.addSecureTokenHeaderForHttps(e, i);
        HeaderUtils.addSessionSpecificHeaders(e);
        e.put("Accept-Encoding", "gzip");
        if (!StringUtils.isNullOrEmpty(h))
        {
            e.put("Content-Type", h);
        }
        if (d != null)
        {
            HeaderUtils.addAnalyticDataInHeader(e, d);
        }
    }

    private void a(NetworkResponse networkresponse)
    {
        if (mEventLog != null && networkresponse != null && !(this instanceof AutoSuggestRequest))
        {
            ApiPerfMetrics apiperfmetrics = new ApiPerfMetrics();
            apiperfmetrics.setApi(getClass().getSimpleName());
            apiperfmetrics.setUrl(g);
            apiperfmetrics.setNetworkType(((ThrottledRetryPolicy)getRetryPolicy()).getNetworkType());
            apiperfmetrics.setDeviceId(FlipkartDeviceInfoProvider.getDeviceId());
            apiperfmetrics.setCustomerId(FlipkartPreferenceManager.instance().getUserAccountId());
            apiperfmetrics.setRetryCount(mEventLog.calcRetries());
            apiperfmetrics.setStatusCode(networkresponse.statusCode);
            apiperfmetrics.setDeviceName(FlipkartDeviceInfoProvider.getReadableDeviceId());
            apiperfmetrics.setLastByteTime(mEventLog.calcDuration());
            apiperfmetrics.setAppVersion(FlipkartPreferenceManager.instance().getAppVersionNumber());
            apiperfmetrics.setOsVersion((new StringBuilder()).append(FlipkartDeviceInfoProvider.getAndroidSDKVersion()).toString());
            apiperfmetrics.setPayloadSize(networkresponse.data.length);
            apiperfmetrics.setClient("Android");
            ApiPerfLogger.log(apiperfmetrics.toMap());
        }
    }

    private void a(Session session)
    {
        if (session == null) goto _L2; else goto _L1
_L1:
        Crashlytics.setString("session_info", session.toString());
        if ((StringUtils.isNullOrEmpty(session.getVid()) || StringUtils.isNullOrEmpty(session.getNsid())) && StringUtils.isNullOrEmpty(session.getSn())) goto _L2; else goto _L3
_L3:
        boolean flag;
        Boolean boolean1 = FlipkartPreferenceManager.instance().isLoggedIn();
        Intent intent1;
        if (boolean1.booleanValue() != session.isLoggedIn() && boolean1.booleanValue() && !(this instanceof LogoutRequest))
        {
            try
            {
                Crashlytics.logException(new Throwable((new StringBuilder("Logout Exception{ Old Session in cache:SN = ")).append(FlipkartPreferenceManager.instance().getSn()).append(",Nsid = ").append(FlipkartPreferenceManager.instance().getNsid()).append(",Vid = ").append(FlipkartPreferenceManager.instance().getVid()).append(",AccountId = ").append(FlipkartPreferenceManager.instance().getUserAccountId()).append(" New Session in api response:SN = ").append(session.getSn()).append(",Nsid = ").append(session.getNsid()).append(",Vid = ").append(session.getVid()).append(",AccountId = ").append(session.getAccountId()).append("}").toString()));
            }
            catch (Exception exception)
            {
                CrashLoggerUtils.pushAndUpdate("session null in extractSessionInfo");
            }
        }
        FlipkartPreferenceManager.instance().saveSn(session.getSn());
        FlipkartPreferenceManager.instance().saveVid(session.getVid());
        FlipkartPreferenceManager.instance().saveNsid(session.getNsid());
        flag = FlipkartPreferenceManager.instance().getUsersFfStatus().booleanValue();
        if (session.isLoggedIn())
        {
            FlipkartPreferenceManager.instance().saveUserFlipkartFirstStatus(session.isFlipkartFirstUser());
        }
        if (boolean1.booleanValue() == session.isLoggedIn()) goto _L5; else goto _L4
_L4:
        if (session.isLoggedIn())
        {
            FlipkartPreferenceManager.instance().saveIsLoggedIn(Boolean.valueOf(session.isLoggedIn()));
            FlipkartPreferenceManager.instance().saveUserEmail(session.getEmailId());
            FlipkartPreferenceManager.instance().saveUserAccountId(session.getAccountId());
            FlipkartPreferenceManager.instance().saveUserFirstName(session.getFirstName());
            FlipkartPreferenceManager.instance().saveUserLastName(session.getLastName());
            FlipkartPreferenceManager.instance().saveGcmIdSentToServerStatus(false);
            GcmUtils.sendRegistrationIdToBackend("login");
        } else
        {
            FlipkartPreferenceManager.instance().saveGcmIdSentToServerStatus(false);
            FlipkartPreferenceManager.instance().clearUserSessionVariables();
            GcmUtils.sendRegistrationIdToBackend("logout");
        }
        intent1 = new Intent();
        intent1.setAction(HomeFragmentHolderActivity.LOGGED_IN_ACTION_COMMAND);
        FlipkartApplication.getAppContext().sendBroadcast(intent1);
_L7:
        if (!StringUtils.isNullOrEmpty(session.getSecureToken()))
        {
            FlipkartPreferenceManager.instance().saveSecureToken(session.getSecureToken());
        }
_L2:
        return;
_L5:
        if (flag != FlipkartPreferenceManager.instance().getUsersFfStatus().booleanValue())
        {
            Intent intent = new Intent();
            intent.setAction(HomeFragmentHolderActivity.LOGGED_IN_ACTION_COMMAND);
            intent.putExtra("updateLogo", true);
            FlipkartApplication.getAppContext().sendBroadcast(intent);
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    private Map b()
    {
        HashMap hashmap = new HashMap();
        hashmap.put("url", g);
        if (getMethod() == 1)
        {
            try
            {
                String s = new String(getBody());
                if (!s.contains("password"))
                {
                    hashmap.put("postData", s);
                }
            }
            catch (Exception exception)
            {
                return hashmap;
            }
        }
        return hashmap;
    }

    protected static String constructGetUri(String s, String s1, String s2)
    {
        return (new StringBuilder()).append(BASE_API_URL).append("/").append(s).append("/").append(s1).append(s2).toString();
    }

    protected static String constructPostUri(String s, String s1)
    {
        return (new StringBuilder()).append(BASE_API_URL).append("/").append(s).append("/").append(s1).toString();
    }

    protected static String constructSecureGetUri(String s, String s1, String s2)
    {
        return (new StringBuilder()).append(BASE_API_URL_SECURE).append("/").append(s).append("/").append(s1).append(s2).toString();
    }

    protected static String constructSecurePostUri(String s, String s1)
    {
        return (new StringBuilder()).append(BASE_API_URL_SECURE).append("/").append(s).append("/").append(s1).toString();
    }

    public static void updateSessionFromWebview(com.flipkart.android.webview.CookieParser.Cookie cookie)
    {
        if (!StringUtils.isNullOrEmpty(cookie.sn))
        {
            FlipkartPreferenceManager.instance().saveSn(cookie.sn);
        } else
        if (!StringUtils.isNullOrEmpty(cookie.vid) && !StringUtils.isNullOrEmpty(cookie.nsid))
        {
            FlipkartPreferenceManager.instance().saveVid(cookie.vid);
            FlipkartPreferenceManager.instance().saveNsid(cookie.nsid);
            return;
        }
    }

    public void deliverError(VolleyError volleyerror)
    {
        a(volleyerror.networkResponse);
        super.deliverError(volleyerror);
    }

    protected void deliverResponse(Object obj)
    {
        b.onResponse(obj);
    }

    public Map getHeaders()
    {
        if (e != null)
        {
            return e;
        } else
        {
            return super.getHeaders();
        }
    }

    protected Map getPostParams()
    {
        return c;
    }

    protected Response parseNetworkResponse(NetworkResponse networkresponse)
    {
        a(networkresponse);
        java.io.Reader reader;
        Object obj1;
        if (networkresponse.statusCode != 200)
        {
            performJsonUpdate(null, null, false);
            return Response.error(new VolleyError(networkresponse));
        }
        reader = ResponseUtils.getJsonReader(networkresponse);
        if (a != com/flipkart/android/response/autoSuggest/AutoSuggestResponse)
        {
            break MISSING_BLOCK_LABEL_87;
        }
        obj1 = f.fromJson(reader, a);
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        return Response.error(new VolleyError(networkresponse));
        return Response.success(obj1, null);
        Object obj;
        if (a != com/flipkart/android/response/android/widget/AndroidWidgetResponseWrapper)
        {
            break MISSING_BLOCK_LABEL_148;
        }
        obj = f.fromJson(reader, a);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_128;
        }
        return Response.error(new VolleyError(networkresponse));
        performJsonUpdate(networkresponse.data, null, ResponseUtils.isResponseGZipped(networkresponse));
        return Response.success(obj, null);
        ResponseWrapper responsewrapper = (ResponseWrapper)f.fromJson(reader, a);
        if (responsewrapper == null)
        {
            break MISSING_BLOCK_LABEL_242;
        }
        a(responsewrapper.getSession());
        if (responsewrapper.getResponse() != null && (responsewrapper.getResponse() instanceof BaseResponse))
        {
            FkLogger.debug("bnm", (new StringBuilder("saving id...................................... ")).append(responsewrapper.getRequestId()).toString());
            ((BaseResponse)responsewrapper.getResponse()).setRequestId(responsewrapper.getRequestId());
        }
        if (responsewrapper == null)
        {
            break MISSING_BLOCK_LABEL_288;
        }
        boolean flag = StringUtils.isNullOrEmpty(responsewrapper.getCacheInvalidationTTL());
        if (flag)
        {
            break MISSING_BLOCK_LABEL_288;
        }
        Exception exception;
        Response response;
        try
        {
            FlipkartPreferenceManager.instance().saveTTLTime(Long.valueOf(responsewrapper.getCacheInvalidationTTL()).longValue());
        }
        catch (Exception exception1) { }
        FlipkartPreferenceManager.instance().saveTTLSavedTime(System.currentTimeMillis());
        if (responsewrapper == null)
        {
            break MISSING_BLOCK_LABEL_301;
        }
        if (responsewrapper.getResponse() != null)
        {
            break MISSING_BLOCK_LABEL_313;
        }
        return Response.error(new VolleyError(networkresponse));
        performJsonUpdate(networkresponse.data, responsewrapper.getResponse(), ResponseUtils.isResponseGZipped(networkresponse));
        if (responsewrapper.getResponse() instanceof ImageProfileMatrix)
        {
            ((ImageProfileMatrix)responsewrapper.getResponse()).setHashCode(responsewrapper.getHashCode());
        }
        response = Response.success(responsewrapper.getResponse(), HttpHeaderParser.parseCacheHeaders(networkresponse));
        return response;
        exception;
        FkLogger.printStackTrace(exception);
        if (!(exception instanceof JsonSyntaxException)) goto _L2; else goto _L1
_L1:
        ApiLogger.logError(LoggerTag.GsonException, "GsonParseException", exception, b());
_L4:
        return Response.error(new VolleyError(networkresponse));
_L2:
        if (exception instanceof JsonIOException)
        {
            ApiLogger.logError(LoggerTag.GsonException, "GsonParseException", exception, b());
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public abstract void performJsonUpdate(byte abyte0[], Object obj, boolean flag);

}
