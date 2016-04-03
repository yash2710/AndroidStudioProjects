// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.internal.gh;
import java.io.IOException;

// Referenced classes of package com.google.android.gms.cast:
//            Cast, ApplicationMetadata, LaunchOptions

public final class sult
    implements sult
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
        class _cls6 extends Cast.c
        {

            final Cast.CastApi.a zW;

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
                zW = Cast.CastApi.a.this;
                super(null);
            }
        }

        return googleapiclient.b(new _cls6());
    }

    public final PendingResult joinApplication(GoogleApiClient googleapiclient, String s)
    {
        class _cls5 extends Cast.c
        {

            final Cast.CastApi.a zW;
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
                zW = Cast.CastApi.a.this;
                zX = s;
                super(null);
            }
        }

        return googleapiclient.b(new _cls5(s));
    }

    public final PendingResult joinApplication(GoogleApiClient googleapiclient, String s, String s1)
    {
        class _cls4 extends Cast.c
        {

            final Cast.CastApi.a zW;
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
                zW = Cast.CastApi.a.this;
                zX = s;
                zZ = s1;
                super(null);
            }
        }

        return googleapiclient.b(new _cls4(s, s1));
    }

    public final PendingResult launchApplication(GoogleApiClient googleapiclient, String s)
    {
        class _cls2 extends Cast.c
        {

            final Cast.CastApi.a zW;
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
                zW = Cast.CastApi.a.this;
                zX = s;
                super(null);
            }
        }

        return googleapiclient.b(new _cls2(s));
    }

    public final PendingResult launchApplication(GoogleApiClient googleapiclient, String s, LaunchOptions launchoptions)
    {
        class _cls3 extends Cast.c
        {

            final Cast.CastApi.a zW;
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
                zW = Cast.CastApi.a.this;
                zX = s;
                zY = launchoptions;
                super(null);
            }
        }

        return googleapiclient.b(new _cls3(s, launchoptions));
    }

    public final PendingResult launchApplication(GoogleApiClient googleapiclient, String s, boolean flag)
    {
        return launchApplication(googleapiclient, s, (new uilder()).setRelaunchIfRunning(flag).build());
    }

    public final PendingResult leaveApplication(GoogleApiClient googleapiclient)
    {
        class _cls7 extends Cast.b
        {

            final Cast.CastApi.a zW;

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
                zW = Cast.CastApi.a.this;
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
        class _cls1 extends Cast.b
        {

            final String zU;
            final String zV;
            final Cast.CastApi.a zW;

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
                zW = Cast.CastApi.a.this;
                zU = s;
                zV = s1;
                super(null);
            }
        }

        return googleapiclient.b(new _cls1(s, s1));
    }

    public final void setMessageReceivedCallbacks(GoogleApiClient googleapiclient, String s, eivedCallback eivedcallback)
    {
        try
        {
            ((gh)googleapiclient.a(Cast.yH)).a(s, eivedcallback);
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
        class _cls8 extends Cast.b
        {

            final Cast.CastApi.a zW;

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
                zW = Cast.CastApi.a.this;
                super(null);
            }
        }

        return googleapiclient.b(new _cls8());
    }

    public final PendingResult stopApplication(GoogleApiClient googleapiclient, String s)
    {
        class _cls9 extends Cast.b
        {

            final Cast.CastApi.a zW;
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
                zW = Cast.CastApi.a.this;
                zZ = s;
                super(null);
            }
        }

        return googleapiclient.b(new _cls9(s));
    }

    public sult()
    {
    }
}
