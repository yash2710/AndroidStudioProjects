// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.wearable;

import com.flipkart.android.wearable.shared.SharedAction;

final class h
{

    static final int a[];

    static 
    {
        a = new int[SharedAction.values().length];
        try
        {
            a[SharedAction.REFRESH_WISH_LIST_PATH.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            a[SharedAction.UPDATE_IMAGE_URL.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            a[SharedAction.OPEN_ON_PHONE.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2)
        {
            return;
        }
    }
}
