// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.facebook:
//            AuthorizationClient, AccessTokenSource, AccessToken, AppEventsLogger, 
//            SessionDefaultAudience

class this._cls0 extends this._cls0
{

    private static final long serialVersionUID = 1L;
    private String applicationId;
    private String callId;
    final AuthorizationClient this$0;

    private this._cls0 createCancelOrErrorResult(this._cls0 _pcls0, Intent intent)
    {
        String s;
        String s1;
        String s2;
        Bundle bundle = intent.getExtras();
        s = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
        if ("UserCanceled".equals(s) || "PermissionDenied".equals(s))
        {
            return com.facebook.this._mth0(_pcls0, intent.getStringExtra("com.facebook.platform.status.ERROR_DESCRIPTION"));
        }
        s1 = bundle.getString("com.facebook.platform.status.ERROR_JSON");
        s2 = null;
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        String s3;
        JVM INSTR new #62  <Class JSONObject>;
        s3 = JSONObjectInstrumentation.init(s1).getString("error_code");
        s2 = s3;
_L2:
        return tation.init(_pcls0, s, intent.getStringExtra("com.facebook.platform.status.ERROR_DESCRIPTION"), s2);
        JSONException jsonexception;
        jsonexception;
        s2 = null;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private tation.init handleResultOk(Intent intent)
    {
        Bundle bundle = intent.getExtras();
        String s = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
        if (s == null)
        {
            return tation.init(pendingRequest, AccessToken.createFromNativeLogin(bundle, AccessTokenSource.FACEBOOK_APPLICATION_NATIVE));
        }
        if ("ServiceDisabled".equals(s))
        {
            addLoggingExtra("service_disabled", "1");
            return null;
        } else
        {
            return createCancelOrErrorResult(pendingRequest, intent);
        }
    }

    private void logEvent(String s, String s1, String s2)
    {
        if (s2 != null)
        {
            AppEventsLogger appeventslogger = AppEventsLogger.newLogger(context, applicationId);
            Bundle bundle = new Bundle();
            bundle.putString("app_id", applicationId);
            bundle.putString("action_id", s2);
            bundle.putLong(s1, System.currentTimeMillis());
            appeventslogger.logSdkEvent(s, null, bundle);
        }
    }

    String getNameForLogging()
    {
        return "katana_login_dialog";
    }

    boolean onActivityResult(int i, int j, Intent intent)
    {
        logEvent("fb_dialogs_native_login_dialog_complete", "fb_native_login_dialog_complete_time", callId);
        applicationId applicationid;
        if (intent == null)
        {
            applicationid = callId(pendingRequest, "Operation canceled");
        } else
        {
            boolean flag = NativeProtocol.isServiceDisabledResult20121101(intent);
            applicationid = null;
            if (!flag)
            {
                if (j == 0)
                {
                    applicationid = createCancelOrErrorResult(pendingRequest, intent);
                } else
                if (j != -1)
                {
                    applicationid = createCancelOrErrorResult(pendingRequest, "Unexpected resultCode from authorization.", null);
                } else
                {
                    applicationid = handleResultOk(intent);
                }
            }
        }
        if (applicationid != null)
        {
            completeAndValidate(applicationid);
        } else
        {
            tryNextHandler();
        }
        return true;
    }

    boolean tryAuthorize(handleResultOk handleresultok)
    {
        applicationId = handleresultok.cationId();
        Intent intent = NativeProtocol.createLoginDialog20121101Intent(context, handleresultok.cationId(), new ArrayList(handleresultok.ssions()), handleresultok.ltAudience().getNativeProtocolAudience());
        if (intent == null)
        {
            return false;
        } else
        {
            callId = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
            addLoggingExtra("call_id", callId);
            addLoggingExtra("protocol_version", Integer.valueOf(intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0)));
            addLoggingExtra("permissions", TextUtils.join(",", intent.getStringArrayListExtra("com.facebook.platform.extra.PERMISSIONS")));
            addLoggingExtra("write_privacy", intent.getStringExtra("com.facebook.platform.extra.WRITE_PRIVACY"));
            logEvent("fb_dialogs_native_login_dialog_start", "fb_native_login_dialog_start_time", callId);
            return tryIntent(intent, handleresultok.stCode());
        }
    }

    tation()
    {
        this$0 = AuthorizationClient.this;
        super(AuthorizationClient.this);
    }
}
