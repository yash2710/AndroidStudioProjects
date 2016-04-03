// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.CustomRobotoLightTextView;
import com.flipkart.android.customviews.CustomRobotoRegularTextView;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.OfferTermsVDataHandler;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.SiteWideOfferValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.flipkart.android.customwidget:
//            ScrollWidget

public class SiteWideOffersWidget extends ScrollWidget
{

    private static int DIALOG_CREATE = 0;
    private static int DIALOG_UPDATE = 0;
    public static final String WIDGET_COMMON_NAME = "SITE_WIDE_OFFERS";
    private int buttonColor;
    private android.view.View.OnClickListener clickListener;
    private Dialog dialog;
    private LayoutInflater layoutInflater;
    private int moreViewSize;
    private int moreViewWidth;
    private LinearLayout offerContent;
    private String offerDetailText;
    private OfferTermsVDataHandler offerTermHandler;
    private Map offerTermsDetails;
    private View siteWideOffersView;
    private int textColor;

    public SiteWideOffersWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, List list, int i, 
            boolean flag, String s, boolean flag1, Activity activity, String s1, int j)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, false, false, activity, j);
        moreViewSize = 14;
        moreViewWidth = ScreenMathUtils.dpToPx(62, FlipkartApplication.getAppContext());
        offerDetailText = null;
        clickListener = new _cls1();
        if (theme != null && theme.equals(BaseWidget.WidgetTheme.dark))
        {
            textColor = context.getResources().getColor(0x7f090070);
            buttonColor = 0xaa171717;
        } else
        {
            textColor = context.getResources().getColor(0x7f090006);
            buttonColor = 0xaabbbbbb;
        }
        layoutInflater = LayoutInflater.from(context);
        if (!s1.equals("homepage"))
        {
            initOffers(list);
        }
        initDataHandlers();
    }

    private void buildOffersLayout(String s, String s1, String s2, String s3)
    {
        int i = ScreenMathUtils.dpToPx(10, context);
        LinearLayout linearlayout = new LinearLayout(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
        layoutparams.bottomMargin = i;
        layoutparams.rightMargin = i;
        layoutparams.leftMargin = i;
        linearlayout.setLayoutParams(layoutparams);
        linearlayout.setBackgroundDrawable(getResources().getDrawable(0x7f020160));
        linearlayout.setOrientation(1);
        linearlayout.setPadding(i, i, i, i);
        linearlayout.addView(getTextView(s, 16, true));
        TextView textview = getTextView(s1, 12, false);
        textview.setMaxLines(2);
        textview.setSingleLine(false);
        linearlayout.addView(textview);
        TextView textview1 = (TextView)layoutInflater.inflate(0x7f03004c, null);
        textview1.setTextColor(Color.parseColor("#1c87b9"));
        textview1.setPadding(0, 0, 0, 0);
        textview1.setTextSize(2, 14F);
        if (textview1 != null)
        {
            textview1.setText("View Terms & Conditions");
        }
        textview1.setTag((new StringBuilder("offer_terms/")).append(s2).toString());
        linearlayout.addView(textview1);
        textview1.setOnClickListener(clickListener);
        addView(linearlayout);
    }

    private TextView getTextView(String s, int i, boolean flag)
    {
        Object obj;
        if (flag)
        {
            obj = new CustomRobotoRegularTextView(context, null);
        } else
        {
            obj = new CustomRobotoLightTextView(context, null);
        }
        ((TextView) (obj)).setText(s);
        ((TextView) (obj)).setTextSize(i);
        ((TextView) (obj)).setTextColor(getResources().getColor(0x7f090054));
        ((TextView) (obj)).setSingleLine(true);
        ((TextView) (obj)).setEllipsize(android.text.TextUtils.TruncateAt.END);
        return ((TextView) (obj));
    }

    private void initDataHandlers()
    {
        offerTermHandler = new _cls2();
    }

    private void initOffers(List list)
    {
        if (list != null && list.size() > 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                WidgetItem widgetitem = (WidgetItem)list.get(i);
                if (widgetitem == null)
                {
                    continue;
                }
                SiteWideOfferValue sitewideoffervalue = (SiteWideOfferValue)widgetitem.getValue();
                com.flipkart.android.response.component.data.customvalues.Action action = widgetitem.getAction();
                if (sitewideoffervalue != null && action != null)
                {
                    buildOffersLayout(sitewideoffervalue.getTitle(), sitewideoffervalue.getDescription(), sitewideoffervalue.getOfferId(), sitewideoffervalue.getOfferType());
                }
            }

        }
    }

    public void createOfferTermsDialog(int i)
    {
        LinearLayout linearlayout;
        if (i == DIALOG_UPDATE)
        {
            linearlayout = (LinearLayout)layoutInflater.inflate(0x7f030066, null);
            ((TextView)linearlayout.findViewById(0x7f0a0053)).setText("Offer Terms & Conditions");
            WebView webview = (WebView)linearlayout.findViewById(0x7f0a00b9);
            String s = StringUtils.getHtmlTextWithCss(offerDetailText);
            if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() < 11)
            {
                s = offerDetailText;
            }
            webview.setVisibility(0);
            if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 14)
            {
                webview.getSettings().setTextZoom(95);
            } else
            {
                webview.getSettings().setTextSize(android.webkit.WebSettings.TextSize.NORMAL);
            }
            webview.loadData(s, "text/html", "UTF-8");
        } else
        {
            linearlayout = null;
        }
        if (i == DIALOG_CREATE)
        {
            linearlayout = (LinearLayout)layoutInflater.inflate(0x7f030066, null);
            linearlayout.removeViewAt(1);
            linearlayout.addView(layoutInflater.inflate(0x7f03005d, null), 1);
            if (dialog == null)
            {
                dialog = new Dialog(activity);
                dialog.requestWindowFeature(1);
            }
            dialog.show();
        }
        dialog.setContentView(linearlayout);
        ((CustomRobotoRegularTextView)linearlayout.findViewById(0x7f0a0139)).setOnClickListener(new _cls3());
    }

    public void showOfferTermsAndConditions(String s, String s1)
    {
        offerTermHandler.getTermsAndCondition(s, new AnalyticData(s1, null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
    }

    static 
    {
        DIALOG_CREATE = 1;
        DIALOG_UPDATE = 2;
    }




/*
    static Map access$102(SiteWideOffersWidget sitewideofferswidget, Map map)
    {
        sitewideofferswidget.offerTermsDetails = map;
        return map;
    }

*/


/*
    static String access$202(SiteWideOffersWidget sitewideofferswidget, String s)
    {
        sitewideofferswidget.offerDetailText = s;
        return s;
    }

*/



    private class _cls1
        implements android.view.View.OnClickListener
    {

        final SiteWideOffersWidget this$0;

        public void onClick(View view)
        {
            String s = (String)view.getTag();
            if (s.contains("offer_terms"))
            {
                String s1 = s.split("/")[1];
                createOfferTermsDialog(SiteWideOffersWidget.DIALOG_CREATE);
                showOfferTermsAndConditions(s1, "");
            }
        }

        _cls1()
        {
            this$0 = SiteWideOffersWidget.this;
            super();
        }
    }


    private class _cls2 extends OfferTermsVDataHandler
    {

        final SiteWideOffersWidget this$0;

        public void cancelRequests()
        {
            super.cancelRequests();
        }

        public void errorReceived(int i, int j, String s)
        {
            if (dialog != null && dialog.isShowing())
            {
                dialog.dismiss();
            }
            CustomDialog.showErrorMessage(i, 200, s, activity);
            super.errorReceived(i, j, s);
        }

        public void resultReceived(OfferTermsResponse offertermsresponse, boolean flag)
        {
            offerTermsDetails = offertermsresponse.getSantaOffer();
            Iterator iterator = offerTermsDetails.keySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                Object obj = iterator.next();
                Iterator iterator1 = ((Map)offerTermsDetails.get(obj)).keySet().iterator();
                if (iterator1.hasNext())
                {
                    Object obj1 = iterator1.next();
                    offerDetailText = (String)((Map)offerTermsDetails.get(obj)).get(obj1);
                }
            } while (true);
            if (dialog != null && dialog.isShowing())
            {
                createOfferTermsDialog(SiteWideOffersWidget.DIALOG_UPDATE);
            }
        }

        public volatile void resultReceived(Object obj, boolean flag)
        {
            resultReceived((OfferTermsResponse)obj, flag);
        }

        _cls2()
        {
            this$0 = SiteWideOffersWidget.this;
            super();
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        final SiteWideOffersWidget this$0;

        public void onClick(View view)
        {
            if (dialog != null && dialog.isShowing())
            {
                dialog.dismiss();
            }
        }

        _cls3()
        {
            this$0 = SiteWideOffersWidget.this;
            super();
        }
    }

}
