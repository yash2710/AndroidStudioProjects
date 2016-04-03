// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;

import android.accounts.Account;
import android.content.DialogInterface;
import java.util.List;

// Referenced classes of package com.flipkart.android.login:
//            GoogleTokenFetcher

final class e
    implements android.content.DialogInterface.OnClickListener
{

    private List a;
    private GoogleTokenFetcher b;

    e(GoogleTokenFetcher googletokenfetcher, List list)
    {
        b = googletokenfetcher;
        a = list;
        super();
    }

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        if (i == a.size())
        {
            GoogleTokenFetcher.a(b);
            return;
        } else
        {
            GoogleTokenFetcher.a(b, (Account)a.get(i));
            return;
        }
    }
}
