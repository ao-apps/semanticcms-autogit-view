/*
 * semanticcms-autogit-view - SemanticCMS view of automatic Git status.
 * Copyright (C) 2019, 2020, 2021, 2022  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-autogit-view.
 *
 * semanticcms-autogit-view is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-autogit-view is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-autogit-view.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.semanticcms.autogit.view;

import com.aoapps.lang.Projects;
import com.aoapps.lang.util.PropertiesUtils;
import java.io.IOException;
import java.util.Properties;

/**
 * @author  AO Industries, Inc.
 */
final class Maven {

  /** Make no instances. */
  private Maven() {
    throw new AssertionError();
  }

  static final String jqueryVersion;

  static {
    try {
      Properties properties = PropertiesUtils.loadFromResource(Maven.class, "Maven.properties");
      jqueryVersion = Projects.getVersion("org.webjars.npm", "jquery", properties.getProperty("jqueryVersion"));
    } catch (IOException e) {
      throw new ExceptionInInitializerError(e);
    }
  }
}
