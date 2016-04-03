// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.Result;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ProductParsedResult;

// Referenced classes of package com.google.zxing.client.android.result:
//            ResultHandler, b

public final class ProductResultHandler extends ResultHandler
{

    private static final int a[];

    public ProductResultHandler(Activity activity, ParsedResult parsedresult, Result result)
    {
        super(activity, parsedresult, result);
        a(new b(this));
    }

    public final int getButtonCount()
    {
        if (a())
        {
            int[] _tmp = a;
            return 3;
        } else
        {
            int[] _tmp1 = a;
            return 2;
        }
    }

    public final int getButtonText(int i)
    {
        return a[i];
    }

    public final int getDisplayTitle()
    {
        return com.google.zxing.client.android.R.string.result_product;
    }

    public final void handleButtonPress(int i)
    {
        ParsedResult parsedresult = getResult();
        String s;
        if (parsedresult instanceof ProductParsedResult)
        {
            s = ((ProductParsedResult)parsedresult).getNormalizedProductID();
        } else
        if (parsedresult instanceof ExpandedProductParsedResult)
        {
            s = ((ExpandedProductParsedResult)parsedresult).getRawText();
        } else
        {
            throw new IllegalArgumentException(parsedresult.getClass().toString());
        }
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            d(s);
            return;

        case 1: // '\001'
            h(s);
            return;

        case 2: // '\002'
            g(j(s));
            break;
        }
    }

    static 
    {
        int ai[] = new int[3];
        ai[0] = com.google.zxing.client.android.R.string.button_product_search;
        ai[1] = com.google.zxing.client.android.R.string.button_web_search;
        ai[2] = com.google.zxing.client.android.R.string.button_custom_product_search;
        a = ai;
    }
}
