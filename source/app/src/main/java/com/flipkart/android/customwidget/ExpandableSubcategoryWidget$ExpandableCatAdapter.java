// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.ExpandableValue;
import com.flipkart.android.response.component.data.customvalues.ImageValue;
import com.flipkart.android.utils.FontCache;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.flipkart.android.customwidget:
//            ExpandableSubcategoryWidget

class <init> extends BaseExpandableListAdapter
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
        ExpandableSubcategoryWidget.access$000(ExpandableSubcategoryWidget.this, ((WidgetItem)items.get(i)).getAction(), expandablevalue.getText());
        ExpandableSubcategoryWidget.access$100(ExpandableSubcategoryWidget.this, ((WidgetItem)items.get(i)).getAction());
        linearlayout.setTag(((WidgetItem)items.get(i)).getAction());
        linearlayout.setContentDescription((new StringBuilder("sub_category_cell_")).append(i).toString());
        if (k > 0 && j == -1 + getChildrenCount(i))
        {
            RelativeLayout relativelayout1 = (RelativeLayout)linearlayout.findViewById(0x7f0a00db);
            relativelayout1.setBackgroundColor(readMoreColor);
            TextView textview2 = (TextView)linearlayout.findViewById(0x7f0a00dd);
            textview2.setText("");
            textview2.setTextColor(ExpandableSubcategoryWidget.access$200(ExpandableSubcategoryWidget.this));
            textview2.setGravity(21);
            textview2.setContentDescription((new StringBuilder("expandable_category_cell_text_")).append(i).toString());
            TextView textview3 = (TextView)linearlayout.findViewById(0x7f0a00de);
            relativelayout1.setPadding(0, ScreenMathUtils.dpToPx(10, getContext()), 0, ScreenMathUtils.dpToPx(10, getContext()));
            textview2.setPadding(0, 0, 0, 0);
            textview3.setText("view more");
            textview3.setTextColor(ExpandableSubcategoryWidget.access$300(ExpandableSubcategoryWidget.this));
            textview3.setGravity(21);
            textview3.setCompoundDrawablesWithIntrinsicBounds(null, null, ExpandableSubcategoryWidget.access$400(ExpandableSubcategoryWidget.this), null);
            ((ImageView)linearlayout.findViewById(0x7f0a00dc)).setVisibility(8);
            return linearlayout;
        }
        relativelayout = (RelativeLayout)linearlayout.findViewById(0x7f0a00db);
        TextView textview = (TextView)linearlayout.findViewById(0x7f0a00dd);
        textview.setGravity(19);
        android.graphics.Typeface typeface = FontCache.getFont("robotolight.ttf");
        textview.setTextColor(ExpandableSubcategoryWidget.access$300(ExpandableSubcategoryWidget.this));
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
        ExpandableSubcategoryWidget.access$500(ExpandableSubcategoryWidget.this);
        textview.setText(expandablevalue.getText());
        textview1 = (TextView)linearlayout.findViewById(0x7f0a00de);
        textview1.setText("");
        if (expandablevalue.isExpandable())
        {
            if (!expandablevalue.isAutoExpand())
            {
                textview1.setCompoundDrawablesWithIntrinsicBounds(null, null, ExpandableSubcategoryWidget.access$400(ExpandableSubcategoryWidget.this), null);
            } else
            {
                textview1.setCompoundDrawablesWithIntrinsicBounds(null, null, ExpandableSubcategoryWidget.access$600(ExpandableSubcategoryWidget.this), null);
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
        if (ExpandableSubcategoryWidget.access$500(ExpandableSubcategoryWidget.this) == 1)
        {
            relativelayout.setBackgroundColor(childCellColorFlyout);
        } else
        {
            relativelayout.setBackgroundColor(childCellColor);
        }
        ExpandableSubcategoryWidget.access$000(ExpandableSubcategoryWidget.this, widgetitem.getAction(), expandablevalue.getText());
        ExpandableSubcategoryWidget.access$100(ExpandableSubcategoryWidget.this, widgetitem.getAction());
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
        if (ExpandableSubcategoryWidget.access$700(ExpandableSubcategoryWidget.this) > 0)
        {
            return 1 + (items.size() - ExpandableSubcategoryWidget.access$700(ExpandableSubcategoryWidget.this));
        }
        boolean flag = ExpandableSubcategoryWidget.access$800(ExpandableSubcategoryWidget.this);
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
        if (ExpandableSubcategoryWidget.access$700(ExpandableSubcategoryWidget.this) <= 0 || i != -1 + getGroupCount()) goto _L2; else goto _L1
_L1:
        k = ScreenMathUtils.dpToPx(1, getContext());
        linearlayout.setPadding(k, k, k, k);
        linearlayout.setBackgroundResource(0x7f0200f6);
        relativelayout2 = (RelativeLayout)linearlayout.findViewById(0x7f0a00db);
        relativelayout2.setBackgroundColor(readMoreColor);
        relativelayout2.setPadding(0, ScreenMathUtils.dpToPx(10, getContext()), 0, ScreenMathUtils.dpToPx(10, getContext()));
        textview4 = (TextView)linearlayout.findViewById(0x7f0a00dd);
        textview4.setText("");
        textview4.setTextColor(ExpandableSubcategoryWidget.access$200(ExpandableSubcategoryWidget.this));
        textview4.setGravity(21);
        textview4.setPadding(0, 0, 0, 0);
        textview5 = (TextView)linearlayout.findViewById(0x7f0a00de);
        textview5.setText("view more");
        textview5.setTextColor(ExpandableSubcategoryWidget.access$300(ExpandableSubcategoryWidget.this));
        textview4.setPadding(0, 0, 0, 0);
        textview5.setGravity(21);
        textview5.setCompoundDrawablesWithIntrinsicBounds(null, null, ExpandableSubcategoryWidget.access$400(ExpandableSubcategoryWidget.this), null);
        textview5.setCompoundDrawablePadding(ScreenMathUtils.dpToPx(10, context));
        textview5.setContentDescription((new StringBuilder("expand_icon_")).append(i).toString());
        ((ImageView)linearlayout.findViewById(0x7f0a00dc)).setVisibility(8);
_L9:
        FkLogger.debug("Expandable", "returning root view for group view.");
        return linearlayout;
_L2:
        if (ExpandableSubcategoryWidget.access$700(ExpandableSubcategoryWidget.this) != 0 || !ExpandableSubcategoryWidget.access$800(ExpandableSubcategoryWidget.this) || i != items.size()) goto _L4; else goto _L3
_L3:
        RelativeLayout relativelayout1 = (RelativeLayout)linearlayout.findViewById(0x7f0a00db);
        relativelayout1.setBackgroundColor(readMoreColor);
        TextView textview2 = (TextView)linearlayout.findViewById(0x7f0a00dd);
        textview2.setText("Search for products");
        textview2.setTextColor(ExpandableSubcategoryWidget.access$300(ExpandableSubcategoryWidget.this));
        textview2.setPadding(0, 0, 0, 0);
        relativelayout1.setPadding(0, ScreenMathUtils.dpToPx(10, getContext()), 0, ScreenMathUtils.dpToPx(10, getContext()));
        textview2.setTypeface(FontCache.getFont("robotolight.ttf"));
        TextView textview3 = (TextView)linearlayout.findViewById(0x7f0a00de);
        textview3.setText("");
        textview3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        textview2.setCompoundDrawablesWithIntrinsicBounds(ExpandableSubcategoryWidget.access$900(ExpandableSubcategoryWidget.this), null, null, null);
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
                textview1.setCompoundDrawablesWithIntrinsicBounds(null, null, ExpandableSubcategoryWidget.access$400(ExpandableSubcategoryWidget.this), null);
            } else
            {
                textview.setTypeface(null, 0);
                textview1.setCompoundDrawablesWithIntrinsicBounds(null, null, ExpandableSubcategoryWidget.access$600(ExpandableSubcategoryWidget.this), null);
            }
        } else
        if (ExpandableSubcategoryWidget.access$1000(ExpandableSubcategoryWidget.this))
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
        if (ExpandableSubcategoryWidget.access$500(ExpandableSubcategoryWidget.this) == 1)
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
            textview.setTextColor(ExpandableSubcategoryWidget.access$300(ExpandableSubcategoryWidget.this));
            relativelayout.setBackgroundColor(mainCellColorFlyout);
        }
        ExpandableSubcategoryWidget.access$000(ExpandableSubcategoryWidget.this, widgetitem.getAction(), expandablevalue.getText());
        ExpandableSubcategoryWidget.access$100(ExpandableSubcategoryWidget.this, widgetitem.getAction());
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

    private ()
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

    readMoreColorFlyout(readMoreColorFlyout readmorecolorflyout)
    {
        this();
    }
}
