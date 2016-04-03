// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.support.v4.util.LruCache;
import android.view.View;
import com.flipkart.android.analytics.ProductListViewType;
import com.flipkart.android.datahandler.param.BaseDataHandlerParam;
import com.flipkart.android.fragments.model.ProductPageModel;
import com.flipkart.android.response.discovery.FacetResponse;
import com.flipkart.android.response.discovery.FacetValue;
import com.flipkart.android.response.discovery.GuidedSearchResponse;
import com.flipkart.android.response.discovery.PerFilterMetaData;
import com.flipkart.android.response.discovery.ResourceResponse;
import com.flipkart.android.response.discovery.StoreMetaInfo;
import com.flipkart.android.response.productInfo.ProductInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.utils:
//            PinCodeWidgetState, FacetData, ProductSpecificSellerTypes

public class FkProductListContext
    implements Cloneable
{

    private List A;
    private ArrayList a;
    private Map b;
    private Map c;
    private LruCache d;
    private Map e;
    private Map f;
    private Map g;
    private Map h;
    private String i;
    private String j;
    private ProductListViewType k;
    private List l;
    private GuidedSearchResponse m;
    private int n;
    private String o;
    private ArrayList p;
    private ArrayList q;
    private StoreMetaInfo r;
    private BaseDataHandlerParam s;
    private ProductSpecificSellerTypes t;
    private ProductPageModel u;
    private PinCodeWidgetState v;
    private boolean w;
    private String x;
    private View y;
    private String z;

    public FkProductListContext()
    {
        a = new ArrayList();
        b = new HashMap();
        c = new HashMap();
        d = null;
        e = new LinkedHashMap();
        f = new LinkedHashMap();
        g = new LinkedHashMap();
        h = new HashMap();
        l = new ArrayList();
        s = null;
        v = PinCodeWidgetState.None;
        A = new ArrayList();
    }

    public void addProductIds(ArrayList arraylist)
    {
        if (a == null)
        {
            a = new ArrayList();
        }
        for (int i1 = 0; i1 < arraylist.size(); i1++)
        {
            a.remove(arraylist.get(i1));
            a.add(arraylist.get(i1));
        }

    }

    public void addProductMap(Map map)
    {
        if (map != null)
        {
            if (d == null)
            {
                d = new LruCache(5);
            }
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) 
            {
                String s1 = (String)iterator.next();
                if (s1 != null)
                {
                    ProductInfo productinfo = (ProductInfo)map.get(s1);
                    if (productinfo != null)
                    {
                        d.put(s1, productinfo);
                    }
                }
            }
        }
    }

    public void clearAllListingIds()
    {
        if (b != null)
        {
            b.clear();
        }
    }

    public void clearAllOfferIds()
    {
        if (c != null)
        {
            c.clear();
        }
    }

    public void clearAllProductIds()
    {
        if (a != null)
        {
            a.clear();
        }
    }

    public void clearAugmentedQueriesList()
    {
        if (q != null)
        {
            q.clear();
        }
    }

    public void clearFilterMaps()
    {
        e.clear();
        g.clear();
        f.clear();
        h.clear();
    }

    public void clearMutipleSubFiltersCheckedItems()
    {
        h.clear();
    }

    public void clearProducts()
    {
        if (a != null)
        {
            a.clear();
        }
        if (d != null)
        {
            d.evictAll();
        }
    }

    public void clearSelectedFilterMap()
    {
        g.clear();
    }

    public void clearSpellSuggestionList()
    {
        if (p != null)
        {
            p.clear();
        }
    }

    public FkProductListContext clone()
    {
        FkProductListContext fkproductlistcontext = new FkProductListContext();
        fkproductlistcontext.setParam(getParam());
        fkproductlistcontext.addProductIds(getProductIds());
        fkproductlistcontext.setProdIdListingIdMap(getProdIdListingIdMap());
        fkproductlistcontext.setProdIdOfferIdMap(getProdIdOfferIdMap());
        fkproductlistcontext.setTotalProductCount(getTotalProductCount());
        return fkproductlistcontext;
    }

    public volatile Object clone()
    {
        return clone();
    }

    public ArrayList getAugmentedQueriesList()
    {
        return q;
    }

    public String getCurrPageVertical()
    {
        return z;
    }

    public Map getFacetMetaDataMap()
    {
        return f;
    }

    public Map getFilterMap()
    {
        return e;
    }

    public GuidedSearchResponse getGuidedSearchResponse()
    {
        return m;
    }

    public View getHeaderView()
    {
        return y;
    }

    public Map getMultipleSubFiltersCheckItems()
    {
        return h;
    }

    public BaseDataHandlerParam getParam()
    {
        return s;
    }

    public PinCodeWidgetState getPinCodeWidgetState()
    {
        return v;
    }

    public String getPincode()
    {
        return j;
    }

    public Map getProdIdListingIdMap()
    {
        return b;
    }

    public Map getProdIdOfferIdMap()
    {
        return c;
    }

    public ProductInfo getProductForId(String s1)
    {
        if (d != null)
        {
            return (ProductInfo)d.get(s1);
        } else
        {
            return null;
        }
    }

    public ArrayList getProductIds()
    {
        if (a == null)
        {
            a = new ArrayList();
        }
        return a;
    }

    public LruCache getProductMap()
    {
        if (d == null)
        {
            d = new LruCache(6);
        }
        return d;
    }

    public ProductPageModel getProductModel()
    {
        return u;
    }

    public ProductSpecificSellerTypes getProductSpecificSellerType()
    {
        return t;
    }

    public int getProductsCount()
    {
        if (a == null)
        {
            return 0;
        } else
        {
            return a.size();
        }
    }

    public Map getSelectedFilterMap()
    {
        return g;
    }

    public List getSelectedSizes()
    {
        return A;
    }

    public List getSortOptions()
    {
        return l;
    }

    public ArrayList getSpellSuggestionList()
    {
        return p;
    }

    public String getStoreID()
    {
        return i;
    }

    public StoreMetaInfo getStoreMetaInfo()
    {
        return r;
    }

    public String getTagTitle()
    {
        return o;
    }

    public String getTitleViewText()
    {
        return x;
    }

    public int getTotalProductCount()
    {
        return n;
    }

    public ProductListViewType getViewType()
    {
        return k;
    }

    public boolean isShowPin()
    {
        return w;
    }

    public void saveListingIdMap(Map map)
    {
        String s1;
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); b.put(s1, map.get(s1)))
        {
            s1 = (String)iterator.next();
        }

    }

    public void saveOfferIdMap(Map map)
    {
        String s1;
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); c.put(s1, map.get(s1)))
        {
            s1 = (String)iterator.next();
        }

    }

    public void setAugmentedQueriesList(ArrayList arraylist)
    {
        q = arraylist;
    }

    public void setCurrPageVertical(String s1)
    {
        z = s1;
    }

    public void setFacetMetaDataMap(Map map)
    {
        f = map;
    }

    public void setFilterMap(Map map)
    {
        e = map;
    }

    public void setFilterMaps(ArrayList arraylist)
    {
        if (arraylist == null || arraylist.size() <= 0) goto _L2; else goto _L1
_L1:
        int i1 = 0;
_L10:
        if (i1 >= arraylist.size()) goto _L2; else goto _L3
_L3:
        String s1 = ((FacetResponse)arraylist.get(i1)).getTitle();
        if (!s1.equals("AvailableCities")) goto _L5; else goto _L4
_L5:
        ArrayList arraylist1;
        com.flipkart.android.response.discovery.MetaDataMap metadatamap;
        ArrayList arraylist2;
        LinkedHashMap linkedhashmap;
        arraylist1 = ((FacetResponse)arraylist.get(i1)).getValue();
        metadatamap = ((FacetResponse)arraylist.get(i1)).getMetadata();
        arraylist2 = new ArrayList();
        linkedhashmap = new LinkedHashMap();
        int j1;
        int k1;
        j1 = 0;
        k1 = 0;
_L7:
        int l1 = arraylist1.size();
label0:
        {
            if (k1 >= l1)
            {
                break; /* Loop/switch isn't completed */
            }
            Exception exception;
            int i2;
            FacetData facetdata;
            String s2;
            String s3;
            Boolean boolean1;
            String s4;
            String s5;
            int j2;
            try
            {
                FacetValue facetvalue = (FacetValue)arraylist1.get(k1);
                facetdata = new FacetData();
                s2 = facetvalue.getTitle();
                s3 = facetvalue.getResource().getParams();
                boolean1 = Boolean.valueOf(facetvalue.getResource().isSelected());
                s4 = facetvalue.getMetadata().getId();
                s5 = facetvalue.getMetadata().getDescription();
                j2 = facetvalue.getCount();
            }
            catch (Exception exception1)
            {
                i2 = j1;
                break label0;
            }
            if (j2 > 0)
            {
                i2 = j1 + 1;
            } else
            {
                i2 = j1;
            }
            try
            {
                if (boolean1.booleanValue())
                {
                    arraylist2.add(s2);
                }
                facetdata.setSelected(boolean1.booleanValue());
                facetdata.setCount(j2);
                facetdata.setParams(s3);
                facetdata.setTitle(s2);
                facetdata.setOfferId(s4);
                facetdata.setOfferDescription(s5);
                linkedhashmap.put(s2, facetdata);
            }
            catch (Exception exception2) { }
        }
        k1++;
        j1 = i2;
        if (true) goto _L7; else goto _L6
_L6:
        if (j1 <= 0) goto _L4; else goto _L8
_L8:
        e.put(s1, linkedhashmap);
        g.put(s1, arraylist2);
        if (metadatamap != null)
        {
            try
            {
                f.put(s1, metadatamap);
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception) { }
        }
          goto _L4
_L2:
        return;
_L4:
        i1++;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public void setGuidedSearchResponse(GuidedSearchResponse guidedsearchresponse)
    {
        m = guidedsearchresponse;
    }

    public void setHeaderView(View view)
    {
        y = view;
    }

    public void setMultipleSubFiltersCheckItems(Map map)
    {
        h = map;
    }

    public void setParam(BaseDataHandlerParam basedatahandlerparam)
    {
        s = basedatahandlerparam;
    }

    public void setPinCodeWidgetState(PinCodeWidgetState pincodewidgetstate)
    {
        v = pincodewidgetstate;
    }

    public void setPincode(String s1)
    {
        j = s1;
    }

    public void setProdIdListingIdMap(Map map)
    {
        b = map;
    }

    public void setProdIdOfferIdMap(Map map)
    {
        c = map;
    }

    public void setProductModel(ProductPageModel productpagemodel)
    {
        u = productpagemodel;
    }

    public void setProductSpecificSellerType(ProductSpecificSellerTypes productspecificsellertypes)
    {
        t = productspecificsellertypes;
    }

    public void setSelectedFilterMap(Map map)
    {
        g = map;
    }

    public void setSelectedSizes(List list)
    {
        A = list;
    }

    public void setShowPin(boolean flag)
    {
        w = flag;
    }

    public void setSortOptions(List list)
    {
        l.clear();
        if (list != null)
        {
            l.addAll(list);
        }
    }

    public void setSpellSuggestionList(ArrayList arraylist)
    {
        p = arraylist;
    }

    public void setStoreID(String s1)
    {
        i = s1;
    }

    public void setStoreMetaInfo(StoreMetaInfo storemetainfo)
    {
        r = storemetainfo;
    }

    public void setTagTitle(String s1)
    {
        o = s1;
    }

    public void setTitleViewText(String s1)
    {
        x = s1;
    }

    public void setTotalProductCount(int i1)
    {
        n = i1;
    }

    public void setViewType(ProductListViewType productlistviewtype)
    {
        k = productlistviewtype;
    }
}
