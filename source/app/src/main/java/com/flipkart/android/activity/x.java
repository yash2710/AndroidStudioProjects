// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

// Referenced classes of package com.flipkart.android.activity:
//            LoginActivity

final class x
    implements android.widget.TextView.OnEditorActionListener
{

    private LoginActivity a;

    x(LoginActivity loginactivity)
    {
        a = loginactivity;
        super();
    }

    public final boolean onEditorAction(TextView textview, int i, KeyEvent keyevent)
    {
        if (i == 6)
        {
            LoginActivity.b(a).performClick();
            return true;
        } else
        {
            return false;
        }
    }
}
