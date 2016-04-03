// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.os.IInterface;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.realtime.internal.m;

// Referenced classes of package com.google.android.gms.drive.internal:
//            OnContentsResponse, OnDownloadProgressResponse, OnDriveIdResponse, OnListEntriesResponse, 
//            OnListParentsResponse, OnLoadRealtimeResponse, OnMetadataResponse, OnResourceIdSetResponse, 
//            OnStorageStatsResponse, OnSyncMoreResponse

public interface ab
    extends IInterface
{

    public abstract void a(OnContentsResponse oncontentsresponse);

    public abstract void a(OnDownloadProgressResponse ondownloadprogressresponse);

    public abstract void a(OnDriveIdResponse ondriveidresponse);

    public abstract void a(OnListEntriesResponse onlistentriesresponse);

    public abstract void a(OnListParentsResponse onlistparentsresponse);

    public abstract void a(OnLoadRealtimeResponse onloadrealtimeresponse, m m);

    public abstract void a(OnMetadataResponse onmetadataresponse);

    public abstract void a(OnResourceIdSetResponse onresourceidsetresponse);

    public abstract void a(OnStorageStatsResponse onstoragestatsresponse);

    public abstract void a(OnSyncMoreResponse onsyncmoreresponse);

    public abstract void o(Status status);

    public abstract void onSuccess();
}
