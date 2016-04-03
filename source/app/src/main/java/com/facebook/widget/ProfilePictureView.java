// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.facebook.FacebookException;
import com.facebook.LoggingBehavior;
import com.facebook.internal.ImageDownloader;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.ImageResponse;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.net.URISyntaxException;

public class ProfilePictureView extends FrameLayout
{

    private static final String BITMAP_HEIGHT_KEY = "ProfilePictureView_height";
    private static final String BITMAP_KEY = "ProfilePictureView_bitmap";
    private static final String BITMAP_WIDTH_KEY = "ProfilePictureView_width";
    public static final int CUSTOM = -1;
    private static final boolean IS_CROPPED_DEFAULT_VALUE = true;
    private static final String IS_CROPPED_KEY = "ProfilePictureView_isCropped";
    public static final int LARGE = -4;
    private static final int MIN_SIZE = 1;
    public static final int NORMAL = -3;
    private static final String PENDING_REFRESH_KEY = "ProfilePictureView_refresh";
    private static final String PRESET_SIZE_KEY = "ProfilePictureView_presetSize";
    private static final String PROFILE_ID_KEY = "ProfilePictureView_profileId";
    public static final int SMALL = -2;
    private static final String SUPER_STATE_KEY = "ProfilePictureView_superState";
    public static final String TAG = com/facebook/widget/ProfilePictureView.getSimpleName();
    private Bitmap customizedDefaultProfilePicture;
    private ImageView image;
    private Bitmap imageContents;
    private boolean isCropped;
    private ImageRequest lastRequest;
    private OnErrorListener onErrorListener;
    private int presetSizeType;
    private String profileId;
    private int queryHeight;
    private int queryWidth;

    public ProfilePictureView(Context context)
    {
        super(context);
        queryHeight = 0;
        queryWidth = 0;
        isCropped = true;
        presetSizeType = -1;
        customizedDefaultProfilePicture = null;
        initialize(context);
    }

