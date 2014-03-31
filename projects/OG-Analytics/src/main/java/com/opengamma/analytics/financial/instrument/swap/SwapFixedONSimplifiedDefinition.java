/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.swap;

import org.apache.commons.lang.Validate;
import org.threeten.bp.Period;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.instrument.annuity.AnnuityCouponFixedDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityCouponONSimplifiedDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinitionBuilder;
import com.opengamma.analytics.financial.instrument.index.GeneratorLegFixed;
import com.opengamma.analytics.financial.instrument.index.GeneratorLegONCompounding;
import com.opengamma.analytics.financial.instrument.index.GeneratorSwapFixedONCompounding;
import com.opengamma.analytics.financial.instrument.index.IndexON;
import com.opengamma.analytics.financial.interestrate.annuity.derivative.Annuity;
import com.opengamma.analytics.financial.interestrate.payments.derivative.Coupon;
import com.opengamma.analytics.financial.interestrate.payments.derivative.CouponFixed;
import com.opengamma.analytics.financial.interestrate.swap.derivative.SwapFixedCoupon;
import com.opengamma.financial.convention.StubType;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;

/**
 * Class describing a fixed for OIS swap. Both legs are in the same currency.
 * The payment dates on the fixed leg a slightly different from the FixedIbor swap due to the lag in payment at the end of each coupon.
 */
public class SwapFixedONSimplifiedDefinition extends SwapDefinition {

  /**
   * Constructor of the fixed-OIS swap from its two legs.
   * @param fixedLeg The fixed leg.
   * @param oisLeg The OIS leg.
   */
  public SwapFixedONSimplifiedDefinition(final AnnuityCouponFixedDefinition fixedLeg, final AnnuityCouponONSimplifiedDefinition oisLeg) {
    super(fixedLeg, oisLeg);
    Validate.isTrue(fixedLeg.getCurrency() == oisLeg.getCurrency(), "Legs should have the same currency");
  }

  /**
   * Builder of OIS swap from financial description.
   * @param settlementDate The annuity settlement or first fixing date.
   * @param tenorAnnuity The total tenor of the annuity.
   * @param notional The annuity notional.
   * @param generator The OIS generator.
   * @param fixedRate The rate of the swap fixed leg.
   * @param isPayer The flag indicating if the annuity is paying (true) or receiving (false).
   * @return The swap.
   */
  public static SwapFixedONSimplifiedDefinition from(final ZonedDateTime settlementDate, final Period tenorAnnuity, final double notional, 
      final GeneratorSwapFixedONCompounding generator, final double fixedRate, final boolean isPayer) {
    final ZonedDateTime maturityDate = settlementDate.plus(tenorAnnuity);
    return from(settlementDate, maturityDate, notional, generator, fixedRate, isPayer);
  }

  /**
   * Builder of OIS swap from financial description (start date and end date).
   * @param settlementDate The annuity settlement or first fixing date.
   * @param endFixingPeriodDate  The end date of the OIS accrual period. Also called the maturity date of the annuity even if the actual payment can take place one or two days later. Not null.
   * @param notional The annuity notional.
   * @param generator The OIS generator.
   * @param fixedRate The rate of the swap fixed leg.
   * @param isPayer The flag indicating if the annuity is paying (true) or receiving (false).
   * @return The swap.
   */
  public static SwapFixedONSimplifiedDefinition from(final ZonedDateTime settlementDate, final ZonedDateTime endFixingPeriodDate, final double notional, 
      final GeneratorSwapFixedONCompounding generator, final double fixedRate, final boolean isPayer) {
    return from(settlementDate, endFixingPeriodDate, notional, notional, generator, fixedRate, isPayer);
  }

