// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.plus;

import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.plus:
//            PlusClient

class abw
    implements com.google.android.gms.common.api.LoadedListener
{

    final PlusClient abv;
    final PeopleLoadedListener abw;

    public void a(opleResult opleresult)
    {
        abw.onPeopleLoaded(opleresult.getStatus().eR(), opleresult.getPersonBuffer(), opleresult.getNextPageToken());
    }

    public volatile void a(Object obj)
    {
        a((opleResult)obj);
    }

    PeopleLoadedListener(PlusClient plusclient, PeopleLoadedListener peopleloadedlistener)
    {
        abv = plusclient;
        abw = peopleloadedlistener;
        super();
    }
}