    public ProfilePictureView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        queryHeight = 0;
        queryWidth = 0;
        isCropped = true;
        presetSizeType = -1;
        customizedDefaultProfilePicture = null;
        initialize(context);
        parseAttributes(attributeset);
    }

    public ProfilePictureView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        queryHeight = 0;
        queryWidth = 0;
        isCropped = true;
        presetSizeType = -1;
        customizedDefaultProfilePicture = null;
        initialize(context);
        parseAttributes(attributeset);
    }

    private int getPresetSizeInPixels(boolean flag)
    {
        presetSizeType;
        JVM INSTR tableswitch -4 -1: default 36
    //                   -4 58
    //                   -3 51
    //                   -2 38
    //                   -1 65;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return 0;
_L4:
        int i = com.facebook.android.R.dimen.com_facebook_profilepictureview_preset_size_small;
_L7:
        return getResources().getDimensionPixelSize(i);
_L3:
        i = com.facebook.android.R.dimen.com_facebook_profilepictureview_preset_size_normal;
        break; /* Loop/switch isn't completed */
_L2:
        i = com.facebook.android.R.dimen.com_facebook_profilepictureview_preset_size_large;
        break; /* Loop/switch isn't completed */
_L5:
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        i = com.facebook.android.R.dimen.com_facebook_profilepictureview_preset_size_normal;
        if (true) goto _L7; else goto _L6
_L6:
        if (true) goto _L1; else goto _L8
_L8:
    }

    private void initialize(Context context)
    {
        removeAllViews();
        image = new ImageView(context);
        android.widget.FrameLayout.LayoutParams layoutparams = new android.widget.FrameLayout.LayoutParams(-1, -1);
        image.setLayoutParams(layoutparams);
        image.setScaleType(android.widget.ImageView.ScaleType.CENTER_INSIDE);
        addView(image);
    }

    private void parseAttributes(AttributeSet attributeset)
    {
        TypedArray typedarray = getContext().obtainStyledAttributes(attributeset, com.facebook.android.R.styleable.com_facebook_profile_picture_view);
        setPresetSize(typedarray.getInt(0, -1));
        isCropped = typedarray.getBoolean(1, true);
        typedarray.recycle();
    }

    private void processResponse(ImageResponse imageresponse)
    {
        if (imageresponse.getRequest() != lastRequest) goto _L2; else goto _L1
_L1:
        Bitmap bitmap;
        Exception exception;
        lastRequest = null;
        bitmap = imageresponse.getBitmap();
        exception = imageresponse.getError();
        if (exception == null) goto _L4; else goto _L3
_L3:
        OnErrorListener onerrorlistener = onErrorListener;
        if (onerrorlistener == null) goto _L6; else goto _L5
_L5:
        onerrorlistener.onError(new FacebookException((new StringBuilder("Error in downloading profile picture for profileId: ")).append(getProfileId()).toString(), exception));
_L2:
        return;
_L6:
        Logger.log(LoggingBehavior.REQUESTS, 6, TAG, exception.toString());
        return;
_L4:
        if (bitmap != null)
        {
            setImageBitmap(bitmap);
            if (imageresponse.isCachedRedirect())
            {
                sendImageRequest(false);
                return;
            }
        }
        if (true) goto _L2; else goto _L7
_L7:
    }

    private void refreshImage(boolean flag)
    {
        boolean flag1 = updateImageQueryParameters();
        if (profileId == null || profileId.length() == 0 || queryWidth == 0 && queryHeight == 0)
        {
            setBlankProfilePicture();
        } else
        if (flag1 || flag)
        {
            sendImageRequest(true);
            return;
        }
    }

    private void sendImageRequest(boolean flag)
    {
        try
        {
            ImageRequest imagerequest = (new com.facebook.internal.ImageRequest.Builder(getContext(), ImageRequest.getProfilePictureUrl(profileId, queryWidth, queryHeight))).setAllowCachedRedirects(flag).setCallerTag(this).setCallback(new _cls1()).build();
            if (lastRequest != null)
            {
                ImageDownloader.cancelRequest(lastRequest);
            }
            lastRequest = imagerequest;
            ImageDownloader.downloadAsync(imagerequest);
            return;
        }
        catch (URISyntaxException urisyntaxexception)
        {
            Logger.log(LoggingBehavior.REQUESTS, 6, TAG, urisyntaxexception.toString());
        }
    }

    private void setBlankProfilePicture()
    {
        if (customizedDefaultProfilePicture == null)
        {
            int i;
            if (isCropped())
            {
                i = com.facebook.android.R.drawable.com_facebook_profile_picture_blank_square;
            } else
            {
                i = com.facebook.android.R.drawable.com_facebook_profile_picture_blank_portrait;
            }
            setImageBitmap(BitmapFactoryInstrumentation.decodeResource(getResources(), i));
            return;
        } else
        {
            updateImageQueryParameters();
            setImageBitmap(Bitmap.createScaledBitmap(customizedDefaultProfilePicture, queryWidth, queryHeight, false));
            return;
        }
    }

    private void setImageBitmap(Bitmap bitmap)
    {
        if (image != null && bitmap != null)
        {
            imageContents = bitmap;
            image.setImageBitmap(bitmap);
        }
    }

    private boolean updateImageQueryParameters()
    {
label0:
        {
            int i = getHeight();
            int j = getWidth();
            if (j <= 0 || i <= 0)
            {
                return false;
            }
            int k = getPresetSizeInPixels(false);
            int i1;
            boolean flag;
            if (k != 0)
            {
                i = k;
            } else
            {
                k = j;
            }
            if (k <= i)
            {
                if (isCropped())
                {
                    i1 = k;
                } else
                {
                    i1 = 0;
                }
            } else
            {
                int l;
                if (isCropped())
                {
                    l = i;
                } else
                {
                    l = 0;
                }
                k = l;
                i1 = i;
            }
            if (k == queryWidth)
            {
                int j1 = queryHeight;
                flag = false;
                if (i1 == j1)
                {
                    break label0;
                }
            }
            flag = true;
        }
        queryWidth = k;
        queryHeight = i1;
        return flag;
    }

    public final OnErrorListener getOnErrorListener()
    {
        return onErrorListener;
    }

    public final int getPresetSize()
    {
        return presetSizeType;
    }

    public final String getProfileId()
    {
        return profileId;
    }

    public final boolean isCropped()
    {
        return isCropped;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        lastRequest = null;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        refreshImage(false);
    }

    protected void onMeasure(int i, int j)
    {
        boolean flag = true;
        android.view.ViewGroup.LayoutParams layoutparams = getLayoutParams();
        int k = android.view.View.MeasureSpec.getSize(j);
        int l = android.view.View.MeasureSpec.getSize(i);
        int i1 = android.view.View.MeasureSpec.getMode(j);
        boolean flag1 = false;
        if (i1 != 0x40000000)
        {
            int k1 = layoutparams.height;
            flag1 = false;
            if (k1 == -2)
            {
                k = getPresetSizeInPixels(flag);
                j = android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000);
                flag1 = flag;
            }
        }
        int j1;
        if (android.view.View.MeasureSpec.getMode(i) != 0x40000000 && layoutparams.width == -2)
        {
            j1 = getPresetSizeInPixels(flag);
            i = android.view.View.MeasureSpec.makeMeasureSpec(j1, 0x40000000);
        } else
        {
            flag = flag1;
            j1 = l;
        }
        if (flag)
        {
            setMeasuredDimension(j1, k);
            measureChildren(i, j);
            return;
        } else
        {
            super.onMeasure(i, j);
            return;
        }
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if (parcelable.getClass() != android/os/Bundle)
        {
            super.onRestoreInstanceState(parcelable);
        } else
        {
            Bundle bundle = (Bundle)parcelable;
            super.onRestoreInstanceState(bundle.getParcelable("ProfilePictureView_superState"));
            profileId = bundle.getString("ProfilePictureView_profileId");
            presetSizeType = bundle.getInt("ProfilePictureView_presetSize");
            isCropped = bundle.getBoolean("ProfilePictureView_isCropped");
            queryWidth = bundle.getInt("ProfilePictureView_width");
            queryHeight = bundle.getInt("ProfilePictureView_height");
            setImageBitmap((Bitmap)bundle.getParcelable("ProfilePictureView_bitmap"));
            if (bundle.getBoolean("ProfilePictureView_refresh"))
            {
                refreshImage(true);
                return;
            }
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        Parcelable parcelable = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ProfilePictureView_superState", parcelable);
        bundle.putString("ProfilePictureView_profileId", profileId);
        bundle.putInt("ProfilePictureView_presetSize", presetSizeType);
        bundle.putBoolean("ProfilePictureView_isCropped", isCropped);
        bundle.putParcelable("ProfilePictureView_bitmap", imageContents);
        bundle.putInt("ProfilePictureView_width", queryWidth);
        bundle.putInt("ProfilePictureView_height", queryHeight);
        boolean flag;
        if (lastRequest != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        bundle.putBoolean("ProfilePictureView_refresh", flag);
        return bundle;
    }

    public final void setCropped(boolean flag)
    {
        isCropped = flag;
        refreshImage(false);
    }

    public final void setDefaultProfilePicture(Bitmap bitmap)
    {
        customizedDefaultProfilePicture = bitmap;
    }

    public final void setOnErrorListener(OnErrorListener onerrorlistener)
    {
        onErrorListener = onerrorlistener;
    }

    public final void setPresetSize(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Must use a predefined preset size");

        case -4: 
        case -3: 
        case -2: 
        case -1: 
            presetSizeType = i;
            break;
        }
        requestLayout();
    }

    public final void setProfileId(String s)
    {
        boolean flag;
label0:
        {
            if (!Utility.isNullOrEmpty(profileId))
            {
                boolean flag1 = profileId.equalsIgnoreCase(s);
                flag = false;
                if (flag1)
                {
                    break label0;
                }
            }
            setBlankProfilePicture();
            flag = true;
        }
        profileId = s;
        refreshImage(flag);
    }



    private class OnErrorListener
    {

        public abstract void onError(FacebookException facebookexception);
    }


    private class _cls1
        implements com.facebook.internal.ImageRequest.Callback
    {

        final ProfilePictureView this$0;

        public void onCompleted(ImageResponse imageresponse)
        {
            processResponse(imageresponse);
        }

        _cls1()
        {
            this$0 = ProfilePictureView.this;
            super();
        }
    }

}
