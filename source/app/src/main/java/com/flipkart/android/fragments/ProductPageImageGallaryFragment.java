// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.fragments.model.ProductPageModel;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.ContextCache;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.ToastMessageUtils;
import com.flipkart.android.utils.TwoStageImageCache;
import com.flipkart.logging.FkLogger;
import com.jakewharton.disklrucache.DiskLruImageCache;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import ru.truba.touchgallery.GalleryWidget.GalleryViewPager;
import ru.truba.touchgallery.GalleryWidget.UrlPagerAdapter;
import ru.truba.touchgallery.TouchView.TouchImageView;
import ru.truba.touchgallery.TouchView.UrlTouchImageView;

// Referenced classes of package com.flipkart.android.fragments:
//            FlipkartBaseFragment, ax, aB, ay, 
//            aA, aD, av, aw, 
//            az

public class ProductPageImageGallaryFragment extends FlipkartBaseFragment
    implements android.view.View.OnClickListener
{

    View a;
    Timer b;
    aB c;
    Handler d;
    boolean e;
    int f;
    private GalleryViewPager g;
    private FkProductListContext h;
    private ProductPageModel i;
    private int j;
    private String k;
    private Bitmap l;
    private ImageView m;
    private ImageView n;
    private ImageView o;
    private UrlPagerAdapter p;
    private ArrayList q;

    public ProductPageImageGallaryFragment()
    {
        a = null;
        g = null;
        b = new Timer();
        c = null;
        d = null;
        e = false;
        h = null;
        i = null;
        j = 0;
        f = 0;
        k = null;
        l = null;
        q = new ArrayList();
    }

    private void a()
    {
        com.android.volley.toolbox.ImageLoader imageloader = FlipkartApplication.getImageLoader();
        LinearLayout linearlayout = (LinearLayout)a.findViewById(0x7f0a018c);
        ((HorizontalScrollView)a.findViewById(0x7f0a018b)).setOnTouchListener(new ax(this));
        new ArrayList();
        ArrayList arraylist = i.getThumbnailsUrls();
        LayoutInflater layoutinflater = (LayoutInflater)activity.getSystemService("layout_inflater");
        Iterator iterator = arraylist.iterator();
        int k1;
        for (int i1 = 0; iterator.hasNext(); i1 = k1)
        {
            String s = (String)iterator.next();
            LinearLayout linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f0300a7, null);
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(83, activity), -1);
            int j1 = ScreenMathUtils.dpToPx(10, activity);
            layoutparams.setMargins(j1, j1, j1, j1);
            linearlayout1.setLayoutParams(layoutparams);
            NetworkImageView networkimageview = (NetworkImageView)linearlayout1.findViewById(0x7f0a004f);
            networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
            networkimageview.setImageUrl(s, imageloader);
            if (i1 == f)
            {
                linearlayout1.setBackgroundResource(0x7f02012b);
            }
            k1 = i1 + 1;
            linearlayout.addView(linearlayout1);
            networkimageview.setOnClickListener(this);
        }

    }

    public boolean handleBackPress()
    {
        if (g == null) goto _L2; else goto _L1
_L1:
        int i1;
        int j1;
        i1 = g.getChildCount();
        j1 = 0;
_L3:
        if (j1 >= i1)
        {
            break; /* Loop/switch isn't completed */
        }
        View view;
        UrlTouchImageView urltouchimageview;
        try
        {
            view = g.getChildAt(j1);
        }
        catch (Throwable throwable)
        {
            break; /* Loop/switch isn't completed */
        }
        if (view == null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        if (view instanceof UrlTouchImageView)
        {
            urltouchimageview = (UrlTouchImageView)view;
            urltouchimageview.cancelOngoingRequest();
            urltouchimageview.getImageView().setImageBitmap(null);
        }
        j1++;
        continue; /* Loop/switch isn't completed */
        if (true) goto _L3; else goto _L2
_L2:
        if (g != null)
        {
            g.invalidate();
            g = null;
        }
        if (a != null)
        {
            a.invalidate();
            a = null;
        }
        if (c != null && !c.isDone())
        {
            c.cancel();
        }
        CustomDialog.dismissDialog();
        ToastMessageUtils.dismissToast(activity);
        CrashLoggerUtils.pushAndUpdate("pressing back from productPageImageGallaryFrag");
        super.handleBackPress();
        return false;
    }

    public boolean handleOnClick()
    {
        return false;
    }

    public void hideProductImageThumbnails(View view)
    {
        if (view != null)
        {
            View view1 = view.findViewById(0x7f0a018b);
            TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, view1.getHeight());
            translateanimation.setDuration(200L);
            translateanimation.setFillAfter(false);
            translateanimation.setAnimationListener(new ay(this, view1));
            view1.startAnimation(translateanimation);
            e = true;
        }
    }

    protected void initActionBar()
    {
        View view = ActionBarView.setActionBarCustomView((HomeFragmentHolderActivity)getActivity(), ActionBarState.ProductImageGallery_Page);
        m = (ImageView)view.findViewById(0x7f0a0129);
        n = (ImageView)view.findViewById(0x7f0a0189);
        o = (ImageView)view.findViewById(0x7f0a0188);
        if (AppConfigUtils.getInstance().isEnableFlipkartFirst() && FlipkartPreferenceManager.instance().getUsersFfStatus().booleanValue())
        {
            m.setImageResource(0x7f0200eb);
        }
        m.setOnClickListener(new aA(this));
        n.setOnClickListener(this);
        o.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        if (a != null) goto _L2; else goto _L1
_L1:
        handleBackPress();
        popFragmentStack();
_L4:
        return;
_L2:
        String s;
        if (view.getTag() == null || !(view.getTag() instanceof String))
        {
            continue; /* Loop/switch isn't completed */
        }
        s = (String)view.getTag();
        if (s.contains("on_click_back"))
        {
            handleBackPress();
            popFragmentStack();
            return;
        }
        if (s.contains("load_homepage"))
        {
            ((HomeFragmentHolderActivity)activity).loadHomeFragment();
            return;
        }
        if (s.contains("save_image"))
        {
            TouchImageView touchimageview1 = p.getPresentView();
            if (touchimageview1 != null)
            {
                l = touchimageview1.getBitmap();
            }
            if (l != null)
            {
                Toast.makeText(activity, "Saving image on Gallery", 1).show();
                aD ad = new aD(this, l);
                Void avoid[] = new Void[0];
                if (!(ad instanceof AsyncTask))
                {
                    ad.execute(avoid);
                    return;
                } else
                {
                    AsyncTaskInstrumentation.execute((AsyncTask)ad, avoid);
                    return;
                }
            } else
            {
                ToastMessageUtils.showErrorToastMessage("Could not save Image on Gallery", activity, true);
                return;
            }
        }
        if (!s.contains("share_image")) goto _L4; else goto _L3
_L3:
        TouchImageView touchimageview = p.getPresentView();
        if (touchimageview != null)
        {
            l = touchimageview.getBitmap();
        }
        Bitmap bitmap = l;
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        if (bitmap != null)
        {
            bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, bytearrayoutputstream);
            try
            {
                long l1 = ScreenMathUtils.getCurrentLinuxTimeStampInMiliSeconds();
                String s1 = (new StringBuilder()).append(TwoStageImageCache.getInstance().getDiskCache().getFilePath()).append("/").append(l1).append(".jpg").toString();
                File file = new File(s1);
                file.createNewFile();
                FileOutputStream fileoutputstream = new FileOutputStream(file);
                fileoutputstream.write(bytearrayoutputstream.toByteArray());
                fileoutputstream.close();
                intent.putExtra("android.intent.extra.STREAM", Uri.parse((new StringBuilder("file:///")).append(s1).toString()));
                startActivity(Intent.createChooser(intent, "Share Image"));
                return;
            }
            catch (Exception exception)
            {
                FkLogger.printStackTrace(exception);
            }
        }
        ToastMessageUtils.showErrorToastMessage("Could not share the image", activity, true);
        return;
        if (e) goto _L4; else goto _L5
