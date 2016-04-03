// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.TelParsedResult;

// Referenced classes of package com.google.zxing.client.android.result:
//            ResultHandler

public final class TelResultHandler extends ResultHandler
{

    private static final int a[];

    public TelResultHandler(Activity activity, ParsedResult parsedresult)
    {
        super(activity, parsedresult);
    }

    public final int getButtonCount()
    {
        int[] _tmp = a;
        return 2;
    }

    public final int getButtonText(int i)
    {
        return a[i];
    }

    public final CharSequence getDisplayContents()
    {
        return PhoneNumberUtils.formatNumber(getResult().getDisplayResult().replace("\r", ""));
    }

    public final int getDisplayTitle()
    {
        return com.google.zxing.client.android.R.string.result_tel;
    }

    public final void handleButtonPress(int i)
    {
        TelParsedResult telparsedresult = (TelParsedResult)getResult();
        String as[];
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            c(telparsedresult.getTelURI());
            b().finish();
            return;

        case 1: // '\001'
            as = new String[1];
            break;
        }
        as[0] = telparsedresult.getNumber();
        a(as, null);
    }

    static 
    {
        int ai[] = new int[2];
        ai[0] = com.google.zxing.client.android.R.string.button_dial;
        ai[1] = com.google.zxing.client.android.R.string.button_add_contact;
        a = ai;
    }
}
