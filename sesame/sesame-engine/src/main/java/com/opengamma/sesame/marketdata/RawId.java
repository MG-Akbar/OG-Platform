/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.marketdata;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
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

import com.opengamma.core.value.MarketDataRequirementNames;
import com.opengamma.id.ExternalIdBundle;

/**
 * Key for requesting market data that has no associated metadata, only an ID.
 * <p>
 * This shouldn't be used unless absolutely necessary. Market data requested in this way can't be filtered
 * when running scenarios except by ID.
 *
 * @param <T> the type of the market data
 */
@BeanDefinition(builderScope = "private")
public final class RawId<T> implements MarketDataId, ImmutableBean {

  /** The ID of the market data. */
  @PropertyDefinition(validate = "notNull")
  private final ExternalIdBundle _id;

  /** The expected type of the market data. */
  @PropertyDefinition(validate = "notNull")
  private final Class<T> _marketDataType;

  // TODO this isn't right - there can be IDs for multiple market data providers in the bundle, need multiple field names
  /** The field name of the market data in the market data record. */
  @PropertyDefinition(validate = "notNull")
  private final FieldName _fieldName;

  /**
   * Creates a key for requesting the market value of an ID.
   * <p>
   * The field name {@link MarketDataRequirementNames#MARKET_VALUE} is used to look up the data in the record.
   *
   * @param id ID of the market data
   * @return a key for looking up the data
   */
  public static RawId<Double> of(ExternalIdBundle id) {
    return new RawId<>(id, Double.class, MarketDataUtils.MARKET_VALUE);
  }

  /**
   * Creates a key for requesting a numerical market data value for an ID.
   *
   * @param id ID of the market data
   * @param fieldName the field name of the required market data in the record
   * @return a key for looking up the data
   */
  public static RawId<Double> of(ExternalIdBundle id, FieldName fieldName) {
    return new RawId<>(id, Double.class, fieldName);
  }

  /**
   * Creates a key for requesting the market value of an ID.
   * <p>
   * The field name {@link MarketDataRequirementNames#MARKET_VALUE} is used to look up the data in the record.
   *
   * @param id ID of the market data
   * @param marketDataType the type of the market data
   * @return a key for looking up the data
   * @param <U> the type of the market data
   */
  public static <U> RawId<U> of(ExternalIdBundle id, Class<U> marketDataType) {
    return new RawId<>(id, marketDataType, MarketDataUtils.MARKET_VALUE);
  }

  /**
   * Creates a key for requesting a numerical market data value for an ID.
   *
   * @param id ID of the market data
   * @param fieldName the field name of the required market data in the record
   * @param marketDataType the type of the market data
   * @return a key for looking up the data
   * @param <U> the type of the market data
   */
  public static <U> RawId<U> of(ExternalIdBundle id, Class<U> marketDataType, FieldName fieldName) {
    return new RawId<>(id, marketDataType, fieldName);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code RawId}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("rawtypes")
  public static RawId.Meta meta() {
    return RawId.Meta.INSTANCE;
  }

