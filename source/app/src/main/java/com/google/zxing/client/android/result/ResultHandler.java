// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.View;
import com.flipkart.logging.FkLogger;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.LocaleManager;
import com.google.zxing.client.android.book.SearchBookContentsActivity;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.ResultParser;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Locale;

// Referenced classes of package com.google.zxing.client.android.result:
//            c

public abstract class ResultHandler
{

    public static final int MAX_BUTTON_COUNT = 4;
    private static final String a = com/google/zxing/client/android/result/ResultHandler.getSimpleName();
    private static final String b[] = {
        "home", "work", "mobile"
    };
    private static final String c[] = {
        "home", "work", "mobile", "fax", "pager", "main"
    };
    private static final String d[] = {
        "home", "work"
    };
    private static final int e[] = {
        1, 2, 4
    };
    private static final int f[] = {
        1, 3, 2, 4, 6, 12
    };
    private static final int g[] = {
        1, 2
    };
    private final ParsedResult h;
    private final Activity i;
    private final Result j;
    private final String k;
    private final android.content.DialogInterface.OnClickListener l;

    ResultHandler(Activity activity, ParsedResult parsedresult)
    {
        this(activity, parsedresult, null);
    }

    ResultHandler(Activity activity, ParsedResult parsedresult, Result result)
    {
        String s;
        l = new c(this);
        h = parsedresult;
        i = activity;
        j = result;
        s = PreferenceManager.getDefaultSharedPreferences(i).getString("preferences_custom_product_search", null);
        if (s == null) goto _L2; else goto _L1
_L1:
        String s1;
        int i1;
        i1 = s.trim().length();
        s1 = null;
        if (i1 != 0) goto _L2; else goto _L3
_L3:
        k = s1;
        activity.findViewById(com.google.zxing.client.android.R.id.shopper_button).setVisibility(8);
        return;
_L2:
        s1 = s;
        if (true) goto _L3; else goto _L4
_L4:
    }

    private static int a(String s, String as[], int ai[])
    {
        if (s == null)
        {
            return -1;
        }
        for (int i1 = 0; i1 < as.length; i1++)
        {
            String s1 = as[i1];
            if (s.startsWith(s1) || s.startsWith(s1.toUpperCase(Locale.ENGLISH)))
            {
                return ai[i1];
            }
        }

        return -1;
    }

    private static void a(Intent intent, String s, String s1)
    {
        if (s1 != null && s1.length() > 0)
        {
            intent.putExtra(s, s1);
        }
    }

