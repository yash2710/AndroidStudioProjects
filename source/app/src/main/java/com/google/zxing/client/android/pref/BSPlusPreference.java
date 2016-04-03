// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.pref;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;

// Referenced classes of package com.google.zxing.client.android.pref:
//            a

public final class BSPlusPreference extends Preference
{

    public BSPlusPreference(Context context)
    {
        super(context);
        a();
    }

    public BSPlusPreference(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        a();
    }

    public BSPlusPreference(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        a();
    }

    private void a()
    {
        setOnPreferenceClickListener(new a(this));
    }
}
