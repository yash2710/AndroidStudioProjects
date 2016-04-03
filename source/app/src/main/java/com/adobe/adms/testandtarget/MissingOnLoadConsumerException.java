// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.testandtarget;


public class MissingOnLoadConsumerException extends Exception
{

    public MissingOnLoadConsumerException()
    {
        super("Mbox.addOnloadConsumer() must be called before loading Mbox.");
    }
}
