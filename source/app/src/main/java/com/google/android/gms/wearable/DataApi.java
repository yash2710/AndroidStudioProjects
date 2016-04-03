// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable;

import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

// Referenced classes of package com.google.android.gms.wearable:
//            Asset, DataItemAsset, PutDataRequest

public interface DataApi
{

    public abstract PendingResult addListener(GoogleApiClient googleapiclient, DataListener datalistener);

    public abstract PendingResult deleteDataItems(GoogleApiClient googleapiclient, Uri uri);

    public abstract PendingResult getDataItem(GoogleApiClient googleapiclient, Uri uri);

    public abstract PendingResult getDataItems(GoogleApiClient googleapiclient);

    public abstract PendingResult getDataItems(GoogleApiClient googleapiclient, Uri uri);

    public abstract PendingResult getFdForAsset(GoogleApiClient googleapiclient, Asset asset);

    public abstract PendingResult getFdForAsset(GoogleApiClient googleapiclient, DataItemAsset dataitemasset);

    public abstract PendingResult putDataItem(GoogleApiClient googleapiclient, PutDataRequest putdatarequest);

    public abstract PendingResult removeListener(GoogleApiClient googleapiclient, DataListener datalistener);
}
