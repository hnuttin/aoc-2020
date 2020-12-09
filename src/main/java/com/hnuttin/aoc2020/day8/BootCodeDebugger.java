package com.hnuttin.aoc2020.day8;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

class BootCodeDebugger {

	static int debug(List<Instruction> instructions) {
		return new DebugSession(instructions).debug().accumulator();
	}

	static int fixAndDebug(List<Instruction> instructions) {
		DebugSession.DebugResult result = null;
		int previousInstructionSwitch = -1;
		while (result == null || !result.halted()) {
			Instruction instructionToSwitch = instructions.stream()
					.skip(previousInstructionSwitch + 1)
					.filter(Instruction::isJmpOrNop)
					.findFirst()
					.orElseThrow();
			Instruction switchedInstruction = instructionToSwitch.switchInstructionType();
			previousInstructionSwitch = instructions.indexOf(instructionToSwitch);
			ArrayList<Instruction> newInstructions = new ArrayList<>(instructions);
			newInstructions.set(previousInstructionSwitch, switchedInstruction);
			result = new DebugSession(newInstructions).debug();
		}
		return result.accumulator();
	}

	@RequiredArgsConstructor
	private static class DebugSession {

		private final List<Instruction> instructions;
		private final List<Instruction> executed = new ArrayList<>();

		private int accumulator = 0;
		private int currentInstruction = 0;

		DebugResult debug() {
			if (currentInstruction == instructions.size()) {
				return new DebugResult(true, accumulator);
			} else {
				Instruction instruction = instructions.get(currentInstruction);
				if (executed.contains(instruction)) {
					return new DebugResult(false, accumulator);
				} else {
					switch (instruction.getType()) {
					case ACC -> {
						accumulator += instruction.getValue();
						currentInstruction++;
					}
					case JMP -> currentInstruction += instruction.getValue();
					case NOP -> currentInstruction++;
					}
					executed.add(instruction);
					return debug();
				}
			}
		}

		static record DebugResult(boolean halted, int accumulator) {

		}
	}

}
