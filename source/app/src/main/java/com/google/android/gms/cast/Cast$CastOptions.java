// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import com.google.android.gms.internal.hm;

// Referenced classes of package com.google.android.gms.cast:
//            CastDevice

public final class Builder
    implements com.google.android.gms.common.api.sOptions
{

    final CastDevice Aa;
    final Builder Ab;
    private final int Ac;

    static int a(ons.HasOptions hasoptions)
    {
        return hasoptions.Ac;
    }

    public static Builder builder(CastDevice castdevice, Ac ac)
    {
        return new Builder(castdevice, ac, null);
    }

    private Builder(Builder builder1)
    {
        class Builder
        {

            CastDevice Ad;
            Cast.Listener Ae;
            private int Af;

            static int a(Builder builder2)
            {
                return builder2.Af;
            }

            public final Cast.CastOptions build()
            {
                return new Cast.CastOptions(this, null);
            }

            public final Builder setVerboseLoggingEnabled(boolean flag)
            {
                if (flag)
                {
                    Af = 1 | Af;
                    return this;
                } else
                {
                    Af = -2 & Af;
                    return this;
                }
            }

            private Builder(CastDevice castdevice, Cast.Listener listener)
            {
                hm.b(castdevice, "CastDevice parameter cannot be null");
                hm.b(listener, "CastListener parameter cannot be null");
                Ad = castdevice;
                Ae = listener;
                Af = 0;
            }

            Builder(CastDevice castdevice, Cast.Listener listener, Cast._cls1 _pcls1)
            {
                this(castdevice, listener);
            }
        }

        Aa = builder1.Ad;
        Ab = builder1.Ae;
        Ac = Builder.a(builder1);
    }

    Builder(Builder builder1, Builder builder2)
    {
        this(builder1);
    }
}
