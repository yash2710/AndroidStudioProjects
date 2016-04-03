// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.panorama;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.kf;

// Referenced classes of package com.google.android.gms.panorama:
//            PanoramaApi

public final class Panorama
{

    public static final Api API;
    public static final PanoramaApi PanoramaApi = new kf();
    public static final com.google.android.gms.common.api.Api.c yH;
    static final com.google.android.gms.common.api.Api.b yI;

    private Panorama()
    {
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

        public final com.google.android.gms.common.api.Api.a a(Context context, Looper looper, gy gy, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            return d(context, looper, gy, (com.google.android.gms.common.api.Api.ApiOptions.NoOptions)obj, connectioncallbacks, onconnectionfailedlistener);
        }

        public final kg d(Context context, Looper looper, gy gy, com.google.android.gms.common.api.Api.ApiOptions.NoOptions nooptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            return new kg(context, looper, connectioncallbacks, onconnectionfailedlistener);
        }

        public final int getPriority()
        {
            return 0x7fffffff;
        }

        _cls1()
        {
        }
    }

}
