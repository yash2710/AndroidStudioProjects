// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;


final class k
{

    static final int a[];
    static final int b[];

    static 
    {
        b = new int[EnhancedListView.SwipeDirection.values().length];
        try
        {
            b[EnhancedListView.SwipeDirection.BOTH.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            b[EnhancedListView.SwipeDirection.START.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            b[EnhancedListView.SwipeDirection.END.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        a = new int[EnhancedListView.UndoStyle.values().length];
        try
        {
            a[EnhancedListView.UndoStyle.SINGLE_POPUP.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            a[EnhancedListView.UndoStyle.COLLAPSED_POPUP.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }
        try
        {
            a[EnhancedListView.UndoStyle.MULTILEVEL_POPUP.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror5)
        {
            return;
        }
    }
}
