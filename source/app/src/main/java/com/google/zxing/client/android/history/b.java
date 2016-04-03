// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.history;

import android.content.DialogInterface;

// Referenced classes of package com.google.zxing.client.android.history:
//            HistoryActivity, HistoryManager

final class b
    implements android.content.DialogInterface.OnClickListener
{

    private HistoryActivity a;

    b(HistoryActivity historyactivity)
    {
        a = historyactivity;
        super();
    }

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        HistoryActivity.a(a).b();
        dialoginterface.dismiss();
        a.finish();
    }
}
