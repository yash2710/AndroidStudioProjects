// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.internal;

import java.io.File;

// Referenced classes of package com.facebook.internal:
//            FileLruCache

class val.filesToDelete
    implements Runnable
{

    final FileLruCache this$0;
    final File val$filesToDelete[];

    public void run()
    {
        File afile[] = val$filesToDelete;
        int i = afile.length;
        for (int j = 0; j < i; j++)
        {
            afile[j].delete();
        }

    }

    ()
    {
        this$0 = final_filelrucache;
        val$filesToDelete = _5B_Ljava.io.File_3B_.this;
        super();
    }
}
