package com;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

@State(Scope.Benchmark)
public class App {
    @Param({ "100", "200", "400", "800", "1600", "3200", "6400", "12800", "25600", "51200" })
    public int size; // size parameter which changes after all iterations executed
    public int flag = 0; // this variable is used to decrease unnecessary increase in count because of
                         // warmup iterations
    public int k;
    public int count = 0; // this variable is used to create new input list for every 5 iterations

    // object of sorting classes are created
    public InsertionSort insertionSort = new InsertionSort();
    public HeapSort heapSort = new HeapSort();
    public MergeSort mergeSort = new MergeSort();
    public PartialSelectionSort partialSelectionSort = new PartialSelectionSort();
    public QuickSort quickSort = new QuickSort();
    public QuickSelect quickSelect = new QuickSelect();

    public volatile List<Integer> input;

    public static void main(String[] args) throws InterruptedException, RunnerException {

        Options opt = new OptionsBuilder()
                .include(App.class.getSimpleName())
                .forks(1)
                .threads(5)
                .warmupIterations(5) // runs before the actual code to get more precise output
                .measurementIterations(75) // each input is used for 5 k values and each type of input is created for 5
                                                // times so that 75 iterations are executed in total
                .resultFormat(ResultFormatType.JSON)
                .result("target/" + App.class.getSimpleName() + ".json") // all cpu time results are printed into a json file
                .timeUnit(MILLISECONDS)
                .build();
        new Runner(opt).run();

    }

    // count and flag are reset after every parameter change
    @Setup(Level.Trial)
    public void setupTrial() {
        count = 0;
        flag = 0;
    }

    // k value is assigned and input list is created
    @Setup(Level.Iteration)
    public void inputSetup() {

        int kType = count % 5;
        int inputType = count / 25;

        this.k = Utility.getK(kType, this.size);
        if (count % 5 == 0) {
            this.input = Utility.getList(inputType, this.size);
        }
        count++;
        if (flag == 0 && count == 5) {
            count = 0;
            flag = 1;
        }
    }
    
    /* benchmark methods are executed for many times to reach the most correct cpu time */

    // measures cpu time of insertion sort algoritm
    @Benchmark
    @Group
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(time = 1000, timeUnit = MILLISECONDS)
    @Measurement(time = 1000, timeUnit = MILLISECONDS)
    public void benchmarkInsertion() throws InterruptedException {
        this.insertionSort.findK(this.input, this.k);

    }

    // measures cpu time of heap sort algoritm
    @Benchmark
    @Group
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(time = 1000, timeUnit = MILLISECONDS)
    @Measurement(time = 1000, timeUnit = MILLISECONDS)
    public void benchmarkHeap() throws InterruptedException {
        this.heapSort.findK(this.input, this.k);
    }

    // measures cpu time of merge sort algoritm
    @Benchmark
    @Group
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(time = 1000, timeUnit = MILLISECONDS)
    @Measurement(time = 1000, timeUnit = MILLISECONDS)
    public void benchmarkMerge() throws InterruptedException {
        this.mergeSort.findK(this.input, this.k);
    }

    // measures cpu time of partial selection sort algoritm
    @Benchmark
    @Group
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(time = 1000, timeUnit = MILLISECONDS)
    @Measurement(time = 1000, timeUnit = MILLISECONDS)
    public void benchmarkPartialSelectionSort() throws InterruptedException {
        this.partialSelectionSort.findK(this.input, this.k);
    }

    // measures cpu time of quick select algoritm
    @Benchmark
    @Group
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(time = 1000, timeUnit = MILLISECONDS)
    @Measurement(time = 1000, timeUnit = MILLISECONDS)
    public void benchmarkQuickSelect() throws InterruptedException {
        this.quickSelect.findK(this.input, this.k);
    }

    // measures cpu time of quick sort algoritm
    @Benchmark
    @Group
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(time = 1000, timeUnit = MILLISECONDS)
    @Measurement(time = 1000, timeUnit = MILLISECONDS)
    public void benchmarkQuickSort() throws InterruptedException {
        this.quickSort.findK(this.input, this.k);
    }

}
