// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.internal.SessionAuthorizationType;
import com.facebook.internal.SessionTracker;
import com.facebook.model.GraphUser;

// Referenced classes of package com.facebook.widget:
//            LoginButton

class <init>
    implements android.view.stener
{

    final LoginButton this$0;

    public void onClick(View view)
    {
        if (fblistener != null && fblistener.onClick())
        {
            return;
        }
        android.content.Context context = getContext();
        final Session openSession = LoginButton.access$300(LoginButton.this).getOpenSession();
        int i;
        if (openSession != null)
        {
            if (LoginButton.access$600(LoginButton.this))
            {
                String s = getResources().getString(com.facebook.android._log_out_action);
                String s1 = getResources().getString(com.facebook.android._cancel_action);
                class _cls1
                    implements android.content.DialogInterface.OnClickListener
                {

                    final LoginButton.LoginClickListener this$1;
                    final Session val$openSession;

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        openSession.closeAndClearTokenInformation();
                    }

            _cls1()
            {
                this$1 = LoginButton.LoginClickListener.this;
                openSession = session;
                super();
            }
                }

                AppEventsLogger appeventslogger;
                Bundle bundle;
                String s2;
                android.app.Properties properties;
                if (LoginButton.access$400(LoginButton.this) != null && LoginButton.access$400(LoginButton.this).getName() != null)
                {
                    String s3 = getResources().getString(com.facebook.android._logged_in_as);
                    Object aobj[] = new Object[1];
                    aobj[0] = LoginButton.access$400(LoginButton.this).getName();
                    s2 = String.format(s3, aobj);
                } else
                {
                    s2 = getResources().getString(com.facebook.android._logged_in_using_facebook);
                }
                properties = new android.app.ook(context);
                properties.ook(s2).le(true).Button(s, new _cls1()).Button(s1, null);
                properties.ener().show();
            } else
            {
                openSession.closeAndClearTokenInformation();
            }
        } else
        {
            Session session = LoginButton.access$300(LoginButton.this).getSession();
            if (session == null || session.getState().isClosed())
            {
                LoginButton.access$300(LoginButton.this).setSession(null);
                session = (new com.facebook.ion(context)).d(LoginButton.access$700(LoginButton.this))._mth0();
                Session.setActiveSession(session);
            }
            if (!session.isOpened())
            {
                com.facebook.ner ner;
                if (LoginButton.access$800(LoginButton.this) != null)
                {
                    ner = new com.facebook.stener.this._cls0(LoginButton.access$800(LoginButton.this));
                } else
                if (context instanceof Activity)
                {
                    ner = new com.facebook.stener.this._cls0((Activity)context);
                } else
                {
                    ner = null;
                }
                if (ner != null)
                {
                    ner.udience(es.access._mth1000(LoginButton.access$900(LoginButton.this)));
                    ner.ons(es.access._mth1100(LoginButton.access$900(LoginButton.this)));
                    ner.avior(es.access._mth1200(LoginButton.access$900(LoginButton.this)));
                    if (SessionAuthorizationType.PUBLISH.equals(es.access._mth1300(LoginButton.access$900(LoginButton.this))))
                    {
                        session.openForPublish(ner);
                    } else
                    {
                        session.openForRead(ner);
                    }
                }
            }
        }
        appeventslogger = AppEventsLogger.newLogger(getContext());
        bundle = new Bundle();
        if (openSession != null)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        bundle.putInt("logging_in", i);
        appeventslogger.logSdkEvent(LoginButton.access$1400(LoginButton.this), null, bundle);
    }

    private es()
    {
        this$0 = LoginButton.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
