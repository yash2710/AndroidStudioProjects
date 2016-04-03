// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.logger;


// Referenced classes of package com.j256.ormlite.logger:
//            LocalLog, Log

final class nit> extends nit>
{

    public final Log createLog(String s)
    {
        return new LocalLog(s);
    }

    public final boolean isAvailable()
    {
        return true;
    }

    (String s, int i, String s1, String s2)
    {
        super(s, i, s1, s2, null);
    }
}
