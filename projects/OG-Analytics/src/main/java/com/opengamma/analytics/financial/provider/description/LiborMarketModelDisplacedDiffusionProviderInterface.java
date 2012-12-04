/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.provider.description;

import com.opengamma.analytics.financial.model.interestrate.definition.LiborMarketModelDisplacedDiffusionParameters;
import com.opengamma.util.money.Currency;

/**
 * Interface for swaption SABR parameters provider for one underlying.
 */
public interface LiborMarketModelDisplacedDiffusionProviderInterface {

  /**
   * Create a new copy of the provider.
   * @return The bundle.
   */
  LiborMarketModelDisplacedDiffusionProviderInterface copy();

  /**
   * Returns the LMM parameters.
   * @return The parameters.
   */
  LiborMarketModelDisplacedDiffusionParameters getLMMParameters();

  /**
   * Returns the currency for which the LMM parameters are valid (LMM on the discounting curve).
   * @return The currency.
   */
  Currency getLMMCurrency();

  /**
   * Returns the MulticurveProvider from which the HullWhiteProvider is composed.
   * @return The multi-curves provider.
   */
  MulticurveProviderInterface getMulticurveProvider();

}
