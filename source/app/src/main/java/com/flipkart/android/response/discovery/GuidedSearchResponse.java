// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.discovery;

import java.util.ArrayList;

public class GuidedSearchResponse
{

    public ArrayList current;

    public GuidedSearchResponse()
    {
    }

    public ArrayList getGuideDataList()
    {
        return current;
    }

    public void setGuideDataList(ArrayList arraylist)
    {
        current = arraylist;
    }
}