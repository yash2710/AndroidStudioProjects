// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.utils:
//            FkProductListContext

public class RefineByCategoryResponse
{

    private ArrayList a;
    private ArrayList b;
    private FkProductListContext c;

    public RefineByCategoryResponse()
    {
    }

    public FkProductListContext getFkContext()
    {
        return c;
    }

    public ArrayList getParentStoreList()
    {
        if (b == null)
        {
            b = new ArrayList();
        }
        return b;
    }

    public ArrayList getStoreList()
    {
        if (a == null)
        {
            a = new ArrayList();
        }
        return a;
    }

    public void setFkContext(FkProductListContext fkproductlistcontext)
    {
        c = fkproductlistcontext;
    }

    public void setParentStoreList(ArrayList arraylist)
    {
        b = arraylist;
    }

    public void setStoreList(ArrayList arraylist)
    {
        a = arraylist;
    }
}
