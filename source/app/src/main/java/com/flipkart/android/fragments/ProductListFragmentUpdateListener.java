// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import com.flipkart.android.datahandler.param.FooterParams;
import com.flipkart.android.datahandler.param.HeaderParams;

public interface ProductListFragmentUpdateListener
{

    public abstract void buildErrorMessage(int i, int j, String s, String s1);

    public abstract void updateFooter(FooterParams footerparams);

    public abstract void updateHeader(HeaderParams headerparams);

    public abstract void updateListView();
}
