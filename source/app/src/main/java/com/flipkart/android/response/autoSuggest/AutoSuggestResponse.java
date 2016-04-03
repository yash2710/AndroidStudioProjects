// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.autoSuggest;

import com.flipkart.android.response.baseresponse.BaseResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class AutoSuggestResponse extends BaseResponse
{

    private Map wordMap;

    public AutoSuggestResponse()
    {
        wordMap = new LinkedHashMap();
    }

    public Map getWordMap()
    {
        if (wordMap == null)
        {
            wordMap = new LinkedHashMap();
        }
        return wordMap;
    }

    public void setWordMap(Map map)
    {
        wordMap = map;
    }
}
