// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import android.app.Activity;
import android.os.Bundle;

// Referenced classes of package com.crashlytics.android.internal:
//            aH, v

final class aI
    implements android.app.Application.ActivityLifecycleCallbacks
{

    private aH a;

    aI(aH ah)
    {
        a = ah;
        super();
    }

    public final void onActivityCreated(Activity activity, Bundle bundle)
    {
        v.a(a.a, activity);
    }

    public final void onActivityDestroyed(Activity activity)
    {
    }

    public final void onActivityPaused(Activity activity)
    {
    }

    public final void onActivityResumed(Activity activity)
    {
        v.a(a.a, activity);
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle)
    {
    }

    public final void onActivityStarted(Activity activity)
    {
        v.a(a.a, activity);
    }

    public final void onActivityStopped(Activity activity)
    {
    }
}
