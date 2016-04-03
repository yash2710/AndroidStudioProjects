// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ru.truba.touchgallery.GalleryWidget;

import android.content.Context;
import android.view.ViewGroup;
import java.util.List;
import ru.truba.touchgallery.TouchView.FileTouchImageView;

// Referenced classes of package ru.truba.touchgallery.GalleryWidget:
//            BasePagerAdapter, GalleryViewPager

public class FilePagerAdapter extends BasePagerAdapter
{

    private android.view.View.OnClickListener a;

    public FilePagerAdapter(Context context, List list)
    {
        super(context, list);
        a = null;
    }

    public Object instantiateItem(ViewGroup viewgroup, int i)
    {
        FileTouchImageView filetouchimageview = new FileTouchImageView(mContext, null);
        filetouchimageview.setUrl((String)mResources.get(i));
        filetouchimageview.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -1));
        viewgroup.addView(filetouchimageview, 0);
        return filetouchimageview;
    }

    public void setPrimaryItem(ViewGroup viewgroup, int i, Object obj)
    {
        super.setPrimaryItem(viewgroup, i, obj);
        ((GalleryViewPager)viewgroup).mCurrentView = ((FileTouchImageView)obj).getImageView();
    }
}
