// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.ExpandableValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.MiscScreenUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class ExpandableSubcategoryWidget extends BaseWidget
{

    public static final int TYPE_CLP_LIST = 2;
    public static final int TYPE_FLYOUT_LIST = 1;
    public static final String WIDGET_COMMON_NAME = "EXPANDABLE";
    ExpandableCatAdapter adapter;
    private Drawable category_collapse;
    private Drawable category_expand;
    private int expandableCellTextColor;
    private int expandableCellTextColorMore;
    ExpandableListView expandableListView;
    private boolean isShowArrow;
    ArrayList items;
    private String requestId;
    private int rootReadMoreCount;
    private Drawable search_icon;
    private boolean showSearch;
    private int viewType;

    public ExpandableSubcategoryWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, List list, int i, 
            boolean flag, String s, boolean flag1, Activity activity, int j)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, j);
        rootReadMoreCount = 0;
        viewType = 2;
        showSearch = false;
        isShowArrow = false;
        expandableCellTextColor = 0xff000000;
        expandableCellTextColorMore = 0xff333333;
        items = new ArrayList();
        adapter = null;
        convertToExpandableViewItem(list);
        viewType = i;
        isShowArrow = flag1;
        requestId = s;
        doInitializingTask(flag);
    }

    private void addRequestIdToActionParamsExplicitly(Action action)
    {
        action.getParams().put("REQUEST_ID", requestId);
    }

    private void convertToExpandableViewItem(List list)
    {
        if (StringUtils.isNullOrEmpty(list))
        {
            setVisibility(8);
            return;
        }
        for (Iterator iterator = list.iterator(); iterator.hasNext(); FkLogger.debug("Expandable", (new StringBuilder("Items is ")).append(items.size()).toString()))
        {
            WidgetItem widgetitem = (WidgetItem)iterator.next();
            if (widgetitem != null)
            {
                initializeChildViews(widgetitem);
            }
            sortItems(items);
        }

        if (theme != null && theme.equals(BaseWidget.WidgetTheme.dark))
        {
            expandableCellTextColor = -1;
            expandableCellTextColorMore = 0xffdddddd;
            category_collapse = getResources().getDrawable(0x7f020087).getConstantState().newDrawable();
            category_collapse = category_collapse.mutate();
            category_collapse.setColorFilter(getResources().getColor(0x7f090070), android.graphics.PorterDuff.Mode.SRC_ATOP);
            category_expand = getResources().getDrawable(0x7f020088).getConstantState().newDrawable();
            category_expand = category_expand.mutate();
            category_expand.setColorFilter(getResources().getColor(0x7f090070), android.graphics.PorterDuff.Mode.SRC_ATOP);
            search_icon = getResources().getDrawable(0x7f02014f);
            return;
        } else
        {
            expandableCellTextColor = 0xff000000;
            expandableCellTextColorMore = 0xff222222;
            category_collapse = getResources().getDrawable(0x7f020087);
            category_expand = getResources().getDrawable(0x7f020088);
            search_icon = getResources().getDrawable(0x7f02014f).getConstantState().newDrawable();
            search_icon = search_icon.mutate();
            search_icon.setColorFilter(getResources().getColor(0x7f090006), android.graphics.PorterDuff.Mode.SRC_ATOP);
            return;
        }
    }

    private void doInitializingTask(boolean flag)
    {
        expandableListView = new ExpandableListView(context);
        addView(expandableListView);
        if (layoutDetails != null)
        {
            layoutDetails.getPaddingInInt();
        }
        expandableListView.setVerticalScrollBarEnabled(false);
        expandableListView.setHorizontalScrollBarEnabled(false);
        showSearch = flag;
        Iterator iterator;
        int i;
        if (viewType == 1)
        {
            expandableListView.setDivider(new ColorDrawable(getContext().getResources().getColor(0x7f090046)));
            expandableListView.setDividerHeight(ScreenMathUtils.dpToPx(1, getContext()));
            expandableListView.setChildDivider(new ColorDrawable(getContext().getResources().getColor(0x7f090047)));
            expandableListView.setGroupIndicator(null);
        } else
        {
            if (layoutDetails != null && layoutDetails.getTheme() != null && layoutDetails.getTheme().equals("light"))
            {
                expandableListView.setDivider(new ColorDrawable(getContext().getResources().getColor(0x7f090028)));
            } else
            {
                expandableListView.setDivider(new ColorDrawable(getContext().getResources().getColor(0x7f090046)));
            }
            expandableListView.setDividerHeight(ScreenMathUtils.dpToPx(1, getContext()));
            expandableListView.setChildDivider(new ColorDrawable(getContext().getResources().getColor(0x7f090047)));
            expandableListView.setGroupIndicator(null);
        }
        adapter = new ExpandableCatAdapter(null);
        FkLogger.debug("flyout", (new StringBuilder(" list of itesms is ")).append(items.size()).toString());
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new _cls2());
        expandableListView.setOnGroupCollapseListener(new _cls3());
        expandableListView.setOnGroupClickListener(new _cls4());
        expandableListView.setOnChildClickListener(new _cls5());
        if (viewType == 1)
        {
            MiscScreenUtils.setListViewHeightBasedOnChildren(expandableListView, adapter, true);
        } else
        {
            MiscScreenUtils.setListViewHeightBasedOnChildren(expandableListView, adapter, false);
        }
        iterator = items.iterator();
        for (i = 0; iterator.hasNext(); i++)
        {
            ExpandableValue expandablevalue = (ExpandableValue)((WidgetItem)iterator.next()).getValue();
            if (expandablevalue.isAutoExpand() && (rootReadMoreCount <= 0 || !expandablevalue.isShowInViewMore()))
            {
                expandableListView.expandGroup(i);
            }
        }

        FkLogger.debug("Expandable", (new StringBuilder(" expandable values ")).append(i).toString());
    }

    private void initializeChildViews(WidgetItem widgetitem)
    {
        ExpandableValue expandablevalue = (ExpandableValue)widgetitem.getValue();
        if (expandablevalue != null)
        {
            if (expandablevalue.isShowInViewMore())
            {
                rootReadMoreCount = 1 + rootReadMoreCount;
            }
            List list = expandablevalue.getItems();
            if (list == null || list.size() == 0)
            {
                expandablevalue.setChildViewMoreCount(0);
            } else
            {
                Iterator iterator = list.iterator();
                int i = 0;
                while (iterator.hasNext()) 
                {
                    int j;
                    if (((ExpandableValue)((WidgetItem)iterator.next()).getValue()).isShowInViewMore())
                    {
                        j = i + 1;
                    } else
                    {
                        j = i;
                    }
                    i = j;
                }
                expandablevalue.setChildViewMoreCount(i);
            }
        }
        items.add(widgetitem);
    }

    private void setFlyoutHeaderView()
    {
        RelativeLayout relativelayout = (RelativeLayout)((LinearLayout)View.inflate(getContext(), 0x7f030042, null)).findViewById(0x7f0a00db);
        relativelayout.setBackgroundColor(getContext().getResources().getColor(0x7f090041));
        TextView textview = (TextView)relativelayout.findViewById(0x7f0a00dd);
        textview.setTextSize(2, 20F);
        textview.setText("Shop by Department");
        textview.setContentDescription("ShopByDepts");
        ((TextView)relativelayout.findViewById(0x7f0a00de)).setVisibility(8);
        ((ImageView)relativelayout.findViewById(0x7f0a00dc)).setVisibility(8);
    }

    private void setHeading(Action action, String s)
    {
        if (action != null && !StringUtils.isNullOrEmpty(s))
        {
            Map map = action.getParams();
            if (map != null)
            {
                map.put("heading", s);
                action.setParams(map);
            }
        }
    }

    private void sortItems(ArrayList arraylist)
    {
        Collections.sort(arraylist, new _cls1());
    }

    public void updateWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist)
    {
        super.updateWidget(context, layoutdetails, widgettheme, onclicklistener, widgetitem, arraylist);
        FkLogger.debug("Expandable", "updte widget");
        removeAllViews();
        convertToExpandableViewItem(arraylist);
        doInitializingTask(showSearch);
    }











