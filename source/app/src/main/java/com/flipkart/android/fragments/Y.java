// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.flipkart.android.analytics.ImageOrientation;
import com.flipkart.android.analytics.ProductListViewType;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.datahandler.param.ProductsListParam;
import com.flipkart.android.fragments.model.ProductListItemModel;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.ProductListItemBuilder;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductListFragment, Z

final class Y extends BaseAdapter
{

    final ProductListFragment a;
    private double b;
    private boolean c;
    private int d;
    private int e;
    private ArrayList f;

    public Y(ProductListFragment productlistfragment, ProductListViewType productlistviewtype)
    {
        a = productlistfragment;
        super();
        b = 1.0D;
        c = true;
        d = -1;
        f = new ArrayList();
        ProductListFragment.b(productlistfragment, false);
        if (productlistviewtype == ProductListViewType.Grid)
        {
            b = 2D;
            return;
        }
        if (productlistviewtype == ProductListViewType.List)
        {
            b = 1.0D;
            return;
        } else
        {
            b = 3D;
            return;
        }
    }

    static int a(Y y)
    {
        int i = y.e;
        y.e = i + 1;
        return i;
    }

    private View a(String s, View view, int i, boolean flag, boolean flag1)
    {
        ProductListItemModel productlistitemmodel = (ProductListItemModel)ProductListFragment.p(a).get(s);
        if (a.fkContext.getParam() instanceof ProductsListParam)
        {
            ProductsListParam productslistparam = (ProductsListParam)a.fkContext.getParam();
            return ProductListItemBuilder.buildProductListItem(productlistitemmodel, view, ProductListFragment.q(a), a, a.activity, productslistparam.getPageType(), a.fkContext, i, flag, flag1);
        } else
        {
            return ProductListItemBuilder.buildProductListItem(productlistitemmodel, view, ProductListFragment.q(a), a, a.activity, null, a.fkContext, i, flag, flag1);
        }
    }

    static ArrayList a(Y y, ArrayList arraylist)
    {
        y.f = arraylist;
        return arraylist;
    }

    static int b(Y y)
    {
        int i = y.e;
        y.e = i - 1;
        return i;
    }

    static int c(Y y)
    {
        return y.e;
    }

    static ArrayList d(Y y)
    {
        return y.f;
    }

