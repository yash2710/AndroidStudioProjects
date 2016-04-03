// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.flipkart.android.utils.ScreenMathUtils;

// Referenced classes of package com.flipkart.android.customwidget:
//            OfferZoneWidget

class val.title
    implements com.android.volley.toolbox.istener
{

    final OfferZoneWidget this$0;
    final TextView val$title;

    public void onErrorResponse(VolleyError volleyerror)
    {
    }

    public void onResponse(com.android.volley.toolbox.ontainer ontainer, boolean flag)
    {
        if (ontainer.getBitmap() != null && val$title != null)
        {
            val$title.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(context.getResources(), ontainer.getBitmap()), null, context.getResources().getDrawable(0x7f02005d), null);
            val$title.setCompoundDrawablePadding(ScreenMathUtils.dpToPx(5, context));
        }
    }

    r()
    {
        this$0 = final_offerzonewidget;
        val$title = TextView.this;
        super();
    }
}
