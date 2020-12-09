package com.hnuttin.aoc2020.day8;

import static com.hnuttin.aoc2020.day8.Instruction.InstructionType.*;

import lombok.Getter;

@Getter
class Instruction {

	private final InstructionType type;
	private final int value;

	public Instruction(InstructionType type, int value) {
		this.type = type;
		this.value = value;
	}

	boolean isJmpOrNop() {
		return type == JMP || type == NOP;
	}

	Instruction switchInstructionType() {
		return new Instruction(
				type == JMP ?
						NOP :
						type == NOP ?
								JMP :
								type,
				value);
	}

	static Instruction fromRawInput(String rawInput) {
		String[] typeAndValue = rawInput.split(" ");
		InstructionType type = valueOf(typeAndValue[0].toUpperCase());
		int value = Integer.parseInt(typeAndValue[1]);
		return new Instruction(type, value);
	}

	enum InstructionType {
		ACC, JMP, NOP
	}

}
