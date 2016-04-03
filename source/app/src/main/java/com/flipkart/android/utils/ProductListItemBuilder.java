// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.DB.WishListDao;
import com.flipkart.android.analytics.ImageOrientation;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.customviews.CustomRobotoRegularTextView;
import com.flipkart.android.fragments.model.ProductListItemModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.utils:
//            ScreenMathUtils, FkProductListContext, FacetData, FontCache, 
//            StringUtils, AppConfigUtils, PageTypeUtils, WishListUtils, 
//            OfferBackgroundUtils

public class ProductListItemBuilder
{

    public ProductListItemBuilder()
    {
    }

    private static View a(Context context)
    {
        View view = new View(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(1, context) / 2, -1);
        layoutparams.rightMargin = ScreenMathUtils.dpToPx(5, context);
        layoutparams.leftMargin = ScreenMathUtils.dpToPx(5, context);
        layoutparams.topMargin = ScreenMathUtils.dpToPx(10, context);
        layoutparams.bottomMargin = ScreenMathUtils.dpToPx(10, context);
        layoutparams.weight = 0.3F;
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() > 10)
        {
            view.setAlpha(0.5F);
        }
        view.setLayoutParams(layoutparams);
        view.setBackgroundColor(-1);
        return view;
    }

    private static ViewGroup a(FkProductListContext fkproductlistcontext, LinearLayout linearlayout, Context context, android.view.View.OnClickListener onclicklistener)
    {
        Map map = (Map)fkproductlistcontext.getFilterMap().get("Offers");
        if (map != null && map.size() != 0) goto _L2; else goto _L1
_L1:
        linearlayout = null;
_L4:
        return linearlayout;
_L2:
        int i;
        linearlayout.setVisibility(0);
        linearlayout.removeAllViews();
        i = map.size();
        if (i != 1)
        {
            break; /* Loop/switch isn't completed */
        }
        Iterator iterator2 = map.keySet().iterator();
        while (iterator2.hasNext()) 
        {
            Object obj2 = iterator2.next();
            LinearLayout linearlayout4 = a(context, 100, false);
            String s3 = ((FacetData)map.get(obj2)).getOfferDescription();
            FontCache.getFont("robotomedium.ttf");
            linearlayout4.addView(a(context, 14, s3, false, 17, 4));
            String as2[] = new String[1];
            as2[0] = ((FacetData)map.get(obj2)).getParams();
            linearlayout4.setTag((new StringBuilder("offers_between_list/")).append(as2[0]).toString());
            linearlayout4.setOnClickListener(onclicklistener);
            linearlayout.addView(linearlayout4);
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (i != 2)
        {
            break; /* Loop/switch isn't completed */
        }
        Iterator iterator1 = map.keySet().iterator();
        int j1 = 0;
        while (iterator1.hasNext()) 
        {
            Object obj1 = iterator1.next();
            LinearLayout linearlayout3 = a(context, 49, false);
            String s2 = ((FacetData)map.get(obj1)).getOfferDescription();
            FontCache.getFont("robotomedium.ttf");
            linearlayout3.addView(a(context, 14, s2, false, 17, 4));
            String as1[] = new String[1];
            as1[0] = ((FacetData)map.get(obj1)).getParams();
            linearlayout3.setTag((new StringBuilder("offers_between_list/")).append(as1[0]).toString());
            linearlayout3.setOnClickListener(onclicklistener);
            linearlayout.addView(linearlayout3);
            if (j1 != 1)
            {
                linearlayout.addView(a(context));
            }
            j1++;
        }
        if (true) goto _L4; else goto _L5
_L5:
        Iterator iterator = map.keySet().iterator();
        int j = 0;
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj = iterator.next();
            if (j >= 2)
            {
                break;
            }
            LinearLayout linearlayout2 = a(context, 38, false);
            String s1 = ((FacetData)map.get(obj)).getOfferDescription();
            FontCache.getFont("robotomedium.ttf");
            linearlayout2.addView(a(context, 14, s1, false, 17, 4));
            String as[] = new String[1];
            as[0] = ((FacetData)map.get(obj)).getParams();
            linearlayout2.setTag((new StringBuilder("offers_between_list/")).append(as[0]).toString());
            linearlayout2.setOnClickListener(onclicklistener);
            linearlayout.addView(linearlayout2);
            linearlayout.addView(a(context));
            j++;
        } while (true);
        LinearLayout linearlayout1 = a(context, 22, true);
        linearlayout1.setBackgroundDrawable(context.getResources().getDrawable(0x7f020112));
        linearlayout1.setGravity(17);
        linearlayout1.setBackgroundColor(Color.parseColor("#434343"));
        String s;
        TextView textview;
        TextView textview1;
        int k;
        int l;
        int i1;
        android.widget.LinearLayout.LayoutParams layoutparams;
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() > 10)
        {
            linearlayout1.getBackground().setAlpha(60);
        } else
        {
            linearlayout1.getBackground().mutate().setAlpha(60);
        }
        s = (new StringBuilder("  + ")).append(String.valueOf(i - 2)).toString();
        FontCache.getFont("robotoregular.ttf");
        textview = a(context, 20, s, false, 17, 1);
        FontCache.getFont("robotomedium.ttf");
        textview1 = a(context, 16, "More", true, 17, 2);
        linearlayout1.addView(textview);
        k = ScreenMathUtils.dpToPx(5, context);
        l = ScreenMathUtils.dpToPx(20, context);
        i1 = ScreenMathUtils.dpToPx(25, context);
        textview.setPadding(l, k, 0, 0);
        linearlayout1.addView(textview1);
        textview1.setPadding(i1, 0, 0, 0);
        layoutparams = (android.widget.LinearLayout.LayoutParams)linearlayout1.getLayoutParams();
        layoutparams.leftMargin = ScreenMathUtils.dpToPx(10, context);
        linearlayout1.setLayoutParams(layoutparams);
        linearlayout1.setOnClickListener(onclicklistener);
        linearlayout1.setTag("open_filter_page");
        linearlayout.addView(linearlayout1);
        return linearlayout;
    }

    private static LinearLayout a(Context context, int i, boolean flag)
    {
        LinearLayout linearlayout = new LinearLayout(context);
        android.widget.LinearLayout.LayoutParams layoutparams;
        int j;
        if (flag)
        {
            layoutparams = new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(30, context), ScreenMathUtils.dpToPx(75, context));
        } else
        {
            layoutparams = new android.widget.LinearLayout.LayoutParams(0, -1);
        }
        layoutparams.weight = i;
        j = ScreenMathUtils.dpToPx(3, context);
        layoutparams.setMargins(j, j, j, j);
        linearlayout.setLayoutParams(layoutparams);
        linearlayout.setGravity(17);
        linearlayout.setOrientation(1);
        return linearlayout;
    }

    private static TextView a(Context context, int i, String s, boolean flag, int j, int k)
    {
        CustomRobotoRegularTextView customrobotoregulartextview = new CustomRobotoRegularTextView(context);
        customrobotoregulartextview.setTextSize(2, i);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
        layoutparams.gravity = 17;
        customrobotoregulartextview.setLayoutParams(layoutparams);
        customrobotoregulartextview.setSingleLine(flag);
        customrobotoregulartextview.setEllipsize(android.text.TextUtils.TruncateAt.END);
        customrobotoregulartextview.setMaxLines(k);
        customrobotoregulartextview.setTextColor(context.getResources().getColor(0x7f090070));
        customrobotoregulartextview.setText(s);
        return customrobotoregulartextview;
    }

    private static void a(View view, ProductListItemModel productlistitemmodel, ImageLoader imageloader, android.view.View.OnClickListener onclicklistener, Context context, PageTypeUtils pagetypeutils)
    {
        int i = ScreenMathUtils.dpToPx(1, view.getContext());
        view.setPadding(i, i, i, i);
        view.setBackgroundResource(0x7f0200f6);
        TextView textview = (TextView)view.findViewById(0x7f0a00fa);
        TextView textview1 = (TextView)view.findViewById(0x7f0a00fe);
        TextView textview2 = (TextView)view.findViewById(0x7f0a00ff);
        TextView textview3 = (TextView)view.findViewById(0x7f0a00fd);
        NetworkImageView networkimageview = (NetworkImageView)view.findViewById(0x7f0a00f4);
        ImageView imageview = (ImageView)view.findViewById(0x7f0a0103);
        imageview.setContentDescription("moreOptions");
        TextView textview4 = (TextView)view.findViewById(0x7f0a00f6);
        RelativeLayout relativelayout = (RelativeLayout)view.findViewById(0x7f0a0165);
        ImageView imageview1 = (ImageView)view.findViewById(0x7f0a00f5);
        if (productlistitemmodel != null && !StringUtils.isNullOrEmpty(productlistitemmodel.getProductId()))
        {
            view.setOnClickListener(onclicklistener);
            if (productlistitemmodel.getListingId() != null)
            {
                view.setTag((new StringBuilder("on_click_product_list_item/")).append(productlistitemmodel.getProductId()).append("/").append(productlistitemmodel.getListingId()).toString());
            } else
            {
                view.setTag((new StringBuilder("on_click_product_list_item/")).append(productlistitemmodel.getProductId()).append("/,").toString());
            }
            if (productlistitemmodel.isEbook() || !productlistitemmodel.isCheckoutEnable() || !AppConfigUtils.getInstance().isEnableOverFlowMenu())
            {
                imageview.setVisibility(8);
            } else
            {
                imageview.setVisibility(0);
            }
            textview.setText(productlistitemmodel.getMainTitle());
            relativelayout.setOnClickListener(onclicklistener);
            if (pagetypeutils == PageTypeUtils.WishList)
            {
                WishListUtils.setWishListTagOnButtons(relativelayout, (new StringBuilder("on_click_delete_from_wishlist/")).append(productlistitemmodel.getProductId()).toString(), PageTypeUtils.ProductGrid);
            } else
            if ((new WishListDao(context)).getWishListById(productlistitemmodel.getProductId()) != null)
            {
                WishListUtils.setWishListTagOnButtons(relativelayout, (new StringBuilder("on_click_remove_from_wishlist/")).append(productlistitemmodel.getProductId()).toString(), PageTypeUtils.ProductGrid);
            } else
            {
                WishListUtils.setWishListTagOnButtons(relativelayout, (new StringBuilder("on_click_add_to_wishlist/")).append(productlistitemmodel.getProductId()).toString(), PageTypeUtils.ProductGrid);
            }
            if (productlistitemmodel.isOfferExisting())
            {
                imageview1.setVisibility(0);
            } else
            {
                imageview1.setVisibility(8);
            }
            if (productlistitemmodel.isInventoryStatusToBeShown())
            {
                textview4.setVisibility(0);
                textview4.setText(productlistitemmodel.getInventoryStatus());
                textview4.setTextColor(productlistitemmodel.getInventoryStatusColor());
            } else
            {
                textview4.setVisibility(8);
                if (productlistitemmodel.isOfferExisting())
                {
                    imageview1.setVisibility(0);
                }
            }
            if (productlistitemmodel.isMrpVisible())
            {
                textview1.setVisibility(0);
                textview2.setVisibility(0);
                textview1.setText((new StringBuilder("Rs.")).append(productlistitemmodel.getMrp()).toString());
                textview1.setPaintFlags(0x10 | textview1.getPaintFlags());
                if (!textview2.equals("0"))
                {
                    textview2.setText((new StringBuilder()).append(productlistitemmodel.getDiscount()).append("% off").toString());
                } else
                {
                    textview2.setText("");
                }
            } else
            {
                textview1.setVisibility(8);
                textview2.setVisibility(8);
            }
            if (productlistitemmodel.isFspShown())
            {
                textview3.setVisibility(0);
                textview3.setText((new StringBuilder("Rs.")).append(productlistitemmodel.getFsp()).toString());
            } else
            if (productlistitemmodel.isCheckoutEnable())
            {
                textview3.setVisibility(0);
                textview3.setText((new StringBuilder("Rs.")).append(productlistitemmodel.getFsp()).toString());
            } else
            {
                textview3.setVisibility(0);
                textview3.setText("Price: N/A");
            }
            networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
            networkimageview.setImageUrl(productlistitemmodel.getPrimaryImageUrlGrid(), imageloader);
            if (productlistitemmodel.getListingId() != null)
            {
                imageview.setTag((new StringBuilder("on_click_product_item_more_options/")).append(productlistitemmodel.getProductId()).append("/").append(productlistitemmodel.getListingId()).toString());
            } else
            {
                imageview.setTag((new StringBuilder("on_click_product_item_more_options/")).append(productlistitemmodel.getProductId()).append("/,").toString());
            }
            imageview.setOnClickListener(onclicklistener);
        }
    }

    private static boolean a(FkProductListContext fkproductlistcontext)
    {
        if (fkproductlistcontext.getSelectedFilterMap() != null && fkproductlistcontext.getSelectedFilterMap().get("Offers") != null)
        {
            return ((ArrayList)fkproductlistcontext.getSelectedFilterMap().get("Offers")).size() > 0;
        } else
        {
            return false;
        }
    }

    public static View buildProductGridItem(ProductListItemModel productlistitemmodel, ProductListItemModel productlistitemmodel1, View view, ImageLoader imageloader, android.view.View.OnClickListener onclicklistener, Context context, ImageOrientation imageorientation, FkProductListContext fkproductlistcontext, 
            int i, PageTypeUtils pagetypeutils, boolean flag)
    {
        boolean flag1 = view instanceof LinearLayout;
        LinearLayout linearlayout = null;
        if (flag1)
        {
            View view3 = ((LinearLayout)view).getChildAt(0);
            linearlayout = null;
            if (view3 != null)
            {
                View view4 = ((LinearLayout)view).getChildAt(1);
                linearlayout = null;
                if (view4 != null)
                {
                    linearlayout = (LinearLayout)view;
                }
            }
        }
        LinearLayout linearlayout1;
        LinearLayout linearlayout2;
        if (linearlayout != null)
        {
            LinearLayout linearlayout10 = (LinearLayout)linearlayout.findViewById(0x7f0a015d);
            View view2 = linearlayout.findViewById(0x7f0a00f1);
            if (i == 0 && !a(fkproductlistcontext))
            {
                view2.setVisibility(0);
                view2.setOnClickListener(null);
                view2.setMinimumHeight(fkproductlistcontext.getHeaderView().getMeasuredHeight());
                linearlayout2 = linearlayout10;
                linearlayout1 = linearlayout;
            } else
            {
                view2.setVisibility(8);
                linearlayout2 = linearlayout10;
                linearlayout1 = linearlayout;
            }
        } else
        if (linearlayout == null)
        {
            LayoutInflater layoutinflater = LayoutInflater.from(context);
            LinearLayout linearlayout3 = (LinearLayout)layoutinflater.inflate(0x7f030072, null);
            LinearLayout linearlayout4 = (LinearLayout)linearlayout3.findViewById(0x7f0a015d);
            View view1 = linearlayout3.findViewById(0x7f0a00f1);
            if (i == 0 && !a(fkproductlistcontext))
            {
                view1.setVisibility(0);
                view1.setOnClickListener(null);
                view1.setMinimumHeight(fkproductlistcontext.getHeaderView().getMeasuredHeight());
            }
            LinearLayout linearlayout5 = (LinearLayout)linearlayout3.findViewById(0x7f0a0164);
            LinearLayout linearlayout7;
            LinearLayout linearlayout8;
            android.widget.LinearLayout.LayoutParams layoutparams;
            android.widget.LinearLayout.LayoutParams layoutparams1;
            if (flag)
            {
                linearlayout5.setVisibility(0);
                OfferBackgroundUtils.setOffersBackground(linearlayout5, AppConfigUtils.getInstance().getBrowsePageTheme());
                a(fkproductlistcontext, linearlayout5, context, onclicklistener);
                android.widget.LinearLayout.LayoutParams layoutparams2 = (android.widget.LinearLayout.LayoutParams)linearlayout5.getLayoutParams();
                layoutparams2.bottomMargin = ScreenMathUtils.dpToPx(25, context);
                linearlayout5.setLayoutParams(layoutparams2);
            } else
            {
                linearlayout5.setVisibility(8);
            }
            if (imageorientation == ImageOrientation.LANDSCAPE)
            {
                LinearLayout linearlayout9 = (LinearLayout)layoutinflater.inflate(0x7f030077, null);
                linearlayout7 = (LinearLayout)layoutinflater.inflate(0x7f030077, null);
                linearlayout8 = linearlayout9;
            } else
            {
                LinearLayout linearlayout6 = (LinearLayout)layoutinflater.inflate(0x7f030076, null);
                linearlayout7 = (LinearLayout)layoutinflater.inflate(0x7f030076, null);
                linearlayout8 = linearlayout6;
            }
            linearlayout4.addView(linearlayout8);
            linearlayout4.addView(linearlayout7);
            layoutparams = (android.widget.LinearLayout.LayoutParams)linearlayout8.getLayoutParams();
            layoutparams.width = 0;
            layoutparams.weight = 50F;
            linearlayout8.setContentDescription("product_grid");
            linearlayout8.setLayoutParams(layoutparams);
            layoutparams1 = (android.widget.LinearLayout.LayoutParams)linearlayout7.getLayoutParams();
            layoutparams1.width = 0;
            layoutparams1.weight = 50F;
            linearlayout7.setContentDescription("product_grid");
            linearlayout7.setLayoutParams(layoutparams1);
            linearlayout2 = linearlayout4;
            linearlayout1 = linearlayout3;
        } else
        {
            linearlayout1 = linearlayout;
            linearlayout2 = null;
        }
        if (productlistitemmodel != null)
        {
            linearlayout2.getChildAt(0).setVisibility(0);
            a(linearlayout2.getChildAt(0), productlistitemmodel, imageloader, onclicklistener, context, pagetypeutils);
        } else
        {
            linearlayout2.getChildAt(0).setVisibility(4);
        }
        if (productlistitemmodel1 != null)
        {
            linearlayout2.getChildAt(1).setVisibility(0);
            a(linearlayout2.getChildAt(1), productlistitemmodel1, imageloader, onclicklistener, context, pagetypeutils);
            return linearlayout1;
        } else
        {
            linearlayout2.getChildAt(1).setVisibility(4);
            return linearlayout1;
        }
    }

    public static View buildProductListItem(ProductListItemModel productlistitemmodel, View view, ImageLoader imageloader, android.view.View.OnClickListener onclicklistener, Context context, PageTypeUtils pagetypeutils, FkProductListContext fkproductlistcontext, int i, 
            boolean flag, boolean flag1)
    {
        LinearLayout linearlayout1;
        boolean flag2 = view instanceof LinearLayout;
        LinearLayout linearlayout = null;
        if (flag2)
        {
            linearlayout = (LinearLayout)view;
        }
        View view1;
        LinearLayout linearlayout2;
        int j;
        TextView textview;
        TextView textview1;
        TextView textview2;
        TextView textview3;
        TextView textview4;
        TextView textview5;
        RatingBar ratingbar;
        TextView textview6;
        TextView textview7;
        ImageView imageview;
        ImageView imageview1;
        NetworkImageView networkimageview;
        RelativeLayout relativelayout;
        String s;
        String s1;
        long l;
        String s2;
        if (linearlayout == null)
        {
            LayoutInflater layoutinflater = LayoutInflater.from(context);
            if (flag)
            {
                linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f030078, null);
            } else
            {
                linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f03004a, null);
            }
        } else
        {
            linearlayout1 = linearlayout;
        }
        if (productlistitemmodel == null)
        {
            return linearlayout1;
        }
        view1 = linearlayout1.findViewById(0x7f0a00f1);
        if (i != 0) goto _L2; else goto _L1
