// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.internal;

import java.net.URI;

class tag
{

    private static final int HASH_MULTIPLIER = 37;
    private static final int HASH_SEED = 29;
    Object tag;
    URI uri;

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if (obj != null)
        {
            boolean flag1 = obj instanceof tag;
            flag = false;
            if (flag1)
            {
                tag tag1 = (tag)obj;
                URI uri1 = tag1.uri;
                URI uri2 = uri;
                flag = false;
                if (uri1 == uri2)
                {
                    Object obj1 = tag1.tag;
                    Object obj2 = tag;
                    flag = false;
                    if (obj1 == obj2)
                    {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    public int hashCode()
    {
        return 37 * (1073 + uri.hashCode()) + tag.hashCode();
    }

    (URI uri1, Object obj)
    {
        uri = uri1;
        tag = obj;
    }
}
