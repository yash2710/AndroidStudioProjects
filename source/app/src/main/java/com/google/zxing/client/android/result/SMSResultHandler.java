// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.SMSParsedResult;

// Referenced classes of package com.google.zxing.client.android.result:
//            ResultHandler

public final class SMSResultHandler extends ResultHandler
{

    private static final int a[];

    public SMSResultHandler(Activity activity, ParsedResult parsedresult)
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
        SMSParsedResult smsparsedresult = (SMSParsedResult)getResult();
        String as[] = smsparsedresult.getNumbers();
        String as1[] = new String[as.length];
        for (int i = 0; i < as.length; i++)
        {
            as1[i] = PhoneNumberUtils.formatNumber(as[i]);
        }

        StringBuilder stringbuilder = new StringBuilder(50);
        ParsedResult.maybeAppend(as1, stringbuilder);
        ParsedResult.maybeAppend(smsparsedresult.getSubject(), stringbuilder);
        ParsedResult.maybeAppend(smsparsedresult.getBody(), stringbuilder);
        return stringbuilder.toString();
    }

    public final int getDisplayTitle()
    {
        return com.google.zxing.client.android.R.string.result_sms;
    }

    public final void handleButtonPress(int i)
    {
        SMSParsedResult smsparsedresult = (SMSParsedResult)getResult();
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            a(smsparsedresult.getNumbers()[0], smsparsedresult.getBody());
            return;

        case 1: // '\001'
            a(smsparsedresult.getNumbers()[0], smsparsedresult.getSubject(), smsparsedresult.getBody());
            break;
        }
    }

    static 
    {
        int ai[] = new int[2];
        ai[0] = com.google.zxing.client.android.R.string.button_sms;
        ai[1] = com.google.zxing.client.android.R.string.button_mms;
        a = ai;
    }
}
