// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.flipkart.android.customviews.CustomRobotoRegularTextView;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.ImageValue;
import com.flipkart.android.response.component.data.customvalues.SantaOfferValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.FontCache;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class OfferZoneWidget extends BaseWidget
    implements android.view.View.OnClickListener
{

    public static final String WIDGET_COMMON_NAME = "OFFER";
    private String requestId;

    public OfferZoneWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist, String s, 
            Activity activity, int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, i);
        requestId = s;
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
        if (arraylist == null || arraylist.size() == 0)
        {
            setVisibility(8);
            setTitleGone();
        } else
        {
            Iterator iterator = arraylist.iterator();
            int j = 0;
            while (iterator.hasNext()) 
            {
                WidgetItem widgetitem1 = (WidgetItem)iterator.next();
                if (j == -1 + arraylist.size())
                {
                    setTitle(widgetitem1, false, j);
                } else
                {
                    setTitle(widgetitem1, true, j);
                }
                j++;
            }
        }
    }

    private void addRequestIdToActionParamsExplicitly(Action action)
    {
        action.getParams().put("REQUEST_ID", requestId);
    }

    private void setHeading(Action action, int i, String s)
    {
        if (action != null)
        {
            Map map = action.getParams();
            if (map != null && s != null)
            {
                map.put("heading", s);
                map.put("position", Integer.valueOf(i));
            }
            action.setParams(map);
        }
    }

    private void setTitle(WidgetItem widgetitem, boolean flag, int i)
    {
        final CustomRobotoRegularTextView title = new CustomRobotoRegularTextView(context, null);
        title.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
        title.setContentDescription("Offer_");
        int j = ScreenMathUtils.dpToPx(5, context);
        title.setPadding(j, j, j, j);
        title.setBackgroundResource(0x7f0200f6);
        Action action = widgetitem.getAction();
        SantaOfferValue santaoffervalue = (SantaOfferValue)widgetitem.getValue();
        String s = santaoffervalue.getText();
        ImageValue imagevalue;
        String s1;
        if (s != null)
        {
            title.setText(s);
        } else
        {
            title.setText("");
        }
        if (action != null && action.getScreenType() != null && action.getScreenType().length() > 0)
        {
            setHeading(action, i + 1, s);
            addRequestIdToActionParamsExplicitly(action);
            title.setTag(action);
        } else
        {
            title.setTag(null);
        }
        imagevalue = santaoffervalue.getImage();
        if (imagevalue != null)
        {
            s1 = ImageUtils.getImageUrl(imagevalue.getImage());
        } else
        {
            s1 = null;
        }
        if (!StringUtils.isNullOrEmpty(s1))
        {
            FlipkartApplication.getImageLoader().get(s1, new _cls1());
        }
        title.setTypeface(FontCache.getFont("robotolight.ttf"));
        title.setTextColor(0xff2e2e2e);
        title.setTextSize(2, 15F);
        title.setGravity(16);
        title.setPadding(ScreenMathUtils.dpToPx(15, context), ScreenMathUtils.dpToPx(8, context), ScreenMathUtils.dpToPx(15, context), ScreenMathUtils.dpToPx(8, context));
        title.setMaxLines(2);
        title.setEllipsize(android.text.TextUtils.TruncateAt.END);
        title.setOnClickListener(onClickListener);
        title.setBackgroundColor(-1);
        addView(title);
        if (flag)
        {
            View view = new View(context);
            view.setBackgroundColor(context.getResources().getColor(0x7f090029));
            view.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(1, context)));
            addView(view);
        }
    }

    public void onClick(View view)
    {
    }

    private class _cls1
        implements com.android.volley.toolbox.ImageLoader.ImageListener
    {

        final OfferZoneWidget this$0;
        final TextView val$title;

        public void onErrorResponse(VolleyError volleyerror)
        {
        }

        public void onResponse(com.android.volley.toolbox.ImageLoader.ImageContainer imagecontainer, boolean flag)
        {
            if (imagecontainer.getBitmap() != null && title != null)
            {
                title.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(context.getResources(), imagecontainer.getBitmap()), null, context.getResources().getDrawable(0x7f02005d), null);
                title.setCompoundDrawablePadding(ScreenMathUtils.dpToPx(5, context));
            }
        }

        _cls1()
        {
            this$0 = OfferZoneWidget.this;
            title = textview;
            super();
        }
    }

}
