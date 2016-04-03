// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.browser;


public class Version
{

    private String a;
    private String b;
    private String c;

    public Version(String s, String s1, String s2)
    {
        a = s;
        b = s1;
        c = s2;
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        Version version;
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        version = (Version)obj;
        if (b == null)
        {
            if (version.b != null)
            {
                return false;
            }
        } else
        if (!b.equals(version.b))
        {
            return false;
        }
        if (c == null)
        {
            if (version.c != null)
            {
                return false;
            }
        } else
        if (!c.equals(version.c))
        {
            return false;
        }
        if (a != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (version.a == null) goto _L1; else goto _L3
_L3:
        return false;
        if (a.equals(version.a)) goto _L1; else goto _L4
_L4:
        return false;
    }

    public String getMajorVersion()
    {
        return b;
    }

    public String getMinorVersion()
    {
        return c;
    }

    public String getVersion()
    {
        return a;
    }

    public int hashCode()
    {
        int i;
        int j;
        int k;
        int l;
        String s;
        int i1;
        if (b == null)
        {
            i = 0;
        } else
        {
            i = b.hashCode();
        }
        j = 31 * (i + 31);
        if (c == null)
        {
            k = 0;
        } else
        {
            k = c.hashCode();
        }
        l = 31 * (k + j);
        s = a;
        i1 = 0;
        if (s != null)
        {
            i1 = a.hashCode();
        }
        return l + i1;
    }

    public String toString()
    {
        return a;
    }
}
