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

import com.syncnapsis.enums.EnumLogicalOperator;
import com.syncnapsis.tests.LoggerTestCase;

/**
 * Class testing BooleanUtil.<br>
 * <br>
 * 
 * @author ultimate
 */
public class BooleanUtilTest extends LoggerTestCase
{
	public void testAnd()
	{
		logger.debug("testing AND with 2 args");

		logger.debug("testing AND with 2 args (valid, primitive)");
		assertEquals(true, BooleanUtil.and(true, true));
		assertEquals(false, BooleanUtil.and(true, false));
		assertEquals(false, BooleanUtil.and(false, true));
		assertEquals(false, BooleanUtil.and(false, false));

		logger.debug("testing AND with 2 args (valid, Object)");
		assertEquals(true, BooleanUtil.and((Boolean) true, (Boolean) true));
		assertEquals(false, BooleanUtil.and((Boolean) true, (Boolean) false));
		assertEquals(false, BooleanUtil.and((Boolean) false, (Boolean) true));
		assertEquals(false, BooleanUtil.and((Boolean) false, (Boolean) false));

		logger.debug("testing AND with 2 args (invalid)");
		try
		{
			BooleanUtil.and(null, true);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b1 is null.", e.getMessage());
		}
		try
		{
			BooleanUtil.and(true, null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b2 is null.", e.getMessage());
		}
	}

	public void testAndArray()
	{
		logger.debug("testing AND with 3 args");

		logger.debug("testing AND with 3 args (valid, primitive)");
		assertEquals(true, BooleanUtil.and(new boolean[] { true, true, true }));
		assertEquals(false, BooleanUtil.and(new boolean[] { true, true, false }));
		assertEquals(false, BooleanUtil.and(new boolean[] { true, false, true }));
		assertEquals(false, BooleanUtil.and(new boolean[] { true, false, false }));
		assertEquals(false, BooleanUtil.and(new boolean[] { false, true, true }));
		assertEquals(false, BooleanUtil.and(new boolean[] { false, true, false }));
		assertEquals(false, BooleanUtil.and(new boolean[] { false, false, true }));
		assertEquals(false, BooleanUtil.and(new boolean[] { false, false, false }));

		logger.debug("testing AND with 3 args (valid, Object)");
		assertEquals(true, BooleanUtil.and(new Boolean[] { true, true, true }));
		assertEquals(false, BooleanUtil.and(new Boolean[] { true, true, false }));
		assertEquals(false, BooleanUtil.and(new Boolean[] { true, false, true }));
		assertEquals(false, BooleanUtil.and(new Boolean[] { true, false, false }));
		assertEquals(false, BooleanUtil.and(new Boolean[] { false, true, true }));
		assertEquals(false, BooleanUtil.and(new Boolean[] { false, true, false }));
		assertEquals(false, BooleanUtil.and(new Boolean[] { false, false, true }));
		assertEquals(false, BooleanUtil.and(new Boolean[] { false, false, false }));

		logger.debug("testing AND with 3 args (invalid)");
		try
		{
			BooleanUtil.and((boolean[]) null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean b is null.", e.getMessage());
		}
		try
		{
			BooleanUtil.and((Boolean[]) null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean b is null.", e.getMessage());
		}
		try
		{
			BooleanUtil.and(new boolean[0]);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains no elements.", e.getMessage());
		}
		try
		{
			BooleanUtil.and(new Boolean[0]);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains no elements.", e.getMessage());
		}
		try
		{
			BooleanUtil.and(new Boolean[] { true, null, true });
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains a null element: index 1.", e.getMessage());
		}
	}

	public void testOr()
	{
		logger.debug("testing OR with 2 args");

		logger.debug("testing OR with 2 args (valid, primitive)");
		assertEquals(true, BooleanUtil.or(true, true));
		assertEquals(true, BooleanUtil.or(true, false));
		assertEquals(true, BooleanUtil.or(false, true));
		assertEquals(false, BooleanUtil.or(false, false));

		logger.debug("testing OR with 2 args (valid, Object)");
		assertEquals(true, BooleanUtil.or((Boolean) true, (Boolean) true));
		assertEquals(true, BooleanUtil.or((Boolean) true, (Boolean) false));
		assertEquals(true, BooleanUtil.or((Boolean) false, (Boolean) true));
		assertEquals(false, BooleanUtil.or((Boolean) false, (Boolean) false));

		logger.debug("testing OR with 2 args (invalid)");
		try
		{
			BooleanUtil.or(null, true);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b1 is null.", e.getMessage());
		}
		try
		{
			BooleanUtil.or(true, null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b2 is null.", e.getMessage());
		}
	}

	public void testOrArray()
	{
		logger.debug("testing OR with 3 args");

		logger.debug("testing OR with 3 args (valid, primitive)");
		assertEquals(true, BooleanUtil.or(new boolean[] { true, true, true }));
		assertEquals(true, BooleanUtil.or(new boolean[] { true, true, false }));
		assertEquals(true, BooleanUtil.or(new boolean[] { true, false, true }));
		assertEquals(true, BooleanUtil.or(new boolean[] { true, false, false }));
		assertEquals(true, BooleanUtil.or(new boolean[] { false, true, true }));
		assertEquals(true, BooleanUtil.or(new boolean[] { false, true, false }));
		assertEquals(true, BooleanUtil.or(new boolean[] { false, false, true }));
		assertEquals(false, BooleanUtil.or(new boolean[] { false, false, false }));

		logger.debug("testing OR with 3 args (valid, Object)");
		assertEquals(true, BooleanUtil.or(new Boolean[] { true, true, true }));
		assertEquals(true, BooleanUtil.or(new Boolean[] { true, true, false }));
		assertEquals(true, BooleanUtil.or(new Boolean[] { true, false, true }));
		assertEquals(true, BooleanUtil.or(new Boolean[] { true, false, false }));
		assertEquals(true, BooleanUtil.or(new Boolean[] { false, true, true }));
		assertEquals(true, BooleanUtil.or(new Boolean[] { false, true, false }));
		assertEquals(true, BooleanUtil.or(new Boolean[] { false, false, true }));
		assertEquals(false, BooleanUtil.or(new Boolean[] { false, false, false }));

		logger.debug("testing OR with 3 args (invalid)");
		try
		{
			BooleanUtil.or((boolean[]) null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean b is null.", e.getMessage());
		}
		try
		{
			BooleanUtil.or((Boolean[]) null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean b is null.", e.getMessage());
		}
		try
		{
			BooleanUtil.or(new boolean[0]);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains no elements.", e.getMessage());
		}
		try
		{
			BooleanUtil.or(new Boolean[0]);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains no elements.", e.getMessage());
		}
		try
		{
			BooleanUtil.or(new Boolean[] { true, null, true });
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains a null element: index 1.", e.getMessage());
		}
	}

	public void testXor()
	{
		logger.debug("testing XOR with 2 args");

		logger.debug("testing XOR with 2 args (valid, primitive)");
		assertEquals(false, BooleanUtil.xor(true, true));
		assertEquals(true, BooleanUtil.xor(true, false));
		assertEquals(true, BooleanUtil.xor(false, true));
		assertEquals(false, BooleanUtil.xor(false, false));

		logger.debug("testing XOR with 2 args (valid, Object)");
		assertEquals(false, BooleanUtil.xor((Boolean) true, (Boolean) true));
		assertEquals(true, BooleanUtil.xor((Boolean) true, (Boolean) false));
		assertEquals(true, BooleanUtil.xor((Boolean) false, (Boolean) true));
		assertEquals(false, BooleanUtil.xor((Boolean) false, (Boolean) false));

		logger.debug("testing XOR with 2 args (invalid)");
		try
		{
			BooleanUtil.xor(null, true);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b1 is null.", e.getMessage());
		}
		try
		{
			BooleanUtil.xor(true, null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b2 is null.", e.getMessage());
		}
	}

	public void testXorArray()
	{
		logger.debug("testing XOR with 3 args");

		logger.debug("testing XOR with 3 args (valid, primitive)");
		assertEquals(false, BooleanUtil.xor(new boolean[] { true, true, true }));
		assertEquals(false, BooleanUtil.xor(new boolean[] { true, true, false }));
		assertEquals(false, BooleanUtil.xor(new boolean[] { true, false, true }));
		assertEquals(true, BooleanUtil.xor(new boolean[] { true, false, false }));
		assertEquals(false, BooleanUtil.xor(new boolean[] { false, true, true }));
		assertEquals(true, BooleanUtil.xor(new boolean[] { false, true, false }));
		assertEquals(true, BooleanUtil.xor(new boolean[] { false, false, true }));
		assertEquals(false, BooleanUtil.xor(new boolean[] { false, false, false }));

		logger.debug("testing XOR with 3 args (valid, Object)");
		assertEquals(false, BooleanUtil.xor(new Boolean[] { true, true, true }));
		assertEquals(false, BooleanUtil.xor(new Boolean[] { true, true, false }));
		assertEquals(false, BooleanUtil.xor(new Boolean[] { true, false, true }));
		assertEquals(true, BooleanUtil.xor(new Boolean[] { true, false, false }));
		assertEquals(false, BooleanUtil.xor(new Boolean[] { false, true, true }));
		assertEquals(true, BooleanUtil.xor(new Boolean[] { false, true, false }));
		assertEquals(true, BooleanUtil.xor(new Boolean[] { false, false, true }));
		assertEquals(false, BooleanUtil.xor(new Boolean[] { false, false, false }));

		logger.debug("testing XOR with 3 args (invalid)");
		try
		{
			BooleanUtil.xor((boolean[]) null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean b is null.", e.getMessage());
		}
		try
		{
			BooleanUtil.xor((Boolean[]) null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean b is null.", e.getMessage());
		}
		try
		{
			BooleanUtil.xor(new boolean[0]);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains no elements.", e.getMessage());
		}
		try
		{
			BooleanUtil.xor(new Boolean[0]);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains no elements.", e.getMessage());
		}
		try
		{
			BooleanUtil.xor(new Boolean[] { true, null, true });
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains a null element: index 1.", e.getMessage());
		}
	}

	public void testNor()
	{
		logger.debug("testing OR with 2 args");

		logger.debug("testing NOR with 2 args (valid, primitive)");
		assertEquals(false, BooleanUtil.nor(true, true));
		assertEquals(false, BooleanUtil.nor(true, false));
		assertEquals(false, BooleanUtil.nor(false, true));
		assertEquals(true, BooleanUtil.nor(false, false));

		logger.debug("testing NOR with 2 args (valid, Object)");
		assertEquals(false, BooleanUtil.nor((Boolean) true, (Boolean) true));
		assertEquals(false, BooleanUtil.nor((Boolean) true, (Boolean) false));
		assertEquals(false, BooleanUtil.nor((Boolean) false, (Boolean) true));
		assertEquals(true, BooleanUtil.nor((Boolean) false, (Boolean) false));

		logger.debug("testing NOR with 2 args (invalid)");
		try
		{
			BooleanUtil.nor(null, true);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b1 is null.", e.getMessage());
		}
		try
		{
			BooleanUtil.nor(true, null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Boolean b2 is null.", e.getMessage());
		}
	}

	public void testNorArray()
	{
		logger.debug("testing NOR with 3 args");

		logger.debug("testing NOR with 3 args (valid, primitive)");
		assertEquals(false, BooleanUtil.nor(new boolean[] { true, true, true }));
		assertEquals(false, BooleanUtil.nor(new boolean[] { true, true, false }));
		assertEquals(false, BooleanUtil.nor(new boolean[] { true, false, true }));
		assertEquals(false, BooleanUtil.nor(new boolean[] { true, false, false }));
		assertEquals(false, BooleanUtil.nor(new boolean[] { false, true, true }));
		assertEquals(false, BooleanUtil.nor(new boolean[] { false, true, false }));
		assertEquals(false, BooleanUtil.nor(new boolean[] { false, false, true }));
		assertEquals(true, BooleanUtil.nor(new boolean[] { false, false, false }));

		logger.debug("testing NOR with 3 args (valid, Object)");
		assertEquals(false, BooleanUtil.nor(new Boolean[] { true, true, true }));
		assertEquals(false, BooleanUtil.nor(new Boolean[] { true, true, false }));
		assertEquals(false, BooleanUtil.nor(new Boolean[] { true, false, true }));
		assertEquals(false, BooleanUtil.nor(new Boolean[] { true, false, false }));
		assertEquals(false, BooleanUtil.nor(new Boolean[] { false, true, true }));
		assertEquals(false, BooleanUtil.nor(new Boolean[] { false, true, false }));
		assertEquals(false, BooleanUtil.nor(new Boolean[] { false, false, true }));
		assertEquals(true, BooleanUtil.nor(new Boolean[] { false, false, false }));

		logger.debug("testing NOR with 3 args (invalid)");
		try
		{
			BooleanUtil.nor((boolean[]) null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean b is null.", e.getMessage());
		}
		try
		{
			BooleanUtil.nor((Boolean[]) null);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean b is null.", e.getMessage());
		}
		try
		{
			BooleanUtil.nor(new boolean[0]);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains no elements.", e.getMessage());
		}
		try
		{
			BooleanUtil.nor(new Boolean[0]);
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains no elements.", e.getMessage());
		}
		try
		{
			BooleanUtil.nor(new Boolean[] { true, null, true });
			fail("Expected Exception not occurred.");
		}
		catch(IllegalArgumentException e)
		{
			assertNotNull(e);
			assertEquals("The given Array of Boolean contains a null element: index 1.", e.getMessage());
		}
	}

	public void testLogical()
	{
		logger.debug("testing LOGICAL with 2 args");

		logger.debug("testing LOGICAL with 2 args (valid only)");
		Boolean[][] B = new Boolean[4][2];
		B[0] = new Boolean[] { true, true };
		B[1] = new Boolean[] { true, false };
		B[2] = new Boolean[] { false, true };
		B[3] = new Boolean[] { false, false };

		boolean[][] b = new boolean[4][2];
		b[0] = new boolean[] { true, true };
		b[1] = new boolean[] { true, false };
		b[2] = new boolean[] { false, true };
		b[3] = new boolean[] { false, false };

		for(int i = 0; i < b.length; i++)
		{
			assertEquals(BooleanUtil.and(B[i][0], B[i][1]), BooleanUtil.logical(EnumLogicalOperator.AND, B[i][0], B[i][1]));
			assertEquals(BooleanUtil.or(B[i][0], B[i][1]), BooleanUtil.logical(EnumLogicalOperator.OR, B[i][0], B[i][1]));
			assertEquals(BooleanUtil.xor(B[i][0], B[i][1]), BooleanUtil.logical(EnumLogicalOperator.XOR, B[i][0], B[i][1]));
			assertEquals(BooleanUtil.nor(B[i][0], B[i][1]), BooleanUtil.logical(EnumLogicalOperator.NOR, B[i][0], B[i][1]));

			assertEquals(BooleanUtil.and(b[i][0], b[i][1]), BooleanUtil.logical(EnumLogicalOperator.AND, b[i][0], b[i][1]));
			assertEquals(BooleanUtil.or(b[i][0], b[i][1]), BooleanUtil.logical(EnumLogicalOperator.OR, b[i][0], b[i][1]));
			assertEquals(BooleanUtil.xor(b[i][0], b[i][1]), BooleanUtil.logical(EnumLogicalOperator.XOR, b[i][0], b[i][1]));
			assertEquals(BooleanUtil.nor(b[i][0], b[i][1]), BooleanUtil.logical(EnumLogicalOperator.NOR, b[i][0], b[i][1]));
		}
	}

	public void testLogicalArray()
	{
		logger.debug("testing LOGICAL with 3 args");

		logger.debug("testing LOGICAL with 3 args (valid only)");
		Boolean[][] B = new Boolean[8][];
		B[0] = new Boolean[] { true, true, true };
		B[1] = new Boolean[] { true, true, false };
		B[2] = new Boolean[] { true, false, true };
		B[3] = new Boolean[] { true, false, false };
		B[4] = new Boolean[] { false, true, true };
		B[5] = new Boolean[] { false, true, false };
		B[6] = new Boolean[] { false, false, true };
		B[7] = new Boolean[] { false, false, false };

		boolean[][] b = new boolean[8][];
		b[0] = new boolean[] { true, true, true };
		b[1] = new boolean[] { true, true, false };
		b[2] = new boolean[] { true, false, true };
		b[3] = new boolean[] { true, false, false };
		b[4] = new boolean[] { false, true, true };
		b[5] = new boolean[] { false, true, false };
		b[6] = new boolean[] { false, false, true };
		b[7] = new boolean[] { false, false, false };

		for(int i = 0; i < b.length; i++)
		{
			assertEquals(BooleanUtil.and(B[i]), BooleanUtil.logical(EnumLogicalOperator.AND, B[i]));
			assertEquals(BooleanUtil.or(B[i]), BooleanUtil.logical(EnumLogicalOperator.OR, B[i]));
			assertEquals(BooleanUtil.xor(B[i]), BooleanUtil.logical(EnumLogicalOperator.XOR, B[i]));
			assertEquals(BooleanUtil.nor(B[i]), BooleanUtil.logical(EnumLogicalOperator.NOR, B[i]));

			assertEquals(BooleanUtil.and(b[i]), BooleanUtil.logical(EnumLogicalOperator.AND, b[i]));
			assertEquals(BooleanUtil.or(b[i]), BooleanUtil.logical(EnumLogicalOperator.OR, b[i]));
			assertEquals(BooleanUtil.xor(b[i]), BooleanUtil.logical(EnumLogicalOperator.XOR, b[i]));
			assertEquals(BooleanUtil.nor(b[i]), BooleanUtil.logical(EnumLogicalOperator.NOR, b[i]));
		}
	}
}