  /**
   * Builder of OIS swap from financial description (start date and end date, the fixed leg and floating leg notionals can be different).
   * @param settlementDate The annuity settlement or first fixing date.
   * @param endFixingPeriodDate  The end date of the OIS accrual period. Also called the maturity date of the annuity even if the actual payment can take place one or two days later. Not null.
   * @param notionalFixed The notional of the fixed leg.
   * @param notionalOIS The notional of the OIS leg.
   * @param generator The OIS generator.
   * @param fixedRate The rate of the swap fixed leg.
   * @param isPayer The flag indicating if the annuity is paying (true) or receiving (false).
   * @return The swap.
   */
  public static SwapFixedONSimplifiedDefinition from(final ZonedDateTime settlementDate, final ZonedDateTime endFixingPeriodDate, final double notionalFixed, final double notionalOIS,
      final GeneratorSwapFixedONCompounding generator, final double fixedRate, final boolean isPayer) {
    ArgumentChecker.notNull(settlementDate, "Settlement date");
    ArgumentChecker.notNull(endFixingPeriodDate, "Maturity date");
    ArgumentChecker.notNull(generator, "Swap generator");
    final GeneratorLegFixed genFix = generator.getFixedLegGenerator();
    final GeneratorLegONCompounding genON = generator.getONLegGenerator();
    final AnnuityCouponFixedDefinition fixedLeg = AnnuityDefinitionBuilder.couponFixed(genFix.getCurrency(), settlementDate, endFixingPeriodDate, genFix.getPaymentTenor().getPeriod(), 
        genFix.getCalendar(), genFix.getDayCount(), genFix.getBusinessDayConvention(), genFix.isEndOfMonth(), notionalFixed, fixedRate, isPayer, genFix.getStubType(), genFix.getPaymentLag());
    final AnnuityCouponONSimplifiedDefinition onLeg = AnnuityDefinitionBuilder.couponONSimpleCompoundedSimplified(settlementDate, endFixingPeriodDate, genON.getPaymentTenor().getPeriod(), 
        genON.getPaymentCalendar(), notionalOIS, genON.getONIndex(), genON.getIndexCalendar(), isPayer, genON.getBusinessDayConvention(), genON.isEndOfMonth(), 
        genON.getStubType(), genON.getPaymentLag());
    return new SwapFixedONSimplifiedDefinition(fixedLeg, onLeg);
  }

  /**
   * Swap builder from the financial details. On the fixed leg, the accrual dates are not the same as the payment dates.
   * There is a difference due to the settlement lag required on the OIS coupons.
   * @param settlementDate The settlement date.
   * @param tenorAnnuity The swap tenor.
   * @param tenorCoupon The coupons tenor. The tenor is the same on the fixed and OIS legs.
   * @param notional The notional.
   * @param index The OIS index.
   * @param fixedRate The fixed leg rate.
   * @param isPayer The flag indicating if the fixed leg is paying (true) or receiving (false).
   * @param settlementDays The number of days between last fixing of each coupon and the coupon payment (also called spot lag).
   * @param businessDayConvention The business day convention to compute the end date of the coupon.
   * @param dayCount The day count convention for the OIS
   * @param isEOM The end-of-month convention to compute the end date of the coupon.
   * @param calendar The holiday calendar for the ibor index.
   * @return The annuity.
   */
  public static SwapFixedONSimplifiedDefinition from(final ZonedDateTime settlementDate, final Period tenorAnnuity, final Period tenorCoupon, final double notional, final IndexON index,
      final double fixedRate, final boolean isPayer, final int settlementDays, final BusinessDayConvention businessDayConvention, final DayCount dayCount, final boolean isEOM,
      final Calendar calendar) {
    final Currency ccy = index.getCurrency();
    final ZonedDateTime endFixingPeriodDate = settlementDate.plus(tenorAnnuity);
    final StubType stub = StubType.SHORT_START;
    final AnnuityCouponFixedDefinition fixedLeg = AnnuityDefinitionBuilder.couponFixed(ccy, settlementDate, endFixingPeriodDate, tenorCoupon, 
        calendar, dayCount, businessDayConvention, isEOM, notional, fixedRate, isPayer, stub, 0);
    final AnnuityCouponONSimplifiedDefinition onLeg = AnnuityDefinitionBuilder.couponONSimpleCompoundedSimplified(settlementDate, endFixingPeriodDate, tenorCoupon, 
        calendar, notional, index, calendar, isPayer, businessDayConvention, isEOM, stub, 0);
    return new SwapFixedONSimplifiedDefinition(fixedLeg, onLeg);
  }

