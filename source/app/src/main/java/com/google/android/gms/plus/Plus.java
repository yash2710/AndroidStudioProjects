// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.hm;
import com.google.android.gms.internal.ki;
import com.google.android.gms.internal.kj;
import com.google.android.gms.internal.kk;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.km;
import com.google.android.gms.plus.internal.e;

// Referenced classes of package com.google.android.gms.plus:
//            Account, Moments, People, b, 
//            a

public final class Plus
{

    public static final Api API;
    public static final Account AccountApi = new ki();
    public static final Moments MomentsApi = new kl();
    public static final People PeopleApi = new km();
    public static final Scope SCOPE_PLUS_LOGIN = new Scope("https://www.googleapis.com/auth/plus.login");
    public static final Scope SCOPE_PLUS_PROFILE = new Scope("https://www.googleapis.com/auth/plus.me");
    public static final b abp = new kk();
    public static final a abq = new kj();
    public static final com.google.android.gms.common.api.Api.c yH;
    static final com.google.android.gms.common.api.Api.b yI;

    private Plus()
    {
    }

    public static e a(GoogleApiClient googleapiclient, com.google.android.gms.common.api.Api.c c)
    {
        boolean flag = true;
        boolean flag1;
        e e1;
        if (googleapiclient != null)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        hm.b(flag1, "GoogleApiClient parameter is required.");
        hm.a(googleapiclient.isConnected(), "GoogleApiClient must be connected.");
        e1 = (e)googleapiclient.a(c);
        if (e1 == null)
        {
            flag = false;
        }
        hm.a(flag, "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        return e1;
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
            return a(context, looper, gy1, (PlusOptions)obj, connectioncallbacks, onconnectionfailedlistener);
        }

        public final e a(Context context, Looper looper, gy gy1, PlusOptions plusoptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            if (plusoptions == null)
            {
                plusoptions = new PlusOptions(null);
            }
            e e1 = new e(context, looper, connectioncallbacks, onconnectionfailedlistener, new h(gy1.fj(), gy1.fm(), (String[])plusoptions.abs.toArray(new String[0]), new String[0], context.getPackageName(), context.getPackageName(), null, new PlusCommonExtras()));
            return e1;
        }

        public final int getPriority()
        {
            return 2;
        }

        _cls1()
        {
        }

        private class PlusOptions
            implements com.google.android.gms.common.api.Api.ApiOptions.Optional
        {

            final String abr;
            final Set abs;

            public static Builder builder()
            {
                return new Builder();
            }

            private PlusOptions()
            {
                abr = null;
                abs = new HashSet();
            }

            PlusOptions(_cls1 _pcls1)
            {
                this();
            }

            private PlusOptions(Builder builder1)
            {
                class Builder
                {

                    String abr;
                    final Set abs = new HashSet();

                    public final transient Builder addActivityTypes(String as[])
                    {
                        hm.b(as, "activityTypes may not be null.");
                        for (int i = 0; i < as.length; i++)
                        {
                            abs.add(as[i]);
                        }

                        return this;
                    }

                    public final PlusOptions build()
                    {
                        return new PlusOptions(this, null);
                    }

                    public final Builder setServerClientId(String s)
                    {
                        abr = s;
                        return this;
                    }

                    public Builder()
                    {
                    }
                }

                abr = builder1.abr;
                abs = builder1.abs;
            }

            PlusOptions(Builder builder1, _cls1 _pcls1)
            {
                this(builder1);
            }
        }

    }

}
