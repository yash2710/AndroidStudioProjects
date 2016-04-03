// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Camera;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.zxing.client.android.camera:
//            d, FrontLightMode

class b
{

    private final Context a;
    private Point b;
    private Point c;

    b(Context context)
    {
        a = context;
    }

    private Point a(android.hardware.Camera.Parameters parameters, Point point)
    {
        Point point1;
        float f;
        float f1;
        Iterator iterator1;
        List list = parameters.getSupportedPreviewSizes();
        if (list == null)
        {
            FkLogger.warn("CameraConfiguration", "Device returned no supported preview sizes; using default");
            android.hardware.Camera.Size size3 = parameters.getPreviewSize();
            return new Point(size3.width, size3.height);
        }
        ArrayList arraylist = new ArrayList(list);
        Collections.sort(arraylist, new d(this));
        if (Log.isLoggable("CameraConfiguration", 4))
        {
            StringBuilder stringbuilder = new StringBuilder();
            android.hardware.Camera.Size size2;
            for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); stringbuilder.append(size2.width).append('x').append(size2.height).append(' '))
            {
                size2 = (android.hardware.Camera.Size)iterator.next();
            }

            FkLogger.info("CameraConfiguration", (new StringBuilder("Supported preview sizes: ")).append(stringbuilder).toString());
        }
        point1 = null;
        f = (float)point.x / (float)point.y;
        f1 = (1.0F / 0.0F);
        iterator1 = arraylist.iterator();
_L3:
        if (!iterator1.hasNext()) goto _L2; else goto _L1
_L1:
        android.hardware.Camera.Size size1 = (android.hardware.Camera.Size)iterator1.next();
        int i = size1.width;
        int j = size1.height;
        int k = i * j;
        if (k >= 0x24b80 && k <= 0xfa000)
        {
            boolean flag;
            int l;
            int i1;
            if (i < j)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                l = j;
            } else
            {
                l = i;
            }
            if (flag)
            {
                i1 = i;
            } else
            {
                i1 = j;
            }
            if (l == point.x && i1 == point.y)
            {
                Point point3 = new Point(i, j);
                Log.i("CameraConfiguration", (new StringBuilder("Found preview size exactly matching screen size: ")).append(point3).toString());
                return point3;
            }
            float f2 = Math.abs((float)l / (float)i1 - f);
            android.hardware.Camera.Size size;
            Point point2;
            if (f2 < f1)
            {
                point2 = new Point(i, j);
            } else
            {
                f2 = f1;
                point2 = point1;
            }
            point1 = point2;
            f1 = f2;
        }
        if (true) goto _L3; else goto _L2
_L2:
        if (point1 == null)
        {
            size = parameters.getPreviewSize();
            point1 = new Point(size.width, size.height);
            Log.i("CameraConfiguration", (new StringBuilder("No suitable preview sizes, using default: ")).append(point1).toString());
        }
        Log.i("CameraConfiguration", (new StringBuilder("Found best approximate preview size: ")).append(point1).toString());
        return point1;
    }

    private static transient String a(Collection collection, String as[])
    {
        int i;
        int j;
        Log.i("CameraConfiguration", (new StringBuilder("Supported values: ")).append(collection).toString());
        if (collection == null)
        {
            break MISSING_BLOCK_LABEL_85;
        }
        i = as.length;
        j = 0;
_L3:
        String s;
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_85;
        }
        s = as[j];
        if (!collection.contains(s)) goto _L2; else goto _L1
_L1:
        Log.i("CameraConfiguration", (new StringBuilder("Settable value: ")).append(s).toString());
        return s;
_L2:
        j++;
          goto _L3
        s = null;
          goto _L1
    }

    private static void a(android.hardware.Camera.Parameters parameters, boolean flag)
    {
        String s;
        if (flag)
        {
            s = a(((Collection) (parameters.getSupportedFlashModes())), new String[] {
                "torch", "on"
            });
        } else
        {
            s = a(((Collection) (parameters.getSupportedFlashModes())), new String[] {
                "off"
            });
        }
        if (s != null)
        {
            parameters.setFlashMode(s);
        }
    }

    Point a()
    {
        return c;
    }

    void a(Camera camera)
    {
        android.hardware.Camera.Parameters parameters = camera.getParameters();
        Display display = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
        int i = display.getWidth();
        int j = display.getHeight();
        if (i < j)
        {
            FkLogger.info("CameraConfiguration", "Display reports portrait orientation; assuming this is incorrect");
        } else
        {
            int k = i;
            i = j;
            j = k;
        }
        b = new Point(j, i);
        FkLogger.info("CameraConfiguration", (new StringBuilder("Screen resolution: ")).append(b).toString());
        c = a(parameters, b);
        FkLogger.info("CameraConfiguration", (new StringBuilder("Camera resolution: ")).append(c).toString());
    }

    void a(Camera camera, boolean flag)
    {
        android.hardware.Camera.Parameters parameters = camera.getParameters();
        if (parameters == null)
        {
            FkLogger.warn("CameraConfiguration", "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i("CameraConfiguration", (new StringBuilder("Initial camera parameters: ")).append(parameters.flatten()).toString());
        if (flag)
        {
            FkLogger.warn("CameraConfiguration", "In camera config safe mode -- most settings will not be honored");
        }
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(a);
        boolean flag1;
        boolean flag2;
        String s;
        if (FrontLightMode.readPref(sharedpreferences) == FrontLightMode.ON)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        a(parameters, flag1);
        flag2 = sharedpreferences.getBoolean("preferences_auto_focus", true);
        s = null;
        if (flag2)
        {
            if (flag || sharedpreferences.getBoolean("preferences_disable_continuous_focus", false))
            {
                s = a(((Collection) (parameters.getSupportedFocusModes())), new String[] {
                    "auto"
                });
            } else
            {
                s = a(((Collection) (parameters.getSupportedFocusModes())), new String[] {
                    "continuous-picture", "continuous-video", "auto"
                });
            }
        }
        if (!flag && s == null)
        {
            s = a(((Collection) (parameters.getSupportedFocusModes())), new String[] {
                "macro", "edof"
            });
        }
        if (s != null)
        {
            parameters.setFocusMode(s);
        }
        if (sharedpreferences.getBoolean("preferences_invert_scan", false))
        {
            String s1 = a(((Collection) (parameters.getSupportedColorEffects())), new String[] {
                "negative"
            });
            if (s1 != null)
            {
                parameters.setColorEffect(s1);
            }
        }
        parameters.setPreviewSize(c.x, c.y);
        camera.setParameters(parameters);
    }

    Point b()
    {
        return b;
    }

    void b(Camera camera, boolean flag)
    {
        android.hardware.Camera.Parameters parameters = camera.getParameters();
        a(parameters, flag);
        camera.setParameters(parameters);
    }
}
