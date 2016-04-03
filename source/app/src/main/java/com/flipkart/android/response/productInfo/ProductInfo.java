// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;

import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.appconfig.ImageProfileMatrix;
import com.flipkart.android.response.appconfig.ImageProfileValues;
import com.flipkart.android.response.discovery.OmnitureData;
import com.flipkart.android.response.ugc.UGCRating;
import com.flipkart.android.utils.NetworkMonitor;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.response.productInfo:
//            ProductAvailabilityDetails, ProductDescription, AppExtras, CallUsWidgetResponse, 
//            InstallCareWidgetData, CheapestEmi, PriceWidget, ProductImage, 
//            MarketPlaceSeller, SizeChart

public class ProductInfo
{

    private ArrayList alsoSee;
    private AppExtras appExtras;
    private ProductAvailabilityDetails availabilityDetails;
    private CallUsWidgetResponse callUs;
    private CheapestEmi cheapestEmi;
    private Map dynamicImageUrl;
    private boolean enableCheckout;
    private String freeShippingLimit;
    private boolean hasExchangeOffer;
    private int infoLevel;
    private InstallCareWidgetData installCare;
    private int instantCashBack;
    private boolean isAudioBook;
    private boolean isBundle;
    private boolean isDigital;
    private boolean isDigitalMusic;
    private boolean isEbook;
    private boolean isFKFirst;
    private ArrayList keyFeatures;
    private long lastUpdatedTimeStamp;
    private String mainTitle;
    private ArrayList marketPlace;
    private int mrp;
    private ArrayList offers;
    private OmnitureData omnitureData;
    private String pin;
    private String preferredListingId;
    private PriceWidget priceWidget;
    private String primaryImageId;
    private String productAltImage;
    private ProductDescription productDescription;
    private String productErrorImage;
    private String productId;
    private Map productMultipleImage;
    private String productPageUrl;
    private String productPrimaryImage;
    private ArrayList productSpecification;
    private String productStatus;
    private String productStatusIntent;
    private String requestId;
    private String returnPolicy;
    private int sellingPrice;
    private boolean shouldOverlayOfferImage;
    private boolean showCODHelpText;
    private boolean showEMIOfferHelpText;
    private boolean showInstantCashBack;
    private boolean showMrp;
    private SizeChart sizeChart;
    private String sizeChartUrl;
    private String slaMessage;
    private String subTitle;
    private Map swatch;
    private ArrayList titleNote;
    private UGCRating ugc;
    private String vertical;
    private String warranty;

    public ProductInfo()
    {
        availabilityDetails = new ProductAvailabilityDetails();
        keyFeatures = new ArrayList();
        alsoSee = new ArrayList();
        productDescription = new ProductDescription();
        marketPlace = new ArrayList();
        omnitureData = new OmnitureData();
        productMultipleImage = new LinkedHashMap();
        productSpecification = new ArrayList();
        ugc = new UGCRating();
        swatch = new LinkedHashMap();
        appExtras = new AppExtras();
        callUs = new CallUsWidgetResponse();
        installCare = new InstallCareWidgetData();
        cheapestEmi = new CheapestEmi();
        offers = new ArrayList();
        titleNote = new ArrayList();
        priceWidget = new PriceWidget();
    }

    private ProductImage fetchBestProductImage(int i, int j, String s)
    {
        if (!StringUtils.isNullOrEmpty(s)) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        int k;
        Map map;
        k = NetworkMonitor.isNetworkFast();
        map = (Map)getProductMultipleImage().get(s);
        if (map == null || map.size() == 0) goto _L1; else goto _L3
_L3:
        Iterator iterator;
        int l;
        ProductImage productimage;
        ProductImage productimage1;
        iterator = map.keySet().iterator();
        l = 0;
        productimage = null;
        productimage1 = null;
_L5:
        ProductImage productimage2;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        productimage2 = (ProductImage)map.get((String)iterator.next());
        if (productimage2.getActualHeight() <= j && productimage2.getActualWidth() <= i)
        {
            if (l >= productimage2.getActualHeight() * productimage2.getActualWidth())
            {
                break MISSING_BLOCK_LABEL_237;
            }
            l = productimage2.getActualHeight() * productimage2.getActualWidth();
            productimage1 = productimage2;
            continue; /* Loop/switch isn't completed */
        }
        if (productimage2.getActualHeight() > 650 || productimage2.getActualWidth() > 650)
        {
            break MISSING_BLOCK_LABEL_237;
        }
        if (productimage == null)
        {
            productimage = productimage2;
            continue; /* Loop/switch isn't completed */
        }
        if (productimage2.getActualHeight() * productimage2.getActualWidth() >= productimage.getActualHeight() * productimage.getActualWidth())
        {
            break MISSING_BLOCK_LABEL_237;
        }
_L6:
        productimage = productimage2;
        if (true) goto _L5; else goto _L4
_L4:
        if (k == 3 || productimage1 == null)
        {
            productimage1 = productimage;
        }
        return productimage1;
        productimage2 = productimage;
          goto _L6
    }

