// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import android.content.Context;
import android.content.SharedPreferences;

// Referenced classes of package com.crittercism.app:
//            Crittercism

final class o
    implements Runnable
{

    private boolean a;

    public o(boolean flag)
    {
        a = false;
        a = flag;
    }

    public final void run()
    {
        StringBuilder stringbuilder;
        android.content.SharedPreferences.Editor editor = Crittercism.a().n().getSharedPreferences("com.crittercism.prefs", 0).edit();
        editor.remove("optOutStatus");
        editor.putBoolean("optOutStatus", a);
        if (!editor.commit())
        {
            throw new Exception();
        }
        stringbuilder = new StringBuilder("Saved optOutStatus as ");
        String s;
        if (a)
        {
            s = "true";
        } else
        {
            s = "false";
        }
        try
        {
            stringbuilder.append(s).append(" to SharedPreferences!!");
            Crittercism.a(Crittercism.a(), a);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }
}
