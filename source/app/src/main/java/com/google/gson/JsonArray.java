// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.gson:
//            JsonElement, JsonNull

public final class JsonArray extends JsonElement
    implements Iterable
{

    private final List a = new ArrayList();

    public JsonArray()
    {
    }

    public final void add(JsonElement jsonelement)
    {
        if (jsonelement == null)
        {
            jsonelement = JsonNull.INSTANCE;
        }
        a.add(jsonelement);
    }

    public final void addAll(JsonArray jsonarray)
    {
        a.addAll(jsonarray.a);
    }

    public final boolean contains(JsonElement jsonelement)
    {
        return a.contains(jsonelement);
    }

    public final boolean equals(Object obj)
    {
        return obj == this || (obj instanceof JsonArray) && ((JsonArray)obj).a.equals(a);
    }

    public final JsonElement get(int i)
    {
        return (JsonElement)a.get(i);
    }

    public final BigDecimal getAsBigDecimal()
    {
        if (a.size() == 1)
        {
            return ((JsonElement)a.get(0)).getAsBigDecimal();
        } else
        {
            throw new IllegalStateException();
        }
    }

    public final BigInteger getAsBigInteger()
    {
        if (a.size() == 1)
        {
            return ((JsonElement)a.get(0)).getAsBigInteger();
        } else
        {
            throw new IllegalStateException();
        }
    }

    public final boolean getAsBoolean()
    {
        if (a.size() == 1)
        {
            return ((JsonElement)a.get(0)).getAsBoolean();
        } else
        {
            throw new IllegalStateException();
        }
    }

    public final byte getAsByte()
    {
        if (a.size() == 1)
        {
            return ((JsonElement)a.get(0)).getAsByte();
        } else
        {
            throw new IllegalStateException();
        }
    }

    public final char getAsCharacter()
    {
        if (a.size() == 1)
        {
            return ((JsonElement)a.get(0)).getAsCharacter();
        } else
        {
            throw new IllegalStateException();
        }
    }

    public final double getAsDouble()
    {
        if (a.size() == 1)
        {
            return ((JsonElement)a.get(0)).getAsDouble();
        } else
        {
            throw new IllegalStateException();
        }
    }

    public final float getAsFloat()
    {
        if (a.size() == 1)
        {
            return ((JsonElement)a.get(0)).getAsFloat();
        } else
        {
            throw new IllegalStateException();
        }
    }

    public final int getAsInt()
    {
        if (a.size() == 1)
        {
            return ((JsonElement)a.get(0)).getAsInt();
        } else
        {
            throw new IllegalStateException();
        }
    }

    public final long getAsLong()
    {
        if (a.size() == 1)
        {
            return ((JsonElement)a.get(0)).getAsLong();
        } else
        {
            throw new IllegalStateException();
        }
    }

    public final Number getAsNumber()
    {
        if (a.size() == 1)
        {
            return ((JsonElement)a.get(0)).getAsNumber();
        } else
        {
            throw new IllegalStateException();
        }
    }

    public final short getAsShort()
    {
        if (a.size() == 1)
        {
            return ((JsonElement)a.get(0)).getAsShort();
        } else
        {
            throw new IllegalStateException();
        }
    }

    public final String getAsString()
    {
        if (a.size() == 1)
        {
            return ((JsonElement)a.get(0)).getAsString();
        } else
        {
            throw new IllegalStateException();
        }
    }

    public final int hashCode()
    {
        return a.hashCode();
    }

    public final Iterator iterator()
    {
        return a.iterator();
    }

    public final JsonElement remove(int i)
    {
        return (JsonElement)a.remove(i);
    }

    public final boolean remove(JsonElement jsonelement)
    {
        return a.remove(jsonelement);
    }

    public final JsonElement set(int i, JsonElement jsonelement)
    {
        return (JsonElement)a.set(i, jsonelement);
    }

    public final int size()
    {
        return a.size();
    }
}