_L1:
        if (a(fkproductlistcontext)) goto _L2; else goto _L3
_L3:
        view1.setVisibility(0);
        view1.setMinimumHeight(fkproductlistcontext.getHeaderView().getMeasuredHeight());
        view1.setOnClickListener(null);
_L46:
        linearlayout2 = (LinearLayout)linearlayout1.findViewById(0x7f0a0164);
        if (!flag1) goto _L5; else goto _L4
_L4:
        linearlayout2.setVisibility(0);
        OfferBackgroundUtils.setOffersBackground(linearlayout2, AppConfigUtils.getInstance().getBrowsePageTheme());
        a(fkproductlistcontext, linearlayout2, context, onclicklistener);
_L31:
        j = ScreenMathUtils.dpToPx(1, context);
        linearlayout1.setPadding(j, j, j, j);
        linearlayout1.setBackgroundResource(0x7f0200f6);
        linearlayout1.setContentDescription("product_list");
        linearlayout1.setOnClickListener(onclicklistener);
        textview = (TextView)linearlayout1.findViewById(0x7f0a00fa);
        textview1 = (TextView)linearlayout1.findViewById(0x7f0a00fb);
        textview2 = (TextView)linearlayout1.findViewById(0x7f0a00fe);
        textview3 = (TextView)linearlayout1.findViewById(0x7f0a00ff);
        textview4 = (TextView)linearlayout1.findViewById(0x7f0a00fd);
        textview5 = (TextView)linearlayout1.findViewById(0x7f0a00f6);
        ratingbar = (RatingBar)linearlayout1.findViewById(0x7f0a0100);
        textview6 = (TextView)linearlayout1.findViewById(0x7f0a0101);
        textview7 = (TextView)linearlayout1.findViewById(0x7f0a0102);
        imageview = (ImageView)linearlayout1.findViewById(0x7f0a00f5);
        imageview1 = (ImageView)linearlayout1.findViewById(0x7f0a0103);
        imageview1.setContentDescription("moreOptions");
        networkimageview = (NetworkImageView)linearlayout1.findViewById(0x7f0a00f4);
        relativelayout = (RelativeLayout)linearlayout1.findViewById(0x7f0a00f7);
        s = productlistitemmodel.getPrimaryImageUrlList();
        if (flag)
        {
            break MISSING_BLOCK_LABEL_406;
        }
        s = productlistitemmodel.getPrimaryImageFullScreen();
        if (StringUtils.isNullOrEmpty(s))
        {
            s = productlistitemmodel.getProductErrorImageUrl();
        }
        ratingbar.setFocusable(false);
        ratingbar.setClickable(false);
        if (productlistitemmodel == null)
        {
            break MISSING_BLOCK_LABEL_1305;
        }
        if (StringUtils.isNullOrEmpty(productlistitemmodel.getProductId()))
        {
            break MISSING_BLOCK_LABEL_1305;
        }
        if (productlistitemmodel.getListingId() == null) goto _L7; else goto _L6
