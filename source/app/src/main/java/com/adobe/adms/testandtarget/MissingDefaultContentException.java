// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.testandtarget;


public class MissingDefaultContentException extends Exception
{

    public MissingDefaultContentException()
    {
        super("Mbox.setDefaultContent() must be called before loading Mbox.");
    }
}
