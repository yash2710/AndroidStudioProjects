// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.facebook:
//            GetTokenClient, AuthorizationClient, AccessTokenSource, AccessToken

class this._cls0 extends this._cls0
{

    private static final long serialVersionUID = 1L;
    private transient GetTokenClient getTokenClient;
    final AuthorizationClient this$0;

    void cancel()
    {
        if (getTokenClient != null)
        {
            getTokenClient.cancel();
            getTokenClient = null;
        }
    }

    String getNameForLogging()
    {
        return "get_token";
    }

    void getTokenCompleted( , Bundle bundle)
    {
        getTokenClient = null;
        AuthorizationClient.access$000(AuthorizationClient.this);
        if (bundle != null)
        {
            ArrayList arraylist = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
            List list = .getPermissions();
            if (arraylist != null && (list == null || arraylist.containsAll(list)))
            {
                AccessToken accesstoken = AccessToken.createFromNativeLogin(bundle, AccessTokenSource.FACEBOOK_APPLICATION_SERVICE);
                 1 = sult(pendingRequest, accesstoken);
                completeAndValidate(1);
                return;
            }
            ArrayList arraylist1 = new ArrayList();
            Iterator iterator = list.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                String s = (String)iterator.next();
                if (!arraylist.contains(s))
                {
                    arraylist1.add(s);
                }
            } while (true);
            if (!arraylist1.isEmpty())
            {
                addLoggingExtra("new_permissions", TextUtils.join(",", arraylist1));
            }
            .setPermissions(arraylist1);
        }
        tryNextHandler();
    }

    boolean tryAuthorize(final  request)
    {
        getTokenClient = new GetTokenClient(context, request.getApplicationId());
        if (!getTokenClient.start())
        {
            return false;
        } else
        {
            AuthorizationClient.access$300(AuthorizationClient.this);
            class _cls1
                implements com.facebook.internal.PlatformServiceClient.CompletedListener
            {

                final AuthorizationClient.GetTokenAuthHandler this$1;
                final AuthorizationClient.AuthorizationRequest val$request;

                public void completed(Bundle bundle)
                {
                    getTokenCompleted(request, bundle);
                }

            _cls1()
            {
                this$1 = AuthorizationClient.GetTokenAuthHandler.this;
                request = authorizationrequest;
                super();
            }
            }

            _cls1 _lcls1 = new _cls1();
            getTokenClient.setCompletedListener(_lcls1);
            return true;
        }
    }

    ()
    {
        this$0 = AuthorizationClient.this;
        super(AuthorizationClient.this);
    }
}
