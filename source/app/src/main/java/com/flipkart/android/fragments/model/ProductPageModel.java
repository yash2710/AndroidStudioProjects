// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments.model;

import android.content.Context;
import android.content.res.Resources;
import android.view.Display;
import android.view.WindowManager;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.productInfo.AppExtras;
import com.flipkart.android.response.productInfo.CallUsWidgetResponse;
import com.flipkart.android.response.productInfo.CheapestEmi;
import com.flipkart.android.response.productInfo.InstallCareWidgetData;
import com.flipkart.android.response.productInfo.MarketPlaceSeller;
import com.flipkart.android.response.productInfo.MarketPlaceSellerUgcInfo;
import com.flipkart.android.response.productInfo.PriceWidget;
import com.flipkart.android.response.productInfo.ProductAvailabilityDetails;
import com.flipkart.android.response.productInfo.ProductDescription;
import com.flipkart.android.response.productInfo.ProductExtraMessages;
import com.flipkart.android.response.productInfo.ProductImage;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.response.productInfo.ProductReturn;
import com.flipkart.android.response.productInfo.PromiseWidget;
import com.flipkart.android.response.productInfo.PromiseWidgetImage;
import com.flipkart.android.response.productInfo.SizeChart;
import com.flipkart.android.response.productInfo.SizeChartData;
import com.flipkart.android.response.productInfo.SwatchAbout;
import com.flipkart.android.response.productInfo.SwatchResponse;
import com.flipkart.android.response.ugc.UGCRating;
import com.flipkart.android.response.ugc.UGCRatingObj;
import com.flipkart.android.response.ugc.UGCReviewObj;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.MiscUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.SellerTypes;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.volley.request.GsonRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.fragments.model:
//            PromiseWidgetModel

public class ProductPageModel
{

    private String A;
    private String B[];
    private boolean C;
    private String D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private ArrayList I;
    private String J;
    private String K;
    private boolean L;
    private float M;
    private long N;
    private boolean O;
    private String P;
    private boolean Q;
    private ProductPageModel R[];
    private boolean S;
    private ArrayList T;
    private boolean U;
    private String V;
    private boolean W;
    private Map X;
    private boolean Y;
    private ArrayList Z;
    private String a;
    private boolean aa;
    private boolean ab;
    private ArrayList ac;
    private String ad;
    private String ae;
    private SellerTypes af;
    private int ag;
    private String ah;
    private MarketPlaceSeller ai;
    private String aj;
    private String ak;
    private WidgetItem al;
    private InstallCareWidgetData am;
    private ArrayList an;
    private ArrayList ao;
    private ArrayList ap;
    private ArrayList aq;
    private long ar;
    private int as;
    private int at;
    private ArrayList au;
    private String av;
    private boolean aw;
    private boolean ax;
    private String ay;
    private PriceWidget az;
    private String b;
    private String c;
    private boolean d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private boolean j;
    private boolean k;
    private String l;
    private String m;
    private boolean n;
    private float o;
    private long p;
    private long q;
    private boolean r;
    private String s;
    private int t;
    private boolean u;
    private boolean v;
    private String w;
    private String x;
    private String y;
    private String z;

    public ProductPageModel()
    {
        Z = null;
    }

    private static SwatchModel a(SwatchResponse swatchresponse)
    {
        String s1 = swatchresponse.getSwatchAttribute();
        String s2 = swatchresponse.getSwatchValue();
        String s3 = swatchresponse.getSwatchType();
        SwatchModel swatchmodel = new SwatchModel();
        swatchmodel.setTitle(s1);
        byte byte0;
        ArrayList arraylist;
        Map map;
        if (s3.equals("boxes"))
        {
            byte0 = 2;
        } else
        if (s3.equals("paletteImage"))
        {
            byte0 = 1;
        } else
        if (s3.equals("dropDown"))
        {
            byte0 = 3;
        } else
        {
            byte0 = -1;
        }
        swatchmodel.setType(byte0);
        arraylist = new ArrayList();
        map = swatchresponse.getSwatchAbout();
        if (map != null)
        {
            Iterator iterator = map.keySet().iterator();
            int i1 = 0;
            while (iterator.hasNext()) 
            {
                String s4 = (String)iterator.next();
                SwatchAbout swatchabout = (SwatchAbout)map.get(s4);
                String s5 = swatchabout.getId();
                String s6 = swatchabout.getImageUrl();
                boolean flag = swatchabout.isAvailable();
                if (s4.equals(s2))
                {
                    swatchmodel.setCurrentIndex(i1);
                }
                SwatchValueModel swatchvaluemodel = new SwatchValueModel();
                swatchvaluemodel.setAvailable(flag);
                boolean flag1;
                if (swatchmodel.getType() == 1)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                swatchvaluemodel.setImage(flag1);
                swatchvaluemodel.setImageUrl(s6);
                swatchvaluemodel.setItemId(s5);
                swatchvaluemodel.setCartItemId("");
                swatchvaluemodel.setSelected(s4.equals(s2));
                swatchvaluemodel.setText(s4);
                arraylist.add(swatchvaluemodel);
                i1++;
            }
        } else
        {
            return null;
        }
        swatchmodel.setSwaatchValues(arraylist);
        return swatchmodel;
    }

