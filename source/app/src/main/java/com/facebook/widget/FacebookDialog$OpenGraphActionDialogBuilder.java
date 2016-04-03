// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.widget;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphObjectException;
import com.facebook.NativeAppCallContentProvider;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.OpenGraphAction;
import com.facebook.model.OpenGraphObject;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.facebook.widget:
//            FacebookDialog

public class previewPropertyName extends previewPropertyName
{

    private OpenGraphAction action;
    private String actionType;
    private boolean dataErrorsFatal;
    private HashMap imageAttachmentFiles;
    private HashMap imageAttachments;
    private String previewPropertyName;

    private previewPropertyName addImageAttachment(String s, Bitmap bitmap)
    {
        if (imageAttachments == null)
        {
            imageAttachments = new HashMap();
        }
        imageAttachments.put(s, bitmap);
        return this;
    }

    private imageAttachments addImageAttachment(String s, File file)
    {
        if (imageAttachmentFiles == null)
        {
            imageAttachmentFiles = new HashMap();
        }
        imageAttachmentFiles.put(s, file);
        return this;
    }

    private List addImageAttachmentFiles(List list)
    {
        ArrayList arraylist = new ArrayList();
        String s;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add(NativeAppCallContentProvider.getAttachmentUrl(applicationId, appCall.appCall(), s)))
        {
            File file = (File)iterator.next();
            s = UUID.randomUUID().toString();
            addImageAttachment(s, file);
        }

        return arraylist;
    }

    private List addImageAttachments(List list)
    {
        ArrayList arraylist = new ArrayList();
        String s;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add(NativeAppCallContentProvider.getAttachmentUrl(applicationId, appCall.appCall(), s)))
        {
            Bitmap bitmap = (Bitmap)iterator.next();
            s = UUID.randomUUID().toString();
            addImageAttachment(s, bitmap);
        }

        return arraylist;
    }

    private JSONObject flattenChildrenOfGraphObject(JSONObject jsonobject)
    {
        JVM INSTR new #171 <Class JSONObject>;
        if (jsonobject instanceof JSONObject) goto _L2; else goto _L1
_L1:
        String s1 = jsonobject.toString();
_L4:
        JSONException jsonexception;
        JSONObject jsonobject1;
        jsonobject1 = JSONObjectInstrumentation.init(s1);
        Iterator iterator = jsonobject1.keys();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s2 = (String)iterator.next();
            if (!s2.equalsIgnoreCase("image"))
            {
                jsonobject1.put(s2, flattenObject(jsonobject1.get(s2)));
            }
        } while (true);
        break; /* Loop/switch isn't completed */
_L2:
        String s;
        try
        {
            s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        }
        // Misplaced declaration of an exception variable
        catch (JSONException jsonexception)
        {
            throw new FacebookException(jsonexception);
        }
        s1 = s;
        if (true) goto _L4; else goto _L3
