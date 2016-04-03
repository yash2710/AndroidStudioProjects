// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.signup.SignupRequest;
import com.flipkart.android.volley.request.signup.params.SignupParam;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class SignUpVDataHandler extends BaseVDataHandler
{

    public SignUpVDataHandler()
    {
    }

    public void doSignUp(String s, String s1)
    {
        SignupRequest signuprequest = new SignupRequest(new SignupParam(s, s1), listner, errorListner);
        request = signuprequest;
        FlipkartApplication.getRequestQueue().add(signuprequest);
    }
}
