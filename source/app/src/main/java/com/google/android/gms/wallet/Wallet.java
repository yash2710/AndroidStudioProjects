// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.lf;
import com.google.android.gms.internal.lq;
import com.google.android.gms.internal.lt;
import com.google.android.gms.internal.lu;
import com.google.android.gms.wallet.wobs.r;

// Referenced classes of package com.google.android.gms.wallet:
//            Payments, FullWalletRequest, MaskedWalletRequest, NotifyTransactionStatusRequest

public final class Wallet
{

    public static final Api API;
    public static final Payments Payments = new lq();
    public static final r ajZ = new lu();
    public static final lf aka = new lt();
    private static final com.google.android.gms.common.api.Api.c yH;
    private static final com.google.android.gms.common.api.Api.b yI;

    private Wallet()
    {
    }

    public static void changeMaskedWallet(GoogleApiClient googleapiclient, String s, String s1, int i)
    {
        Payments.changeMaskedWallet(googleapiclient, s, s1, i);
    }

    public static void checkForPreAuthorization(GoogleApiClient googleapiclient, int i)
    {
        Payments.checkForPreAuthorization(googleapiclient, i);
    }

    public static void loadFullWallet(GoogleApiClient googleapiclient, FullWalletRequest fullwalletrequest, int i)
    {
        Payments.loadFullWallet(googleapiclient, fullwalletrequest, i);
    }

    public static void loadMaskedWallet(GoogleApiClient googleapiclient, MaskedWalletRequest maskedwalletrequest, int i)
    {
        Payments.loadMaskedWallet(googleapiclient, maskedwalletrequest, i);
    }

    static com.google.android.gms.common.api.Api.c nh()
    {
        return yH;
    }

    public static void notifyTransactionStatus(GoogleApiClient googleapiclient, NotifyTransactionStatusRequest notifytransactionstatusrequest)
    {
        Payments.notifyTransactionStatus(googleapiclient, notifytransactionstatusrequest);
    }

    static 
    {
        yH = new com.google.android.gms.common.api.Api.c();
        yI = new _cls1();
        API = new Api(yI, yH, new Scope[0]);
    }

    private class _cls1
        implements com.google.android.gms.common.api.Api.b
    {

        public final volatile com.google.android.gms.common.api.Api.a a(Context context, Looper looper, gy gy1, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            return a(context, looper, gy1, (WalletOptions)obj, connectioncallbacks, onconnectionfailedlistener);
        }

        public final lr a(Context context, Looper looper, gy gy1, WalletOptions walletoptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            hm.b(context instanceof Activity, "An Activity must be used for Wallet APIs");
            Activity activity = (Activity)context;
            if (walletoptions == null)
            {
                walletoptions = new WalletOptions(null);
            }
            return new lr(activity, looper, connectioncallbacks, onconnectionfailedlistener, walletoptions.environment, gy1.getAccountName(), walletoptions.theme);
        }

        public final int getPriority()
        {
            return 0x7fffffff;
        }

        _cls1()
        {
        }

        private class WalletOptions
            implements com.google.android.gms.common.api.Api.ApiOptions.HasOptions
        {

            public final int environment;
            public final int theme;

            private WalletOptions()
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

                    public final WalletOptions build()
                    {
                        return new WalletOptions(this, null);
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

            WalletOptions(_cls1 _pcls1)
            {
                this();
            }

            private WalletOptions(Builder builder)
            {
                environment = Builder.a(builder);
                theme = Builder.b(builder);
            }

            WalletOptions(Builder builder, _cls1 _pcls1)
            {
                this(builder);
            }
        }

    }

}