  /**
   * Swap builder from the financial details. On the fixed leg, the accrual dates are not the same as the payment dates.
   * There is a difference due to the settlement lag required on the OIS coupons.
   * @param settlementDate The settlement date.
   * @param maturityDate The maturity date. This is the last date of the fixing period, not the last payment date.
   * @param frequency The payments frequency.
   * @param notional The notional.
   * @param index The OIS index.
   * @param fixedRate The fixed leg rate.
   * @param isPayer The flag indicating if the fixed leg is paying (true) or receiving (false).
   * @param settlementDays The number of days between last fixing of each coupon and the coupon payment (also called spot lag).
   * @param businessDayConvention The business day convention to compute the end date of the coupon.
   * @param dayCount The day count convention for the OIS.
   * @param isEOM The end-of-month convention to compute the end date of the coupon.
   * @param calendar The holiday calendar for the ibor index.
   * @return The annuity.
   */
  public static SwapFixedONSimplifiedDefinition from(final ZonedDateTime settlementDate, final ZonedDateTime maturityDate, final Period frequency, final double notional, final IndexON index,
      final double fixedRate, final boolean isPayer, final int settlementDays, final BusinessDayConvention businessDayConvention, final DayCount dayCount, final boolean isEOM,
      final Calendar calendar) {
    final Currency ccy = index.getCurrency();
    final StubType stub = StubType.SHORT_START;
    final AnnuityCouponFixedDefinition fixedLeg = AnnuityDefinitionBuilder.couponFixed(ccy, settlementDate, maturityDate, frequency, 
        calendar, dayCount, businessDayConvention, isEOM, notional, fixedRate, isPayer, stub, 0);
    final AnnuityCouponONSimplifiedDefinition onLeg = AnnuityDefinitionBuilder.couponONSimpleCompoundedSimplified(settlementDate, maturityDate, frequency, 
        calendar, notional, index, calendar, isPayer, businessDayConvention, isEOM, stub, 0);
    return new SwapFixedONSimplifiedDefinition(fixedLeg, onLeg);
  }

  /**
   * The fixed leg of the swap.
   * @return Fixed leg.
   */
  public AnnuityCouponFixedDefinition getFixedLeg() {
    return (AnnuityCouponFixedDefinition) getFirstLeg();
  }

  /**
   * The Ibor leg of the swap.
   * @return Ibor leg.
   */
  public AnnuityCouponONSimplifiedDefinition getOISLeg() {
    return (AnnuityCouponONSimplifiedDefinition) getSecondLeg();
  }

  /**
   * {@inheritDoc}
   * @deprecated Use the method that does not take yield curve names
   */
  @Deprecated
  @SuppressWarnings({"unchecked" })
  @Override
  public SwapFixedCoupon<Coupon> toDerivative(final ZonedDateTime date, final String... yieldCurveNames) {
    final Annuity<CouponFixed> fixedLeg = this.getFixedLeg().toDerivative(date, yieldCurveNames);
    final Annuity<? extends Coupon> oisLeg = (Annuity<? extends Coupon>) this.getOISLeg().toDerivative(date, yieldCurveNames);
    return new SwapFixedCoupon<>(fixedLeg, (Annuity<Coupon>) oisLeg);
  }

  @SuppressWarnings({"unchecked" })
  @Override
  public SwapFixedCoupon<Coupon> toDerivative(final ZonedDateTime date) {
    final Annuity<CouponFixed> fixedLeg = this.getFixedLeg().toDerivative(date);
    final Annuity<? extends Coupon> oisLeg = (Annuity<? extends Coupon>) this.getOISLeg().toDerivative(date);
    return new SwapFixedCoupon<>(fixedLeg, (Annuity<Coupon>) oisLeg);
  }
}
