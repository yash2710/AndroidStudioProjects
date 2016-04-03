// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.flipkart.android.analytics.TrackingHelper;

// Referenced classes of package com.flipkart.android.customwidget:
//            CallUsWidget

class val.telNumber
    implements android.view.tener
{

    final CallUsWidget this$0;
    final String val$telNumber;

    public void onClick(View view)
    {
        TrackingHelper.sendCallUsClicked();
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse((new StringBuilder("tel:")).append(val$telNumber).toString()));
        activity.startActivity(intent);
    }

    ()
    {
        this$0 = final_calluswidget;
        val$telNumber = String.this;
        super();
    }
}
