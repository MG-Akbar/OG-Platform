package com.opengamma.analytics.financial.credit.isdastandardmodel;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;
import org.threeten.bp.Period;

public class CS01FromPUFTest extends ISDABaseTest {
  private static final CDSAnalyticFactory FACTORY = new CDSAnalyticFactory();

  protected static final double NOTIONAL = 1e6;
  private static final LocalDate TRADE_DATE = LocalDate.of(2013, Month.APRIL, 10); //Today 
  private static final LocalDate STARTDATE = LocalDate.of(2013, Month.MARCH, 20);//last IMM date before TRADE_DATE;

  private static final Period[] BUCKETS = new Period[] {Period.ofMonths(6), Period.ofYears(1), Period.ofYears(2), Period.ofYears(3), Period.ofYears(4), Period.ofYears(5), Period.ofYears(6),
    Period.ofYears(7), Period.ofYears(8), Period.ofYears(9), Period.ofYears(10) };

  private static final double COUPON = 500;
  private static final double[] PUF = new double[] {0.32, 0.69, 1.32, 1.79, 2.36, 3.01, 3.7, 4.39, 5.02, 5.93, 6.85, 7.76, 8.67, 9.6, 10.53, 11.45, 12.33, 13.29, 14.26, 15.2, 16.11, 16.62, 17.12,
    17.62, 18.09, 18.55, 19, 19.44, 19.87, 20.33, 20.79, 21.24, 21.67, 22.04, 22.41, 22.77, 23.12, 23.46, 23.8, 24.14, 24.46 };
  //from Excel sheet
  private static final double[] EXPECTED_PCS01 = new double[] {19.4476717214585, 43.8661130664243, 66.8471984124203, 88.7553472912889, 110.06998773129, 130.241766300415, 149.078259562216,
    166.69903416236, 183.957182839617, 199.138069115451, 212.919225708888, 225.611666159392, 237.356489788121, 247.869659508626, 257.114141612835, 265.199358214979, 272.786968453925,
    278.794138474647, 283.547449071608, 287.3896580744, 290.717945552049, 296.390819613274, 301.375408610055, 305.626841744427, 309.714345009954, 313.29322325846, 316.312476703606, 318.886594326717,
    321.108562383404, 322.498334553417, 323.314906431477, 323.700137078414, 323.96484289246, 324.483246674001, 324.554665413312, 324.325609046883, 323.949329444989, 323.352508975527,
    322.393347729988, 321.094035337033, 319.829180765091 };