    private void b(String s, String s1)
    {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(s));
        a(intent, "sms_body", s1);
        intent.putExtra("compose_mode", true);
        b(intent);
    }

    final void a(double d1, double d2)
    {
        b(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("http://maps.google.")).append(LocaleManager.getCountryTLD(i)).append("/maps?f=d&daddr=").append(d1).append(',').append(d2).toString())));
    }

    final void a(Intent intent)
    {
        if (intent != null)
        {
            intent.addFlags(0x80000);
            i.startActivity(intent);
        }
    }

    final void a(android.view.View.OnClickListener onclicklistener)
    {
        View view = i.findViewById(com.google.zxing.client.android.R.id.shopper_button);
        view.setVisibility(0);
        view.setOnClickListener(onclicklistener);
    }

    final void a(String s)
    {
        a("mailto:", null, i.getString(com.google.zxing.client.android.R.string.msg_share_subject_line), s);
    }

    final void a(String s, String s1)
    {
        b((new StringBuilder("smsto:")).append(s).toString(), s1);
    }

    final void a(String s, String s1, String s2)
    {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse((new StringBuilder("mmsto:")).append(s).toString()));
        if (s1 == null || s1.length() == 0)
        {
            a(intent, "subject", i.getString(com.google.zxing.client.android.R.string.msg_default_mms_subject));
        } else
        {
            a(intent, "subject", s1);
        }
        a(intent, "sms_body", s2);
        intent.putExtra("compose_mode", true);
        b(intent);
    }

    final void a(String s, String s1, String s2, String s3)
    {
        Intent intent = new Intent("android.intent.action.SEND", Uri.parse(s));
        if (s1 != null)
        {
            intent.putExtra("android.intent.extra.EMAIL", new String[] {
                s1
            });
        }
        a(intent, "android.intent.extra.SUBJECT", s2);
        a(intent, "android.intent.extra.TEXT", s3);
        intent.setType("text/plain");
        b(intent);
    }

    final void a(String as[], String as1[])
    {
        a(null, null, null, as, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    final void a(String as[], String as1[], String s, String as2[], String as3[], String as4[], String as5[], 
            String s1, String s2, String s3, String s4, String s5, String s6, String as6[], 
            String s7, String as7[])
    {
        Intent intent = new Intent("android.intent.action.INSERT_OR_EDIT", android.provider.ContactsContract.Contacts.CONTENT_URI);
        intent.setType("vnd.android.cursor.item/contact");
        String s8;
        int i1;
        int j1;
        if (as != null)
        {
            s8 = as[0];
        } else
        {
            s8 = null;
        }
        a(intent, "name", s8);
        a(intent, "phonetic_name", s);
        if (as2 != null)
        {
            i1 = as2.length;
        } else
        {
            i1 = 0;
        }
        j1 = Math.min(i1, Contents.PHONE_KEYS.length);
        for (int k1 = 0; k1 < j1; k1++)
        {
            a(intent, Contents.PHONE_KEYS[k1], as2[k1]);
            if (as3 == null || k1 >= as3.length)
            {
                continue;
            }
            int j4 = a(as3[k1], c, f);
            if (j4 >= 0)
            {
                intent.putExtra(Contents.PHONE_TYPE_KEYS[k1], j4);
            }
        }

        int l1;
        int i2;
        if (as4 != null)
        {
            l1 = as4.length;
        } else
        {
            l1 = 0;
        }
        i2 = Math.min(l1, Contents.EMAIL_KEYS.length);
        for (int j2 = 0; j2 < i2; j2++)
        {
            a(intent, Contents.EMAIL_KEYS[j2], as4[j2]);
            if (as5 == null || j2 >= as5.length)
            {
                continue;
            }
            int i4 = a(as5[j2], b, e);
            if (i4 >= 0)
            {
                intent.putExtra(Contents.EMAIL_TYPE_KEYS[j2], i4);
            }
        }

        StringBuilder stringbuilder = new StringBuilder();
        if (as6 != null)
        {
            int k3 = as6.length;
            for (int l3 = 0; l3 < k3; l3++)
            {
                String s11 = as6[l3];
                if (s11 != null && s11.length() > 0)
                {
                    stringbuilder.append('\n').append(s11);
                }
            }

        }
        String as8[] = {
            s7, s1
        };
        for (int k2 = 0; k2 < 2; k2++)
        {
            String s10 = as8[k2];
            if (s10 != null)
            {
                stringbuilder.append('\n').append(s10);
            }
        }

        if (as1 != null)
        {
            int i3 = as1.length;
            for (int j3 = 0; j3 < i3; j3++)
            {
                String s9 = as1[j3];
                if (s9 != null && s9.length() > 0)
                {
                    stringbuilder.append('\n').append(s9);
                }
            }

        }
        if (as7 != null)
        {
            stringbuilder.append('\n').append(as7[0]).append(',').append(as7[1]);
        }
        if (stringbuilder.length() > 0)
        {
            a(intent, "notes", stringbuilder.substring(1));
        }
        a(intent, "im_handle", s2);
        a(intent, "postal", s3);
        if (s4 != null)
        {
            int l2 = a(s4, d, g);
            if (l2 >= 0)
            {
                intent.putExtra("postal_type", l2);
            }
        }
        a(intent, "company", s5);
        a(intent, "job_title", s6);
        b(intent);
    }

    final boolean a()
    {
        return k != null;
    }

    public boolean areContentsSecure()
    {
        return false;
    }

    final Activity b()
    {
        return i;
    }

    final void b(Intent intent)
    {
        try
        {
            a(intent);
            return;
        }
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(i);
            builder.setTitle(com.google.zxing.client.android.R.string.app_name);
            builder.setMessage(com.google.zxing.client.android.R.string.msg_intent_failed);
            builder.setPositiveButton(com.google.zxing.client.android.R.string.button_ok, null);
            builder.show();
            return;
        }
    }

    final void b(String s)
    {
        b("smsto:", (new StringBuilder()).append(i.getString(com.google.zxing.client.android.R.string.msg_share_subject_line)).append(":\n").append(s).toString());
    }

    final void c(String s)
    {
        b(new Intent("android.intent.action.DIAL", Uri.parse(s)));
    }

    final void d(String s)
    {
        b(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("http://www.google.")).append(LocaleManager.getProductSearchCountryTLD(i)).append("/m/products?q=").append(s).append("&source=zxing").toString())));
    }

    final void e(String s)
    {
        b(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("http://books.google.")).append(LocaleManager.getBookSearchCountryTLD(i)).append("/books?vid=isbn").append(s).toString())));
    }

    final void f(String s)
    {
        Intent intent = new Intent("com.google.zxing.client.android.SEARCH_BOOK_CONTENTS");
        intent.setClassName(i, com/google/zxing/client/android/book/SearchBookContentsActivity.getName());
        a(intent, "ISBN", s);
        b(intent);
    }

    final void g(String s)
    {
        Intent intent;
        if (s.startsWith("HTTP://"))
        {
            s = (new StringBuilder("http")).append(s.substring(4)).toString();
        } else
        if (s.startsWith("HTTPS://"))
        {
            s = (new StringBuilder("https")).append(s.substring(5)).toString();
        }
        intent = new Intent("android.intent.action.VIEW", Uri.parse(s));
        try
        {
            b(intent);
            return;
        }
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            FkLogger.warn(a, (new StringBuilder("Nothing available to handle ")).append(intent).toString());
        }
    }

    public abstract int getButtonCount();

    public abstract int getButtonText(int i1);

    public CharSequence getDisplayContents()
    {
        return h.getDisplayResult().replace("\r", "");
    }

    public abstract int getDisplayTitle();

    public final ParsedResult getResult()
    {
        return h;
    }

    public final ParsedResultType getType()
    {
        return h.getType();
    }

    final void h(String s)
    {
        Intent intent = new Intent("android.intent.action.WEB_SEARCH");
        intent.putExtra("query", s);
        b(intent);
    }

    public abstract void handleButtonPress(int i1);

    final void i(String s)
    {
        Intent intent = new Intent("android.intent.action.SEARCH");
        intent.setClassName("com.google.android.apps.shopper", "com.google.android.apps.shopper.results.SearchResultsActivity");
        intent.putExtra("query", s);
        java.util.List list = i.getPackageManager().queryIntentActivities(intent, 0x10000);
        if (list != null && !list.isEmpty())
        {
            i.startActivity(intent);
            return;
        } else
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(i);
            builder.setTitle(com.google.zxing.client.android.R.string.msg_google_shopper_missing);
            builder.setMessage(com.google.zxing.client.android.R.string.msg_install_google_shopper);
            builder.setIcon(com.google.zxing.client.android.R.drawable.shopper_icon);
            builder.setPositiveButton(com.google.zxing.client.android.R.string.button_ok, l);
            builder.setNegativeButton(com.google.zxing.client.android.R.string.button_cancel, null);
            builder.show();
            return;
        }
    }

    final String j(String s)
    {
        if (k == null)
        {
            return s;
        }
        String s2 = URLEncoder.encode(s, "UTF-8");
        s = s2;
_L2:
        String s1 = k.replace("%s", s);
        if (j != null)
        {
            s1 = s1.replace("%f", j.getBarcodeFormat().toString());
            if (s1.contains("%t"))
            {
                s1 = s1.replace("%t", ResultParser.parseResult(j).getType().toString());
            }
        }
        return s1;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        if (true) goto _L2; else goto _L1
_L1:
    }

}
