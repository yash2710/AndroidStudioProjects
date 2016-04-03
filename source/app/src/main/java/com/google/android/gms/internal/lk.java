// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public interface lk
    extends IInterface
{

    public abstract void a(d d, WalletFragmentOptions walletfragmentoptions, Bundle bundle);

    public abstract int getState();

    public abstract void initialize(WalletFragmentInitParams walletfragmentinitparams);

    public abstract void onActivityResult(int i, int j, Intent intent);

    public abstract void onCreate(Bundle bundle);

    public abstract d onCreateView(d d, d d1, Bundle bundle);

    public abstract void onPause();

    public abstract void onResume();

    public abstract void onSaveInstanceState(Bundle bundle);

    public abstract void onStart();

    public abstract void onStop();

    public abstract void setEnabled(boolean flag);

    public abstract void updateMaskedWallet(MaskedWallet maskedwallet);

    public abstract void updateMaskedWalletRequest(MaskedWalletRequest maskedwalletrequest);
}
