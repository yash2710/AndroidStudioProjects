// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;


// Referenced classes of package net.simonvt.menudrawer:
//            BuildLayerFrameLayout

final class a
    implements Runnable
{

    private BuildLayerFrameLayout a;

    a(BuildLayerFrameLayout buildlayerframelayout)
    {
        a = buildlayerframelayout;
        super();
    }

    public final void run()
    {
        BuildLayerFrameLayout.a(a, true);
        a.invalidate();
    }
}
