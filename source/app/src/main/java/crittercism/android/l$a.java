// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import java.io.File;
import java.io.FilenameFilter;

public final class a
    implements FilenameFilter
{

    private String a;

    public final boolean accept(File file, String s)
    {
        return s.endsWith(a);
    }

    public (String s)
    {
        a = new String();
        if (s != null)
        {
            a = s;
        }
    }
}