_L6:
        s2 = (new StringBuilder("on_click_product_list_item/")).append(productlistitemmodel.getProductId()).append("/").append(productlistitemmodel.getListingId()).toString();
        linearlayout1.setTag(s2);
_L32:
        if (!productlistitemmodel.isEbook() && productlistitemmodel.isCheckoutEnable() && AppConfigUtils.getInstance().isEnableOverFlowMenu()) goto _L9; else goto _L8
_L8:
        imageview1.setVisibility(8);
_L33:
        textview.setText(productlistitemmodel.getMainTitle());
        if (!StringUtils.isNullOrEmpty(productlistitemmodel.getSubTitle()) && flag) goto _L11; else goto _L10
_L10:
        textview1.setVisibility(8);
_L34:
        relativelayout.setOnClickListener(onclicklistener);
        if (pagetypeutils != PageTypeUtils.WishList) goto _L13; else goto _L12
_L12:
        WishListUtils.setWishListTagOnButtons(relativelayout, (new StringBuilder("on_click_delete_from_wishlist/")).append(productlistitemmodel.getProductId()).toString(), PageTypeUtils.ProductList);
_L35:
        if (!productlistitemmodel.isInventoryStatusToBeShown()) goto _L15; else goto _L14
_L14:
        textview5.setVisibility(0);
        textview5.setText(productlistitemmodel.getInventoryStatus());
        textview5.setTextColor(productlistitemmodel.getInventoryStatusColor());
