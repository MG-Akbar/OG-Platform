/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.marketdata.scenarios;

import java.util.HashMap;
import java.util.List;
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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.opengamma.analytics.ShiftType;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldCurve;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldCurveUtils;
import com.opengamma.analytics.financial.provider.curve.CurveBuildingBlockBundle;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderDiscount;
import com.opengamma.sesame.CurveNodeId;
import com.opengamma.sesame.MulticurveBundle;
import com.opengamma.sesame.marketdata.MarketDataUtils;

/**
 * Perturbation that can apply a different shift to each point on a curve.
 * <p>
 * The {@link CurveNodeId} instances defining the shifts must correspond to the curve node IDs in the
 * curve's {@link MulticurveBundle}.
 */
@BeanDefinition
public class MulticurvePointShift implements Perturbation, ImmutableBean {

  /** The shifts to apply to the curve nodes, keyed by the ID of node. */
  @PropertyDefinition(validate = "notNull")
  private final Map<CurveNodeId, Double> _shifts;

  /**
   * How the shift should be applied to the node values.
   * <p>
   * A relative shift of 0.1 (+10%) scales the point value by 1.1, a relative shift of -0.2 (-20%) scales the
   * point value by 0.8.
   * <p>
   * An absolute shift adds the shift amount to the curve value at the nodal point.
   */
  @PropertyDefinition(validate = "notNull")
  private final ShiftType _shiftType;

  @Override
  public Object apply(Object marketData, MatchDetails matchDetails) {
    MulticurveBundle bundle = ((MulticurveBundle) marketData);
    String curveName = ((MulticurveMatchDetails) matchDetails).getCurveName();
    MulticurveProviderDiscount multicurve = bundle.getMulticurveProvider();
    // This is safe ATM, all curves are YieldCurves. this will need to be updated when that changes.
    YieldCurve curve = (YieldCurve) multicurve.getCurve(curveName);
    Double[] xData = curve.getCurve().getXData();
    ImmutableList.Builder<Double> timeValuesBuilder = ImmutableList.builder();
    ImmutableList.Builder<Double> shiftValuesBuilder = ImmutableList.builder();

    for (Map.Entry<CurveNodeId, Double> entry : _shifts.entrySet()) {
      CurveNodeId nodeId = entry.getKey();
      Double shift = entry.getValue();
      Integer index = bundle.curveNodeIndex(curveName, nodeId);

      if (index != null) {
        timeValuesBuilder.add(xData[index]);
        shiftValuesBuilder.add(shift);
      }
    }
    List<Double> timeValues = timeValuesBuilder.build();
    List<Double> shiftValues = shiftValuesBuilder.build();
    YieldCurve shiftedCurve = YieldCurveUtils.withPointShifts(curve, timeValues, shiftValues, _shiftType, "");
    // In future it might be necessary to check the shifted curve and apply some post-processing rules at this point
    //   * No zero rates allowed
    //   * No negative rates allowed
    //   * Curve slope must not be negative
    MulticurveProviderDiscount shiftedMulticurve = MarketDataUtils.replaceCurve(multicurve, shiftedCurve);
    return MulticurveBundle.builder()
        .multicurveProvider(shiftedMulticurve)
        .curveNodeIndices(bundle.getCurveNodeIndices())
        .curveBuildingBlockBundle(new CurveBuildingBlockBundle()).build();
  }

  @Override
  public Class<?> getMarketDataType() {
    return MulticurveBundle.class;
  }

