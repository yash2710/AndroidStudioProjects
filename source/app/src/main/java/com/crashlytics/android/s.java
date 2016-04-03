// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class s
    implements FilenameFilter
{

    private final String a;

    public s(String s1)
    {
        a = s1;
    }

    public final boolean accept(File file, String s1)
    {
        while (s1.equals((new StringBuilder()).append(a).append(".cls").toString()) || !s1.contains(a) || s1.endsWith(".cls_temp")) 
        {
            return false;
        }
        return true;
    }
}
