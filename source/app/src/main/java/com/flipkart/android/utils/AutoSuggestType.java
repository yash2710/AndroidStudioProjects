// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;


public final class AutoSuggestType extends Enum
{

    public static final AutoSuggestType BrowseHistory;
    public static final AutoSuggestType RecentSearch;
    public static final AutoSuggestType TopSearches;
    private static final AutoSuggestType a[];

    private AutoSuggestType(String s, int i)
    {
        super(s, i);
    }

    public static AutoSuggestType valueOf(String s)
    {
        return (AutoSuggestType)Enum.valueOf(com/flipkart/android/utils/AutoSuggestType, s);
    }

    public static AutoSuggestType[] values()
    {
        return (AutoSuggestType[])a.clone();
    }

    static 
    {
        RecentSearch = new AutoSuggestType("RecentSearch", 0);
        BrowseHistory = new AutoSuggestType("BrowseHistory", 1);
        TopSearches = new AutoSuggestType("TopSearches", 2);
        AutoSuggestType aautosuggesttype[] = new AutoSuggestType[3];
        aautosuggesttype[0] = RecentSearch;
        aautosuggesttype[1] = BrowseHistory;
        aautosuggesttype[2] = TopSearches;
        a = aautosuggesttype;
    }
}
