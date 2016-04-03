// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus;

import android.net.Uri;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.plus.model.people.Person;
import java.util.Collection;

public class PlusClient
    implements GooglePlayServicesClient
{

    final e abt;

    PlusClient(e e1)
    {
        abt = e1;
    }

    public void clearDefaultAccount()
    {
        abt.clearDefaultAccount();
    }

    public void connect()
    {
        abt.connect();
    }

    public void disconnect()
    {
        abt.disconnect();
    }

    public String getAccountName()
    {
        return abt.getAccountName();
    }

    public Person getCurrentPerson()
    {
        return abt.getCurrentPerson();
    }

    public boolean isConnected()
    {
        return abt.isConnected();
    }

    public boolean isConnecting()
    {
        return abt.isConnecting();
    }

    public boolean isConnectionCallbacksRegistered(com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks connectioncallbacks)
    {
        return abt.isConnectionCallbacksRegistered(connectioncallbacks);
    }

    public boolean isConnectionFailedListenerRegistered(com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        return abt.isConnectionFailedListenerRegistered(onconnectionfailedlistener);
    }

    e jS()
    {
        return abt;
    }

    public void loadMoments(OnMomentsLoadedListener onmomentsloadedlistener)
    {
        abt.k(new _cls1(onmomentsloadedlistener));
    }

    public void loadMoments(OnMomentsLoadedListener onmomentsloadedlistener, int i, String s, Uri uri, String s1, String s2)
    {
        abt.a(new _cls2(onmomentsloadedlistener), i, s, uri, s1, s2);
    }

    public void loadPeople(OnPeopleLoadedListener onpeopleloadedlistener, Collection collection)
    {
        abt.a(new _cls5(onpeopleloadedlistener), collection);
    }

    public transient void loadPeople(OnPeopleLoadedListener onpeopleloadedlistener, String as[])
    {
        abt.d(new _cls6(onpeopleloadedlistener), as);
    }

    public void loadVisiblePeople(OnPeopleLoadedListener onpeopleloadedlistener, int i, String s)
    {
        abt.a(new _cls3(onpeopleloadedlistener), i, s);
    }

    public void loadVisiblePeople(OnPeopleLoadedListener onpeopleloadedlistener, String s)
    {
        abt.r(new _cls4(onpeopleloadedlistener), s);
    }

    public void registerConnectionCallbacks(com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks connectioncallbacks)
    {
        abt.registerConnectionCallbacks(connectioncallbacks);
    }

    public void registerConnectionFailedListener(com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        abt.registerConnectionFailedListener(onconnectionfailedlistener);
    }

    public void removeMoment(String s)
    {
        abt.removeMoment(s);
    }

    public void revokeAccessAndDisconnect(OnAccessRevokedListener onaccessrevokedlistener)
    {
        abt.m(new _cls7(onaccessrevokedlistener));
    }

    public void unregisterConnectionCallbacks(com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks connectioncallbacks)
    {
        abt.unregisterConnectionCallbacks(connectioncallbacks);
    }

    public void unregisterConnectionFailedListener(com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        abt.unregisterConnectionFailedListener(onconnectionfailedlistener);
    }

    public void writeMoment(Moment moment)
    {
        abt.a(null, moment);
    }

    private class _cls1
        implements com.google.android.gms.common.api.a.d
    {

        final OnMomentsLoadedListener abu;
        final PlusClient abv;

        public void a(Moments.LoadMomentsResult loadmomentsresult)
        {
            abu.onMomentsLoaded(loadmomentsresult.getStatus().eR(), loadmomentsresult.getMomentBuffer(), loadmomentsresult.getNextPageToken(), loadmomentsresult.getUpdated());
        }

        public volatile void a(Object obj)
        {
            a((Moments.LoadMomentsResult)obj);
        }

        _cls1(OnMomentsLoadedListener onmomentsloadedlistener)
        {
            abv = PlusClient.this;
            abu = onmomentsloadedlistener;
            super();
        }

        private class OnMomentsLoadedListener
        {

            public abstract void onMomentsLoaded(ConnectionResult connectionresult, MomentBuffer momentbuffer, String s, String s1);
        }

    }


    private class _cls2
        implements com.google.android.gms.common.api.a.d
    {

        final OnMomentsLoadedListener abu;
        final PlusClient abv;

        public void a(Moments.LoadMomentsResult loadmomentsresult)
        {
            abu.onMomentsLoaded(loadmomentsresult.getStatus().eR(), loadmomentsresult.getMomentBuffer(), loadmomentsresult.getNextPageToken(), loadmomentsresult.getUpdated());
        }

        public volatile void a(Object obj)
        {
            a((Moments.LoadMomentsResult)obj);
        }

        _cls2(OnMomentsLoadedListener onmomentsloadedlistener)
        {
            abv = PlusClient.this;
            abu = onmomentsloadedlistener;
            super();
        }
    }


    private class _cls5
        implements com.google.android.gms.common.api.a.d
    {

        final PlusClient abv;
        final OnPeopleLoadedListener abw;

        public void a(People.LoadPeopleResult loadpeopleresult)
        {
            abw.onPeopleLoaded(loadpeopleresult.getStatus().eR(), loadpeopleresult.getPersonBuffer(), loadpeopleresult.getNextPageToken());
        }

        public volatile void a(Object obj)
        {
            a((People.LoadPeopleResult)obj);
        }

        _cls5(OnPeopleLoadedListener onpeopleloadedlistener)
        {
            abv = PlusClient.this;
            abw = onpeopleloadedlistener;
            super();
        }

        private class OnPeopleLoadedListener
        {

            public abstract void onPeopleLoaded(ConnectionResult connectionresult, PersonBuffer personbuffer, String s);
        }

    }


    private class _cls6
        implements com.google.android.gms.common.api.a.d
    {

        final PlusClient abv;
        final OnPeopleLoadedListener abw;

        public void a(People.LoadPeopleResult loadpeopleresult)
        {
            abw.onPeopleLoaded(loadpeopleresult.getStatus().eR(), loadpeopleresult.getPersonBuffer(), loadpeopleresult.getNextPageToken());
        }

        public volatile void a(Object obj)
        {
            a((People.LoadPeopleResult)obj);
        }

        _cls6(OnPeopleLoadedListener onpeopleloadedlistener)
        {
            abv = PlusClient.this;
            abw = onpeopleloadedlistener;
            super();
        }
    }


    private class _cls3
        implements com.google.android.gms.common.api.a.d
    {

        final PlusClient abv;
        final OnPeopleLoadedListener abw;

        public void a(People.LoadPeopleResult loadpeopleresult)
        {
            abw.onPeopleLoaded(loadpeopleresult.getStatus().eR(), loadpeopleresult.getPersonBuffer(), loadpeopleresult.getNextPageToken());
        }

        public volatile void a(Object obj)
        {
            a((People.LoadPeopleResult)obj);
        }

        _cls3(OnPeopleLoadedListener onpeopleloadedlistener)
        {
            abv = PlusClient.this;
            abw = onpeopleloadedlistener;
            super();
        }
    }


    private class _cls4
        implements com.google.android.gms.common.api.a.d
    {

        final PlusClient abv;
        final OnPeopleLoadedListener abw;

        public void a(People.LoadPeopleResult loadpeopleresult)
        {
            abw.onPeopleLoaded(loadpeopleresult.getStatus().eR(), loadpeopleresult.getPersonBuffer(), loadpeopleresult.getNextPageToken());
        }

        public volatile void a(Object obj)
        {
            a((People.LoadPeopleResult)obj);
        }

        _cls4(OnPeopleLoadedListener onpeopleloadedlistener)
        {
            abv = PlusClient.this;
            abw = onpeopleloadedlistener;
            super();
        }
    }


    private class _cls7
        implements com.google.android.gms.common.api.a.d
    {

        final PlusClient abv;
        final OnAccessRevokedListener abx;

        public void a(Object obj)
        {
            al((Status)obj);
        }

        public void al(Status status)
        {
            abx.onAccessRevoked(status.getStatus().eR());
        }

        _cls7(OnAccessRevokedListener onaccessrevokedlistener)
        {
            abv = PlusClient.this;
            abx = onaccessrevokedlistener;
            super();
        }

        private class OnAccessRevokedListener
        {

            public abstract void onAccessRevoked(ConnectionResult connectionresult);
        }

    }

}
