// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import com.facebook.internal.Utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

// Referenced classes of package com.facebook:
//            NativeAppCallAttachmentStore

class this._cls0
    implements ocessAttachment
{

    final NativeAppCallAttachmentStore this$0;

    public void processAttachment(File file, File file1)
    {
        FileOutputStream fileoutputstream = new FileOutputStream(file1);
        FileInputStream fileinputstream = new FileInputStream(file);
        byte abyte0[] = new byte[1024];
_L3:
        int i = fileinputstream.read(abyte0);
        if (i <= 0) goto _L2; else goto _L1
_L1:
        fileoutputstream.write(abyte0, 0, i);
          goto _L3
        Exception exception;
        exception;
_L5:
        Utility.closeQuietly(fileoutputstream);
        Utility.closeQuietly(fileinputstream);
        throw exception;
_L2:
        Utility.closeQuietly(fileoutputstream);
        Utility.closeQuietly(fileinputstream);
        return;
        exception;
        fileinputstream = null;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public volatile void processAttachment(Object obj, File file)
    {
        processAttachment((File)obj, file);
    }

    ocessAttachment()
    {
        this$0 = NativeAppCallAttachmentStore.this;
        super();
    }
}
