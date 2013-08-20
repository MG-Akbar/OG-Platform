/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.marketdatasnapshot.impl;

import java.util.Map;
import java.util.Set;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.Maps;
import com.opengamma.core.marketdatasnapshot.CurveKey;
import com.opengamma.core.marketdatasnapshot.CurveSnapshot;
import com.opengamma.core.marketdatasnapshot.StructuredMarketDataSnapshot;
import com.opengamma.core.marketdatasnapshot.UnstructuredMarketDataSnapshot;
import com.opengamma.core.marketdatasnapshot.VolatilityCubeKey;
import com.opengamma.core.marketdatasnapshot.VolatilityCubeSnapshot;
import com.opengamma.core.marketdatasnapshot.VolatilitySurfaceKey;
import com.opengamma.core.marketdatasnapshot.VolatilitySurfaceSnapshot;
import com.opengamma.core.marketdatasnapshot.YieldCurveKey;
import com.opengamma.core.marketdatasnapshot.YieldCurveSnapshot;
import com.opengamma.id.UniqueId;
import com.opengamma.util.PublicSPI;

/**
 * A snapshot of market data potentially altered by hand.
 */
@BeanDefinition
@PublicSPI
public class ManageableMarketDataSnapshot extends DirectBean implements StructuredMarketDataSnapshot {

  /**
   * The unique identifier of the snapshot. This must be null when adding to a master and not null when retrieved from a master.
   */
  @PropertyDefinition
  private UniqueId _uniqueId;

  /**
   * The name of the snapshot intended for display purposes. This field must not be null for the object to be valid.
   */
  @PropertyDefinition
  private String _name;

  /**
   * The name of the view on which this snapshot was based
   */
  @PropertyDefinition
  private String _basisViewName;

  @PropertyDefinition
  private ManageableUnstructuredMarketDataSnapshot _globalValues;

  /**
   * The yield curves in this snapshot
   */
  @PropertyDefinition
  private Map<YieldCurveKey, YieldCurveSnapshot> _yieldCurves;

  /**
   * The yield curves in this snapshot
   */
  @PropertyDefinition
  private Map<CurveKey, CurveSnapshot> _curves;

  /**
   * The vol cubes in this snapshot
   */
  @PropertyDefinition
  private Map<VolatilityCubeKey, VolatilityCubeSnapshot> _volatilityCubes;

  /**
   * The vol surfaces in this snapshot
   */
  @PropertyDefinition
  private Map<VolatilitySurfaceKey, VolatilitySurfaceSnapshot> _volatilitySurfaces = Maps.newHashMap(); //Initialize in order to handle old format

  /**
   * Creates a snapshot
   */
  public ManageableMarketDataSnapshot() {
    super();
  }

  /**
   * Creates a snapshot
   * 
   * @param name the name of the snapshot
   * @param globalValues the snapshot for the global scope
   * @param yieldCurves the yield curves
   */
  public ManageableMarketDataSnapshot(final String name, final UnstructuredMarketDataSnapshot globalValues,
      final Map<YieldCurveKey, YieldCurveSnapshot> yieldCurves) {
    super();
    _name = name;
    _globalValues = (globalValues != null) ? new ManageableUnstructuredMarketDataSnapshot(globalValues) : null;
    _yieldCurves = yieldCurves;
  }

  /**
   * Creates a snapshot.
   * 
   * @param name the name of the snapshot
   * @param globalValues the snapshot for the global scope
   * @param yieldCurves the yield curves
   * @param volatilitySurfaces the volatility surfaces
   * @param volatilityCubes the volatility cubes
   */
  public ManageableMarketDataSnapshot(final String name, final UnstructuredMarketDataSnapshot globalValues,
      final Map<YieldCurveKey, YieldCurveSnapshot> yieldCurves, final Map<VolatilitySurfaceKey,
      VolatilitySurfaceSnapshot> volatilitySurfaces, final Map<VolatilityCubeKey, VolatilityCubeSnapshot> volatilityCubes) {
    super();
    _name = name;
    _globalValues = (globalValues != null) ? new ManageableUnstructuredMarketDataSnapshot(globalValues) : null;
    _yieldCurves = yieldCurves;
    _curves = null;
    _volatilitySurfaces = volatilitySurfaces;
    _volatilityCubes = volatilityCubes;
  }

