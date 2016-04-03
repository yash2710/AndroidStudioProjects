// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.internal.go;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.cast:
//            MediaInfo, MediaStatus, CastDevice, TextTrackStyle

public class RemoteMediaPlayer
    implements Cast.MessageReceivedCallback
{

    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_CANCELED = 2;
    public static final int STATUS_FAILED = 1;
    public static final int STATUS_REPLACED = 4;
    public static final int STATUS_SUCCEEDED = 0;
    public static final int STATUS_TIMED_OUT = 3;
    private final go AW = new _cls1();
    private final a AX = new a();
    private OnMetadataUpdatedListener AY;
    private OnStatusUpdatedListener AZ;
    private final Object ls = new Object();

    public RemoteMediaPlayer()
    {
        AW.a(AX);
    }

    static void a(RemoteMediaPlayer remotemediaplayer)
    {
        remotemediaplayer.onStatusUpdated();
    }

    static void b(RemoteMediaPlayer remotemediaplayer)
    {
        remotemediaplayer.onMetadataUpdated();
    }

    static Object c(RemoteMediaPlayer remotemediaplayer)
    {
        return remotemediaplayer.ls;
    }

    static a d(RemoteMediaPlayer remotemediaplayer)
    {
        return remotemediaplayer.AX;
    }

    static go e(RemoteMediaPlayer remotemediaplayer)
    {
        return remotemediaplayer.AW;
    }

    private void onMetadataUpdated()
    {
        if (AY != null)
        {
            AY.onMetadataUpdated();
        }
    }

    private void onStatusUpdated()
    {
        if (AZ != null)
        {
            AZ.onStatusUpdated();
        }
    }

    public long getApproximateStreamPosition()
    {
        long l;
        synchronized (ls)
        {
            l = AW.getApproximateStreamPosition();
        }
        return l;
    }

    public MediaInfo getMediaInfo()
    {
        MediaInfo mediainfo;
        synchronized (ls)
        {
            mediainfo = AW.getMediaInfo();
        }
        return mediainfo;
    }

    public MediaStatus getMediaStatus()
    {
        MediaStatus mediastatus;
        synchronized (ls)
        {
            mediastatus = AW.getMediaStatus();
        }
        return mediastatus;
    }

    public String getNamespace()
    {
        return AW.getNamespace();
    }

    public long getStreamDuration()
    {
        long l;
        synchronized (ls)
        {
            l = AW.getStreamDuration();
        }
        return l;
    }

    public PendingResult load(GoogleApiClient googleapiclient, MediaInfo mediainfo)
    {
        return load(googleapiclient, mediainfo, true, 0L, null);
    }

    public PendingResult load(GoogleApiClient googleapiclient, MediaInfo mediainfo, boolean flag)
    {
        return load(googleapiclient, mediainfo, flag, 0L, null);
    }

    public PendingResult load(GoogleApiClient googleapiclient, MediaInfo mediainfo, boolean flag, long l)
    {
        return load(googleapiclient, mediainfo, flag, l, null);
    }

    public PendingResult load(GoogleApiClient googleapiclient, MediaInfo mediainfo, boolean flag, long l, JSONObject jsonobject)
    {
        return googleapiclient.b(new _cls4(googleapiclient, mediainfo, flag, l, jsonobject));
    }

    public void onMessageReceived(CastDevice castdevice, String s, String s1)
    {
        AW.ai(s1);
    }

    public PendingResult pause(GoogleApiClient googleapiclient)
    {
        return pause(googleapiclient, null);
    }

    public PendingResult pause(GoogleApiClient googleapiclient, JSONObject jsonobject)
    {
        return googleapiclient.b(new _cls5(googleapiclient, jsonobject));
    }

    public PendingResult play(GoogleApiClient googleapiclient)
    {
        return play(googleapiclient, null);
    }

    public PendingResult play(GoogleApiClient googleapiclient, JSONObject jsonobject)
    {
        return googleapiclient.b(new _cls7(googleapiclient, jsonobject));
    }

    public PendingResult requestStatus(GoogleApiClient googleapiclient)
    {
        return googleapiclient.b(new _cls11(googleapiclient));
    }

    public PendingResult seek(GoogleApiClient googleapiclient, long l)
    {
        return seek(googleapiclient, l, 0, null);
    }

    public PendingResult seek(GoogleApiClient googleapiclient, long l, int i)
    {
        return seek(googleapiclient, l, i, null);
    }

    public PendingResult seek(GoogleApiClient googleapiclient, long l, int i, JSONObject jsonobject)
    {
        return googleapiclient.b(new _cls8(googleapiclient, l, i, jsonobject));
    }

    public PendingResult setActiveMediaTracks(GoogleApiClient googleapiclient, long al[])
    {
        return googleapiclient.b(new _cls2(googleapiclient, al));
    }

    public void setOnMetadataUpdatedListener(OnMetadataUpdatedListener onmetadataupdatedlistener)
    {
        AY = onmetadataupdatedlistener;
    }

    public void setOnStatusUpdatedListener(OnStatusUpdatedListener onstatusupdatedlistener)
    {
        AZ = onstatusupdatedlistener;
    }

    public PendingResult setStreamMute(GoogleApiClient googleapiclient, boolean flag)
    {
        return setStreamMute(googleapiclient, flag, null);
    }

    public PendingResult setStreamMute(GoogleApiClient googleapiclient, boolean flag, JSONObject jsonobject)
    {
        return googleapiclient.b(new _cls10(googleapiclient, flag, jsonobject));
    }

    public PendingResult setStreamVolume(GoogleApiClient googleapiclient, double d1)
    {
        return setStreamVolume(googleapiclient, d1, null);
    }

    public PendingResult setStreamVolume(GoogleApiClient googleapiclient, double d1, JSONObject jsonobject)
    {
        if (Double.isInfinite(d1) || Double.isNaN(d1))
        {
            throw new IllegalArgumentException((new StringBuilder("Volume cannot be ")).append(d1).toString());
        } else
        {
            return googleapiclient.b(new _cls9(googleapiclient, d1, jsonobject));
        }
    }

    public PendingResult setTextTrackStyle(GoogleApiClient googleapiclient, TextTrackStyle texttrackstyle)
    {
        return googleapiclient.b(new _cls3(googleapiclient, texttrackstyle));
    }

    public PendingResult stop(GoogleApiClient googleapiclient)
    {
        return stop(googleapiclient, null);
    }

    public PendingResult stop(GoogleApiClient googleapiclient, JSONObject jsonobject)
    {
        return googleapiclient.b(new _cls6(googleapiclient, jsonobject));
    }

    private class a
        implements gp
    {

        final RemoteMediaPlayer Ba;
        private GoogleApiClient Bm;
        private long Bn;

        public void a(String s, String s1, long l, String s2)
        {
            if (Bm == null)
            {
                throw new IOException("No GoogleApiClient available");
            } else
            {
                class a
                    implements ResultCallback
                {

                    private final long Bo;
                    final a Bp;

                    public final void k(Status status)
                    {
                        if (!status.isSuccess())
                        {
                            RemoteMediaPlayer.e(Bp.Ba).a(Bo, status.getStatusCode());
                        }
                    }

                    public final void onResult(Result result)
                    {
                        k((Status)result);
                    }

                a(long l)
                {
                    Bp = a.this;
                    super();
                    Bo = l;
                }
                }

                Cast.CastApi.sendMessage(Bm, s, s1).setResultCallback(new a(l));
                return;
            }
        }

        public void b(GoogleApiClient googleapiclient)
        {
            Bm = googleapiclient;
        }

        public long eb()
        {
            long l = 1L + Bn;
            Bn = l;
            return l;
        }

        public a()
        {
            Ba = RemoteMediaPlayer.this;
            super();
            Bn = 0L;
        }
    }


    private class _cls1 extends go
    {

        final RemoteMediaPlayer Ba;

        protected void onMetadataUpdated()
        {
            RemoteMediaPlayer.b(Ba);
        }

        protected void onStatusUpdated()
        {
            RemoteMediaPlayer.a(Ba);
        }

        _cls1()
        {
            Ba = RemoteMediaPlayer.this;
            super();
        }
    }


    private class OnMetadataUpdatedListener
    {

        public abstract void onMetadataUpdated();
    }


    private class OnStatusUpdatedListener
    {

        public abstract void onStatusUpdated();
    }


    private class _cls4 extends b
    {
        private class b extends Cast.a
        {

            gq Bq;

            public Result c(Status status)
            {
                return l(status);
            }

            public MediaChannelResult l(Status status)
            {
                class _cls2
                    implements MediaChannelResult
                {

                    final b Br;
                    final Status yJ;

                    public JSONObject getCustomData()
                    {
                        return null;
                    }

                    public Status getStatus()
                    {
                        return yJ;
                    }

                    _cls2(Status status)
                    {
                        Br = b.this;
                        yJ = status;
                        super();
                    }
                }

                return new _cls2(status);
            }

            b()
            {
                class _cls1
                    implements gq
                {

                    final b Br;

                    public void a(long l1, int i, JSONObject jsonobject)
                    {
                        Br.b(new c(new Status(i), jsonobject));
                    }

                    public void n(long l1)
                    {
                        Br.b(Br.l(new Status(4)));
                    }

                    _cls1()
                    {
                        Br = b.this;
                        super();
                    }

                    private class c
                        implements MediaChannelResult
                    {

                        private final JSONObject AA;
                        private final Status yz;

                        public final JSONObject getCustomData()
                        {
                            return AA;
                        }

                        public final Status getStatus()
                        {
                            return yz;
                        }

                        c(Status status, JSONObject jsonobject)
                        {
                            yz = status;
                            AA = jsonobject;
                        }
                    }

                }

                Bq = new _cls1();
            }
        }


        final RemoteMediaPlayer Ba;
        final GoogleApiClient Bb;
        final MediaInfo Be;
        final boolean Bf;
        final long Bg;
        final JSONObject Bh;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((gh)a1);
        }

        protected void a(gh gh1)
        {
            Object obj = RemoteMediaPlayer.c(Ba);
            obj;
            JVM INSTR monitorenter ;
            RemoteMediaPlayer.d(Ba).b(Bb);
            RemoteMediaPlayer.e(Ba).a(Bq, Be, Bf, Bg, Bh);
            RemoteMediaPlayer.d(Ba).b(null);
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            IOException ioexception;
            ioexception;
            b(l(new Status(1)));
            RemoteMediaPlayer.d(Ba).b(null);
              goto _L1
            Exception exception;
            exception;
            throw exception;
            Exception exception1;
            exception1;
            RemoteMediaPlayer.d(Ba).b(null);
            throw exception1;
        }

        _cls4(GoogleApiClient googleapiclient, MediaInfo mediainfo, boolean flag, long l, JSONObject jsonobject)
        {
            Ba = RemoteMediaPlayer.this;
            Bb = googleapiclient;
            Be = mediainfo;
            Bf = flag;
            Bg = l;
            Bh = jsonobject;
            super();
        }
    }


    private class _cls5 extends b
    {

        final RemoteMediaPlayer Ba;
        final GoogleApiClient Bb;
        final JSONObject Bh;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((gh)a1);
        }

        protected void a(gh gh1)
        {
            Object obj = RemoteMediaPlayer.c(Ba);
            obj;
            JVM INSTR monitorenter ;
            RemoteMediaPlayer.d(Ba).b(Bb);
            RemoteMediaPlayer.e(Ba).a(Bq, Bh);
            RemoteMediaPlayer.d(Ba).b(null);
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            IOException ioexception;
            ioexception;
            b(l(new Status(1)));
            RemoteMediaPlayer.d(Ba).b(null);
              goto _L1
            Exception exception;
            exception;
            throw exception;
            Exception exception1;
            exception1;
            RemoteMediaPlayer.d(Ba).b(null);
            throw exception1;
        }

        _cls5(GoogleApiClient googleapiclient, JSONObject jsonobject)
        {
            Ba = RemoteMediaPlayer.this;
            Bb = googleapiclient;
            Bh = jsonobject;
            super();
        }
    }


    private class _cls7 extends b
    {

        final RemoteMediaPlayer Ba;
        final GoogleApiClient Bb;
        final JSONObject Bh;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((gh)a1);
        }

        protected void a(gh gh1)
        {
            Object obj = RemoteMediaPlayer.c(Ba);
            obj;
            JVM INSTR monitorenter ;
            RemoteMediaPlayer.d(Ba).b(Bb);
            RemoteMediaPlayer.e(Ba).c(Bq, Bh);
            RemoteMediaPlayer.d(Ba).b(null);
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            IOException ioexception;
            ioexception;
            b(l(new Status(1)));
            RemoteMediaPlayer.d(Ba).b(null);
              goto _L1
            Exception exception;
            exception;
            throw exception;
            Exception exception1;
            exception1;
            RemoteMediaPlayer.d(Ba).b(null);
            throw exception1;
        }

        _cls7(GoogleApiClient googleapiclient, JSONObject jsonobject)
        {
            Ba = RemoteMediaPlayer.this;
            Bb = googleapiclient;
            Bh = jsonobject;
            super();
        }
    }


    private class _cls11 extends b
    {

        final RemoteMediaPlayer Ba;
        final GoogleApiClient Bb;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((gh)a1);
        }

        protected void a(gh gh1)
        {
            Object obj = RemoteMediaPlayer.c(Ba);
            obj;
            JVM INSTR monitorenter ;
            RemoteMediaPlayer.d(Ba).b(Bb);
            RemoteMediaPlayer.e(Ba).a(Bq);
            RemoteMediaPlayer.d(Ba).b(null);
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            IOException ioexception;
            ioexception;
            b(l(new Status(1)));
            RemoteMediaPlayer.d(Ba).b(null);
              goto _L1
            Exception exception;
            exception;
            throw exception;
            Exception exception1;
            exception1;
            RemoteMediaPlayer.d(Ba).b(null);
            throw exception1;
        }

        _cls11(GoogleApiClient googleapiclient)
        {
            Ba = RemoteMediaPlayer.this;
            Bb = googleapiclient;
            super();
        }
    }


    private class _cls8 extends b
    {

        final RemoteMediaPlayer Ba;
        final GoogleApiClient Bb;
        final JSONObject Bh;
        final long Bi;
        final int Bj;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((gh)a1);
        }

        protected void a(gh gh1)
        {
            Object obj = RemoteMediaPlayer.c(Ba);
            obj;
            JVM INSTR monitorenter ;
            RemoteMediaPlayer.d(Ba).b(Bb);
            RemoteMediaPlayer.e(Ba).a(Bq, Bi, Bj, Bh);
            RemoteMediaPlayer.d(Ba).b(null);
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            IOException ioexception;
            ioexception;
            b(l(new Status(1)));
            RemoteMediaPlayer.d(Ba).b(null);
              goto _L1
            Exception exception;
            exception;
            throw exception;
            Exception exception1;
            exception1;
            RemoteMediaPlayer.d(Ba).b(null);
            throw exception1;
        }

        _cls8(GoogleApiClient googleapiclient, long l, int i, JSONObject jsonobject)
        {
            Ba = RemoteMediaPlayer.this;
            Bb = googleapiclient;
            Bi = l;
            Bj = i;
            Bh = jsonobject;
            super();
        }
    }


    private class _cls2 extends b
    {

        final RemoteMediaPlayer Ba;
        final GoogleApiClient Bb;
        final long Bc[];

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((gh)a1);
        }

        protected void a(gh gh1)
        {
            Object obj = RemoteMediaPlayer.c(Ba);
            obj;
            JVM INSTR monitorenter ;
            RemoteMediaPlayer.d(Ba).b(Bb);
            RemoteMediaPlayer.e(Ba).a(Bq, Bc);
            RemoteMediaPlayer.d(Ba).b(null);
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            IOException ioexception;
            ioexception;
            b(l(new Status(1)));
            RemoteMediaPlayer.d(Ba).b(null);
              goto _L1
            Exception exception;
            exception;
            throw exception;
            Exception exception1;
            exception1;
            RemoteMediaPlayer.d(Ba).b(null);
            throw exception1;
        }

        _cls2(GoogleApiClient googleapiclient, long al[])
        {
            Ba = RemoteMediaPlayer.this;
            Bb = googleapiclient;
            Bc = al;
            super();
        }
    }


    private class _cls10 extends b
    {

        final RemoteMediaPlayer Ba;
        final GoogleApiClient Bb;
        final JSONObject Bh;
        final boolean Bl;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((gh)a1);
        }

        protected void a(gh gh1)
        {
            Object obj = RemoteMediaPlayer.c(Ba);
            obj;
            JVM INSTR monitorenter ;
            RemoteMediaPlayer.d(Ba).b(Bb);
            RemoteMediaPlayer.e(Ba).a(Bq, Bl, Bh);
            RemoteMediaPlayer.d(Ba).b(null);
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            IllegalStateException illegalstateexception;
            illegalstateexception;
            b(l(new Status(1)));
            RemoteMediaPlayer.d(Ba).b(null);
              goto _L1
            Exception exception;
            exception;
            throw exception;
            IOException ioexception;
            ioexception;
            b(l(new Status(1)));
            RemoteMediaPlayer.d(Ba).b(null);
              goto _L1
            Exception exception1;
            exception1;
            RemoteMediaPlayer.d(Ba).b(null);
            throw exception1;
        }

        _cls10(GoogleApiClient googleapiclient, boolean flag, JSONObject jsonobject)
        {
            Ba = RemoteMediaPlayer.this;
            Bb = googleapiclient;
            Bl = flag;
            Bh = jsonobject;
            super();
        }
    }


    private class _cls9 extends b
    {

        final RemoteMediaPlayer Ba;
        final GoogleApiClient Bb;
        final JSONObject Bh;
        final double Bk;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((gh)a1);
        }

        protected void a(gh gh1)
        {
            Object obj = RemoteMediaPlayer.c(Ba);
            obj;
            JVM INSTR monitorenter ;
            RemoteMediaPlayer.d(Ba).b(Bb);
            RemoteMediaPlayer.e(Ba).a(Bq, Bk, Bh);
            RemoteMediaPlayer.d(Ba).b(null);
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            IllegalStateException illegalstateexception;
            illegalstateexception;
            b(l(new Status(1)));
            RemoteMediaPlayer.d(Ba).b(null);
              goto _L1
            Exception exception;
            exception;
            throw exception;
            IllegalArgumentException illegalargumentexception;
            illegalargumentexception;
            b(l(new Status(1)));
            RemoteMediaPlayer.d(Ba).b(null);
              goto _L1
            IOException ioexception;
            ioexception;
            b(l(new Status(1)));
            RemoteMediaPlayer.d(Ba).b(null);
              goto _L1
            Exception exception1;
            exception1;
            RemoteMediaPlayer.d(Ba).b(null);
            throw exception1;
        }

        _cls9(GoogleApiClient googleapiclient, double d1, JSONObject jsonobject)
        {
            Ba = RemoteMediaPlayer.this;
            Bb = googleapiclient;
            Bk = d1;
            Bh = jsonobject;
            super();
        }
    }


    private class _cls3 extends b
    {

        final RemoteMediaPlayer Ba;
        final GoogleApiClient Bb;
        final TextTrackStyle Bd;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((gh)a1);
        }

        protected void a(gh gh1)
        {
            Object obj = RemoteMediaPlayer.c(Ba);
            obj;
            JVM INSTR monitorenter ;
            RemoteMediaPlayer.d(Ba).b(Bb);
            RemoteMediaPlayer.e(Ba).a(Bq, Bd);
            RemoteMediaPlayer.d(Ba).b(null);
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            IOException ioexception;
            ioexception;
            b(l(new Status(1)));
            RemoteMediaPlayer.d(Ba).b(null);
              goto _L1
            Exception exception;
            exception;
            throw exception;
            Exception exception1;
            exception1;
            RemoteMediaPlayer.d(Ba).b(null);
            throw exception1;
        }

        _cls3(GoogleApiClient googleapiclient, TextTrackStyle texttrackstyle)
        {
            Ba = RemoteMediaPlayer.this;
            Bb = googleapiclient;
            Bd = texttrackstyle;
            super();
        }
    }


    private class _cls6 extends b
    {

        final RemoteMediaPlayer Ba;
        final GoogleApiClient Bb;
        final JSONObject Bh;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((gh)a1);
        }

        protected void a(gh gh1)
        {
            Object obj = RemoteMediaPlayer.c(Ba);
            obj;
            JVM INSTR monitorenter ;
            RemoteMediaPlayer.d(Ba).b(Bb);
            RemoteMediaPlayer.e(Ba).b(Bq, Bh);
            RemoteMediaPlayer.d(Ba).b(null);
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            IOException ioexception;
            ioexception;
            b(l(new Status(1)));
            RemoteMediaPlayer.d(Ba).b(null);
              goto _L1
            Exception exception;
            exception;
            throw exception;
            Exception exception1;
            exception1;
            RemoteMediaPlayer.d(Ba).b(null);
            throw exception1;
        }

        _cls6(GoogleApiClient googleapiclient, JSONObject jsonobject)
        {
            Ba = RemoteMediaPlayer.this;
            Bb = googleapiclient;
            Bh = jsonobject;
            super();
        }
    }

}