/*
    static int access$702(ExpandableSubcategoryWidget expandablesubcategorywidget, int i)
    {
        expandablesubcategorywidget.rootReadMoreCount = i;
        return i;
    }

*/



    private class ExpandableCatAdapter extends BaseExpandableListAdapter
    {

        int childCellColor;
        int childCellColorFlyout;
        int mainCellColor;
        int mainCellColorFlyout;
        int readMoreColor;
        int readMoreColorFlyout;
        final ExpandableSubcategoryWidget this$0;

        public Object getChild(int i, int j)
        {
            return null;
        }

        public long getChildId(int i, int j)
        {
            return 0L;
        }

        public View getChildView(int i, int j, boolean flag, View view, ViewGroup viewgroup)
        {
            RelativeLayout relativelayout;
            NetworkImageView networkimageview;
            WidgetItem widgetitem = (WidgetItem)((ExpandableValue)((WidgetItem)items.get(i)).getValue()).getItems().get(j);
            ExpandableValue expandablevalue = (ExpandableValue)widgetitem.getValue();
            int k = expandablevalue.getChildViewMoreCount();
            LinearLayout linearlayout;
            int l;
            if (view == null)
            {
                linearlayout = (LinearLayout)View.inflate(getContext(), 0x7f030042, null);
            } else
            {
                linearlayout = (LinearLayout)view;
            }
            l = ScreenMathUtils.dpToPx(1, getContext());
            linearlayout.setPadding(l, l, l, l);
            linearlayout.setBackgroundResource(0x7f0200f6);
            setHeading(((WidgetItem)items.get(i)).getAction(), expandablevalue.getText());
            addRequestIdToActionParamsExplicitly(((WidgetItem)items.get(i)).getAction());
            linearlayout.setTag(((WidgetItem)items.get(i)).getAction());
            linearlayout.setContentDescription((new StringBuilder("sub_category_cell_")).append(i).toString());
            if (k > 0 && j == -1 + getChildrenCount(i))
            {
                RelativeLayout relativelayout1 = (RelativeLayout)linearlayout.findViewById(0x7f0a00db);
                relativelayout1.setBackgroundColor(readMoreColor);
                TextView textview2 = (TextView)linearlayout.findViewById(0x7f0a00dd);
                textview2.setText("");
                textview2.setTextColor(expandableCellTextColorMore);
                textview2.setGravity(21);
                textview2.setContentDescription((new StringBuilder("expandable_category_cell_text_")).append(i).toString());
                TextView textview3 = (TextView)linearlayout.findViewById(0x7f0a00de);
                relativelayout1.setPadding(0, ScreenMathUtils.dpToPx(10, getContext()), 0, ScreenMathUtils.dpToPx(10, getContext()));
                textview2.setPadding(0, 0, 0, 0);
                textview3.setText("view more");
                textview3.setTextColor(expandableCellTextColor);
                textview3.setGravity(21);
                textview3.setCompoundDrawablesWithIntrinsicBounds(null, null, category_expand, null);
                ((ImageView)linearlayout.findViewById(0x7f0a00dc)).setVisibility(8);
                return linearlayout;
            }
            relativelayout = (RelativeLayout)linearlayout.findViewById(0x7f0a00db);
            TextView textview = (TextView)linearlayout.findViewById(0x7f0a00dd);
            textview.setGravity(19);
            android.graphics.Typeface typeface = FontCache.getFont("robotolight.ttf");
            textview.setTextColor(expandableCellTextColor);
            textview.setTextSize(2, 14F);
            textview.setTypeface(typeface);
            TextView textview1;
            String s;
            if (expandablevalue.isNew())
            {
                textview.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f020117, 0);
                textview.setCompoundDrawablePadding(ScreenMathUtils.dpToPx(5, context));
            } else
            {
                textview.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                textview.setCompoundDrawablePadding(0);
            }
            viewType;
            textview.setText(expandablevalue.getText());
            textview1 = (TextView)linearlayout.findViewById(0x7f0a00de);
            textview1.setText("");
            if (expandablevalue.isExpandable())
            {
                if (!expandablevalue.isAutoExpand())
                {
                    textview1.setCompoundDrawablesWithIntrinsicBounds(null, null, category_expand, null);
                } else
                {
                    textview1.setCompoundDrawablesWithIntrinsicBounds(null, null, category_collapse, null);
                }
            } else
            {
                textview1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            networkimageview = (NetworkImageView)linearlayout.findViewById(0x7f0a00dc);
            if (expandablevalue.getImage() == null)
            {
                break MISSING_BLOCK_LABEL_754;
            }
            s = ImageUtils.getImageUrl(expandablevalue.getImage().getImage());
            if (StringUtils.isNullOrEmpty(s))
            {
                break MISSING_BLOCK_LABEL_754;
            }
            networkimageview.setVisibility(0);
            networkimageview.setImageUrl(s, FlipkartApplication.getImageLoader());
_L1:
            if (viewType == 1)
            {
                relativelayout.setBackgroundColor(childCellColorFlyout);
            } else
            {
                relativelayout.setBackgroundColor(childCellColor);
            }
            setHeading(widgetitem.getAction(), expandablevalue.getText());
            addRequestIdToActionParamsExplicitly(widgetitem.getAction());
            linearlayout.setTag(widgetitem.getAction());
            return linearlayout;
            networkimageview.setVisibility(4);
              goto _L1
        }

        public int getChildrenCount(int i)
        {
            int j = items.size();
            int k = 0;
            if (i < j)
            {
                ExpandableValue expandablevalue = (ExpandableValue)((WidgetItem)items.get(i)).getValue();
                int l = expandablevalue.getChildViewMoreCount();
                if (l > 0)
                {
                    int i1;
                    if (expandablevalue.getItems() == null)
                    {
                        i1 = 0;
                    } else
                    {
                        i1 = expandablevalue.getItems().size();
                    }
                    k = 1 + (i1 - l);
                } else
                {
                    List list = expandablevalue.getItems();
                    k = 0;
                    if (list != null)
                    {
                        return expandablevalue.getItems().size();
                    }
                }
            }
            return k;
        }

        public Object getGroup(int i)
        {
            return null;
        }

        public int getGroupCount()
        {
            if (items == null)
            {
                return 0;
            }
            if (rootReadMoreCount > 0)
            {
                return 1 + (items.size() - rootReadMoreCount);
            }
            boolean flag = showSearch;
            int i = 0;
            if (flag)
            {
                i = 1;
            }
            return i + items.size();
        }

        public long getGroupId(int i)
        {
            return 0L;
        }

        public View getGroupView(int i, boolean flag, View view, ViewGroup viewgroup)
        {
            LinearLayout linearlayout;
            int k;
            RelativeLayout relativelayout2;
            TextView textview4;
            TextView textview5;
            if (view == null)
            {
                linearlayout = (LinearLayout)View.inflate(getContext(), 0x7f030042, null);
            } else
            {
                linearlayout = (LinearLayout)view;
            }
            FkLogger.debug("Expandable", "returning root view for group view. top");
            linearlayout.setContentDescription((new StringBuilder("expandable_category_cell_")).append(i).toString());
            if (rootReadMoreCount <= 0 || i != -1 + getGroupCount()) goto _L2; else goto _L1
_L1:
            k = ScreenMathUtils.dpToPx(1, getContext());
            linearlayout.setPadding(k, k, k, k);
            linearlayout.setBackgroundResource(0x7f0200f6);
            relativelayout2 = (RelativeLayout)linearlayout.findViewById(0x7f0a00db);
            relativelayout2.setBackgroundColor(readMoreColor);
            relativelayout2.setPadding(0, ScreenMathUtils.dpToPx(10, getContext()), 0, ScreenMathUtils.dpToPx(10, getContext()));
            textview4 = (TextView)linearlayout.findViewById(0x7f0a00dd);
            textview4.setText("");
            textview4.setTextColor(expandableCellTextColorMore);
            textview4.setGravity(21);
            textview4.setPadding(0, 0, 0, 0);
            textview5 = (TextView)linearlayout.findViewById(0x7f0a00de);
            textview5.setText("view more");
            textview5.setTextColor(expandableCellTextColor);
            textview4.setPadding(0, 0, 0, 0);
            textview5.setGravity(21);
            textview5.setCompoundDrawablesWithIntrinsicBounds(null, null, category_expand, null);
            textview5.setCompoundDrawablePadding(ScreenMathUtils.dpToPx(10, context));
            textview5.setContentDescription((new StringBuilder("expand_icon_")).append(i).toString());
            ((ImageView)linearlayout.findViewById(0x7f0a00dc)).setVisibility(8);
_L9:
            FkLogger.debug("Expandable", "returning root view for group view.");
            return linearlayout;
_L2:
            if (rootReadMoreCount != 0 || !showSearch || i != items.size()) goto _L4; else goto _L3
_L3:
            RelativeLayout relativelayout1 = (RelativeLayout)linearlayout.findViewById(0x7f0a00db);
            relativelayout1.setBackgroundColor(readMoreColor);
            TextView textview2 = (TextView)linearlayout.findViewById(0x7f0a00dd);
            textview2.setText("Search for products");
            textview2.setTextColor(expandableCellTextColor);
            textview2.setPadding(0, 0, 0, 0);
            relativelayout1.setPadding(0, ScreenMathUtils.dpToPx(10, getContext()), 0, ScreenMathUtils.dpToPx(10, getContext()));
            textview2.setTypeface(FontCache.getFont("robotolight.ttf"));
            TextView textview3 = (TextView)linearlayout.findViewById(0x7f0a00de);
            textview3.setText("");
            textview3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            textview2.setCompoundDrawablesWithIntrinsicBounds(search_icon, null, null, null);
            textview2.setCompoundDrawablePadding(ScreenMathUtils.dpToPx(10, context));
            ((NetworkImageView)linearlayout.findViewById(0x7f0a00dc)).setVisibility(8);
            linearlayout.setTag("open_search_page");
              goto _L5
_L4:
            if (i >= items.size()) goto _L5; else goto _L6
_L6:
            RelativeLayout relativelayout;
            TextView textview;
            ExpandableValue expandablevalue;
            NetworkImageView networkimageview;
            WidgetItem widgetitem = (WidgetItem)items.get(i);
            relativelayout = (RelativeLayout)linearlayout.findViewById(0x7f0a00db);
            textview = (TextView)linearlayout.findViewById(0x7f0a00dd);
            textview.setContentDescription((new StringBuilder("expandable_category_cell_text_")).append(i).toString());
            expandablevalue = (ExpandableValue)widgetitem.getValue();
            TextView textview1;
            android.graphics.Typeface typeface;
            if (expandablevalue.isNew())
            {
                textview.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f020117, 0);
                textview.setCompoundDrawablePadding(ScreenMathUtils.dpToPx(5, context));
            } else
            {
                textview.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                textview.setCompoundDrawablePadding(0);
            }
            textview.setText(expandablevalue.getText());
            textview.setGravity(19);
            textview1 = (TextView)linearlayout.findViewById(0x7f0a00de);
            textview1.setText("");
            textview1.setContentDescription((new StringBuilder("expand_icon_")).append(i).toString());
            typeface = FontCache.getFont("robotolight.ttf");
            textview.setTypeface(typeface);
            if (expandablevalue.isExpandable())
            {
                if (!expandablevalue.isAutoExpand())
                {
                    textview.setTypeface(typeface);
                    textview1.setCompoundDrawablesWithIntrinsicBounds(null, null, category_expand, null);
                } else
                {
                    textview.setTypeface(null, 0);
                    textview1.setCompoundDrawablesWithIntrinsicBounds(null, null, category_collapse, null);
                }
            } else
            if (isShowArrow)
            {
                textview1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f02005d, 0);
            } else
            {
                textview1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            networkimageview = (NetworkImageView)linearlayout.findViewById(0x7f0a00dc);
            if (expandablevalue.getImage() != null && !expandablevalue.isHeading()) goto _L8; else goto _L7
_L7:
            networkimageview.setVisibility(8);
_L10:
            String s;
            if (viewType == 1)
            {
                relativelayout.setBackgroundColor(mainCellColorFlyout);
            } else
            {
                relativelayout.setBackgroundColor(mainCellColor);
            }
            if (expandablevalue.isHeading())
            {
                relativelayout.setPadding(0, 0, 0, 0);
                textview.setPadding(0, 0, 0, 0);
                textview.setTextSize(2, 13F);
                textview.setGravity(21);
                textview.setTextColor(0xff454545);
                textview.setText(expandablevalue.getText());
                relativelayout.setBackgroundColor(0xffe7e7e7);
            } else
            {
                int j = ScreenMathUtils.dpToPx(1, getContext());
                linearlayout.setPadding(j, j, j, j);
                linearlayout.setBackgroundResource(0x7f0200f6);
                relativelayout.setPadding(0, ScreenMathUtils.dpToPx(10, getContext()), 0, ScreenMathUtils.dpToPx(10, getContext()));
                textview.setPadding(0, 0, 0, 0);
                textview.setTextSize(2, 15F);
                textview.setTextColor(expandableCellTextColor);
                relativelayout.setBackgroundColor(mainCellColorFlyout);
            }
            setHeading(widgetitem.getAction(), expandablevalue.getText());
            addRequestIdToActionParamsExplicitly(widgetitem.getAction());
            linearlayout.setTag(widgetitem.getAction());
_L5:
            if (true) goto _L9; else goto _L8
_L8:
            s = ImageUtils.getImageUrl(expandablevalue.getImage().getImage());
            if (!StringUtils.isNullOrEmpty(s))
            {
                networkimageview.setVisibility(0);
                networkimageview.setImageUrl(s, FlipkartApplication.getImageLoader());
            } else
            {
                networkimageview.setVisibility(8);
            }
              goto _L10
        }

        public boolean hasStableIds()
        {
            return false;
        }

        public boolean isChildSelectable(int i, int j)
        {
            return true;
        }

        private ExpandableCatAdapter()
        {
            this$0 = ExpandableSubcategoryWidget.this;
            super();
            mainCellColor = getContext().getResources().getColor(0x7f090044);
            childCellColor = getContext().getResources().getColor(0x7f090045);
            readMoreColor = getContext().getResources().getColor(0x7f090044);
            mainCellColorFlyout = getContext().getResources().getColor(0x7f090044);
            childCellColorFlyout = getContext().getResources().getColor(0x7f090045);
            readMoreColorFlyout = getContext().getResources().getColor(0x7f090044);
        }

        ExpandableCatAdapter(_cls1 _pcls1)
        {
            this();
        }
    }


    private class _cls2
        implements android.widget.ExpandableListView.OnGroupExpandListener
    {

        final ExpandableSubcategoryWidget this$0;

        public void onGroupExpand(int i)
        {
            if (viewType == 1)
            {
                MiscScreenUtils.setListViewHeightBasedOnChildren(expandableListView, adapter, true);
                return;
            } else
            {
                MiscScreenUtils.setListViewHeightBasedOnChildren(expandableListView, adapter, false);
                return;
            }
        }

        _cls2()
        {
            this$0 = ExpandableSubcategoryWidget.this;
            super();
        }
    }


    private class _cls3
        implements android.widget.ExpandableListView.OnGroupCollapseListener
    {

        final ExpandableSubcategoryWidget this$0;

        public void onGroupCollapse(int i)
        {
            if (viewType == 1)
            {
                MiscScreenUtils.setListViewHeightBasedOnChildren(expandableListView, adapter, true);
                return;
            } else
            {
                MiscScreenUtils.setListViewHeightBasedOnChildren(expandableListView, adapter, false);
                return;
            }
        }

        _cls3()
        {
            this$0 = ExpandableSubcategoryWidget.this;
            super();
        }
    }


    private class _cls4
        implements android.widget.ExpandableListView.OnGroupClickListener
    {

        final ExpandableSubcategoryWidget this$0;

        public boolean onGroupClick(ExpandableListView expandablelistview, View view, int i, long l)
        {
            if (rootReadMoreCount > 0 && i == -1 + adapter.getGroupCount())
            {
                rootReadMoreCount = 0;
                adapter.notifyDataSetChanged();
                if (viewType == 1)
                {
                    MiscScreenUtils.setListViewHeightBasedOnChildren(expandableListView, adapter, true);
                    return true;
                } else
                {
                    TrackingHelper.sendViewMoreOnClpClicked();
                    MiscScreenUtils.setListViewHeightBasedOnChildren(expandableListView, adapter, false);
                    return true;
                }
            }
            if (showSearch && i == items.size()) goto _L2; else goto _L1
_L1:
            if (!((ExpandableValue)((WidgetItem)items.get(i)).getValue()).isExpandable()) goto _L4; else goto _L3
_L3:
            ExpandableValue expandablevalue = (ExpandableValue)((WidgetItem)items.get(i)).getValue();
            boolean flag;
            if (!((ExpandableValue)((WidgetItem)items.get(i)).getValue()).isAutoExpand())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            expandablevalue.setAutoExpand(flag);
_L6:
            return false;
_L4:
            if (viewType == 1)
            {
                TrackingHelper.setProductFindingMethod(ProductFindingMethod.Flyout.name());
            }
_L2:
            onClickListener.onClick(view);
            if (true) goto _L6; else goto _L5
_L5:
        }

        _cls4()
        {
            this$0 = ExpandableSubcategoryWidget.this;
            super();
        }
    }


    private class _cls5
        implements android.widget.ExpandableListView.OnChildClickListener
    {

        final ExpandableSubcategoryWidget this$0;

        public boolean onChildClick(ExpandableListView expandablelistview, View view, int i, int j, long l)
        {
            ExpandableValue expandablevalue = (ExpandableValue)((WidgetItem)items.get(i)).getValue();
            if (expandablevalue.getChildViewMoreCount() > 0 && j == -1 + adapter.getChildrenCount(i))
            {
                expandablevalue.setChildViewMoreCount(0);
                adapter.notifyDataSetChanged();
                return false;
            }
            if (viewType == 1)
            {
                TrackingHelper.setProductFindingMethod(ProductFindingMethod.Flyout.name());
            }
            onClickListener.onClick(view);
            return false;
        }

        _cls5()
        {
            this$0 = ExpandableSubcategoryWidget.this;
            super();
        }
    }


    private class _cls1
        implements Comparator
    {

        final ExpandableSubcategoryWidget this$0;

        public int compare(WidgetItem widgetitem, WidgetItem widgetitem1)
        {
            ExpandableValue expandablevalue = (ExpandableValue)widgetitem.getValue();
            ExpandableValue expandablevalue1 = (ExpandableValue)widgetitem1.getValue();
            if (!expandablevalue.isShowInViewMore())
            {
                return !expandablevalue1.isShowInViewMore() ? 0 : -1;
            }
            return !expandablevalue1.isShowInViewMore() ? 1 : 0;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((WidgetItem)obj, (WidgetItem)obj1);
        }

        _cls1()
        {
            this$0 = ExpandableSubcategoryWidget.this;
            super();
        }
    }

}
