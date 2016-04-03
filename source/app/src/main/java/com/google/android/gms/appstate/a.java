// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.appstate;

import com.google.android.gms.internal.hk;

// Referenced classes of package com.google.android.gms.appstate:
//            AppState

public final class a
    implements AppState
{

    private final int yB;
    private final String yC;
    private final byte yD[];
    private final boolean yE;
    private final String yF;
    private final byte yG[];

    public a(AppState appstate)
    {
        yB = appstate.getKey();
        yC = appstate.getLocalVersion();
        yD = appstate.getLocalData();
        yE = appstate.hasConflict();
        yF = appstate.getConflictVersion();
        yG = appstate.getConflictData();
    }

    static int a(AppState appstate)
    {
        Object aobj[] = new Object[6];
        aobj[0] = Integer.valueOf(appstate.getKey());
        aobj[1] = appstate.getLocalVersion();
        aobj[2] = appstate.getLocalData();
        aobj[3] = Boolean.valueOf(appstate.hasConflict());
        aobj[4] = appstate.getConflictVersion();
        aobj[5] = appstate.getConflictData();
        return hk.hashCode(aobj);
    }

    static boolean a(AppState appstate, Object obj)
    {
        if (obj instanceof AppState)
        {
            if (appstate == obj)
            {
                return true;
            }
            AppState appstate1 = (AppState)obj;
            if (hk.equal(Integer.valueOf(appstate1.getKey()), Integer.valueOf(appstate.getKey())) && hk.equal(appstate1.getLocalVersion(), appstate.getLocalVersion()) && hk.equal(appstate1.getLocalData(), appstate.getLocalData()) && hk.equal(Boolean.valueOf(appstate1.hasConflict()), Boolean.valueOf(appstate.hasConflict())) && hk.equal(appstate1.getConflictVersion(), appstate.getConflictVersion()) && hk.equal(appstate1.getConflictData(), appstate.getConflictData()))
            {
                return true;
            }
        }
        return false;
    }

    static String b(AppState appstate)
    {
        return hk.e(appstate).a("Key", Integer.valueOf(appstate.getKey())).a("LocalVersion", appstate.getLocalVersion()).a("LocalData", appstate.getLocalData()).a("HasConflict", Boolean.valueOf(appstate.hasConflict())).a("ConflictVersion", appstate.getConflictVersion()).a("ConflictData", appstate.getConflictData()).toString();
    }

    public final AppState dS()
    {
        return this;
    }

    public final boolean equals(Object obj)
    {
        return a(this, obj);
    }

    public final Object freeze()
    {
        return dS();
    }

    public final byte[] getConflictData()
    {
        return yG;
    }

    public final String getConflictVersion()
    {
        return yF;
    }

    public final int getKey()
    {
        return yB;
    }

    public final byte[] getLocalData()
    {
        return yD;
    }

    public final String getLocalVersion()
    {
        return yC;
    }

    public final boolean hasConflict()
    {
        return yE;
    }

    public final int hashCode()
    {
        return a(this);
    }

    public final boolean isDataValid()
    {
        return true;
    }

    public final String toString()
    {
        return b(this);
    }
}
