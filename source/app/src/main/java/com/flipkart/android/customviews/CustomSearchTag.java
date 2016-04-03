// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.customviews:
//            TagData

public class CustomSearchTag extends LinearLayout
{

    private final Context a;
    private final android.view.View.OnClickListener b;
    private TextView c;
    private TagData d;

    public CustomSearchTag(Context context, TagData tagdata, android.view.View.OnClickListener onclicklistener)
    {
        super(context);
        a = context;
        d = tagdata;
        b = onclicklistener;
        setOrientation(0);
        setGravity(17);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-2, ScreenMathUtils.dpToPx(24, context));
        layoutparams.setMargins(ScreenMathUtils.dpToPx(2, context), 0, ScreenMathUtils.dpToPx(2, context), 0);
        setLayoutParams(layoutparams);
        c = new TextView(a);
        c.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-2, -1));
        c.setPadding(ScreenMathUtils.dpToPx(3, a), 0, ScreenMathUtils.dpToPx(3, a), 0);
        c.setOnClickListener(b);
        c.setGravity(17);
        c.setTextSize(2, 16F);
        c.setTextColor(Color.parseColor("#FFFFFF"));
        c.setTag(d);
        addView(c);
        if (!StringUtils.isNullOrEmpty(d.getTagText()))
        {
            c.setText(d.getTagText());
            return;
        } else
        {
            setVisibility(8);
            return;
        }
    }
}
