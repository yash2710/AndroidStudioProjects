// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.logger;


public interface Log
{

    public abstract boolean isLevelEnabled(Level level);

    public abstract void log(Level level, String s);

    public abstract void log(Level level, String s, Throwable throwable);
}
