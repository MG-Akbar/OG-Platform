/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

#ifndef __inc_og_language_connector_livedata_h
#define __inc_og_language_connector_livedata_h

#include "RequestBuilder.h"
#include "com_opengamma_language_livedata_Available.h"
#include "com_opengamma_language_livedata_QueryAvailable.h"

REQUESTBUILDER_BEGIN(CLiveDataQueryAvailable)
protected:
	REQUESTBUILDER_REQUEST (com_opengamma_language_livedata_QueryAvailable)
public:
	bool Send () {
		com_opengamma_language_livedata_QueryAvailable query;
		memset (&query, 0, sizeof (query));
		return Send (&query);
	}
	REQUESTBUILDER_RESPONSE (com_opengamma_language_livedata_Available)
REQUESTBUILDER_END

#endif /* ifndef __inc_og_language_connector_livedata_h */
