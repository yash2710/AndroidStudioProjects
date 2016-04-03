// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.identity.intents;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

// Referenced classes of package com.google.android.gms.identity.intents:
//            UserAddressRequest

public final class Address
{

    public static final Api API;
    static final com.google.android.gms.common.api.Api.c yH;
    private static final com.google.android.gms.common.api.Api.b yI;

    public Address()
    {
    }

    public static void requestUserAddress(GoogleApiClient googleapiclient, UserAddressRequest useraddressrequest, int i)
    {
        googleapiclient.a(new _cls2(useraddressrequest, i));
    }

    static 
    {
        yH = new com.google.android.gms.common.api.Api.c();
        yI = new _cls1();
        API = new Api(yI, yH, new Scope[0]);
    }

    private class _cls2 extends a
    {
        private class a extends com.google.android.gms.common.api.a.b
        {

            public Result c(Status status)
            {
                return d(status);
            }

            public Status d(Status status)
            {
                return status;
            }

            public a()
            {
                super(Address.yH);
            }
        }


        final UserAddressRequest UC;
        final int UD;

        protected final volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((iw)a1);
        }

        protected final void a(iw iw1)
        {
            iw1.a(UC, UD);
            b(Status.En);
        }

        _cls2(UserAddressRequest useraddressrequest, int i)
        {
            UC = useraddressrequest;
            UD = i;
            super();
        }
    }


    private class _cls1
        implements com.google.android.gms.common.api.Api.b
    {

        public final volatile com.google.android.gms.common.api.Api.a a(Context context, Looper looper, gy gy1, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            return a(context, looper, gy1, (AddressOptions)obj, connectioncallbacks, onconnectionfailedlistener);
        }

        public final iw a(Context context, Looper looper, gy gy1, AddressOptions addressoptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            hm.b(context instanceof Activity, "An Activity must be used for Address APIs");
            if (addressoptions == null)
            {
                addressoptions = new AddressOptions();
            }
            return new iw((Activity)context, looper, connectioncallbacks, onconnectionfailedlistener, gy1.getAccountName(), addressoptions.theme);
        }

        public final int getPriority()
        {
            return 0x7fffffff;
        }

        _cls1()
        {
        }

        private class AddressOptions
            implements com.google.android.gms.common.api.Api.ApiOptions.HasOptions
        {

            public final int theme;

            public AddressOptions()
            {
                theme = 0;
            }

            public AddressOptions(int i)
            {
                theme = i;
            }
        }

    }

}
