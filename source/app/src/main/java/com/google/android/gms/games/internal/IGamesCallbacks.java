// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;

public interface IGamesCallbacks
    extends IInterface
{

    public abstract void A(DataHolder dataholder);

    public abstract void B(DataHolder dataholder);

    public abstract void C(DataHolder dataholder);

    public abstract void D(DataHolder dataholder);

    public abstract void E(DataHolder dataholder);

    public abstract void F(DataHolder dataholder);

    public abstract void G(DataHolder dataholder);

    public abstract void H(DataHolder dataholder);

    public abstract void I(DataHolder dataholder);

    public abstract void J(DataHolder dataholder);

    public abstract void K(DataHolder dataholder);

    public abstract void L(DataHolder dataholder);

    public abstract void M(DataHolder dataholder);

    public abstract void N(DataHolder dataholder);

    public abstract void O(DataHolder dataholder);

    public abstract void P(DataHolder dataholder);

    public abstract void a(int i1, String s1, boolean flag);

    public abstract void a(DataHolder dataholder, DataHolder dataholder1);

    public abstract void a(DataHolder dataholder, Contents contents);

    public abstract void a(DataHolder dataholder, String s1, Contents contents, Contents contents1, Contents contents2);

    public abstract void a(DataHolder dataholder, String as[]);

    public abstract void b(int i1, int j1, String s1);

    public abstract void b(int i1, Bundle bundle);

    public abstract void b(DataHolder dataholder, String as[]);

    public abstract void c(int i1, Bundle bundle);

    public abstract void c(DataHolder dataholder);

    public abstract void c(DataHolder dataholder, String as[]);

    public abstract void cd(int i1);

    public abstract void ce(int i1);

    public abstract void d(int i1, Bundle bundle);

    public abstract void d(int i1, String s1);

    public abstract void d(DataHolder dataholder);

    public abstract void d(DataHolder dataholder, String as[]);

    public abstract void dT();

    public abstract void e(int i1, Bundle bundle);

    public abstract void e(int i1, String s1);

    public abstract void e(DataHolder dataholder);

    public abstract void e(DataHolder dataholder, String as[]);

    public abstract void f(int i1, Bundle bundle);

    public abstract void f(int i1, String s1);

    public abstract void f(DataHolder dataholder);

    public abstract void f(DataHolder dataholder, String as[]);

    public abstract void g(int i1, String s1);

    public abstract void g(DataHolder dataholder);

    public abstract void h(DataHolder dataholder);

    public abstract void i(DataHolder dataholder);

    public abstract void j(DataHolder dataholder);

    public abstract void k(DataHolder dataholder);

    public abstract void l(DataHolder dataholder);

    public abstract void m(DataHolder dataholder);

    public abstract void n(DataHolder dataholder);

    public abstract void o(DataHolder dataholder);

    public abstract void onInvitationRemoved(String s1);

    public abstract void onLeftRoom(int i1, String s1);

    public abstract void onP2PConnected(String s1);

    public abstract void onP2PDisconnected(String s1);

    public abstract void onRealTimeMessageReceived(RealTimeMessage realtimemessage);

    public abstract void onRequestRemoved(String s1);

    public abstract void onTurnBasedMatchRemoved(String s1);

    public abstract void p(DataHolder dataholder);

    public abstract void q(DataHolder dataholder);

    public abstract void r(DataHolder dataholder);

    public abstract void s(DataHolder dataholder);

    public abstract void t(DataHolder dataholder);

    public abstract void u(DataHolder dataholder);

    public abstract void v(DataHolder dataholder);

    public abstract void w(DataHolder dataholder);

    public abstract void x(DataHolder dataholder);

    public abstract void y(DataHolder dataholder);

    public abstract void z(DataHolder dataholder);
}
