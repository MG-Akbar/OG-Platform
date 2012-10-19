/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.security;

import java.io.Serializable;
import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.id.UniqueId;
import com.opengamma.master.AbstractDocument;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.PublicSPI;

/**
 * A document used to pass into and out of the security master.
 */
@PublicSPI
@BeanDefinition
public class SecurityDocument extends AbstractDocument<ManageableSecurity> implements Serializable {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The security unique identifier.
   * This field is managed by the master but must be set for updates.
   */
  @PropertyDefinition
  private UniqueId _uniqueId;

  /**
   * Creates an instance.
   */
  public SecurityDocument() {
  }

  /**
   * Creates an instance from a security.
   * @param security  the security, not null
   */
  public SecurityDocument(final ManageableSecurity security) {
    ArgumentChecker.notNull(security, "security");
    setUniqueId(security.getUniqueId());
    setObject(security);
  }

  //-------------------------------------------------------------------------

  /**
   * Gets the name of the security.
   * <p>
   * This is derived from the security itself.
   * @return the name, null if no name
   */
  public String getName() {
    return (getObject() != null ? getObject().getName() : null);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SecurityDocument}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static SecurityDocument.Meta meta() {
    return SecurityDocument.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(SecurityDocument.Meta.INSTANCE);
  }

  @Override
  public SecurityDocument.Meta metaBean() {
    return SecurityDocument.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -294460212:  // uniqueId
        return getUniqueId();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -294460212:  // uniqueId
        setUniqueId((UniqueId) newValue);
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
      SecurityDocument other = (SecurityDocument) obj;
      return JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getUniqueId());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the security unique identifier.
   * This field is managed by the master but must be set for updates.
   * @return the value of the property
   */
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  /**
   * Sets the security unique identifier.
   * This field is managed by the master but must be set for updates.
   * @param uniqueId  the new value of the property
   */
  public void setUniqueId(UniqueId uniqueId) {
    this._uniqueId = uniqueId;
  }

  /**
   * Gets the the {@code uniqueId} property.
   * This field is managed by the master but must be set for updates.
   * @return the property, not null
   */
  public final Property<UniqueId> uniqueId() {
    return metaBean().uniqueId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SecurityDocument}.
   */
  public static class Meta extends AbstractDocument.Meta<ManageableSecurity> {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueId> _uniqueId = DirectMetaProperty.ofReadWrite(
        this, "uniqueId", SecurityDocument.class, UniqueId.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "uniqueId");

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
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends SecurityDocument> builder() {
      return new DirectBeanBuilder<SecurityDocument>(new SecurityDocument());
    }

    @Override
    public Class<? extends SecurityDocument> beanType() {
      return SecurityDocument.class;
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

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
