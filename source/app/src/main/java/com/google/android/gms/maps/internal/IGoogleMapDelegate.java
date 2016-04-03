// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.IInterface;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.internal.b;
import com.google.android.gms.maps.model.internal.c;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.model.internal.g;
import com.google.android.gms.maps.model.internal.h;

// Referenced classes of package com.google.android.gms.maps.internal:
//            b, IProjectionDelegate, IUiSettingsDelegate, d, 
//            ILocationSourceDelegate, e, f, g, 
//            i, j, k, l, 
//            m, n, o, s

public interface IGoogleMapDelegate
    extends IInterface
{

    public abstract b addCircle(CircleOptions circleoptions);

    public abstract c addGroundOverlay(GroundOverlayOptions groundoverlayoptions);

    public abstract f addMarker(MarkerOptions markeroptions);

    public abstract g addPolygon(PolygonOptions polygonoptions);

    public abstract IPolylineDelegate addPolyline(PolylineOptions polylineoptions);

    public abstract h addTileOverlay(TileOverlayOptions tileoverlayoptions);

    public abstract void animateCamera(d d);

    public abstract void animateCameraWithCallback(d d, com.google.android.gms.maps.internal.b b);

    public abstract void animateCameraWithDurationAndCallback(d d, int i, com.google.android.gms.maps.internal.b b);

    public abstract void clear();

    public abstract CameraPosition getCameraPosition();

    public abstract com.google.android.gms.maps.model.internal.d getFocusedBuilding();

    public abstract int getMapType();

    public abstract float getMaxZoomLevel();

    public abstract float getMinZoomLevel();

    public abstract Location getMyLocation();

    public abstract IProjectionDelegate getProjection();

    public abstract d getTestingHelper();

    public abstract IUiSettingsDelegate getUiSettings();

    public abstract boolean isBuildingsEnabled();

    public abstract boolean isIndoorEnabled();

    public abstract boolean isMyLocationEnabled();

    public abstract boolean isTrafficEnabled();

    public abstract void moveCamera(d d);

    public abstract void setBuildingsEnabled(boolean flag);

    public abstract boolean setIndoorEnabled(boolean flag);

    public abstract void setInfoWindowAdapter(com.google.android.gms.maps.internal.d d);

    public abstract void setLocationSource(ILocationSourceDelegate ilocationsourcedelegate);

    public abstract void setMapType(int i);

    public abstract void setMyLocationEnabled(boolean flag);

    public abstract void setOnCameraChangeListener(e e);

    public abstract void setOnIndoorStateChangeListener(com.google.android.gms.maps.internal.f f);

    public abstract void setOnInfoWindowClickListener(com.google.android.gms.maps.internal.g g);

    public abstract void setOnMapClickListener(i i);

    public abstract void setOnMapLoadedCallback(j j);

    public abstract void setOnMapLongClickListener(k k);

    public abstract void setOnMarkerClickListener(l l);

    public abstract void setOnMarkerDragListener(m m);

    public abstract void setOnMyLocationButtonClickListener(n n);

    public abstract void setOnMyLocationChangeListener(o o);

    public abstract void setPadding(int i, int j, int k, int l);

    public abstract void setTrafficEnabled(boolean flag);

    public abstract void setWatermarkEnabled(boolean flag);

    public abstract void snapshot(s s, d d);

    public abstract void stopAnimation();
}
