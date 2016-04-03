// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.internal.widget;

import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlSerializer;

// Referenced classes of package android.support.v7.internal.widget:
//            ActivityChooserModel

final class <init> extends AsyncTask
{

    final ActivityChooserModel this$0;

    public final volatile Object doInBackground(Object aobj[])
    {
        return doInBackground(aobj);
    }

    public final transient Void doInBackground(Object aobj[])
    {
        int i;
        List list;
        String s;
        i = 0;
        list = (List)aobj[0];
        s = (String)aobj[1];
        FileOutputStream fileoutputstream = ActivityChooserModel.access$200(ActivityChooserModel.this).openFileOutput(s, 0);
        XmlSerializer xmlserializer = Xml.newSerializer();
        int j;
        xmlserializer.setOutput(fileoutputstream, null);
        xmlserializer.startDocument("UTF-8", Boolean.valueOf(true));
        xmlserializer.startTag(null, "historical-records");
        j = list.size();
_L2:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        doInBackground doinbackground = (this._cls0)list.remove(0);
        xmlserializer.startTag(null, "historical-record");
        xmlserializer.attribute(null, "activity", doinbackground.y.flattenToString());
        xmlserializer.attribute(null, "time", String.valueOf(doinbackground.y));
        xmlserializer.attribute(null, "weight", String.valueOf(doinbackground.y));
        xmlserializer.endTag(null, "historical-record");
        i++;
        if (true) goto _L2; else goto _L1
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        Log.e(ActivityChooserModel.access$300(), (new StringBuilder("Error writing historical recrod file: ")).append(s).toString(), filenotfoundexception);
_L3:
        return null;
_L1:
        xmlserializer.endTag(null, "historical-records");
        xmlserializer.endDocument();
        ActivityChooserModel.access$502(ActivityChooserModel.this, true);
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.close();
            }
            catch (IOException ioexception5)
            {
                return null;
            }
            return null;
        }
          goto _L3
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        Log.e(ActivityChooserModel.access$300(), (new StringBuilder("Error writing historical recrod file: ")).append(ActivityChooserModel.access$400(ActivityChooserModel.this)).toString(), illegalargumentexception);
        ActivityChooserModel.access$502(ActivityChooserModel.this, true);
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.close();
            }
            catch (IOException ioexception4)
            {
                return null;
            }
            return null;
        }
          goto _L3
        IllegalStateException illegalstateexception;
        illegalstateexception;
        Log.e(ActivityChooserModel.access$300(), (new StringBuilder("Error writing historical recrod file: ")).append(ActivityChooserModel.access$400(ActivityChooserModel.this)).toString(), illegalstateexception);
        ActivityChooserModel.access$502(ActivityChooserModel.this, true);
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.close();
            }
            catch (IOException ioexception3)
            {
                return null;
            }
            return null;
        }
          goto _L3
        IOException ioexception1;
        ioexception1;
        Log.e(ActivityChooserModel.access$300(), (new StringBuilder("Error writing historical recrod file: ")).append(ActivityChooserModel.access$400(ActivityChooserModel.this)).toString(), ioexception1);
        ActivityChooserModel.access$502(ActivityChooserModel.this, true);
        if (fileoutputstream == null) goto _L3; else goto _L4
_L4:
        try
        {
            fileoutputstream.close();
        }
        catch (IOException ioexception2)
        {
            return null;
        }
        return null;
        Exception exception;
        exception;
        ActivityChooserModel.access$502(ActivityChooserModel.this, true);
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.close();
            }
            catch (IOException ioexception) { }
        }
        throw exception;
    }

    private ()
    {
        this$0 = ActivityChooserModel.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
