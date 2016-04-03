// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;


// Referenced classes of package net.simonvt.menudrawer:
//            DraggableDrawer

final class g
    implements Runnable
{

    private DraggableDrawer a;

    g(DraggableDrawer draggabledrawer)
    {
        a = draggabledrawer;
        super();
    }

    public final void run()
    {
        a.startPeek();
    }
}
