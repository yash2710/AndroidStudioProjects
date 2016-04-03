// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.widget;

import android.content.Context;
import com.facebook.NativeAppCallAttachmentStore;
import java.util.HashMap;

// Referenced classes of package com.facebook.widget:
//            FacebookDialog

class this._cls0
    implements this._cls0
{

    final this._cls0 this$0;

    public void onPresent(Context context)
    {
        if (cess._mth400(this._cls0.this) != null && cess._mth400(this._cls0.this).size() > 0)
        {
            FacebookDialog.access$500().addAttachmentsForCall(context, pCall.pCall(), cess._mth400(this._cls0.this));
        }
        if (cess._mth600(this._cls0.this) != null && cess._mth600(this._cls0.this).size() > 0)
        {
            FacebookDialog.access$500().addAttachmentFilesForCall(context, pCall.pCall(), cess._mth600(this._cls0.this));
        }
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