  //These come from OG code (i.e. a regression), since this methodology cannot be reproduced with the ISDA Excel plug-in 
  private static final double[][] EXPECTED_BCS01 = new double[][] {
    {19.441001704435454, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {43.859222177977664, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {66.84719841284354, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {32.47547919589533, 56.29986693759387, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.3368704161118017, 111.413108277357, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.3830327143943277, 80.695395699798, 50.943921103996374, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.423140366510478, 51.448064965584074, 99.07006404229202, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.4459503089869763, 23.731847915764437, 144.4265986980664, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.4345836386736588, -3.270414476050365, 188.68031380837868, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.4866138201563572, -3.3996997203106227, 135.00732990266718, 69.06262671438346, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.5232906312168115, -3.494426703568032, 84.41655295829409, 133.57660767102098, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.5430576934954976, -3.5506222668679754, 36.45603487409388, 194.3008485380121, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.5499857383693971, -3.5775793651993126, -9.32022819782885, 251.84342531781033, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.5515273747712666, -3.5925518767909725, -9.363138121526449, 178.65941028577527, 83.80695282969586, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.5471326477722869, -3.593997082246281, -9.370348864418121, 110.23185862010787, 161.50014205204565, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.5367776675101474, -3.5816901767238285, -9.34106701558357, 46.491478759597136, 233.26100398193827, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.5147229907863657, -3.541977265486551, -9.238218905804896, -14.522962422297558, 301.66392780967, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.4973386689987045, -3.514063027121317, -9.16780904938741, -14.408107890606647, 212.31413203237625, 95.20718423114415, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.4779264849718032, -3.481639227670419, -9.085675620024158, -14.274727466234705, 129.39170799372567, 182.64019874339454, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.4536597495728998, -3.4376423475590556, -8.97244026959454, -14.090587844323155, 52.84106084124662, 262.64270741807593, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.422541382856135, -3.3774100829664366, -8.815315493487796, -13.83418938374903, -19.819053439118495, 338.0594913376389, 0.0, 0.0, 0.0, 0.0, 0.0 },
    {-1.3660313898933296, -3.2532155691544773, -8.482326708109111, -13.284787819034527, -18.998650008816842, 237.95960224898272, 104.01207559906922, 0.0, 0.0, 0.0, 0.0 },
    {-1.3132778162450176, -3.137564924954317, -8.172361456032506, -12.773383323311238, -18.235164013585823, 145.1690411152784, 200.0702689574918, 0.0, 0.0, 0.0, 0.0 },
    {-1.2643192549566251, -3.0306670818214254, -7.88603053397674, -12.300989218338998, -17.53018786232463, 59.18307727653671, 288.6493079943264, 0.0, 0.0, 0.0, 0.0 },
    {-1.2150107696340395, -2.922559726808993, -7.596193813741436, -11.822596341359848, -16.815696328387197, -22.910571584877637, 373.08416893727656, 0.0, 0.0, 0.0, 0.0 },
    {-1.1680817119663622, -2.8198055249673004, -7.320729515092328, -11.367851346644198, -16.136494452922978, -21.945622484365046, 262.40319413292633, 111.9110111449939, 0.0, 0.0, 0.0 },
    {-1.123973720179583, -2.7234235080442204, -7.062392830992081, -10.941308799999883, -15.499422948234143, -21.040635429819822, 160.04227075674947, 214.97422743010918, 0.0, 0.0, 0.0 },
    {-1.0817837584053969, -2.631325621421654, -6.815533074161495, -10.533617045199728, -14.89041756694287, -20.175436218350384, 64.44196396040991, 310.82701278994926, 0.0, 0.0, 0.0 },
    {-1.0407836055215114, -2.541829720925648, -6.575601491226601, -10.137254826347863, -14.298177177796134, -19.333828839313007, -25.646247754085305, 400.7797328247631, 0.0, 0.0, 0.0 },
    {-1.00268355954225, -2.45936064185992, -6.354740453379248, -9.772300120169941, -13.753126175214403, -18.559998296446835, -24.589268073388347, 280.8220658950811, 118.5079363335284, 0.0, 0.0 },
    {-0.9667956833991909, -2.3819637897104506, -6.147516715931944, -9.429738954652711, -13.241481395170895, -17.83368100938265, -23.597688602078335, 170.18483022573915, 227.1368878963842, 0.0, 0.0 },
    {-0.9326626483341371, -2.3084639758363945, -5.95071005971004, -9.10425015765437, -12.755188201485357, -17.143193527996424, -22.654939870248025, 68.32846448498464, 326.54543041998886, 0.0, 0.0 },
    {-0.8985932634930638, -2.2348415187334325, -5.753412470194119, -8.777808897686423, -12.26711755888954, -16.449539824769932, -21.706581885827525, -28.308045521535654, 420.462448096931, 0.0, 0.0 },
    {-0.8649507656222077, -2.1614769874500617, -5.5565017696157515, -8.451915922635944, -11.779346567375448, -15.755233055053663, -20.754881172341257, -27.042096276130856, 294.2028735729574,
      123.07758922092127, 0.0 },
    {-0.8333128332371764, -2.0927143828397554, -5.371978176765824, -8.146391313967882, -11.322002458125091, -15.10426252432362, -19.862920557944808, -25.856396972306904, 177.91915633702527,
      235.74655901217878, 0.0 },
    {-0.8033490749570493, -2.027675222310288, -5.197427568964796, -7.857254148480042, -10.889056542812137, -14.487868002871496, -19.018224634959058, -24.733476379351636, 70.9858110631012,
      338.75957568360593, 0.0 },
    {-0.7738658317213432, -1.9636060938355016, -5.02540748759106, -7.572204572370289, -10.462037753095066, -13.879595220644969, -18.184100579216043, -23.623712565895573, -30.414118091487417,
      435.9488306168446, 0.0 },
    {-0.7456225625535051, -1.9022607592733554, -4.86066309268729, -7.299093962243042, -10.05275067314515, -13.296370876381136, -17.38405599954751, -22.5589387432279, -29.02827635362093,
      304.4333079171568, 126.58625458042417 },
    {-0.7189836491450219, -1.8445920572096597, -4.705805415727848, -7.042220294406487, -9.66768553187225, -12.747591349587806, -16.631402511430025, -21.55768197634189, -27.72588854102476,
      183.54655657770724, 242.14628064278543 },
    {-0.6938092922126327, -1.790276204421959, -4.559958790867347, -6.800135258389073, -9.304664761927706, -12.230132447083042, -15.921794364631703, -20.614043326649956, -26.49909752092117,
      72.578438520432, 347.4305457272786 },
    {-0.6689795128966836, -1.736509613381454, -4.415483418063193, -6.560265411514887, -8.944762737855827, -11.716728276378596, -15.216919931587691, -19.675255644668876, -25.27641667116054,
      -32.36002850215414, 446.4924857241758 } };

  private static final String[] MATURITY_STRINGS = new String[] {"20/06/2013", "20/09/2013", "20/12/2013", "20/03/2014", "20/06/2014", "20/09/2014", "20/12/2014", "20/03/2015", "20/06/2015",
    "20/09/2015", "20/12/2015", "20/03/2016", "20/06/2016", "20/09/2016", "20/12/2016", "20/03/2017", "20/06/2017", "20/09/2017", "20/12/2017", "20/03/2018", "20/06/2018", "20/09/2018", "20/12/2018",
    "20/03/2019", "20/06/2019", "20/09/2019", "20/12/2019", "20/03/2020", "20/06/2020", "20/09/2020", "20/12/2020", "20/03/2021", "20/06/2021", "20/09/2021", "20/12/2021", "20/03/2022", "20/06/2022",
    "20/09/2022", "20/12/2022", "20/03/2023", "20/06/2023" };
  private static final LocalDate[] MATURITIES = parseDateStrings(MATURITY_STRINGS);

  //yield curve
  private static final LocalDate SPOT_DATE = LocalDate.of(2013, Month.APRIL, 12);
  private static final String[] YIELD_CURVE_POINTS = new String[] {"1M", "2M", "3M", "6M", "9M", "1Y", "2Y", "3Y", "4Y", "5Y", "6Y", "7Y", "8Y", "9Y", "10Y", "12Y", "15Y", "20Y", "25Y", "30Y" };
  private static final String[] YIELD_CURVE_INSTRUMENTS = new String[] {"M", "M", "M", "M", "M", "M", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S" };
  private static final double[] YIELD_CURVE_RATES = new double[] {0.001993, 0.002403, 0.002781, 0.004419, 0.005782, 0.00721, 0.003795, 0.00483, 0.00658, 0.008815, 0.01127, 0.01362, 0.01573, 0.017605,
    0.019215, 0.02195, 0.02468, 0.026975, 0.0281, 0.02874 };
  private static final ISDACompliantYieldCurve YIELD_CURVE = makeYieldCurve(TRADE_DATE, SPOT_DATE, YIELD_CURVE_POINTS, YIELD_CURVE_INSTRUMENTS, YIELD_CURVE_RATES);

  private static final CDSAnalytic[] BUCKET_CDS = FACTORY.makeIMMCDS(TRADE_DATE, BUCKETS);
  private static final CDSAnalytic[] PRICE_CDS = FACTORY.makeCDS(TRADE_DATE, STARTDATE, MATURITIES);

  @Test
  public void parellelCS01Test() {
    final double notional = 1e6;
    final double coupon = COUPON * ONE_BP;
    final double scale = notional * ONE_BP;
    final int n = MATURITIES.length;

    for (int i = 0; i < n; i++) {
      final double cs01 = scale * CS01_CAL.parallelCS01FromPUF(PRICE_CDS[i], coupon, YIELD_CURVE, PUF[i] * ONE_PC, ONE_BP);
      // System.out.println(EXPECTED_PCS01[i] + "\t" + cs01);
      assertEquals(MATURITIES[i].toString(), EXPECTED_PCS01[i], cs01, 1e-14 * notional);
    }
  }

  @Test
  //(enabled = false)
  public void bucketedCS01Test() {
    final double notional = 1e6;
    final double scale = notional * ONE_BP;
    final int n = MATURITIES.length;

    final double[][] cs01 = new double[n][];
    for (int i = 0; i < n; i++) {
      cs01[i] = CS01_CAL.bucketedCS01FromPUF(PRICE_CDS[i], new PointsUpFront(COUPON * ONE_BP, PUF[i] * ONE_PC), YIELD_CURVE, BUCKET_CDS, ONE_BP);

      final int m = cs01[i].length;
      for (int j = 0; j < m; j++) {
        assertEquals(EXPECTED_BCS01[i][j], scale * cs01[i][j], 1e-15 * NOTIONAL);
      }
    }

    // print(cs01, scale);
  }

  @Test(enabled = false)
  public void bucketedCS01Test2() {

    final String[] matString = new String[] {"20/09/2013", "20/12/2013", "20/03/2014", "20/06/2014", "20/09/2014", "20/12/2014", "20/03/2015", "20/06/2015", "20/09/2015", "20/12/2015", "20/03/2016",
      "20/06/2016", "20/09/2016", "20/12/2016", "20/03/2017", "20/06/2017", "20/09/2017", "20/12/2017", "20/03/2018", "20/06/2018", "20/09/2018", "20/12/2018", "20/03/2019", "20/06/2019",
      "20/09/2019", "20/12/2019", "20/03/2020", "20/06/2020", "20/09/2020", "20/12/2020", "20/03/2021", "20/06/2021", "20/09/2021", "20/12/2021", "20/03/2022", "20/06/2022", "20/09/2022",
      "20/12/2022", "20/03/2023", "20/06/2023", "20/09/2023" };
    final LocalDate[] mats = parseDateStrings(matString);
    final LocalDate tradeDate = LocalDate.of(2013, Month.SEPTEMBER, 14); //Today 
    final LocalDate accStart = LocalDate.of(2013, Month.JUNE, 20);
    final double notional = 1e6;
    final double scale = notional * ONE_BP;
    final double[] puf = new double[] {3.53, 6.25, 7.28, 12.28, 15, 18.62, 20.32, 22.52, 26.55, 28.67, 27.26, 30.26, 29.84, 31.56, 30.94, 31.94, 33.6, 33.94, 33.41, 33.65, 35.83, 34.52, 36.58, 34.61,
      35.06, 36.36, 37.47, 36.97, 37.91, 37.33, 36.88, 37.03, 37.58, 38.96, 38.78, 39.71, 38.87, 38.8, 38.68, 38.17, 38.3 };
    final double coupon = 0.05;
    final CDSAnalyticFactory factory = new CDSAnalyticFactory(RECOVERY_RATE);

    final CDSAnalytic[] cds = factory.makeCDS(tradeDate, accStart, mats);
    final CDSAnalytic[] buckets = factory.makeIMMCDS(tradeDate, BUCKETS);
    final int n = cds.length;
    final double[][] cs01 = new double[n][];
    for (int i = 0; i < n; i++) {
      cs01[i] = CS01_CAL.bucketedCS01FromPUF(cds[i], new PointsUpFront(coupon, puf[i] * ONE_PC), YIELD_CURVE, buckets, ONE_BP);
    }
    print(cs01, scale);
  }

  private void print(final double[][] data, final double scale) {
    final int rows = data.length;
    System.out.print("{");
    for (int i = 0; i < rows; i++) {
      System.out.print("{");
      final int cols = data[i].length;
      for (int j = 0; j < cols - 1; j++) {
        System.out.print(scale * data[i][j] + ", ");
      }
      System.out.print(scale * data[i][cols - 1] + "}");
      if (i < rows - 1) {
        System.out.print(",\n");
      } else {
        System.out.print("}\n");
      }
    }

  }
}
