// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.GalleryWidget;

import android.content.Context;
import android.view.ViewGroup;
import java.util.List;
import ru.truba.touchgallery.TouchView.TouchImageView;
import ru.truba.touchgallery.TouchView.UrlTouchImageView;

// Referenced classes of package ru.truba.touchgallery.GalleryWidget:
//            BasePagerAdapter, GalleryViewPager

public class UrlPagerAdapter extends BasePagerAdapter
{

    private android.view.View.OnClickListener a;
    private android.view.View.OnCreateContextMenuListener b;
    private TouchImageView c;

    public UrlPagerAdapter(Context context, List list, android.view.View.OnClickListener onclicklistener, android.view.View.OnCreateContextMenuListener oncreatecontextmenulistener)
    {
        super(context, list);
        a = null;
        b = null;
        c = null;
        a = onclicklistener;
        b = oncreatecontextmenulistener;
    }

    public TouchImageView getPresentView()
    {
        return c;
    }

    public Object instantiateItem(ViewGroup viewgroup, int i)
    {
        UrlTouchImageView urltouchimageview = new UrlTouchImageView(mContext, a, b);
        urltouchimageview.setUrl((String)mResources.get(i));
        urltouchimageview.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -1));
        viewgroup.addView(urltouchimageview, 0);
        return urltouchimageview;
    }

    public void setPrimaryItem(ViewGroup viewgroup, int i, Object obj)
    {
        super.setPrimaryItem(viewgroup, i, obj);
        ((GalleryViewPager)viewgroup).mCurrentView = ((UrlTouchImageView)obj).getImageView();
        c = ((GalleryViewPager)viewgroup).mCurrentView;
    }
}
