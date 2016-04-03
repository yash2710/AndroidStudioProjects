// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.flipkart.android.analytics.ProductListViewType;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.NullResultViewWidget;
import com.flipkart.android.datahandler.param.BaseDataHandlerParam;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.datahandler.param.FooterParams;
import com.flipkart.android.datahandler.param.HeaderParams;
import com.flipkart.android.datahandler.param.ProductsListParam;
import com.flipkart.android.fragments.FiltersListFragment;
import com.flipkart.android.fragments.ProductListFragment;
import com.flipkart.android.fragments.ProductListFragmentUpdateListener;
import com.flipkart.android.fragments.model.ProductListItemModel;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.discovery.GuidedSearchResponse;
import com.flipkart.android.response.discovery.StoreMetaInfo;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.flipkart.android.datahandler:
//            SearchAndProdInfoDataHandler

public class SearchAndProdInfoDataHandlerImpl extends SearchAndProdInfoDataHandler
{

    private HeaderParams a;
    private FooterParams b;
    private ProductListFragmentUpdateListener c;
    private ProductListFragment d;
    private FkProductListContext e;

    public SearchAndProdInfoDataHandlerImpl(BaseDataHandlerParam basedatahandlerparam, int i, ProductListFragment productlistfragment)
    {
        super(basedatahandlerparam, i);
        a = new HeaderParams();
        b = new FooterParams();
        c = productlistfragment;
        d = productlistfragment;
        e = productlistfragment.getFkContext();
    }

    public void resultReceivedFilterInfo(int i, String s, ArrayList arraylist, ArrayList arraylist1)
    {
        while (!d.isRefreshFiltersView() || e == null) 
        {
            return;
        }
        if (i == 200 && e != null && e.getProductsCount() != 0)
        {
            a.setActionUpdateSortAndFiltersLayout(true);
            a.setSortOptions(arraylist1);
            a.setFiltersInfo(arraylist);
            c.updateHeader(a);
            a.setActionUpdateSortAndFiltersLayout(false);
        }
        d.setRefreshFiltersView(false);
    }

    public void resultReceivedNoResultFound(int i, String s)
    {
        d.setRefreshing(false);
        d.setNoMoreDataToDownload(true);
        if (e != null && e.getProductsCount() == 0)
        {
            c.buildErrorMessage(i, -1, s, "");
        }
    }

    public void resultReceivedOmnitureInfo(String s, String s1, String s2, String s3)
    {
        if (!d.isOnSamePage() && e != null && e.getProductsCount() == 0)
        {
            TrackingHelper.sendProductListPage(s, s1, s2, s3);
        }
        if (e != null)
        {
            e.setCurrPageVertical(s2);
        }
        d.setOnSamePage(false);
    }

