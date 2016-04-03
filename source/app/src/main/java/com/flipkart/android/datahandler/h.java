// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.flipkart.android.response.discovery.DiscoveryResponse;
import com.flipkart.android.response.discovery.MetaDataResponse;
import com.flipkart.android.response.discovery.OmnitureData;
import com.flipkart.android.response.discovery.ResourceResponse;
import com.flipkart.android.response.discovery.SearchResponse;
import com.flipkart.android.response.discovery.ShowPinResponse;
import com.flipkart.android.response.discovery.StoreSearchResultResponse;
import com.flipkart.android.response.discovery.TagResponse;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.datahandler:
//            SearchVDataHander, SearchAndProdInfoDataHandler

final class h extends SearchVDataHander
{

    private SearchAndProdInfoDataHandler a;

    h(SearchAndProdInfoDataHandler searchandprodinfodatahandler)
    {
        a = searchandprodinfodatahandler;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        a.resultReceivedProductInfo(i, j, s, null, null, null, false, null);
    }

    public final void resultReceived(DiscoveryResponse discoveryresponse, boolean flag)
    {
        if (StringUtils.isNull(discoveryresponse)) goto _L2; else goto _L1
_L1:
        SearchResponse searchresponse = discoveryresponse.getSearchResponse();
        if (StringUtils.isNull(searchresponse)) goto _L4; else goto _L3
_L3:
        String s1;
        String s3;
        String s = "";
        ArrayList arraylist = searchresponse.getTagResponseList();
        if (arraylist != null && arraylist.size() != 0)
        {
            int i = 0;
            while (i < arraylist.size()) 
            {
                TagResponse tagresponse = (TagResponse)arraylist.get(i);
                OmnitureData omnituredata;
                String s2;
                StoreSearchResultResponse storesearchresultresponse;
                ArrayList arraylist1;
                Map map;
                if (tagresponse != null && tagresponse.getResource() != null && tagresponse.getResource().isSelected())
                {
                    s3 = tagresponse.getTitle();
                    if (!StringUtils.isNullOrEmpty(s3))
                    {
                        break MISSING_BLOCK_LABEL_367;
                    }
                } else
                {
                    s3 = s;
                }
                i++;
                s = s3;
            }
        }
        s1 = s;
_L6:
        a.resultReceivedStoreInfo(200, "", searchresponse.getStoreMetaInfoList(), searchresponse.getParentMetaInfoList(), s1, searchresponse.getMetadata().getTotalProduct(), searchresponse.getSpellSuggestions(), searchresponse.getAugmentedQueries(), discoveryresponse.getShowPin().isShowPinWidget(), null);
        omnituredata = searchresponse.getMetadata().getOmnitureData();
        if (!StringUtils.isNull(omnituredata))
        {
            a.resultReceivedOmnitureInfo(omnituredata.getSuperCategory(), omnituredata.getCategory(), omnituredata.getVertical(), omnituredata.getSubCategory());
        }
        s2 = searchresponse.getStoreIdInProductList();
        storesearchresultresponse = (StoreSearchResultResponse)searchresponse.getStoreSearchResult().get(s2);
        arraylist1 = new ArrayList();
        if (storesearchresultresponse != null)
        {
            arraylist1 = storesearchresultresponse.getProductIds();
        }
        map = discoveryresponse.getProductInfoMap();
        if (StringUtils.isNullOrEmpty(map))
        {
            a.resultReceivedNoResultFound(200, "");
        } else
        {
            SearchAndProdInfoDataHandler.a(a);
            a.resultReceivedProductInfo(200, -1, "", arraylist1, map, searchresponse.getMetadata().getLayout(), false, discoveryresponse.getRequestId());
        }
        a.resultReceivedFilterInfo(200, "", searchresponse.getFacetResponseList(), searchresponse.getSortOptions());
_L2:
        return;
_L4:
        a.resultReceivedStoreInfo(4321, "Opps someting went wrong", null, null, null, 0, null, null, false, null);
        return;
        s1 = s3;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((DiscoveryResponse)obj, flag);
    }
}
