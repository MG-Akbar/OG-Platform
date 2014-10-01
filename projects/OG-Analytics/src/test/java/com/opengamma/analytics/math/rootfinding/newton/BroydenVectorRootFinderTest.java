/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.rootfinding.newton;

import org.testng.annotations.Test;

import com.opengamma.util.test.TestGroup;

/**
 * Test.
 */
@Test(groups = TestGroup.UNIT)
public class BroydenVectorRootFinderTest extends VectorRootFinderTest {
  private static final NewtonVectorRootFinder DEFAULT = new BroydenVectorRootFinder(TOLERANCE, TOLERANCE, MAXSTEPS);
  private static final NewtonVectorRootFinder DEFAULT_JACOBIAN_2D = new BroydenVectorRootFinder(TOLERANCE, TOLERANCE, MAXSTEPS);
  private static final NewtonVectorRootFinder DEFAULT_JACOBIAN_3D = new BroydenVectorRootFinder(TOLERANCE, TOLERANCE, MAXSTEPS);

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSingular1() {
    assertFunction2D(DEFAULT, EPS);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSingular2() {
    assertFunction2D(DEFAULT_JACOBIAN_2D, EPS);
  }

  @Test
  public void test() {
    assertLinear(DEFAULT, EPS);
    assertFunction2D(DEFAULT_JACOBIAN_2D, EPS);
    assertFunction2D(DEFAULT, EPS);
    assertFunction3D(DEFAULT, EPS);
    assertFunction3D(DEFAULT_JACOBIAN_3D, EPS);
    assertYieldCurveBootstrap(DEFAULT, EPS);
  }
}
