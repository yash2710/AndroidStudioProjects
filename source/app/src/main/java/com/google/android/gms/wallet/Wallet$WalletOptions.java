// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import java.util.Locale;

public final class Builder
    implements com.google.android.gms.common.api.ions
{

    public final int environment;
    public final int theme;

    private Builder()
    {
        class Builder
        {

            private int akb;
            private int mTheme;

            static int a(Builder builder)
            {
                return builder.akb;
            }

            static int b(Builder builder)
            {
                return builder.mTheme;
            }

            public final Wallet.WalletOptions build()
            {
                return new Wallet.WalletOptions(this, null);
            }

            public final Builder setEnvironment(int i)
            {
                if (i == 0 || i == 2 || i == 1)
                {
                    akb = i;
                    return this;
                } else
                {
                    Locale locale = Locale.US;
                    Object aobj[] = new Object[1];
                    aobj[0] = Integer.valueOf(i);
                    throw new IllegalArgumentException(String.format(locale, "Invalid environment value %d", aobj));
                }
            }

            public final Builder setTheme(int i)
            {
                if (i == 0 || i == 1)
                {
                    mTheme = i;
                    return this;
                } else
                {
                    Locale locale = Locale.US;
                    Object aobj[] = new Object[1];
                    aobj[0] = Integer.valueOf(i);
                    throw new IllegalArgumentException(String.format(locale, "Invalid theme value %d", aobj));
                }
            }

            public Builder()
            {
                akb = 0;
                mTheme = 0;
            }
        }

        this(new Builder());
    }

    Builder(Builder builder)
    {
        this();
    }

    private Builder(Builder builder)
    {
        environment = Builder.a(builder);
        theme = Builder.b(builder);
    }

    Builder(Builder builder, Builder builder1)
    {
        this(builder);
    }
}
