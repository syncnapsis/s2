/**
 * Syncnapsis Framework - Copyright (c) 2012-2014 ultimate
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version
 * 3 of the License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MECHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Plublic License along with this program;
 * if not, see <http://www.gnu.org/licenses/>.
 */
package com.syncnapsis.utils;

import com.syncnapsis.tests.LoggerTestCase;
import com.syncnapsis.tests.annotations.TestExcludesMethods;

@TestExcludesMethods("printError")
public class JettyUtilTest extends LoggerTestCase
{
	public void testGetJettyVersion() throws Exception
	{
		assertNotNull(JettyUtil.versionFound);
	}
	
	public void testCheckJettyVersion() throws Exception
	{
		assertTrue(JettyUtil.checkJettyVersion());
	}

	public void testJettyFound() throws Exception
	{
		assertTrue(JettyUtil.jettyFound());
	}
}