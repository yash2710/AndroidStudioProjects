// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.j256.ormlite.android.apptools;

import java.io.File;
import java.io.FileFilter;

final class 
    implements FileFilter
{

    public final boolean accept(File file)
    {
        return file.getName().equals("raw") && file.isDirectory();
    }

    ()
    {
    }
}
