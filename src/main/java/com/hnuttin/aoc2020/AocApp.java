package com.hnuttin.aoc2020;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.apache.commons.lang3.time.StopWatch;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderMethodName = "forDay", setterPrefix = "with")
public class AocApp {

	int day;

	Supplier<Object> part1;
	Supplier<Object> part2;

	private void run() {
		System.out.printf("Day %s...%n", day);
		runPart(part1, 1);
		runPart(part2, 2);
	}

	private void runPart(Supplier<Object> part, int partNumber) {
		if (part != null) {
			StopWatch stopWatch = StopWatch.createStarted();
			Object result = part.get();
			stopWatch.stop();
			System.out.printf("\tPart %s (%sms): %s%n", partNumber, stopWatch.getTime(TimeUnit.MILLISECONDS), result);
		}
	}

	public static AocAppBuilder forDay(int day) {
		return new AocAppBuilder().withDay(day);
	}

	public static class AocAppBuilder {

		public void run() {
			build().run();
		}

	}
}