_L5:
        LinearLayout linearlayout = (LinearLayout)a.findViewById(0x7f0a018c);
        int i1 = linearlayout.indexOfChild((View)view.getParent());
        int j1 = 0;
        while (j1 < linearlayout.getChildCount()) 
        {
            if (j1 != i1)
            {
                linearlayout.getChildAt(j1).setBackgroundColor(Color.parseColor("#99EFEFEF"));
            } else
            {
                linearlayout.getChildAt(j1).setBackgroundResource(0x7f02012b);
            }
            j1++;
        }
        g.setCurrentItem(i1, true);
        if (!c.isDone())
        {
            c.cancel();
        }
        c = new aB(this);
        b.schedule(c, 5000L);
        return;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        super.onCreateView(layoutinflater, viewgroup, bundle);
        activity = (HomeFragmentHolderActivity)getActivity();
        d = new Handler();
        if (h == null)
        {
            Bundle bundle1 = getArguments();
            if (bundle1 != null)
            {
                j = bundle1.getInt("PRODUCT_PAGE_SELECTED_INDEX");
                f = bundle1.getInt("PRODUCT_PAGE_IMAGE_SELECTED_INDEX");
                bundle1.getString("PRODUCT_PAGE_SELECTED_PRODUCT");
                k = bundle1.getString("PRODUCT_PAGE_UUID");
                h = (FkProductListContext)ContextCache.getInstance().getResponse(k);
            }
        }
        a = layoutinflater.inflate(0x7f030084, viewgroup, false);
        if (FlipkartPreferenceManager.instance().isPoppingSearchFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(false));
            return a;
        }
        if (h == null)
        {
            ((HomeFragmentHolderActivity)activity).loadHomeFragmentNotImmediate();
            return a;
        }
        i = h.getProductModel();
        if (i == null)
        {
            ((HomeFragmentHolderActivity)activity).loadHomeFragmentNotImmediate();
            return a;
        }
        q = i.getOriginalUrls();
        if (q == null || q.size() == 0)
        {
            ((HomeFragmentHolderActivity)activity).loadHomeFragmentNotImmediate();
            return a;
        }
        FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.ProductImageGalleryPage);
        TrackingHelper.sendImageZoom();
        if (FlipkartPreferenceManager.instance().isFirstTimeProdImagePageLoad().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveFirstTimeProdImagePageLoad(Boolean.valueOf(false));
            if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 11 || FlipkartDeviceInfoProvider.isSDCardInstalled())
            {
                loadCueTips(0x7f030086);
            } else
            {
                loadCueTips(0x7f030085);
            }
        }
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 11 && FlipkartDeviceInfoProvider.isSDCardInstalled())
        {
            initActionBar();
        }
        try
        {
            h.getProductForId((String)h.getProductIds().get(j));
        }
        catch (Exception exception)
        {
            ((HomeFragmentHolderActivity)activity).loadHomeFragmentNotImmediate();
            return a;
        }
        p = new UrlPagerAdapter(activity, q, new av(this), this);
        g = (GalleryViewPager)a.findViewById(0x7f0a018a);
        g.setOnPageChangeListener(new aw(this));
        g.setOffscreenPageLimit(1);
        g.setAdapter(p);
        a();
        g.setCurrentItem(f, true);
        c = new aB(this);
        b.schedule(c, 5000L);
        return a;
    }

    public void onDestroy()
    {
        ((HomeFragmentHolderActivity)activity).showMenu();
        ((HomeFragmentHolderActivity)activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ToastMessageUtils.dismissToast(activity);
        super.onDestroy();
        a = null;
        g = null;
        b = null;
        c = null;
        d = null;
        h = null;
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        analyticData.setPageTypeUtils(PageTypeUtils.ProductImageGalleryPage);
        ((HomeFragmentHolderActivity)activity).showMenu();
        ((HomeFragmentHolderActivity)activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onFragmentPopped()
    {
    }

    public void onFragmentPushed()
    {
        CustomDialog.dismissDialog();
        ToastMessageUtils.dismissToast(activity);
        super.onFragmentPushed();
    }

    public void showProductImageThumbnails(View view)
    {
        if (view != null && e)
        {
            View view1 = view.findViewById(0x7f0a018b);
            TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, view1.getHeight(), 0.0F);
            translateanimation.setDuration(200L);
            translateanimation.setFillAfter(false);
            translateanimation.setAnimationListener(new az(this, view1));
            view1.startAnimation(translateanimation);
            e = false;
        }
    }
}
