// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import com.facebook.model.GraphMultiResult;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.facebook:
//            Response, AuthorizationClient

class val.tokenPermissions
    implements val.tokenPermissions
{

    final AuthorizationClient this$0;
    final ArrayList val$tokenPermissions;

    public void onCompleted(Response response)
    {
        GraphMultiResult graphmultiresult;
        GraphObjectList graphobjectlist;
        GraphObject graphobject;
        try
        {
            graphmultiresult = (GraphMultiResult)response.getGraphObjectAs(com/facebook/model/GraphMultiResult);
        }
        catch (Exception exception)
        {
            return;
        }
        if (graphmultiresult == null)
        {
            break MISSING_BLOCK_LABEL_71;
        }
        graphobjectlist = graphmultiresult.getData();
        if (graphobjectlist == null)
        {
            break MISSING_BLOCK_LABEL_71;
        }
        if (graphobjectlist.size() == 1)
        {
            graphobject = (GraphObject)graphobjectlist.get(0);
            val$tokenPermissions.addAll(graphobject.asMap().keySet());
        }
    }

    ()
    {
        this$0 = final_authorizationclient;
        val$tokenPermissions = ArrayList.this;
        super();
    }
}
