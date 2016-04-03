// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.flipkart.android.DB.WishList;
import com.flipkart.android.DB.WishListDao;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.WishListVDataHandler;
import com.flipkart.android.init.FlipkartApplication;

// Referenced classes of package com.flipkart.android.utils:
//            PageTypeUtils, ToastMessageUtils, G, StringUtils, 
//            ScreenMathUtils, H

public class WishListUtils
{

    public WishListUtils()
    {
    }

    static void a(String s, View view, PageTypeUtils pagetypeutils)
    {
        b(s, view, pagetypeutils);
    }

    static void a(String s, View view, PageTypeUtils pagetypeutils, Activity activity, String s1)
    {
        if (pagetypeutils == PageTypeUtils.ProductPage)
        {
            setWishListTagOnButtons(view, "on_click_remove_from_wishlist", pagetypeutils);
        } else
        {
            setWishListTagOnButtons(view, (new StringBuilder("on_click_remove_from_wishlist/")).append(s).toString(), pagetypeutils);
        }
        ToastMessageUtils.showErrorToastMessage(s1, activity, false);
    }

    static void a(String s, String s1, View view, PageTypeUtils pagetypeutils)
    {
        b(s, s1, view, pagetypeutils);
    }

    static void a(String s, String s1, View view, PageTypeUtils pagetypeutils, Activity activity, String s2)
    {
        if (pagetypeutils == PageTypeUtils.ProductPage)
        {
            setWishListTagOnButtons(view, "on_click_add_to_wishlist", pagetypeutils);
        } else
        {
            setWishListTagOnButtons(view, (new StringBuilder("on_click_add_to_wishlist/")).append(s).toString(), pagetypeutils);
        }
        ToastMessageUtils.showErrorToastMessage(s2, activity, false);
    }

    public static void addToWishList(String s, String s1, View view, PageTypeUtils pagetypeutils, Activity activity)
    {
        if (!FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            if (insertEntryInWishListDB(s) != 0)
            {
                b(s, s1, view, pagetypeutils);
                return;
            } else
            {
                ToastMessageUtils.showErrorToastMessage("Add to Wishlist Failed.Please try again", activity, false);
                return;
            }
        } else
        {
            G g = new G(s, s1, view, pagetypeutils, activity);
            AnalyticData analyticdata = new AnalyticData();
            analyticdata.setPageTypeUtils(pagetypeutils);
            g.addToWishList(new String[] {
                s
            }, analyticdata);
            setWishListTagOnButtons(view, "adding_to_wishlist", pagetypeutils);
            return;
        }
    }

    private static void b(String s, View view, PageTypeUtils pagetypeutils)
    {
        if (pagetypeutils == PageTypeUtils.ProductPage)
        {
            setWishListTagOnButtons(view, "on_click_add_to_wishlist", pagetypeutils);
        } else
        {
            setWishListTagOnButtons(view, (new StringBuilder("on_click_add_to_wishlist/")).append(s).toString(), pagetypeutils);
        }
        Toast.makeText(FlipkartApplication.getAppContext(), "Item deleted from WishList", 0).show();
    }

    private static void b(String s, String s1, View view, PageTypeUtils pagetypeutils)
    {
        TrackingHelper.sendAddToWishList(s, s1);
        if (pagetypeutils == PageTypeUtils.ProductPage)
        {
            setWishListTagOnButtons(view, "on_click_remove_from_wishlist", pagetypeutils);
        } else
        {
            setWishListTagOnButtons(view, (new StringBuilder("on_click_remove_from_wishlist/")).append(s).toString(), pagetypeutils);
        }
        Toast.makeText(FlipkartApplication.getAppContext(), "Item added to WishList", 0).show();
    }

