// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import java.util.TimerTask;

// Referenced classes of package com.flipkart.android.fragments:
//            SearchFragment

final class bb extends TimerTask
{

    private SearchFragment a;

    bb(SearchFragment searchfragment)
    {
        a = searchfragment;
        super();
    }

    public final void run()
    {
        if (a.activity != null)
        {
            InputMethodManager inputmethodmanager = (InputMethodManager)a.activity.getSystemService("input_method");
            if (inputmethodmanager != null)
            {
                inputmethodmanager.toggleSoftInput(0, 1);
            }
        }
    }
}
