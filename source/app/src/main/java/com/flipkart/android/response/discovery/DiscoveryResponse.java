// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;

import com.flipkart.android.response.baseresponse.BaseResponse;
import java.util.LinkedHashMap;
import java.util.Map;

// Referenced classes of package com.flipkart.android.response.discovery:
//            SearchResponse, ShowPinResponse

public class DiscoveryResponse extends BaseResponse
{

    private Map productInfoMap;
    private SearchResponse searchResponse;
    private ShowPinResponse showPin;

    public DiscoveryResponse()
    {
        searchResponse = new SearchResponse();
        productInfoMap = new LinkedHashMap();
        showPin = new ShowPinResponse();
    }

    public Map getProductInfoMap()
    {
        if (productInfoMap == null)
        {
            productInfoMap = new LinkedHashMap();
        }
        return productInfoMap;
    }

    public SearchResponse getSearchResponse()
    {
        return searchResponse;
    }

    public ShowPinResponse getShowPin()
    {
        if (showPin == null)
        {
            showPin = new ShowPinResponse();
        }
        return showPin;
    }

    public void setProductInfoMap(Map map)
    {
        productInfoMap = map;
    }

    public void setSearchResponse(SearchResponse searchresponse)
    {
        searchResponse = searchresponse;
    }

    public void setShowPin(ShowPinResponse showpinresponse)
    {
        showPin = showpinresponse;
    }
}
