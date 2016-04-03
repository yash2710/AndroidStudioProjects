// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import com.flipkart.logging.FkLogger;
import java.io.IOException;

// Referenced classes of package com.google.zxing.client.android:
//            c

final class b
{

    private static final String a = com/google/zxing/client/android/b.getSimpleName();
    private final Activity b;
    private MediaPlayer c;
    private boolean d;
    private boolean e;

    b(Activity activity)
    {
        b = activity;
        c = null;
        a();
    }

    private static MediaPlayer a(Context context)
    {
        MediaPlayer mediaplayer = new MediaPlayer();
        mediaplayer.setAudioStreamType(3);
        mediaplayer.setOnCompletionListener(new c());
        try
        {
            AssetFileDescriptor assetfiledescriptor = context.getResources().openRawResourceFd(R.raw.beep);
            mediaplayer.setDataSource(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getLength());
            assetfiledescriptor.close();
            mediaplayer.setVolume(0.1F, 0.1F);
            mediaplayer.prepare();
        }
        catch (IOException ioexception)
        {
            FkLogger.warn(a, ioexception);
            return null;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return mediaplayer;
    }

    final void a()
    {
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(b);
        Activity activity = b;
        boolean flag = sharedpreferences.getBoolean("preferences_play_beep", true);
        boolean flag1;
        if (flag && ((AudioManager)activity.getSystemService("audio")).getRingerMode() != 2)
        {
            flag1 = false;
        } else
        {
            flag1 = flag;
        }
        d = flag1;
        e = sharedpreferences.getBoolean("preferences_vibrate", false);
        if (d && c == null)
        {
            b.setVolumeControlStream(3);
            c = a(((Context) (b)));
        }
    }

    final void b()
    {
        if (d && c != null)
        {
            c.start();
        }
        if (e)
        {
            ((Vibrator)b.getSystemService("vibrator")).vibrate(200L);
        }
    }

}
