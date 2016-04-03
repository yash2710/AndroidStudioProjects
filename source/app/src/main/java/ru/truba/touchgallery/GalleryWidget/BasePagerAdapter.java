// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.GalleryWidget;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import ru.truba.touchgallery.TouchView.TouchImageView;
import ru.truba.touchgallery.TouchView.UrlTouchImageView;

// Referenced classes of package ru.truba.touchgallery.GalleryWidget:
//            GalleryViewPager

public class BasePagerAdapter extends PagerAdapter
{

    protected final Context mContext;
    protected int mCurrentPosition;
    protected OnItemChangeListener mOnItemChangeListener;
    protected final List mResources;

    public BasePagerAdapter()
    {
        mCurrentPosition = -1;
        mResources = null;
        mContext = null;
    }

    public BasePagerAdapter(Context context, List list)
    {
        mCurrentPosition = -1;
        mResources = list;
        mContext = context;
    }

    public void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        if (obj instanceof UrlTouchImageView)
        {
            ((UrlTouchImageView)obj).cancelOngoingRequest();
        }
        viewgroup.removeView((View)obj);
    }

    public void finishUpdate(ViewGroup viewgroup)
    {
    }

    public int getCount()
    {
        return mResources.size();
    }

    public int getCurrentPosition()
    {
        return mCurrentPosition;
    }

    public boolean isViewFromObject(View view, Object obj)
    {
        return view.equals(obj);
    }

    public void restoreState(Parcelable parcelable, ClassLoader classloader)
    {
    }

    public Parcelable saveState()
    {
        return null;
    }

    public void setOnItemChangeListener(OnItemChangeListener onitemchangelistener)
    {
        mOnItemChangeListener = onitemchangelistener;
    }

    public void setPrimaryItem(ViewGroup viewgroup, int i, Object obj)
    {
        super.setPrimaryItem(viewgroup, i, obj);
        if (mCurrentPosition != i)
        {
            GalleryViewPager galleryviewpager = (GalleryViewPager)viewgroup;
            if (galleryviewpager.mCurrentView != null)
            {
                galleryviewpager.mCurrentView.resetScale();
            }
            mCurrentPosition = i;
            if (mOnItemChangeListener != null)
            {
                mOnItemChangeListener.onItemChange(mCurrentPosition);
                return;
            }
        }
    }

    public void startUpdate(ViewGroup viewgroup)
    {
    }

    private class OnItemChangeListener
    {

        public abstract void onItemChange(int i);
    }

}
