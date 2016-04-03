// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.Payments;

public class lq
    implements Payments
{

    public lq()
    {
    }

    public void changeMaskedWallet(GoogleApiClient googleapiclient, String s, String s1, int i)
    {
        googleapiclient.a(new _cls4(s, s1, i));
    }

    public void checkForPreAuthorization(GoogleApiClient googleapiclient, int i)
    {
        googleapiclient.a(new _cls1(i));
    }

    public void loadFullWallet(GoogleApiClient googleapiclient, FullWalletRequest fullwalletrequest, int i)
    {
        googleapiclient.a(new _cls3(fullwalletrequest, i));
    }

    public void loadMaskedWallet(GoogleApiClient googleapiclient, MaskedWalletRequest maskedwalletrequest, int i)
    {
        googleapiclient.a(new _cls2(maskedwalletrequest, i));
    }

    public void notifyTransactionStatus(GoogleApiClient googleapiclient, NotifyTransactionStatusRequest notifytransactionstatusrequest)
    {
        googleapiclient.a(new _cls5(notifytransactionstatusrequest));
    }

    private class _cls4 extends com.google.android.gms.wallet.Wallet.b
    {

        final int UD;
        final lq akG;
        final String akJ;
        final String akK;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((lr)a1);
        }

        protected void a(lr lr1)
        {
            lr1.d(akJ, akK, UD);
            b(Status.En);
        }

        _cls4(String s, String s1, int i)
        {
            akG = lq.this;
            akJ = s;
            akK = s1;
            UD = i;
            super();
        }
    }


    private class _cls1 extends com.google.android.gms.wallet.Wallet.b
    {

        final int UD;
        final lq akG;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((lr)a1);
        }

        protected void a(lr lr1)
        {
            lr1.dQ(UD);
            b(Status.En);
        }

        _cls1(int i)
        {
            akG = lq.this;
            UD = i;
            super();
        }
    }


    private class _cls3 extends com.google.android.gms.wallet.Wallet.b
    {

        final int UD;
        final lq akG;
        final FullWalletRequest akI;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((lr)a1);
        }

        protected void a(lr lr1)
        {
            lr1.a(akI, UD);
            b(Status.En);
        }

        _cls3(FullWalletRequest fullwalletrequest, int i)
        {
            akG = lq.this;
            akI = fullwalletrequest;
            UD = i;
            super();
        }
    }


    private class _cls2 extends com.google.android.gms.wallet.Wallet.b
    {

        final int UD;
        final lq akG;
        final MaskedWalletRequest akH;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((lr)a1);
        }

        protected void a(lr lr1)
        {
            lr1.a(akH, UD);
            b(Status.En);
        }

        _cls2(MaskedWalletRequest maskedwalletrequest, int i)
        {
            akG = lq.this;
            akH = maskedwalletrequest;
            UD = i;
            super();
        }
    }


    private class _cls5 extends com.google.android.gms.wallet.Wallet.b
    {

        final lq akG;
        final NotifyTransactionStatusRequest akL;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((lr)a1);
        }

        protected void a(lr lr1)
        {
            lr1.a(akL);
            b(Status.En);
        }

        _cls5(NotifyTransactionStatusRequest notifytransactionstatusrequest)
        {
            akG = lq.this;
            akL = notifytransactionstatusrequest;
            super();
        }
    }

}
