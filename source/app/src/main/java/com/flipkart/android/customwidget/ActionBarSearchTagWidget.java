// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.flipkart.android.customviews.CustomSearchTag;
import com.flipkart.android.customviews.TagData;
import com.flipkart.android.utils.ScreenMathUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class ActionBarSearchTagWidget extends HorizontalScrollView
{

    private Context context;
    private LinearLayout customSearchTagLL;
    private android.view.View.OnClickListener listener;
    private LinearLayout rootLinearLayout;
    private View searchEditText;
    private ArrayList tagList;

    public ActionBarSearchTagWidget(Context context1)
    {
        this(context1, null);
    }

    public ActionBarSearchTagWidget(Context context1, AttributeSet attributeset)
    {
        super(context1, attributeset);
        context = context1;
        setHorizontalScrollBarEnabled(false);
        initViews();
    }

    private void drawView()
    {
        if (customSearchTagLL != null)
        {
            customSearchTagLL.removeAllViews();
        }
        CustomSearchTag customsearchtag;
        for (Iterator iterator = tagList.iterator(); iterator.hasNext(); customSearchTagLL.addView(customsearchtag))
        {
            TagData tagdata = (TagData)iterator.next();
            customsearchtag = new CustomSearchTag(context, tagdata, listener);
        }

        if (customSearchTagLL.getParent() != null)
        {
            ((ViewGroup)customSearchTagLL.getParent()).removeView(customSearchTagLL);
        }
        if (searchEditText.getParent() != null)
        {
            ((ViewGroup)searchEditText.getParent()).removeView(searchEditText);
        }
        rootLinearLayout.addView(customSearchTagLL);
        rootLinearLayout.addView(searchEditText);
    }

    private void initViews()
    {
        rootLinearLayout = new LinearLayout(context);
        rootLinearLayout.setOrientation(0);
        rootLinearLayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        customSearchTagLL = new LinearLayout(context);
        customSearchTagLL.setOrientation(0);
        customSearchTagLL.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1, 0.0F));
        searchEditText = new View(context);
        searchEditText.setTag("edit_guided_search_text");
        searchEditText.setLayoutParams(new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(30, context), -1, 1.0F));
        addView(rootLinearLayout);
    }

    public void addTag(String s)
    {
        TagData tagdata = new TagData(s, tagList.size(), false);
        tagList.add(tagdata);
        CustomSearchTag customsearchtag = new CustomSearchTag(context, tagdata, listener);
        customSearchTagLL.addView(customsearchtag);
    }

    public void setOnClickListener(android.view.View.OnClickListener onclicklistener)
    {
        listener = onclicklistener;
        searchEditText.setOnClickListener(onclicklistener);
    }

    public void updateViewsWithTags(ArrayList arraylist)
    {
        tagList = arraylist;
        drawView();
    }
}
