// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.login;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.io.IOException;

// Referenced classes of package com.flipkart.android.login:
//            GoogleTokenFetcher

final class h
    implements AccountManagerCallback
{

    private Account a;
    private boolean b;
    private GoogleTokenFetcher c;

    public h(GoogleTokenFetcher googletokenfetcher, Account account)
    {
        c = googletokenfetcher;
        super();
        b = true;
        a = account;
    }

    public final void run(AccountManagerFuture accountmanagerfuture)
    {
        Bundle bundle;
        bundle = (Bundle)accountmanagerfuture.getResult();
        if (bundle.get("intent") != null)
        {
            Intent intent = (Intent)bundle.getParcelable("intent");
            intent.setFlags(0xefffffff & intent.getFlags());
            GoogleTokenFetcher.c(c).startActivityForResult(intent, GoogleTokenFetcher.b(c));
            return;
        }
        OperationCanceledException operationcanceledexception;
        String s;
        if (bundle.getString("authtoken") == null)
        {
            break MISSING_BLOCK_LABEL_192;
        }
        s = bundle.getString("authtoken");
        if (b)
        {
            GoogleTokenFetcher.e(c).invalidateAuthToken(GoogleTokenFetcher.d(c), s);
            b = false;
            GoogleTokenFetcher.e(c).getAuthToken(a, GoogleTokenFetcher.f(c), null, GoogleTokenFetcher.c(c), this, null);
            return;
        }
        try
        {
            GoogleTokenFetcher.b(c, s);
            GoogleTokenFetcher.a(c, 0, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (OperationCanceledException operationcanceledexception)
        {
            GoogleTokenFetcher.a(c, 1);
            return;
        }
        catch (AuthenticatorException authenticatorexception)
        {
            GoogleTokenFetcher.a(c, 2);
            return;
        }
        catch (IOException ioexception)
        {
            GoogleTokenFetcher.a(c, 3);
        }
        break MISSING_BLOCK_LABEL_210;
        GoogleTokenFetcher.a(c, 0);
        return;
    }
}
