// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.logging.FkLogger;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.flipkart.android.utils:
//            x, NetworkMonitor

public class ScreenMathUtils
{

    public ScreenMathUtils()
    {
    }

    public static int dpToPx(int i, Context context)
    {
        if (context == null)
        {
            context = FlipkartApplication.getAppContext();
        }
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getMetrics(displaymetrics);
        float f = TypedValue.applyDimension(1, i, displaymetrics);
        if (f < 1.0F)
        {
            f = 1.0F;
        }
        return (int)f;
    }

    public static void fixForPopupBelowICS(PopupWindow popupwindow)
    {
        try
        {
            if (android.os.Build.VERSION.SDK_INT < 14)
            {
                Field field = android/widget/PopupWindow.getDeclaredField("mAnchor");
                field.setAccessible(true);
                Field field1 = android/widget/PopupWindow.getDeclaredField("mOnScrollChangedListener");
                field1.setAccessible(true);
                field1.set(popupwindow, new x(field, popupwindow, (android.view.ViewTreeObserver.OnScrollChangedListener)field1.get(popupwindow)));
            }
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            FkLogger.printStackTrace(illegalargumentexception);
            return;
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            FkLogger.printStackTrace(illegalaccessexception);
            return;
        }
        catch (NoSuchFieldException nosuchfieldexception)
        {
            FkLogger.printStackTrace(nosuchfieldexception);
            return;
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
        }
    }

    public static long getCurrentLinuxTimeInSeconds()
    {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }

    public static long getCurrentLinuxTimeStamp()
    {
        return System.currentTimeMillis() / 1000L;
    }

    public static long getCurrentLinuxTimeStampInMiliSeconds()
    {
        return System.currentTimeMillis();
    }

    public static int getRelativeLeft(View view)
    {
        if (view.getParent() == view.getRootView())
        {
            return view.getLeft();
        } else
        {
            return view.getLeft() + getRelativeLeft((View)view.getParent());
        }
    }

    public static int getScreenDpi(Context context)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager)context.getSystemService("window");
        windowmanager.getDefaultDisplay().getMetrics(displaymetrics);
        int i = windowmanager.getDefaultDisplay().getWidth();
        boolean flag;
        if (NetworkMonitor.isNetworkFast() == 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            i /= 2;
        }
        if (i <= 240)
        {
            return 240;
        }
        if (i <= 320)
        {
            return 320;
        }
        if (i <= 480)
        {
            return 480;
        }
        return i > 720 ? 1080 : 720;
    }

    public static String getScreenDpiString(Context context)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getMetrics(displaymetrics);
        if (displaymetrics.density <= 0.75F)
        {
            return "ldpi";
        }
        if (displaymetrics.density <= 1.0F)
        {
            return "mdpi";
        }
        if (displaymetrics.density <= 1.5F)
        {
            return "hdpi";
        }
        if (displaymetrics.density <= 2.0F)
        {
            return "xhdpi";
        }
        if (displaymetrics.density <= 3F)
        {
            return "xhdpi";
        }
        if (displaymetrics.density <= 4F)
        {
            return "xhdpi";
        } else
        {
            return "mdpi";
        }
    }

    public static String getScreenDpiStringOneUp(Context context)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getMetrics(displaymetrics);
        if (displaymetrics.density <= 0.75F)
        {
            return "mdpi";
        }
        if (displaymetrics.density <= 1.0F)
        {
            return "hdpi";
        }
        if (displaymetrics.density <= 1.5F)
        {
            return "xhdpi";
        }
        if (displaymetrics.density <= 2.0F)
        {
            return "xhdpi";
        }
        if (displaymetrics.density <= 3F)
        {
            return "xhdpi";
        }
        if (displaymetrics.density <= 4F)
        {
            return "xhdpi";
        } else
        {
            return "mdpi";
        }
    }

    public static int getScreenHeight()
    {
        return FlipkartApplication.getAppContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth()
    {
        return FlipkartApplication.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static long getSizeInBytes(Bitmap bitmap)
    {
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 12)
        {
            return (long)bitmap.getByteCount();
        } else
        {
            return (long)(bitmap.getRowBytes() * bitmap.getHeight());
        }
    }

    public static void unbindDrawables(View view)
    {
        if (view.getBackground() != null)
        {
            view.getBackground().setCallback(null);
            view.setBackgroundDrawable(null);
        }
        if (!(view instanceof ViewGroup))
        {
            break MISSING_BLOCK_LABEL_64;
        }
        int i = 0;
        do
        {
            try
            {
                if (i >= ((ViewGroup)view).getChildCount())
                {
                    break;
                }
                unbindDrawables(((ViewGroup)view).getChildAt(i));
            }
            catch (Exception exception)
            {
                return;
            }
            i++;
        } while (true);
        ((ViewGroup)view).removeAllViews();
    }
}
