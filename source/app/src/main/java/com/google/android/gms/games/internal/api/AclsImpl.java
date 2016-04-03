// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.internal.game.Acls;

public final class AclsImpl
    implements Acls
{

    public AclsImpl()
    {
    }

    private static com.google.android.gms.games.internal.game.Acls.LoadAclResult x(Status status)
    {
        return new _cls1(status);
    }

    static com.google.android.gms.games.internal.game.Acls.LoadAclResult y(Status status)
    {
        return x(status);
    }

    private class _cls1
        implements com.google.android.gms.games.internal.game.Acls.LoadAclResult
    {

        final Status yJ;

        public final Status getStatus()
        {
            return yJ;
        }

        public final void release()
        {
        }

        _cls1(Status status)
        {
            yJ = status;
            super();
        }
    }

}
