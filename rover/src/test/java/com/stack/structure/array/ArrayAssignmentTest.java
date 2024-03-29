package com.stack.structure.array;

import com.stack.combinatoric.BinomialCoefficient;
import com.stack.structure.array.provider.Array2DArgumentsProvider;
import com.stack.structure.array.provider.ArrayArgumentsProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class ArrayAssignmentTest {

    private ArrayAssignment array;

    private static final String[][] arr =
            {{"Привет", "всем", "кто"}, {"изучает", "язык", "программирования"}, {"java"}};
    private static final int[] arrayInt =
            {11, 22, 86, 11, 86, 22, 11, 22, 86, 11, 86, 22, 11, 22, 86, 11, 86, 22, 11, 22, 86, 11, 86, 22, 11, 22, 86, 11, 86, 22, 11, 22, 86, 11, 86, 22, 11, 22, 86, 11, 86, 22, 11, 22, 86, 11, 86, 22, 11, 22, 86, 11, 86, 22, 11, 22, 86, 11, 86, 22, 11, 22, 86, 11, 86, 22, 11, 22, 86, 11, 86, 22, 11, 22, 86, 11, 86, 22, 11, 22, 86, 11, 86, 22, 8};

    @BeforeEach
    public void setup() {
        array = new ArrayAssignment();
    }

    @Test
    void countBy() {
        long startTime, duration;
        int b, c, a;

        startTime = System.nanoTime();
        b = array.countBy2(arr, 'е');
        duration = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        a = array.countBy(arr, 'е');
        long duration2 = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        c = array.countLetter(arr, 'е');
        long duration3 = System.nanoTime() - startTime;

        assertEquals(3, b);
        assertEquals(3, c);
        assertEquals(3, a);

        log.info("Execution time in two loops: {} ns", duration);
        log.info("Execution time in 1 loop: {} ns", duration2);
        log.info("Execution time in XOR: {} ns", duration3);
    }


    @ParameterizedTest(name = "{0}, sum:{1}")
    @ArgumentsSource(ArrayArgumentsProvider.class)
    void testSumArray(
            int[] array,
            int sum) {
        int expected = expectedSum(array);
        assertEquals(expected, this.array.sumArrayTraverse(array));
        assertEquals(sum, this.array.sumArrayTraverse(array));
        assertEquals(sum, expected);
    }

    @ParameterizedTest
    @ArgumentsSource(ArrayArgumentsProvider.class)
    void testMaxArray(int[] array) {
        assertEquals(expectedMax(array), this.array.maxArray(array));
    }

    @ParameterizedTest
    @ArgumentsSource(ArrayArgumentsProvider.class)
    void testMinArray(int[] array) {
        assertEquals(expectedMin(array), this.array.minArray(array));
    }

    @ParameterizedTest
    @ArgumentsSource(ArrayArgumentsProvider.class)
    void testMeanArray(
            int[] array,
            double sum) {
        double expected = expectedMean(array);
        double actual = this.array.meanArray(array);
        double mean = sum / array.length;
        assertEquals(mean, expected);
        assertEquals(mean, actual);
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "{0}, sum:{1}")
    @ArgumentsSource(Array2DArgumentsProvider.class)
    void testSum2DArray(
            int[][] array,
            int sum) {
        int expected = expectedSum2D(array);
        int actual = this.array.sum2DArray(array);
        assertEquals(sum, expected);
        assertEquals(sum, actual);
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "{0}, max:{2}")
    @ArgumentsSource(Array2DArgumentsProvider.class)
    void testMax2DArray(
            int[][] array,
            int sum,
            int max) {
        int expected = expectedMax2D(array);
        int actual = this.array.max2DArray(array);
        assertEquals(max, expected);
        assertEquals(max, actual);
        assertEquals(expected, actual);
        assertEquals((sum - actual), (sum - expected));
    }

    @ParameterizedTest
    @ArgumentsSource(Array2DArgumentsProvider.class)
    void testCountElements(int[][] array) {
        int expected = expectedCountElements(array);
        int actual = this.array.countElements(array);
        assertEquals(expected, actual);
    }

    private int expectedSum(int[] array) {
        return IntStream.of(array)
                        .reduce(0, Integer::sum);
    }

    private int expectedMax(int[] array) {
        return Arrays.stream(array)
                     .max()
                     .orElse(Integer.MAX_VALUE);
    }

    private int expectedMin(int[] array) {
        return Arrays.stream(array)
                     .min()
                     .orElse(Integer.MIN_VALUE);
    }

    private double expectedMean(int[] array) {
        return (double) expectedSum(array) / array.length;
    }

    private int expectedSum2D(int[][] array) {
        return Arrays.stream(array)
                     .mapToInt(row -> Arrays.stream(row).sum())
                     .sum();
    }

    private int expectedMax2D(int[][] array) {
        return Arrays.stream(array)
                     .flatMapToInt(Arrays::stream)
                     .max()
                     .orElse(Integer.MIN_VALUE);
    }

    private int expectedCountElements(int[][] array) {
        return Arrays.stream(array)
                     .mapToInt(row -> row.length)
                     .sum();
    }

    @Test
    void findIndexOfUniqueSingleNumber() {
        long start = System.nanoTime();
        int unique = array.findIndexOfSingleNumber(arrayInt);
        long end = System.nanoTime();
        System.out.println("HashMap Execution time in nanoseconds: " + (end - start));
        assertEquals(84, unique);
    }

    @Test
    void findIndexOfUnique() {
        long start = System.nanoTime();
        int unique = array.findIndexOfUnique(arrayInt);
        long end = System.nanoTime();
        System.out.println("XOR Execution time in nanoseconds: " + (end - start));
        assertEquals(84, unique);
    }

    @Test
    void findIndexOfUniqueSingleNumbers() {
        long start = System.nanoTime();
        int unique = array.findIndexOfSingleNumberList(arrayInt);
        long end = System.nanoTime();
        System.out.println("Execution time in nanoseconds: " + (end - start));
        assertEquals(84, unique);
    }

    @Test
    void testFindIndexOfSingleNumberLists() {
        long start = System.nanoTime();
        int unique = array.findIndexOfSingleNumberLists(arrayInt);
        long end = System.nanoTime();
        System.out.println("List Execution time in nanoseconds: " + (end - start));
        assertEquals(84, unique);
    }

    @Test
    void findIndexOfUniqueSingleChar() {
        char[] arr = {'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'a', 'b', 'c', 'a', 'b', 'z'};
        long start = System.nanoTime();
        int unique = array.findIndexOfSingleChar(arr);
        long end = System.nanoTime();
        System.out.println("Execution time in nanoseconds: " + (end - start));
        assertEquals(124, unique);
    }

    @Test
    void testMain() {
        int numRows = 5;
        BinomialCoefficient.printPascalPyramid(numRows);
    }
}
