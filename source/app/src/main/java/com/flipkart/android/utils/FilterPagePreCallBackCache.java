// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.android.response.discovery.FacetResponse;
import com.flipkart.android.response.discovery.FacetValue;
import com.flipkart.android.response.discovery.PerFilterMetaData;
import com.flipkart.android.response.discovery.ResourceResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.flipkart.android.utils:
//            FkProductListContext, StringUtils, FacetData

public class FilterPagePreCallBackCache
{

    private Map a;
    private Map b;
    private Map c;
    private List d;
    private String e;
    private String f;
    private boolean g;

    public FilterPagePreCallBackCache(FkProductListContext fkproductlistcontext)
    {
        a = new LinkedHashMap(fkproductlistcontext.getFilterMap());
        b = new LinkedHashMap(fkproductlistcontext.getFacetMetaDataMap());
        c = new LinkedHashMap(fkproductlistcontext.getSelectedFilterMap());
        d = new ArrayList(fkproductlistcontext.getSortOptions());
    }

    public void clearFilterMaps()
    {
        a.clear();
        c.clear();
        b.clear();
    }

    public void clearSelectedFilterMap()
    {
        c.clear();
    }

    public Map getFilterMap()
    {
        return a;
    }

    public String getPinCode()
    {
        return f;
    }

    public Map getSelectedFilterMap()
    {
        return c;
    }

    public List getSortOptions()
    {
        return d;
    }

    public String getStoreID()
    {
        return e;
    }

    public boolean isShowPin()
    {
        return g;
    }

    public void setFilterMap(Map map)
    {
        a = new LinkedHashMap(map);
    }

    public void setFilterMaps(ArrayList arraylist)
    {
        for (int i = 0; i < arraylist.size(); i++)
        {
            String s = ((FacetResponse)arraylist.get(i)).getTitle();
            if (s.equals("AvailableCities"))
            {
                continue;
            }
            ArrayList arraylist1 = ((FacetResponse)arraylist.get(i)).getValue();
            com.flipkart.android.response.discovery.MetaDataMap metadatamap = ((FacetResponse)arraylist.get(i)).getMetadata();
            if (StringUtils.isNullOrEmpty(s) || arraylist1.size() <= 0)
            {
                continue;
            }
            ArrayList arraylist2 = new ArrayList();
            LinkedHashMap linkedhashmap = new LinkedHashMap();
            int j = 0;
            for (int k = 0; k < arraylist1.size(); k++)
            {
                FacetValue facetvalue = (FacetValue)arraylist1.get(k);
                FacetData facetdata = new FacetData();
                if (facetvalue == null)
                {
                    continue;
                }
                String s1 = facetvalue.getTitle();
                String s2 = facetvalue.getResource().getParams();
                Boolean boolean1 = Boolean.valueOf(facetvalue.getResource().isSelected());
                String s3 = facetvalue.getMetadata().getId();
                String s4 = facetvalue.getMetadata().getDescription();
                int l = facetvalue.getCount();
                if (StringUtils.isNullOrEmpty(s1) || StringUtils.isNullOrEmpty(s2))
                {
                    continue;
                }
                if (l > 0)
                {
                    j++;
                }
                if (boolean1.booleanValue())
                {
                    arraylist2.add(s1);
                }
                facetdata.setSelected(boolean1.booleanValue());
                facetdata.setCount(l);
                facetdata.setParams(s2);
                facetdata.setTitle(s1);
                facetdata.setOfferId(s3);
                facetdata.setOfferDescription(s4);
                linkedhashmap.put(s1, facetdata);
            }

            if (j <= 0)
            {
                continue;
            }
            a.put(s, linkedhashmap);
            c.put(s, arraylist2);
            if (metadatamap != null)
            {
                b.put(s, metadatamap);
            }
        }

    }

    public void setPinCode(String s)
    {
        f = s;
    }

    public void setSelectedFilterMap(Map map)
    {
        c = new LinkedHashMap(map);
    }

    public void setShowPin(boolean flag)
    {
        g = flag;
    }

    public void setSortOptions(List list)
    {
        d = new ArrayList(list);
    }

    public void setStoreID(String s)
    {
        e = s;
    }
}