_L36:
        if (!productlistitemmodel.isMrpVisible()) goto _L17; else goto _L16
_L16:
        textview2.setVisibility(0);
        textview3.setVisibility(0);
        textview2.setText((new StringBuilder("Rs.")).append(productlistitemmodel.getMrp()).toString());
        textview2.setPaintFlags(0x10 | textview2.getPaintFlags());
        if (productlistitemmodel.getDiscount().equals("0")) goto _L19; else goto _L18
_L18:
        textview3.setText((new StringBuilder()).append(productlistitemmodel.getDiscount()).append("% off").toString());
_L37:
        if (!productlistitemmodel.isFspShown()) goto _L21; else goto _L20
_L20:
        textview4.setVisibility(0);
        textview4.setText((new StringBuilder("Rs.")).append(productlistitemmodel.getFsp()).toString());
_L38:
        if (!productlistitemmodel.isRatingVisible()) goto _L23; else goto _L22
_L22:
        ratingbar.setVisibility(0);
        textview6.setVisibility(0);
        ratingbar.setRating(productlistitemmodel.getRating());
        l = productlistitemmodel.getTotalRatings();
        if (l > 1L) goto _L25; else goto _L24
_L24:
        textview6.setText((new StringBuilder()).append(l).append(" Rating").toString());
