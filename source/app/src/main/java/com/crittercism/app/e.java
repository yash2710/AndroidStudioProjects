// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import crittercism.android.b;
import org.json.JSONObject;

// Referenced classes of package com.crittercism.app:
//            Crittercism

final class e
    implements Runnable
{

    private JSONObject a;
    private Crittercism b;

    e(Crittercism crittercism, JSONObject jsonobject)
    {
        b = crittercism;
        a = jsonobject;
        super();
    }

    public final void run()
    {
        try
        {
            Crittercism.e(b).b(a);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }
}
