// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.fragments.model.ProductPageModel;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.MiscUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageFragment

final class ar extends BaseAdapter
{

    private ProductPageFragment a;

    ar(ProductPageFragment productpagefragment)
    {
        a = productpagefragment;
        super();
    }

    public final int getCount()
    {
        if (ProductPageFragment.c(a) == null)
        {
            return 0;
        } else
        {
            return ProductPageFragment.c(a).getProductIds().size();
        }
    }

    public final Object getItem(int i)
    {
        return null;
    }

    public final long getItemId(int i)
    {
        return 0L;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        LinearLayout linearlayout;
        ProgressBar progressbar;
        LinearLayout linearlayout1;
        LinearLayout linearlayout2;
        TextView textview;
        TextView textview1;
        TextView textview2;
        int j;
        linearlayout = (LinearLayout)view;
        if (ProductPageFragment.c(a).getProductsCount() > 0 && i >= -4 + ProductPageFragment.c(a).getProductsCount())
        {
            ProductPageFragment.c(a, 1);
        }
        ProductPageModel productpagemodel = (ProductPageModel)ProductPageFragment.d(a).get(ProductPageFragment.c(a).getProductIds().get(i));
        if (linearlayout == null)
        {
            linearlayout = (LinearLayout)((LayoutInflater)a.getActivity().getSystemService("layout_inflater")).inflate(0x7f030071, null);
        }
        progressbar = (ProgressBar)linearlayout.findViewById(0x7f0a008b);
        linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a0156);
        ProductInfo productinfo = ProductPageFragment.c(a).getProductForId((String)ProductPageFragment.c(a).getProductIds().get(i));
        if (productpagemodel == null && productinfo != null)
        {
            productpagemodel = ProductPageModel.getModel(productinfo, a.activity);
            ProductPageFragment.d(a).put(productinfo.getProductId(), productpagemodel);
        }
        ProductPageModel productpagemodel1 = productpagemodel;
        if (productpagemodel1 == null)
        {
            break MISSING_BLOCK_LABEL_578;
        }
        progressbar.setVisibility(8);
        linearlayout1.setVisibility(0);
        NetworkImageView networkimageview = (NetworkImageView)linearlayout.findViewById(0x7f0a0157);
        String s = productpagemodel1.getPrimaryImageUrlSmall();
        double d;
        double d1;
        int k;
        if (StringUtils.isNullOrEmpty(s))
        {
            networkimageview.setScaleType(android.widget.ImageView.ScaleType.CENTER);
            s = productpagemodel1.getProductErrorImageUrl();
        } else
        {
            networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
        }
        networkimageview.setImageUrl(s, ProductPageFragment.o(a));
        ((TextView)linearlayout.findViewById(0x7f0a0158)).setText(productpagemodel1.getMainTitle());
        linearlayout2 = (LinearLayout)linearlayout.findViewById(0x7f0a015a);
        textview = (TextView)linearlayout.findViewById(0x7f0a015b);
        textview1 = (TextView)linearlayout.findViewById(0x7f0a015c);
        if (!productpagemodel1.isMrpVisible())
        {
            break MISSING_BLOCK_LABEL_558;
        }
        d = Double.parseDouble(productpagemodel1.getFsp());
        d1 = Double.parseDouble(productpagemodel1.getMrp());
        k = MiscUtils.roundoffDecimal((float)(100D * ((d1 - d) / d1)));
        j = k;
_L1:
        Exception exception;
        if (!productpagemodel1.getMrp().equals("0"))
        {
            textview.setText((new StringBuilder("Rs. ")).append(productpagemodel1.getMrp()).toString());
        } else
        {
            textview.setText("Price not Available");
        }
        textview.setPaintFlags(0x10 | textview.getPaintFlags());
        if (j != 0)
        {
            textview1.setText((new StringBuilder()).append(j).append("% OFF").toString());
        } else
        {
            textview1.setText("");
        }
_L2:
        textview2 = (TextView)linearlayout.findViewById(0x7f0a0159);
        if (!productpagemodel1.getFsp().equals("0"))
        {
            textview2.setText((new StringBuilder("Rs. ")).append(productpagemodel1.getFsp()).toString());
            return linearlayout;
        } else
        {
            textview2.setText("Price not Available");
            return linearlayout;
        }
        exception;
        j = 0;
          goto _L1
        linearlayout2.setVisibility(8);
          goto _L2
        ProductPageFragment.a(a, i, 1);
        linearlayout1.setVisibility(8);
        progressbar.setVisibility(0);
        return linearlayout;
    }
}
