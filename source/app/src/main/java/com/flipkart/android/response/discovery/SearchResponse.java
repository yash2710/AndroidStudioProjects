// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;

import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.response.discovery:
//            MetaDataResponse

public class SearchResponse
{

    private ArrayList augmentedQueries;
    private ArrayList facetResponseList;
    private MetaDataResponse metadata;
    private ArrayList parentMetaInfoList;
    private ArrayList sortOptions;
    private ArrayList spellSuggestions;
    private ArrayList storeMetaInfoList;
    private Map storeSearchResult;
    private ArrayList tagResponseList;

    public SearchResponse()
    {
        metadata = new MetaDataResponse();
        storeSearchResult = new LinkedHashMap();
        storeMetaInfoList = new ArrayList();
        parentMetaInfoList = new ArrayList();
        facetResponseList = new ArrayList();
        tagResponseList = new ArrayList();
        sortOptions = new ArrayList();
        spellSuggestions = new ArrayList();
        augmentedQueries = new ArrayList();
    }

    public ArrayList getAugmentedQueries()
    {
        if (augmentedQueries == null)
        {
            augmentedQueries = new ArrayList();
        } else
        {
            ArrayList arraylist = new ArrayList();
            for (int i = 0; i < augmentedQueries.size(); i++)
            {
                String s = (String)augmentedQueries.get(i);
                if (!StringUtils.isNullOrEmpty(s))
                {
                    arraylist.add(s);
                }
            }

            augmentedQueries = arraylist;
        }
        return augmentedQueries;
    }

    public ArrayList getFacetResponseList()
    {
        if (facetResponseList == null)
        {
            facetResponseList = new ArrayList();
        }
        return facetResponseList;
    }

    public MetaDataResponse getMetadata()
    {
        if (metadata == null)
        {
            metadata = new MetaDataResponse();
        }
        return metadata;
    }

    public ArrayList getParentMetaInfoList()
    {
        if (parentMetaInfoList == null)
        {
            parentMetaInfoList = new ArrayList();
        }
        return parentMetaInfoList;
    }

    public ArrayList getSortOptions()
    {
        if (sortOptions == null)
        {
            sortOptions = new ArrayList();
        }
        return sortOptions;
    }

    public ArrayList getSpellSuggestions()
    {
        if (spellSuggestions == null)
        {
            spellSuggestions = new ArrayList();
        } else
        {
            ArrayList arraylist = new ArrayList();
            for (int i = 0; i < spellSuggestions.size(); i++)
            {
                String s = (String)spellSuggestions.get(i);
                if (!StringUtils.isNullOrEmpty(s))
                {
                    arraylist.add(s);
                }
            }

            spellSuggestions = arraylist;
        }
        return spellSuggestions;
    }

    public String getStoreIdInProductList()
    {
        String s = "";
        if (storeSearchResult != null)
        {
            Set set = storeSearchResult.keySet();
            if (set.size() > 0)
            {
                s = (String)set.iterator().next();
            }
        }
        return s;
    }

    public ArrayList getStoreMetaInfoList()
    {
        if (storeMetaInfoList == null)
        {
            storeMetaInfoList = new ArrayList();
        }
        return storeMetaInfoList;
    }

    public Map getStoreSearchResult()
    {
        if (storeSearchResult == null)
        {
            storeSearchResult = new LinkedHashMap();
        }
        return storeSearchResult;
    }

    public ArrayList getTagResponseList()
    {
        if (tagResponseList == null)
        {
            tagResponseList = new ArrayList();
        }
        return tagResponseList;
    }

    public void setAugmentedQueries(ArrayList arraylist)
    {
        augmentedQueries = arraylist;
    }

    public void setFacetResponseList(ArrayList arraylist)
    {
        facetResponseList = arraylist;
    }

    public void setMetadata(MetaDataResponse metadataresponse)
    {
        metadata = metadataresponse;
    }

    public void setParentMetaInfoList(ArrayList arraylist)
    {
        parentMetaInfoList = arraylist;
    }

    public void setSortOptions(ArrayList arraylist)
    {
        sortOptions = arraylist;
    }

    public void setSpellSuggestions(ArrayList arraylist)
    {
        spellSuggestions = arraylist;
    }

    public void setStoreMetaInfoList(ArrayList arraylist)
    {
        storeMetaInfoList = arraylist;
    }

    public void setStoreSearchResult(Map map)
    {
        storeSearchResult = map;
    }

    public void setTagResponseList(ArrayList arraylist)
    {
        tagResponseList = arraylist;
    }
}
