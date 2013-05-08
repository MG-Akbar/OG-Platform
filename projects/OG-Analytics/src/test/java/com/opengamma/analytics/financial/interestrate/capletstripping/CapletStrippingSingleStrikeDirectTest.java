/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.capletstripping;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 
 */
public class CapletStrippingSingleStrikeDirectTest extends CapletStrippingAbsoluteStrikeTest {

  @Override
  public CapletStrippingAbsoluteStrike getStripper(List<CapFloor> caps) {
    return new CapletStrippingSingleStrikeDirect(caps, getYieldCurves());
  }
  
  @Test
  public void test() {
    final double tol = 1e-4; //allow a 1bps error on cap vols 
    final boolean print = false;
    testVolStripping(tol,print);
  }
  
  @Test
  public void timingTest() {
    timingTest(50, 50);
  }


}
