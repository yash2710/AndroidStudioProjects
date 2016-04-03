// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

// Referenced classes of package android.support.v4.print:
//            PrintHelperKitkat

final class mPrintHelper
    implements 
{

    private final PrintHelperKitkat mPrintHelper;

    public final int getColorMode()
    {
        return mPrintHelper.getColorMode();
    }

    public final int getOrientation()
    {
        return mPrintHelper.getOrientation();
    }

    public final int getScaleMode()
    {
        return mPrintHelper.getScaleMode();
    }

    public final void printBitmap(String s, Bitmap bitmap)
    {
        mPrintHelper.printBitmap(s, bitmap);
    }

    public final void printBitmap(String s, Uri uri)
    {
        mPrintHelper.printBitmap(s, uri);
    }

    public final void setColorMode(int i)
    {
        mPrintHelper.setColorMode(i);
    }

    public final void setOrientation(int i)
    {
        mPrintHelper.setOrientation(i);
    }

    public final void setScaleMode(int i)
    {
        mPrintHelper.setScaleMode(i);
    }

    (Context context)
    {
        mPrintHelper = new PrintHelperKitkat(context);
    }
}
