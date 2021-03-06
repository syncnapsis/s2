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
package com.syncnapsis.data.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;

import com.syncnapsis.data.dao.NewsDao;
import com.syncnapsis.data.model.News;
import com.syncnapsis.data.model.Parameter;
import com.syncnapsis.data.service.NewsManager;
import com.syncnapsis.data.service.ParameterManager;
import com.syncnapsis.enums.EnumDateFormat;
import com.syncnapsis.enums.EnumLocale;
import com.syncnapsis.enums.EnumNewsAge;
import com.syncnapsis.tests.GenericManagerImplTestCase;
import com.syncnapsis.tests.annotations.TestCoversClasses;
import com.syncnapsis.tests.annotations.TestCoversMethods;

@TestCoversClasses({ NewsManager.class, NewsManagerImpl.class })
public class NewsManagerImplTest extends GenericManagerImplTestCase<News, Long, NewsManager, NewsDao>
{
	private NewsManager			newsManager;
	private ParameterManager	parameterManager;

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		setEntity(new News());
		setDaoClass(NewsDao.class);
		setMockDao(mockContext.mock(NewsDao.class));
		setMockManager(new NewsManagerImpl(mockDao, parameterManager));
	}

	public void testGetIdsByMaxAge() throws Exception
	{
		final EnumNewsAge maxAge = EnumNewsAge.length1;
		final long maxAgeValue = parameterManager.getLong(maxAge.getParameterKey()) * 1000;
		final Date date = new Date(timeProvider.get());
		final ArrayList<String> list = new ArrayList<String>();

		mockContext.checking(new Expectations() {
			{
				oneOf(mockDao).getIdsByMaxAge(maxAge, maxAgeValue, date);
				will(returnValue(list));
			}
		});
		
		List<String> result = mockManager.getIdsByMaxAge(maxAge, date);
		
		assertSame(list, result);
		mockContext.assertIsSatisfied();
	}

	public void testGetByNewsIdAndLocale() throws Exception
	{
		MethodCall managerCall = new MethodCall("getByNewsIdAndLocale", entity, "T1", EnumLocale.EN);
		MethodCall daoCall = managerCall;
		simpleGenericTest(managerCall, daoCall);
	}

	public void testGetActualIds() throws Exception
	{
		logger.debug("testing getQuickLaunchItemsByUser...");

		final List<String> testDatas = new ArrayList<String>();
		testDatas.add("1");
		testDatas.add("2");
		testDatas.add("3");

		final Date d = new Date(timeProvider.get());

		mockContext.checking(new Expectations() {
			{
				for(EnumNewsAge age : EnumNewsAge.values())
				{
					long ageValue = parameterManager.getLong(age.getParameterKey()) * 1000;
					oneOf(mockDao).getIdsByMaxAge(age, ageValue, d);
					will(returnValue(testDatas));
				}
			}
		});

		List<String> ids = mockManager.getActualIds(d);
		assertEquals(3 * EnumNewsAge.values().length, ids.size());

		mockContext.assertIsSatisfied();
	}

	@TestCoversMethods({ "getActual", "getActualIds" })
	public void testGetActualAndIds() throws Exception
	{
		logger.debug("testing on DB...");
		assertNotNull(newsManager);

		// setting maxItems to 12 for test-case
		Parameter p = parameterManager.getByName("news.maxItems");
		p.setValue("12");
		parameterManager.save(p);

		Date referenceDate = EnumDateFormat.yMd24_minus.getDateFormat().parse("2008-01-29 12:00:00");

		String[] expectedNewsIdsAll = { "T1SHOW", "T1SHOW2", "T1SHOW3", "T2SHOW", "T2SHOW2", "T2SHOW3", "T3SHOW", "T3SHOW2", "T4SHOW", "T4SHOW2",
				"T5SHOW", "T5SHOW2", "T6SHOW", "T6SHOW2", "T7SHOW", "T7SHOW2", "T8SHOW", "T8SHOW2", "T9SHOW", "T9SHOW2" };
		String[] expectedNewsIdsLimited = { "T1SHOW3", "T2SHOW3", "T1SHOW", "T2SHOW", "T3SHOW", "T4SHOW", "T5SHOW", "T6SHOW", "T7SHOW", "T8SHOW",
				"T9SHOW", "T1SHOW2" };
		assertEquals(new Integer(parameterManager.getByName("news.maxItems").getValue()), (Integer) expectedNewsIdsLimited.length);

		List<String> newsIdsAll = newsManager.getActualIds(referenceDate);
		assertEquals(expectedNewsIdsAll.length, newsIdsAll.size());
		for(int i = 0; i < newsIdsAll.size(); i++)
			assertEquals(expectedNewsIdsAll[i], newsIdsAll.get(i));

		List<News> newsLimited = newsManager.getActual(EnumLocale.EN, referenceDate);
		assertEquals(expectedNewsIdsLimited.length, newsLimited.size());
		for(int i = 0; i < newsLimited.size(); i++)
		{
			assertEquals(expectedNewsIdsLimited[i], newsLimited.get(i).getNewsId());
			assertEquals(EnumLocale.EN, newsLimited.get(i).getLocale());
		}

		// resetting maxItems to 10 after test-case
		p = parameterManager.getByName("news.maxItems");
		p.setValue("10");
		parameterManager.save(p);
	}
}
