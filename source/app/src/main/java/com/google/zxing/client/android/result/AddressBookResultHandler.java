// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ParsedResult;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// Referenced classes of package com.google.zxing.client.android.result:
//            ResultHandler

public final class AddressBookResultHandler extends ResultHandler
{

    private static final DateFormat a[];
    private static final int b[];
    private final boolean c[] = new boolean[4];
    private int d;

    public AddressBookResultHandler(Activity activity, ParsedResult parsedresult)
    {
        int i = 0;
        super(activity, parsedresult);
        AddressBookParsedResult addressbookparsedresult = (AddressBookParsedResult)parsedresult;
        String as[] = addressbookparsedresult.getAddresses();
        boolean flag;
        String as1[];
        boolean flag1;
        String as2[];
        boolean flag2;
        if (as != null && as.length > 0 && as[0] != null && as[0].length() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        as1 = addressbookparsedresult.getPhoneNumbers();
        if (as1 != null && as1.length > 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        as2 = addressbookparsedresult.getEmails();
        if (as2 != null && as2.length > 0)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        c[0] = true;
        c[1] = flag;
        c[2] = flag1;
        c[3] = flag2;
        d = 0;
        for (; i < 4; i++)
        {
            if (c[i])
            {
                d = 1 + d;
            }
        }

    }

    private int a(int i)
    {
        if (i < d)
        {
            int j = 0;
            int l = -1;
            for (; j < 4; j++)
            {
                if (c[j])
                {
                    l++;
                }
                if (l == i)
                {
                    return j;
                }
            }

        }
        return -1;
    }

    private static Date k(String s)
    {
        DateFormat adateformat[];
        int i;
        adateformat = a;
        i = 0;
_L2:
        DateFormat dateformat;
        if (i >= 4)
        {
            break; /* Loop/switch isn't completed */
        }
        dateformat = adateformat[i];
        Date date = dateformat.parse(s);
        return date;
        ParseException parseexception;
        parseexception;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        return null;
    }

    public final int getButtonCount()
    {
        return d;
    }

    public final int getButtonText(int i)
    {
        return b[a(i)];
    }

    public final CharSequence getDisplayContents()
    {
        AddressBookParsedResult addressbookparsedresult = (AddressBookParsedResult)getResult();
        StringBuilder stringbuilder = new StringBuilder(100);
        ParsedResult.maybeAppend(addressbookparsedresult.getNames(), stringbuilder);
        int i = stringbuilder.length();
        String s = addressbookparsedresult.getPronunciation();
        if (s != null && s.length() > 0)
        {
            stringbuilder.append("\n(");
            stringbuilder.append(s);
            stringbuilder.append(')');
        }
        ParsedResult.maybeAppend(addressbookparsedresult.getTitle(), stringbuilder);
        ParsedResult.maybeAppend(addressbookparsedresult.getOrg(), stringbuilder);
        ParsedResult.maybeAppend(addressbookparsedresult.getAddresses(), stringbuilder);
        String as[] = addressbookparsedresult.getPhoneNumbers();
        if (as != null)
        {
            int j = as.length;
            for (int l = 0; l < j; l++)
            {
                ParsedResult.maybeAppend(PhoneNumberUtils.formatNumber(as[l]), stringbuilder);
            }

        }
        ParsedResult.maybeAppend(addressbookparsedresult.getEmails(), stringbuilder);
        ParsedResult.maybeAppend(addressbookparsedresult.getURLs(), stringbuilder);
        String s1 = addressbookparsedresult.getBirthday();
        if (s1 != null && s1.length() > 0)
        {
            Date date = k(s1);
            if (date != null)
            {
                ParsedResult.maybeAppend(DateFormat.getDateInstance(2).format(Long.valueOf(date.getTime())), stringbuilder);
            }
        }
        ParsedResult.maybeAppend(addressbookparsedresult.getNote(), stringbuilder);
        if (i > 0)
        {
            SpannableString spannablestring = new SpannableString(stringbuilder.toString());
            spannablestring.setSpan(new StyleSpan(1), 0, i, 0);
            return spannablestring;
        } else
        {
            return stringbuilder.toString();
        }
    }

    public final int getDisplayTitle()
    {
        return com.google.zxing.client.android.R.string.result_address_book;
    }

    public final void handleButtonPress(int i)
    {
        AddressBookParsedResult addressbookparsedresult = (AddressBookParsedResult)getResult();
        String as[] = addressbookparsedresult.getAddresses();
        String s;
        String as1[];
        String s1;
        if (as == null || as.length <= 0)
        {
            s = null;
        } else
        {
            s = as[0];
        }
        as1 = addressbookparsedresult.getAddressTypes();
        if (as1 == null || as1.length <= 0)
        {
            s1 = null;
        } else
        {
            s1 = as1[0];
        }
        switch (a(i))
        {
        default:
            return;

        case 0: // '\0'
            a(addressbookparsedresult.getNames(), addressbookparsedresult.getNicknames(), addressbookparsedresult.getPronunciation(), addressbookparsedresult.getPhoneNumbers(), addressbookparsedresult.getPhoneTypes(), addressbookparsedresult.getEmails(), addressbookparsedresult.getEmailTypes(), addressbookparsedresult.getNote(), addressbookparsedresult.getInstantMessenger(), s, s1, addressbookparsedresult.getOrg(), addressbookparsedresult.getTitle(), addressbookparsedresult.getURLs(), addressbookparsedresult.getBirthday(), addressbookparsedresult.getGeo());
            return;

        case 1: // '\001'
            String as2[] = addressbookparsedresult.getNames();
            String s4;
            if (as2 != null)
            {
                s4 = as2[0];
            } else
            {
                s4 = null;
            }
            if (s4 != null && s4.length() > 0)
            {
                s = (new StringBuilder()).append(s).append(" (").append(s4).append(')').toString();
            }
            b(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("geo:0,0?q=")).append(Uri.encode(s)).toString())));
            return;

        case 2: // '\002'
            String s3 = addressbookparsedresult.getPhoneNumbers()[0];
            b(new Intent("android.intent.action.DIAL", Uri.parse((new StringBuilder("tel:")).append(s3).toString())));
            return;

        case 3: // '\003'
            String s2 = addressbookparsedresult.getEmails()[0];
            a((new StringBuilder("mailto:")).append(s2).toString(), s2, null, null);
            return;
        }
    }

    static 
    {
        DateFormat adateformat[] = new DateFormat[4];
        adateformat[0] = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        adateformat[1] = new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH);
        adateformat[2] = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        adateformat[3] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        a = adateformat;
        for (int i = 0; i < 4; i++)
        {
            adateformat[i].setLenient(false);
        }

        int ai[] = new int[4];
        ai[0] = com.google.zxing.client.android.R.string.button_add_contact;
        ai[1] = com.google.zxing.client.android.R.string.button_show_map;
        ai[2] = com.google.zxing.client.android.R.string.button_dial;
        ai[3] = com.google.zxing.client.android.R.string.button_email;
        b = ai;
    }
}
