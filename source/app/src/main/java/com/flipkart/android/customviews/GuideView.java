// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.response.GuidedSearchTag;
import com.flipkart.android.response.discovery.GuideData;
import com.flipkart.android.response.discovery.GuideFacet;
import com.flipkart.android.response.discovery.GuideFacetTag;
import com.flipkart.android.response.discovery.GuideFacetValue;
import com.flipkart.android.response.discovery.GuideMetaData;
import com.flipkart.android.response.discovery.GuideResource;
import com.flipkart.android.response.discovery.GuideStoreData;
import com.flipkart.android.response.discovery.GuideStoreTag;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

// Referenced classes of package com.flipkart.android.customviews:
//            CustomRobotoRegularTextView

public class GuideView extends LinearLayout
{

    private Context a;
    private TextView b;
    private TextView c;
    private TextView d;
    private GuideData e;
    private android.view.View.OnClickListener f;
    private String g;
    private String h;
    private String i;
    private String j;

    public GuideView(Context context, GuideData guidedata, android.view.View.OnClickListener onclicklistener, String s)
    {
        super(context);
        a = context;
        e = guidedata;
        f = onclicklistener;
        g = s;
        StringTokenizer stringtokenizer = new StringTokenizer(e.getMeta().getTitle());
        if (stringtokenizer.hasMoreTokens())
        {
            String s3 = stringtokenizer.nextToken();
            android.widget.LinearLayout.LayoutParams layoutparams;
            GradientDrawable gradientdrawable;
            if (s3.length() > 8)
            {
                h = (new StringBuilder()).append(s3.substring(0, 8)).append("..").toString();
            } else
            {
                h = s3;
            }
        }
        if (stringtokenizer.hasMoreTokens())
        {
            String s2 = stringtokenizer.nextToken();
            if (s2.length() > 8)
            {
                i = (new StringBuilder()).append(s2.substring(0, 8)).append("..").toString();
            } else
            {
                i = s2;
            }
        }
        if (stringtokenizer.hasMoreTokens())
        {
            String s1 = stringtokenizer.nextToken();
            if (s1.length() > 8)
            {
                j = (new StringBuilder()).append(s1.substring(0, 8)).append("..").toString();
            } else
            {
                j = s1;
            }
        }
        setGravity(17);
        layoutparams = new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(80, a), ScreenMathUtils.dpToPx(80, a));
        layoutparams.setMargins(ScreenMathUtils.dpToPx(4, a), 0, ScreenMathUtils.dpToPx(4, a), 0);
        setLayoutParams(layoutparams);
        setOrientation(1);
        gradientdrawable = new GradientDrawable();
        gradientdrawable.setCornerRadius(8F);
        gradientdrawable.setColor(Color.parseColor(g));
        setBackgroundDrawable(gradientdrawable);
        setPadding(ScreenMathUtils.dpToPx(5, a), ScreenMathUtils.dpToPx(5, a), ScreenMathUtils.dpToPx(5, a), ScreenMathUtils.dpToPx(5, a));
        b = a();
        c = a();
        d = a();
        addView(b);
        addView(c);
        addView(d);
        if (!StringUtils.isNullOrEmpty(h))
        {
            b.setText(h);
        } else
        {
            b.setVisibility(8);
        }
        if (!StringUtils.isNullOrEmpty(i))
        {
            c.setText(i);
        } else
        {
            c.setVisibility(8);
        }
        if (!StringUtils.isNullOrEmpty(j))
        {
            d.setText(j);
        } else
        {
            d.setVisibility(8);
        }
        setOnClickListener(f);
        setTag(b());
    }

    private TextView a()
    {
        CustomRobotoRegularTextView customrobotoregulartextview = new CustomRobotoRegularTextView(a, null);
        customrobotoregulartextview.setGravity(17);
        customrobotoregulartextview.setTextColor(getResources().getColor(0x7f090070));
        customrobotoregulartextview.setMaxLines(1);
        customrobotoregulartextview.setTextSize(2, 16F);
        customrobotoregulartextview.setEllipsize(android.text.TextUtils.TruncateAt.END);
        customrobotoregulartextview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
        return customrobotoregulartextview;
    }

    private GuidedSearchTag b()
    {
        GuidedSearchTag guidedsearchtag = new GuidedSearchTag();
        ArrayList arraylist2;
        if (e == null)
        {
            break MISSING_BLOCK_LABEL_345;
        }
        guidedsearchtag.setSearchKeywords(e.getSearchKeywords());
        if (e.getMeta() != null)
        {
            guidedsearchtag.setTitle(e.getMeta().getTitle());
        }
        if (e.getStoreTag() == null)
        {
            break MISSING_BLOCK_LABEL_181;
        }
        arraylist2 = e.getStoreTag().getStores();
        if (arraylist2 == null)
        {
            break MISSING_BLOCK_LABEL_181;
        }
        StringBuilder stringbuilder;
        Iterator iterator1;
        if (arraylist2.size() <= 0)
        {
            break MISSING_BLOCK_LABEL_181;
        }
        stringbuilder = new StringBuilder();
        iterator1 = arraylist2.iterator();
_L2:
        GuideStoreData guidestoredata;
        do
        {
            if (!iterator1.hasNext())
            {
                break MISSING_BLOCK_LABEL_172;
            }
            guidestoredata = (GuideStoreData)iterator1.next();
        } while (guidestoredata == null);
        if (StringUtils.isNullOrEmpty(guidestoredata.getStoreId())) goto _L2; else goto _L1
_L1:
        stringbuilder.append((new StringBuilder()).append(guidestoredata.getStoreId()).append("/").toString());
          goto _L2
        guidedsearchtag.setStorePath(stringbuilder.toString());
        ArrayList arraylist;
        ArrayList arraylist1;
        if (e.getFacetTag() == null)
        {
            break MISSING_BLOCK_LABEL_345;
        }
        arraylist = new ArrayList();
        if (e.getFacetTag().getFacet() == null)
        {
            break MISSING_BLOCK_LABEL_310;
        }
        arraylist1 = e.getFacetTag().getFacet().getValue();
        if (arraylist1 == null)
        {
            break MISSING_BLOCK_LABEL_310;
        }
        Iterator iterator;
        if (arraylist1.size() <= 0)
        {
            break MISSING_BLOCK_LABEL_310;
        }
        iterator = arraylist1.iterator();
_L4:
        GuideFacetValue guidefacetvalue;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_310;
            }
            guidefacetvalue = (GuideFacetValue)iterator.next();
        } while (guidefacetvalue == null);
        if (guidefacetvalue.getResource() == null) goto _L4; else goto _L3
_L3:
        String s = guidefacetvalue.getResource().getParams();
        if (!StringUtils.isNullOrEmpty(s))
        {
            arraylist.add(s);
        }
          goto _L4
        if (arraylist.size() <= 0)
        {
            break MISSING_BLOCK_LABEL_337;
        }
        guidedsearchtag.setFilterParams((String[])arraylist.toArray(new String[arraylist.size()]));
        return guidedsearchtag;
        guidedsearchtag.setFilterParams(null);
        return guidedsearchtag;
        Exception exception;
        exception;
        return guidedsearchtag;
    }
}
