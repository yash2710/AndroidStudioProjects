// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal.bind;

import com.google.mygson.JsonSyntaxException;
import com.google.mygson.TypeAdapter;
import com.google.mygson.stream.JsonReader;
import com.google.mygson.stream.JsonToken;
import com.google.mygson.stream.JsonWriter;
import java.util.BitSet;

// Referenced classes of package com.google.mygson.internal.bind:
//            N

final class y extends TypeAdapter
{

    y()
    {
    }

    public final volatile Object read(JsonReader jsonreader)
    {
        return read(jsonreader);
    }

    public final BitSet read(JsonReader jsonreader)
    {
        BitSet bitset;
        JsonToken jsontoken;
        int i;
        if (jsonreader.peek() == JsonToken.NULL)
        {
            jsonreader.nextNull();
            return null;
        }
        bitset = new BitSet();
        jsonreader.beginArray();
        jsontoken = jsonreader.peek();
        i = 0;
_L2:
        boolean flag;
        if (jsontoken == JsonToken.END_ARRAY)
        {
            break MISSING_BLOCK_LABEL_204;
        }
        switch (N.a[jsontoken.ordinal()])
        {
        default:
            throw new JsonSyntaxException((new StringBuilder("Invalid bitset value type: ")).append(jsontoken).toString());

        case 2: // '\002'
            break; /* Loop/switch isn't completed */

        case 3: // '\003'
            break MISSING_BLOCK_LABEL_147;

        case 1: // '\001'
            if (jsonreader.nextInt() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            break;
        }
_L3:
        if (flag)
        {
            bitset.set(i);
        }
        i++;
        jsontoken = jsonreader.peek();
        if (true) goto _L2; else goto _L1
_L1:
        flag = jsonreader.nextBoolean();
          goto _L3
        String s = jsonreader.nextString();
        int j;
        try
        {
            j = Integer.parseInt(s);
        }
        catch (NumberFormatException numberformatexception)
        {
            throw new JsonSyntaxException((new StringBuilder("Error: Expecting: bitset number value (1, 0), Found: ")).append(s).toString());
        }
        if (j != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L3
        jsonreader.endArray();
        return bitset;
    }

    public final volatile void write(JsonWriter jsonwriter, Object obj)
    {
        write(jsonwriter, (BitSet)obj);
    }

    public final void write(JsonWriter jsonwriter, BitSet bitset)
    {
        if (bitset == null)
        {
            jsonwriter.nullValue();
            return;
        }
        jsonwriter.beginArray();
        int i = 0;
        while (i < bitset.length()) 
        {
            int j;
            if (bitset.get(i))
            {
                j = 1;
            } else
            {
                j = 0;
            }
            jsonwriter.value(j);
            i++;
        }
        jsonwriter.endArray();
    }
}
