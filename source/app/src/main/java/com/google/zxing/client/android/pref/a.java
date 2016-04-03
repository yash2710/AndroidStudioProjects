// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.pref;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.Preference;

// Referenced classes of package com.google.zxing.client.android.pref:
//            BSPlusPreference

final class a
    implements android.preference.Preference.OnPreferenceClickListener
{

    private BSPlusPreference a;

    a(BSPlusPreference bspluspreference)
    {
        a = bspluspreference;
        super();
    }

    public final boolean onPreferenceClick(Preference preference)
    {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.srowen.bs.android"));
        intent.addFlags(0x80000);
        a.getContext().startActivity(intent);
        return true;
    }
}
