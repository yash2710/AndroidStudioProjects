// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.client.result.EmailAddressParsedResult;
import com.google.zxing.client.result.ParsedResult;

// Referenced classes of package com.google.zxing.client.android.result:
//            ResultHandler

public final class EmailAddressResultHandler extends ResultHandler
{

    private static final int a[];

    public EmailAddressResultHandler(Activity activity, ParsedResult parsedresult)
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

    public final int getDisplayTitle()
    {
        return com.google.zxing.client.android.R.string.result_email_address;
    }

    public final void handleButtonPress(int i)
    {
        EmailAddressParsedResult emailaddressparsedresult = (EmailAddressParsedResult)getResult();
        String as[];
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            a(emailaddressparsedresult.getMailtoURI(), emailaddressparsedresult.getEmailAddress(), emailaddressparsedresult.getSubject(), emailaddressparsedresult.getBody());
            return;

        case 1: // '\001'
            as = new String[1];
            break;
        }
        as[0] = emailaddressparsedresult.getEmailAddress();
        a(null, null, null, null, null, as, null, null, null, null, null, null, null, null, null, null);
    }

    static 
    {
        int ai[] = new int[2];
        ai[0] = com.google.zxing.client.android.R.string.button_email;
        ai[1] = com.google.zxing.client.android.R.string.button_add_contact;
        a = ai;
    }
}
