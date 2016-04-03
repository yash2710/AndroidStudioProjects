// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler.param;


// Referenced classes of package com.flipkart.android.datahandler.param:
//            BaseViewParams

public class ListViewUpdateParams extends BaseViewParams
{

    private boolean a;

    public ListViewUpdateParams()
    {
    }

    public boolean isActionToggleListView()
    {
        return a;
    }

    public void setActionToggleListView(boolean flag)
    {
        a = flag;
    }
}
