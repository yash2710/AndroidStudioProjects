// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.Result;
import com.google.zxing.client.result.ISBNParsedResult;
import com.google.zxing.client.result.ParsedResult;

// Referenced classes of package com.google.zxing.client.android.result:
//            ResultHandler, a

public final class ISBNResultHandler extends ResultHandler
{

    private static final int a[];

    public ISBNResultHandler(Activity activity, ParsedResult parsedresult, Result result)
    {
        super(activity, parsedresult, result);
        a(new a(this));
    }

    public final int getButtonCount()
    {
        if (a())
        {
            int[] _tmp = a;
            return 4;
        } else
        {
            int[] _tmp1 = a;
            return 3;
        }
    }

    public final int getButtonText(int i)
    {
        return a[i];
    }

    public final int getDisplayTitle()
    {
        return com.google.zxing.client.android.R.string.result_isbn;
    }

    public final void handleButtonPress(int i)
    {
        ISBNParsedResult isbnparsedresult = (ISBNParsedResult)getResult();
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            d(isbnparsedresult.getISBN());
            return;

        case 1: // '\001'
            e(isbnparsedresult.getISBN());
            return;

        case 2: // '\002'
            f(isbnparsedresult.getISBN());
            return;

        case 3: // '\003'
            g(j(isbnparsedresult.getISBN()));
            break;
        }
    }

    static 
    {
        int ai[] = new int[4];
        ai[0] = com.google.zxing.client.android.R.string.button_product_search;
        ai[1] = com.google.zxing.client.android.R.string.button_book_search;
        ai[2] = com.google.zxing.client.android.R.string.button_search_book_contents;
        ai[3] = com.google.zxing.client.android.R.string.button_custom_product_search;
        a = ai;
    }
}