  @Override
  public Class<? extends MatchDetails> getMatchDetailsType() {
    return MulticurveMatchDetails.class;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code MulticurvePointShift}.
   * @return the meta-bean, not null
   */
  public static MulticurvePointShift.Meta meta() {
    return MulticurvePointShift.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(MulticurvePointShift.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static MulticurvePointShift.Builder builder() {
    return new MulticurvePointShift.Builder();
  }

  /**
   * Restricted constructor.
   * @param builder  the builder to copy from, not null
   */
  protected MulticurvePointShift(MulticurvePointShift.Builder builder) {
    JodaBeanUtils.notNull(builder._shifts, "shifts");
    JodaBeanUtils.notNull(builder._shiftType, "shiftType");
    this._shifts = ImmutableMap.copyOf(builder._shifts);
    this._shiftType = builder._shiftType;
  }

  @Override
  public MulticurvePointShift.Meta metaBean() {
    return MulticurvePointShift.Meta.INSTANCE;
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
   * Gets the shifts to apply to the curve nodes, keyed by the ID of node.
   * @return the value of the property, not null
   */
  public Map<CurveNodeId, Double> getShifts() {
    return _shifts;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets how the shift should be applied to the node values.
   * <p>
   * A relative shift of 0.1 (+10%) scales the point value by 1.1, a relative shift of -0.2 (-20%) scales the
   * point value by 0.8.
   * <p>
   * An absolute shift adds the shift amount to the curve value at the nodal point.
   * @return the value of the property, not null
   */
  public ShiftType getShiftType() {
    return _shiftType;
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
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      MulticurvePointShift other = (MulticurvePointShift) obj;
      return JodaBeanUtils.equal(getShifts(), other.getShifts()) &&
          JodaBeanUtils.equal(getShiftType(), other.getShiftType());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getShifts());
    hash = hash * 31 + JodaBeanUtils.hashCode(getShiftType());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("MulticurvePointShift{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("shifts").append('=').append(JodaBeanUtils.toString(getShifts())).append(',').append(' ');
    buf.append("shiftType").append('=').append(JodaBeanUtils.toString(getShiftType())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code MulticurvePointShift}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code shifts} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Map<CurveNodeId, Double>> _shifts = DirectMetaProperty.ofImmutable(
        this, "shifts", MulticurvePointShift.class, (Class) Map.class);
    /**
     * The meta-property for the {@code shiftType} property.
     */
    private final MetaProperty<ShiftType> _shiftType = DirectMetaProperty.ofImmutable(
        this, "shiftType", MulticurvePointShift.class, ShiftType.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "shifts",
        "shiftType");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -903338959:  // shifts
          return _shifts;
        case 893345500:  // shiftType
          return _shiftType;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public MulticurvePointShift.Builder builder() {
      return new MulticurvePointShift.Builder();
    }

    @Override
    public Class<? extends MulticurvePointShift> beanType() {
      return MulticurvePointShift.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code shifts} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Map<CurveNodeId, Double>> shifts() {
      return _shifts;
    }

    /**
     * The meta-property for the {@code shiftType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ShiftType> shiftType() {
      return _shiftType;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -903338959:  // shifts
          return ((MulticurvePointShift) bean).getShifts();
        case 893345500:  // shiftType
          return ((MulticurvePointShift) bean).getShiftType();
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
   * The bean-builder for {@code MulticurvePointShift}.
   */
  public static class Builder extends DirectFieldsBeanBuilder<MulticurvePointShift> {

    private Map<CurveNodeId, Double> _shifts = new HashMap<CurveNodeId, Double>();
    private ShiftType _shiftType;

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    protected Builder(MulticurvePointShift beanToCopy) {
      this._shifts = new HashMap<CurveNodeId, Double>(beanToCopy.getShifts());
      this._shiftType = beanToCopy.getShiftType();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -903338959:  // shifts
          return _shifts;
        case 893345500:  // shiftType
          return _shiftType;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -903338959:  // shifts
          this._shifts = (Map<CurveNodeId, Double>) newValue;
          break;
        case 893345500:  // shiftType
          this._shiftType = (ShiftType) newValue;
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
    public MulticurvePointShift build() {
      return new MulticurvePointShift(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code shifts} property in the builder.
     * @param shifts  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder shifts(Map<CurveNodeId, Double> shifts) {
      JodaBeanUtils.notNull(shifts, "shifts");
      this._shifts = shifts;
      return this;
    }

    /**
     * Sets the {@code shiftType} property in the builder.
     * @param shiftType  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder shiftType(ShiftType shiftType) {
      JodaBeanUtils.notNull(shiftType, "shiftType");
      this._shiftType = shiftType;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("MulticurvePointShift.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    protected void toString(StringBuilder buf) {
      buf.append("shifts").append('=').append(JodaBeanUtils.toString(_shifts)).append(',').append(' ');
      buf.append("shiftType").append('=').append(JodaBeanUtils.toString(_shiftType)).append(',').append(' ');
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
