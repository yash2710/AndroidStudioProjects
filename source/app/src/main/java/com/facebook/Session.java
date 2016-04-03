// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.facebook.internal.SessionAuthorizationType;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.facebook:
//            SharedPreferencesTokenCachingStrategy, SessionState, TokenCachingStrategy, AccessToken, 
//            Settings, AppEventsLogger, LoginActivity, SessionLoginBehavior, 
//            FacebookAuthorizationException, FacebookOperationCanceledException, AuthorizationClient, SessionDefaultAudience, 
//            FacebookException, AccessTokenSource

public class Session
    implements Serializable
{

    public static final String ACTION_ACTIVE_SESSION_CLOSED = "com.facebook.sdk.ACTIVE_SESSION_CLOSED";
    public static final String ACTION_ACTIVE_SESSION_OPENED = "com.facebook.sdk.ACTIVE_SESSION_OPENED";
    public static final String ACTION_ACTIVE_SESSION_SET = "com.facebook.sdk.ACTIVE_SESSION_SET";
    public static final String ACTION_ACTIVE_SESSION_UNSET = "com.facebook.sdk.ACTIVE_SESSION_UNSET";
    public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
    private static final String AUTH_BUNDLE_SAVE_KEY = "com.facebook.sdk.Session.authBundleKey";
    public static final int DEFAULT_AUTHORIZE_ACTIVITY_CODE = 64206;
    private static final String MANAGE_PERMISSION_PREFIX = "manage";
    private static final Set OTHER_PUBLISH_PERMISSIONS = new _cls1();
    private static final String PUBLISH_PERMISSION_PREFIX = "publish";
    private static final String SESSION_BUNDLE_SAVE_KEY = "com.facebook.sdk.Session.saveSessionKey";
    private static final Object STATIC_LOCK = new Object();
    public static final String TAG = com/facebook/Session.getCanonicalName();
    private static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
    private static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 0x15180;
    public static final String WEB_VIEW_ERROR_CODE_KEY = "com.facebook.sdk.WebViewErrorCode";
    public static final String WEB_VIEW_FAILING_URL_KEY = "com.facebook.sdk.FailingUrl";
    private static Session activeSession;
    private static final long serialVersionUID = 1L;
    private static volatile Context staticContext;
    private AppEventsLogger appEventsLogger;
    private String applicationId;
    private volatile Bundle authorizationBundle;
    private AuthorizationClient authorizationClient;
    private AutoPublishAsyncTask autoPublishAsyncTask;
    private final List callbacks;
    private volatile TokenRefreshRequest currentTokenRefreshRequest;
    private Handler handler;
    private Date lastAttemptedTokenExtendDate;
    private final Object lock;
    private AuthorizationRequest pendingAuthorizationRequest;
    private SessionState state;
    private TokenCachingStrategy tokenCachingStrategy;
    private AccessToken tokenInfo;

    public Session(Context context)
    {
        this(context, null, null, true);
    }

    Session(Context context, String s, TokenCachingStrategy tokencachingstrategy)
    {
        this(context, s, tokencachingstrategy, true);
    }

    Session(Context context, String s, TokenCachingStrategy tokencachingstrategy, boolean flag)
    {
        lastAttemptedTokenExtendDate = new Date(0L);
        lock = new Object();
        if (context != null && s == null)
        {
            s = Utility.getMetadataApplicationId(context);
        }
        Validate.notNull(s, "applicationId");
        initializeStaticContext(context);
        if (tokencachingstrategy == null)
        {
            tokencachingstrategy = new SharedPreferencesTokenCachingStrategy(staticContext);
        }
        applicationId = s;
        tokenCachingStrategy = tokencachingstrategy;
        state = SessionState.CREATED;
        pendingAuthorizationRequest = null;
        callbacks = new ArrayList();
        handler = new Handler(Looper.getMainLooper());
        Bundle bundle = null;
        if (flag)
        {
            bundle = tokencachingstrategy.load();
        }
        if (TokenCachingStrategy.hasTokenInformation(bundle))
        {
            Date date = TokenCachingStrategy.getDate(bundle, "com.facebook.TokenCachingStrategy.ExpirationDate");
            Date date1 = new Date();
            if (date == null || date.before(date1))
            {
                tokencachingstrategy.clear();
                tokenInfo = AccessToken.createEmptyToken(Collections.emptyList());
                return;
            } else
            {
                tokenInfo = AccessToken.createFromCache(bundle);
                state = SessionState.CREATED_TOKEN_LOADED;
                return;
            }
        } else
        {
            tokenInfo = AccessToken.createEmptyToken(Collections.emptyList());
            return;
        }
    }

    private Session(String s, SessionState sessionstate, AccessToken accesstoken, Date date, boolean flag, AuthorizationRequest authorizationrequest)
    {
        lastAttemptedTokenExtendDate = new Date(0L);
        lock = new Object();
        applicationId = s;
        state = sessionstate;
        tokenInfo = accesstoken;
        lastAttemptedTokenExtendDate = date;
        pendingAuthorizationRequest = authorizationrequest;
        handler = new Handler(Looper.getMainLooper());
        currentTokenRefreshRequest = null;
        tokenCachingStrategy = null;
        callbacks = new ArrayList();
    }

    Session(String s, SessionState sessionstate, AccessToken accesstoken, Date date, boolean flag, AuthorizationRequest authorizationrequest, _cls1 _pcls1)
    {
        this(s, sessionstate, accesstoken, date, flag, authorizationrequest);
    }

    private static boolean areEqual(Object obj, Object obj1)
    {
        if (obj == null)
        {
            return obj1 == null;
        } else
        {
            return obj.equals(obj1);
        }
    }

    private void autoPublishAsync()
    {
        this;
        JVM INSTR monitorenter ;
        AutoPublishAsyncTask autopublishasynctask = autoPublishAsyncTask;
        AutoPublishAsyncTask autopublishasynctask1;
        autopublishasynctask1 = null;
        if (autopublishasynctask != null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        boolean flag = Settings.getShouldAutoPublishInstall();
        autopublishasynctask1 = null;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        String s = applicationId;
        autopublishasynctask1 = null;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        autopublishasynctask1 = new AutoPublishAsyncTask(s, staticContext);
        autoPublishAsyncTask = autopublishasynctask1;
        this;
        JVM INSTR monitorexit ;
        Void avoid[];
        if (autopublishasynctask1 != null)
        {
            avoid = new Void[0];
            if (autopublishasynctask1 instanceof AsyncTask)
            {
                break MISSING_BLOCK_LABEL_89;
            }
            autopublishasynctask1.execute(avoid);
        }
        return;
        Exception exception;
        exception;
        throw exception;
        AsyncTaskInstrumentation.execute((AsyncTask)autopublishasynctask1, avoid);
        return;
    }

    private void finishAuthorization(AccessToken accesstoken, Exception exception)
    {
        SessionState sessionstate = state;
        if (accesstoken == null) goto _L2; else goto _L1
_L1:
        tokenInfo = accesstoken;
        saveTokenToCache(accesstoken);
        state = SessionState.OPENED;
_L4:
        pendingAuthorizationRequest = null;
        postStateChange(sessionstate, state, exception);
        return;
_L2:
        if (exception != null)
        {
            state = SessionState.CLOSED_LOGIN_FAILED;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void finishReauthorization(AccessToken accesstoken, Exception exception)
    {
        SessionState sessionstate = state;
        if (accesstoken != null)
        {
            tokenInfo = accesstoken;
            saveTokenToCache(accesstoken);
            state = SessionState.OPENED_TOKEN_UPDATED;
        }
        pendingAuthorizationRequest = null;
        postStateChange(sessionstate, state, exception);
    }

    public static final Session getActiveSession()
    {
        Session session;
        synchronized (STATIC_LOCK)
        {
            session = activeSession;
        }
        return session;
    }

    private AppEventsLogger getAppEventsLogger()
    {
        AppEventsLogger appeventslogger;
        synchronized (lock)
        {
            if (appEventsLogger == null)
            {
                appEventsLogger = AppEventsLogger.newLogger(staticContext, applicationId);
            }
            appeventslogger = appEventsLogger;
        }
        return appeventslogger;
    }

    private Intent getLoginActivityIntent(AuthorizationRequest authorizationrequest)
    {
        Intent intent = new Intent();
        intent.setClass(getStaticContext(), com/facebook/LoginActivity);
        intent.setAction(authorizationrequest.getLoginBehavior().toString());
        intent.putExtras(LoginActivity.populateIntentExtras(authorizationrequest.getAuthorizationClientRequest()));
        return intent;
    }

    static Context getStaticContext()
    {
        return staticContext;
    }

    private void handleAuthorizationResult(int i, AuthorizationClient.Result result)
    {
        Object obj;
        AccessToken accesstoken;
        if (i == -1)
        {
            if (result.code == AuthorizationClient.Result.Code.SUCCESS)
            {
                accesstoken = result.token;
                obj = null;
            } else
            {
                obj = new FacebookAuthorizationException(result.errorMessage);
                accesstoken = null;
            }
        } else
        if (i == 0)
        {
            obj = new FacebookOperationCanceledException(result.errorMessage);
            accesstoken = null;
        } else
        {
            obj = null;
            accesstoken = null;
        }
        logAuthorizationComplete(result.code, result.loggingExtras, ((Exception) (obj)));
        authorizationClient = null;
        finishAuthOrReauth(accesstoken, ((Exception) (obj)));
    }

    static void initializeStaticContext(Context context)
    {
        if (context != null && staticContext == null)
        {
            Context context1 = context.getApplicationContext();
            if (context1 != null)
            {
                context = context1;
            }
            staticContext = context;
        }
    }

    public static boolean isPublishPermission(String s)
    {
        return s != null && (s.startsWith("publish") || s.startsWith("manage") || OTHER_PUBLISH_PERMISSIONS.contains(s));
    }

    private void logAuthorizationComplete(AuthorizationClient.Result.Code code, Map map, Exception exception)
    {
        if (pendingAuthorizationRequest != null) goto _L2; else goto _L1
_L1:
        Bundle bundle1;
        bundle1 = AuthorizationClient.newAuthorizationLoggingBundle("");
        bundle1.putString("2_result", AuthorizationClient.Result.Code.ERROR.getLoggingValue());
        bundle1.putString("5_error_message", "Unexpected call to logAuthorizationComplete with null pendingAuthorizationRequest.");
_L6:
        bundle1.putLong("1_timestamp_ms", System.currentTimeMillis());
        getAppEventsLogger().logSdkEvent("fb_mobile_login_complete", null, bundle1);
        return;
_L2:
        Bundle bundle = AuthorizationClient.newAuthorizationLoggingBundle(pendingAuthorizationRequest.getAuthId());
        if (code != null)
        {
            bundle.putString("2_result", code.getLoggingValue());
        }
        if (exception != null && exception.getMessage() != null)
        {
            bundle.putString("5_error_message", exception.getMessage());
        }
        JSONObject jsonobject;
        String s;
        JSONObject jsonobject1;
        JSONException jsonexception;
        Iterator iterator;
        java.util.Map.Entry entry;
        if (!pendingAuthorizationRequest.loggingExtras.isEmpty())
        {
            jsonobject = new JSONObject(pendingAuthorizationRequest.loggingExtras);
        } else
        {
            jsonobject = null;
        }
        if (map == null) goto _L4; else goto _L3
_L3:
        if (jsonobject == null)
        {
            jsonobject1 = new JSONObject();
        } else
        {
            jsonobject1 = jsonobject;
        }
        for (iterator = map.entrySet().iterator(); iterator.hasNext(); jsonobject1.put((String)entry.getKey(), entry.getValue()))
        {
            entry = (java.util.Map.Entry)iterator.next();
        }

          goto _L5
        jsonexception;
        jsonobject = jsonobject1;
_L4:
        if (jsonobject != null)
        {
            if (!(jsonobject instanceof JSONObject))
            {
                s = jsonobject.toString();
            } else
            {
                s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
            }
            bundle.putString("6_extras", s);
        }
        bundle1 = bundle;
        if (true) goto _L6; else goto _L5
_L5:
        jsonobject = jsonobject1;
          goto _L4
    }

    private void logAuthorizationStart()
    {
        Bundle bundle;
        bundle = AuthorizationClient.newAuthorizationLoggingBundle(pendingAuthorizationRequest.getAuthId());
        bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        JSONObject jsonobject;
        jsonobject = new JSONObject();
        jsonobject.put("login_behavior", pendingAuthorizationRequest.loginBehavior.toString());
        jsonobject.put("request_code", pendingAuthorizationRequest.requestCode);
        jsonobject.put("is_legacy", pendingAuthorizationRequest.isLegacy);
        jsonobject.put("permissions", TextUtils.join(",", pendingAuthorizationRequest.permissions));
        jsonobject.put("default_audience", pendingAuthorizationRequest.defaultAudience.toString());
        if (jsonobject instanceof JSONObject) goto _L2; else goto _L1
_L1:
        String s1 = jsonobject.toString();
_L3:
        bundle.putString("6_extras", s1);
_L4:
        getAppEventsLogger().logSdkEvent("fb_mobile_login_start", null, bundle);
        return;
_L2:
        String s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        s1 = s;
          goto _L3
        JSONException jsonexception;
        jsonexception;
          goto _L4
    }

    private void open(OpenRequest openrequest, SessionAuthorizationType sessionauthorizationtype)
    {
        SessionState sessionstate;
label0:
        {
            validatePermissions(openrequest, sessionauthorizationtype);
            validateLoginBehavior(openrequest);
            synchronized (lock)
            {
                if (pendingAuthorizationRequest == null)
                {
                    break label0;
                }
                postStateChange(state, state, new UnsupportedOperationException("Session: an attempt was made to open a session that has a pending request."));
            }
            return;
        }
        sessionstate = state;
        _cls4..SwitchMap.com.facebook.SessionState[state.ordinal()];
        JVM INSTR tableswitch 1 3: default 92
    //                   1 110
    //                   2 92
    //                   3 178;
           goto _L1 _L2 _L1 _L3
_L1:
        throw new UnsupportedOperationException("Session: an attempt was made to open an already opened session.");
_L2:
        SessionState sessionstate1;
        sessionstate1 = SessionState.OPENING;
        state = sessionstate1;
        if (openrequest != null) goto _L5; else goto _L4
_L4:
        throw new IllegalArgumentException("openRequest cannot be null when opening a new Session");
_L5:
        pendingAuthorizationRequest = openrequest;
_L12:
        if (openrequest == null) goto _L7; else goto _L6
_L6:
        addCallback(openrequest.getCallback());
_L7:
        postStateChange(sessionstate, sessionstate1, null);
        obj;
        JVM INSTR monitorexit ;
        if (sessionstate1 == SessionState.OPENING)
        {
            authorize(openrequest);
            return;
        } else
        {
            return;
        }
_L3:
        if (openrequest == null) goto _L9; else goto _L8
_L8:
        if (!Utility.isNullOrEmpty(openrequest.getPermissions()) && !Utility.isSubset(openrequest.getPermissions(), getPermissions()))
        {
            pendingAuthorizationRequest = openrequest;
        }
_L9:
        if (pendingAuthorizationRequest != null) goto _L11; else goto _L10
_L10:
        sessionstate1 = SessionState.OPENED;
        state = sessionstate1;
          goto _L12
_L11:
        sessionstate1 = SessionState.OPENING;
        state = sessionstate1;
          goto _L12
    }

    public static Session openActiveSession(Activity activity, boolean flag, StatusCallback statuscallback)
    {
        return openActiveSession(((Context) (activity)), flag, (new OpenRequest(activity)).setCallback(statuscallback));
    }

    public static Session openActiveSession(Context context, Fragment fragment, boolean flag, StatusCallback statuscallback)
    {
        return openActiveSession(context, flag, (new OpenRequest(fragment)).setCallback(statuscallback));
    }

    private static Session openActiveSession(Context context, boolean flag, OpenRequest openrequest)
    {
        Session session = (new Builder(context)).build();
        if (SessionState.CREATED_TOKEN_LOADED.equals(session.getState()) || flag)
        {
            setActiveSession(session);
            session.openForRead(openrequest);
            return session;
        } else
        {
            return null;
        }
    }

    public static Session openActiveSessionFromCache(Context context)
    {
        return openActiveSession(context, false, ((OpenRequest) (null)));
    }

    public static Session openActiveSessionWithAccessToken(Context context, AccessToken accesstoken, StatusCallback statuscallback)
    {
        Session session = new Session(context, null, null, false);
        setActiveSession(session);
        session.open(accesstoken, statuscallback);
        return session;
    }

    static void postActiveSessionAction(String s)
    {
        Intent intent = new Intent(s);
        LocalBroadcastManager.getInstance(getStaticContext()).sendBroadcast(intent);
    }

    private void readObject(ObjectInputStream objectinputstream)
    {
        throw new InvalidObjectException("Cannot readObject, serialization proxy required");
    }

    private void requestNewPermissions(NewPermissionsRequest newpermissionsrequest, SessionAuthorizationType sessionauthorizationtype)
    {
label0:
        {
            validatePermissions(newpermissionsrequest, sessionauthorizationtype);
            validateLoginBehavior(newpermissionsrequest);
            if (newpermissionsrequest == null)
            {
                break MISSING_BLOCK_LABEL_77;
            }
            synchronized (lock)
            {
                if (pendingAuthorizationRequest != null)
                {
                    throw new UnsupportedOperationException("Session: an attempt was made to request new permissions for a session that has a pending request.");
                }
                break label0;
            }
        }
        if (!state.isOpened())
        {
            break MISSING_BLOCK_LABEL_78;
        }
        pendingAuthorizationRequest = newpermissionsrequest;
        obj;
        JVM INSTR monitorexit ;
        newpermissionsrequest.setValidateSameFbidAsToken(getAccessToken());
        authorize(newpermissionsrequest);
        return;
        if (state.isClosed())
        {
            throw new UnsupportedOperationException("Session: an attempt was made to request new permissions for a session that has been closed.");
        } else
        {
            throw new UnsupportedOperationException("Session: an attempt was made to request new permissions for a session that is not currently open.");
        }
    }

    private boolean resolveIntent(Intent intent)
    {
        return getStaticContext().getPackageManager().resolveActivity(intent, 0) != null;
    }

    public static final Session restoreSession(Context context, TokenCachingStrategy tokencachingstrategy, StatusCallback statuscallback, Bundle bundle)
    {
        byte abyte0[];
        if (bundle == null)
        {
            return null;
        }
        abyte0 = bundle.getByteArray("com.facebook.sdk.Session.saveSessionKey");
        if (abyte0 == null) goto _L2; else goto _L1
_L1:
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        Session session;
        session = (Session)(new ObjectInputStream(bytearrayinputstream)).readObject();
        initializeStaticContext(context);
        if (tokencachingstrategy == null) goto _L4; else goto _L3
_L3:
        session.tokenCachingStrategy = tokencachingstrategy;
_L5:
        if (statuscallback == null)
        {
            break MISSING_BLOCK_LABEL_71;
        }
        session.addCallback(statuscallback);
        session.authorizationBundle = bundle.getBundle("com.facebook.sdk.Session.authBundleKey");
        return session;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        FkLogger.warn(TAG, "Unable to restore session", classnotfoundexception);
_L2:
        return null;
_L4:
        session.tokenCachingStrategy = new SharedPreferencesTokenCachingStrategy(context);
          goto _L5
        IOException ioexception;
        ioexception;
        FkLogger.warn(TAG, "Unable to restore session.", ioexception);
          goto _L2
    }

    private static void runWithHandlerOrExecutor(Handler handler1, Runnable runnable)
    {
        if (handler1 != null)
        {
            handler1.post(runnable);
            return;
        } else
        {
            Settings.getExecutor().execute(runnable);
            return;
        }
    }

    public static final void saveSession(Session session, Bundle bundle)
    {
        if (bundle != null && session != null && !bundle.containsKey("com.facebook.sdk.Session.saveSessionKey"))
        {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            try
            {
                (new ObjectOutputStream(bytearrayoutputstream)).writeObject(session);
            }
            catch (IOException ioexception)
            {
                throw new FacebookException("Unable to save session.", ioexception);
            }
            bundle.putByteArray("com.facebook.sdk.Session.saveSessionKey", bytearrayoutputstream.toByteArray());
            bundle.putBundle("com.facebook.sdk.Session.authBundleKey", session.authorizationBundle);
        }
    }

    private void saveTokenToCache(AccessToken accesstoken)
    {
        if (accesstoken != null && tokenCachingStrategy != null)
        {
            tokenCachingStrategy.save(accesstoken.toCacheBundle());
        }
    }

    public static final void setActiveSession(Session session)
    {
        Object obj = STATIC_LOCK;
        obj;
        JVM INSTR monitorenter ;
        Session session1;
        if (session == activeSession)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        session1 = activeSession;
        if (session1 == null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        session1.close();
        activeSession = session;
        if (session1 == null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_UNSET");
        if (session == null)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_SET");
        if (session.isOpened())
        {
            postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_OPENED");
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void tryLegacyAuth(AuthorizationRequest authorizationrequest)
    {
        authorizationClient = new AuthorizationClient();
        authorizationClient.setOnCompletedListener(new _cls2());
        authorizationClient.setContext(getStaticContext());
        authorizationClient.startOrContinueAuth(authorizationrequest.getAuthorizationClientRequest());
    }

    private boolean tryLoginActivity(AuthorizationRequest authorizationrequest)
    {
        Intent intent = getLoginActivityIntent(authorizationrequest);
        if (!resolveIntent(intent))
        {
            return false;
        }
        try
        {
            authorizationrequest.getStartActivityDelegate().startActivityForResult(intent, authorizationrequest.getRequestCode());
        }
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            return false;
        }
        return true;
    }

    private void validateLoginBehavior(AuthorizationRequest authorizationrequest)
    {
        if (authorizationrequest != null && !authorizationrequest.isLegacy)
        {
            Intent intent = new Intent();
            intent.setClass(getStaticContext(), com/facebook/LoginActivity);
            if (!resolveIntent(intent))
            {
                Object aobj[] = new Object[2];
                aobj[0] = authorizationrequest.getLoginBehavior();
                aobj[1] = com/facebook/LoginActivity.getName();
                throw new FacebookException(String.format("Cannot use SessionLoginBehavior %s when %s is not declared as an activity in AndroidManifest.xml", aobj));
            }
        }
    }

    private void validatePermissions(AuthorizationRequest authorizationrequest, SessionAuthorizationType sessionauthorizationtype)
    {
        if (authorizationrequest == null || Utility.isNullOrEmpty(authorizationrequest.getPermissions()))
        {
            if (SessionAuthorizationType.PUBLISH.equals(sessionauthorizationtype))
            {
                throw new FacebookException("Cannot request publish or manage authorization with no permissions.");
            }
        } else
        {
            Iterator iterator = authorizationrequest.getPermissions().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                String s = (String)iterator.next();
                if (isPublishPermission(s))
                {
                    if (SessionAuthorizationType.READ.equals(sessionauthorizationtype))
                    {
                        throw new FacebookException(String.format("Cannot pass a publish or manage permission (%s) to a request for read authorization", new Object[] {
                            s
                        }));
                    }
                } else
                if (SessionAuthorizationType.PUBLISH.equals(sessionauthorizationtype))
                {
                    FkLogger.warn(TAG, String.format("Should not pass a read permission (%s) to a request for publish or manage authorization", new Object[] {
                        s
                    }));
                }
            } while (true);
        }
    }

    private Object writeReplace()
    {
        return new SerializationProxyV1(applicationId, state, tokenInfo, lastAttemptedTokenExtendDate, false, pendingAuthorizationRequest);
    }

    public final void addCallback(StatusCallback statuscallback)
    {
        List list = callbacks;
        list;
        JVM INSTR monitorenter ;
        if (statuscallback == null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        if (!callbacks.contains(statuscallback))
        {
            callbacks.add(statuscallback);
        }
        list;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void authorize(AuthorizationRequest authorizationrequest)
    {
        authorizationrequest.setApplicationId(applicationId);
        autoPublishAsync();
        logAuthorizationStart();
        boolean flag = tryLoginActivity(authorizationrequest);
        Map map = pendingAuthorizationRequest.loggingExtras;
        String s;
        boolean flag1;
        Object obj;
        SessionState sessionstate;
        FacebookException facebookexception;
        if (flag)
        {
            s = "1";
        } else
        {
            s = "0";
        }
        map.put("try_login_activity", s);
        Exception exception;
        if (!flag && authorizationrequest.isLegacy)
        {
            pendingAuthorizationRequest.loggingExtras.put("try_legacy", "1");
            tryLegacyAuth(authorizationrequest);
            flag1 = true;
        } else
        {
            flag1 = flag;
        }
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_187;
        }
        obj = lock;
        obj;
        JVM INSTR monitorenter ;
        sessionstate = state;
        switch (_cls4..SwitchMap.com.facebook.SessionState[state.ordinal()])
        {
        default:
            state = SessionState.CLOSED_LOGIN_FAILED;
            facebookexception = new FacebookException("Log in attempt failed: LoginActivity could not be started, and not legacy request");
            logAuthorizationComplete(AuthorizationClient.Result.Code.ERROR, null, facebookexception);
            postStateChange(sessionstate, state, facebookexception);
            break;

        case 6: // '\006'
        case 7: // '\007'
            break MISSING_BLOCK_LABEL_197;
        }
        return;
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void close()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        SessionState sessionstate = state;
        _cls4..SwitchMap.com.facebook.SessionState[state.ordinal()];
        JVM INSTR tableswitch 1 5: default 56
    //                   1 59
    //                   2 59
    //                   3 93
    //                   4 93
    //                   5 93;
           goto _L1 _L2 _L2 _L3 _L3 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_93;
_L4:
        return;
_L2:
        state = SessionState.CLOSED_LOGIN_FAILED;
        postStateChange(sessionstate, state, new FacebookException("Log in attempt aborted."));
          goto _L4
        Exception exception;
        exception;
        throw exception;
        state = SessionState.CLOSED;
        postStateChange(sessionstate, state, null);
          goto _L4
    }

    public final void closeAndClearTokenInformation()
    {
        if (tokenCachingStrategy != null)
        {
            tokenCachingStrategy.clear();
        }
        Utility.clearFacebookCookies(staticContext);
        Utility.clearCaches(staticContext);
        close();
    }

    public boolean equals(Object obj)
    {
        Session session;
        if (obj instanceof Session)
        {
            if (areEqual((session = (Session)obj).applicationId, applicationId) && areEqual(session.authorizationBundle, authorizationBundle) && areEqual(session.state, state) && areEqual(session.getExpirationDate(), getExpirationDate()))
            {
                return true;
            }
        }
        return false;
    }

    void extendAccessToken()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        TokenRefreshRequest tokenrefreshrequest = currentTokenRefreshRequest;
        TokenRefreshRequest tokenrefreshrequest1;
        tokenrefreshrequest1 = null;
        if (tokenrefreshrequest != null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        tokenrefreshrequest1 = new TokenRefreshRequest();
        currentTokenRefreshRequest = tokenrefreshrequest1;
        obj;
        JVM INSTR monitorexit ;
        if (tokenrefreshrequest1 != null)
        {
            tokenrefreshrequest1.bind();
        }
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void extendAccessTokenIfNeeded()
    {
        if (shouldExtendAccessToken())
        {
            extendAccessToken();
        }
    }

    void extendTokenCompleted(Bundle bundle)
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        SessionState sessionstate = state;
        _cls4..SwitchMap.com.facebook.SessionState[state.ordinal()];
        JVM INSTR tableswitch 4 5: default 48
    //                   4 78
    //                   5 96;
           goto _L1 _L2 _L3
_L1:
        FkLogger.debug(TAG, (new StringBuilder("refreshToken ignored in state ")).append(state).toString());
        return;
_L2:
        state = SessionState.OPENED_TOKEN_UPDATED;
        postStateChange(sessionstate, state, null);
_L3:
        tokenInfo = AccessToken.createFromRefresh(tokenInfo, bundle);
        if (tokenCachingStrategy != null)
        {
            tokenCachingStrategy.save(tokenInfo.toCacheBundle());
        }
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void finishAuthOrReauth(AccessToken accesstoken, Exception exception)
    {
        if (accesstoken != null && accesstoken.isInvalid())
        {
            accesstoken = null;
            exception = new FacebookException("Invalid access token.");
        }
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        _cls4..SwitchMap.com.facebook.SessionState[state.ordinal()];
        JVM INSTR tableswitch 1 7: default 84
    //                   1 112
    //                   2 87
    //                   3 112
    //                   4 103
    //                   5 103
    //                   6 112
    //                   7 112;
           goto _L1 _L2 _L3 _L2 _L4 _L4 _L2 _L2
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break MISSING_BLOCK_LABEL_112;
_L5:
        return;
_L3:
        finishAuthorization(accesstoken, exception);
          goto _L5
        Exception exception1;
        exception1;
        throw exception1;
_L4:
        finishReauthorization(accesstoken, exception);
          goto _L5
        FkLogger.debug(TAG, (new StringBuilder("Unexpected call to finishAuthOrReauth in state ")).append(state).toString());
          goto _L5
    }

    public final String getAccessToken()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (tokenInfo != null) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        obj;
        JVM INSTR monitorexit ;
        return s;
_L2:
        String s1 = tokenInfo.getToken();
        s = s1;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final String getApplicationId()
    {
        return applicationId;
    }

    public final Bundle getAuthorizationBundle()
    {
        Bundle bundle;
        synchronized (lock)
        {
            bundle = authorizationBundle;
        }
        return bundle;
    }

    public final Date getExpirationDate()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (tokenInfo != null) goto _L2; else goto _L1
_L1:
        Date date = null;
_L4:
        obj;
        JVM INSTR monitorexit ;
        return date;
_L2:
        Date date1 = tokenInfo.getExpires();
        date = date1;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    Date getLastAttemptedTokenExtendDate()
    {
        return lastAttemptedTokenExtendDate;
    }

    public final List getPermissions()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (tokenInfo != null) goto _L2; else goto _L1
_L1:
        List list = null;
_L4:
        obj;
        JVM INSTR monitorexit ;
        return list;
_L2:
        List list1 = tokenInfo.getPermissions();
        list = list1;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final SessionState getState()
    {
        SessionState sessionstate;
        synchronized (lock)
        {
            sessionstate = state;
        }
        return sessionstate;
    }

    AccessToken getTokenInfo()
    {
        return tokenInfo;
    }

    public int hashCode()
    {
        return 0;
    }

    public final boolean isClosed()
    {
        boolean flag;
        synchronized (lock)
        {
            flag = state.isClosed();
        }
        return flag;
    }

    public final boolean isOpened()
    {
        boolean flag;
        synchronized (lock)
        {
            flag = state.isOpened();
        }
        return flag;
    }

    public final boolean onActivityResult(Activity activity, int i, int j, Intent intent)
    {
        exception;
        throw exception;
        if (authorizationClient != null)
        {
            authorizationClient.onActivityResult(i, j, intent);
            return true;
        }
        obj1 = null;
_L2:
        if (obj1 == null)
        {
            obj1 = new FacebookException("Unexpected call to Session.onActivityResult");
        }
        logAuthorizationComplete(code, null, ((Exception) (obj1)));
        finishAuthOrReauth(null, ((Exception) (obj1)));
        return true;
label0:
        {
            Validate.notNull(activity, "currentActivity");
            initializeStaticContext(activity);
            synchronized (lock)
            {
                if (pendingAuthorizationRequest != null && i == pendingAuthorizationRequest.getRequestCode())
                {
                    break label0;
                }
            }
            return false;
        }
        AuthorizationClient.Result.Code code = AuthorizationClient.Result.Code.ERROR;
        Object obj1;
        if (intent != null)
        {
            AuthorizationClient.Result result = (AuthorizationClient.Result)intent.getSerializableExtra("com.facebook.LoginActivity:Result");
            if (result != null)
            {
                handleAuthorizationResult(j, result);
                return true;
            }
            break MISSING_BLOCK_LABEL_91;
        }
        if (j == 0)
        {
            FacebookOperationCanceledException facebookoperationcanceledexception = new FacebookOperationCanceledException("User canceled operation.");
            AuthorizationClient.Result.Code code1 = AuthorizationClient.Result.Code.CANCEL;
            obj1 = facebookoperationcanceledexception;
            code = code1;
        } else
        {
            obj1 = null;
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final void open(AccessToken accesstoken, StatusCallback statuscallback)
    {
        synchronized (lock)
        {
            if (pendingAuthorizationRequest != null)
            {
                throw new UnsupportedOperationException("Session: an attempt was made to open a session that has a pending request.");
            }
            break MISSING_BLOCK_LABEL_32;
        }
        if (state.isClosed())
        {
            throw new UnsupportedOperationException("Session: an attempt was made to open a previously-closed session.");
        }
        if (state != SessionState.CREATED && state != SessionState.CREATED_TOKEN_LOADED)
        {
            throw new UnsupportedOperationException("Session: an attempt was made to open an already opened session.");
        }
        if (statuscallback == null)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        addCallback(statuscallback);
        tokenInfo = accesstoken;
        if (tokenCachingStrategy != null)
        {
            tokenCachingStrategy.save(accesstoken.toCacheBundle());
        }
        SessionState sessionstate = state;
        state = SessionState.OPENED;
        postStateChange(sessionstate, state, null);
        obj;
        JVM INSTR monitorexit ;
        autoPublishAsync();
        return;
    }

    public final void openForPublish(OpenRequest openrequest)
    {
        open(openrequest, SessionAuthorizationType.PUBLISH);
    }

    public final void openForRead(OpenRequest openrequest)
    {
        open(openrequest, SessionAuthorizationType.READ);
    }

    void postStateChange(SessionState sessionstate, final SessionState newState, final Exception exception)
    {
        if (sessionstate != newState || sessionstate == SessionState.OPENED_TOKEN_UPDATED || exception != null)
        {
            if (newState.isClosed())
            {
                tokenInfo = AccessToken.createEmptyToken(Collections.emptyList());
            }
            synchronized (callbacks)
            {
                _cls3 _lcls3 = new _cls3();
                runWithHandlerOrExecutor(handler, _lcls3);
            }
            if (this == activeSession && sessionstate.isOpened() != newState.isOpened())
            {
                if (newState.isOpened())
                {
                    postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_OPENED");
                    return;
                } else
                {
                    postActiveSessionAction("com.facebook.sdk.ACTIVE_SESSION_CLOSED");
                    return;
                }
            }
        }
    }

    public final void removeCallback(StatusCallback statuscallback)
    {
        synchronized (callbacks)
        {
            callbacks.remove(statuscallback);
        }
    }

    public final void requestNewPublishPermissions(NewPermissionsRequest newpermissionsrequest)
    {
        requestNewPermissions(newpermissionsrequest, SessionAuthorizationType.PUBLISH);
    }

    public final void requestNewReadPermissions(NewPermissionsRequest newpermissionsrequest)
    {
        requestNewPermissions(newpermissionsrequest, SessionAuthorizationType.READ);
    }

    void setCurrentTokenRefreshRequest(TokenRefreshRequest tokenrefreshrequest)
    {
        currentTokenRefreshRequest = tokenrefreshrequest;
    }

    void setLastAttemptedTokenExtendDate(Date date)
    {
        lastAttemptedTokenExtendDate = date;
    }

    void setTokenInfo(AccessToken accesstoken)
    {
        tokenInfo = accesstoken;
    }

    boolean shouldExtendAccessToken()
    {
        if (currentTokenRefreshRequest == null)
        {
            Date date = new Date();
            if (state.isOpened() && tokenInfo.getSource().canExtendToken() && date.getTime() - lastAttemptedTokenExtendDate.getTime() > 0x36ee80L && date.getTime() - tokenInfo.getLastRefresh().getTime() > 0x5265c00L)
            {
                return true;
            }
        }
        return false;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder("{Session")).append(" state:").append(state).append(", token:");
        Object obj;
        StringBuilder stringbuilder1;
        String s;
        if (tokenInfo == null)
        {
            obj = "null";
        } else
        {
            obj = tokenInfo;
        }
        stringbuilder1 = stringbuilder.append(obj).append(", appId:");
        if (applicationId == null)
        {
            s = "null";
        } else
        {
            s = applicationId;
        }
        return stringbuilder1.append(s).append("}").toString();
    }






/*
    static TokenRefreshRequest access$1202(Session session, TokenRefreshRequest tokenrefreshrequest)
    {
        session.currentTokenRefreshRequest = tokenrefreshrequest;
        return tokenrefreshrequest;
    }

*/


/*
    static AutoPublishAsyncTask access$1402(Session session, AutoPublishAsyncTask autopublishasynctask)
    {
        session.autoPublishAsyncTask = autopublishasynctask;
        return autopublishasynctask;
    }

*/




    private class AutoPublishAsyncTask extends AsyncTask
        implements TraceFieldInterface
    {

        public Trace _nr_trace;
        private final Context mApplicationContext;
        private final String mApplicationId;
        final Session this$0;

        public void _nr_setTrace(Trace trace)
        {
            try
            {
                _nr_trace = trace;
                return;
            }
            catch (Exception exception)
            {
                return;
            }
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            TraceMachine.enterMethod(_nr_trace, "Session$AutoPublishAsyncTask#doInBackground", null);
_L1:
            Void void1 = doInBackground((Void[])aobj);
            TraceMachine.exitMethod();
            TraceMachine.unloadTraceContext(this);
            return void1;
            NoSuchFieldError nosuchfielderror;
            nosuchfielderror;
            TraceMachine.enterMethod(null, "Session$AutoPublishAsyncTask#doInBackground", null);
              goto _L1
        }

        protected transient Void doInBackground(Void avoid[])
        {
            try
            {
                Settings.publishInstallAndWaitForResponse(mApplicationContext, mApplicationId, true);
            }
            catch (Exception exception)
            {
                Utility.logd("Facebook-publish", exception);
            }
            return null;
        }

        protected volatile void onPostExecute(Object obj)
        {
            TraceMachine.enterMethod(_nr_trace, "Session$AutoPublishAsyncTask#onPostExecute", null);
_L1:
            onPostExecute((Void)obj);
            TraceMachine.exitMethod();
            return;
            NoSuchFieldError nosuchfielderror;
            nosuchfielderror;
            TraceMachine.enterMethod(null, "Session$AutoPublishAsyncTask#onPostExecute", null);
              goto _L1
        }

        protected void onPostExecute(Void void1)
        {
            synchronized (Session.this)
            {
                autoPublishAsyncTask = null;
            }
        }

        public AutoPublishAsyncTask(String s, Context context)
        {
            this$0 = Session.this;
            super();
            mApplicationId = s;
            mApplicationContext = context.getApplicationContext();
        }
    }


    private class AuthorizationRequest
        implements Serializable
    {

        private static final long serialVersionUID = 1L;
        private String applicationId;
        private final String authId;
        private SessionDefaultAudience defaultAudience;
        private boolean isLegacy;
        private final Map loggingExtras;
        private SessionLoginBehavior loginBehavior;
        private List permissions;
        private int requestCode;
        private final StartActivityDelegate startActivityDelegate;
        private StatusCallback statusCallback;
        private String validateSameFbidAsToken;

        private void readObject(ObjectInputStream objectinputstream)
        {
            throw new InvalidObjectException("Cannot readObject, serialization proxy required");
        }

        String getApplicationId()
        {
            return applicationId;
        }

        String getAuthId()
        {
            return authId;
        }

        AuthorizationClient.AuthorizationRequest getAuthorizationClientRequest()
        {
            class _cls4
                implements AuthorizationClient.StartActivityDelegate
            {

                final AuthorizationRequest this$0;

                public Activity getActivityContext()
                {
                    return startActivityDelegate.getActivityContext();
                }

                public void startActivityForResult(Intent intent, int i)
                {
                    startActivityDelegate.startActivityForResult(intent, i);
                }

                _cls4()
                {
                    this$0 = AuthorizationRequest.this;
                    super();
                }
            }

            _cls4 _lcls4 = new _cls4();
            return new AuthorizationClient.AuthorizationRequest(loginBehavior, requestCode, isLegacy, permissions, defaultAudience, applicationId, validateSameFbidAsToken, _lcls4, authId);
        }

        StatusCallback getCallback()
        {
            return statusCallback;
        }

        SessionDefaultAudience getDefaultAudience()
        {
            return defaultAudience;
        }

        SessionLoginBehavior getLoginBehavior()
        {
            return loginBehavior;
        }

        List getPermissions()
        {
            return permissions;
        }

        int getRequestCode()
        {
            return requestCode;
        }

        StartActivityDelegate getStartActivityDelegate()
        {
            return startActivityDelegate;
        }

        String getValidateSameFbidAsToken()
        {
            return validateSameFbidAsToken;
        }

        boolean isLegacy()
        {
            return isLegacy;
        }

        void setApplicationId(String s)
        {
            applicationId = s;
        }

        AuthorizationRequest setCallback(StatusCallback statuscallback)
        {
            statusCallback = statuscallback;
            return this;
        }

        AuthorizationRequest setDefaultAudience(SessionDefaultAudience sessiondefaultaudience)
        {
            if (sessiondefaultaudience != null)
            {
                defaultAudience = sessiondefaultaudience;
            }
            return this;
        }

        public void setIsLegacy(boolean flag)
        {
            isLegacy = flag;
        }

        AuthorizationRequest setLoginBehavior(SessionLoginBehavior sessionloginbehavior)
        {
            if (sessionloginbehavior != null)
            {
                loginBehavior = sessionloginbehavior;
            }
            return this;
        }

        AuthorizationRequest setPermissions(List list)
        {
            if (list != null)
            {
                permissions = list;
            }
            return this;
        }

        transient AuthorizationRequest setPermissions(String as[])
        {
            return setPermissions(Arrays.asList(as));
        }

        AuthorizationRequest setRequestCode(int i)
        {
            if (i >= 0)
            {
                requestCode = i;
            }
            return this;
        }

        void setValidateSameFbidAsToken(String s)
        {
            validateSameFbidAsToken = s;
        }

        Object writeReplace()
        {
            class AuthRequestSerializationProxyV1
                implements Serializable
            {

                private static final long serialVersionUID = 0x8697a02191312db1L;
                private final String applicationId;
                private final String defaultAudience;
                private boolean isLegacy;
                private final SessionLoginBehavior loginBehavior;
                private final List permissions;
                private final int requestCode;
                private final String validateSameFbidAsToken;

                private Object readResolve()
                {
                    return new AuthorizationRequest(loginBehavior, requestCode, permissions, defaultAudience, isLegacy, applicationId, validateSameFbidAsToken, null);
                }

                private AuthRequestSerializationProxyV1(SessionLoginBehavior sessionloginbehavior, int i, List list, String s, boolean flag, String s1, String s2)
                {
                    loginBehavior = sessionloginbehavior;
                    requestCode = i;
                    permissions = list;
                    defaultAudience = s;
                    isLegacy = flag;
                    applicationId = s1;
                    validateSameFbidAsToken = s2;
                }

                AuthRequestSerializationProxyV1(SessionLoginBehavior sessionloginbehavior, int i, List list, String s, boolean flag, String s1, String s2, 
                        _cls1 _pcls1)
                {
                    this(sessionloginbehavior, i, list, s, flag, s1, s2);
                }
            }

            return new AuthRequestSerializationProxyV1(loginBehavior, requestCode, permissions, defaultAudience.name(), isLegacy, applicationId, validateSameFbidAsToken, null);
        }








        AuthorizationRequest(final Activity activity)
        {
            loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
            requestCode = 64206;
            isLegacy = false;
            permissions = Collections.emptyList();
            defaultAudience = SessionDefaultAudience.FRIENDS;
            authId = UUID.randomUUID().toString();
            loggingExtras = new HashMap();
            class _cls1
                implements StartActivityDelegate
            {

                final AuthorizationRequest this$0;
                final Activity val$activity;

                public Activity getActivityContext()
                {
                    return activity;
                }

                public void startActivityForResult(Intent intent, int i)
                {
                    activity.startActivityForResult(intent, i);
                }

                _cls1()
                {
                    this$0 = AuthorizationRequest.this;
                    activity = activity1;
                    super();
                }
            }

            startActivityDelegate = new _cls1();
        }

        AuthorizationRequest(final Fragment fragment)
        {
            loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
            requestCode = 64206;
            isLegacy = false;
            permissions = Collections.emptyList();
            defaultAudience = SessionDefaultAudience.FRIENDS;
            authId = UUID.randomUUID().toString();
            loggingExtras = new HashMap();
            class _cls2
                implements StartActivityDelegate
            {

                final AuthorizationRequest this$0;
                final Fragment val$fragment;

                public Activity getActivityContext()
                {
                    return fragment.getActivity();
                }

                public void startActivityForResult(Intent intent, int i)
                {
                    fragment.startActivityForResult(intent, i);
                }

                _cls2()
                {
                    this$0 = AuthorizationRequest.this;
                    fragment = fragment1;
                    super();
                }
            }

            startActivityDelegate = new _cls2();
        }

        private AuthorizationRequest(SessionLoginBehavior sessionloginbehavior, int i, List list, String s, boolean flag, String s1, String s2)
        {
            loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
            requestCode = 64206;
            isLegacy = false;
            permissions = Collections.emptyList();
            defaultAudience = SessionDefaultAudience.FRIENDS;
            authId = UUID.randomUUID().toString();
            loggingExtras = new HashMap();
            class _cls3
                implements StartActivityDelegate
            {

                final AuthorizationRequest this$0;

                public Activity getActivityContext()
                {
                    throw new UnsupportedOperationException("Cannot create an AuthorizationRequest without a valid Activity or Fragment");
                }

                public void startActivityForResult(Intent intent, int j)
                {
                    throw new UnsupportedOperationException("Cannot create an AuthorizationRequest without a valid Activity or Fragment");
                }

                _cls3()
                {
                    this$0 = AuthorizationRequest.this;
                    super();
                }
            }

            startActivityDelegate = new _cls3();
            loginBehavior = sessionloginbehavior;
            requestCode = i;
            permissions = list;
            defaultAudience = SessionDefaultAudience.valueOf(s);
            isLegacy = flag;
            applicationId = s1;
            validateSameFbidAsToken = s2;
        }

        AuthorizationRequest(SessionLoginBehavior sessionloginbehavior, int i, List list, String s, boolean flag, String s1, String s2, 
                _cls1 _pcls1)
        {
            this(sessionloginbehavior, i, list, s, flag, s1, s2);
        }
    }


    private class _cls4
    {

        static final int $SwitchMap$com$facebook$SessionState[];

        static 
        {
            $SwitchMap$com$facebook$SessionState = new int[SessionState.values().length];
            try
            {
                $SwitchMap$com$facebook$SessionState[SessionState.CREATED.ordinal()] = 1;
            }
            catch (NoSuchFieldError nosuchfielderror) { }
            try
            {
                $SwitchMap$com$facebook$SessionState[SessionState.OPENING.ordinal()] = 2;
            }
            catch (NoSuchFieldError nosuchfielderror1) { }
            try
            {
                $SwitchMap$com$facebook$SessionState[SessionState.CREATED_TOKEN_LOADED.ordinal()] = 3;
            }
            catch (NoSuchFieldError nosuchfielderror2) { }
            try
            {
                $SwitchMap$com$facebook$SessionState[SessionState.OPENED.ordinal()] = 4;
            }
            catch (NoSuchFieldError nosuchfielderror3) { }
            try
            {
                $SwitchMap$com$facebook$SessionState[SessionState.OPENED_TOKEN_UPDATED.ordinal()] = 5;
            }
            catch (NoSuchFieldError nosuchfielderror4) { }
            try
            {
                $SwitchMap$com$facebook$SessionState[SessionState.CLOSED.ordinal()] = 6;
            }
            catch (NoSuchFieldError nosuchfielderror5) { }
            try
            {
                $SwitchMap$com$facebook$SessionState[SessionState.CLOSED_LOGIN_FAILED.ordinal()] = 7;
            }
            catch (NoSuchFieldError nosuchfielderror6)
            {
                return;
            }
        }
    }


    private class OpenRequest extends AuthorizationRequest
    {

        private static final long serialVersionUID = 1L;

        public final volatile AuthorizationRequest setCallback(StatusCallback statuscallback)
        {
            return setCallback(statuscallback);
        }

        public final OpenRequest setCallback(StatusCallback statuscallback)
        {
            super.setCallback(statuscallback);
            return this;
        }

        public final volatile AuthorizationRequest setDefaultAudience(SessionDefaultAudience sessiondefaultaudience)
        {
            return setDefaultAudience(sessiondefaultaudience);
        }

        public final OpenRequest setDefaultAudience(SessionDefaultAudience sessiondefaultaudience)
        {
            super.setDefaultAudience(sessiondefaultaudience);
            return this;
        }

        public final volatile AuthorizationRequest setLoginBehavior(SessionLoginBehavior sessionloginbehavior)
        {
            return setLoginBehavior(sessionloginbehavior);
        }

        public final OpenRequest setLoginBehavior(SessionLoginBehavior sessionloginbehavior)
        {
            super.setLoginBehavior(sessionloginbehavior);
            return this;
        }

        public final volatile AuthorizationRequest setPermissions(List list)
        {
            return setPermissions(list);
        }

        public final volatile AuthorizationRequest setPermissions(String as[])
        {
            return setPermissions(as);
        }

        public final OpenRequest setPermissions(List list)
        {
            super.setPermissions(list);
            return this;
        }

        public final transient OpenRequest setPermissions(String as[])
        {
            super.setPermissions(as);
            return this;
        }

        public final volatile AuthorizationRequest setRequestCode(int i)
        {
            return setRequestCode(i);
        }

        public final OpenRequest setRequestCode(int i)
        {
            super.setRequestCode(i);
            return this;
        }

        public OpenRequest(Activity activity)
        {
            super(activity);
        }

        public OpenRequest(Fragment fragment)
        {
            super(fragment);
        }
    }


    private class Builder
    {

        private String applicationId;
        private final Context context;
        private TokenCachingStrategy tokenCachingStrategy;

        public final Session build()
        {
            return new Session(context, applicationId, tokenCachingStrategy);
        }

        public final Builder setApplicationId(String s)
        {
            applicationId = s;
            return this;
        }

        public final Builder setTokenCachingStrategy(TokenCachingStrategy tokencachingstrategy)
        {
            tokenCachingStrategy = tokencachingstrategy;
            return this;
        }

        public Builder(Context context1)
        {
            context = context1;
        }
    }


    private class NewPermissionsRequest extends AuthorizationRequest
    {

        private static final long serialVersionUID = 1L;

        public final volatile AuthorizationRequest setCallback(StatusCallback statuscallback)
        {
            return setCallback(statuscallback);
        }

        public final NewPermissionsRequest setCallback(StatusCallback statuscallback)
        {
            super.setCallback(statuscallback);
            return this;
        }

        public final volatile AuthorizationRequest setDefaultAudience(SessionDefaultAudience sessiondefaultaudience)
        {
            return setDefaultAudience(sessiondefaultaudience);
        }

        public final NewPermissionsRequest setDefaultAudience(SessionDefaultAudience sessiondefaultaudience)
        {
            super.setDefaultAudience(sessiondefaultaudience);
            return this;
        }

        public final volatile AuthorizationRequest setLoginBehavior(SessionLoginBehavior sessionloginbehavior)
        {
            return setLoginBehavior(sessionloginbehavior);
        }

        public final NewPermissionsRequest setLoginBehavior(SessionLoginBehavior sessionloginbehavior)
        {
            super.setLoginBehavior(sessionloginbehavior);
            return this;
        }

        public final volatile AuthorizationRequest setRequestCode(int i)
        {
            return setRequestCode(i);
        }

        public final NewPermissionsRequest setRequestCode(int i)
        {
            super.setRequestCode(i);
            return this;
        }

        public NewPermissionsRequest(Activity activity, List list)
        {
            super(activity);
            setPermissions(list);
        }

        public transient NewPermissionsRequest(Activity activity, String as[])
        {
            super(activity);
            setPermissions(as);
        }

        public NewPermissionsRequest(Fragment fragment, List list)
        {
            super(fragment);
            setPermissions(list);
        }

        public transient NewPermissionsRequest(Fragment fragment, String as[])
        {
            super(fragment);
            setPermissions(as);
        }
    }


    private class _cls2
        implements AuthorizationClient.OnCompletedListener
    {

        final Session this$0;

        public void onCompleted(AuthorizationClient.Result result)
        {
            int i;
            if (result.code == AuthorizationClient.Result.Code.CANCEL)
            {
                i = 0;
            } else
            {
                i = -1;
            }
            handleAuthorizationResult(i, result);
        }

        _cls2()
        {
            this$0 = Session.this;
            super();
        }
    }


    private class StartActivityDelegate
    {

        public abstract Activity getActivityContext();

        public abstract void startActivityForResult(Intent intent, int i);
    }


    private class SerializationProxyV1
        implements Serializable
    {

        private static final long serialVersionUID = 0x6a59fe98cd935affL;
        private final String applicationId;
        private final Date lastAttemptedTokenExtendDate;
        private final AuthorizationRequest pendingAuthorizationRequest;
        private final boolean shouldAutoPublish;
        private final SessionState state;
        private final AccessToken tokenInfo;

        private Object readResolve()
        {
            return new Session(applicationId, state, tokenInfo, lastAttemptedTokenExtendDate, shouldAutoPublish, pendingAuthorizationRequest, null);
        }

        SerializationProxyV1(String s, SessionState sessionstate, AccessToken accesstoken, Date date, boolean flag, AuthorizationRequest authorizationrequest)
        {
            applicationId = s;
            state = sessionstate;
            tokenInfo = accesstoken;
            lastAttemptedTokenExtendDate = date;
            shouldAutoPublish = flag;
            pendingAuthorizationRequest = authorizationrequest;
        }
    }


    private class TokenRefreshRequest
        implements ServiceConnection
    {
        private class TokenRefreshRequestHandler extends Handler
        {

            private WeakReference refreshRequestWeakReference;
            private WeakReference sessionWeakReference;

            public void handleMessage(Message message)
            {
                String s = message.getData().getString("access_token");
                Session session = (Session)sessionWeakReference.get();
                if (session != null && s != null)
                {
                    session.extendTokenCompleted(message.getData());
                }
                TokenRefreshRequest tokenrefreshrequest = (TokenRefreshRequest)refreshRequestWeakReference.get();
                if (tokenrefreshrequest != null)
                {
                    Session.staticContext.unbindService(tokenrefreshrequest);
                    tokenrefreshrequest.cleanup();
                }
            }

            TokenRefreshRequestHandler(TokenRefreshRequest tokenrefreshrequest)
            {
                super(Looper.getMainLooper());
                sessionWeakReference = new WeakReference(Session.this);
                refreshRequestWeakReference = new WeakReference(tokenrefreshrequest);
            }
        }


        final Messenger messageReceiver;
        Messenger messageSender;
        final Session this$0;

        private void cleanup()
        {
            if (currentTokenRefreshRequest == this)
            {
                currentTokenRefreshRequest = null;
            }
        }

        private void refreshToken()
        {
            Bundle bundle = new Bundle();
            bundle.putString("access_token", getTokenInfo().getToken());
            Message message = Message.obtain();
            message.setData(bundle);
            message.replyTo = messageReceiver;
            try
            {
                messageSender.send(message);
                return;
            }
            catch (RemoteException remoteexception)
            {
                cleanup();
            }
        }

        public void bind()
        {
            Intent intent = NativeProtocol.createTokenRefreshIntent(Session.getStaticContext());
            if (intent != null && Session.staticContext.bindService(intent, this, 1))
            {
                setLastAttemptedTokenExtendDate(new Date());
                return;
            } else
            {
                cleanup();
                return;
            }
        }

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            messageSender = new Messenger(ibinder);
            refreshToken();
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            cleanup();
            Session.staticContext.unbindService(this);
        }


        TokenRefreshRequest()
        {
            this$0 = Session.this;
            super();
            messageReceiver = new Messenger(new TokenRefreshRequestHandler(this));
            messageSender = null;
        }
    }


    private class _cls3
        implements Runnable
    {

        final Session this$0;
        final Exception val$exception;
        final SessionState val$newState;

        public void run()
        {
            _cls1 _lcls1;
            for (Iterator iterator = callbacks.iterator(); iterator.hasNext(); Session.runWithHandlerOrExecutor(handler, _lcls1))
            {
                class _cls1
                    implements Runnable
                {

                    final _cls3 this$1;
                    final StatusCallback val$callback;

                    public void run()
                    {
                        callback.call(_fld0, newState, exception);
                    }

                _cls1()
                {
                    this$1 = _cls3.this;
                    callback = statuscallback;
                    super();
                }
                }

                _lcls1 = new _cls1();
            }

        }

        _cls3()
        {
            this$0 = Session.this;
            newState = sessionstate;
            exception = exception1;
            super();
        }

        private class StatusCallback
        {

            public abstract void call(Session session, SessionState sessionstate, Exception exception1);
        }

    }


    private class _cls1 extends HashSet
    {

        _cls1()
        {
            add("ads_management");
            add("create_event");
            add("rsvp_event");
        }
    }

}