    public void resultReceivedProductInfo(int i, int j, String s, List list, Map map, String s1, boolean flag, 
            String s2)
    {
        int k = 0;
        d.setRefreshing(false);
        if (d.getFkContext() != null)
        {
            if (i == 200)
            {
                d.noConnection = false;
                d.setVisibilityNoConnectionBar(false, null);
                if (d.nullResultViewWidget.getVisibility() == 0)
                {
                    d.nullResultViewWidget.setVisibility(8);
                }
                if (map == null || map.size() == 0)
                {
                    d.setNoMoreDataToDownload(true);
                    if (e.getParam() instanceof ProductsListParam)
                    {
                        ProductsListParam productslistparam = (ProductsListParam)e.getParam();
                        if (productslistparam != null && productslistparam.getPageType() == PageTypeUtils.WishList && (productslistparam.getProductIds() == null || productslistparam.getProductIds().size() == 0))
                        {
                            c.buildErrorMessage(i, j, "", "There are no items in your WishList");
                        }
                    }
                } else
                {
                    System.gc();
                    if (e.getProductsCount() == 0 && s1 != null)
                    {
                        if (s1.equals("list"))
                        {
                            if (d.isGridLayout())
                            {
                                d.toggleListAdapter(ProductListViewType.List);
                            }
                        } else
                        if (!d.isGridLayout())
                        {
                            d.toggleListAdapter(ProductListViewType.Grid);
                        }
                    }
                    if (list != null && list.size() > 0)
                    {
                        if (e.getParam() instanceof ProductsListParam)
                        {
                            ProductsListParam productslistparam1 = (ProductsListParam)e.getParam();
                            if (productslistparam1 != null)
                            {
                                a.setActionUpdateTitle(true);
                                a.setProductsListParam(productslistparam1);
                                c.updateHeader(a);
                                a.setActionUpdateTitle(false);
                                c.updateFooter(b);
                            }
                        }
                        e.addProductIds(new ArrayList(list));
                        if (list != null && map != null && map.size() != 0)
                        {
                            HashMap hashmap = new HashMap();
                            while (k < list.size()) 
                            {
                                Iterator iterator;
                                String s3;
                                ProductInfo productinfo;
                                ProductListItemModel productlistitemmodel;
                                try
                                {
                                    if (((ProductInfo)map.get(list.get(k))).getPreferredListingId() != null)
                                    {
                                        hashmap.put(list.get(k), ((ProductInfo)map.get(list.get(k))).getPreferredListingId());
                                    }
                                }
                                catch (Exception exception) { }
                                k++;
                            }
                            e.saveListingIdMap(hashmap);
                        }
                        d.updateListAdapterVars();
                        System.currentTimeMillis();
                        iterator = list.iterator();
                        do
                        {
                            if (!iterator.hasNext())
                            {
                                break;
                            }
                            s3 = (String)iterator.next();
                            productinfo = (ProductInfo)map.get(s3);
                            if (productinfo != null)
                            {
                                productinfo.setRequestId(s2);
                            }
                            productlistitemmodel = ProductListItemModel.getProductListModel(productinfo, FlipkartApplication.getAppContext());
                            if (productlistitemmodel != null)
                            {
                                d.getModelMap().put(s3, productlistitemmodel);
                            }
                        } while (true);
                    }
                    if (list != null && e.getProductsCount() >= e.getTotalProductCount())
                    {
                        d.setNoMoreDataToDownload(true);
                    }
                }
            } else
            {
                if (j != 2014 && !e.isShowPin())
                {
                    d.setVisibilityNoConnectionBar(true, CustomDialog.getShortErrorMessage(j));
                    d.noConnection = true;
                }
                if (e.isShowPin())
                {
                    e.setShowPin(false);
                }
                d.setNoMoreDataToDownload(true);
                d.setRefreshing(false);
                if (e.getProductsCount() == 0)
                {
                    c.buildErrorMessage(i, j, "", d.getErrorMessage(i));
                } else
                {
                    d.showError(i, 200, s);
                }
            }
            if (d.getProductListAdapter() != null && e != null)
            {
                if (d.getAnimate() == 0)
                {
                    d.refreshListView();
                    return;
                } else
                {
                    d.setToNotify(true);
                    return;
                }
            }
        }
    }

    public void resultReceivedStoreInfo(int i, String s, ArrayList arraylist, ArrayList arraylist1, String s1, int j, ArrayList arraylist2, 
            ArrayList arraylist3, boolean flag, GuidedSearchResponse guidedsearchresponse)
    {
        if (e != null)
        {
            e.setShowPin(flag);
            if (i == 200 && (e.getParam() instanceof BrowseParam))
            {
                BrowseParam browseparam = (BrowseParam)e.getParam();
                if (flag)
                {
                    String s2 = FlipkartPreferenceManager.instance().getUserPinCode();
                    a.setActionShowPin(flag);
                    a.setBrowseParam(browseparam);
                    a.setPinCode(s2);
                    c.updateHeader(a);
                    a.setActionShowPin(false);
                }
                if (e.getProductsCount() == 0)
                {
                    e.setTotalProductCount(j);
                    if (j != 0)
                    {
                        e.setSpellSuggestionList(arraylist2);
                        e.setAugmentedQueriesList(arraylist3);
                    }
                    e.setTagTitle(s1);
                    if (arraylist1 == null || arraylist1.size() == 0)
                    {
                        e.setStoreMetaInfo(null);
                    } else
                    {
                        e.setStoreMetaInfo((StoreMetaInfo)arraylist1.get(-1 + arraylist1.size()));
                    }
                    a.setActionUpdateTitle(true);
                    a.setResultReceived(true);
                    a.setBrowseParam(browseparam);
                    c.updateHeader(a);
                    a.setActionUpdateTitle(false);
                    if (d instanceof FiltersListFragment)
                    {
                        ((FiltersListFragment)d).setStoreList(arraylist);
                        ((FiltersListFragment)d).setParentStoreList(arraylist1);
                    }
                }
                if (guidedsearchresponse != null && !StringUtils.isNullOrEmpty(guidedsearchresponse.getGuideDataList()))
                {
                    a.setActionShowGuides(true);
                    a.setGuidedSearchResponse(guidedsearchresponse);
                    e.setGuidedSearchResponse(guidedsearchresponse);
                    c.updateHeader(a);
                    a.setActionShowGuides(false);
                    return;
                }
            }
        }
    }

    public void updateTotalProductCount(int i)
    {
        if (e != null)
        {
            e.setTotalProductCount(i);
        }
    }
}