  /**
   * The meta-bean for {@code RawId}.
   * @param <R>  the bean's generic type
   * @param cls  the bean's generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R> RawId.Meta<R> metaRawId(Class<R> cls) {
    return RawId.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(RawId.Meta.INSTANCE);
  }

  private RawId(
      ExternalIdBundle id,
      Class<T> marketDataType,
      FieldName fieldName) {
    JodaBeanUtils.notNull(id, "id");
    JodaBeanUtils.notNull(marketDataType, "marketDataType");
    JodaBeanUtils.notNull(fieldName, "fieldName");
    this._id = id;
    this._marketDataType = marketDataType;
    this._fieldName = fieldName;
  }

  @SuppressWarnings("unchecked")
  @Override
  public RawId.Meta<T> metaBean() {
    return RawId.Meta.INSTANCE;
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
   * Gets the ID of the market data.
   * @return the value of the property, not null
   */
  public ExternalIdBundle getId() {
    return _id;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the expected type of the market data.
   * @return the value of the property, not null
   */
  public Class<T> getMarketDataType() {
    return _marketDataType;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the field name of the market data in the market data record.
   * @return the value of the property, not null
   */
  public FieldName getFieldName() {
    return _fieldName;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      RawId<?> other = (RawId<?>) obj;
      return JodaBeanUtils.equal(getId(), other.getId()) &&
          JodaBeanUtils.equal(getMarketDataType(), other.getMarketDataType()) &&
          JodaBeanUtils.equal(getFieldName(), other.getFieldName());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getMarketDataType());
    hash = hash * 31 + JodaBeanUtils.hashCode(getFieldName());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("RawId{");
    buf.append("id").append('=').append(getId()).append(',').append(' ');
    buf.append("marketDataType").append('=').append(getMarketDataType()).append(',').append(' ');
    buf.append("fieldName").append('=').append(JodaBeanUtils.toString(getFieldName()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code RawId}.
   * @param <T>  the type
   */
  public static final class Meta<T> extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code id} property.
     */
    private final MetaProperty<ExternalIdBundle> _id = DirectMetaProperty.ofImmutable(
        this, "id", RawId.class, ExternalIdBundle.class);
    /**
     * The meta-property for the {@code marketDataType} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Class<T>> _marketDataType = DirectMetaProperty.ofImmutable(
        this, "marketDataType", RawId.class, (Class) Class.class);
    /**
     * The meta-property for the {@code fieldName} property.
     */
    private final MetaProperty<FieldName> _fieldName = DirectMetaProperty.ofImmutable(
        this, "fieldName", RawId.class, FieldName.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "id",
        "marketDataType",
        "fieldName");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return _id;
        case 843057760:  // marketDataType
          return _marketDataType;
        case 1265009317:  // fieldName
          return _fieldName;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends RawId<T>> builder() {
      return new RawId.Builder<T>();
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends RawId<T>> beanType() {
      return (Class) RawId.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code id} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ExternalIdBundle> id() {
      return _id;
    }

    /**
     * The meta-property for the {@code marketDataType} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Class<T>> marketDataType() {
      return _marketDataType;
    }

    /**
     * The meta-property for the {@code fieldName} property.
     * @return the meta-property, not null
     */
    public MetaProperty<FieldName> fieldName() {
      return _fieldName;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return ((RawId<?>) bean).getId();
        case 843057760:  // marketDataType
          return ((RawId<?>) bean).getMarketDataType();
        case 1265009317:  // fieldName
          return ((RawId<?>) bean).getFieldName();
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
   * The bean-builder for {@code RawId}.
   * @param <T>  the type
   */
  private static final class Builder<T> extends DirectFieldsBeanBuilder<RawId<T>> {

    private ExternalIdBundle _id;
    private Class<T> _marketDataType;
    private FieldName _fieldName;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return _id;
        case 843057760:  // marketDataType
          return _marketDataType;
        case 1265009317:  // fieldName
          return _fieldName;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder<T> set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          this._id = (ExternalIdBundle) newValue;
          break;
        case 843057760:  // marketDataType
          this._marketDataType = (Class<T>) newValue;
          break;
        case 1265009317:  // fieldName
          this._fieldName = (FieldName) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder<T> set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder<T> setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder<T> setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder<T> setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public RawId<T> build() {
      return new RawId<T>(
          _id,
          _marketDataType,
          _fieldName);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("RawId.Builder{");
      buf.append("id").append('=').append(JodaBeanUtils.toString(_id)).append(',').append(' ');
      buf.append("marketDataType").append('=').append(JodaBeanUtils.toString(_marketDataType)).append(',').append(' ');
      buf.append("fieldName").append('=').append(JodaBeanUtils.toString(_fieldName));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
