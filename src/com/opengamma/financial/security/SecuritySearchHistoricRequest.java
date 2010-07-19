/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.time.Instant;
import javax.time.InstantProvider;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.BasicMetaBean;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaProperty;

import com.opengamma.id.UniqueIdentifier;
import com.opengamma.util.db.PagingRequest;

/**
 * Request for searching for historic securities.
 */
@BeanDefinition
public class SecuritySearchHistoricRequest extends DirectBean {

  /**
   * The request for paging.
   */
  @PropertyDefinition
  private PagingRequest _pagingRequest = new PagingRequest();
  /**
   * The object identifier to match.
   * The unique identifier must not contain a version.
   */
  @PropertyDefinition
  private UniqueIdentifier _objectIdentifier;
  /**
   * The earliest instant to return, inclusive.
   * A null value will retrieve values starting from the earilest version.
   */
  @PropertyDefinition
  private Instant _versionFromInstant;
  /**
   * The latest instant to return, exclusive.
   * A null value will retrieve values up to the latest version.
   */
  @PropertyDefinition
  private Instant _versionToInstant;
  /**
   * Whether to return all the corrections of each version.
   * True returns all data corrections, false returns the data as of the correction view instant.
   */
  @PropertyDefinition
  private boolean _allCorrections;
  /**
   * The instant that the data has been corrected to.
   * This allows the data to be accessed before or after a specific correction.
   * A null value will retrieve the latest correction.
   */
  @PropertyDefinition
  private Instant _correctedToInstant;

  /**
   * Creates an instance.
   */
  public SecuritySearchHistoricRequest() {
  }

  /**
   * Creates an instance.
   * @param uid  the object identifier
   */
  public SecuritySearchHistoricRequest(final UniqueIdentifier uid) {
    setObjectIdentifier(uid);
  }

  /**
   * Creates an instance.
   * @param uid  the object identifier
   * @param versionInstantProvider  the version instant to retrieve
   */
  public SecuritySearchHistoricRequest(final UniqueIdentifier uid, InstantProvider versionInstantProvider) {
    setObjectIdentifier(uid);
    if (versionInstantProvider != null) {
      final Instant versionInstant = Instant.of(versionInstantProvider);
      setVersionFromInstant(versionInstant);
      setVersionToInstant(versionInstant);
    }
  }

  //------------------------- AUTOGENERATED START -------------------------
  /**
   * The meta-bean for {@code SecuritySearchHistoricRequest}.
   * @return the meta-bean, not null
   */
  public static SecuritySearchHistoricRequest.Meta meta() {
    return SecuritySearchHistoricRequest.Meta.INSTANCE;
  }

  @Override
  public SecuritySearchHistoricRequest.Meta metaBean() {
    return SecuritySearchHistoricRequest.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
      case -2092032669:  // pagingRequest
        return getPagingRequest();
      case 1534290248:  // objectIdentifier
        return getObjectIdentifier();
      case 2006263519:  // versionFromInstant
        return getVersionFromInstant();
      case 1577022702:  // versionToInstant
        return getVersionToInstant();
      case 772627572:  // allCorrections
        return isAllCorrections();
      case -28367267:  // correctedToInstant
        return getCorrectedToInstant();
    }
    return super.propertyGet(propertyName);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
      case -2092032669:  // pagingRequest
        setPagingRequest((PagingRequest) newValue);
        return;
      case 1534290248:  // objectIdentifier
        setObjectIdentifier((UniqueIdentifier) newValue);
        return;
      case 2006263519:  // versionFromInstant
        setVersionFromInstant((Instant) newValue);
        return;
      case 1577022702:  // versionToInstant
        setVersionToInstant((Instant) newValue);
        return;
      case 772627572:  // allCorrections
        setAllCorrections((boolean) (Boolean) newValue);
        return;
      case -28367267:  // correctedToInstant
        setCorrectedToInstant((Instant) newValue);
        return;
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the request for paging.
   * @return the value of the property
   */
  public PagingRequest getPagingRequest() {
    return _pagingRequest;
  }

  /**
   * Sets the request for paging.
   * @param pagingRequest  the new value of the property
   */
  public void setPagingRequest(PagingRequest pagingRequest) {
    this._pagingRequest = pagingRequest;
  }

