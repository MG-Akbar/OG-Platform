/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.model.option.pricing.analytic;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.time.Instant;
import javax.time.InstantProvider;

import org.junit.Test;

import com.opengamma.financial.greeks.Greek;
import com.opengamma.financial.greeks.Price;
import com.opengamma.financial.model.interestrate.curve.ConstantInterestRateDiscountCurve;
import com.opengamma.financial.model.interestrate.curve.DiscountCurve;
import com.opengamma.financial.model.option.definition.AsymmetricPowerOptionDefinition;
import com.opengamma.financial.model.option.definition.CappedPowerOptionDefinition;
import com.opengamma.financial.model.option.definition.StandardOptionDataBundle;
import com.opengamma.financial.model.volatility.surface.ConstantVolatilitySurface;
import com.opengamma.financial.model.volatility.surface.VolatilitySurface;
import com.opengamma.util.time.DateUtil;
import com.opengamma.util.time.Expiry;

/**
 * 
 * @author emcleod
 */
public class CappedPowerOptionModelTest {
  private static final double B = 0.02;
  private static final double SPOT = 10;
  private static final double STRIKE = 100;
  private static final InstantProvider DATE = Instant.millisInstant(1000);
  private static final Expiry EXPIRY = new Expiry(DateUtil.getDateOffsetWithYearFraction(DATE, 0.5));
  private static final DiscountCurve CURVE = new ConstantInterestRateDiscountCurve(0.08);
  private static final VolatilitySurface SURFACE = new ConstantVolatilitySurface(0.1);
  private static final StandardOptionDataBundle BUNDLE = new StandardOptionDataBundle(CURVE, B, SURFACE, SPOT, DATE);
  private static final AnalyticOptionModel<CappedPowerOptionDefinition, StandardOptionDataBundle> CAPPED_MODEL = new CappedPowerOptionModel();
  private static final AnalyticOptionModel<AsymmetricPowerOptionDefinition, StandardOptionDataBundle> UNCAPPED_MODEL = new AsymmetricPowerOptionModel();
  private static final Greek PRICE = new Price();
  private static final List<Greek> REQUIRED_GREEKS = Arrays.asList(new Greek[] { PRICE });
  private static final double HIGH_CAP = 100;
  private static final double EPS = 1e-4;

  @Test
  public void test() {
    // assertEquals(getCappedPrice(1.9, HIGH_CAP, true), getUncappedPrice(1.9,
    // true), EPS);
    // assertEquals(getCappedPrice(1.95, HIGH_CAP, true), getUncappedPrice(1.95,
    // true), EPS);
    // assertEquals(getCappedPrice(2., HIGH_CAP, true), getUncappedPrice(2.,
    // true), EPS);
    // assertEquals(getCappedPrice(2.05, HIGH_CAP, true), getUncappedPrice(2.05,
    // true), EPS);
    // assertEquals(getCappedPrice(2.1, HIGH_CAP, true), getUncappedPrice(2.1,
    // true), EPS);
    assertEquals(getCappedPrice(1.9, HIGH_CAP, false), getUncappedPrice(1.9, false), EPS);
    assertEquals(getCappedPrice(1.95, HIGH_CAP, false), getUncappedPrice(1.95, false), EPS);
    assertEquals(getCappedPrice(2., HIGH_CAP, false), getUncappedPrice(2., false), EPS);
    assertEquals(getCappedPrice(2.05, HIGH_CAP, false), getUncappedPrice(2.05, false), EPS);
    assertEquals(getCappedPrice(2.1, HIGH_CAP, false), getUncappedPrice(2.1, false), EPS);
  }

  private double getCappedPrice(double power, double cap, boolean isCall) {
    return CAPPED_MODEL.getGreeks(getDefinition(power, cap, isCall), BUNDLE, REQUIRED_GREEKS).get(PRICE).values().iterator().next();
  }

  private double getUncappedPrice(double power, boolean isCall) {
    return UNCAPPED_MODEL.getGreeks(getDefinition(power, isCall), BUNDLE, REQUIRED_GREEKS).get(PRICE).values().iterator().next();
  }

  private CappedPowerOptionDefinition getDefinition(double power, double cap, boolean isCall) {
    return new CappedPowerOptionDefinition(STRIKE, EXPIRY, power, cap, isCall);
  }

  private AsymmetricPowerOptionDefinition getDefinition(double power, boolean isCall) {
    return new AsymmetricPowerOptionDefinition(STRIKE, EXPIRY, power, isCall);
  }
}
