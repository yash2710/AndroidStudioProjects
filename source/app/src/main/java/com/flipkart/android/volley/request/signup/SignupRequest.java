// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.signup;

import com.flipkart.android.response.signup.SignupResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.signup.params.SignupParam;

// Referenced classes of package com.flipkart.android.volley.request.signup:
//            a

public class SignupRequest extends GsonRequest
{

    private SignupParam a;

    public SignupRequest(SignupParam signupparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(1, constructSecureGetUri("2", "user/signUp", signupparam.generateURI()), null, (new a()).getType(), listener, errorlistener);
        a = null;
        a = signupparam;
    }

    public byte[] getBody()
    {
        return a.generateToByteArray();
    }

    public void performJsonUpdate(byte abyte0[], SignupResponse signupresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (SignupResponse)obj, flag);
    }
}
