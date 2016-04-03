// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Dialog;
import com.flipkart.android.datahandler.OfferTermsVDataHandler;
import com.flipkart.android.response.offerTermsResponse.OfferTermsResponse;
import com.flipkart.android.utils.CustomDialog;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.customwidget:
//            SiteWideOffersWidget

class init> extends OfferTermsVDataHandler
{

    final SiteWideOffersWidget this$0;

    public void cancelRequests()
    {
        super.cancelRequests();
    }

    public void errorReceived(int i, int j, String s)
    {
        if (SiteWideOffersWidget.access$300(SiteWideOffersWidget.this) != null && SiteWideOffersWidget.access$300(SiteWideOffersWidget.this).isShowing())
        {
            SiteWideOffersWidget.access$300(SiteWideOffersWidget.this).dismiss();
        }
        CustomDialog.showErrorMessage(i, 200, s, activity);
        super.errorReceived(i, j, s);
    }

    public void resultReceived(OfferTermsResponse offertermsresponse, boolean flag)
    {
        SiteWideOffersWidget.access$102(SiteWideOffersWidget.this, offertermsresponse.getSantaOffer());
        Iterator iterator = SiteWideOffersWidget.access$100(SiteWideOffersWidget.this).keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj = iterator.next();
            Iterator iterator1 = ((Map)SiteWideOffersWidget.access$100(SiteWideOffersWidget.this).get(obj)).keySet().iterator();
            if (iterator1.hasNext())
            {
                Object obj1 = iterator1.next();
                SiteWideOffersWidget.access$202(SiteWideOffersWidget.this, (String)((Map)SiteWideOffersWidget.access$100(SiteWideOffersWidget.this).get(obj)).get(obj1));
            }
        } while (true);
        if (SiteWideOffersWidget.access$300(SiteWideOffersWidget.this) != null && SiteWideOffersWidget.access$300(SiteWideOffersWidget.this).isShowing())
        {
            createOfferTermsDialog(SiteWideOffersWidget.access$400());
        }
    }

    public volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((OfferTermsResponse)obj, flag);
    }

    msResponse()
    {
        this$0 = SiteWideOffersWidget.this;
        super();
    }
}
