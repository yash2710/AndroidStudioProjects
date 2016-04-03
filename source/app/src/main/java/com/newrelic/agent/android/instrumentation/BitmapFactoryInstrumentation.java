// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.TypedValue;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            MetricCategory

public class BitmapFactoryInstrumentation
{

    private static final ArrayList a;

    private BitmapFactoryInstrumentation()
    {
    }

    public static Bitmap decodeByteArray(byte abyte0[], int i, int j)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeByteArray", a);
        Bitmap bitmap = BitmapFactory.decodeByteArray(abyte0, i, j);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeByteArray(byte abyte0[], int i, int j, android.graphics.BitmapFactory.Options options)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeByteArray", a);
        Bitmap bitmap = BitmapFactory.decodeByteArray(abyte0, i, j, options);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeFile(String s)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeFile", a);
        Bitmap bitmap = BitmapFactory.decodeFile(s);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeFile(String s, android.graphics.BitmapFactory.Options options)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeFile", a);
        Bitmap bitmap = BitmapFactory.decodeFile(s, options);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeFileDescriptor(FileDescriptor filedescriptor)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeFileDescriptor", a);
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(filedescriptor);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeFileDescriptor(FileDescriptor filedescriptor, Rect rect, android.graphics.BitmapFactory.Options options)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeFileDescriptor", a);
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(filedescriptor, rect, options);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeResource(Resources resources, int i)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeResource", a);
        Bitmap bitmap = BitmapFactory.decodeResource(resources, i);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeResource(Resources resources, int i, android.graphics.BitmapFactory.Options options)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeResource", a);
        Bitmap bitmap = BitmapFactory.decodeResource(resources, i, options);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeResourceStream(Resources resources, TypedValue typedvalue, InputStream inputstream, Rect rect, android.graphics.BitmapFactory.Options options)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeResourceStream", a);
        Bitmap bitmap = BitmapFactory.decodeResourceStream(resources, typedvalue, inputstream, rect, options);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeStream(InputStream inputstream)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeStream", a);
        Bitmap bitmap = BitmapFactory.decodeStream(inputstream);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeStream(InputStream inputstream, Rect rect, android.graphics.BitmapFactory.Options options)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeStream", a);
        Bitmap bitmap = BitmapFactory.decodeStream(inputstream, rect, options);
        TraceMachine.exitMethod();
        return bitmap;
    }

    static 
    {
        String as[] = new String[3];
        as[0] = "category";
        as[1] = com/newrelic/agent/android/instrumentation/MetricCategory.getName();
        as[2] = "IMAGE";
        a = new ArrayList(Arrays.asList(as));
    }
}
