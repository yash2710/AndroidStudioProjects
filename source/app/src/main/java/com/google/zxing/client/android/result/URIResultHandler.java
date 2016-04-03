// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.client.android.LocaleManager;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.URIParsedResult;
import java.util.Locale;

// Referenced classes of package com.google.zxing.client.android.result:
//            ResultHandler

public final class URIResultHandler extends ResultHandler
{

    private static final String a[] = {
        "otpauth:"
    };
    private static final int b[];

    public URIResultHandler(Activity activity, ParsedResult parsedresult)
    {
        super(activity, parsedresult);
    }

    public final boolean areContentsSecure()
    {
        String s = ((URIParsedResult)getResult()).getURI().toLowerCase(Locale.ENGLISH);
        String as[] = a;
        for (int i = 0; i <= 0; i++)
        {
            if (s.startsWith(as[0]))
            {
                return true;
            }
        }

        return false;
    }

    public final int getButtonCount()
    {
        if (LocaleManager.isBookSearchUrl(((URIParsedResult)getResult()).getURI()))
        {
            int[] _tmp = b;
            return 4;
        } else
        {
            int[] _tmp1 = b;
            return 3;
        }
    }

    public final int getButtonText(int i)
    {
        return b[i];
    }

    public final int getDisplayTitle()
    {
        return com.google.zxing.client.android.R.string.result_uri;
    }

    public final void handleButtonPress(int i)
    {
        String s = ((URIParsedResult)getResult()).getURI();
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            g(s);
            return;

        case 1: // '\001'
            a(s);
            return;

        case 2: // '\002'
            b(s);
            return;

        case 3: // '\003'
            f(s);
            break;
        }
    }

    static 
    {
        int ai[] = new int[4];
        ai[0] = com.google.zxing.client.android.R.string.button_open_browser;
        ai[1] = com.google.zxing.client.android.R.string.button_share_by_email;
        ai[2] = com.google.zxing.client.android.R.string.button_share_by_sms;
        ai[3] = com.google.zxing.client.android.R.string.button_search_book_contents;
        b = ai;
    }
}
