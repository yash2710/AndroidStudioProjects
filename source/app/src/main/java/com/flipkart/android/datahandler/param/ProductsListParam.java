// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler.param;

import com.flipkart.android.utils.PageTypeUtils;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.datahandler.param:
//            BaseDataHandlerParam

public class ProductsListParam extends BaseDataHandlerParam
{

    private ArrayList a;
    private String b;
    private String c;
    private int d;
    private PageTypeUtils e;

    public ProductsListParam(ArrayList arraylist, String s, String s1, PageTypeUtils pagetypeutils)
    {
        a = arraylist;
        b = s;
        c = s1;
        e = pagetypeutils;
    }

    public PageTypeUtils getPageType()
    {
        return e;
    }

    public ArrayList getProductIds()
    {
        return a;
    }

    public String getSelectedId()
    {
        return b;
    }

    public int getSelectedIndex()
    {
        return d;
    }

    public String getTitle()
    {
        if (e == PageTypeUtils.WishList)
        {
            if (getProductIds() != null)
            {
                setTitle((new StringBuilder("Wishlist (")).append(getProductIds().size()).append(" items)").toString());
            } else
            {
                setTitle("Wishlist (0 items)");
            }
        }
        return c;
    }

    public void removeProdId(String s)
    {
        a.remove(s);
    }

    public void setPageType(PageTypeUtils pagetypeutils)
    {
        e = pagetypeutils;
    }

    public void setProductIds(ArrayList arraylist)
    {
        if (arraylist != null)
        {
            a = new ArrayList();
            for (int i = 0; i < arraylist.size(); i++)
            {
                a.add(arraylist.get(i));
            }

        } else
        {
            a = arraylist;
        }
    }

    public void setSelectedId(String s)
    {
        b = s;
    }

    public void setSelectedIndex(int i)
    {
        d = i;
    }

    public void setTitle(String s)
    {
        c = s;
    }

    public String toString()
    {
        return (new StringBuilder("ProductsListParam [title=")).append(c).append(", selectedIndex=").append(d).append(", pageType=").append(e).append("]").toString();
    }
}
