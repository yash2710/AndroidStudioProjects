// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.register;

import android.app.Activity;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.register.RegisterResponse;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.volley.request.register.RegisterRequest;
import java.util.concurrent.ExecutionException;

// Referenced classes of package com.flipkart.android.register:
//            RegisterInfo, a

public class RegistrationHelper
{

    public static int retryCount = 0;

    public RegistrationHelper()
    {
    }

    public static boolean doRegister(String s)
    {
        RequestFuture requestfuture;
        if (!StringUtils.isNullOrEmpty(FlipkartPreferenceManager.instance().getRegisterKey()))
        {
            return false;
        }
        requestfuture = RequestFuture.newFuture();
        RegisterRequest registerrequest = new RegisterRequest(getMessage(s, FlipkartDeviceInfoProvider.getDeviceId()), (new RegisterInfo(Long.valueOf(Long.parseLong(s)))).toPostString(), requestfuture, requestfuture);
        FlipkartApplication.getRequestQueue().add(registerrequest);
        String s1;
        s1 = ((RegisterResponse)requestfuture.get()).getKey();
        if (StringUtils.isNullOrEmpty(s1))
        {
            Boolean.valueOf(false);
            break MISSING_BLOCK_LABEL_216;
        }
        FlipkartPreferenceManager.instance().saveRegisterKey(s1);
        Boolean.valueOf(true);
        break MISSING_BLOCK_LABEL_216;
        InterruptedException interruptedexception;
        interruptedexception;
_L2:
        return false;
        ExecutionException executionexception;
        executionexception;
        VolleyError volleyerror;
        if (!(executionexception.getCause() instanceof VolleyError))
        {
            continue; /* Loop/switch isn't completed */
        }
        volleyerror = (VolleyError)executionexception.getCause();
        int i = ((VolleyError)volleyerror).networkResponse.statusCode;
        if (i != 401)
        {
            break MISSING_BLOCK_LABEL_187;
        }
        int j;
        j = 1 + retryCount;
        retryCount = j;
        Exception exception;
        if (j < 3)
        {
            try
            {
                doRegister(Long.toString(System.currentTimeMillis() / 1000L));
            }
            catch (Exception exception1) { }
        }
        continue; /* Loop/switch isn't completed */
        if (i != 409)
        {
            continue; /* Loop/switch isn't completed */
        }
        FlipkartApplication.getCurrentActivity().runOnUiThread(new a());
        continue; /* Loop/switch isn't completed */
        exception;
        if (true) goto _L2; else goto _L1
_L1:
        return true;
    }

    public static native String getMessage(String s, String s1);

}
