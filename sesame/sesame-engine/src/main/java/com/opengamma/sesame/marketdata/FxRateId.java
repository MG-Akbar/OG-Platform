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

import com.opengamma.financial.currency.CurrencyPair;
import com.opengamma.util.money.Currency;

/**
 * Identifies the market data for an FX rate.
 */
@BeanDefinition(builderScope = "private")
public final class FxRateId implements MarketDataId<Double>, ImmutableBean {

  /** The currency pair whose rate this identifies. */
  @PropertyDefinition(validate = "notNull")
  private final CurrencyPair _currencyPair;

  /**
   * Creates an ID for the FX rate for a currency pair.
   *
   * @param currencyPair a currency pair
   * @return an ID for the FX rate for the currency pair
   */
  public static FxRateId of(CurrencyPair currencyPair) {
    return new FxRateId(currencyPair);
  }

  /**
   * Creates an ID for the FX rate for a currency pair.
   *
   * @param base the base currency of the pair
   * @param counter the counter currency of the pair
   * @return an ID for the FX rate for the currency pair
   */
  public static FxRateId of(Currency base, Currency counter) {
    return new FxRateId(CurrencyPair.of(base, counter));
  }

  @Override
  public Class<Double> getMarketDataType() {
    return Double.class;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FxRateId}.
   * @return the meta-bean, not null
   */
  public static FxRateId.Meta meta() {
    return FxRateId.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FxRateId.Meta.INSTANCE);
  }

  private FxRateId(
      CurrencyPair currencyPair) {
    JodaBeanUtils.notNull(currencyPair, "currencyPair");
    this._currencyPair = currencyPair;
  }

  @Override
  public FxRateId.Meta metaBean() {
    return FxRateId.Meta.INSTANCE;
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
   * Gets the currency pair whose rate this identifies.
   * @return the value of the property, not null
   */
  public CurrencyPair getCurrencyPair() {
    return _currencyPair;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FxRateId other = (FxRateId) obj;
      return JodaBeanUtils.equal(getCurrencyPair(), other.getCurrencyPair());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getCurrencyPair());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("FxRateId{");
    buf.append("currencyPair").append('=').append(JodaBeanUtils.toString(getCurrencyPair()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FxRateId}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code currencyPair} property.
     */
    private final MetaProperty<CurrencyPair> _currencyPair = DirectMetaProperty.ofImmutable(
        this, "currencyPair", FxRateId.class, CurrencyPair.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "currencyPair");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1005147787:  // currencyPair
          return _currencyPair;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FxRateId> builder() {
      return new FxRateId.Builder();
    }

    @Override
    public Class<? extends FxRateId> beanType() {
      return FxRateId.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code currencyPair} property.
     * @return the meta-property, not null
     */
    public MetaProperty<CurrencyPair> currencyPair() {
      return _currencyPair;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1005147787:  // currencyPair
          return ((FxRateId) bean).getCurrencyPair();
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
   * The bean-builder for {@code FxRateId}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<FxRateId> {

    private CurrencyPair _currencyPair;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1005147787:  // currencyPair
          return _currencyPair;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 1005147787:  // currencyPair
          this._currencyPair = (CurrencyPair) newValue;
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
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public FxRateId build() {
      return new FxRateId(
          _currencyPair);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("FxRateId.Builder{");
      buf.append("currencyPair").append('=').append(JodaBeanUtils.toString(_currencyPair));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
