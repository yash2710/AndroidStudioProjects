// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.share;

import android.text.ClipboardManager;
import android.view.View;

// Referenced classes of package com.google.zxing.client.android.share:
//            ShareActivity

final class h
    implements android.view.View.OnClickListener
{

    private ShareActivity a;

    h(ShareActivity shareactivity)
    {
        a = shareactivity;
        super();
    }

    public final void onClick(View view)
    {
        ClipboardManager clipboardmanager = (ClipboardManager)a.getSystemService("clipboard");
        if (clipboardmanager.hasText())
        {
            ShareActivity.a(a, clipboardmanager.getText().toString());
        }
    }
}
