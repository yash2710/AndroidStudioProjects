// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;

// Referenced classes of package com.flipkart.android.customviews:
//            NewEditText

final class s
    implements TextWatcher
{

    private NewEditText a;

    s(NewEditText newedittext)
    {
        a = newedittext;
        super();
    }

    public final void afterTextChanged(Editable editable)
    {
    }

    public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        if (charsequence.length() > 0)
        {
            if (a.b != null)
            {
                a.b.setVisibility(0);
            }
        } else
        if (a.b != null)
        {
            a.b.setVisibility(8);
            return;
        }
    }
}
