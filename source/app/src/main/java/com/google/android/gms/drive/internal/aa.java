// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.internal;

import android.content.IntentSender;
import android.os.IInterface;

// Referenced classes of package com.google.android.gms.drive.internal:
//            CreateFileIntentSenderRequest, OpenFileIntentSenderRequest, AddEventListenerRequest, ac, 
//            ab, AuthorizeAccessRequest, CheckResourceIdsExistRequest, CloseContentsAndUpdateMetadataRequest, 
//            CloseContentsRequest, CreateContentsRequest, CreateFileRequest, CreateFolderRequest, 
//            DeleteCustomPropertyRequest, DeleteResourceRequest, DisconnectRequest, GetDriveIdFromUniqueIdentifierRequest, 
//            GetMetadataRequest, ListParentsRequest, LoadRealtimeRequest, OpenContentsRequest, 
//            QueryRequest, RemoveEventListenerRequest, SetResourceParentsRequest, TrashResourceRequest, 
//            UpdateMetadataRequest

public interface aa
    extends IInterface
{

    public abstract IntentSender a(CreateFileIntentSenderRequest createfileintentsenderrequest);

    public abstract IntentSender a(OpenFileIntentSenderRequest openfileintentsenderrequest);

    public abstract void a(AddEventListenerRequest addeventlistenerrequest, ac ac, String s, ab ab);

    public abstract void a(AuthorizeAccessRequest authorizeaccessrequest, ab ab);

    public abstract void a(CheckResourceIdsExistRequest checkresourceidsexistrequest, ab ab);

    public abstract void a(CloseContentsAndUpdateMetadataRequest closecontentsandupdatemetadatarequest, ab ab);

    public abstract void a(CloseContentsRequest closecontentsrequest, ab ab);

    public abstract void a(CreateContentsRequest createcontentsrequest, ab ab);

    public abstract void a(CreateFileRequest createfilerequest, ab ab);

    public abstract void a(CreateFolderRequest createfolderrequest, ab ab);

    public abstract void a(DeleteCustomPropertyRequest deletecustompropertyrequest, ab ab);

    public abstract void a(DeleteResourceRequest deleteresourcerequest, ab ab);

    public abstract void a(DisconnectRequest disconnectrequest);

    public abstract void a(GetDriveIdFromUniqueIdentifierRequest getdriveidfromuniqueidentifierrequest, ab ab);

    public abstract void a(GetMetadataRequest getmetadatarequest, ab ab);

    public abstract void a(ListParentsRequest listparentsrequest, ab ab);

    public abstract void a(LoadRealtimeRequest loadrealtimerequest, ab ab);

    public abstract void a(OpenContentsRequest opencontentsrequest, ab ab);

    public abstract void a(QueryRequest queryrequest, ab ab);

    public abstract void a(RemoveEventListenerRequest removeeventlistenerrequest, ac ac, String s, ab ab);

    public abstract void a(SetResourceParentsRequest setresourceparentsrequest, ab ab);

    public abstract void a(TrashResourceRequest trashresourcerequest, ab ab);

    public abstract void a(UpdateMetadataRequest updatemetadatarequest, ab ab);

    public abstract void a(ab ab);

    public abstract void b(QueryRequest queryrequest, ab ab);

    public abstract void b(ab ab);

    public abstract void c(ab ab);

    public abstract void d(ab ab);

    public abstract void e(ab ab);
}
