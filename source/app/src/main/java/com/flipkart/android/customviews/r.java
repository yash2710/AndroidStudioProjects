// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.View;
import android.widget.EditText;

// Referenced classes of package com.flipkart.android.customviews:
//            NewEditText

final class r
    implements android.view.View.OnClickListener
{

    private NewEditText a;

    r(NewEditText newedittext)
    {
        a = newedittext;
        super();
    }

    public final void onClick(View view)
    {
        if (a.a != null)
        {
            a.a.setText("");
        }
    }
}
