/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.tool.portfolio.xml.v1_0.jaxb;

import java.math.BigDecimal;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.integration.tool.portfolio.xml.v1_0.conversion.FxOptionTradeSecurityExtractor;
import com.opengamma.integration.tool.portfolio.xml.v1_0.conversion.TradeSecurityExtractor;
import com.opengamma.util.money.Currency;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@BeanDefinition
public class FxOptionTrade extends AbstractFxOptionTrade {

  @XmlElement(name = "notional", required = true)
  @PropertyDefinition
  private BigDecimal _notional;

  @XmlElement(name = "notionalCurrency", required = true)
  @PropertyDefinition
  private Currency _notionalCurrency;

  @XmlElement(name = "settlementType")
  @PropertyDefinition
  private SettlementType _settlementType;

  @XmlElement(name = "settlementCurrency")
  @PropertyDefinition
  private Currency _settlementCurrency;

  @XmlElement(name = "exerciseType")
  @PropertyDefinition
  private ExerciseType _exerciseType;

  @Override
  public TradeSecurityExtractor getSecurityExtractor() {
    return new FxOptionTradeSecurityExtractor(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FxOptionTrade}.
   * @return the meta-bean, not null
   */
  public static FxOptionTrade.Meta meta() {
    return FxOptionTrade.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FxOptionTrade.Meta.INSTANCE);
  }

