package dev.danvega.slug;

import java.util.Locale;

/**
 * Turns a blog post title into a URL slug.
 *
 * This starter handles casing and whitespace. See PLAN.md for the remaining work.
 */
public final class SlugGenerator {

	/** The maximum length to enforce in PLAN.md's length task. */
	public static final int MAX_LENGTH = 50;

	private SlugGenerator() {
	}

	public static String slugify(String title) {
		return title.strip().toLowerCase(Locale.ROOT).replaceAll("\\s+", "-");
	}

}
