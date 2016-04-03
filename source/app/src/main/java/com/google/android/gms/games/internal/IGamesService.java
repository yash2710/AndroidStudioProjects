// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.internal.multiplayer.ZInvitationCluster;
import com.google.android.gms.games.internal.request.GameRequestCluster;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;

// Referenced classes of package com.google.android.gms.games.internal:
//            IGamesCallbacks

public interface IGamesService
    extends IInterface
{

    public abstract void E(boolean flag);

    public abstract void F(boolean flag);

    public abstract int a(IGamesCallbacks igamescallbacks, byte abyte0[], String s1, String s2);

    public abstract Intent a(int i1, int j1, boolean flag);

    public abstract Intent a(int i1, byte abyte0[], int j1, String s1);

    public abstract Intent a(ZInvitationCluster zinvitationcluster, String s1, String s2);

    public abstract Intent a(GameRequestCluster gamerequestcluster, String s1);

    public abstract Intent a(RoomEntity roomentity, int i1);

    public abstract Intent a(String s1, boolean flag, boolean flag1, int i1);

    public abstract Intent a(int ai[]);

    public abstract Intent a(ParticipantEntity aparticipantentity[], String s1, String s2, Uri uri, Uri uri1);

    public abstract void a(long l1, String s1);

    public abstract void a(IBinder ibinder, Bundle bundle);

    public abstract void a(Contents contents);

    public abstract void a(IGamesCallbacks igamescallbacks);

    public abstract void a(IGamesCallbacks igamescallbacks, int i1);

    public abstract void a(IGamesCallbacks igamescallbacks, int i1, int j1, int k1);

    public abstract void a(IGamesCallbacks igamescallbacks, int i1, int j1, boolean flag, boolean flag1);

    public abstract void a(IGamesCallbacks igamescallbacks, int i1, int j1, String as[], Bundle bundle);

    public abstract void a(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1);

    public abstract void a(IGamesCallbacks igamescallbacks, int i1, int ai[]);

    public abstract void a(IGamesCallbacks igamescallbacks, long l1);

    public abstract void a(IGamesCallbacks igamescallbacks, long l1, String s1);

    public abstract void a(IGamesCallbacks igamescallbacks, Bundle bundle, int i1, int j1);

    public abstract void a(IGamesCallbacks igamescallbacks, IBinder ibinder, int i1, String as[], Bundle bundle, boolean flag, long l1);

    public abstract void a(IGamesCallbacks igamescallbacks, IBinder ibinder, String s1, boolean flag, long l1);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1, int j1, int k1, boolean flag);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1, IBinder ibinder, Bundle bundle);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1, boolean flag2, boolean flag3);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int i1, int ai[]);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, long l1);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, long l1, String s2);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, IBinder ibinder, Bundle bundle);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, SnapshotMetadataChange snapshotmetadatachange, Contents contents);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, int j1);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, int j1, int k1);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, int j1, int k1, boolean flag);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, boolean flag, boolean flag1);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, SnapshotMetadataChange snapshotmetadatachange, Contents contents);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, boolean flag);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, int ai[], int i1, boolean flag);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, String as[]);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String s2, String as[], boolean flag);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, boolean flag);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, byte abyte0[], String s2, ParticipantResult aparticipantresult[]);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, byte abyte0[], ParticipantResult aparticipantresult[]);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, int ai[]);

    public abstract void a(IGamesCallbacks igamescallbacks, String s1, String as[], int i1, byte abyte0[], int j1);

    public abstract void a(IGamesCallbacks igamescallbacks, boolean flag);

    public abstract void a(IGamesCallbacks igamescallbacks, boolean flag, Bundle bundle);

    public abstract void a(IGamesCallbacks igamescallbacks, boolean flag, String as[]);

    public abstract void a(IGamesCallbacks igamescallbacks, int ai[]);

    public abstract void a(IGamesCallbacks igamescallbacks, int ai[], int i1, boolean flag);

    public abstract void a(IGamesCallbacks igamescallbacks, String as[]);

    public abstract void a(IGamesCallbacks igamescallbacks, String as[], boolean flag);

    public abstract Intent aR(String s1);

    public abstract Intent aU(String s1);

    public abstract String aV(String s1);

    public abstract String aW(String s1);

    public abstract void aX(String s1);

    public abstract int aY(String s1);

    public abstract Uri aZ(String s1);

    public abstract int b(byte abyte0[], String s1, String as[]);

    public abstract Intent b(int i1, int j1, boolean flag);

    public abstract void b(long l1, String s1);

    public abstract void b(IGamesCallbacks igamescallbacks);

    public abstract void b(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1);

    public abstract void b(IGamesCallbacks igamescallbacks, long l1);

    public abstract void b(IGamesCallbacks igamescallbacks, long l1, String s1);

    public abstract void b(IGamesCallbacks igamescallbacks, String s1);

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, int i1);

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, int i1, int j1, int k1, boolean flag);

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, int i1, IBinder ibinder, Bundle bundle);

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag);

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1);

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, IBinder ibinder, Bundle bundle);

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, String s2);

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, int j1, int k1, boolean flag);

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, String s2, int i1, boolean flag, boolean flag1);

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, String s2, boolean flag);

    public abstract void b(IGamesCallbacks igamescallbacks, String s1, boolean flag);

    public abstract void b(IGamesCallbacks igamescallbacks, boolean flag);

    public abstract void b(IGamesCallbacks igamescallbacks, String as[]);

    public abstract void b(String s1, String s2, int i1);

    public abstract void ba(String s1);

    public abstract ParcelFileDescriptor bb(String s1);

    public abstract void c(long l1, String s1);

    public abstract void c(IGamesCallbacks igamescallbacks);

    public abstract void c(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1);

    public abstract void c(IGamesCallbacks igamescallbacks, long l1);

    public abstract void c(IGamesCallbacks igamescallbacks, long l1, String s1);

    public abstract void c(IGamesCallbacks igamescallbacks, String s1);

    public abstract void c(IGamesCallbacks igamescallbacks, String s1, int i1);

    public abstract void c(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1);

    public abstract void c(IGamesCallbacks igamescallbacks, String s1, String s2);

    public abstract void c(IGamesCallbacks igamescallbacks, String s1, String s2, boolean flag);

    public abstract void c(IGamesCallbacks igamescallbacks, String s1, boolean flag);

    public abstract void c(IGamesCallbacks igamescallbacks, boolean flag);

    public abstract void c(IGamesCallbacks igamescallbacks, String as[]);

    public abstract void c(String s1, String s2, int i1);

    public abstract void ch(int i1);

    public abstract void d(long l1, String s1);

    public abstract void d(IGamesCallbacks igamescallbacks);

    public abstract void d(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1);

    public abstract void d(IGamesCallbacks igamescallbacks, long l1);

    public abstract void d(IGamesCallbacks igamescallbacks, long l1, String s1);

    public abstract void d(IGamesCallbacks igamescallbacks, String s1);

    public abstract void d(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1);

    public abstract void d(IGamesCallbacks igamescallbacks, String s1, String s2);

    public abstract void d(IGamesCallbacks igamescallbacks, String s1, boolean flag);

    public abstract void d(IGamesCallbacks igamescallbacks, boolean flag);

    public abstract void e(IGamesCallbacks igamescallbacks);

    public abstract void e(IGamesCallbacks igamescallbacks, int i1, boolean flag, boolean flag1);

    public abstract void e(IGamesCallbacks igamescallbacks, String s1);

    public abstract void e(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1);

    public abstract void e(IGamesCallbacks igamescallbacks, String s1, String s2);

    public abstract void e(IGamesCallbacks igamescallbacks, String s1, boolean flag);

    public abstract void e(IGamesCallbacks igamescallbacks, boolean flag);

    public abstract Bundle ef();

    public abstract void f(IGamesCallbacks igamescallbacks);

    public abstract void f(IGamesCallbacks igamescallbacks, String s1);

    public abstract void f(IGamesCallbacks igamescallbacks, String s1, int i1, boolean flag, boolean flag1);

    public abstract void f(IGamesCallbacks igamescallbacks, String s1, String s2);

    public abstract void f(IGamesCallbacks igamescallbacks, boolean flag);

    public abstract void g(IGamesCallbacks igamescallbacks);

    public abstract void g(IGamesCallbacks igamescallbacks, String s1);

    public abstract String gZ();

    public abstract ParcelFileDescriptor h(Uri uri);

    public abstract RoomEntity h(IGamesCallbacks igamescallbacks, String s1);

    public abstract void h(IGamesCallbacks igamescallbacks);

    public abstract DataHolder hA();

    public abstract void hB();

    public abstract Intent hC();

    public abstract void hD();

    public abstract boolean hE();

    public abstract String ha();

    public abstract Intent hd();

    public abstract Intent he();

    public abstract Intent hf();

    public abstract Intent hg();

    public abstract Intent hl();

    public abstract Intent hm();

    public abstract int hn();

    public abstract String ho();

    public abstract int hp();

    public abstract Intent hq();

    public abstract int hr();

    public abstract int hs();

    public abstract int ht();

    public abstract int hu();

    public abstract void hw();

    public abstract DataHolder hy();

    public abstract boolean hz();

    public abstract void i(IGamesCallbacks igamescallbacks);

    public abstract void i(IGamesCallbacks igamescallbacks, String s1);

    public abstract void j(IGamesCallbacks igamescallbacks);

    public abstract void j(IGamesCallbacks igamescallbacks, String s1);

    public abstract void k(IGamesCallbacks igamescallbacks, String s1);

    public abstract void l(IGamesCallbacks igamescallbacks, String s1);

    public abstract void l(String s1, int i1);

    public abstract void m(IGamesCallbacks igamescallbacks, String s1);

    public abstract void m(String s1, int i1);

    public abstract void m(String s1, String s2);

    public abstract void n(IGamesCallbacks igamescallbacks, String s1);

    public abstract void n(String s1, int i1);

    public abstract void n(String s1, String s2);

    public abstract void o(IGamesCallbacks igamescallbacks, String s1);

    public abstract void p(IGamesCallbacks igamescallbacks, String s1);

    public abstract void p(String s1, int i1);

    public abstract void q(long l1);

    public abstract void q(IGamesCallbacks igamescallbacks, String s1);

    public abstract void q(String s1, int i1);

    public abstract void r(long l1);

    public abstract void r(IGamesCallbacks igamescallbacks, String s1);

    public abstract void s(long l1);

    public abstract void s(IGamesCallbacks igamescallbacks, String s1);

    public abstract void t(long l1);

    public abstract void t(IGamesCallbacks igamescallbacks, String s1);

    public abstract void u(long l1);

    public abstract void u(IGamesCallbacks igamescallbacks, String s1);
}
