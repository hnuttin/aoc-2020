package com.hnuttin.aoc2020;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class InputReader {

	static List<String> readInput(int day) {
		String path = String.format("com/hnuttin/aoc2020/day%s/input.txt", day);
		try (InputStream resourceAsStream = InputReader.class.getClassLoader().getResourceAsStream(path)) {
			if (resourceAsStream != null) {
				return IOUtils.readLines(resourceAsStream, Charset.defaultCharset());
			} else {
				throw new IllegalArgumentException("No input file for day " + day);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