    private static String getImageForBaseUrl(String s, String s1)
    {
        if (FlipkartApplication.getImageProfileMatrix() == null) goto _L2; else goto _L1
_L1:
        ImageProfileValues imageprofilevalues;
        int i;
        imageprofilevalues = (ImageProfileValues)((Map)FlipkartApplication.getImageProfileMatrix().getImageConfig().get("default")).get(s);
        i = NetworkMonitor.isNetworkFast();
        String s2;
        String s3;
        {
            if (i != 2)
            {
                break MISSING_BLOCK_LABEL_102;
            }
            String s4;
            ImageProfileData imageprofiledata;
            String s5;
            try
            {
                s4 = imageprofilevalues.getG3();
            }
            catch (Exception exception)
            {
                return null;
            }
        }
        s3 = null;
        if (s4 == null)
        {
            break MISSING_BLOCK_LABEL_143;
        }
        imageprofiledata = ImageProfileData.parseString(s4);
        s2 = s1.replace("{@height}", imageprofiledata.height).replace("{@width}", imageprofiledata.width).replace("{@quality}", imageprofiledata.quality);
        break; /* Loop/switch isn't completed */
        if (i != 3)
        {
            break MISSING_BLOCK_LABEL_118;
        }
        s4 = imageprofilevalues.getG2();
        continue; /* Loop/switch isn't completed */
        s5 = imageprofilevalues.getWifi();
        s4 = s5;
        if (true) goto _L4; else goto _L3
_L4:
        break MISSING_BLOCK_LABEL_51;
_L2:
        s2 = null;
_L3:
        s3 = s2;
        return s3;
    }

    public ProductImage fetchBestImage(int i, int j)
    {
        int k = 650;
        if (i > k)
        {
            j = k;
        } else
        {
            k = i;
        }
        if (NetworkMonitor.isNetworkFast() == 3 && (j > 100 || k > 100))
        {
            k /= 2;
            j /= 2;
        }
        return fetchBestProductImage(k, j, getPrimaryImageId());
    }

    public String fetchBestImageUrl(String s)
    {
        String s1;
        if (dynamicImageUrl == null || dynamicImageUrl.size() == 0)
        {
            break MISSING_BLOCK_LABEL_43;
        }
        s1 = getImageForBaseUrl(s, (String)dynamicImageUrl.get(getPrimaryImageId()));
        return s1;
        Exception exception;
        exception;
        return null;
    }

