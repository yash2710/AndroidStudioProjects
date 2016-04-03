// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

final class  extends ThreadLocal
{

    protected final volatile Object initialValue()
    {
        return initialValue();
    }

    protected final DateFormat initialValue()
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
    }

    ()
    {
    }
}
