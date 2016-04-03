// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.view.View;
import com.flipkart.android.activity.HomeFragmentHolderActivity;

// Referenced classes of package com.flipkart.android.customviews:
//            ActionBarView

final class b
    implements android.view.View.OnClickListener
{

    b()
    {
    }

    public final void onClick(View view)
    {
        ActionBarView.a().doShowCart();
    }
}
