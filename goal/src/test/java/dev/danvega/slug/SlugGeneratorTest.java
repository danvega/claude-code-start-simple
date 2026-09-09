package dev.danvega.slug;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.danvega.slug.SlugGenerator.slugify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The spec for SlugGenerator. Every rule the slug has to follow lives here.
 */
class SlugGeneratorTest {

	@Test
	@DisplayName("lowercases the title and joins words with a hyphen")
	void lowercasesAndJoins() {
		assertEquals("hello-world", slugify("Hello World"));
	}

	@Test
	@DisplayName("keeps digits")
	void keepsDigits() {
		assertEquals("spring-boot-4", slugify("Spring Boot 4"));
	}

	@Test
	@DisplayName("drops apostrophes without leaving a gap")
	void dropsApostrophes() {
		assertEquals("whats-new-in-java-26", slugify("What's New in Java 26?"));
	}

	@Test
	@DisplayName("folds accented characters down to ascii")
	void foldsAccents() {
		assertEquals("cafe-con-leche", slugify("Café Con Leche"));
	}

	@Test
	@DisplayName("turns punctuation into a separator")
	void punctuationBecomesSeparator() {
		assertEquals("spring-boot-4-modular-auto-configuration", slugify("Spring Boot 4: Modular Auto-Configuration"));
	}

	@Test
	@DisplayName("turns underscores into a separator")
	void underscoresBecomeSeparator() {
		assertEquals("building-a-cli-with-java", slugify("Building_a_CLI_with_Java"));
	}

	@Test
	@DisplayName("collapses runs of separators into one hyphen")
	void collapsesRepeatedSeparators() {
		assertEquals("too-many-spaces", slugify("Too    many     spaces"));
	}

	@Test
	@DisplayName("trims leading and trailing separators")
	void trimsEnds() {
		assertEquals("leading-and-trailing", slugify("  Leading and trailing!  "));
	}

	@Test
	@DisplayName("handles a percent sign in a title")
	void handlesPercent() {
		assertEquals("100-test-coverage", slugify("100% Test Coverage"));
	}

	@Test
	@DisplayName("truncates a long title at a word boundary")
	void truncatesAtWordBoundary() {
		String title = "Everything You Need to Know About Spring Boot 4 and the New Modular Auto Configuration";

		String slug = slugify(title);

		assertEquals("everything-you-need-to-know-about-spring-boot-4", slug);
		assertTrue(slug.length() <= SlugGenerator.MAX_LENGTH, "slug was longer than MAX_LENGTH");
	}

	@Test
	@DisplayName("cuts a single long word at the 50 character limit")
	void truncatesSingleLongWord() {
		assertEquals("a".repeat(50), slugify("a".repeat(60)));
	}

	@Test
	@DisplayName("never ends a truncated slug with a hyphen")
	void truncatedSlugHasNoTrailingHyphen() {
		String title = "A Practical Guide to Testing Spring Boot Applications End to End";

		String slug = slugify(title);

		assertTrue(slug.length() <= SlugGenerator.MAX_LENGTH, "slug was longer than MAX_LENGTH");
		assertTrue(!slug.endsWith("-"), "slug ended with a hyphen: " + slug);
	}

	@Test
	@DisplayName("rejects a null title")
	void rejectsNull() {
		assertThrows(IllegalArgumentException.class, () -> slugify(null));
	}

	@Test
	@DisplayName("rejects a title with nothing to slug")
	void rejectsTitleWithNoLettersOrDigits() {
		assertThrows(IllegalArgumentException.class, () -> slugify("   "));
		assertThrows(IllegalArgumentException.class, () -> slugify("!!!"));
	}

}
