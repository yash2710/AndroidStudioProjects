// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;


// Referenced classes of package com.android.volley:
//            Request, RequestQueue

final class e
    implements RequestQueue.RequestFilter
{

    private Object a;

    e(RequestQueue requestqueue, Object obj)
    {
        a = obj;
        super();
    }

    public final boolean apply(Request request)
    {
        return request.getTag() == a;
    }
}
