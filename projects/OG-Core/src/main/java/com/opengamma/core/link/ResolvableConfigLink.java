/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.link;


import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
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
 * Represents a link to a Config object using a name
 * that can be resolved on demand.
 *
 * @param <T> type of the config
 */
@BeanDefinition
public class ResolvableConfigLink<T> extends ConfigLink<T> implements ImmutableBean {

  /**
   * The identification data for the object being linked to.
   */
  @PropertyDefinition(validate = "notNull")
  private final LinkIdentifier<String, T> _identifier;

  /**
   * The resolver used to resolve the link on demand, not null.
   */
  // note that the resolver does not form part of the serialized form
  // of the bean
  private final LinkResolver<String, T> _resolver;

  /**
   * Creates a resolved link.
   *
   * @param identifier the identifier for the linked object
   * @param type the type of the linked object
   * @param linkResolver the resolver used to resolve the link when requested
   */
  /* package */ ResolvableConfigLink(String identifier,
                                     Class<T> type,
                                     LinkResolver<String, T> linkResolver) {
    this(LinkIdentifier.of(identifier, type), linkResolver);
  }

  @ImmutableConstructor
  private ResolvableConfigLink(LinkIdentifier<String, T> identifier) {
    this(identifier, new ServiceContextConfigLinkResolver<T>());
  }

  private ResolvableConfigLink(LinkIdentifier<String, T> identifier, LinkResolver<String, T> linkResolver) {
    _identifier = identifier;
    _resolver = ArgumentChecker.notNull(linkResolver, "linkResolver");
  }

  @Override
  public T resolve() {
    return _resolver.resolve(_identifier);
  }

  @Override
  public Class<T> getTargetType() {
    return _identifier.getType();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ResolvableConfigLink}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("rawtypes")
  public static ResolvableConfigLink.Meta meta() {
    return ResolvableConfigLink.Meta.INSTANCE;
  }

  /**
   * The meta-bean for {@code ResolvableConfigLink}.
   * @param <R>  the bean's generic type
   * @param cls  the bean's generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R> ResolvableConfigLink.Meta<R> metaResolvableConfigLink(Class<R> cls) {
    return ResolvableConfigLink.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ResolvableConfigLink.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @param <T>  the type
   * @return the builder, not null
   */
  public static <T> ResolvableConfigLink.Builder<T> builder() {
    return new ResolvableConfigLink.Builder<T>();
  }

  @SuppressWarnings("unchecked")
  @Override
  public ResolvableConfigLink.Meta<T> metaBean() {
    return ResolvableConfigLink.Meta.INSTANCE;
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
   * Gets the identification data for the object being linked to.
   * @return the value of the property, not null
   */
  public LinkIdentifier<String, T> getIdentifier() {
    return _identifier;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder<T> toBuilder() {
    return new Builder<T>(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ResolvableConfigLink<?> other = (ResolvableConfigLink<?>) obj;
      return JodaBeanUtils.equal(getIdentifier(), other.getIdentifier());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getIdentifier());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("ResolvableConfigLink{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("identifier").append('=').append(JodaBeanUtils.toString(getIdentifier())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ResolvableConfigLink}.
   * @param <T>  the type
   */
  public static class Meta<T> extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code identifier} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<LinkIdentifier<String, T>> _identifier = DirectMetaProperty.ofImmutable(
        this, "identifier", ResolvableConfigLink.class, (Class) LinkIdentifier.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "identifier");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1618432855:  // identifier
          return _identifier;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public ResolvableConfigLink.Builder<T> builder() {
      return new ResolvableConfigLink.Builder<T>();
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends ResolvableConfigLink<T>> beanType() {
      return (Class) ResolvableConfigLink.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code identifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<LinkIdentifier<String, T>> identifier() {
      return _identifier;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1618432855:  // identifier
          return ((ResolvableConfigLink<?>) bean).getIdentifier();
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
   * The bean-builder for {@code ResolvableConfigLink}.
   * @param <T>  the type
   */
  public static class Builder<T> extends DirectFieldsBeanBuilder<ResolvableConfigLink<T>> {

    private LinkIdentifier<String, T> _identifier;

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    protected Builder(ResolvableConfigLink<T> beanToCopy) {
      this._identifier = beanToCopy.getIdentifier();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1618432855:  // identifier
          return _identifier;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder<T> set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -1618432855:  // identifier
          this._identifier = (LinkIdentifier<String, T>) newValue;
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
    public ResolvableConfigLink<T> build() {
      return new ResolvableConfigLink<T>(
          _identifier);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code identifier} property in the builder.
     * @param identifier  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder<T> identifier(LinkIdentifier<String, T> identifier) {
      JodaBeanUtils.notNull(identifier, "identifier");
      this._identifier = identifier;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("ResolvableConfigLink.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    protected void toString(StringBuilder buf) {
      buf.append("identifier").append('=').append(JodaBeanUtils.toString(_identifier)).append(',').append(' ');
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
