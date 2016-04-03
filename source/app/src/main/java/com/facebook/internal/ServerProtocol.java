// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.internal;

import com.facebook.Settings;
import java.util.Collection;

// Referenced classes of package com.facebook.internal:
//            Utility

public final class ServerProtocol
{

    public static final String BATCHED_REST_METHOD_URL_BASE = "method/";
    private static final String DIALOG_AUTHORITY_FORMAT = "m.%s";
    public static final String DIALOG_PARAM_ACCESS_TOKEN = "access_token";
    public static final String DIALOG_PARAM_APP_ID = "app_id";
    public static final String DIALOG_PARAM_CLIENT_ID = "client_id";
    public static final String DIALOG_PARAM_DISPLAY = "display";
    public static final String DIALOG_PARAM_E2E = "e2e";
    public static final String DIALOG_PARAM_REDIRECT_URI = "redirect_uri";
    public static final String DIALOG_PARAM_SCOPE = "scope";
    public static final String DIALOG_PARAM_TYPE = "type";
    public static final String DIALOG_PATH = "dialog/";
    private static final String GRAPH_URL_FORMAT = "https://graph.%s";
    private static final String REST_URL_FORMAT = "https://api.%s/method";
    public static final Collection errorsProxyAuthDisabled = Utility.unmodifiableCollection(new String[] {
        "service_disabled", "AndroidAuthKillSwitchException"
    });
    public static final Collection errorsUserCanceled = Utility.unmodifiableCollection(new String[] {
        "access_denied", "OAuthAccessDeniedException"
    });

    public ServerProtocol()
    {
    }

    public static final String getDialogAuthority()
    {
        Object aobj[] = new Object[1];
        aobj[0] = Settings.getFacebookDomain();
        return String.format("m.%s", aobj);
    }

    public static final String getGraphUrlBase()
    {
        Object aobj[] = new Object[1];
        aobj[0] = Settings.getFacebookDomain();
        return String.format("https://graph.%s", aobj);
    }

    public static final String getRestUrlBase()
    {
        Object aobj[] = new Object[1];
        aobj[0] = Settings.getFacebookDomain();
        return String.format("https://api.%s/method", aobj);
    }

}
