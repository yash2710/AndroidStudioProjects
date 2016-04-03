// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.flipkart.android.utils.FontCache;

public class CustomRobotoRegularTextView extends TextView
{

    public CustomRobotoRegularTextView(Context context)
    {
        super(context);
        setTypeface(FontCache.getFont("robotoregular.ttf"));
    }

    public CustomRobotoRegularTextView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setTypeface(FontCache.getFont("robotoregular.ttf"));
    }
}
