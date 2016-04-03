// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.content.Intent;
import android.view.View;
import com.flipkart.android.analytics.TrackingHelper;

// Referenced classes of package com.flipkart.android.fragments:
//            bi, SearchFragment

final class aY
    implements android.view.View.OnClickListener
{

    private SearchFragment a;

    aY(SearchFragment searchfragment)
    {
        a = searchfragment;
        super();
    }

    public final void onClick(View view)
    {
        TrackingHelper.sendVoiceClicked();
        try
        {
            SearchFragment.a(a, bi.b);
            Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
            intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "en-US");
            a.startActivityForResult(intent, 14);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }
}
