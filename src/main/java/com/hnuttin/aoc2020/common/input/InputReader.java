package com.hnuttin.aoc2020.common.input;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class InputReader {

	public static List<String> readInput(int day) {
		String path = String.format("com/hnuttin/aoc2020/day%s/day%s.input", day, day);
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
