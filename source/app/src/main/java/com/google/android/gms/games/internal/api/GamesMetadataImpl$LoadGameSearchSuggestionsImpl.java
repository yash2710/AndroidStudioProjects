// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class _cls1 extends com.google.android.gms.games.gestionsImpl
{

    public com.google.android.gms.games.gestionsImpl D(Status status)
    {
        class _cls1
            implements com.google.android.gms.games.GamesMetadata.LoadGameSearchSuggestionsResult
        {

            final GamesMetadataImpl.LoadGameSearchSuggestionsImpl PC;
            final Status yJ;

            public Status getStatus()
            {
                return yJ;
            }

            public void release()
            {
            }

            _cls1(Status status)
            {
                PC = GamesMetadataImpl.LoadGameSearchSuggestionsImpl.this;
                yJ = status;
                super();
            }
        }

        return new _cls1(status);
    }

    public Result c(Status status)
    {
        return D(status);
    }

    private _cls1()
    {
    }
}
