// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.drive.internal.p;
import com.google.android.gms.drive.internal.t;
import com.google.android.gms.drive.internal.w;

// Referenced classes of package com.google.android.gms.drive:
//            DriveApi, b, d

public final class Drive
{

    public static final Api API;
    public static final DriveApi DriveApi = new p();
    public static final Scope HH = new Scope("https://www.googleapis.com/auth/drive");
    public static final Scope HI = new Scope("https://www.googleapis.com/auth/drive.apps");
    public static final Api HJ;
    public static final b HK = new t();
    public static final d HL = new w();
    public static final Scope SCOPE_APPFOLDER = new Scope("https://www.googleapis.com/auth/drive.appdata");
    public static final Scope SCOPE_FILE = new Scope("https://www.googleapis.com/auth/drive.file");
    public static final com.google.android.gms.common.api.Api.c yH;

    private Drive()
    {
    }

    static 
    {
        yH = new com.google.android.gms.common.api.Api.c();
        API = new Api(new _cls1(), yH, new Scope[0]);
        HJ = new Api(new _cls2(), yH, new Scope[0]);
    }

    private class _cls1 extends a
    {
        private class a
            implements com.google.android.gms.common.api.Api.b
        {

            protected abstract Bundle a(com.google.android.gms.common.api.Api.ApiOptions apioptions);

            public volatile com.google.android.gms.common.api.Api.a a(Context context, Looper looper, gy gy1, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
            {
                return a(context, looper, gy1, (com.google.android.gms.common.api.Api.ApiOptions)obj, connectioncallbacks, onconnectionfailedlistener);
            }

            public r a(Context context, Looper looper, gy gy1, com.google.android.gms.common.api.Api.ApiOptions apioptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
            {
                List list = gy1.fl();
                return new r(context, looper, gy1, connectioncallbacks, onconnectionfailedlistener, (String[])list.toArray(new String[list.size()]), a(apioptions));
            }

            public int getPriority()
            {
                return 0x7fffffff;
            }

            public a()
            {
            }
        }


        protected final Bundle a(com.google.android.gms.common.api.Api.ApiOptions.NoOptions nooptions)
        {
            return new Bundle();
        }

        protected final volatile Bundle a(com.google.android.gms.common.api.Api.ApiOptions apioptions)
        {
            return a((com.google.android.gms.common.api.Api.ApiOptions.NoOptions)apioptions);
        }

        _cls1()
        {
        }
    }


    private class _cls2 extends a
    {

        protected final volatile Bundle a(com.google.android.gms.common.api.Api.ApiOptions apioptions)
        {
            return a((b)apioptions);
        }

        protected final Bundle a(b b1)
        {
            if (b1 == null)
            {
                return new Bundle();
            } else
            {
                return b1.gj();
            }
        }

        _cls2()
        {
        }

        private class b
            implements com.google.android.gms.common.api.Api.ApiOptions.Optional
        {

            private final Bundle HM;

            public Bundle gj()
            {
                return HM;
            }

            private b()
            {
                this(new Bundle());
            }

            private b(Bundle bundle)
            {
                HM = bundle;
            }
        }

    }

}
