/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.statistics.leastsquare;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.testng.annotations.Test;

import cern.jet.random.engine.MersenneTwister;
import cern.jet.random.engine.MersenneTwister64;
import cern.jet.random.engine.RandomEngine;

import com.opengamma.analytics.math.function.Function1D;
import com.opengamma.analytics.math.interpolation.BasisFunctionAggregation;
import com.opengamma.analytics.math.interpolation.BasisFunctionGenerator;
import com.opengamma.analytics.math.interpolation.CombinedInterpolatorExtrapolator;
import com.opengamma.analytics.math.interpolation.DoubleQuadraticInterpolator1D;
import com.opengamma.analytics.math.interpolation.FlatExtrapolator1D;
import com.opengamma.analytics.math.interpolation.Interpolator1D;
import com.opengamma.analytics.math.interpolation.PSplineFitter;
import com.opengamma.analytics.math.interpolation.data.Interpolator1DDataBundle;
import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.analytics.math.statistics.distribution.NormalDistribution;
import com.opengamma.util.test.TestGroup;

/**
 * Test.
 */
@Test(groups = TestGroup.UNIT)
public class GeneralizedLeastSquareTest {
  private static boolean PRINT = false;

  protected static final RandomEngine RANDOM = new MersenneTwister64(MersenneTwister.DEFAULT_SEED);
  private static final NormalDistribution NORMAL = new NormalDistribution(0, 1.0, RANDOM);
  private static final double[] WEIGHTS = new double[] {1.0, -0.5, 2.0, 0.23, 1.45 };
  // private static final double[] WEIGHTS = new double[] {1.0, 1.0, 0.0, 0.0, 0};
  private static final Double[] X;
  private static final double[] Y;
  private static final double[] SIGMA;
  private static final List<DoubleMatrix1D> X_TRIG;
  private static final List<Double> Y_TRIG;
  private static final List<Double> SIGMA_TRIG;
  private static final List<Double> SIGMA_COS_EXP;
  private static final List<double[]> X_COS_EXP;
  private static final List<Double> Y_COS_EXP;
  private static final List<Function1D<Double, Double>> SIN_FUNCTIONS;
  private static final Function1D<Double, Double> TEST_FUNCTION;
  private static final List<Function1D<Double, Double>> BASIS_FUNCTIONS;
  private static final List<Function1D<double[], Double>> BASIS_FUNCTIONS_2D;
  private static Function1D<double[], Double> COS_EXP_FUNCTION;

  private static final List<Function1D<DoubleMatrix1D, Double>> VECTOR_TRIG_FUNCTIONS;
  private static final Function1D<DoubleMatrix1D, Double> VECTOR_TEST_FUNCTION;

  static {
    SIN_FUNCTIONS = new ArrayList<>();
    for (int i = 0; i < WEIGHTS.length; i++) {
      final int k = i;
      final Function1D<Double, Double> func = new Function1D<Double, Double>() {

        @Override
        public Double evaluate(final Double x) {
          return Math.sin((2 * k + 1) * x);
        }
      };
      SIN_FUNCTIONS.add(func);
    }
    TEST_FUNCTION = new BasisFunctionAggregation<>(SIN_FUNCTIONS, WEIGHTS);

    VECTOR_TRIG_FUNCTIONS = new ArrayList<>();
    for (int i = 0; i < WEIGHTS.length; i++) {
      final int k = i;
      final Function1D<DoubleMatrix1D, Double> func = new Function1D<DoubleMatrix1D, Double>() {
        @Override
        public Double evaluate(final DoubleMatrix1D x) {
          Validate.isTrue(x.getNumberOfElements() == 2);
          return Math.sin((2 * k + 1) * x.getEntry(0)) * Math.cos((2 * k + 1) * x.getEntry(1));
        }
      };
      VECTOR_TRIG_FUNCTIONS.add(func);
    }
    VECTOR_TEST_FUNCTION = new BasisFunctionAggregation<>(VECTOR_TRIG_FUNCTIONS, WEIGHTS);

    COS_EXP_FUNCTION = new Function1D<double[], Double>() {

      @Override
      public Double evaluate(final double[] x) {
        return Math.sin(Math.PI * x[0] / 10.0) * Math.exp(-x[1] / 5.);
      }
    };

    final int n = 10;

    X = new Double[n];
    Y = new double[n];
    SIGMA = new double[n];
    X_TRIG = new ArrayList<>();
    Y_TRIG = new ArrayList<>();
    SIGMA_TRIG = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      X[i] = i / 5.0;
      Y[i] = TEST_FUNCTION.evaluate(X[i]);
      final double[] temp = new double[2];
      temp[0] = 2.0 * RANDOM.nextDouble();
      temp[1] = 2.0 * RANDOM.nextDouble();
      X_TRIG.add(new DoubleMatrix1D(temp));
      Y_TRIG.add(VECTOR_TEST_FUNCTION.evaluate(X_TRIG.get(i)));
      SIGMA[i] = 0.01;
      SIGMA_TRIG.add(0.01);
    }

