// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.view.View;

// Referenced classes of package com.flipkart.android.utils:
//            CustomAlertDialog

final class b
    implements android.view.View.OnClickListener
{

    private String a;
    private CustomAlertDialog b;

    b(CustomAlertDialog customalertdialog, String s)
    {
        b = customalertdialog;
        a = s;
        super();
    }

    public final void onClick(View view)
    {
        CustomAlertDialog.a(b, a);
    }
}
