// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.util;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.conn.ConnectTimeoutException;

public final class NetworkFailure extends Enum
{

    public static final NetworkFailure BadServerResponse;
    public static final NetworkFailure BadURL;
    public static final NetworkFailure CannotConnectToHost;
    public static final NetworkFailure DNSLookupFailed;
    public static final NetworkFailure SecureConnectionFailed;
    public static final NetworkFailure TimedOut;
    public static final NetworkFailure Unknown;
    private static final NetworkFailure b[];
    private int a;

    private NetworkFailure(String s, int i, int j)
    {
        super(s, i);
        a = j;
    }

    public static int exceptionToErrorCode(Exception exception)
    {
        return exceptionToNetworkFailure(exception).getErrorCode();
    }

    public static NetworkFailure exceptionToNetworkFailure(Exception exception)
    {
        NetworkFailure networkfailure = Unknown;
        if (exception instanceof UnknownHostException)
        {
            networkfailure = DNSLookupFailed;
        } else
        {
            if ((exception instanceof SocketTimeoutException) || (exception instanceof ConnectTimeoutException))
            {
                return TimedOut;
            }
            if (exception instanceof ConnectException)
            {
                return CannotConnectToHost;
            }
            if (exception instanceof MalformedURLException)
            {
                return BadURL;
            }
            if (exception instanceof SSLException)
            {
                return SecureConnectionFailed;
            }
            if ((exception instanceof HttpResponseException) || (exception instanceof ClientProtocolException))
            {
                return BadServerResponse;
            }
        }
        return networkfailure;
    }

    public static NetworkFailure valueOf(String s)
    {
        return (NetworkFailure)Enum.valueOf(com/newrelic/agent/android/util/NetworkFailure, s);
    }

    public static NetworkFailure[] values()
    {
        return (NetworkFailure[])b.clone();
    }

    public final int getErrorCode()
    {
        return a;
    }

    static 
    {
        Unknown = new NetworkFailure("Unknown", 0, -1);
        BadURL = new NetworkFailure("BadURL", 1, -1000);
        TimedOut = new NetworkFailure("TimedOut", 2, -1001);
        CannotConnectToHost = new NetworkFailure("CannotConnectToHost", 3, -1004);
        DNSLookupFailed = new NetworkFailure("DNSLookupFailed", 4, -1006);
        BadServerResponse = new NetworkFailure("BadServerResponse", 5, -1011);
        SecureConnectionFailed = new NetworkFailure("SecureConnectionFailed", 6, -1200);
        NetworkFailure anetworkfailure[] = new NetworkFailure[7];
        anetworkfailure[0] = Unknown;
        anetworkfailure[1] = BadURL;
        anetworkfailure[2] = TimedOut;
        anetworkfailure[3] = CannotConnectToHost;
        anetworkfailure[4] = DNSLookupFailed;
        anetworkfailure[5] = BadServerResponse;
        anetworkfailure[6] = SecureConnectionFailed;
        b = anetworkfailure;
    }
}
