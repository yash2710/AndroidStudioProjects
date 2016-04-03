// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.stream;

import com.google.mygson.internal.JsonReaderInternalAccess;
import com.google.mygson.internal.bind.JsonTreeReader;

// Referenced classes of package com.google.mygson.stream:
//            JsonReader

final class a extends JsonReaderInternalAccess
{

    a()
    {
    }

    public final void promoteNameToValue(JsonReader jsonreader)
    {
        if (jsonreader instanceof JsonTreeReader)
        {
            ((JsonTreeReader)jsonreader).promoteNameToValue();
            return;
        }
        int i = JsonReader.a(jsonreader);
        if (i == 0)
        {
            i = JsonReader.b(jsonreader);
        }
        if (i == 13)
        {
            JsonReader.a(jsonreader, 9);
            return;
        }
        if (i == 12)
        {
            JsonReader.a(jsonreader, 8);
            return;
        }
        if (i == 14)
        {
            JsonReader.a(jsonreader, 10);
            return;
        } else
        {
            throw new IllegalStateException((new StringBuilder("Expected a name but was ")).append(jsonreader.peek()).append("  at line ").append(JsonReader.c(jsonreader)).append(" column ").append(JsonReader.d(jsonreader)).toString());
        }
    }
}
