/**
 * Copyright (C) 2010-2016 eBusiness Information, Excilys Group
 * Copyright (C) 2016-2018 the AndroidAnnotations project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.androidannotations.test.nonconfiguration;

import static org.fest.assertions.api.Assertions.assertThat;

import org.androidannotations.test.ebean.EmptyDependency;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class NonConfigurationActivityTest {

	private NonConfigurationActivity_ activity;

	@Before
	public void setUp() {
		ActivityController<NonConfigurationActivity_> controller = Robolectric.buildActivity(NonConfigurationActivity_.class);
		activity = controller.create().get();
	}

	@Test
	public void testNonConfigurationFieldReinjected() {
		activity.someObject = new Object();

		activity.recreate();

		assertThat(activity.someObject).isSameAs(activity.someObject);
	}

	@Test
	public void testConfigurationFieldDoesNotGetReinjected() {
		EmptyDependency dep = activity.recreatedDependency;

		activity.recreate();

		assertThat(activity.recreatedDependency).isNotSameAs(dep);
	}
}
