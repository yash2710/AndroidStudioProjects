// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.achievement;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface Achievements
{

    public abstract Intent getAchievementsIntent(GoogleApiClient googleapiclient);

    public abstract void increment(GoogleApiClient googleapiclient, String s, int i);

    public abstract PendingResult incrementImmediate(GoogleApiClient googleapiclient, String s, int i);

    public abstract PendingResult load(GoogleApiClient googleapiclient, boolean flag);

    public abstract void reveal(GoogleApiClient googleapiclient, String s);

    public abstract PendingResult revealImmediate(GoogleApiClient googleapiclient, String s);

    public abstract void setSteps(GoogleApiClient googleapiclient, String s, int i);

    public abstract PendingResult setStepsImmediate(GoogleApiClient googleapiclient, String s, int i);

    public abstract void unlock(GoogleApiClient googleapiclient, String s);

    public abstract PendingResult unlockImmediate(GoogleApiClient googleapiclient, String s);
}