_L39:
        if (StringUtils.isNullOrEmpty(productlistitemmodel.getEmiText())) goto _L27; else goto _L26
_L26:
        textview7.setVisibility(0);
        textview7.setText(productlistitemmodel.getEmiText());
_L40:
        networkimageview.setImageUrl(s, imageloader);
        if (!productlistitemmodel.isOfferExisting()) goto _L29; else goto _L28
_L28:
        imageview.setVisibility(0);
_L41:
        if (productlistitemmodel.getListingId() == null)
        {
            break MISSING_BLOCK_LABEL_1264;
        }
        imageview1.setTag((new StringBuilder("on_click_product_item_more_options/")).append(productlistitemmodel.getProductId()).append("/").append(productlistitemmodel.getListingId()).toString());
_L42:
        imageview1.setOnClickListener(onclicklistener);
        break MISSING_BLOCK_LABEL_1305;
_L2:
        try
        {
            view1.setVisibility(8);
            continue; /* Loop/switch isn't completed */
        }
        catch (Exception exception) { }
          goto _L30
_L5:
        linearlayout2.setVisibility(8);
          goto _L31
_L7:
        s1 = (new StringBuilder("on_click_product_list_item/")).append(productlistitemmodel.getProductId()).append("/,").toString();
        linearlayout1.setTag(s1);
          goto _L32
