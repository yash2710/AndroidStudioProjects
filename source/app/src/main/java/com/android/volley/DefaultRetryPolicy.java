// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;


// Referenced classes of package com.android.volley:
//            RetryPolicy, VolleyError

public class DefaultRetryPolicy
    implements RetryPolicy
{

    public static final float DEFAULT_BACKOFF_MULT = 1F;
    public static final int DEFAULT_MAX_RETRIES = 1;
    public static final int DEFAULT_TIMEOUT_MS = 2500;
    private int a;
    private int b;
    private final int c;
    private final float d;

    public DefaultRetryPolicy()
    {
        this(2500, 1, 1.0F);
    }

    public DefaultRetryPolicy(int i, int j, float f)
    {
        a = i;
        c = j;
        d = f;
    }

    public int getCurrentRetryCount()
    {
        return b;
    }

    public int getCurrentTimeout()
    {
        return a;
    }

    protected boolean hasAttemptRemaining()
    {
        return b <= c;
    }

    public void retry(VolleyError volleyerror)
    {
        b = 1 + b;
        a = (int)((float)a + (float)a * d);
        if (!hasAttemptRemaining())
        {
            throw volleyerror;
        } else
        {
            return;
        }
    }
}
