/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve.strips;

import java.util.Map;

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

import com.opengamma.id.ExternalId;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Tenor;

/**
 * A curve node using an FX swap.
 */
@BeanDefinition
public class FXSwapNode extends CurveNode {

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The start tenor.
   */
  @PropertyDefinition(validate = "notNull")
  private Tenor _startTenor;

  /**
   * The maturity tenor.
   */
  @PropertyDefinition(validate = "notNull")
  private Tenor _maturityTenor;

  /**
   * The FX swap convention.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _fxSwapConvention;

  /**
   * The pay currency.
   */
  @PropertyDefinition(validate = "notNull")
  private Currency _payCurrency;

  /**
   * The receive currency.
   */
  @PropertyDefinition(validate = "notNull")
  private Currency _receiveCurrency;

  /**
   * @param startTenor The start tenor, not null
   * @param maturityTenor The maturity tenor, not null
   * @param fxSwapConvention The FX forward convention, not null
   * @param payCurrency The pay currency, not null
   * @param receiveCurrency The receive currency, not null
   * @param curveNodeIdMapperName The curve node id mapper name, not null
   */
  public FXSwapNode(final Tenor startTenor,
                    final Tenor maturityTenor,
                    final ExternalId fxSwapConvention,
                    final Currency payCurrency,
                    final Currency receiveCurrency,
                    final String curveNodeIdMapperName) {
    super(curveNodeIdMapperName);
    setStartTenor(startTenor);
    setMaturityTenor(maturityTenor);
    setFxSwapConvention(fxSwapConvention);
    setPayCurrency(payCurrency);
    setReceiveCurrency(receiveCurrency);
  }

  /**
   * @param startTenor The start tenor, not null
   * @param maturityTenor The maturity tenor, not null
   * @param fxSwapConvention The FX forward convention, not null
   * @param payCurrency The pay currency, not null
   * @param receiveCurrency The receive currency, not null
   * @param curveNodeIdMapperName The curve node id mapper name, not null
   * @param name The name
   */
  public FXSwapNode(final Tenor startTenor,
                    final Tenor maturityTenor,
                    final ExternalId fxSwapConvention,
                    final Currency payCurrency,
                    final Currency receiveCurrency,
                    final String curveNodeIdMapperName,
                    final String name) {
    super(curveNodeIdMapperName, name);
    setStartTenor(startTenor);
    setMaturityTenor(maturityTenor);
    setFxSwapConvention(fxSwapConvention);
    setPayCurrency(payCurrency);
    setReceiveCurrency(receiveCurrency);
  }

  /**
   * Private constructor for the builder.
   */
  private FXSwapNode() {
  }

  @Override
  public Tenor getResolvedMaturity() {
    return Tenor.of(_startTenor.getPeriod().plus(_maturityTenor.getPeriod())); // _maturityTenor;
  }

  @Override
  public <T> T accept(final CurveNodeVisitor<T> visitor) {
    ArgumentChecker.notNull(visitor, "visitor");
    return visitor.visitFXSwapNode(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FXSwapNode}.
   * @return the meta-bean, not null
   */
  public static FXSwapNode.Meta meta() {
    return FXSwapNode.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FXSwapNode.Meta.INSTANCE);
  }

