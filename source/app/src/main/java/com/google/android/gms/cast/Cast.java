// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;

public final class Cast
{

    public static final Api API;
    public static final CastApi CastApi = new CastApi.a();
    public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
    public static final int MAX_MESSAGE_LENGTH = 0x10000;
    public static final int MAX_NAMESPACE_LENGTH = 128;
    static final com.google.android.gms.common.api.Api.c yH;
    private static final com.google.android.gms.common.api.Api.b yI;

    private Cast()
    {
    }

    static 
    {
        yH = new com.google.android.gms.common.api.Api.c();
        yI = new _cls1();
        API = new Api(yI, yH, new Scope[0]);
        class CastApi.a
            implements CastApi
        {

            public final ApplicationMetadata getApplicationMetadata(GoogleApiClient googleapiclient)
            {
                return ((gh)googleapiclient.a(Cast.yH)).getApplicationMetadata();
            }

            public final String getApplicationStatus(GoogleApiClient googleapiclient)
            {
                return ((gh)googleapiclient.a(Cast.yH)).getApplicationStatus();
            }

            public final double getVolume(GoogleApiClient googleapiclient)
            {
                return ((gh)googleapiclient.a(Cast.yH)).eh();
            }

            public final boolean isMute(GoogleApiClient googleapiclient)
            {
                return ((gh)googleapiclient.a(Cast.yH)).isMute();
            }

            public final PendingResult joinApplication(GoogleApiClient googleapiclient)
            {
                class _cls6 extends c
                {
                        private class c extends a
                        {
                            private class a extends com.google.android.gms.common.api.a.b
                            {

                                public void N(int i)
                                {
                                    b(c(new Status(i)));
                                }

                                public void c(int i, String s)
                                {
                                    b(c(new Status(i, s, null)));
                                }

                                public a()
                                {
                                    super(Cast.yH);
                                }
                            }


                            public Result c(Status status)
                            {
                                return j(status);
                            }

                            public ApplicationConnectionResult j(Status status)
                            {
                                class _cls1
                                    implements ApplicationConnectionResult
                                {

                                    final c Ag;
                                    final Status yJ;

                                    public ApplicationMetadata getApplicationMetadata()
                                    {
                                        return null;
                                    }

                                    public String getApplicationStatus()
                                    {
                                        return null;
                                    }

                                    public String getSessionId()
                                    {
                                        return null;
                                    }

                                    public Status getStatus()
                                    {
                                        return yJ;
                                    }

                                    public boolean getWasLaunched()
                                    {
                                        return false;
                                    }

                                    _cls1(Status status)
                                    {
                                        Ag = c.this;
                                        yJ = status;
                                        super();
                                    }
                                }

                                return new _cls1(status);
                            }

                            private c()
                            {
                            }

                            c(_cls1 _pcls1)
                            {
                                this();
                            }
                        }


                    final CastApi.a zW;

                    protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    {
                        a((gh)a1);
                    }

                    protected void a(gh gh1)
                    {
                        try
                        {
                            gh1.b(null, null, this);
                            return;
                        }
                        catch (IllegalStateException illegalstateexception)
                        {
                            N(2001);
                        }
                    }

                        _cls6()
                        {
                            zW = CastApi.a.this;
                            super(null);
                        }
                }

                return googleapiclient.b(new _cls6());
            }

            public final PendingResult joinApplication(GoogleApiClient googleapiclient, String s)
            {
                class _cls5 extends c
                {

                    final CastApi.a zW;
                    final String zX;

                    protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    {
                        a((gh)a1);
                    }

                    protected void a(gh gh1)
                    {
                        try
                        {
                            gh1.b(zX, null, this);
                            return;
                        }
                        catch (IllegalStateException illegalstateexception)
                        {
                            N(2001);
                        }
                    }

                        _cls5(String s)
                        {
                            zW = CastApi.a.this;
                            zX = s;
                            super(null);
                        }
                }

                return googleapiclient.b(new _cls5(s));
            }

            public final PendingResult joinApplication(GoogleApiClient googleapiclient, String s, String s1)
            {
                class _cls4 extends c
                {

                    final CastApi.a zW;
                    final String zX;
                    final String zZ;

                    protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    {
                        a((gh)a1);
                    }

                    protected void a(gh gh1)
                    {
                        try
                        {
                            gh1.b(zX, zZ, this);
                            return;
                        }
                        catch (IllegalStateException illegalstateexception)
                        {
                            N(2001);
                        }
                    }

                        _cls4(String s, String s1)
                        {
                            zW = CastApi.a.this;
                            zX = s;
                            zZ = s1;
                            super(null);
                        }
                }

                return googleapiclient.b(new _cls4(s, s1));
            }

            public final PendingResult launchApplication(GoogleApiClient googleapiclient, String s)
            {
                class _cls2 extends c
                {

                    final CastApi.a zW;
                    final String zX;

                    protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    {
                        a((gh)a1);
                    }

                    protected void a(gh gh1)
                    {
                        try
                        {
                            gh1.a(zX, false, this);
                            return;
                        }
                        catch (IllegalStateException illegalstateexception)
                        {
                            N(2001);
                        }
                    }

                        _cls2(String s)
                        {
                            zW = CastApi.a.this;
                            zX = s;
                            super(null);
                        }
                }

                return googleapiclient.b(new _cls2(s));
            }

            public final PendingResult launchApplication(GoogleApiClient googleapiclient, String s, LaunchOptions launchoptions)
            {
                class _cls3 extends c
                {

                    final CastApi.a zW;
                    final String zX;
                    final LaunchOptions zY;

                    protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    {
                        a((gh)a1);
                    }

                    protected void a(gh gh1)
                    {
                        try
                        {
                            gh1.a(zX, zY, this);
                            return;
                        }
                        catch (IllegalStateException illegalstateexception)
                        {
                            N(2001);
                        }
                    }

                        _cls3(String s, LaunchOptions launchoptions)
                        {
                            zW = CastApi.a.this;
                            zX = s;
                            zY = launchoptions;
                            super(null);
                        }
                }

                return googleapiclient.b(new _cls3(s, launchoptions));
            }

            public final PendingResult launchApplication(GoogleApiClient googleapiclient, String s, boolean flag)
            {
                return launchApplication(googleapiclient, s, (new LaunchOptions.Builder()).setRelaunchIfRunning(flag).build());
            }

            public final PendingResult leaveApplication(GoogleApiClient googleapiclient)
            {
                class _cls7 extends b
                {
                        private class b extends a
                        {

                            public Result c(Status status)
                            {
                                return d(status);
                            }

                            public Status d(Status status)
                            {
                                return status;
                            }

                            private b()
                            {
                            }

                            b(_cls1 _pcls1)
                            {
                                this();
                            }
                        }


                    final CastApi.a zW;

                    protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    {
                        a((gh)a1);
                    }

                    protected void a(gh gh1)
                    {
                        try
                        {
                            gh1.d(this);
                            return;
                        }
                        catch (IllegalStateException illegalstateexception)
                        {
                            N(2001);
                        }
                    }

                        _cls7()
                        {
                            zW = CastApi.a.this;
                            super(null);
                        }
                }

                return googleapiclient.b(new _cls7());
            }

            public final void removeMessageReceivedCallbacks(GoogleApiClient googleapiclient, String s)
            {
                try
                {
                    ((gh)googleapiclient.a(Cast.yH)).aj(s);
                    return;
                }
                catch (RemoteException remoteexception)
                {
                    throw new IOException("service error");
                }
            }

            public final void requestStatus(GoogleApiClient googleapiclient)
            {
                try
                {
                    ((gh)googleapiclient.a(Cast.yH)).eg();
                    return;
                }
                catch (RemoteException remoteexception)
                {
                    throw new IOException("service error");
                }
            }

            public final PendingResult sendMessage(GoogleApiClient googleapiclient, String s, String s1)
            {
                class _cls1 extends b
                {

                    final String zU;
                    final String zV;
                    final CastApi.a zW;

                    protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    {
                        a((gh)a1);
                    }

                    protected void a(gh gh1)
                    {
                        try
                        {
                            gh1.a(zU, zV, this);
                            return;
                        }
                        catch (IllegalArgumentException illegalargumentexception)
                        {
                            N(2001);
                            return;
                        }
                        catch (IllegalStateException illegalstateexception)
                        {
                            N(2001);
                        }
                    }

                        _cls1(String s, String s1)
                        {
                            zW = CastApi.a.this;
                            zU = s;
                            zV = s1;
                            super(null);
                        }
                }

                return googleapiclient.b(new _cls1(s, s1));
            }

            public final void setMessageReceivedCallbacks(GoogleApiClient googleapiclient, String s, MessageReceivedCallback messagereceivedcallback)
            {
                try
                {
                    ((gh)googleapiclient.a(Cast.yH)).a(s, messagereceivedcallback);
                    return;
                }
                catch (RemoteException remoteexception)
                {
                    throw new IOException("service error");
                }
            }

            public final void setMute(GoogleApiClient googleapiclient, boolean flag)
            {
                try
                {
                    ((gh)googleapiclient.a(Cast.yH)).y(flag);
                    return;
                }
                catch (RemoteException remoteexception)
                {
                    throw new IOException("service error");
                }
            }

            public final void setVolume(GoogleApiClient googleapiclient, double d)
            {
                try
                {
                    ((gh)googleapiclient.a(Cast.yH)).a(d);
                    return;
                }
                catch (RemoteException remoteexception)
                {
                    throw new IOException("service error");
                }
            }

            public final PendingResult stopApplication(GoogleApiClient googleapiclient)
            {
                class _cls8 extends b
                {

                    final CastApi.a zW;

                    protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    {
                        a((gh)a1);
                    }

                    protected void a(gh gh1)
                    {
                        try
                        {
                            gh1.a("", this);
                            return;
                        }
                        catch (IllegalStateException illegalstateexception)
                        {
                            N(2001);
                        }
                    }

                        _cls8()
                        {
                            zW = CastApi.a.this;
                            super(null);
                        }
                }

                return googleapiclient.b(new _cls8());
            }

            public final PendingResult stopApplication(GoogleApiClient googleapiclient, String s)
            {
                class _cls9 extends b
                {

                    final CastApi.a zW;
                    final String zZ;

                    protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                    {
                        a((gh)a1);
                    }

                    protected void a(gh gh1)
                    {
                        if (TextUtils.isEmpty(zZ))
                        {
                            c(2001, "IllegalArgument: sessionId cannot be null or empty");
                            return;
                        }
                        try
                        {
                            gh1.a(zZ, this);
                            return;
                        }
                        catch (IllegalStateException illegalstateexception)
                        {
                            N(2001);
                        }
                    }

                        _cls9(String s)
                        {
                            zW = CastApi.a.this;
                            zZ = s;
                            super(null);
                        }
                }

                return googleapiclient.b(new _cls9(s));
            }

            public CastApi.a()
            {
            }
        }

    }

