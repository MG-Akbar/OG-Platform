/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.annuity;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;
import org.threeten.bp.Period;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.instrument.index.GeneratorSwapFixedIbor;
import com.opengamma.analytics.financial.instrument.index.GeneratorSwapFixedIborMaster;
import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.analytics.financial.instrument.index.IndexSwap;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.businessday.BusinessDayConventions;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCounts;
import com.opengamma.util.money.Currency;
import com.opengamma.util.test.TestGroup;
import com.opengamma.util.time.DateUtils;

/**
 * Tests related to the construction of CMS cap/floor.
 */
@Test(groups = TestGroup.UNIT)
public class AnnuityCapFloorCMSDefinitionTest {
  private static final Currency CUR = Currency.EUR;
  // Ibor index
  private static final BusinessDayConvention BUSINESS_DAY = BusinessDayConventions.MODIFIED_FOLLOWING;
  private static final boolean IS_EOM = true;
  private static final int IBOR_SETTLEMENT_DAYS = 2;
  //CMS 10Y
  private static final Period CMS_TENOR = Period.ofYears(10);
  private static final GeneratorSwapFixedIbor EUR1YEURIBOR3M = GeneratorSwapFixedIborMaster.getInstance().getGenerator("EUR1YEURIBOR3M");
  private static final IndexSwap CMS_INDEX = new IndexSwap("Index",EUR1YEURIBOR3M, CMS_TENOR);
  // Annuity
  private static final Calendar CALENDAR = EUR1YEURIBOR3M.getFixedLegGenerator().getCalendar();
  private static final ZonedDateTime START_DATE = DateUtils.getUTCDate(2011, 3, 17);
  private static final Period ANNUITY_TENOR = Period.ofYears(5);
  private static final ZonedDateTime MATURITY_DATE = START_DATE.plus(ANNUITY_TENOR);
  private static final double NOTIONAL = 100000000; //100m
  private static final Period LEG_PAYMENT_PERIOD = Period.ofMonths(12);
  private static final DayCount LEG_DAY_COUNT = DayCounts.ACT_365;
  private static final boolean IS_PAYER = true;
  private static final double STRIKE = 0.04;
  private static final boolean IS_CAP = true;
  private static final AnnuityCapFloorCMSDefinition CMS_LEG = AnnuityCapFloorCMSDefinition.from(START_DATE, MATURITY_DATE, NOTIONAL, CMS_INDEX, LEG_PAYMENT_PERIOD, LEG_DAY_COUNT, IS_PAYER, STRIKE,
      IS_CAP, EUR1YEURIBOR3M.getFixedLegGenerator().getCalendar());

  @Test
  public void dates() {
    final IborIndex fakeIborIndex12 = new IborIndex(CUR, LEG_PAYMENT_PERIOD, IBOR_SETTLEMENT_DAYS, LEG_DAY_COUNT, BUSINESS_DAY, IS_EOM, "Ibor");
    final AnnuityCouponIborDefinition iborLeg = AnnuityCouponIborDefinition.from(START_DATE, MATURITY_DATE, NOTIONAL, fakeIborIndex12, IS_PAYER, CALENDAR);
    for (int loopcpn = 0; loopcpn < iborLeg.getNumberOfPayments(); loopcpn++) {
      assertEquals(iborLeg.getNthPayment(loopcpn).getAccrualStartDate(), CMS_LEG.getNthPayment(loopcpn).getAccrualStartDate());
      assertEquals(iborLeg.getNthPayment(loopcpn).getAccrualEndDate(), CMS_LEG.getNthPayment(loopcpn).getAccrualEndDate());
      assertEquals(iborLeg.getNthPayment(loopcpn).getPaymentYearFraction(), CMS_LEG.getNthPayment(loopcpn).getPaymentYearFraction());
      assertEquals(iborLeg.getNthPayment(loopcpn).getPaymentDate(), CMS_LEG.getNthPayment(loopcpn).getPaymentDate());
      assertEquals(iborLeg.getNthPayment(loopcpn).getFixingDate(), CMS_LEG.getNthPayment(loopcpn).getFixingDate());
    }
  }

  @Test
  public void common() {
    for (int loopcpn = 0; loopcpn < CMS_LEG.getNumberOfPayments(); loopcpn++) {
      assertEquals(CMS_INDEX, CMS_LEG.getNthPayment(loopcpn).getCMSIndex());
      assertEquals(NOTIONAL * (IS_PAYER ? -1.0 : 1.0), CMS_LEG.getNthPayment(loopcpn).getNotional());
      assertEquals(STRIKE, CMS_LEG.getNthPayment(loopcpn).getStrike());
      assertEquals(IS_CAP, CMS_LEG.getNthPayment(loopcpn).isCap());
    }
    final AnnuityCapFloorCMSDefinition cmsCapReceiver = AnnuityCapFloorCMSDefinition.from(START_DATE, MATURITY_DATE, NOTIONAL, CMS_INDEX, LEG_PAYMENT_PERIOD, LEG_DAY_COUNT, !IS_PAYER, STRIKE, IS_CAP,
        CALENDAR);
    for (int loopcpn = 0; loopcpn < CMS_LEG.getNumberOfPayments(); loopcpn++) {
      assertEquals(CMS_INDEX, cmsCapReceiver.getNthPayment(loopcpn).getCMSIndex());
      assertEquals(-NOTIONAL * (IS_PAYER ? -1.0 : 1.0), cmsCapReceiver.getNthPayment(loopcpn).getNotional());
    }
  }

}
