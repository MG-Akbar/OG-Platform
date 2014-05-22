/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.util.fudgemsg.multimap;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.SortedSetMultimap;

@BeanDefinition
public class SortedMultimapMockBean implements ImmutableBean {

  @PropertyDefinition(validate = "notNull")
  private final SortedSetMultimap<String, String> _sortedMultimap;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SortedMultimapMockBean}.
   * @return the meta-bean, not null
   */
  public static SortedMultimapMockBean.Meta meta() {
    return SortedMultimapMockBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(SortedMultimapMockBean.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static SortedMultimapMockBean.Builder builder() {
    return new SortedMultimapMockBean.Builder();
  }

  /**
   * Restricted constructor.
   * @param builder  the builder to copy from, not null
   */
  protected SortedMultimapMockBean(SortedMultimapMockBean.Builder builder) {
    JodaBeanUtils.notNull(builder._sortedMultimap, "sortedMultimap");
    this._sortedMultimap = builder._sortedMultimap;
  }

  @Override
  public SortedMultimapMockBean.Meta metaBean() {
    return SortedMultimapMockBean.Meta.INSTANCE;
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
  /**
   * Gets the sortedMultimap.
   * @return the value of the property, not null
   */
  public SortedSetMultimap<String, String> getSortedMultimap() {
    return _sortedMultimap;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public SortedMultimapMockBean clone() {
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      SortedMultimapMockBean other = (SortedMultimapMockBean) obj;
      return JodaBeanUtils.equal(getSortedMultimap(), other.getSortedMultimap());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getSortedMultimap());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("SortedMultimapMockBean{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("sortedMultimap").append('=').append(JodaBeanUtils.toString(getSortedMultimap())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SortedMultimapMockBean}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code sortedMultimap} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<SortedSetMultimap<String, String>> _sortedMultimap = DirectMetaProperty.ofImmutable(
        this, "sortedMultimap", SortedMultimapMockBean.class, (Class) SortedSetMultimap.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "sortedMultimap");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1018561376:  // sortedMultimap
          return _sortedMultimap;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public SortedMultimapMockBean.Builder builder() {
      return new SortedMultimapMockBean.Builder();
    }

    @Override
    public Class<? extends SortedMultimapMockBean> beanType() {
      return SortedMultimapMockBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code sortedMultimap} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<SortedSetMultimap<String, String>> sortedMultimap() {
      return _sortedMultimap;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1018561376:  // sortedMultimap
          return ((SortedMultimapMockBean) bean).getSortedMultimap();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code SortedMultimapMockBean}.
   */
  public static class Builder extends DirectFieldsBeanBuilder<SortedMultimapMockBean> {

    private SortedSetMultimap<String, String> _sortedMultimap;

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    protected Builder(SortedMultimapMockBean beanToCopy) {
      this._sortedMultimap = beanToCopy.getSortedMultimap();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1018561376:  // sortedMultimap
          return _sortedMultimap;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 1018561376:  // sortedMultimap
          this._sortedMultimap = (SortedSetMultimap<String, String>) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public SortedMultimapMockBean build() {
      return new SortedMultimapMockBean(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code sortedMultimap} property in the builder.
     * @param sortedMultimap  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder sortedMultimap(SortedSetMultimap<String, String> sortedMultimap) {
      JodaBeanUtils.notNull(sortedMultimap, "sortedMultimap");
      this._sortedMultimap = sortedMultimap;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("SortedMultimapMockBean.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    protected void toString(StringBuilder buf) {
      buf.append("sortedMultimap").append('=').append(JodaBeanUtils.toString(_sortedMultimap)).append(',').append(' ');
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