  @Override
  public FxOptionTrade.Meta metaBean() {
    return FxOptionTrade.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the notional.
   * @return the value of the property
   */
  public BigDecimal getNotional() {
    return _notional;
  }

  /**
   * Sets the notional.
   * @param notional  the new value of the property
   */
  public void setNotional(BigDecimal notional) {
    this._notional = notional;
  }

  /**
   * Gets the the {@code notional} property.
   * @return the property, not null
   */
  public final Property<BigDecimal> notional() {
    return metaBean().notional().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the notionalCurrency.
   * @return the value of the property
   */
  public Currency getNotionalCurrency() {
    return _notionalCurrency;
  }

  /**
   * Sets the notionalCurrency.
   * @param notionalCurrency  the new value of the property
   */
  public void setNotionalCurrency(Currency notionalCurrency) {
    this._notionalCurrency = notionalCurrency;
  }

  /**
   * Gets the the {@code notionalCurrency} property.
   * @return the property, not null
   */
  public final Property<Currency> notionalCurrency() {
    return metaBean().notionalCurrency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the settlementType.
   * @return the value of the property
   */
  public SettlementType getSettlementType() {
    return _settlementType;
  }

  /**
   * Sets the settlementType.
   * @param settlementType  the new value of the property
   */
  public void setSettlementType(SettlementType settlementType) {
    this._settlementType = settlementType;
  }

  /**
   * Gets the the {@code settlementType} property.
   * @return the property, not null
   */
  public final Property<SettlementType> settlementType() {
    return metaBean().settlementType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the settlementCurrency.
   * @return the value of the property
   */
  public Currency getSettlementCurrency() {
    return _settlementCurrency;
  }

  /**
   * Sets the settlementCurrency.
   * @param settlementCurrency  the new value of the property
   */
  public void setSettlementCurrency(Currency settlementCurrency) {
    this._settlementCurrency = settlementCurrency;
  }

  /**
   * Gets the the {@code settlementCurrency} property.
   * @return the property, not null
   */
  public final Property<Currency> settlementCurrency() {
    return metaBean().settlementCurrency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the exerciseType.
   * @return the value of the property
   */
  public ExerciseType getExerciseType() {
    return _exerciseType;
  }

  /**
   * Sets the exerciseType.
   * @param exerciseType  the new value of the property
   */
  public void setExerciseType(ExerciseType exerciseType) {
    this._exerciseType = exerciseType;
  }

  /**
   * Gets the the {@code exerciseType} property.
   * @return the property, not null
   */
  public final Property<ExerciseType> exerciseType() {
    return metaBean().exerciseType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public FxOptionTrade clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FxOptionTrade other = (FxOptionTrade) obj;
      return JodaBeanUtils.equal(getNotional(), other.getNotional()) &&
          JodaBeanUtils.equal(getNotionalCurrency(), other.getNotionalCurrency()) &&
          JodaBeanUtils.equal(getSettlementType(), other.getSettlementType()) &&
          JodaBeanUtils.equal(getSettlementCurrency(), other.getSettlementCurrency()) &&
          JodaBeanUtils.equal(getExerciseType(), other.getExerciseType()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getNotional());
    hash = hash * 31 + JodaBeanUtils.hashCode(getNotionalCurrency());
    hash = hash * 31 + JodaBeanUtils.hashCode(getSettlementType());
    hash = hash * 31 + JodaBeanUtils.hashCode(getSettlementCurrency());
    hash = hash * 31 + JodaBeanUtils.hashCode(getExerciseType());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("FxOptionTrade{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  @Override
  protected void toString(StringBuilder buf) {
    super.toString(buf);
    buf.append("notional").append('=').append(JodaBeanUtils.toString(getNotional())).append(',').append(' ');
    buf.append("notionalCurrency").append('=').append(JodaBeanUtils.toString(getNotionalCurrency())).append(',').append(' ');
    buf.append("settlementType").append('=').append(JodaBeanUtils.toString(getSettlementType())).append(',').append(' ');
    buf.append("settlementCurrency").append('=').append(JodaBeanUtils.toString(getSettlementCurrency())).append(',').append(' ');
    buf.append("exerciseType").append('=').append(JodaBeanUtils.toString(getExerciseType())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FxOptionTrade}.
   */
  public static class Meta extends AbstractFxOptionTrade.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code notional} property.
     */
    private final MetaProperty<BigDecimal> _notional = DirectMetaProperty.ofReadWrite(
        this, "notional", FxOptionTrade.class, BigDecimal.class);
    /**
     * The meta-property for the {@code notionalCurrency} property.
     */
    private final MetaProperty<Currency> _notionalCurrency = DirectMetaProperty.ofReadWrite(
        this, "notionalCurrency", FxOptionTrade.class, Currency.class);
    /**
     * The meta-property for the {@code settlementType} property.
     */
    private final MetaProperty<SettlementType> _settlementType = DirectMetaProperty.ofReadWrite(
        this, "settlementType", FxOptionTrade.class, SettlementType.class);
    /**
     * The meta-property for the {@code settlementCurrency} property.
     */
    private final MetaProperty<Currency> _settlementCurrency = DirectMetaProperty.ofReadWrite(
        this, "settlementCurrency", FxOptionTrade.class, Currency.class);
    /**
     * The meta-property for the {@code exerciseType} property.
     */
    private final MetaProperty<ExerciseType> _exerciseType = DirectMetaProperty.ofReadWrite(
        this, "exerciseType", FxOptionTrade.class, ExerciseType.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "notional",
        "notionalCurrency",
        "settlementType",
        "settlementCurrency",
        "exerciseType");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1585636160:  // notional
          return _notional;
        case -1573783695:  // notionalCurrency
          return _notionalCurrency;
        case -295448573:  // settlementType
          return _settlementType;
        case -1024875430:  // settlementCurrency
          return _settlementCurrency;
        case -466331342:  // exerciseType
          return _exerciseType;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FxOptionTrade> builder() {
      return new DirectBeanBuilder<FxOptionTrade>(new FxOptionTrade());
    }

    @Override
    public Class<? extends FxOptionTrade> beanType() {
      return FxOptionTrade.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code notional} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BigDecimal> notional() {
      return _notional;
    }

    /**
     * The meta-property for the {@code notionalCurrency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Currency> notionalCurrency() {
      return _notionalCurrency;
    }

    /**
     * The meta-property for the {@code settlementType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<SettlementType> settlementType() {
      return _settlementType;
    }

    /**
     * The meta-property for the {@code settlementCurrency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Currency> settlementCurrency() {
      return _settlementCurrency;
    }

    /**
     * The meta-property for the {@code exerciseType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExerciseType> exerciseType() {
      return _exerciseType;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1585636160:  // notional
          return ((FxOptionTrade) bean).getNotional();
        case -1573783695:  // notionalCurrency
          return ((FxOptionTrade) bean).getNotionalCurrency();
        case -295448573:  // settlementType
          return ((FxOptionTrade) bean).getSettlementType();
        case -1024875430:  // settlementCurrency
          return ((FxOptionTrade) bean).getSettlementCurrency();
        case -466331342:  // exerciseType
          return ((FxOptionTrade) bean).getExerciseType();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1585636160:  // notional
          ((FxOptionTrade) bean).setNotional((BigDecimal) newValue);
          return;
        case -1573783695:  // notionalCurrency
          ((FxOptionTrade) bean).setNotionalCurrency((Currency) newValue);
          return;
        case -295448573:  // settlementType
          ((FxOptionTrade) bean).setSettlementType((SettlementType) newValue);
          return;
        case -1024875430:  // settlementCurrency
          ((FxOptionTrade) bean).setSettlementCurrency((Currency) newValue);
          return;
        case -466331342:  // exerciseType
          ((FxOptionTrade) bean).setExerciseType((ExerciseType) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
