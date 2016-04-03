// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.book;

import android.view.View;

// Referenced classes of package com.google.zxing.client.android.book:
//            SearchBookContentsActivity

final class b
    implements android.view.View.OnClickListener
{

    private SearchBookContentsActivity a;

    b(SearchBookContentsActivity searchbookcontentsactivity)
    {
        a = searchbookcontentsactivity;
        super();
    }

    public final void onClick(View view)
    {
        SearchBookContentsActivity.a(a);
    }
}
