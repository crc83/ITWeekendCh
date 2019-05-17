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
import java.util.UUID;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;
import com.github.javafaker.Lebowski;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.sbelei.spannersample.entities.Lebovski;
import org.sbelei.spannersample.entities.LebovskiActor;
import org.sbelei.spannersample.entities.embedded.LebovskiActorEmb;
import org.sbelei.spannersample.entities.embedded.LebovskiEmb;
import org.sbelei.spannersample.entities.interleaved.LebovskiActorIl;
import org.sbelei.spannersample.entities.interleaved.LebovskiIl;
import org.sbelei.spannersample.repos.LebovskiActorRepository;
import org.sbelei.spannersample.repos.LebovskiEmbRepository;
import org.sbelei.spannersample.repos.LebovskiIlRepository;
import org.sbelei.spannersample.repos.LebovskiRepository;
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

	@Autowired
	private SpannerSchemaUtils spannerSchemaUtils;

	@Autowired
	private LebovskiRepository lebovskiRepository;

	@Autowired
	private LebovskiActorRepository lebovskiActorRepository;

	@Autowired
	private LebovskiEmbRepository lebovskiEmbRepository;

	@Autowired
	private LebovskiIlRepository lebovskiIlRepository;

	private Faker f = new Faker();

	public static void main(String[] args) {
		System.out.println(Arrays.toString(args));
		SpringApplication.run(SpannerITWeekendApp.class, args);
	}

	@Bean
	@Profile("!test")
	ApplicationRunner applicationRunner() {
		return (args) -> {
			reCreateTable("LEBOVSKI", Lebovski.class);
			reCreateTable("LEBOVSKIActor", LebovskiActor.class);
			reCreateTable("LEBOVSKIEMB", LebovskiEmb.class);
			reCreateTableForInterleavedHierarchy(LebovskiIl.class);
			reCreateTableForInterleavedHierarchy(LebovskiActorIl.class);
			fillWithLebovskiQuotas();
			fillWithLebovskiEmbQuotas();
			fillWithLebovskiIlQuotas();
		};
	}

	private void reCreateTableForInterleavedHierarchy(Class entityClass) {
		this.spannerDatabaseAdminTemplate.executeDdlStrings(
				this.spannerSchemaUtils.getDropTableDdlStringsForInterleavedHierarchy(entityClass), false
		);
		this.spannerDatabaseAdminTemplate.executeDdlStrings(
				this.spannerSchemaUtils.getCreateTableDdlStringsForInterleavedHierarchy(entityClass), false
		);
	}

	private void reCreateTable(String tableName, Class entityClass) {
		this.spannerDatabaseAdminTemplate.executeDdlStrings(
				Arrays.asList(this.spannerSchemaUtils.getDropTableDdlString(entityClass)), false
		);
		this.spannerDatabaseAdminTemplate.executeDdlStrings(
				Arrays.asList(this.spannerSchemaUtils.getCreateTableDdlString(entityClass)), false
		);
	}

	private void fillWithLebovskiQuotas() {
		for (int i = 0; i <= 100; i++) {
			Lebovski lebovski = new Lebovski();
			lebovski.setId(UUID.randomUUID());
			lebovski.setQuote(f.lebowski().quote());

			LebovskiActor actor = new LebovskiActor();
			actor.setId(i);
			actor.setName(f.lebowski().actor());

			lebovski.setActorId(actor.getId());

			lebovskiActorRepository.save(actor);
			System.out.println(lebovski.getId() + " " +lebovski.getQuote() + " " + actor.getName());
			lebovskiRepository.save(lebovski);
		}
	}

	private void fillWithLebovskiEmbQuotas() {
		for (int i = 0; i <= 100; i++) {
			LebovskiEmb lebovski = new LebovskiEmb();
			lebovski.setId(UUID.randomUUID());
			lebovski.setQuote(f.lebowski().quote());

			LebovskiActorEmb actor = new LebovskiActorEmb();
			actor.setNumber(i);
			actor.setName(f.lebowski().actor());

			lebovski.setActor(actor);

			lebovskiEmbRepository.save(lebovski);
			System.out.println(lebovski.getId() + " " +lebovski.getQuote() + " " + lebovski.getActor().getName());
		}
	}

	private void fillWithLebovskiIlQuotas() {
		for (int i = 0; i <= 100; i++) {
			LebovskiIl lebovski = new LebovskiIl();
			lebovski.setId(UUID.randomUUID());
			lebovski.setQuote(f.lebowski().quote());

			LebovskiActorIl actor = new LebovskiActorIl();
			actor.setId(lebovski.getId());
			actor.setNumber(i);
			actor.setName(f.lebowski().actor());

			lebovski.setActor(Arrays.asList(actor));

			lebovskiIlRepository.save(lebovski);
			System.out.println(lebovski.getId() + " " +lebovski.getQuote() + " " + lebovski.getActor().get(0).getName());
		}
	}
}