_L3:
        return jsonobject1;
    }

    private Object flattenObject(Object obj)
    {
        if (obj != null) goto _L2; else goto _L1
_L1:
        obj = null;
_L4:
        return obj;
_L2:
        JSONObject jsonobject;
        if (!(obj instanceof JSONObject))
        {
            continue; /* Loop/switch isn't completed */
        }
        jsonobject = (JSONObject)obj;
        if (jsonobject.optBoolean("fbsdk:create_object")) goto _L4; else goto _L3
_L3:
        if (jsonobject.has("id"))
        {
            return jsonobject.getString("id");
        }
        if (!jsonobject.has("url")) goto _L4; else goto _L5
_L5:
        return jsonobject.getString("url");
        if (!(obj instanceof JSONArray)) goto _L4; else goto _L6
_L6:
        JSONArray jsonarray = (JSONArray)obj;
        JSONArray jsonarray1 = new JSONArray();
        int i = jsonarray.length();
        for (int j = 0; j < i; j++)
        {
            jsonarray1.put(flattenObject(jsonarray.get(j)));
        }

        return jsonarray1;
    }

    private void updateActionAttachmentUrls(List list, boolean flag)
    {
        List list1 = action.getImage();
        Object obj;
        Iterator iterator;
        String s;
        JSONObject jsonobject;
        JSONException jsonexception;
        if (list1 == null)
        {
            obj = new ArrayList(list.size());
        } else
        {
            obj = list1;
        }
        iterator = list.iterator();
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        s = (String)iterator.next();
        jsonobject = new JSONObject();
        try
        {
            jsonobject.put("url", s);
        }
        // Misplaced declaration of an exception variable
        catch (JSONException jsonexception)
        {
            throw new FacebookException("Unable to attach images", jsonexception);
        }
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        jsonobject.put("user_generated", true);
        ((List) (obj)).add(jsonobject);
        break MISSING_BLOCK_LABEL_37;
_L2:
        action.setImage(((List) (obj)));
        return;
    }

    public volatile FacebookDialog build()
    {
        return super.action();
    }

    public volatile boolean canPresent()
    {
        return super.action();
    }

    List getImageAttachmentNames()
    {
        return new ArrayList(imageAttachments.keySet());
    }

    imageAttachments getOnPresentCallback()
    {
        class _cls1
            implements FacebookDialog.OnPresentCallback
        {

            final FacebookDialog.OpenGraphActionDialogBuilder this$0;

            public void onPresent(Context context)
            {
                if (imageAttachments != null && imageAttachments.size() > 0)
                {
                    FacebookDialog.access$500().addAttachmentsForCall(context, appCall.getCallId(), imageAttachments);
                }
                if (imageAttachmentFiles != null && imageAttachmentFiles.size() > 0)
                {
                    FacebookDialog.access$500().addAttachmentFilesForCall(context, appCall.getCallId(), imageAttachmentFiles);
                }
            }

            _cls1()
            {
                this$0 = FacebookDialog.OpenGraphActionDialogBuilder.this;
                super();
            }
        }

        return new _cls1();
    }

    Intent handleBuild(Bundle bundle)
    {
        putExtra(bundle, "com.facebook.platform.extra.PREVIEW_PROPERTY_NAME", previewPropertyName);
        putExtra(bundle, "com.facebook.platform.extra.ACTION_TYPE", actionType);
        bundle.putBoolean("com.facebook.platform.extra.DATA_FAILURES_FATAL", dataErrorsFatal);
        JSONObject jsonobject = flattenChildrenOfGraphObject(action.getInnerJSONObject());
        String s;
        int i;
        if (!(jsonobject instanceof JSONObject))
        {
            s = jsonobject.toString();
        } else
        {
            s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        }
        putExtra(bundle, "com.facebook.platform.extra.ACTION", s);
        i = FacebookDialog.access$300(activity, Integer.valueOf(0x1332b3a));
        return NativeProtocol.createPlatformActivityIntent(activity, "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", i, bundle);
    }

    boolean handleCanPresent()
    {
        Activity activity = this.activity;
        activity aactivity[] = new activity[1];
        aactivity[0] = OG_ACTION_DIALOG;
        return FacebookDialog.canPresentOpenGraphActionDialog(activity, aactivity);
    }

    public og setDataErrorsFatal(boolean flag)
    {
        dataErrorsFatal = flag;
        return this;
    }

    public dataErrorsFatal setImageAttachmentFilesForAction(List list)
    {
        return setImageAttachmentFilesForAction(list, false);
    }

    public setImageAttachmentFilesForAction setImageAttachmentFilesForAction(List list, boolean flag)
    {
        Validate.containsNoNulls(list, "bitmapFiles");
        if (action == null)
        {
            throw new FacebookException("Can not set attachments prior to setting action.");
        } else
        {
            updateActionAttachmentUrls(addImageAttachmentFiles(list), flag);
            return this;
        }
    }

    public addImageAttachmentFiles setImageAttachmentFilesForObject(String s, List list)
    {
        return setImageAttachmentFilesForObject(s, list, false);
    }

    public setImageAttachmentFilesForObject setImageAttachmentFilesForObject(String s, List list, boolean flag)
    {
        Validate.notNull(s, "objectProperty");
        Validate.containsNoNulls(list, "bitmapFiles");
        if (action == null)
        {
            throw new FacebookException("Can not set attachments prior to setting action.");
        } else
        {
            updateObjectAttachmentUrls(s, addImageAttachmentFiles(list), flag);
            return this;
        }
    }

    public addImageAttachmentFiles setImageAttachmentsForAction(List list)
    {
        return setImageAttachmentsForAction(list, false);
    }

    public setImageAttachmentsForAction setImageAttachmentsForAction(List list, boolean flag)
    {
        Validate.containsNoNulls(list, "bitmaps");
        if (action == null)
        {
            throw new FacebookException("Can not set attachments prior to setting action.");
        } else
        {
            updateActionAttachmentUrls(addImageAttachments(list), flag);
            return this;
        }
    }

    public addImageAttachments setImageAttachmentsForObject(String s, List list)
    {
        return setImageAttachmentsForObject(s, list, false);
    }

    public setImageAttachmentsForObject setImageAttachmentsForObject(String s, List list, boolean flag)
    {
        Validate.notNull(s, "objectProperty");
        Validate.containsNoNulls(list, "bitmaps");
        if (action == null)
        {
            throw new FacebookException("Can not set attachments prior to setting action.");
        } else
        {
            updateObjectAttachmentUrls(s, addImageAttachments(list), flag);
            return this;
        }
    }

    void updateObjectAttachmentUrls(String s, List list, boolean flag)
    {
        OpenGraphObject opengraphobject;
        try
        {
            opengraphobject = (OpenGraphObject)action.getPropertyAs(s, com/facebook/model/OpenGraphObject);
        }
        catch (FacebookGraphObjectException facebookgraphobjectexception)
        {
            throw new IllegalArgumentException((new StringBuilder("Property '")).append(s).append("' is not a graph object").toString());
        }
        if (opengraphobject != null)
        {
            break MISSING_BLOCK_LABEL_87;
        }
        throw new IllegalArgumentException((new StringBuilder("Action does not contain a property '")).append(s).append("'").toString());
        if (!opengraphobject.getCreateObject())
        {
            throw new IllegalArgumentException((new StringBuilder("The Open Graph object in '")).append(s).append("' is not marked for creation").toString());
        }
        GraphObjectList graphobjectlist = opengraphobject.getImage();
        GraphObjectList graphobjectlist1;
        Iterator iterator;
        GraphObject graphobject;
        if (graphobjectlist == null)
        {
            graphobjectlist1 = com.facebook.model..action(com/facebook/model/GraphObject);
        } else
        {
            graphobjectlist1 = graphobjectlist;
        }
        for (iterator = list.iterator(); iterator.hasNext(); graphobjectlist1.add(graphobject))
        {
            String s1 = (String)iterator.next();
            graphobject = com.facebook.model..action();
            graphobject.setProperty("url", s1);
            if (flag)
            {
                graphobject.setProperty("user_generated", Boolean.valueOf(true));
            }
        }

        opengraphobject.setImage(graphobjectlist1);
        return;
    }



    public _cls1(Activity activity, OpenGraphAction opengraphaction, String s)
    {
        super(activity);
        Validate.notNull(opengraphaction, "action");
        Validate.notNullOrEmpty(opengraphaction.getType(), "action.getType()");
        Validate.notNullOrEmpty(s, "previewPropertyName");
        if (opengraphaction.getProperty(s) == null)
        {
            throw new IllegalArgumentException((new StringBuilder("A property named \"")).append(s).append("\" was not found on the action.  The name of the preview property must match the name of an action property.").toString());
        } else
        {
            action = opengraphaction;
            actionType = opengraphaction.getType();
            previewPropertyName = s;
            return;
        }
    }

    public previewPropertyName(Activity activity, OpenGraphAction opengraphaction, String s, String s1)
    {
        super(activity);
        Validate.notNull(opengraphaction, "action");
        Validate.notNullOrEmpty(s, "actionType");
        Validate.notNullOrEmpty(s1, "previewPropertyName");
        if (opengraphaction.getProperty(s1) == null)
        {
            throw new IllegalArgumentException((new StringBuilder("A property named \"")).append(s1).append("\" was not found on the action.  The name of the preview property must match the name of an action property.").toString());
        }
        String s2 = opengraphaction.getType();
        if (!Utility.isNullOrEmpty(s2) && !s2.equals(s))
        {
            throw new IllegalArgumentException("'actionType' must match the type of 'action' if it is specified. Consider using OpenGraphActionDialogBuilder(Activity activity, OpenGraphAction action, String previewPropertyName) instead.");
        } else
        {
            action = opengraphaction;
            actionType = s;
            previewPropertyName = s1;
            return;
        }
    }
}