    public static int deleteEntryFromWishListDB(String s)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            WishListDao wishlistdao = new WishListDao(FlipkartApplication.getAppContext());
            WishList wishlist = wishlistdao.getWishListById(s);
            if (wishlist != null)
            {
                return wishlistdao.delete(wishlist);
            }
        }
        return 0;
    }

    public static int insertEntryInWishListDB(String s)
    {
        if (StringUtils.isNullOrEmpty(s))
        {
            return 0;
        } else
        {
            return (new WishListDao(FlipkartApplication.getAppContext())).create(new WishList(s, ScreenMathUtils.getCurrentLinuxTimeInSeconds()));
        }
    }

    public static void removeFromWishList(String s, View view, PageTypeUtils pagetypeutils, Activity activity)
    {
        if (!FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            if (deleteEntryFromWishListDB(s) != 0)
            {
                b(s, view, pagetypeutils);
                return;
            } else
            {
                ToastMessageUtils.showErrorToastMessage("Delete from WishList Failed.Please try again", activity, false);
                return;
            }
        } else
        {
            H h = new H(s, view, pagetypeutils, activity);
            AnalyticData analyticdata = new AnalyticData();
            analyticdata.setPageTypeUtils(pagetypeutils);
            h.deleteFromWishList(new String[] {
                s
            }, false, analyticdata);
            setWishListTagOnButtons(view, "removing_from_wishlist", pagetypeutils);
            return;
        }
    }

    public static void setWishListTagOnButtons(View view, String s, PageTypeUtils pagetypeutils)
    {
        if (view instanceof RelativeLayout)
        {
            RelativeLayout relativelayout = (RelativeLayout)view;
            TextView textview;
            ImageView imageview;
            if (pagetypeutils == PageTypeUtils.ProductPage)
            {
                textview = (TextView)relativelayout.findViewById(0x7f0a0205);
                imageview = (ImageView)relativelayout.findViewById(0x7f0a0204);
            } else
            if (pagetypeutils == PageTypeUtils.ProductGrid)
            {
                textview = (TextView)relativelayout.findViewById(0x7f0a0167);
                imageview = (ImageView)relativelayout.findViewById(0x7f0a0166);
            } else
            {
                PageTypeUtils pagetypeutils1 = PageTypeUtils.ProductList;
                textview = null;
                imageview = null;
                if (pagetypeutils == pagetypeutils1)
                {
                    textview = (TextView)relativelayout.findViewById(0x7f0a00f9);
                    imageview = (ImageView)relativelayout.findViewById(0x7f0a00f8);
                }
            }
            if (textview != null && imageview != null)
            {
                if (s.contains("on_click_remove_from_wishlist"))
                {
                    PageTypeUtils _tmp = PageTypeUtils.ProductPage;
                    imageview.setImageResource(0x7f02018e);
                    textview.setVisibility(4);
                } else
                if (s.contains("removing_from_wishlist"))
                {
                    PageTypeUtils _tmp1 = PageTypeUtils.ProductPage;
                    imageview.setImageResource(0x7f02018e);
                    textview.setVisibility(0);
                    textview.setText("Removing");
                } else
                if (s.contains("adding_to_wishlist"))
                {
                    PageTypeUtils _tmp2 = PageTypeUtils.ProductPage;
                    imageview.setImageResource(0x7f02018d);
                    textview.setVisibility(0);
                    textview.setText("Adding");
                } else
                if (s.contains("on_click_add_to_wishlist"))
                {
                    PageTypeUtils _tmp3 = PageTypeUtils.ProductPage;
                    imageview.setImageResource(0x7f02018d);
                    textview.setVisibility(4);
                } else
                if (s.contains("deleting_from_wishlist"))
                {
                    imageview.setImageResource(0x7f0200d6);
                    textview.setVisibility(0);
                    textview.setText("Deleting");
                } else
                if (s.contains("on_click_delete_from_wishlist"))
                {
                    imageview.setImageResource(0x7f0200d6);
                    textview.setVisibility(4);
                }
                relativelayout.setTag(s);
            }
        }
    }
}