    SIGMA_COS_EXP = new ArrayList<>();
    X_COS_EXP = new ArrayList<>();
    Y_COS_EXP = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      final double[] temp = new double[2];
      temp[0] = 10.0 * RANDOM.nextDouble();
      temp[1] = 10.0 * RANDOM.nextDouble();
      X_COS_EXP.add(temp);
      Y_COS_EXP.add(COS_EXP_FUNCTION.evaluate(X_COS_EXP.get(i)));
      SIGMA_COS_EXP.add(0.01);
    }

    final BasisFunctionGenerator generator = new BasisFunctionGenerator();
    BASIS_FUNCTIONS = generator.generateSet(0.0, 2.0, 20, 3);
    BASIS_FUNCTIONS_2D = generator.generateSet(new double[] {0.0, 0.0 }, new double[] {10.0, 10.0 }, new int[] {10, 10 }, new int[] {3, 3 });

    // for (int i = 0; i < 101; i++) {
    // double xx = 0 + 2.0 * i / 100.0;
    // System.out.println(xx + "\t" + TEST_FUNCTION.evaluate(xx));
    // }

    // double[] x = new double[2];
    // for (int i = 0; i < 101; i++) {
    // x[0] = 0 + i * 10.0 / 100.0;
    // System.out.print("\t" + x[0]);
    // }
    // System.out.print("\n");
    // for (int i = 0; i < 101; i++) {
    // x[0] = -0. + i * 10 / 100.0;
    // System.out.print(x[0]);
    // for (int j = 0; j < 101; j++) {
    // x[1] = -0.0 + j * 10.0 / 100.0;
    // double y = COS_EXP_FUNCTION.evaluate(x);
    // System.out.print("\t" + y);
    // }
    // System.out.print("\n");
    // }

  }

  @Test
  public void printTest() {
    if (PRINT) {
      System.out.println("GeneralizedLeastSquareTest: true PRINT to false");
    }
  }

  @Test
  public void testPerfectFit() {
    final GeneralizedLeastSquare gls = new GeneralizedLeastSquare();
    final LeastSquareResults results = gls.solve(X, Y, SIGMA, SIN_FUNCTIONS);
    assertEquals(0.0, results.getChiSq(), 1e-8);
    final DoubleMatrix1D w = results.getFitParameters();
    for (int i = 0; i < WEIGHTS.length; i++) {
      assertEquals(WEIGHTS[i], w.getEntry(i), 1e-8);
    }
  }

  @Test
  public void testPerfectFitVector() {
    final GeneralizedLeastSquare gls = new GeneralizedLeastSquare();
    final LeastSquareResults results = gls.solve(X_TRIG, Y_TRIG, SIGMA_TRIG, VECTOR_TRIG_FUNCTIONS);
    assertEquals(0.0, results.getChiSq(), 1e-8);
    final DoubleMatrix1D w = results.getFitParameters();
    for (int i = 0; i < WEIGHTS.length; i++) {
      assertEquals(WEIGHTS[i], w.getEntry(i), 1e-8);
    }
  }

  @Test
  public void testFit() {
    final GeneralizedLeastSquare gls = new GeneralizedLeastSquare();
    final double[] y = new double[Y.length];
    for (int i = 0; i < Y.length; i++) {
      y[i] = Y[i] + SIGMA[i] * NORMAL.nextRandom();
    }

    final LeastSquareResults results = gls.solve(X, y, SIGMA, SIN_FUNCTIONS);
    assertTrue(results.getChiSq() < 3 * Y.length);

  }

  @Test
  public void testBSplineFit() {
    final GeneralizedLeastSquare gls = new GeneralizedLeastSquare();

    final LeastSquareResults results = gls.solve(X, Y, SIGMA, BASIS_FUNCTIONS);
    final Function1D<Double, Double> spline = new BasisFunctionAggregation<>(BASIS_FUNCTIONS, results.getFitParameters().getData());
    assertEquals(0.0, results.getChiSq(), 1e-12);
    assertEquals(-0.023605293, spline.evaluate(0.5), 1e-8);

    if (PRINT) {
      System.out.println("Chi^2:\t" + results.getChiSq());
      System.out.println("weights:\t" + results.getFitParameters());

      for (int i = 0; i < 101; i++) {
        final double x = 0 + i * 2.0 / 100.0;
        System.out.println(x + "\t" + spline.evaluate(x));
      }
      for (int i = 0; i < X.length; i++) {
        System.out.println(X[i] + "\t" + Y[i]);
      }
    }
  }

  @Test
  public void testBSplineFit2D() {
    final GeneralizedLeastSquare gls = new GeneralizedLeastSquare();

    final LeastSquareResults results = gls.solve(X_COS_EXP, Y_COS_EXP, SIGMA_COS_EXP, BASIS_FUNCTIONS_2D);
    final Function1D<double[], Double> spline = new BasisFunctionAggregation<>(BASIS_FUNCTIONS_2D, results.getFitParameters().getData());
    assertEquals(0.0, results.getChiSq(), 1e-16);
    assertEquals(0.05161579, spline.evaluate(new double[] {4, 3 }), 1e-8);

    /*
     * Print out function for debugging
     */
    if (PRINT) {
      System.out.println("Chi^2:\t" + results.getChiSq());
      System.out.println("weights:\t" + results.getFitParameters());

      final double[] x = new double[2];

      for (int i = 0; i < 101; i++) {
        x[0] = 0 + i * 10.0 / 100.0;
        System.out.print("\t" + x[0]);
      }
      System.out.print("\n");
      for (int i = 0; i < 101; i++) {
        x[0] = -0. + i * 10 / 100.0;
        System.out.print(x[0]);
        for (int j = 0; j < 101; j++) {
          x[1] = -0.0 + j * 10.0 / 100.0;
          final double y = spline.evaluate(x);
          System.out.print("\t" + y);
        }
        System.out.print("\n");
      }
    }
  }

  @Test
  public void testPSplineFit() {
    final GeneralizedLeastSquare gls = new GeneralizedLeastSquare();

    final GeneralizedLeastSquareResults<Double> results = gls.solve(X, Y, SIGMA, BASIS_FUNCTIONS, 1000.0, 2);
    final Function1D<Double, Double> spline = results.getFunction();
    assertEquals(2225.7, results.getChiSq(), 1e-1);
    assertEquals(-0.758963811327287, spline.evaluate(1.1), 1e-8);

    /*
     * Print out function for debugging
     */
    if (PRINT) {
      System.out.println("Chi^2:\t" + results.getChiSq());
      System.out.println("weights:\t" + results.getFitParameters());

      for (int i = 0; i < 101; i++) {
        final double x = 0 + i * 2.0 / 100.0;
        System.out.println(x + "\t" + spline.evaluate(x));
      }
      for (int i = 0; i < X.length; i++) {
        System.out.println(X[i] + "\t" + Y[i]);
      }
    }
  }

  @Test
  public void testPSplineFit2() {
    final BasisFunctionGenerator generator = new BasisFunctionGenerator();
    List<Function1D<Double, Double>> basisFuncs = generator.generateSet(0, 12, 100, 3);
    List<Function1D<Double, Double>> basisFuncsLog = generator.generateSet(-5, 3, 100, 3);

    final GeneralizedLeastSquare gls = new GeneralizedLeastSquare();

    final Interpolator1D interpolator = new CombinedInterpolatorExtrapolator(new DoubleQuadraticInterpolator1D(), new FlatExtrapolator1D());

    //  final double[] xData = new double[] {0.4, 0.9, 1.0, 1.8, 2.8, 5 };
    //  final double[] yData = new double[] {0.8, 4., 4.1, 5.6, 7., 8.1 };

    final double[] xData = new double[] {7. / 365, 14 / 365., 21 / 365., 1 / 12., 3 / 12., 0.5, 0.75, 1, 5, 10 };
    final double[] yData = new double[] {0.972452371,
      0.749039802,
      0.759792085,
      0.714206462,
      0.604446956,
      0.517955313,
      0.474807307,
      0.443532132,
      0.2404755,
      0.197128583,

    };

    final int n = xData.length;
    final double[] lnX = new double[n];
    final double[] yData2 = new double[n];
    for (int i = 0; i < n; i++) {
      lnX[i] = Math.log(xData[i]);
      yData2[i] = yData[i] * yData[i] * xData[i];
    }

    Interpolator1DDataBundle db = interpolator.getDataBundle(xData, yData);
    Interpolator1DDataBundle dbLog = interpolator.getDataBundle(lnX, yData);
    Interpolator1DDataBundle dbVar = interpolator.getDataBundle(xData, yData2);
    Interpolator1DDataBundle dbVarLog = interpolator.getDataBundle(lnX, yData2);

    final double[] sigma = new double[n];
    Arrays.fill(sigma, 0.01);
    final GeneralizedLeastSquareResults<Double> results = gls.solve(xData, yData, sigma, basisFuncs, 1000.0, 2);
    final Function1D<Double, Double> spline = results.getFunction();
    final GeneralizedLeastSquareResults<Double> resultsLog = gls.solve(lnX, yData, sigma, basisFuncsLog, 1000.0, 2);
    final Function1D<Double, Double> splineLog = resultsLog.getFunction();
    final GeneralizedLeastSquareResults<Double> resultsVar = gls.solve(xData, yData2, sigma, basisFuncs, 1000.0, 2);
    final Function1D<Double, Double> splineVar = resultsVar.getFunction();
    final GeneralizedLeastSquareResults<Double> resultsVarLog = gls.solve(lnX, yData2, sigma, basisFuncsLog, 1000.0, 2);
    final Function1D<Double, Double> splineVarLog = resultsVarLog.getFunction();

    if (PRINT) {
      System.out.println("Chi^2:\t" + results.getChiSq());
      System.out.println("weights:\t" + results.getFitParameters());

      for (int i = 0; i < 101; i++) {
        final double logX = -5 + 8 * i / 100.;
        final double x = Math.exp(logX);
        System.out.println(x + "\t" + +logX + "\t" + spline.evaluate(x) + "\t" + interpolator.interpolate(db, x) + "\t"
            + splineLog.evaluate(logX) + "\t" + interpolator.interpolate(dbLog, logX) + "\t" + splineVar.evaluate(x) + "\t"
            + interpolator.interpolate(dbVar, x) + "\t" + splineVarLog.evaluate(logX) + "\t" + interpolator.interpolate(dbVarLog, logX));
      }
      for (int i = 0; i < n; i++) {
        System.out.println(lnX[i] + "\t" + yData[i]);
      }
    }

  }

  @Test
  public void testPSplineFit2D() {

    final PSplineFitter psf = new PSplineFitter();
    final GeneralizedLeastSquareResults<double[]> results = psf.solve(X_COS_EXP, Y_COS_EXP, SIGMA_COS_EXP, new double[] {0.0, 0.0 }, new double[] {10.0, 10.0 }, new int[] {10, 10 },
        new int[] {3, 3 },
        new double[] {0.001, 0.001 }, new int[] {3, 3 });

    assertEquals(0.0, results.getChiSq(), 1e-9);
    final Function1D<double[], Double> spline = results.getFunction();
    assertEquals(0.4635978895963084, spline.evaluate(new double[] {4, 3 }), 1e-8);

    /*
     * Print out function for debugging
     */
    if (PRINT) {
      System.out.println("Chi^2:\t" + results.getChiSq());
      System.out.println("weights:\t" + results.getFitParameters());

      final double[] x = new double[2];

      for (int i = 0; i < 101; i++) {
        x[0] = 0 + i * 10.0 / 100.0;
        System.out.print("\t" + x[0]);
      }
      System.out.print("\n");
      for (int i = 0; i < 101; i++) {
        x[0] = -0. + i * 10 / 100.0;
        System.out.print(x[0]);
        for (int j = 0; j < 101; j++) {
          x[1] = -0.0 + j * 10.0 / 100.0;
          final double y = spline.evaluate(x);
          System.out.print("\t" + y);
        }
        System.out.print("\n");
      }
    }
  }
}
