// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.measurement;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

final class f
    implements HostnameVerifier
{

    f()
    {
    }

    public final boolean verify(String s, SSLSession sslsession)
    {
        return true;
    }
}
