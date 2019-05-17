/*
 * Copyright 2017-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sbelei.spannersample;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.data.spanner.core.admin.SpannerDatabaseAdminTemplate;
import org.springframework.cloud.gcp.data.spanner.core.admin.SpannerSchemaUtils;
import org.springframework.cloud.gcp.data.spanner.repository.config.EnableSpannerRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * Application to execute the sample app for Spanner.
 *
 * @author Daniel Zou
 */
@SpringBootApplication
@EnableSpannerRepositories(value = "org.sbelei.spannersample.repos")
public class SpannerITWeekendApp {

	private static final Log LOGGER = LogFactory.getLog(SpannerITWeekendApp.class);

	@Autowired
	private SpannerDatabaseAdminTemplate spannerDatabaseAdminTemplate;

	public static void main(String[] args) {
		System.out.println(Arrays.toString(args));
		SpringApplication.run(SpannerITWeekendApp.class, args);
	}

	@Bean
	@Profile("!test")
	ApplicationRunner applicationRunner() {
		return (args) -> {

		};
	}
}