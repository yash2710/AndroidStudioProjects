// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import com.flipkart.android.activity.GoogleLoginWebActivity;
import com.flipkart.android.utils.FkDialogHelper;
import com.flipkart.android.utils.StringUtils;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

// Referenced classes of package com.flipkart.android.login:
//            e, GoogleLoginConstants, g, f, 
//            h, d, onGoogleTokenFetchedListener

public class GoogleTokenFetcher
{

    private Activity a;
    private AccountManager b;
    private String c;
    private onGoogleTokenFetchedListener d;
    private int e;
    private int f;
    private String g;
    private String h;
    private AlertDialog i;

    public GoogleTokenFetcher(Activity activity, onGoogleTokenFetchedListener ongoogletokenfetchedlistener)
    {
        g = "oauth2:https://www.googleapis.com/auth/userinfo.email";
        h = "com.google";
        a = activity;
        b = AccountManager.get(a);
        d = ongoogletokenfetchedlistener;
        c = "";
    }

    private AlertDialog a(List list, String s)
    {
        CharSequence acharsequence[] = new CharSequence[1 + list.size()];
        for (int j = 0; j < list.size(); j++)
        {
            acharsequence[j] = ((Account)list.get(j)).name;
        }

        acharsequence[list.size()] = "Choose another account";
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(a);
        builder.setTitle(s);
        builder.setItems(acharsequence, new e(this, list));
        return builder.create();
    }

    static String a(GoogleTokenFetcher googletokenfetcher, String s)
    {
        JSONObject _tmp = JVM INSTR new #92  <Class JSONObject>;
        String s1 = JSONObjectInstrumentation.init(s).getString(GoogleLoginConstants.KKeyAccessToken);
        if (StringUtils.isNullOrEmpty(s1))
        {
            throw new Exception();
        } else
        {
            return s1;
        }
    }

    private void a()
    {
        Intent intent = new Intent(a, com/flipkart/android/activity/GoogleLoginWebActivity);
        a.startActivityForResult(intent, f);
    }

    private void a(int j)
    {
        if (d == null)
        {
            return;
        } else
        {
            a.runOnUiThread(new g(this, j));
            return;
        }
    }

    private void a(int j, String s)
    {
        if (d == null)
        {
            return;
        } else
        {
            a.runOnUiThread(new f(this, j, s));
            return;
        }
    }

    static void a(GoogleTokenFetcher googletokenfetcher)
    {
        googletokenfetcher.a();
    }

    static void a(GoogleTokenFetcher googletokenfetcher, int j)
    {
        googletokenfetcher.a(j);
    }

    static void a(GoogleTokenFetcher googletokenfetcher, int j, String s)
    {
        googletokenfetcher.a(j, s);
    }

    static void a(GoogleTokenFetcher googletokenfetcher, Account account)
    {
        googletokenfetcher.a(1, "");
        googletokenfetcher.b.getAuthToken(account, googletokenfetcher.g, null, googletokenfetcher.a, new h(googletokenfetcher, account), null);
    }

    static int b(GoogleTokenFetcher googletokenfetcher)
    {
        return googletokenfetcher.e;
    }

    static void b(GoogleTokenFetcher googletokenfetcher, String s)
    {
        if (s != null)
        {
            googletokenfetcher.c = s;
        }
    }

    static Activity c(GoogleTokenFetcher googletokenfetcher)
    {
        return googletokenfetcher.a;
    }

    static String d(GoogleTokenFetcher googletokenfetcher)
    {
        return googletokenfetcher.h;
    }

    static AccountManager e(GoogleTokenFetcher googletokenfetcher)
    {
        return googletokenfetcher.b;
    }

    static String f(GoogleTokenFetcher googletokenfetcher)
    {
        return googletokenfetcher.g;
    }

    static onGoogleTokenFetchedListener g(GoogleTokenFetcher googletokenfetcher)
    {
        return googletokenfetcher.d;
    }

    public void cancelDialog()
    {
        if (i != null)
        {
            FkDialogHelper.cancelDialog(i);
        }
    }

    public void fetchToken(int j, int k)
    {
        e = j;
        f = k;
        ArrayList arraylist = new ArrayList();
        Account aaccount[] = b.getAccounts();
        int l = aaccount.length;
        for (int i1 = 0; i1 < l; i1++)
        {
            Account account = aaccount[i1];
            if (account.type.compareToIgnoreCase(h) == 0)
            {
                arraylist.add(account);
            }
        }

        if (arraylist.size() == 0)
        {
            a();
            return;
        } else
        {
            i = a(arraylist, "Select an account to login");
            i.setCanceledOnTouchOutside(true);
            FkDialogHelper.showDialog(i);
            return;
        }
    }

    public void fetchTokenCallback(int j, int k, Intent intent)
    {
        if (j == e)
        {
            if (k == -1)
            {
                fetchToken(e, f);
                return;
            }
            if (k == 0)
            {
                a(1);
                return;
            } else
            {
                a(0);
                return;
            }
        }
        if (j == f)
        {
            if (k == -1)
            {
                if (intent != null)
                {
                    String s = intent.getStringExtra(GoogleLoginWebActivity.KKeyAuthCodeStatus);
                    if (s != null && s.equals(GoogleLoginWebActivity.KValueAuthCodeStatusSuccess))
                    {
                        (new d(this, intent.getStringExtra(GoogleLoginWebActivity.KKeyAuthCode), this)).start();
                        return;
                    } else
                    {
                        a(0);
                        return;
                    }
                } else
                {
                    a(0);
                    return;
                }
            }
            if (k == 0)
            {
                a(1);
                return;
            } else
            {
                a(0);
                return;
            }
        } else
        {
            a(0);
            return;
        }
    }

    public String getAuthToken()
    {
        return c;
    }
}
