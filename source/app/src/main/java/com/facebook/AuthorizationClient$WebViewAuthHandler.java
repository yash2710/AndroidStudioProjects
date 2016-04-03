// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.facebook.internal.Utility;
import com.facebook.widget.WebDialog;

// Referenced classes of package com.facebook:
//            AuthorizationClient, AccessTokenSource, AccessToken, FacebookOperationCanceledException, 
//            FacebookException, FacebookServiceException, FacebookRequestError

class this._cls0 extends this._cls0
{

    private static final long serialVersionUID = 1L;
    private String applicationId;
    private String e2e;
    private transient WebDialog loginDialog;
    final AuthorizationClient this$0;

    private String loadCookieToken()
    {
        return getStartActivityDelegate().getActivityContext().getSharedPreferences("com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).getString("TOKEN", "");
    }

    private void saveCookieToken(String s)
    {
        android.content.getActivityContext getactivitycontext = getStartActivityDelegate().getActivityContext().getSharedPreferences("com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).edit();
        getactivitycontext.getActivityContext("TOKEN", s);
        if (!getactivitycontext.getActivityContext())
        {
            Utility.logd("Facebook-AuthorizationClient", "Could not update saved web view auth handler token.");
        }
    }

    void cancel()
    {
        if (loginDialog != null)
        {
            loginDialog.dismiss();
            loginDialog = null;
        }
    }

    String getNameForLogging()
    {
        return "web_view";
    }

    boolean needsInternetPermission()
    {
        return true;
    }

    boolean needsRestart()
    {
        return true;
    }

    void onWebDialogComplete(t t, Bundle bundle, FacebookException facebookexception)
    {
        t t1;
        if (bundle != null)
        {
            if (bundle.containsKey("e2e"))
            {
                e2e = bundle.getString("e2e");
            }
            AccessToken accesstoken = AccessToken.createFromWebBundle(t.getPermissions(), bundle, AccessTokenSource.WEB_VIEW);
            t1 = esult(pendingRequest, accesstoken);
            CookieSyncManager.createInstance(context).sync();
            saveCookieToken(accesstoken.getToken());
        } else
        if (facebookexception instanceof FacebookOperationCanceledException)
        {
            t1 = Result(pendingRequest, "User canceled log in.");
        } else
        {
            e2e = null;
            String s = facebookexception.getMessage();
            String s1;
            if (facebookexception instanceof FacebookServiceException)
            {
                FacebookRequestError facebookrequesterror = ((FacebookServiceException)facebookexception).getRequestError();
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(facebookrequesterror.getErrorCode());
                s1 = String.format("%d", aobj);
                s = facebookrequesterror.toString();
            } else
            {
                s1 = null;
            }
            t1 = esult(pendingRequest, null, s, s1);
        }
        if (!Utility.isNullOrEmpty(e2e))
        {
            AuthorizationClient.access$200(AuthorizationClient.this, applicationId, e2e);
        }
        completeAndValidate(t1);
    }

    boolean tryAuthorize(final t request)
    {
        applicationId = request.getApplicationId();
        Bundle bundle = new Bundle();
        if (!Utility.isNullOrEmpty(request.getPermissions()))
        {
            String s1 = TextUtils.join(",", request.getPermissions());
            bundle.putString("scope", s1);
            addLoggingExtra("scope", s1);
        }
        String s = request.getPreviousAccessToken();
        class _cls1
            implements com.facebook.widget.WebDialog.OnCompleteListener
        {

            final AuthorizationClient.WebViewAuthHandler this$1;
            final AuthorizationClient.AuthorizationRequest val$request;

            public void onComplete(Bundle bundle1, FacebookException facebookexception)
            {
                onWebDialogComplete(request, bundle1, facebookexception);
            }

            _cls1()
            {
                this$1 = AuthorizationClient.WebViewAuthHandler.this;
                request = authorizationrequest;
                super();
            }
        }

        _cls1 _lcls1;
        if (!Utility.isNullOrEmpty(s) && s.equals(loadCookieToken()))
        {
            bundle.putString("access_token", s);
            addLoggingExtra("access_token", "1");
        } else
        {
            Utility.clearFacebookCookies(context);
            addLoggingExtra("access_token", "0");
        }
        _lcls1 = new _cls1();
        e2e = AuthorizationClient.access$100();
        addLoggingExtra("e2e", e2e);
        loginDialog = ((com.facebook.widget.alog)(new init>(getStartActivityDelegate().getActivityContext(), applicationId, bundle)).etE2E(e2e).etOnCompleteListener(_lcls1)).pleteListener();
        loginDialog.show();
        return true;
    }

    t()
    {
        this$0 = AuthorizationClient.this;
        super(AuthorizationClient.this);
    }
}
