// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable.internal;

import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.wearable.DataItem;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.gms.wearable.internal:
//            k, l

public final class o extends d
    implements DataItem
{

    private final int RG;

    public o(DataHolder dataholder, int i, int j)
    {
        super(dataholder, i);
        RG = j;
    }

    public final Object freeze()
    {
        return nr();
    }

    public final Map getAssets()
    {
        HashMap hashmap = new HashMap(RG);
        for (int i = 0; i < RG; i++)
        {
            k k1 = new k(DG, i + EC);
            if (k1.getDataItemKey() != null)
            {
                hashmap.put(k1.getDataItemKey(), k1);
            }
        }

        return hashmap;
    }

    public final byte[] getData()
    {
        return getByteArray("data");
    }

    public final Uri getUri()
    {
        return Uri.parse(getString("path"));
    }

    public final DataItem nr()
    {
        return new l(this);
    }

    public final DataItem setData(byte abyte0[])
    {
        throw new UnsupportedOperationException();
    }
}