_L9:
        imageview1.setVisibility(0);
          goto _L33
_L11:
        textview1.setVisibility(0);
        textview1.setText(productlistitemmodel.getSubTitle());
          goto _L34
_L13:
label0:
        {
            if ((new WishListDao(context)).getWishListById(productlistitemmodel.getProductId()) == null)
            {
                break label0;
            }
            WishListUtils.setWishListTagOnButtons(relativelayout, (new StringBuilder("on_click_remove_from_wishlist/")).append(productlistitemmodel.getProductId()).toString(), PageTypeUtils.ProductList);
        }
          goto _L35
        WishListUtils.setWishListTagOnButtons(relativelayout, (new StringBuilder("on_click_add_to_wishlist/")).append(productlistitemmodel.getProductId()).toString(), PageTypeUtils.ProductList);
          goto _L35
_L15:
        textview5.setVisibility(4);
          goto _L36
_L19:
        textview3.setText("");
        textview3.setVisibility(8);
          goto _L37
_L17:
        textview2.setVisibility(8);
        textview3.setVisibility(8);
          goto _L37
_L21:
label1:
        {
            if (!productlistitemmodel.isCheckoutEnable())
            {
                break label1;
            }
            textview4.setVisibility(0);
            textview4.setText((new StringBuilder("Rs.")).append(productlistitemmodel.getFsp()).toString());
        }
          goto _L38
        textview4.setVisibility(0);
        textview4.setText("Price: N/A");
          goto _L38
_L25:
        textview6.setText((new StringBuilder()).append(l).append(" Ratings").toString());
          goto _L39
_L23:
        ratingbar.setVisibility(4);
        textview6.setVisibility(4);
          goto _L39
_L27:
        textview7.setVisibility(8);
          goto _L40
_L29:
        imageview.setVisibility(8);
          goto _L41
        imageview1.setTag((new StringBuilder("on_click_product_item_more_options/")).append(productlistitemmodel.getProductId()).append("/,").toString());
          goto _L42
_L44:
        return linearlayout1;
_L30:
        if (true) goto _L44; else goto _L43
_L43:
        if (true) goto _L46; else goto _L45
_L45:
    }

    public static View buildRefreshingListitem(View view, ImageLoader imageloader, android.view.View.OnClickListener onclicklistener, Context context, boolean flag)
    {
        if (!flag)
        {
            return new View(context);
        } else
        {
            return (RelativeLayout)LayoutInflater.from(context).inflate(0x7f03005d, null);
        }
    }
}