  /**
   * Gets the the {@code pagingRequest} property.
   * @return the property, not null
   */
  public final Property<PagingRequest> pagingRequest() {
    return metaBean().pagingRequest().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the object identifier to match.
   * The unique identifier must not contain a version.
   * @return the value of the property
   */
  public UniqueIdentifier getObjectIdentifier() {
    return _objectIdentifier;
  }

  /**
   * Sets the object identifier to match.
   * The unique identifier must not contain a version.
   * @param objectIdentifier  the new value of the property
   */
  public void setObjectIdentifier(UniqueIdentifier objectIdentifier) {
    this._objectIdentifier = objectIdentifier;
  }

  /**
   * Gets the the {@code objectIdentifier} property.
   * The unique identifier must not contain a version.
   * @return the property, not null
   */
  public final Property<UniqueIdentifier> objectIdentifier() {
    return metaBean().objectIdentifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the earliest instant to return, inclusive.
   * A null value will retrieve values starting from the earilest version.
   * @return the value of the property
   */
  public Instant getVersionFromInstant() {
    return _versionFromInstant;
  }

  /**
   * Sets the earliest instant to return, inclusive.
   * A null value will retrieve values starting from the earilest version.
   * @param versionFromInstant  the new value of the property
   */
  public void setVersionFromInstant(Instant versionFromInstant) {
    this._versionFromInstant = versionFromInstant;
  }

  /**
   * Gets the the {@code versionFromInstant} property.
   * A null value will retrieve values starting from the earilest version.
   * @return the property, not null
   */
  public final Property<Instant> versionFromInstant() {
    return metaBean().versionFromInstant().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the latest instant to return, exclusive.
   * A null value will retrieve values up to the latest version.
   * @return the value of the property
   */
  public Instant getVersionToInstant() {
    return _versionToInstant;
  }

  /**
   * Sets the latest instant to return, exclusive.
   * A null value will retrieve values up to the latest version.
   * @param versionToInstant  the new value of the property
   */
  public void setVersionToInstant(Instant versionToInstant) {
    this._versionToInstant = versionToInstant;
  }

  /**
   * Gets the the {@code versionToInstant} property.
   * A null value will retrieve values up to the latest version.
   * @return the property, not null
   */
  public final Property<Instant> versionToInstant() {
    return metaBean().versionToInstant().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets whether to return all the corrections of each version.
   * True returns all data corrections, false returns the data as of the correction view instant.
   * @return the value of the property
   */
  public boolean isAllCorrections() {
    return _allCorrections;
  }

  /**
   * Sets whether to return all the corrections of each version.
   * True returns all data corrections, false returns the data as of the correction view instant.
   * @param allCorrections  the new value of the property
   */
  public void setAllCorrections(boolean allCorrections) {
    this._allCorrections = allCorrections;
  }

  /**
   * Gets the the {@code allCorrections} property.
   * True returns all data corrections, false returns the data as of the correction view instant.
   * @return the property, not null
   */
  public final Property<Boolean> allCorrections() {
    return metaBean().allCorrections().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the instant that the data has been corrected to.
   * This allows the data to be accessed before or after a specific correction.
   * A null value will retrieve the latest correction.
   * @return the value of the property
   */
  public Instant getCorrectedToInstant() {
    return _correctedToInstant;
  }

  /**
   * Sets the instant that the data has been corrected to.
   * This allows the data to be accessed before or after a specific correction.
   * A null value will retrieve the latest correction.
   * @param correctedToInstant  the new value of the property
   */
  public void setCorrectedToInstant(Instant correctedToInstant) {
    this._correctedToInstant = correctedToInstant;
  }

  /**
   * Gets the the {@code correctedToInstant} property.
   * This allows the data to be accessed before or after a specific correction.
   * A null value will retrieve the latest correction.
   * @return the property, not null
   */
  public final Property<Instant> correctedToInstant() {
    return metaBean().correctedToInstant().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SecuritySearchHistoricRequest}.
   */
  public static class Meta extends BasicMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code pagingRequest} property.
     */
    private final MetaProperty<PagingRequest> _pagingRequest = DirectMetaProperty.ofReadWrite(this, "pagingRequest", PagingRequest.class);
    /**
     * The meta-property for the {@code objectIdentifier} property.
     */
    private final MetaProperty<UniqueIdentifier> _objectIdentifier = DirectMetaProperty.ofReadWrite(this, "objectIdentifier", UniqueIdentifier.class);
    /**
     * The meta-property for the {@code versionFromInstant} property.
     */
    private final MetaProperty<Instant> _versionFromInstant = DirectMetaProperty.ofReadWrite(this, "versionFromInstant", Instant.class);
    /**
     * The meta-property for the {@code versionToInstant} property.
     */
    private final MetaProperty<Instant> _versionToInstant = DirectMetaProperty.ofReadWrite(this, "versionToInstant", Instant.class);
    /**
     * The meta-property for the {@code allCorrections} property.
     */
    private final MetaProperty<Boolean> _allCorrections = DirectMetaProperty.ofReadWrite(this, "allCorrections", Boolean.TYPE);
    /**
     * The meta-property for the {@code correctedToInstant} property.
     */
    private final MetaProperty<Instant> _correctedToInstant = DirectMetaProperty.ofReadWrite(this, "correctedToInstant", Instant.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings("unchecked")
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap();
      temp.put("pagingRequest", _pagingRequest);
      temp.put("objectIdentifier", _objectIdentifier);
      temp.put("versionFromInstant", _versionFromInstant);
      temp.put("versionToInstant", _versionToInstant);
      temp.put("allCorrections", _allCorrections);
      temp.put("correctedToInstant", _correctedToInstant);
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public SecuritySearchHistoricRequest createBean() {
      return new SecuritySearchHistoricRequest();
    }

    @Override
    public Class<? extends SecuritySearchHistoricRequest> beanType() {
      return SecuritySearchHistoricRequest.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code pagingRequest} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<PagingRequest> pagingRequest() {
      return _pagingRequest;
    }

    /**
     * The meta-property for the {@code objectIdentifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueIdentifier> objectIdentifier() {
      return _objectIdentifier;
    }

    /**
     * The meta-property for the {@code versionFromInstant} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> versionFromInstant() {
      return _versionFromInstant;
    }

    /**
     * The meta-property for the {@code versionToInstant} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> versionToInstant() {
      return _versionToInstant;
    }

    /**
     * The meta-property for the {@code allCorrections} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> allCorrections() {
      return _allCorrections;
    }

    /**
     * The meta-property for the {@code correctedToInstant} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> correctedToInstant() {
      return _correctedToInstant;
    }

  }

  //-------------------------- AUTOGENERATED END --------------------------
}
