// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.view.View;
import com.google.zxing.client.result.ProductParsedResult;

// Referenced classes of package com.google.zxing.client.android.result:
//            ProductResultHandler

final class b
    implements android.view.View.OnClickListener
{

    private ProductResultHandler a;

    b(ProductResultHandler productresulthandler)
    {
        a = productresulthandler;
        super();
    }

    public final void onClick(View view)
    {
        ProductParsedResult productparsedresult = (ProductParsedResult)a.getResult();
        a.i(productparsedresult.getNormalizedProductID());
    }
}
