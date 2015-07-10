/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.legalentity;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Pattern;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableConstructor;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.util.ArgumentChecker;

/**
 * Class representing a Global Industry Classification Standard Code (GICS).
 * GICS codes are eight-digit numbers used to classify the industry of a
 * company.
 * <p>
 * The digits are divided into four pairs representing:
 * <ul>
 * <li>Sector
 * <li>Industry group
 * <li>Industry
 * <li>Sub-industry
 * </ul>
 * <p>
 * This class is thread-safe and immutable.
 * <p>
 * See <a href="http://www.spindices.com/documents/index-policies/methodology-gics.pdf">http://www.spindices.com/documents/index-policies/methodology-gics.pdf</a>
 */
@BeanDefinition(builderScope = "private")
public final class GICSCode implements ImmutableBean, Serializable {
  /** Pattern for the code. */
  private static final Pattern FORMAT = Pattern.compile("([1-9][0-9]){1,4}");

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The GICS classification name.
   */
  public static final String NAME = "GICS";

  /**
   * The code.
   */
  @PropertyDefinition(get = "manual", validate = "notNull")
  private final String _code;

  /**
   * The sector.
   */
  private final String _sector;

  /**
   * The industry group.
   */
  private final String _industryGroup;

  /**
   * The industry.
   */
  private final String _industry;

  /**
   * Constructs a GICS code from an integer.
   * @param code The code as an integer, greater than 10 and less than 100000000
   * @return The GICS
   */
  public static GICSCode of(final int code) {
    if ((code < 10) || (code > 99999999)) {
      throw new IllegalArgumentException("Code out of range: " + code);
    }
    return GICSCode.of(Integer.toString(code));
  }

  /**
   * Constructs a GICS code from a string.
   * @param code The code as a string, not null
   * @return The GICS
   * @throws IllegalArgumentException If the code entered is not an eight-digit number
   * of the appropriate pattern
   */
  public static GICSCode of(final String code) {
    ArgumentChecker.notNull(code, "code");
    if (!FORMAT.matcher(code).matches()) {
      throw new IllegalArgumentException("Invalid code : " + code);
    }
    return new GICSCode(code);
  }

  /**
   * @param code The GICS code, not null
   */
  @ImmutableConstructor
  private GICSCode(final String code) {
    ArgumentChecker.notNull(code, "code");
    _code = code;
    _sector = code.substring(0, 2);
    _industryGroup = code.length() <= 4 ? "" : code.substring(0, 4);
    _industry = code.length() <= 6 ? "" : code.substring(0, 6);
  }

  /**
   * Gets the name of this classification type.
   * @return The name
   */
  public String getClassificationName() {
    return NAME;
  }

  /**
   * Gets the GICS code.
   * @return The GICS code
   */
  public String getCode() {
    return _code;
  }

  /**
   * Gets the sector.
   * @return The sector
   */
  public String getSector() {
    return _sector;
  }

  /**
   * Gets the industry group. Returns an empty string if the code does
   * not contain this information.
   * @return The industry group
   */
  public String getIndustryGroup() {
    return _industryGroup;
  }

  /**
   * Gets the industry. Returns an empty string if the code does not
   * contain this information.
   * @return The industry
   */
  public String getIndustry() {
    return _industry;
  }

  /**
   * Gets the sub-industry. Returns an empty string if the code does not
   * contain this information.
   * @return The sub-industry
   */
  public String getSubIndustry() {
    return _code;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code GICSCode}.
   * @return the meta-bean, not null
   */
  public static GICSCode.Meta meta() {
    return GICSCode.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(GICSCode.Meta.INSTANCE);
  }

  @Override
  public GICSCode.Meta metaBean() {
    return GICSCode.Meta.INSTANCE;
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
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      GICSCode other = (GICSCode) obj;
      return JodaBeanUtils.equal(getCode(), other.getCode());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getCode());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("GICSCode{");
    buf.append("code").append('=').append(JodaBeanUtils.toString(getCode()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code GICSCode}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code code} property.
     */
    private final MetaProperty<String> _code = DirectMetaProperty.ofImmutable(
        this, "code", GICSCode.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "code");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3059181:  // code
          return _code;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends GICSCode> builder() {
      return new GICSCode.Builder();
    }

    @Override
    public Class<? extends GICSCode> beanType() {
      return GICSCode.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code code} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> code() {
      return _code;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3059181:  // code
          return ((GICSCode) bean).getCode();
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
   * The bean-builder for {@code GICSCode}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<GICSCode> {

    private String _code;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3059181:  // code
          return _code;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 3059181:  // code
          this._code = (String) newValue;
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
    public GICSCode build() {
      return new GICSCode(
          _code);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("GICSCode.Builder{");
      buf.append("code").append('=').append(JodaBeanUtils.toString(_code));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