    public final int getCount()
    {
        int i = 1 + f.size();
        if (b == 2D)
        {
            if (f.size() % 2 != 0)
            {
                return 1 + (int)Math.ceil((double)i / b);
            } else
            {
                return (int)Math.ceil((double)i / b);
            }
        } else
        {
            return (int)Math.ceil(i);
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

    public final int getItemViewType(int i)
    {
        if (a.offerLimits >= 0)
        {
            int j = a.offerLimits;
            if (ProductListFragment.k(a) == ProductListViewType.Grid)
            {
                j = 1 + j / 2;
            }
            if (i == j && i != 0)
            {
                return 0;
            }
        }
        return 1;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        System.currentTimeMillis();
        Object obj;
        if (b == 2D)
        {
            ProductListFragment productlistfragment1 = a;
            int k2;
            if (ProductListFragment.s(a) > 2 + i * 2)
            {
                k2 = ProductListFragment.s(a);
            } else
            {
                k2 = 2 + i * 2;
            }
            ProductListFragment.c(productlistfragment1, k2);
        } else
        {
            ProductListFragment productlistfragment = a;
            int j;
            if (ProductListFragment.s(a) > i + 1)
            {
                j = ProductListFragment.s(a);
            } else
            {
                j = i + 1;
            }
            ProductListFragment.c(productlistfragment, j);
        }
        if (i == -1 + getCount())
        {
            obj = ProductListItemBuilder.buildRefreshingListitem(view, ProductListFragment.q(a), a, a.activity, a.isRefreshing);
        } else
        {
            FkProductListContext fkproductlistcontext = a.fkContext;
            obj = null;
            if (fkproductlistcontext != null)
            {
                if (i == -1 + getCount())
                {
                    return ProductListItemBuilder.buildRefreshingListitem(view, ProductListFragment.q(a), a, a.activity, a.isRefreshing);
                }
                if (i >= getCount())
                {
                    return new View(FlipkartApplication.getAppContext());
                }
                int k1 = getItemViewType(i);
                boolean flag = false;
                if (k1 == 0)
                {
                    Object obj1 = a.fkContext.getSelectedFilterMap().get("Offers");
                    flag = false;
                    if (obj1 != null)
                    {
                        int j2 = ((ArrayList)a.fkContext.getSelectedFilterMap().get("Offers")).size();
                        flag = false;
                        if (j2 == 0)
                        {
                            flag = true;
                        }
                    }
                }
                if (i == 0)
                {
                    a.fkContext.setHeaderView(ProductListFragment.b(a));
                }
                if (b == 2D)
                {
                    String s = (String)f.get(i * 2);
                    int l1 = f.size();
                    int i2 = 2 + (i << 1);
                    String s1 = null;
                    if (l1 >= i2)
                    {
                        s1 = (String)f.get(1 + i * 2);
                    }
                    ProductListItemModel productlistitemmodel = (ProductListItemModel)ProductListFragment.p(a).get(s);
                    ProductListItemModel productlistitemmodel1 = (ProductListItemModel)ProductListFragment.p(a).get(s1);
                    View view1;
                    if (ProductListFragment.w(a) == ImageOrientation.None)
                    {
                        if (productlistitemmodel != null && productlistitemmodel.isImageLandscape())
                        {
                            ProductListFragment.a(a, ImageOrientation.LANDSCAPE);
                        } else
                        {
                            ProductListFragment.a(a, ImageOrientation.PORTRAIT);
                        }
                    }
                    if (a.fkContext.getParam() instanceof ProductsListParam)
                    {
                        ProductsListParam productslistparam = (ProductsListParam)a.fkContext.getParam();
                        view1 = ProductListItemBuilder.buildProductGridItem(productlistitemmodel, productlistitemmodel1, view, ProductListFragment.q(a), a, a.activity, ProductListFragment.w(a), a.fkContext, i, productslistparam.getPageType(), flag);
                    } else
                    {
                        view1 = ProductListItemBuilder.buildProductGridItem(productlistitemmodel, productlistitemmodel1, view, ProductListFragment.q(a), a, a.activity, ProductListFragment.w(a), a.fkContext, i, null, flag);
                    }
                    obj = (ViewGroup)view1;
                } else
                if (b == 1.0D)
                {
                    obj = (ViewGroup)a((String)f.get(i), view, i, true, flag);
                } else
                {
                    obj = (ViewGroup)a((String)f.get(i), view, i, false, flag);
                }
            }
            if (obj == null)
            {
                return new View(FlipkartApplication.getAppContext());
            }
            if (ProductListFragment.m(a) == 0 && i > 0)
            {
                ((ViewGroup) (obj)).measure(android.view.View.MeasureSpec.makeMeasureSpec(0, 0), android.view.View.MeasureSpec.makeMeasureSpec(0, 0));
                int k = a.productList.getMeasuredHeight();
                int l = ((ViewGroup) (obj)).getMeasuredHeight();
                ProductListFragment.t(a);
                int i1 = ProductListFragment.b(a).getMeasuredHeight();
                int j1 = (k - i1) % l;
                ProductListFragment.d(a, (k - i1) / l);
                if (j1 != 0)
                {
                    ProductListFragment.e(a, 1);
                }
                ViewPropertyAnimator viewpropertyanimator;
                if (ProductListFragment.k(a) == ProductListViewType.Full)
                {
                    ProductListFragment.d(a, 1);
                } else
                if (ProductListFragment.k(a) == ProductListViewType.List)
                {
                    ProductListFragment.e(a, 1);
                }
            }
            if (AppConfigUtils.getInstance().isEnableAnimation() && FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 11)
            {
                if (i > d && ProductListFragment.j(a).c && b != 2D)
                {
                    ((View) (obj)).setTranslationY(ProductListFragment.u(a));
                    viewpropertyanimator = ((View) (obj)).animate().translationY(0.0F).setDuration(600L).setInterpolator(new DecelerateInterpolator());
                    viewpropertyanimator.setListener(new Z(this));
                    viewpropertyanimator.start();
                }
                d = i;
                return ((View) (obj));
            }
        }
        return ((View) (obj));
    }

    public final int getViewTypeCount()
    {
        return 2;
    }

    public final void setAnimate(Boolean boolean1)
    {
        c = boolean1.booleanValue();
    }
}
