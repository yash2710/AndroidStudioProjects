// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import android.util.Log;
import com.crashlytics.android.internal.ab;
import com.crashlytics.android.internal.q;
import com.crashlytics.android.internal.v;

// Referenced classes of package com.crashlytics.android:
//            CrashlyticsMissingDependencyException

final class G
{

    private String a;
    private boolean b;

    public G(String s, boolean flag)
    {
        a = s;
        b = flag;
    }

    public final void a(String s, String s1)
    {
        if (ab.e(a) && b)
        {
            Log.e("Crashlytics", ".");
            Log.e("Crashlytics", ".     |  | ");
            Log.e("Crashlytics", ".     |  |");
            Log.e("Crashlytics", ".     |  |");
            Log.e("Crashlytics", ".   \\ |  | /");
            Log.e("Crashlytics", ".    \\    /");
            Log.e("Crashlytics", ".     \\  /");
            Log.e("Crashlytics", ".      \\/");
            Log.e("Crashlytics", ".");
            Log.e("Crashlytics", "This app relies on Crashlytics. Configure your build environment here: ");
            Log.e("Crashlytics", String.format("https://crashlytics.com/register/%s/android/%s", new Object[] {
                s, s1
            }));
            Log.e("Crashlytics", ".");
            Log.e("Crashlytics", ".      /\\");
            Log.e("Crashlytics", ".     /  \\");
            Log.e("Crashlytics", ".    /    \\");
            Log.e("Crashlytics", ".   / |  | \\");
            Log.e("Crashlytics", ".     |  |");
            Log.e("Crashlytics", ".     |  |");
            Log.e("Crashlytics", ".     |  |");
            Log.e("Crashlytics", ".");
            throw new CrashlyticsMissingDependencyException(s, s1);
        }
        if (!b)
        {
            v.a().b().a("Crashlytics", "Configured not to require a build ID.");
        }
    }
}
