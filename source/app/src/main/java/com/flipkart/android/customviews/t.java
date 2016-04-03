// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.customviews:
//            NewEditText

final class t
    implements android.view.View.OnFocusChangeListener
{

    private NewEditText a;

    t(NewEditText newedittext)
    {
        a = newedittext;
        super();
    }

    public final void onFocusChange(View view, boolean flag)
    {
        if (flag)
        {
            if (a.c != null)
            {
                a.c.setBackgroundResource(0x7f02012b);
                if (a.a != null && !StringUtils.isNullOrEmpty(a.a.getText().toString()))
                {
                    a.b.setVisibility(0);
                }
            }
        } else
        if (a.c != null)
        {
            a.c.setBackgroundResource(0x7f02012c);
            a.b.setVisibility(8);
            return;
        }
    }
}
