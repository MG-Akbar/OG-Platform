/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.tool.portfolio.xml.v1_0.jaxb;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.beans.BeanDefinition;
import org.joda.beans.PropertyDefinition;

import com.opengamma.integration.tool.portfolio.xml.v1_0.conversion.FxDigitalOptionTradeSecurityExtractor;
import com.opengamma.integration.tool.portfolio.xml.v1_0.conversion.TradeSecurityExtractor;
import com.opengamma.util.money.Currency;
import java.util.Map;
import org.joda.beans.BeanBuilder;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@BeanDefinition
public class FxDigitalOptionTrade extends AbstractFxOptionTrade {

  @XmlElement(name = "payout", required = true)
  @PropertyDefinition
  private BigDecimal _payout;

  @XmlElement(name = "payoutCurrency", required = true)
  @PropertyDefinition
  private Currency _payoutCurrency;

  @Override
  public TradeSecurityExtractor getSecurityExtractor() {
    return new FxDigitalOptionTradeSecurityExtractor(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FxDigitalOptionTrade}.
   * @return the meta-bean, not null
   */
  public static FxDigitalOptionTrade.Meta meta() {
    return FxDigitalOptionTrade.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(FxDigitalOptionTrade.Meta.INSTANCE);
  }

  @Override
  public FxDigitalOptionTrade.Meta metaBean() {
    return FxDigitalOptionTrade.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -995205722:  // payout
        return getPayout();
      case 1569172439:  // payoutCurrency
        return getPayoutCurrency();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -995205722:  // payout
        setPayout((BigDecimal) newValue);
        return;
      case 1569172439:  // payoutCurrency
        setPayoutCurrency((Currency) newValue);
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
      FxDigitalOptionTrade other = (FxDigitalOptionTrade) obj;
      return JodaBeanUtils.equal(getPayout(), other.getPayout()) &&
          JodaBeanUtils.equal(getPayoutCurrency(), other.getPayoutCurrency()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getPayout());
    hash += hash * 31 + JodaBeanUtils.hashCode(getPayoutCurrency());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the payout.
   * @return the value of the property
   */
  public BigDecimal getPayout() {
    return _payout;
  }

  /**
   * Sets the payout.
   * @param payout  the new value of the property
   */
  public void setPayout(BigDecimal payout) {
    this._payout = payout;
  }

  /**
   * Gets the the {@code payout} property.
   * @return the property, not null
   */
  public final Property<BigDecimal> payout() {
    return metaBean().payout().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the payoutCurrency.
   * @return the value of the property
   */
  public Currency getPayoutCurrency() {
    return _payoutCurrency;
  }

  /**
   * Sets the payoutCurrency.
   * @param payoutCurrency  the new value of the property
   */
  public void setPayoutCurrency(Currency payoutCurrency) {
    this._payoutCurrency = payoutCurrency;
  }

  /**
   * Gets the the {@code payoutCurrency} property.
   * @return the property, not null
   */
  public final Property<Currency> payoutCurrency() {
    return metaBean().payoutCurrency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FxDigitalOptionTrade}.
   */
  public static class Meta extends AbstractFxOptionTrade.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code payout} property.
     */
    private final MetaProperty<BigDecimal> _payout = DirectMetaProperty.ofReadWrite(
        this, "payout", FxDigitalOptionTrade.class, BigDecimal.class);
    /**
     * The meta-property for the {@code payoutCurrency} property.
     */
    private final MetaProperty<Currency> _payoutCurrency = DirectMetaProperty.ofReadWrite(
        this, "payoutCurrency", FxDigitalOptionTrade.class, Currency.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "payout",
        "payoutCurrency");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -995205722:  // payout
          return _payout;
        case 1569172439:  // payoutCurrency
          return _payoutCurrency;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FxDigitalOptionTrade> builder() {
      return new DirectBeanBuilder<FxDigitalOptionTrade>(new FxDigitalOptionTrade());
    }

    @Override
    public Class<? extends FxDigitalOptionTrade> beanType() {
      return FxDigitalOptionTrade.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code payout} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BigDecimal> payout() {
      return _payout;
    }

    /**
     * The meta-property for the {@code payoutCurrency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Currency> payoutCurrency() {
      return _payoutCurrency;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
