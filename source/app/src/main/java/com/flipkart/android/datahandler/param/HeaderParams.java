// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler.param;

import com.flipkart.android.response.discovery.GuidedSearchResponse;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.datahandler.param:
//            BaseViewParams, BrowseParam, ProductsListParam

public class HeaderParams extends BaseViewParams
{

    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private BrowseParam e;
    private String f;
    private ProductsListParam g;
    private boolean h;
    private ArrayList i;
    private ArrayList j;
    private GuidedSearchResponse k;

    public HeaderParams()
    {
    }

    public BrowseParam getBrowseParam()
    {
        return e;
    }

    public ArrayList getFiltersInfo()
    {
        return i;
    }

    public GuidedSearchResponse getGuidedSearchResponse()
    {
        return k;
    }

    public String getPinCode()
    {
        return f;
    }

    public ProductsListParam getProductsListParam()
    {
        return g;
    }

    public ArrayList getSortOptions()
    {
        return j;
    }

    public boolean isActionShowGuides()
    {
        return d;
    }

    public boolean isActionShowPin()
    {
        return a;
    }

    public boolean isActionUpdateSortAndFiltersLayout()
    {
        return c;
    }

    public boolean isActionUpdateTitle()
    {
        return b;
    }

    public boolean isResultReceived()
    {
        return h;
    }

    public void setActionShowGuides(boolean flag)
    {
        d = flag;
    }

    public void setActionShowPin(boolean flag)
    {
        a = flag;
    }

    public void setActionUpdateSortAndFiltersLayout(boolean flag)
    {
        c = flag;
    }

    public void setActionUpdateTitle(boolean flag)
    {
        b = flag;
    }

    public void setBrowseParam(BrowseParam browseparam)
    {
        e = browseparam;
    }

    public void setFiltersInfo(ArrayList arraylist)
    {
        i = arraylist;
    }

    public void setGuidedSearchResponse(GuidedSearchResponse guidedsearchresponse)
    {
        k = guidedsearchresponse;
    }

    public void setPinCode(String s)
    {
        f = s;
    }

    public void setProductsListParam(ProductsListParam productslistparam)
    {
        g = productslistparam;
    }

    public void setResultReceived(boolean flag)
    {
        h = flag;
    }

    public void setSortOptions(ArrayList arraylist)
    {
        j = arraylist;
    }
}
