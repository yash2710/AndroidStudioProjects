// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.widget.Toast;

// Referenced classes of package com.flipkart.android.fragments:
//            SubCategoryTreeView

final class br
    implements android.view.View.OnClickListener
{

    private SubCategoryTreeView a;

    br(SubCategoryTreeView subcategorytreeview)
    {
        a = subcategorytreeview;
        super();
    }

    public final void onClick(View view)
    {
        Toast.makeText(SubCategoryTreeView.a(a), "To select this category, remove other filters", 0).show();
    }
}