  @Override
  public FXSwapNode.Meta metaBean() {
    return FXSwapNode.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the start tenor.
   * @return the value of the property, not null
   */
  public Tenor getStartTenor() {
    return _startTenor;
  }

  /**
   * Sets the start tenor.
   * @param startTenor  the new value of the property, not null
   */
  public void setStartTenor(Tenor startTenor) {
    JodaBeanUtils.notNull(startTenor, "startTenor");
    this._startTenor = startTenor;
  }

  /**
   * Gets the the {@code startTenor} property.
   * @return the property, not null
   */
  public final Property<Tenor> startTenor() {
    return metaBean().startTenor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the maturity tenor.
   * @return the value of the property, not null
   */
  public Tenor getMaturityTenor() {
    return _maturityTenor;
  }

  /**
   * Sets the maturity tenor.
   * @param maturityTenor  the new value of the property, not null
   */
  public void setMaturityTenor(Tenor maturityTenor) {
    JodaBeanUtils.notNull(maturityTenor, "maturityTenor");
    this._maturityTenor = maturityTenor;
  }

  /**
   * Gets the the {@code maturityTenor} property.
   * @return the property, not null
   */
  public final Property<Tenor> maturityTenor() {
    return metaBean().maturityTenor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the FX swap convention.
   * @return the value of the property, not null
   */
  public ExternalId getFxSwapConvention() {
    return _fxSwapConvention;
  }

  /**
   * Sets the FX swap convention.
   * @param fxSwapConvention  the new value of the property, not null
   */
  public void setFxSwapConvention(ExternalId fxSwapConvention) {
    JodaBeanUtils.notNull(fxSwapConvention, "fxSwapConvention");
    this._fxSwapConvention = fxSwapConvention;
  }

  /**
   * Gets the the {@code fxSwapConvention} property.
   * @return the property, not null
   */
  public final Property<ExternalId> fxSwapConvention() {
    return metaBean().fxSwapConvention().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the pay currency.
   * @return the value of the property, not null
   */
  public Currency getPayCurrency() {
    return _payCurrency;
  }

  /**
   * Sets the pay currency.
   * @param payCurrency  the new value of the property, not null
   */
  public void setPayCurrency(Currency payCurrency) {
    JodaBeanUtils.notNull(payCurrency, "payCurrency");
    this._payCurrency = payCurrency;
  }

  /**
   * Gets the the {@code payCurrency} property.
   * @return the property, not null
   */
  public final Property<Currency> payCurrency() {
    return metaBean().payCurrency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the receive currency.
   * @return the value of the property, not null
   */
  public Currency getReceiveCurrency() {
    return _receiveCurrency;
  }

  /**
   * Sets the receive currency.
   * @param receiveCurrency  the new value of the property, not null
   */
  public void setReceiveCurrency(Currency receiveCurrency) {
    JodaBeanUtils.notNull(receiveCurrency, "receiveCurrency");
    this._receiveCurrency = receiveCurrency;
  }

  /**
   * Gets the the {@code receiveCurrency} property.
   * @return the property, not null
   */
  public final Property<Currency> receiveCurrency() {
    return metaBean().receiveCurrency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public FXSwapNode clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FXSwapNode other = (FXSwapNode) obj;
      return JodaBeanUtils.equal(getStartTenor(), other.getStartTenor()) &&
          JodaBeanUtils.equal(getMaturityTenor(), other.getMaturityTenor()) &&
          JodaBeanUtils.equal(getFxSwapConvention(), other.getFxSwapConvention()) &&
          JodaBeanUtils.equal(getPayCurrency(), other.getPayCurrency()) &&
          JodaBeanUtils.equal(getReceiveCurrency(), other.getReceiveCurrency()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getStartTenor());
    hash = hash * 31 + JodaBeanUtils.hashCode(getMaturityTenor());
    hash = hash * 31 + JodaBeanUtils.hashCode(getFxSwapConvention());
    hash = hash * 31 + JodaBeanUtils.hashCode(getPayCurrency());
    hash = hash * 31 + JodaBeanUtils.hashCode(getReceiveCurrency());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("FXSwapNode{");
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
    buf.append("startTenor").append('=').append(JodaBeanUtils.toString(getStartTenor())).append(',').append(' ');
    buf.append("maturityTenor").append('=').append(JodaBeanUtils.toString(getMaturityTenor())).append(',').append(' ');
    buf.append("fxSwapConvention").append('=').append(JodaBeanUtils.toString(getFxSwapConvention())).append(',').append(' ');
    buf.append("payCurrency").append('=').append(JodaBeanUtils.toString(getPayCurrency())).append(',').append(' ');
    buf.append("receiveCurrency").append('=').append(JodaBeanUtils.toString(getReceiveCurrency())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FXSwapNode}.
   */
  public static class Meta extends CurveNode.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code startTenor} property.
     */
    private final MetaProperty<Tenor> _startTenor = DirectMetaProperty.ofReadWrite(
        this, "startTenor", FXSwapNode.class, Tenor.class);
    /**
     * The meta-property for the {@code maturityTenor} property.
     */
    private final MetaProperty<Tenor> _maturityTenor = DirectMetaProperty.ofReadWrite(
        this, "maturityTenor", FXSwapNode.class, Tenor.class);
    /**
     * The meta-property for the {@code fxSwapConvention} property.
     */
    private final MetaProperty<ExternalId> _fxSwapConvention = DirectMetaProperty.ofReadWrite(
        this, "fxSwapConvention", FXSwapNode.class, ExternalId.class);
    /**
     * The meta-property for the {@code payCurrency} property.
     */
    private final MetaProperty<Currency> _payCurrency = DirectMetaProperty.ofReadWrite(
        this, "payCurrency", FXSwapNode.class, Currency.class);
    /**
     * The meta-property for the {@code receiveCurrency} property.
     */
    private final MetaProperty<Currency> _receiveCurrency = DirectMetaProperty.ofReadWrite(
        this, "receiveCurrency", FXSwapNode.class, Currency.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "startTenor",
        "maturityTenor",
        "fxSwapConvention",
        "payCurrency",
        "receiveCurrency");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1583746178:  // startTenor
          return _startTenor;
        case 45907375:  // maturityTenor
          return _maturityTenor;
        case 394899158:  // fxSwapConvention
          return _fxSwapConvention;
        case -295641895:  // payCurrency
          return _payCurrency;
        case -1228590060:  // receiveCurrency
          return _receiveCurrency;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FXSwapNode> builder() {
      return new DirectBeanBuilder<FXSwapNode>(new FXSwapNode());
    }

    @Override
    public Class<? extends FXSwapNode> beanType() {
      return FXSwapNode.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code startTenor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Tenor> startTenor() {
      return _startTenor;
    }

    /**
     * The meta-property for the {@code maturityTenor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Tenor> maturityTenor() {
      return _maturityTenor;
    }

    /**
     * The meta-property for the {@code fxSwapConvention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> fxSwapConvention() {
      return _fxSwapConvention;
    }

    /**
     * The meta-property for the {@code payCurrency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Currency> payCurrency() {
      return _payCurrency;
    }

    /**
     * The meta-property for the {@code receiveCurrency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Currency> receiveCurrency() {
      return _receiveCurrency;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1583746178:  // startTenor
          return ((FXSwapNode) bean).getStartTenor();
        case 45907375:  // maturityTenor
          return ((FXSwapNode) bean).getMaturityTenor();
        case 394899158:  // fxSwapConvention
          return ((FXSwapNode) bean).getFxSwapConvention();
        case -295641895:  // payCurrency
          return ((FXSwapNode) bean).getPayCurrency();
        case -1228590060:  // receiveCurrency
          return ((FXSwapNode) bean).getReceiveCurrency();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1583746178:  // startTenor
          ((FXSwapNode) bean).setStartTenor((Tenor) newValue);
          return;
        case 45907375:  // maturityTenor
          ((FXSwapNode) bean).setMaturityTenor((Tenor) newValue);
          return;
        case 394899158:  // fxSwapConvention
          ((FXSwapNode) bean).setFxSwapConvention((ExternalId) newValue);
          return;
        case -295641895:  // payCurrency
          ((FXSwapNode) bean).setPayCurrency((Currency) newValue);
          return;
        case -1228590060:  // receiveCurrency
          ((FXSwapNode) bean).setReceiveCurrency((Currency) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((FXSwapNode) bean)._startTenor, "startTenor");
      JodaBeanUtils.notNull(((FXSwapNode) bean)._maturityTenor, "maturityTenor");
      JodaBeanUtils.notNull(((FXSwapNode) bean)._fxSwapConvention, "fxSwapConvention");
      JodaBeanUtils.notNull(((FXSwapNode) bean)._payCurrency, "payCurrency");
      JodaBeanUtils.notNull(((FXSwapNode) bean)._receiveCurrency, "receiveCurrency");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
