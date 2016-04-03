// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.share;

import android.content.Intent;
import android.view.View;

// Referenced classes of package com.google.zxing.client.android.share:
//            ShareActivity

final class e
    implements android.view.View.OnClickListener
{

    private ShareActivity a;

    e(ShareActivity shareactivity)
    {
        a = shareactivity;
        super();
    }

    public final void onClick(View view)
    {
        Intent intent = new Intent("android.intent.action.PICK", android.provider.ContactsContract.Contacts.CONTENT_URI);
        intent.addFlags(0x80000);
        a.startActivityForResult(intent, 1);
    }
}
