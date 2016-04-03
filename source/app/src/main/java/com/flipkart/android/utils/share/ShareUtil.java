// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.share;

import android.app.Activity;
import com.flipkart.android.datahandler.ShareTheAppDataHandler;

// Referenced classes of package com.flipkart.android.utils.share:
//            a

public class ShareUtil
{

    public ShareUtil()
    {
    }

    public static void sendShareResultToServer(String s, String s1, Activity activity)
    {
        (new a(activity, s1)).shareTheApp(s);
    }
}
