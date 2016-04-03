// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.share;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

// Referenced classes of package com.google.zxing.client.android.share:
//            ShareActivity

final class i
    implements android.view.View.OnKeyListener
{

    private ShareActivity a;

    i(ShareActivity shareactivity)
    {
        a = shareactivity;
        super();
    }

    public final boolean onKey(View view, int j, KeyEvent keyevent)
    {
        if (j == 66 && keyevent.getAction() == 0)
        {
            String s = ((TextView)view).getText().toString();
            if (s != null && s.length() > 0)
            {
                ShareActivity.a(a, s);
            }
            return true;
        } else
        {
            return false;
        }
    }
}
