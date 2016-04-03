// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import android.os.Bundle;
import com.flipkart.logging.FkLogger;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.facebook:
//            AppLinkData

final class val.completionHandler
    implements com.facebook.internal.eClient.CompletedListener
{

    final mpletionHandler val$completionHandler;

    public final void completed(Bundle bundle)
    {
        AppLinkData applinkdata = null;
        if (bundle != null)
        {
            String s = bundle.getString("com.facebook.platform.APPLINK_ARGS");
            long l = bundle.getLong("com.facebook.platform.APPLINK_TAP_TIME_UTC", -1L);
            applinkdata = AppLinkData.access$000(s);
            if (l != -1L)
            {
                try
                {
                    applinkdata.getArguments().put("com.facebook.platform.APPLINK_TAP_TIME_UTC", l);
                }
                catch (JSONException jsonexception)
                {
                    FkLogger.debug(AppLinkData.access$100(), "Unable to put tap time in AppLinkData.arguments");
                }
            }
        }
        val$completionHandler.onDeferredAppLinkDataFetched(applinkdata);
    }

    mpletionHandler()
    {
        val$completionHandler = mpletionhandler;
        super();
    }
}
