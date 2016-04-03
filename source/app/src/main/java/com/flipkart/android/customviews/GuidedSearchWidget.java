// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.flipkart.android.response.discovery.GuideData;
import com.flipkart.android.response.discovery.GuidedSearchResponse;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

// Referenced classes of package com.flipkart.android.customviews:
//            GuideView

public class GuidedSearchWidget extends HorizontalScrollView
{

    private static final ArrayList a = new ArrayList(Arrays.asList(new String[] {
        "#e88433", "#e47272", "#43c8e0", "#e9b200", "#46be46", "#da5f34", "#d457ef", "#6e2353"
    }));
    private Context b;
    private android.view.View.OnClickListener c;
    private LinearLayout d;

    public GuidedSearchWidget(Context context, android.view.View.OnClickListener onclicklistener)
    {
        super(context, null);
        b = context;
        c = onclicklistener;
        setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(94, b)));
        BitmapDrawable bitmapdrawable = (BitmapDrawable)getResources().getDrawable(0x7f020060);
        bitmapdrawable.setTileModeXY(android.graphics.Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.REPEAT);
        setBackgroundDrawable(bitmapdrawable);
        setHorizontalScrollBarEnabled(false);
        d = new LinearLayout(b);
        d.setOrientation(0);
        d.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-2, -1));
        d.setPadding(ScreenMathUtils.dpToPx(7, b), ScreenMathUtils.dpToPx(7, b), ScreenMathUtils.dpToPx(7, b), ScreenMathUtils.dpToPx(7, b));
        d.setGravity(17);
        addView(d);
    }

    public void clearAllViews()
    {
        d.removeAllViews();
    }

    public void updateViews(GuidedSearchResponse guidedsearchresponse)
    {
        int i;
        Iterator iterator;
        if (StringUtils.isNullOrEmpty(guidedsearchresponse.getGuideDataList()))
        {
            setVisibility(8);
        } else
        {
            setVisibility(0);
        }
        i = -1;
        iterator = guidedsearchresponse.getGuideDataList().iterator();
        while (iterator.hasNext()) 
        {
            GuideData guidedata = (GuideData)iterator.next();
            int j = i + 1;
            GuideView guideview;
            if (j == a.size())
            {
                i = 0;
            } else
            {
                i = j;
            }
            guideview = new GuideView(b, guidedata, c, (String)a.get(i));
            d.addView(guideview);
        }
    }

}
