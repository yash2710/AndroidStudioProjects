// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;


// Referenced classes of package net.simonvt.menudrawer:
//            BuildLayerFrameLayout

final class b
    implements Runnable
{

    private BuildLayerFrameLayout a;

    b(BuildLayerFrameLayout buildlayerframelayout)
    {
        a = buildlayerframelayout;
        super();
    }

    public final void run()
    {
        if (BuildLayerFrameLayout.a(a) && (a.getLayerType() != 2 || BuildLayerFrameLayout.b(a)))
        {
            BuildLayerFrameLayout.b(a, false);
            a.setLayerType(2, null);
            a.buildLayer();
            a.setLayerType(0, null);
        }
    }
}
