// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.cart.Cart;
import com.flipkart.android.cart.CartHandler;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.TagData;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.customwidget.ActionBarSearchTagWidget;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

// Referenced classes of package com.flipkart.android.fragments:
//            FiltersListFragment, bp, bj, bk, 
//            bl, bm, bn, bo

public class SearchListFragment extends FiltersListFragment
    implements android.view.View.OnClickListener
{

    private ActionBarSearchTagWidget b;
    private ArrayList c;

    public SearchListFragment()
    {
        c = new ArrayList();
    }

    private void a(BrowseParam browseparam)
    {
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        ArrayList arraylist = fkContext.getSpellSuggestionList();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("No products found for ");
        stringbuilder.append((new StringBuilder("\"")).append(browseparam.getQuery()).append("\"").toString());
        int i = stringbuilder.length();
        stringbuilder.append((new StringBuilder("\nShowing ")).append(fkContext.getTotalProductCount()).append(" results for ").toString());
        int j = stringbuilder.length();
        stringbuilder.append((new StringBuilder("\"")).append((String)arraylist.get(0)).append("\" ").toString());
        int k = stringbuilder.length();
        stringbuilder.append("instead.");
        int l = arraylist.size();
        int i1 = 0;
        if (l > 1)
        {
            stringbuilder.append(" \nDid you mean ");
        }
        while (arraylist.size() > i1 + 1) 
        {
            int k1 = stringbuilder.length();
            stringbuilder.append((String)arraylist.get(i1 + 1));
            bp bp2 = new bp(this, k1, stringbuilder.length(), (String)arraylist.get(i1 + 1));
            linkedhashmap.put(Integer.valueOf(i1), bp2);
            int l1 = i1 + 1;
            if (l1 + 1 < arraylist.size())
            {
                stringbuilder.append(" or ");
                i1 = l1;
            } else
            {
                stringbuilder.append(" ?");
                i1 = l1;
            }
        }
        SpannableString spannablestring = new SpannableString(stringbuilder.toString());
        int j1 = 0;
        do
        {
            try
            {
                if (j1 >= linkedhashmap.size())
                {
                    break;
                }
                bp bp1 = (bp)linkedhashmap.get(Integer.valueOf(j1));
                spannablestring.setSpan(new bj(this, browseparam, bp1), bp1.getStart(), bp1.getEnd(), 18);
            }
            catch (Exception exception)
            {
                break;
            }
            j1++;
        } while (true);
        if (i >= 0)
        {
            spannablestring.setSpan(new RelativeSizeSpan(1.35F), 0, i, 18);
        }
        if (stringbuilder.length() >= i)
        {
            spannablestring.setSpan(new RelativeSizeSpan(0.9F), i, stringbuilder.length(), 18);
        }
        if (k >= j)
        {
            spannablestring.setSpan(new StyleSpan(1), j, k, 18);
        }
        titleView.setMovementMethod(LinkMovementMethod.getInstance());
        titleView.setMovementMethod(LinkMovementMethod.getInstance());
        titleView.setText(spannablestring);
        browseparam.setQuery((String)arraylist.get(0));
        browseparam.setEnableAugmentSearch(false);
        augmentedSearchCloseButton.setVisibility(0);
        augmentedSearchCloseButton.setOnClickListener(new bk(this, browseparam));
    }

    public void appendNewActionBarTags(ArrayList arraylist)
    {
        if (b != null && !StringUtils.isNullOrEmpty(arraylist))
        {
            String s;
            for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); b.addTag(s))
            {
                s = (String)iterator.next();
            }

        }
    }

    protected void initActionBar()
    {
        ActionBarView.setActionBarCustomView(activity, ActionBarState.Search_Result_Page);
        ActionBar actionbar;
        if (fkContext == null)
        {
            searchQuery = "";
        } else
        {
            searchQuery = ((BrowseParam)fkContext.getParam()).getQuery();
        }
        actionbar = homeFragmentHolderActivity.getSupportActionBar();
        if (actionbar != null)
        {
            View view = actionbar.getCustomView();
            b = (ActionBarSearchTagWidget)actionbar.getCustomView().findViewById(0x7f0a0107);
            b.setOnClickListener(this);
            actionbar.getCustomView().findViewById(0x7f0a0106).setOnClickListener(this);
            String s = searchQuery;
            if (!StringUtils.isNullOrEmpty(c))
            {
                c.clear();
            }
            for (StringTokenizer stringtokenizer = new StringTokenizer(s); stringtokenizer.hasMoreTokens(); c.add(new TagData(stringtokenizer.nextToken(), -1 + c.size(), false))) { }
            b.updateViewsWithTags(c);
            ImageView imageview = (ImageView)view.findViewById(0x7f0a0064);
            RelativeLayout relativelayout = (RelativeLayout)view.findViewById(0x7f0a0105);
            if (imageview != null)
            {
                if (AppConfigUtils.getInstance().isEnableFlipkartFirst() && FlipkartPreferenceManager.instance().getUsersFfStatus().booleanValue())
                {
                    imageview.setImageResource(0x7f0200eb);
                }
                imageview.setOnClickListener(new bl(this));
            }
            if (relativelayout != null)
            {
                if (CartHandler.getCart().getCartItemCount() != 0)
                {
                    TextView textview = (TextView)relativelayout.findViewById(0x7f0a009e);
                    textview.setVisibility(0);
                    textview.setText((new StringBuilder()).append(CartHandler.getCart().getCartItemCount()).toString());
                }
                relativelayout.setOnClickListener(new bm(this));
            }
        }
    }

    public void onClick(View view)
    {
        if (view.getTag() instanceof TagData)
        {
            ((HomeFragmentHolderActivity)activity).openSearchPageWithQuery(requestId, searchQuery);
            return;
        }
        try
        {
            if ((view.getTag() instanceof String) && ((String)view.getTag()).equalsIgnoreCase("edit_guided_search_text"))
            {
                ((HomeFragmentHolderActivity)activity).openSearchPageWithQuery(requestId, searchQuery);
                return;
            }
        }
        catch (Exception exception) { }
        super.onClick(view);
        return;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = super.onCreateView(layoutinflater, viewgroup, bundle);
        FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.SearchListPage);
        return view;
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        analyticData.setPageTypeUtils(PageTypeUtils.SearchListPage);
    }

    public void onResume()
    {
        super.onResume();
        activity.getWindow().setSoftInputMode(3);
    }

    protected void setBrowseParamTitle(BrowseParam browseparam, boolean flag)
    {
        if (fkContext != null)
        {
            augmentedSearchCloseButton.setVisibility(8);
            if (fkContext.getAugmentedQueriesList() != null && fkContext.getAugmentedQueriesList().size() > 0)
            {
                TrackingHelper.sendAugmentedSearchShown();
                setTitlePadding(true);
                LinkedHashMap linkedhashmap = new LinkedHashMap();
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append((new StringBuilder("Showing ")).append(fkContext.getTotalProductCount()).append(" results for ").toString());
                ArrayList arraylist = fkContext.getAugmentedQueriesList();
                int i;
                int j1;
                for (i = 0; i < arraylist.size(); i = j1)
                {
                    int i1 = stringbuilder.length();
                    stringbuilder.append((String)arraylist.get(i));
                    bp bp3 = new bp(this, i1, stringbuilder.length(), (String)arraylist.get(i));
                    j1 = i + 1;
                    linkedhashmap.put(Integer.valueOf(i), bp3);
                    if (j1 < arraylist.size())
                    {
                        stringbuilder.append(" , ");
                    }
                }

                stringbuilder.append(" \nDid you mean ");
                int j = stringbuilder.length();
                stringbuilder.append(browseparam.getQuery());
                bp bp1 = new bp(this, j, stringbuilder.length(), browseparam.getQuery());
                int k = i + 1;
                linkedhashmap.put(Integer.valueOf(i), bp1);
                stringbuilder.append(" ?");
                SpannableString spannablestring = new SpannableString(stringbuilder.toString());
                for (int l = 0; l < k; l++)
                {
                    bp bp2 = (bp)linkedhashmap.get(Integer.valueOf(l));
                    spannablestring.setSpan(new bn(this, arraylist, bp2, browseparam), bp2.getStart(), bp2.getEnd(), 18);
                }

                titleView.setMovementMethod(LinkMovementMethod.getInstance());
                titleView.setText(spannablestring);
                augmentedSearchCloseButton.setVisibility(0);
                augmentedSearchCloseButton.setOnClickListener(new bo(this, arraylist, browseparam));
            } else
            if (fkContext.getSpellSuggestionList() != null && fkContext.getSpellSuggestionList().size() > 0)
            {
                TrackingHelper.sendAugmentedSearchShown();
                setTitlePadding(true);
                a(browseparam);
            } else
            {
                super.setBrowseParamTitle(browseparam, flag);
            }
            titleView.setVisibility(0);
            fkContext.setTitleViewText(titleView.getText().toString());
        }
    }
}
