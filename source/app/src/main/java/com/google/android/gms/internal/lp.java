// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;

// Referenced classes of package com.google.android.gms.internal:
//            li

public interface lp
    extends IInterface
{

    public abstract void a(int j, FullWallet fullwallet, Bundle bundle);

    public abstract void a(int j, MaskedWallet maskedwallet, Bundle bundle);

    public abstract void a(int j, boolean flag, Bundle bundle);

    public abstract void a(Status status, li li, Bundle bundle);

    public abstract void i(int j, Bundle bundle);
}
