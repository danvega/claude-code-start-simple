package dev.danvega.slug;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Runs the packaged application in a separate JVM during ./mvnw verify. */
class SlugCliIT {

	@Test
	void printsSlugFromPackagedApplication() throws Exception {
		var result = runCli("Spring Boot 4");

		assertEquals(0, result.exitCode());
		assertEquals("spring-boot-4" + System.lineSeparator(), result.stdout());
		assertEquals("", result.stderr());
	}

	private static CliResult runCli(String... args) throws IOException, InterruptedException {
		String executable = System.getProperty("os.name").startsWith("Windows") ? "java.exe" : "java";
		String java = Path.of(System.getProperty("java.home"), "bin", executable).toString();
		var command = new ArrayList<>(List.of(java, "-jar", System.getProperty("slug.jar")));
		command.addAll(List.of(args));
		Process process = new ProcessBuilder(command).start();
		try {
			assertTrue(process.waitFor(10, TimeUnit.SECONDS), "CLI did not exit within 10 seconds");
			return new CliResult(process.exitValue(),
					new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8),
					new String(process.getErrorStream().readAllBytes(), StandardCharsets.UTF_8));
		}
		finally {
			process.destroyForcibly();
		}
	}

	private record CliResult(int exitCode, String stdout, String stderr) {
	}

}
