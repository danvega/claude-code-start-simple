package dev.danvega.slug;

/** Command-line entry point used by the integration tests. */
public final class SlugCli {

	private SlugCli() {
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java -jar slug-1.0.0-SNAPSHOT.jar \"Blog title\"");
			System.exit(2);
			return;
		}

		try {
			System.out.println(SlugGenerator.slugify(args[0]));
		}
		catch (IllegalArgumentException ex) {
			System.err.println("Error: " + ex.getMessage());
			System.exit(2);
		}
	}

}