    private class _cls1
        implements com.google.android.gms.common.api.Api.b
    {

        public final volatile com.google.android.gms.common.api.Api.a a(Context context, Looper looper, gy gy, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            return a(context, looper, gy, (CastOptions)obj, connectioncallbacks, onconnectionfailedlistener);
        }

        public final gh a(Context context, Looper looper, gy gy, CastOptions castoptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            hm.b(castoptions, "Setting the API options is required.");
            return new gh(context, looper, castoptions.Aa, CastOptions.a(castoptions), castoptions.Ab, connectioncallbacks, onconnectionfailedlistener);
        }

        public final int getPriority()
        {
            return 0x7fffffff;
        }

        _cls1()
        {
        }

        private class CastOptions
            implements com.google.android.gms.common.api.Api.ApiOptions.HasOptions
        {

            final CastDevice Aa;
            final Listener Ab;
            private final int Ac;

            static int a(CastOptions castoptions)
            {
                return castoptions.Ac;
            }

            public static Builder builder(CastDevice castdevice, Listener listener)
            {
                return new Builder(castdevice, listener, null);
            }

            private CastOptions(Builder builder1)
            {
                class Builder
                {

                    CastDevice Ad;
                    Listener Ae;
                    private int Af;

                    static int a(Builder builder2)
                    {
                        return builder2.Af;
                    }

                    public final CastOptions build()
                    {
                        return new CastOptions(this, null);
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

                    private Builder(CastDevice castdevice, Listener listener)
                    {
                        hm.b(castdevice, "CastDevice parameter cannot be null");
                        hm.b(listener, "CastListener parameter cannot be null");
                        Ad = castdevice;
                        Ae = listener;
                        Af = 0;
                    }

                    Builder(CastDevice castdevice, Listener listener, _cls1 _pcls1)
                    {
                        this(castdevice, listener);
                    }
                }

                Aa = builder1.Ad;
                Ab = builder1.Ae;
                Ac = Builder.a(builder1);
            }

            CastOptions(Builder builder1, _cls1 _pcls1)
            {
                this(builder1);
            }
        }

    }

}
