package com.hnuttin.aoc2020.day8;

import static com.hnuttin.aoc2020.common.input.InputParsers.aLineParser;

import java.util.List;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class day8 {

	public static void main(String... args) {
		AocApp.<List<Instruction>>forDay(8)
				.withInputParser(aLineParser(Instruction::fromRawInput)::parse)
				.withPart1(BootCodeDebugger::debug)
				.withPart2(BootCodeDebugger::fixAndDebug)
				.run();
	}
}
