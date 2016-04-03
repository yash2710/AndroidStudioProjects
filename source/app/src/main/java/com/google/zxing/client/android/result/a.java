// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.view.View;
import com.google.zxing.client.result.ISBNParsedResult;

// Referenced classes of package com.google.zxing.client.android.result:
//            ISBNResultHandler

final class a
    implements android.view.View.OnClickListener
{

    private ISBNResultHandler a;

    a(ISBNResultHandler isbnresulthandler)
    {
        a = isbnresulthandler;
        super();
    }

    public final void onClick(View view)
    {
        ISBNParsedResult isbnparsedresult = (ISBNParsedResult)a.getResult();
        a.i(isbnparsedresult.getISBN());
    }
}
