// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import java.io.File;
import java.util.Comparator;

final class R
    implements Comparator
{

    R()
    {
    }

    public final int compare(Object obj, Object obj1)
    {
        File file = (File)obj;
        File file1 = (File)obj1;
        return (int)(file.lastModified() - file1.lastModified());
    }
}
