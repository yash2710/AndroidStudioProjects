// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.google.zxing.client.result.GeoParsedResult;
import com.google.zxing.client.result.ParsedResult;

// Referenced classes of package com.google.zxing.client.android.result:
//            ResultHandler

public final class GeoResultHandler extends ResultHandler
{

    private static final int a[];

    public GeoResultHandler(Activity activity, ParsedResult parsedresult)
    {
        super(activity, parsedresult);
    }

    public final int getButtonCount()
    {
        int[] _tmp = a;
        return 2;
    }

    public final int getButtonText(int i)
    {
        return a[i];
    }

    public final int getDisplayTitle()
    {
        return com.google.zxing.client.android.R.string.result_geo;
    }

    public final void handleButtonPress(int i)
    {
        GeoParsedResult geoparsedresult = (GeoParsedResult)getResult();
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            b(new Intent("android.intent.action.VIEW", Uri.parse(geoparsedresult.getGeoURI())));
            return;

        case 1: // '\001'
            a(geoparsedresult.getLatitude(), geoparsedresult.getLongitude());
            break;
        }
    }

    static 
    {
        int ai[] = new int[2];
        ai[0] = com.google.zxing.client.android.R.string.button_show_map;
        ai[1] = com.google.zxing.client.android.R.string.button_get_directions;
        a = ai;
    }
}