    /**
     * Creates a snapshot.
     *
     * @param name the name of the snapshot
     * @param globalValues the snapshot for the global scope
     * @param yieldCurves the yield curves
     * @param curves the curves
     * @param volatilitySurfaces the volatility surfaces
     * @param volatilityCubes the volatility cubes
     */
  public ManageableMarketDataSnapshot(final String name, final UnstructuredMarketDataSnapshot globalValues,
      final Map<YieldCurveKey, YieldCurveSnapshot> yieldCurves, final Map<CurveKey, CurveSnapshot> curves, final Map<VolatilitySurfaceKey,
      VolatilitySurfaceSnapshot> volatilitySurfaces, final Map<VolatilityCubeKey, VolatilityCubeSnapshot> volatilityCubes) {
    super();
    _name = name;
    _globalValues = (globalValues != null) ? new ManageableUnstructuredMarketDataSnapshot(globalValues) : null;
    _yieldCurves = yieldCurves;
    _curves = curves;
    _volatilitySurfaces = volatilitySurfaces;
    _volatilityCubes = volatilityCubes;
  }

  public ManageableMarketDataSnapshot(final StructuredMarketDataSnapshot copyFrom) {
    this(copyFrom.getName(), copyFrom.getGlobalValues(), copyFrom.getYieldCurves(), copyFrom.getCurves(),  copyFrom.getVolatilitySurfaces(), copyFrom.getVolatilityCubes());
    _basisViewName = copyFrom.getBasisViewName();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ManageableMarketDataSnapshot}.
   * @return the meta-bean, not null
   */
  public static ManageableMarketDataSnapshot.Meta meta() {
    return ManageableMarketDataSnapshot.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ManageableMarketDataSnapshot.Meta.INSTANCE);
  }

  @Override
  public ManageableMarketDataSnapshot.Meta metaBean() {
    return ManageableMarketDataSnapshot.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -294460212:  // uniqueId
        return getUniqueId();
      case 3373707:  // name
        return getName();
      case 858810670:  // basisViewName
        return getBasisViewName();
      case -591591771:  // globalValues
        return getGlobalValues();
      case 119589713:  // yieldCurves
        return getYieldCurves();
      case -1349116572:  // curves
        return getCurves();
      case -2137883207:  // volatilityCubes
        return getVolatilityCubes();
      case -1329840981:  // volatilitySurfaces
        return getVolatilitySurfaces();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -294460212:  // uniqueId
        setUniqueId((UniqueId) newValue);
        return;
      case 3373707:  // name
        setName((String) newValue);
        return;
      case 858810670:  // basisViewName
        setBasisViewName((String) newValue);
        return;
      case -591591771:  // globalValues
        setGlobalValues((ManageableUnstructuredMarketDataSnapshot) newValue);
        return;
      case 119589713:  // yieldCurves
        setYieldCurves((Map<YieldCurveKey, YieldCurveSnapshot>) newValue);
        return;
      case -1349116572:  // curves
        setCurves((Map<CurveKey, CurveSnapshot>) newValue);
        return;
      case -2137883207:  // volatilityCubes
        setVolatilityCubes((Map<VolatilityCubeKey, VolatilityCubeSnapshot>) newValue);
        return;
      case -1329840981:  // volatilitySurfaces
        setVolatilitySurfaces((Map<VolatilitySurfaceKey, VolatilitySurfaceSnapshot>) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ManageableMarketDataSnapshot other = (ManageableMarketDataSnapshot) obj;
      return JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getBasisViewName(), other.getBasisViewName()) &&
          JodaBeanUtils.equal(getGlobalValues(), other.getGlobalValues()) &&
          JodaBeanUtils.equal(getYieldCurves(), other.getYieldCurves()) &&
          JodaBeanUtils.equal(getCurves(), other.getCurves()) &&
          JodaBeanUtils.equal(getVolatilityCubes(), other.getVolatilityCubes()) &&
          JodaBeanUtils.equal(getVolatilitySurfaces(), other.getVolatilitySurfaces());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getUniqueId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getName());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBasisViewName());
    hash += hash * 31 + JodaBeanUtils.hashCode(getGlobalValues());
    hash += hash * 31 + JodaBeanUtils.hashCode(getYieldCurves());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCurves());
    hash += hash * 31 + JodaBeanUtils.hashCode(getVolatilityCubes());
    hash += hash * 31 + JodaBeanUtils.hashCode(getVolatilitySurfaces());
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unique identifier of the snapshot. This must be null when adding to a master and not null when retrieved from a master.
   * @return the value of the property
   */
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  /**
   * Sets the unique identifier of the snapshot. This must be null when adding to a master and not null when retrieved from a master.
   * @param uniqueId  the new value of the property
   */
  public void setUniqueId(UniqueId uniqueId) {
    this._uniqueId = uniqueId;
  }

  /**
   * Gets the the {@code uniqueId} property.
   * @return the property, not null
   */
  public final Property<UniqueId> uniqueId() {
    return metaBean().uniqueId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the name of the snapshot intended for display purposes. This field must not be null for the object to be valid.
   * @return the value of the property
   */
  public String getName() {
    return _name;
  }

  /**
   * Sets the name of the snapshot intended for display purposes. This field must not be null for the object to be valid.
   * @param name  the new value of the property
   */
  public void setName(String name) {
    this._name = name;
  }

  /**
   * Gets the the {@code name} property.
   * @return the property, not null
   */
  public final Property<String> name() {
    return metaBean().name().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the name of the view on which this snapshot was based
   * @return the value of the property
   */
  public String getBasisViewName() {
    return _basisViewName;
  }

  /**
   * Sets the name of the view on which this snapshot was based
   * @param basisViewName  the new value of the property
   */
  public void setBasisViewName(String basisViewName) {
    this._basisViewName = basisViewName;
  }

  /**
   * Gets the the {@code basisViewName} property.
   * @return the property, not null
   */
  public final Property<String> basisViewName() {
    return metaBean().basisViewName().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the globalValues.
   * @return the value of the property
   */
  public ManageableUnstructuredMarketDataSnapshot getGlobalValues() {
    return _globalValues;
  }

  /**
   * Sets the globalValues.
   * @param globalValues  the new value of the property
   */
  public void setGlobalValues(ManageableUnstructuredMarketDataSnapshot globalValues) {
    this._globalValues = globalValues;
  }

  /**
   * Gets the the {@code globalValues} property.
   * @return the property, not null
   */
  public final Property<ManageableUnstructuredMarketDataSnapshot> globalValues() {
    return metaBean().globalValues().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the yield curves in this snapshot
   * @return the value of the property
   */
  public Map<YieldCurveKey, YieldCurveSnapshot> getYieldCurves() {
    return _yieldCurves;
  }

  /**
   * Sets the yield curves in this snapshot
   * @param yieldCurves  the new value of the property
   */
  public void setYieldCurves(Map<YieldCurveKey, YieldCurveSnapshot> yieldCurves) {
    this._yieldCurves = yieldCurves;
  }

  /**
   * Gets the the {@code yieldCurves} property.
   * @return the property, not null
   */
  public final Property<Map<YieldCurveKey, YieldCurveSnapshot>> yieldCurves() {
    return metaBean().yieldCurves().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the yield curves in this snapshot
   * @return the value of the property
   */
  public Map<CurveKey, CurveSnapshot> getCurves() {
    return _curves;
  }

  /**
   * Sets the yield curves in this snapshot
   * @param curves  the new value of the property
   */
  public void setCurves(Map<CurveKey, CurveSnapshot> curves) {
    this._curves = curves;
  }

  /**
   * Gets the the {@code curves} property.
   * @return the property, not null
   */
  public final Property<Map<CurveKey, CurveSnapshot>> curves() {
    return metaBean().curves().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the vol cubes in this snapshot
   * @return the value of the property
   */
  public Map<VolatilityCubeKey, VolatilityCubeSnapshot> getVolatilityCubes() {
    return _volatilityCubes;
  }

  /**
   * Sets the vol cubes in this snapshot
   * @param volatilityCubes  the new value of the property
   */
  public void setVolatilityCubes(Map<VolatilityCubeKey, VolatilityCubeSnapshot> volatilityCubes) {
    this._volatilityCubes = volatilityCubes;
  }

  /**
   * Gets the the {@code volatilityCubes} property.
   * @return the property, not null
   */
  public final Property<Map<VolatilityCubeKey, VolatilityCubeSnapshot>> volatilityCubes() {
    return metaBean().volatilityCubes().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the vol surfaces in this snapshot
   * @return the value of the property
   */
  public Map<VolatilitySurfaceKey, VolatilitySurfaceSnapshot> getVolatilitySurfaces() {
    return _volatilitySurfaces;
  }

  /**
   * Sets the vol surfaces in this snapshot
   * @param volatilitySurfaces  the new value of the property
   */
  public void setVolatilitySurfaces(Map<VolatilitySurfaceKey, VolatilitySurfaceSnapshot> volatilitySurfaces) {
    this._volatilitySurfaces = volatilitySurfaces;
  }

  /**
   * Gets the the {@code volatilitySurfaces} property.
   * @return the property, not null
   */
  public final Property<Map<VolatilitySurfaceKey, VolatilitySurfaceSnapshot>> volatilitySurfaces() {
    return metaBean().volatilitySurfaces().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ManageableMarketDataSnapshot}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueId> _uniqueId = DirectMetaProperty.ofReadWrite(
        this, "uniqueId", ManageableMarketDataSnapshot.class, UniqueId.class);
    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofReadWrite(
        this, "name", ManageableMarketDataSnapshot.class, String.class);
    /**
     * The meta-property for the {@code basisViewName} property.
     */
    private final MetaProperty<String> _basisViewName = DirectMetaProperty.ofReadWrite(
        this, "basisViewName", ManageableMarketDataSnapshot.class, String.class);
    /**
     * The meta-property for the {@code globalValues} property.
     */
    private final MetaProperty<ManageableUnstructuredMarketDataSnapshot> _globalValues = DirectMetaProperty.ofReadWrite(
        this, "globalValues", ManageableMarketDataSnapshot.class, ManageableUnstructuredMarketDataSnapshot.class);
    /**
     * The meta-property for the {@code yieldCurves} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Map<YieldCurveKey, YieldCurveSnapshot>> _yieldCurves = DirectMetaProperty.ofReadWrite(
        this, "yieldCurves", ManageableMarketDataSnapshot.class, (Class) Map.class);
    /**
     * The meta-property for the {@code curves} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Map<CurveKey, CurveSnapshot>> _curves = DirectMetaProperty.ofReadWrite(
        this, "curves", ManageableMarketDataSnapshot.class, (Class) Map.class);
    /**
     * The meta-property for the {@code volatilityCubes} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Map<VolatilityCubeKey, VolatilityCubeSnapshot>> _volatilityCubes = DirectMetaProperty.ofReadWrite(
        this, "volatilityCubes", ManageableMarketDataSnapshot.class, (Class) Map.class);
    /**
     * The meta-property for the {@code volatilitySurfaces} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Map<VolatilitySurfaceKey, VolatilitySurfaceSnapshot>> _volatilitySurfaces = DirectMetaProperty.ofReadWrite(
        this, "volatilitySurfaces", ManageableMarketDataSnapshot.class, (Class) Map.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "uniqueId",
        "name",
        "basisViewName",
        "globalValues",
        "yieldCurves",
        "curves",
        "volatilityCubes",
        "volatilitySurfaces");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case 3373707:  // name
          return _name;
        case 858810670:  // basisViewName
          return _basisViewName;
        case -591591771:  // globalValues
          return _globalValues;
        case 119589713:  // yieldCurves
          return _yieldCurves;
        case -1349116572:  // curves
          return _curves;
        case -2137883207:  // volatilityCubes
          return _volatilityCubes;
        case -1329840981:  // volatilitySurfaces
          return _volatilitySurfaces;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ManageableMarketDataSnapshot> builder() {
      return new DirectBeanBuilder<ManageableMarketDataSnapshot>(new ManageableMarketDataSnapshot());
    }

    @Override
    public Class<? extends ManageableMarketDataSnapshot> beanType() {
      return ManageableMarketDataSnapshot.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code uniqueId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueId> uniqueId() {
      return _uniqueId;
    }

    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code basisViewName} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> basisViewName() {
      return _basisViewName;
    }

    /**
     * The meta-property for the {@code globalValues} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ManageableUnstructuredMarketDataSnapshot> globalValues() {
      return _globalValues;
    }

    /**
     * The meta-property for the {@code yieldCurves} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Map<YieldCurveKey, YieldCurveSnapshot>> yieldCurves() {
      return _yieldCurves;
    }

    /**
     * The meta-property for the {@code curves} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Map<CurveKey, CurveSnapshot>> curves() {
      return _curves;
    }

    /**
     * The meta-property for the {@code volatilityCubes} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Map<VolatilityCubeKey, VolatilityCubeSnapshot>> volatilityCubes() {
      return _volatilityCubes;
    }

    /**
     * The meta-property for the {@code volatilitySurfaces} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Map<VolatilitySurfaceKey, VolatilitySurfaceSnapshot>> volatilitySurfaces() {
      return _volatilitySurfaces;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
