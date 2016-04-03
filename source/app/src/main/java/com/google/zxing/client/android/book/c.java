// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.book;

import android.view.KeyEvent;
import android.view.View;

// Referenced classes of package com.google.zxing.client.android.book:
//            SearchBookContentsActivity

final class c
    implements android.view.View.OnKeyListener
{

    private SearchBookContentsActivity a;

    c(SearchBookContentsActivity searchbookcontentsactivity)
    {
        a = searchbookcontentsactivity;
        super();
    }

    public final boolean onKey(View view, int i, KeyEvent keyevent)
    {
        if (i == 66 && keyevent.getAction() == 0)
        {
            SearchBookContentsActivity.a(a);
            return true;
        } else
        {
            return false;
        }
    }
}