    public ArrayList fetchBestImageUrls(int i, int j)
    {
        int k = 650;
        int l;
        ArrayList arraylist;
        Iterator iterator;
        if (i > k)
        {
            j = k;
        } else
        {
            k = i;
        }
        if (NetworkMonitor.isNetworkFast() == 3 && (j > 100 || k > 'd'))
        {
            int i1 = k / 2;
            j /= 2;
            l = i1;
        } else
        {
            l = k;
        }
        arraylist = new ArrayList();
        iterator = getProductMultipleImage().keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s = (String)iterator.next();
            ProductImage productimage = fetchBestProductImage(l, j, s);
            if (productimage != null)
            {
                String s1 = productimage.getUrl();
                if (!StringUtils.isNullOrEmpty(s1))
                {
                    if (s.equals(getPrimaryImageId()))
                    {
                        arraylist.add(0, s1);
                    } else
                    {
                        arraylist.add(s1);
                    }
                }
            }
        } while (true);
        return arraylist;
    }

    public ArrayList fetchBestUrlFromDynamicImages(String s)
    {
        ArrayList arraylist = new ArrayList();
        if (dynamicImageUrl != null && dynamicImageUrl.size() != 0)
        {
            for (Iterator iterator = dynamicImageUrl.keySet().iterator(); iterator.hasNext();)
            {
                String s1 = (String)iterator.next();
                String s2 = (String)dynamicImageUrl.get(s1);
                if (s2 == null)
                {
                    return null;
                }
                String s3 = getImageForBaseUrl(s, s2);
                if (s3 == null)
                {
                    return null;
                }
                if (s1.equals(getPrimaryImageId()))
                {
                    arraylist.add(0, s3);
                } else
                {
                    arraylist.add(s3);
                }
            }

        } else
        {
            return null;
        }
        return arraylist;
    }

    public ArrayList getAlsoSee()
    {
        if (alsoSee == null)
        {
            alsoSee = new ArrayList();
        }
        return alsoSee;
    }

    public AppExtras getAppExtras()
    {
        return appExtras;
    }

    public ProductAvailabilityDetails getAvailabilityDetails()
    {
        return availabilityDetails;
    }

    public CallUsWidgetResponse getCallUsWidget()
    {
        return callUs;
    }

    public CheapestEmi getCheapestEmi()
    {
        return cheapestEmi;
    }

    public Map getDynamicImageUrl()
    {
        return dynamicImageUrl;
    }

    public String getFreeShippingLimit()
    {
        return freeShippingLimit;
    }

    public int getInfoLevel()
    {
        return infoLevel;
    }

    public InstallCareWidgetData getInstallCare()
    {
        return installCare;
    }

    public int getInstantCashBack()
    {
        return instantCashBack;
    }

    public ArrayList getKeyFeatures()
    {
        if (keyFeatures == null)
        {
            keyFeatures = new ArrayList();
        }
        return keyFeatures;
    }

    public long getLastUpdatedTimeStamp()
    {
        return lastUpdatedTimeStamp;
    }

    public String getMainTitle()
    {
        return mainTitle;
    }

    public ArrayList getMarketPlace()
    {
        if (marketPlace == null)
        {
            marketPlace = new ArrayList();
        }
        return marketPlace;
    }

    public MarketPlaceSeller getMarketPlaceSeller(String s)
    {
        ArrayList arraylist = getMarketPlace();
        for (int i = 0; i < arraylist.size(); i++)
        {
            MarketPlaceSeller marketplaceseller = (MarketPlaceSeller)arraylist.get(i);
            if (s.equals(marketplaceseller.getSellerDisplayName()))
            {
                return marketplaceseller;
            }
        }

        return null;
    }

    public int getMrp()
    {
        return mrp;
    }

    public ArrayList getOffers()
    {
        return offers;
    }

    public OmnitureData getOmnitureData()
    {
        if (omnitureData == null)
        {
            omnitureData = new OmnitureData();
        }
        return omnitureData;
    }

    public String getPin()
    {
        return pin;
    }

    public String getPreferredListingId()
    {
        return preferredListingId;
    }

    public MarketPlaceSeller getPreferredSeller()
    {
label0:
        {
            String s = getPreferredListingId();
            if (StringUtils.isNullOrEmpty(s))
            {
                break label0;
            }
            ArrayList arraylist = getMarketPlace();
            if (arraylist == null || arraylist.size() <= 0)
            {
                break label0;
            }
            Iterator iterator = arraylist.iterator();
            MarketPlaceSeller marketplaceseller;
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
                marketplaceseller = (MarketPlaceSeller)iterator.next();
            } while (StringUtils.isNullOrEmpty(marketplaceseller.getListId()) || !marketplaceseller.getListId().equals(s));
            return marketplaceseller;
        }
        return null;
    }

    public PriceWidget getPriceWidget()
    {
        return priceWidget;
    }

    public String getPrimaryImageId()
    {
        return primaryImageId;
    }

    public String getProductAltImage()
    {
        return productAltImage;
    }

    public ProductDescription getProductDescription()
    {
        return productDescription;
    }

    public String getProductErrorImage()
    {
        return productErrorImage;
    }

    public String getProductId()
    {
        return productId;
    }

    public Map getProductMultipleImage()
    {
        if (productMultipleImage == null)
        {
            productMultipleImage = new LinkedHashMap();
        }
        return productMultipleImage;
    }

    public ArrayList getProductOffers()
    {
        MarketPlaceSeller marketplaceseller = getPreferredSeller();
        if (marketplaceseller != null)
        {
            return marketplaceseller.getOffer();
        } else
        {
            return new ArrayList();
        }
    }

    public String getProductPageUrl()
    {
        return productPageUrl;
    }

    public String getProductPrimaryImage()
    {
        return productPrimaryImage;
    }

    public ArrayList getProductSpecification()
    {
        if (productSpecification == null)
        {
            productSpecification = new ArrayList();
        }
        return productSpecification;
    }

    public String getProductStatus()
    {
        return productStatus;
    }

    public String getProductStatusIntent()
    {
        return productStatusIntent;
    }

    public String getRequestId()
    {
        return requestId;
    }

    public String getReturnPolicy()
    {
        return returnPolicy;
    }

    public String getSellerId(String s)
    {
        ArrayList arraylist = getMarketPlace();
        for (int i = 0; i < arraylist.size(); i++)
        {
            MarketPlaceSeller marketplaceseller = (MarketPlaceSeller)arraylist.get(i);
            if (s.equals(marketplaceseller.getSellerDisplayName()))
            {
                return marketplaceseller.getSellerId();
            }
        }

        return null;
    }

    public String getSellerListingId(String s)
    {
        ArrayList arraylist = getMarketPlace();
        for (int i = 0; i < arraylist.size(); i++)
        {
            MarketPlaceSeller marketplaceseller = (MarketPlaceSeller)arraylist.get(i);
            if (s.equals(marketplaceseller.getSellerDisplayName()))
            {
                return marketplaceseller.getListId();
            }
        }

        return null;
    }

    public int getSellingPrice()
    {
        return sellingPrice;
    }

    public SizeChart getSizeChart()
    {
        return sizeChart;
    }

    public String getSizeChartUrl()
    {
        return sizeChartUrl;
    }

    public String getSlaMessage()
    {
        return slaMessage;
    }

    public String getSubTitle()
    {
        return subTitle;
    }

    public Map getSwatch()
    {
        if (swatch == null)
        {
            swatch = new LinkedHashMap();
        }
        return swatch;
    }

    public ArrayList getTitleNote()
    {
        return titleNote;
    }

    public UGCRating getUgc()
    {
        return ugc;
    }

    public String getVertical()
    {
        return vertical;
    }

    public String getWarranty()
    {
        return warranty;
    }

    public boolean isAudioBook()
    {
        return isAudioBook;
    }

    public boolean isBundle()
    {
        return isBundle;
    }

    public boolean isDigital()
    {
        return isDigital;
    }

    public boolean isDigitalMusic()
    {
        return isDigitalMusic;
    }

    public boolean isEbook()
    {
        return isEbook;
    }

    public boolean isEnableCheckout()
    {
        return enableCheckout;
    }

    public boolean isFKFirst()
    {
        return isFKFirst;
    }

    public boolean isHasExchangeOffer()
    {
        return hasExchangeOffer;
    }

    public boolean isShowCODHelpText()
    {
        return showCODHelpText;
    }

    public boolean isShowEMIOfferHelpText()
    {
        return showEMIOfferHelpText;
    }

    public boolean isShowInstantCashBack()
    {
        return showInstantCashBack;
    }

    public boolean isShowMrp()
    {
        return showMrp;
    }

    public void setAlsoSee(ArrayList arraylist)
    {
        alsoSee = arraylist;
    }

    public void setAppExtras(AppExtras appextras)
    {
        appExtras = appextras;
    }

    public void setAudioBook(boolean flag)
    {
        isAudioBook = flag;
    }

    public void setAvailabilityDetails(ProductAvailabilityDetails productavailabilitydetails)
    {
        availabilityDetails = productavailabilitydetails;
    }

    public void setBundle(boolean flag)
    {
        isBundle = flag;
    }

    public void setCallUsWidget(CallUsWidgetResponse calluswidgetresponse)
    {
        callUs = calluswidgetresponse;
    }

    public void setCheapestEmi(CheapestEmi cheapestemi)
    {
        cheapestEmi = cheapestemi;
    }

    public void setDigital(boolean flag)
    {
        isDigital = flag;
    }

    public void setDigitalMusic(boolean flag)
    {
        isDigitalMusic = flag;
    }

    public void setDynamicImageUrl(Map map)
    {
        dynamicImageUrl = map;
    }

    public void setEbook(boolean flag)
    {
        isEbook = flag;
    }

    public void setEnableCheckout(boolean flag)
    {
        enableCheckout = flag;
    }

    public void setFKFirst(boolean flag)
    {
        isFKFirst = flag;
    }

    public void setFreeShippingLimit(String s)
    {
        freeShippingLimit = s;
    }

    public void setHasExchangeOffer(boolean flag)
    {
        hasExchangeOffer = flag;
    }

    public void setInfoLevel(int i)
    {
        infoLevel = i;
    }

    public void setInstallCare(InstallCareWidgetData installcarewidgetdata)
    {
        installCare = installcarewidgetdata;
    }

    public void setInstantCashBack(int i)
    {
        instantCashBack = i;
    }

    public void setKeyFeatures(ArrayList arraylist)
    {
        keyFeatures = arraylist;
    }

    public void setLastUpdatedTimeStamp(long l)
    {
        lastUpdatedTimeStamp = l;
    }

    public void setMainTitle(String s)
    {
        mainTitle = s;
    }

    public void setMarketPlace(ArrayList arraylist)
    {
        marketPlace = arraylist;
    }

    public void setMrp(int i)
    {
        mrp = i;
    }

    public void setOffers(ArrayList arraylist)
    {
        offers = arraylist;
    }

    public void setOmnitureData(OmnitureData omnituredata)
    {
        omnitureData = omnituredata;
    }

    public void setPin(String s)
    {
        pin = s;
    }

    public void setPreferredListingId(String s)
    {
        preferredListingId = s;
    }

    public void setPriceWidget(PriceWidget pricewidget)
    {
        priceWidget = pricewidget;
    }

    public void setPrimaryImageId(String s)
    {
        primaryImageId = s;
    }

    public void setProductAltImage(String s)
    {
        productAltImage = s;
    }

    public void setProductDescription(ProductDescription productdescription)
    {
        productDescription = productdescription;
    }

    public void setProductErrorImage(String s)
    {
        productErrorImage = s;
    }

    public void setProductId(String s)
    {
        productId = s;
    }

    public void setProductMultipleImage(Map map)
    {
        productMultipleImage = map;
    }

    public void setProductPageUrl(String s)
    {
        productPageUrl = s;
    }

    public void setProductPrimaryImage(String s)
    {
        productPrimaryImage = s;
    }

    public void setProductSpecification(ArrayList arraylist)
    {
        productSpecification = arraylist;
    }

    public void setProductStatus(String s)
    {
        productStatus = s;
    }

    public void setProductStatusIntent(String s)
    {
        productStatusIntent = s;
    }

    public void setRequestId(String s)
    {
        requestId = s;
    }

    public void setReturnPolicy(String s)
    {
        returnPolicy = s;
    }

    public void setSellingPrice(int i)
    {
        sellingPrice = i;
    }

    public void setShouldOverlayOfferImage(boolean flag)
    {
        shouldOverlayOfferImage = flag;
    }

    public void setShowCODHelpText(boolean flag)
    {
        showCODHelpText = flag;
    }

    public void setShowEMIOfferHelpText(boolean flag)
    {
        showEMIOfferHelpText = flag;
    }

    public void setShowInstantCashBack(boolean flag)
    {
        showInstantCashBack = flag;
    }

    public void setShowMrp(boolean flag)
    {
        showMrp = flag;
    }

    public void setSizeChart(SizeChart sizechart)
    {
        sizeChart = sizechart;
    }

    public void setSizeChartUrl(String s)
    {
        sizeChartUrl = s;
    }

    public void setSlaMessage(String s)
    {
        slaMessage = s;
    }

    public void setSubTitle(String s)
    {
        subTitle = s;
    }

    public void setSwatch(Map map)
    {
        swatch = map;
    }

    public void setTitleNote(ArrayList arraylist)
    {
        titleNote = arraylist;
    }

    public void setUgc(UGCRating ugcrating)
    {
        ugc = ugcrating;
    }

    public void setVertical(String s)
    {
        vertical = s;
    }

    public void setWarranty(String s)
    {
        warranty = s;
    }

    public boolean shouldOverLayOfferImage()
    {
        return shouldOverlayOfferImage;
    }

    private class ImageProfileData
    {

        public String height;
        public String quality;
        public String width;

        public static ImageProfileData parseString(String s)
        {
            String as[] = s.split(",");
            if (as.length == 3)
            {
                ImageProfileData imageprofiledata = new ImageProfileData();
                imageprofiledata.height = as[0];
                imageprofiledata.width = as[1];
                imageprofiledata.quality = as[2];
                return imageprofiledata;
            } else
            {
                return null;
            }
        }

        public ImageProfileData()
        {
        }
    }

}
