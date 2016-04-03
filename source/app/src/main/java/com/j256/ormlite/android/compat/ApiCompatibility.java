// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.android.compat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public interface ApiCompatibility
{

    public abstract CancellationHook createCancellationHook();

    public abstract Cursor rawQuery(SQLiteDatabase sqlitedatabase, String s, String as[], CancellationHook cancellationhook);
}
