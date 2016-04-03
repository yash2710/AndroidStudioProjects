// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.crittercism.NotificationActivity;

// Referenced classes of package com.crittercism.app:
//            Crittercism

final class f extends Handler
{

    private Crittercism a;

    f(Crittercism crittercism)
    {
        a = crittercism;
        super();
    }

    public final void handleMessage(Message message)
    {
        Bundle bundle;
        super.handleMessage(message);
        bundle = message.getData();
        if (!bundle.containsKey("notification"))
        {
            break MISSING_BLOCK_LABEL_67;
        }
        Intent intent = new Intent(Crittercism.c(a), com/crittercism/NotificationActivity);
        intent.setFlags(0x10400000);
        intent.putExtra("com.crittercism.notification", bundle.getString("notification"));
        Crittercism.c(a).startActivity(intent);
        return;
        Exception exception;
        exception;
    }
}
