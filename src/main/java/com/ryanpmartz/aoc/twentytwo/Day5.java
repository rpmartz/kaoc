package com.ryanpmartz.aoc.twentytwo;

import com.ryanpmartz.aoc.common.AocDayNumber;
import com.ryanpmartz.aoc.common.AocYear;
import com.ryanpmartz.aoc.common.io.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Day5 {

    public static void main(String[] args) {

        Map<Integer, Stack<String>> stacks = new HashMap<>();

        stacks.put(1, buildStack("S", "C", "V", "N"));
        stacks.put(2, buildStack("Z", "M", "J", "H", "N", "S"));
        stacks.put(3, buildStack("M", "C", "T", "G", "J", "N", "D"));
        stacks.put(4, buildStack("T", "D", "F", "J", "W", "R", "M"));
        stacks.put(5, buildStack("H", "F", "P"));
        stacks.put(6, buildStack("C", "T", "Z", "H", "J"));
        stacks.put(7, buildStack("D", "P", "R", "Q", "F", "S", "L", "Z"));
        stacks.put(8, buildStack("C", "S", "L", "H", "D", "F", "P", "W"));
        stacks.put(9, buildStack("D", "S", "M", "P", "F", "N", "G", "Z"));

        List<String> lines = InputReader.INSTANCE.read(AocYear.TWENTY_TWO, AocDayNumber.FIVE);
        for (String line : lines) {
            String numericInstructions = line.replace("move ", "")
                    .replace("from ", "")
                    .replace("to ", "");
            String[] parts = numericInstructions.split(" ");

            int numToMove = Integer.parseInt(parts[0]);
            int srcStackNum = Integer.parseInt(parts[1]);
            int dstStackNum = Integer.parseInt(parts[2]);

            Stack<String> srcStack = stacks.get(srcStackNum);
            Stack<String> dstStack = stacks.get(dstStackNum);

            Stack<String> tmpStack = new Stack<String>();
            for (int i = 0; i < numToMove; i++) {
                tmpStack.push(srcStack.pop());
            }

            for (int i = 0; i < numToMove; i++) {
                dstStack.push(tmpStack.pop());
            }

            stacks.put(srcStackNum, srcStack);
            stacks.put(dstStackNum, dstStack);
        }

        for (int i = 1; i < 10; i++) {
            System.out.print(stacks.get(i).peek());
        }


    }

    public static Stack<String> buildStack(String... vals) {
        Stack<String> newStack = new Stack<>();

        for (String val : vals) {
            newStack.push(val);
        }

        return newStack;
    }
}
