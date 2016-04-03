// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.flipkart.android.customviews.CustomRobotoLightTextView;
import com.flipkart.android.customviews.CustomRobotoMediumTextView;
import com.flipkart.android.fragments.model.ProductPageModel;
import com.flipkart.android.response.productInfo.ProductSpecification;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

// Referenced classes of package com.flipkart.android.utils:
//            StringUtils, ProductPageBuilder

public class ProductPageSpecificationBuilder
{

    public ProductPageSpecificationBuilder()
    {
    }

    public static void buildProductPageSpecification(ProductPageModel productpagemodel, View view, android.view.View.OnClickListener onclicklistener)
    {
        LinearLayout linearlayout;
        LayoutInflater layoutinflater;
        int i;
        int j;
        if (StringUtils.isNullOrEmpty(productpagemodel.getProductId()))
        {
            break MISSING_BLOCK_LABEL_496;
        }
        ProductPageBuilder.buildProductPageSubPagesTiltBar(productpagemodel.getMainTitle(), productpagemodel.getSubTitle(), view, onclicklistener, productpagemodel.isEbook());
        ProductPageBuilder.buildProductPageSubPagesPriceBar(productpagemodel, view);
        if (!productpagemodel.isSpecificationEnabled())
        {
            break MISSING_BLOCK_LABEL_496;
        }
        linearlayout = (LinearLayout)view.findViewById(0x7f0a01f0);
        layoutinflater = (LayoutInflater)view.getContext().getSystemService("layout_inflater");
        i = 0;
        j = 0;
_L8:
        LinearLayout linearlayout1;
        Iterator iterator;
        int k;
        if (i >= productpagemodel.getSpecificationList().size())
        {
            break; /* Loop/switch isn't completed */
        }
        String s = ((ProductSpecification)productpagemodel.getSpecificationList().get(i)).getKey();
        linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f03009b, null);
        ((CustomRobotoMediumTextView)linearlayout1.findViewById(0x7f0a01ef)).setText(s.toUpperCase());
        iterator = ((ProductSpecification)productpagemodel.getSpecificationList().get(i)).getValue().keySet().iterator();
        k = j;
_L2:
        LinearLayout linearlayout2;
        CustomRobotoLightTextView customrobotolighttextview1;
        Object obj;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_470;
        }
        String s1 = (String)iterator.next();
        linearlayout2 = (LinearLayout)layoutinflater.inflate(0x7f03009a, null);
        CustomRobotoLightTextView customrobotolighttextview = (CustomRobotoLightTextView)linearlayout2.findViewById(0x7f0a01ed);
        customrobotolighttextview.setText(s1);
        customrobotolighttextview1 = (CustomRobotoLightTextView)linearlayout2.findViewById(0x7f0a01ee);
        obj = ((ProductSpecification)productpagemodel.getSpecificationList().get(i)).getValue().get(s1);
        if (s1.trim().isEmpty())
        {
            customrobotolighttextview.setVisibility(8);
            android.widget.TableRow.LayoutParams layoutparams = new android.widget.TableRow.LayoutParams(-1, -2);
            layoutparams.setMargins(0, 0, 0, 0);
            customrobotolighttextview1.setLayoutParams(layoutparams);
        }
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_319;
        }
        if (!(obj instanceof String))
        {
            break; /* Loop/switch isn't completed */
        }
        customrobotolighttextview1.setText((String)obj);
_L4:
        View view1 = layoutinflater.inflate(0x7f030099, null);
        k++;
        linearlayout1.addView(linearlayout2);
        linearlayout1.addView(view1);
        if (true) goto _L2; else goto _L1
_L1:
        if (!(obj instanceof JSONArray)) goto _L4; else goto _L3
_L3:
        JSONArray jsonarray;
        StringBuilder stringbuilder;
        jsonarray = (JSONArray)obj;
        stringbuilder = new StringBuilder();
        int l = 0;
_L6:
        if (l >= jsonarray.length())
        {
            break; /* Loop/switch isn't completed */
        }
        stringbuilder.append((new StringBuilder()).append(jsonarray.get(l).toString()).append(",").toString());
        l++;
        if (true) goto _L6; else goto _L5
_L5:
        try
        {
            if (stringbuilder.length() > 0)
            {
                stringbuilder.deleteCharAt(-1 + stringbuilder.length());
            }
            customrobotolighttextview1.setText(stringbuilder.toString());
        }
        catch (JSONException jsonexception)
        {
            FkLogger.printStackTrace(jsonexception);
        }
          goto _L4
        linearlayout.addView(linearlayout1);
        i++;
        j = k;
        if (true) goto _L8; else goto _L7
_L7:
        if (j > 0)
        {
            linearlayout.setVisibility(0);
        }
    }
}
