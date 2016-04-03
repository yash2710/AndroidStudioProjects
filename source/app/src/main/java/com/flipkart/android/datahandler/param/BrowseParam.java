// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler.param;

import java.util.Arrays;

// Referenced classes of package com.flipkart.android.datahandler.param:
//            BaseDataHandlerParam

public class BrowseParam extends BaseDataHandlerParam
{

    private String a;
    private String b;
    private String c;
    private String d;
    private String e[];
    private String f;
    private String g[];
    private String h;
    private String i;
    private String j;
    private boolean k;
    private String l[];

    public BrowseParam()
    {
    }

    public BrowseParam(String s, String s1, String s2, String as[], String s3, String as1[], String s4, 
            String s5, String s6, boolean flag, String as2[])
    {
        a = s;
        c = s2;
        b = s1;
        e = as;
        f = s3;
        g = as1;
        h = s4;
        i = s5;
        j = s6;
        k = flag;
        l = as2;
    }

    public String[] getFilters()
    {
        return e;
    }

    public String getFindingMethod()
    {
        return j;
    }

    public String getPath()
    {
        return i;
    }

    public String getPincode()
    {
        return a;
    }

    public String getQuery()
    {
        return b;
    }

    public String getSortOption()
    {
        return f;
    }

    public String getStoreId()
    {
        return c;
    }

    public String getStoreName()
    {
        return d;
    }

    public String[] getTags()
    {
        return g;
    }

    public String getTitle()
    {
        return h;
    }

    public String[] getViews()
    {
        return l;
    }

    public boolean isEnableAugmentSearch()
    {
        return k;
    }

    public void setEnableAugmentSearch(boolean flag)
    {
        k = flag;
    }

    public void setFilters(String as[])
    {
        e = as;
    }

    public void setFindingMethod(String s)
    {
        j = s;
    }

    public void setPath(String s)
    {
        i = s;
    }

    public void setPincode(String s)
    {
        a = s;
    }

    public void setQuery(String s)
    {
        b = s;
    }

    public void setSortOption(String s)
    {
        f = s;
    }

    public void setStoreId(String s)
    {
        c = s;
    }

    public void setStoreName(String s)
    {
        d = s;
    }

    public void setTags(String as[])
    {
        g = as;
    }

    public void setTitle(String s)
    {
        h = s;
    }

    public void setViews(String as[])
    {
        l = as;
    }

    public String toString()
    {
        return (new StringBuilder("BrowseParam [pincode=")).append(a).append(", query=").append(b).append(", storeId=").append(c).append(", storeName=").append(d).append(", filters=").append(Arrays.toString(e)).append(", sortOption=").append(f).append(", tags=").append(Arrays.toString(g)).append(", title=").append(h).append(", view=").append(Arrays.toString(l)).append("]").toString();
    }
}
