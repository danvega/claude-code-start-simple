package dev.danvega.slug;

import org.junit.jupiter.api.Test;

import static dev.danvega.slug.SlugGenerator.slugify;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Baseline coverage. Add tests for each task in PLAN.md during the demo. */
class SlugGeneratorTest {

	@Test
	void lowercasesAndKeepsDigits() {
		assertEquals("spring-boot-4", slugify("Spring Boot 4"));
	}

	@Test
	void trimsAndCollapsesWhitespace() {
		assertEquals("hello-world", slugify("  Hello   World  "));
	}

}
