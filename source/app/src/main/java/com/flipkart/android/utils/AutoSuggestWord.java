// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;


// Referenced classes of package com.flipkart.android.utils:
//            StringUtils, AutoSuggestType

public class AutoSuggestWord
{

    private AutoSuggestType a;
    private String b;
    private int c;
    private String d;
    private String e;
    private boolean f;

    public AutoSuggestWord(AutoSuggestType autosuggesttype, String s, int i, String s1, String s2)
    {
        a = autosuggesttype;
        b = s;
        c = i;
        e = s1;
        d = s2;
        if (!StringUtils.isNullOrEmpty(s1) && !StringUtils.isNullOrEmpty(s2))
        {
            f = true;
            return;
        } else
        {
            f = false;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        return obj != null && (obj instanceof AutoSuggestWord) && obj.toString().equals(toString());
    }

    public AutoSuggestType getAutoSuggestType()
    {
        return a;
    }

    public boolean getIsStore()
    {
        return f;
    }

    public int getSortingValue()
    {
        return c;
    }

    public String getStoreId()
    {
        return d;
    }

    public String getStoreTitle()
    {
        return e;
    }

    public String getWord()
    {
        return b;
    }

    public void setWord(String s)
    {
        b = s;
    }

    public String toString()
    {
        if (b != null && b.length() != 0)
        {
            if (f)
            {
                return (new StringBuilder()).append(b).append(" in ").append(e).toString();
            } else
            {
                return b;
            }
        } else
        {
            return "";
        }
    }
}