    public static ProductPageModel getModel(ProductInfo productinfo, Context context)
    {
        ProductPageModel productpagemodel;
        int i1;
        MarketPlaceSeller amarketplaceseller[];
        boolean flag;
        ArrayList arraylist6;
        int i4;
        boolean flag1;
        int j4;
        int k4;
        boolean flag3;
        if (productinfo == null)
        {
            return null;
        }
        productpagemodel = new ProductPageModel();
        productpagemodel.setRequestId(productinfo.getRequestId());
        productpagemodel.setInfoLevel(productinfo.getInfoLevel());
        productpagemodel.setKeyFeatures(productinfo.getKeyFeatures());
        productpagemodel.setLastUpdatedTimeStamp(productinfo.getLastUpdatedTimeStamp());
        productpagemodel.setProductId(productinfo.getProductId());
        productpagemodel.setMainTitle(productinfo.getMainTitle());
        productpagemodel.setSubTitle(productinfo.getSubTitle());
        productpagemodel.setProductErrorImageUrl(productinfo.getProductErrorImage());
        productpagemodel.setProductPageUrl(productinfo.getProductPageUrl());
        productpagemodel.setSizeChartUrl(productinfo.getSizeChartUrl());
        productpagemodel.setInstallCare(productinfo.getInstallCare());
        productpagemodel.setAllOffers(productinfo.getOffers());
        productpagemodel.setTitleNote(productinfo.getTitleNote());
        productpagemodel.setFKFirst(productinfo.isFKFirst());
        productpagemodel.setShouldOverLayOfferImage(productinfo.shouldOverLayOfferImage());
        CallUsWidgetResponse calluswidgetresponse = productinfo.getCallUsWidget();
        ArrayList arraylist;
        if (calluswidgetresponse != null)
        {
            ArrayList arraylist9 = calluswidgetresponse.getItems();
            String as1[];
            String s1;
            String s2;
            SizeChart sizechart;
            ArrayList arraylist1;
            int l3;
            String s7;
            MarketPlaceSellerUgcInfo marketplacesellerugcinfo;
            String s9;
            Iterator iterator;
            PromiseWidget promisewidget;
            PromiseWidgetModel promisewidgetmodel;
            SizeChartData sizechartdata;
            double d3;
            double d4;
            int i6;
            String s11;
            if (arraylist9 != null && arraylist9.size() > 0)
            {
                productpagemodel.setCallUsWidget((WidgetItem)arraylist9.get(0));
            } else
            {
                productpagemodel.setCallUsWidget(null);
            }
        }
        arraylist = productinfo.getProductOffers();
        as1 = null;
        if (arraylist != null)
        {
            as1 = new String[arraylist.size()];
            arraylist.toArray(as1);
        }
        if (as1 == null || as1.length == 0)
        {
            productpagemodel.setOfferExisting(false);
        } else
        {
            String as2[] = new String[arraylist.size()];
            arraylist.toArray(as2);
            productpagemodel.setOfferExisting(true);
            productpagemodel.setOffersText(as2);
        }
        if (productinfo.getCheapestEmi() != null)
        {
            String s10 = productinfo.getCheapestEmi().getUrl();
            if (!StringUtils.isNullOrEmpty(s10))
            {
                if (s10.contains("http://flipkart.com"))
                {
                    productpagemodel.setEmiUrl(s10);
                } else
                {
                    productpagemodel.setEmiUrl((new StringBuilder()).append(GsonRequest.BASE_WEB_URL).append(s10).toString());
                }
            }
            s11 = productinfo.getCheapestEmi().getMonthlyInstallment();
            if (!StringUtils.isNullOrEmpty(s11))
            {
                productpagemodel.setEmiText((new StringBuilder("EMI ")).append(s11).toString());
            } else
            {
                productpagemodel.setEmiText("");
            }
        }
        productpagemodel.setFsp((new StringBuilder()).append(productinfo.getSellingPrice()).toString());
        productpagemodel.setPriceWidget(productinfo.getPriceWidget());
        productpagemodel.setMrpVisible(productinfo.isShowMrp());
        productpagemodel.setMrp((new StringBuilder()).append(productinfo.getMrp()).toString());
        d3 = productinfo.getSellingPrice();
        d4 = productinfo.getMrp();
        i6 = MiscUtils.roundoffDecimal((float)(100D * ((d4 - d3) / d4)));
        i1 = i6;
_L16:
        productpagemodel.setDiscount((new StringBuilder()).append(i1).toString());
        if (productinfo.getSellingPrice() == 0) goto _L2; else goto _L1
_L1:
        productpagemodel.setFspShown(true);
_L7:
        UGCRating ugcrating = productinfo.getUgc();
        String s3;
        if (ugcrating != null)
        {
            long l5 = ugcrating.getRatingObj().getTotalRatingCount();
            long l6 = ugcrating.getReviewObj().getTotalNoReviews();
            Exception exception1;
            if (l5 == 0L && l6 == 0L)
            {
                productpagemodel.setRatingVisible(false);
            } else
            {
                productpagemodel.setRatingVisible(true);
                productpagemodel.setTotalRatings(l5);
                productpagemodel.setTotalReviews(l6);
                productpagemodel.setRating((float)ugcrating.getRatingObj().getOverallRating());
            }
        } else
        {
            productpagemodel.setRatingVisible(false);
        }
        s1 = productinfo.getPin();
        s2 = FlipkartPreferenceManager.instance().getUserPinCode();
        if (!StringUtils.isNullOrEmpty(s1) && s2.equalsIgnoreCase(s1))
        {
            productpagemodel.setPinAvailable(true);
        } else
        {
            productpagemodel.setPinAvailable(false);
        }
        s3 = productinfo.getWarranty();
        if (StringUtils.isNullOrEmpty(s3))
        {
            productpagemodel.setWarrantyAvailable(false);
        } else
        {
            productpagemodel.setWarrantyAvailable(true);
            productpagemodel.setWarrantyText(s3);
        }
        productpagemodel.setEbook(productinfo.isEbook());
        productpagemodel.setCheckoutEnabled(productinfo.isEnableCheckout());
        productpagemodel.setIs30DayReplacementAvailable(true);
        productpagemodel.setSizeChartHeading("");
        sizechart = productinfo.getSizeChart();
        if (sizechart != null)
        {
            sizechartdata = sizechart.getSizeChartData();
            if (sizechartdata != null)
            {
                productpagemodel.setSizeChartHeading(sizechartdata.getHeading());
            }
        }
        arraylist1 = productinfo.getMarketPlace();
        amarketplaceseller = new MarketPlaceSeller[arraylist1.size()];
        arraylist1.toArray(amarketplaceseller);
        if (amarketplaceseller.length <= 0) goto _L4; else goto _L3
_L3:
        flag = false;
        arraylist6 = new ArrayList();
        l3 = amarketplaceseller.length;
        i4 = 0;
        flag1 = false;
        j4 = 0;
        k4 = 0x7fffffff;
_L8:
        if (i4 >= l3) goto _L6; else goto _L5
_L2:
        try
        {
            productpagemodel.setFspShown(false);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception1)
        {
            productpagemodel.setFspShown(false);
        }
          goto _L7
_L5:
        int l4;
        boolean flag2;
        int j5;
        MarketPlaceSeller marketplaceseller = amarketplaceseller[i4];
        s7 = marketplaceseller.getListId();
        if (StringUtils.isNullOrEmpty(s7))
        {
            break MISSING_BLOCK_LABEL_2814;
        }
        int k5 = j4 + 1;
        MoreSellerModel moresellermodel = new MoreSellerModel();
        moresellermodel.setDisplayName(marketplaceseller.getSellerDisplayName());
        moresellermodel.setSellingPrice(Integer.toString(marketplaceseller.getSellingPrice()));
        moresellermodel.setListingId(marketplaceseller.getListId());
        if (marketplaceseller.getUgcInfo() != null)
        {
            moresellermodel.setAvgRatings((float)marketplaceseller.getUgcInfo().getRating());
            moresellermodel.setTotalRatings(marketplaceseller.getUgcInfo().getTotalRatingCount());
        }
        if (marketplaceseller.getAvailabilityDetails() != null)
        {
            moresellermodel.setStatus(marketplaceseller.getAvailiabilityDetails().getAvailabilityStatus());
            moresellermodel.setShippingText(marketplaceseller.getAvailabilityDetails().getAvailabilityStatusMessage());
        }
        if (marketplaceseller.getProductReturn() != null)
        {
            moresellermodel.setReplacementPolicy(marketplaceseller.getProductReturn().getReturnPolicy());
            moresellermodel.setSellerReturnPolicyShown(marketplaceseller.getProductReturn().isShowReturn());
        }
        moresellermodel.setFbf(marketplaceseller.getFbf());
        moresellermodel.setPinCodeServiceability(marketplaceseller.isServiceable());
        moresellermodel.setShippingCharge(marketplaceseller.getShippingCharge());
        ArrayList arraylist7 = marketplaceseller.getOffer();
        if (StringUtils.isNullOrEmpty(arraylist7))
        {
            moresellermodel.setOfferExisting(false);
        } else
        {
            moresellermodel.setOfferExisting(true);
            moresellermodel.setOffersText(arraylist7);
        }
        arraylist6.add(moresellermodel);
        Exception exception;
        WindowManager windowmanager;
        int j1;
        int k1;
        int l1;
        int i2;
        ArrayList arraylist2;
        ArrayList arraylist3;
        String s4;
        ProductImage productimage;
        ProductDescription productdescription;
        ArrayList arraylist4;
        ProductInfo aproductinfo[];
        Map map;
        AppExtras appextras;
        int j2;
        ProductExtraMessages productextramessages;
        ArrayList arraylist5;
        ProductPageModel aproductpagemodel[];
        int k2;
        int l2;
        int i3;
        ProductInfo productinfo1;
        Exception exception2;
        int j3;
        String s5;
        ProductImage productimage1;
        String s6;
        double d1;
        int k3;
        double d2;
        ProductInfo aproductinfo1[];
        boolean flag4;
        if (marketplaceseller.isWSR())
        {
            flag3 = true;
        } else
        {
            flag3 = flag1;
        }
        if (marketplaceseller.getSellingPrice() < k4)
        {
            k4 = marketplaceseller.getSellingPrice();
        }
        if (s7.equals(productinfo.getPreferredListingId()))
        {
            productpagemodel.setPreferredSeller(marketplaceseller);
            flag = true;
            productpagemodel.setSellerInfoAvailable(true);
            productpagemodel.setPreferredSellerName(marketplaceseller.getSellerDisplayName());
            boolean flag5;
            String s8;
            boolean flag6;
            if (marketplaceseller.getEmiOptions() == null)
            {
                flag5 = false;
            } else
            {
                flag5 = true;
            }
            productpagemodel.setEMIAvailable(flag5);
            productpagemodel.setCODAvailable(marketplaceseller.isCodAvailable());
            productpagemodel.setSellerName(marketplaceseller.getSellerDisplayName());
            marketplacesellerugcinfo = marketplaceseller.getUgcInfo();
            if (marketplacesellerugcinfo != null)
            {
                productpagemodel.setSellerRating((float)marketplacesellerugcinfo.getRating());
                productpagemodel.setSellerRatingCount(marketplacesellerugcinfo.getTotalRatingCount());
            } else
            {
                productpagemodel.setSellerRating(0.0F);
                productpagemodel.setSellerRatingCount(0L);
            }
            if (marketplaceseller.getProductReturn() == null)
            {
                s8 = "";
            } else
            {
                s8 = marketplaceseller.getProductReturn().getReturnPolicy();
            }
            productpagemodel.setSellerReturnPolicy(s8);
            if (marketplaceseller.getProductReturn() == null)
            {
                flag6 = false;
            } else
            {
                flag6 = marketplaceseller.getProductReturn().isShowReturn();
            }
            productpagemodel.setSellerReturnPolicyShown(flag6);
            if (marketplaceseller.isServiceable())
            {
                productpagemodel.setDeliveryAvailable(true);
                productpagemodel.setServicabilityText((new StringBuilder("Ships to <strong>")).append(productinfo.getPin()).append("</strong>").toString());
                productpagemodel.setSlaText(marketplaceseller.getAvailiabilityDetails().getShippingText());
                if (marketplaceseller.getShippingCharge() == 0)
                {
                    productpagemodel.setFreeDeliveryText("Free home delivery");
                } else
                {
                    productpagemodel.setFreeDeliveryText((new StringBuilder("+")).append(marketplaceseller.getShippingCharge()).append(" Delivery charge").toString());
                }
                if (marketplaceseller.isCodAvailable())
                {
                    productpagemodel.setCashOnDeliveryText("Cash on delivery also available");
                }
            } else
            {
                productpagemodel.setDeliveryAvailable(false);
                productpagemodel.setServicabilityText((new StringBuilder("This product is not available in ")).append(productinfo.getPin()).toString());
            }
            productpagemodel.setInventoryStatus(marketplaceseller.getAvailiabilityDetails().getAvailabilityStatus());
            s9 = marketplaceseller.getAvailiabilityDetails().getAvailabilityStatusIntent();
            if (s9 != null && s9.equals("positive"))
            {
                productpagemodel.setInventoryStatusColor(context.getResources().getColor(0x7f090035));
            } else
            {
                productpagemodel.setInventoryStatusColor(context.getResources().getColor(0x7f090048));
            }
            if (marketplaceseller.getPromiseWidgets() != null && marketplaceseller.getPromiseWidgets().size() > 0)
            {
                ArrayList arraylist8 = new ArrayList();
                iterator = marketplaceseller.getPromiseWidgets().iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    promisewidget = (PromiseWidget)iterator.next();
                    if (promisewidget != null)
                    {
                        promisewidgetmodel = new PromiseWidgetModel();
                        promisewidgetmodel.setBody(promisewidget.getBody());
                        promisewidgetmodel.setBulletType(promisewidget.getBulletEnum());
                        if (promisewidget.getStartImage() != null)
                        {
                            promisewidgetmodel.setImageView1Action(promisewidget.getStartImage().getAction());
                            promisewidgetmodel.setImageView1Url(ImageUtils.getImageUrl(promisewidget.getStartImage()));
                        }
                        if (promisewidget.getEndImage() != null)
                        {
                            promisewidgetmodel.setImageView2Action(promisewidget.getEndImage().getAction());
                            promisewidgetmodel.setImageView2Url(ImageUtils.getImageUrl(promisewidget.getEndImage()));
                        }
                        arraylist8.add(promisewidgetmodel);
                    }
                } while (true);
                productpagemodel.setPromiseWidget(arraylist8);
            }
            productpagemodel.setFbf(marketplaceseller.getFbf());
        }
        j5 = k5;
        l4 = k4;
        flag2 = flag;
_L17:
        i4++;
        flag = flag2;
        k4 = l4;
        flag4 = flag3;
        j4 = j5;
        flag1 = flag4;
          goto _L8
_L6:
        productpagemodel.setCheapestPrice(k4);
        if (flag1)
        {
            if (j4 == 1)
            {
                productpagemodel.setSellerType(SellerTypes.WSR_SINGLE);
            } else
            {
                productpagemodel.setSellerType(SellerTypes.WSR_MULTIPLE);
            }
        } else
        if (j4 == 1)
        {
            productpagemodel.setSellerType(SellerTypes.NWSR_SINGLE);
        } else
        {
            productpagemodel.setSellerType(SellerTypes.NWSR_MULTIPLE);
        }
        if (amarketplaceseller.length > 1)
        {
            productpagemodel.setMoreSellers(true);
            productpagemodel.setMoreSellerStartingPrice((new StringBuilder()).append(k4).toString());
            productpagemodel.setMoreSellerList(arraylist6);
        } else
        {
            productpagemodel.setMoreSellers(false);
        }
        if (!flag)
        {
            productpagemodel.setEMIAvailable(false);
            productpagemodel.setCODAvailable(false);
            productpagemodel.setIs30DayReplacementAvailable(false);
            productpagemodel.setSellerInfoAvailable(false);
        }
_L13:
        windowmanager = (WindowManager)context.getSystemService("window");
        j1 = windowmanager.getDefaultDisplay().getHeight();
        k1 = windowmanager.getDefaultDisplay().getWidth();
        l1 = j1 - (3 * ScreenMathUtils.dpToPx(63, context) + ScreenMathUtils.dpToPx(40, context));
        i2 = (k1 * 90) / 100;
        arraylist2 = productinfo.fetchBestUrlFromDynamicImages("ProductPage image");
        if (arraylist2 != null && arraylist2.size() != 0)
        {
            productpagemodel.setOriginalUrls(arraylist2);
        } else
        {
            productpagemodel.setOriginalUrls(productinfo.fetchBestImageUrls(i2, l1));
        }
        arraylist3 = productinfo.fetchBestUrlFromDynamicImages("ProductPage thumbnails");
        if (arraylist3 != null && arraylist3.size() != 0)
        {
            productpagemodel.setThumbnailsUrls(arraylist3);
        } else
        {
            productpagemodel.setThumbnailsUrls(productinfo.fetchBestImageUrls((int)(0.20000000000000001D * (double)i2), (int)(0.20000000000000001D * (double)l1)));
        }
        productpagemodel.setImageViewHeight(l1);
        s4 = productinfo.fetchBestImageUrl("ProductPage PullOut image");
        if (s4 != null)
        {
            productpagemodel.setPrimaryImageUrlSmall(s4);
        } else
        {
            productimage = productinfo.fetchBestImage(ScreenMathUtils.dpToPx(130, context), ScreenMathUtils.dpToPx(177, context));
            if (productimage != null)
            {
                productpagemodel.setPrimaryImageUrlSmall(productimage.getUrl());
            }
        }
        productinfo.getUgc();
        if (productinfo.getProductSpecification().size() > 0)
        {
            productpagemodel.setSpecificationEnabled(true);
            productpagemodel.setSpecificationList(productinfo.getProductSpecification());
        }
        productdescription = productinfo.getProductDescription();
        if (productdescription != null && productdescription.isShowDescription() && !StringUtils.isNullOrEmpty(productdescription.getDecriptionText()))
        {
            productpagemodel.setSummaryEnabled(true);
            productpagemodel.setSummaryText(productdescription.getDecriptionText());
        } else
        {
            productpagemodel.setSummaryEnabled(productinfo.getProductDescription().isShowDescription());
            productpagemodel.setSummaryText("");
        }
        arraylist4 = productinfo.getAlsoSee();
        if (arraylist4 != null)
        {
            aproductinfo1 = new ProductInfo[arraylist4.size()];
            arraylist4.toArray(aproductinfo1);
            aproductinfo = aproductinfo1;
        } else
        {
            aproductinfo = null;
        }
        if (aproductinfo == null || aproductinfo.length <= 0) goto _L10; else goto _L9
_L9:
        productpagemodel.setOtherVariantsAvailable(true);
        aproductpagemodel = new ProductPageModel[aproductinfo.length];
        k2 = aproductinfo.length;
        l2 = 0;
        i3 = 0;
_L12:
        if (i3 >= k2)
        {
            break; /* Loop/switch isn't completed */
        }
        productinfo1 = aproductinfo[i3];
        aproductpagemodel[l2] = new ProductPageModel();
        aproductpagemodel[l2].setProductId(productinfo1.getProductId());
        aproductpagemodel[l2].setMainTitle(productinfo1.getMainTitle());
        aproductpagemodel[l2].setSubTitle(productinfo1.getSubTitle());
        aproductpagemodel[l2].setFsp((new StringBuilder()).append(productinfo1.getSellingPrice()).toString());
        aproductpagemodel[l2].setMrpVisible(productinfo1.isShowMrp());
        aproductpagemodel[l2].setMrp((new StringBuilder()).append(productinfo1.getMrp()).toString());
        d1 = productinfo.getSellingPrice();
        k3 = productinfo.getMrp();
        d2 = k3;
        j3 = (int)(100D * ((d2 - d1) / d2));
_L15:
        aproductpagemodel[l2].setDiscount((new StringBuilder()).append(j3).toString());
        s5 = productinfo1.fetchBestImageUrl("ProductList page");
        if (s5 != null)
        {
            aproductpagemodel[l2].setPrimaryImageUrl(s5);
        } else
        {
            productimage1 = productinfo1.fetchBestImage(ScreenMathUtils.dpToPx(80, context), ScreenMathUtils.dpToPx(80, context));
            if (productimage1 != null)
            {
                s6 = productimage1.getUrl();
                if (!StringUtils.isNullOrEmpty(s6))
                {
                    aproductpagemodel[l2].setPrimaryImageUrl(s6);
                } else
                {
                    aproductpagemodel[l2].setPrimaryImageUrl(productinfo1.getProductErrorImage());
                }
            } else
            {
                aproductpagemodel[l2].setPrimaryImageUrl(productinfo1.getProductErrorImage());
            }
        }
        l2++;
        i3++;
        if (true) goto _L12; else goto _L11
_L4:
        productpagemodel.setEMIAvailable(false);
        productpagemodel.setCODAvailable(false);
        productpagemodel.setIs30DayReplacementAvailable(false);
        productpagemodel.setSellerInfoAvailable(false);
          goto _L13
_L11:
        productpagemodel.setOtherVariants(aproductpagemodel);
_L14:
        map = processSwatches(productinfo);
        productpagemodel.setSwatchesMap(map);
        if (map != null && map.size() > 0 && map.containsKey("size") && ((SwatchModel)map.get("size")).getType() == 2)
        {
            productpagemodel.setSizeToBeSelected(true);
        }
        appextras = productinfo.getAppExtras();
        if (appextras != null)
        {
            j2 = FlipkartDeviceInfoProvider.getAppVersionNumber();
            productextramessages = (ProductExtraMessages)appextras.getAndroidExtraMessages().get(Integer.toString(j2));
            if (productextramessages == null)
            {
                productextramessages = (ProductExtraMessages)appextras.getAndroidExtraMessages().get("default");
            }
            if (productextramessages != null)
            {
                productpagemodel.setExtraDisableBuyNow(productextramessages.isDisableBuy());
                arraylist5 = productextramessages.getExtraMessages();
                if (arraylist5 != null && arraylist5.size() != 0)
                {
                    productpagemodel.setExtraMessageAvailable(true);
                    productpagemodel.setExtraMessages(arraylist5);
                } else
                {
                    productpagemodel.setExtraMessageAvailable(false);
                }
            } else
            {
                productpagemodel.setExtraMessageAvailable(false);
                productpagemodel.setExtraDisableBuyNow(false);
            }
        } else
        {
            productpagemodel.setExtraMessageAvailable(false);
            productpagemodel.setExtraDisableBuyNow(false);
        }
        return productpagemodel;
_L10:
        productpagemodel.setOtherVariantsAvailable(false);
          goto _L14
        exception2;
        j3 = 0;
          goto _L15
        exception;
        i1 = 0;
          goto _L16
        l4 = k4;
        flag2 = flag;
        int i5 = j4;
        flag3 = flag1;
        j5 = i5;
          goto _L17
    }

    public static Map processSwatches(ProductInfo productinfo)
    {
        Map map = productinfo.getSwatch();
        if (map == null || map.size() == 0)
        {
            return null;
        }
        HashMap hashmap = new HashMap();
        Iterator iterator = map.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s1 = (String)iterator.next();
            if (!StringUtils.isNullOrEmpty(s1))
            {
                SwatchModel swatchmodel = a((SwatchResponse)map.get(s1));
                if (swatchmodel != null)
                {
                    hashmap.put(s1, swatchmodel);
                }
            }
        } while (true);
        return hashmap;
    }

    public ArrayList getAllOffers()
    {
        return an;
    }

    public WidgetItem getCallUsWidget()
    {
        return al;
    }

    public String getCashOnDeliveryText()
    {
        return x;
    }

    public int getCheapestPrice()
    {
        return ag;
    }

    public String getDiscount()
    {
        return m;
    }

    public String getEmiText()
    {
        return ak;
    }

    public String getEmiUrl()
    {
        return aj;
    }

    public ArrayList getExtraMessages()
    {
        return ac;
    }

    public String getFbf()
    {
        return av;
    }

    public String getFreeDeliveryText()
    {
        return A;
    }

    public String getFsp()
    {
        return i;
    }

    public int getImageViewHeight()
    {
        return at;
    }

    public int getInfoLevel()
    {
        return as;
    }

    public InstallCareWidgetData getInstallCare()
    {
        return am;
    }

    public String getInventoryStatus()
    {
        return s;
    }

    public int getInventoryStatusColor()
    {
        return t;
    }

    public ArrayList getKeyFeatures()
    {
        return Z;
    }

    public long getLastUpdatedTimeStamp()
    {
        return ar;
    }

    public String getMainTitle()
    {
        return b;
    }

    public ArrayList getMoreSellerList()
    {
        return I;
    }

    public String getMoreSellerStartingPrice()
    {
        return P;
    }

    public String getMrp()
    {
        return l;
    }

    public String[] getOffersText()
    {
        return B;
    }

    public ArrayList getOriginalUrls()
    {
        return ap;
    }

    public ProductPageModel[] getOtherVariants()
    {
        return R;
    }

    public MarketPlaceSeller getPreferredSeller()
    {
        return ai;
    }

    public String getPreferredSellerName()
    {
        return ah;
    }

    public PriceWidget getPriceWidget()
    {
        return az;
    }

    public String getPrimaryImageUrl()
    {
        return f;
    }

    public String getPrimaryImageUrlSmall()
    {
        return e;
    }

    public String getProductErrorImageUrl()
    {
        return g;
    }

    public String getProductId()
    {
        return a;
    }

    public String getProductPageUrl()
    {
        return h;
    }

    public ArrayList getPromiseWidget()
    {
        return aq;
    }

    public float getRating()
    {
        return o;
    }

    public String getRatingAsString()
    {
        return (new DecimalFormat("#.#")).format(o);
    }

    public String getRequestId()
    {
        return ay;
    }

    public String getSellerChargeText()
    {
        return y;
    }

    public String getSellerName()
    {
        return J;
    }

    public float getSellerRating()
    {
        return M;
    }

    public long getSellerRatingCount()
    {
        return N;
    }

    public String getSellerReturnPolicy()
    {
        return K;
    }

    public SellerTypes getSellerType()
    {
        return af;
    }

    public String getServicabilityText()
    {
        return w;
    }

    public String getSizeChartHeading()
    {
        return ae;
    }

    public String getSizeChartUrl()
    {
        return ad;
    }

    public String getSlaText()
    {
        return z;
    }

    public ArrayList getSpecificationList()
    {
        return T;
    }

    public String getSubTitle()
    {
        return c;
    }

    public String getSummaryText()
    {
        return V;
    }

    public Map getSwatchesMap()
    {
        return X;
    }

    public ArrayList getThumbnailsUrls()
    {
        return ao;
    }

    public ArrayList getTitleNote()
    {
        return au;
    }

    public long getTotalRatings()
    {
        return q;
    }

    public long getTotalReviews()
    {
        return p;
    }

    public String getWarrantyText()
    {
        return D;
    }

    public boolean isCODAvailable()
    {
        return G;
    }

    public boolean isCheckoutEnabled()
    {
        return r;
    }

    public boolean isDeliveryAvailable()
    {
        return v;
    }

    public boolean isEMIAvailable()
    {
        return F;
    }

    public boolean isEbook()
    {
        return W;
    }

    public boolean isExtraDisableBuyNow()
    {
        return aa;
    }

    public boolean isExtraMessageAvailable()
    {
        return ab;
    }

    public boolean isFKFirst()
    {
        return aw;
    }

    public boolean isFspShown()
    {
        return j;
    }

    public boolean isIs30DayReplacementAvailable()
    {
        return E;
    }

    public boolean isMoreSellers()
    {
        return O;
    }

    public boolean isMrpVisible()
    {
        return k;
    }

    public boolean isOfferExisting()
    {
        return d;
    }

    public boolean isOtherVariantsAvailable()
    {
        return Q;
    }

    public boolean isPinAvailable()
    {
        return u;
    }

    public boolean isRatingVisible()
    {
        return n;
    }

    public boolean isSellerInfoAvailable()
    {
        return H;
    }

    public boolean isSellerReturnPolicyShown()
    {
        return L;
    }

    public boolean isSizeToBeSelected()
    {
        return Y;
    }

    public boolean isSpecificationEnabled()
    {
        return S;
    }

    public boolean isSummaryEnabled()
    {
        return U;
    }

    public boolean isWarrantyAvailable()
    {
        return C;
    }

    public void setAllOffers(ArrayList arraylist)
    {
        an = arraylist;
    }

    public void setCODAvailable(boolean flag)
    {
        G = flag;
    }

    public void setCallUsWidget(WidgetItem widgetitem)
    {
        al = widgetitem;
    }

    public void setCashOnDeliveryText(String s1)
    {
        x = s1;
    }

    public void setCheapestPrice(int i1)
    {
        ag = i1;
    }

    public void setCheckoutEnabled(boolean flag)
    {
        r = flag;
    }

    public void setDeliveryAvailable(boolean flag)
    {
        v = flag;
    }

    public void setDiscount(String s1)
    {
        m = s1;
    }

    public void setEMIAvailable(boolean flag)
    {
        F = flag;
    }

    public void setEbook(boolean flag)
    {
        W = flag;
    }

    public void setEmiText(String s1)
    {
        ak = s1;
    }

    public void setEmiUrl(String s1)
    {
        aj = s1;
    }

    public void setExtraDisableBuyNow(boolean flag)
    {
        aa = flag;
    }

    public void setExtraMessageAvailable(boolean flag)
    {
        ab = flag;
    }

    public void setExtraMessages(ArrayList arraylist)
    {
        ac = arraylist;
    }

    public void setFKFirst(boolean flag)
    {
        aw = flag;
    }

    public void setFbf(String s1)
    {
        av = s1;
    }

    public void setFreeDeliveryText(String s1)
    {
        A = s1;
    }

    public void setFsp(String s1)
    {
        i = s1;
    }

    public void setFspShown(boolean flag)
    {
        j = flag;
    }

    public void setImageViewHeight(int i1)
    {
        at = i1;
    }

    public void setInfoLevel(int i1)
    {
        as = i1;
    }

    public void setInstallCare(InstallCareWidgetData installcarewidgetdata)
    {
        am = installcarewidgetdata;
    }

    public void setInventoryStatus(String s1)
    {
        s = s1;
    }

    public void setInventoryStatusColor(int i1)
    {
        t = i1;
    }

    public void setIs30DayReplacementAvailable(boolean flag)
    {
        E = flag;
    }

    public void setKeyFeatures(ArrayList arraylist)
    {
        Z = arraylist;
    }

    public void setLastUpdatedTimeStamp(long l1)
    {
        ar = l1;
    }

    public void setMainTitle(String s1)
    {
        b = s1;
    }

    public void setMoreSellerList(ArrayList arraylist)
    {
        I = arraylist;
    }

    public void setMoreSellerStartingPrice(String s1)
    {
        P = s1;
    }

    public void setMoreSellers(boolean flag)
    {
        O = flag;
    }

    public void setMrp(String s1)
    {
        l = s1;
    }

    public void setMrpVisible(boolean flag)
    {
        k = flag;
    }

    public void setOfferExisting(boolean flag)
    {
        d = flag;
    }

    public void setOffersText(String as1[])
    {
        B = as1;
    }

    public void setOriginalUrls(ArrayList arraylist)
    {
        ap = arraylist;
    }

    public void setOtherVariants(ProductPageModel aproductpagemodel[])
    {
        R = aproductpagemodel;
    }

    public void setOtherVariantsAvailable(boolean flag)
    {
        Q = flag;
    }

    public void setPinAvailable(boolean flag)
    {
        u = flag;
    }

    public void setPreferredSeller(MarketPlaceSeller marketplaceseller)
    {
        ai = marketplaceseller;
    }

    public void setPreferredSellerName(String s1)
    {
        ah = s1;
    }

    public void setPriceWidget(PriceWidget pricewidget)
    {
        az = pricewidget;
    }

    public void setPrimaryImageUrl(String s1)
    {
        f = s1;
    }

    public void setPrimaryImageUrlSmall(String s1)
    {
        e = s1;
    }

    public void setProductErrorImageUrl(String s1)
    {
        g = s1;
    }

    public void setProductId(String s1)
    {
        a = s1;
    }

    public void setProductPageUrl(String s1)
    {
        h = s1;
    }

    public void setPromiseWidget(ArrayList arraylist)
    {
        aq = arraylist;
    }

    public void setRating(float f1)
    {
        o = f1;
    }

    public void setRatingVisible(boolean flag)
    {
        n = flag;
    }

    public void setRequestId(String s1)
    {
        ay = s1;
    }

    public void setSellerChargeText(String s1)
    {
        y = s1;
    }

    public void setSellerInfoAvailable(boolean flag)
    {
        H = flag;
    }

    public void setSellerName(String s1)
    {
        J = s1;
    }

    public void setSellerRating(float f1)
    {
        M = f1;
    }

    public void setSellerRatingCount(long l1)
    {
        N = l1;
    }

    public void setSellerReturnPolicy(String s1)
    {
        K = s1;
    }

    public void setSellerReturnPolicyShown(boolean flag)
    {
        L = flag;
    }

    public void setSellerType(SellerTypes sellertypes)
    {
        af = sellertypes;
    }

    public void setServicabilityText(String s1)
    {
        w = s1;
    }

    public void setShouldOverLayOfferImage(boolean flag)
    {
        ax = flag;
    }

    public void setSizeChartHeading(String s1)
    {
        ae = s1;
    }

    public void setSizeChartUrl(String s1)
    {
        ad = s1;
    }

    public void setSizeToBeSelected(boolean flag)
    {
        Y = flag;
    }

    public void setSlaText(String s1)
    {
        z = s1;
    }

    public void setSpecificationEnabled(boolean flag)
    {
        S = flag;
    }

    public void setSpecificationList(ArrayList arraylist)
    {
        T = arraylist;
    }

    public void setSubTitle(String s1)
    {
        c = s1;
    }

    public void setSummaryEnabled(boolean flag)
    {
        U = flag;
    }

    public void setSummaryText(String s1)
    {
        V = s1;
    }

    public void setSwatchesMap(Map map)
    {
        X = map;
    }

    public void setThumbnailsUrls(ArrayList arraylist)
    {
        ao = arraylist;
    }

    public void setTitleNote(ArrayList arraylist)
    {
        au = arraylist;
    }

    public void setTotalRatings(long l1)
    {
        q = l1;
    }

    public void setTotalReviews(long l1)
    {
        p = l1;
    }

    public void setWarrantyAvailable(boolean flag)
    {
        C = flag;
    }

    public void setWarrantyText(String s1)
    {
        D = s1;
    }

    public boolean shouldOverLayOfferImage()
    {
        return ax;
    }

    private class SwatchModel
    {

        public static final int SWATCH_TYPE_BOXES_INT = 2;
        public static final String SWATCH_TYPE_BOXES_STRING = "boxes";
        public static final int SWATCH_TYPE_DROPDOWN_INT = 3;
        public static final String SWATCH_TYPE_DROPDOWN_STRING = "dropDown";
        public static final int SWATCH_TYPE_PALETTE_INT = 1;
        public static final String SWATCH_TYPE_PALETTE_STRING = "paletteImage";
        private String a;
        private int b;
        private int c;
        private ArrayList d;

        public int getCurrentIndex()
        {
            return c;
        }

        public ArrayList getSwaatchValues()
        {
            return d;
        }

        public String getTitle()
        {
            return a;
        }

        public int getType()
        {
            return b;
        }

        public void setCurrentIndex(int i1)
        {
            c = i1;
        }

        public void setSwaatchValues(ArrayList arraylist)
        {
            d = arraylist;
        }

        public void setTitle(String s1)
        {
            a = s1;
        }

        public void setType(int i1)
        {
            b = i1;
        }

        public SwatchModel()
        {
        }
    }


    private class SwatchValueModel
    {

        private String a;
        private String b;
        private String c;
        private boolean d;
        private boolean e;
        private boolean f;
        private String g;

        public String getCartItemId()
        {
            return c;
        }

        public String getImageUrl()
        {
            return a;
        }

        public String getItemId()
        {
            return b;
        }

        public String getText()
        {
            return g;
        }

        public boolean isAvailable()
        {
            return e;
        }

        public boolean isImage()
        {
            return f;
        }

        public boolean isSelected()
        {
            return d;
        }

        public void setAvailable(boolean flag)
        {
            e = flag;
        }

        public void setCartItemId(String s1)
        {
            c = s1;
        }

        public void setImage(boolean flag)
        {
            f = flag;
        }

        public void setImageUrl(String s1)
        {
            a = s1;
        }

        public void setItemId(String s1)
        {
            b = s1;
        }

        public void setSelected(boolean flag)
        {
            d = flag;
        }

        public void setText(String s1)
        {
            g = s1;
        }

        public String toString()
        {
            return getText();
        }

        public SwatchValueModel()
        {
        }
    }


    private class MoreSellerModel
    {

        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private long f;
        private String g;
        private boolean h;
        private float i;
        private boolean j;
        private ArrayList k;
        private boolean l;
        private int m;
        private String n;

        public float getAvgRatings()
        {
            return i;
        }

        public String getDisplayName()
        {
            return a;
        }

        public String getFbf()
        {
            return n;
        }

        public String getListingId()
        {
            return c;
        }

        public ArrayList getOffersText()
        {
            return k;
        }

        public boolean getPinCodeServiceability()
        {
            return h;
        }

        public String getReplacementPolicy()
        {
            return g;
        }

        public String getSellingPrice()
        {
            return b;
        }

        public int getShippingCharge()
        {
            return m;
        }

        public String getShippingText()
        {
            return e;
        }

        public String getStatus()
        {
            return d;
        }

        public long getTotalRatings()
        {
            return f;
        }

        public boolean isOfferExisting()
        {
            return l;
        }

        public boolean isSellerReturnPolicyShown()
        {
            return j;
        }

        public void setAvgRatings(float f1)
        {
            i = f1;
        }

        public void setDisplayName(String s1)
        {
            a = s1;
        }

        public void setFbf(String s1)
        {
            n = s1;
        }

        public void setListingId(String s1)
        {
            c = s1;
        }

        public void setOfferExisting(boolean flag)
        {
            l = flag;
        }

        public void setOffersText(ArrayList arraylist)
        {
            k = arraylist;
        }

        public void setPinCodeServiceability(boolean flag)
        {
            h = flag;
        }

        public void setReplacementPolicy(String s1)
        {
            g = s1;
        }

        public void setSellerReturnPolicyShown(boolean flag)
        {
            j = flag;
        }

        public void setSellingPrice(String s1)
        {
            b = s1;
        }

        public void setShippingCharge(int i1)
        {
            m = i1;
        }

        public void setShippingText(String s1)
        {
            e = s1;
        }

        public void setStatus(String s1)
        {
            d = s1;
        }

        public void setTotalRatings(long l1)
        {
            f = l1;
        }

        public MoreSellerModel()
        {
            m = 0;
        }
    }

}
