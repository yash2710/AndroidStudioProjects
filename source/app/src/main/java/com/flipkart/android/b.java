// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android;

import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.android:
//            SplashActivity

final class b
    implements Runnable
{

    b(SplashActivity splashactivity)
    {
    }

    public final void run()
    {
        try
        {
            Class.forName("android.os.AsyncTask");
            return;
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            FkLogger.printStackTrace(classnotfoundexception);
        }
    }
}
