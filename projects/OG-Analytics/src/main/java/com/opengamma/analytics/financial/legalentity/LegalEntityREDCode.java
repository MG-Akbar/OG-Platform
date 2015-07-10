/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.legalentity;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.util.ArgumentChecker;

/**
 * Gets the region of an {@link LegalEntity}.
 */
@BeanDefinition
public class LegalEntityREDCode implements LegalEntityFilter<LegalEntityWithREDCode>, Bean {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * For the builder.
   */
  public LegalEntityREDCode() {
  }

  @Override
  public Object getFilteredData(final LegalEntityWithREDCode legalEntity) {
    ArgumentChecker.notNull(legalEntity, "legal entity");
    return legalEntity.getRedCode();
  }

  @Override
  public Type getFilteredDataType() {
    return LegalEntityWithREDCode.meta().redCode().propertyGenericType();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code LegalEntityREDCode}.
   * @return the meta-bean, not null
   */
  public static LegalEntityREDCode.Meta meta() {
    return LegalEntityREDCode.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(LegalEntityREDCode.Meta.INSTANCE);
  }

  @Override
  public LegalEntityREDCode.Meta metaBean() {
    return LegalEntityREDCode.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  @Override
  public LegalEntityREDCode clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(32);
    buf.append("LegalEntityREDCode{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code LegalEntityREDCode}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null);

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends LegalEntityREDCode> builder() {
      return new DirectBeanBuilder<LegalEntityREDCode>(new LegalEntityREDCode());
    }

    @Override
    public Class<? extends LegalEntityREDCode> beanType() {
      return LegalEntityREDCode.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
