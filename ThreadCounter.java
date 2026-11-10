
public class ThreadCounter {

    public static void main(String[] args) {

        final int TOTAL_THREADS = 1000;
        final int MAX_NUMBER = 1_000_000;

        // Store the count produced by each thread
        int[] results = new int[TOTAL_THREADS];

        // Store all threads
        Thread[] threads = new Thread[TOTAL_THREADS];

        // Create 1000 threads
        for (int i = 0; i < TOTAL_THREADS; i++) {

            final int threadNumber = i;

            threads[i] = new Thread(() -> {

                // Determine this thread's range
                int start = (threadNumber * MAX_NUMBER / TOTAL_THREADS) + 1;
                int end = ((threadNumber + 1) * MAX_NUMBER / TOTAL_THREADS);

                int count = 0;

                // Count numbers in this thread's range
                for (int number = start; number <= end; number++) {
                    count++;
                }

                // Save this thread's result
                results[threadNumber] = count;

            });

            // Start the thread
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < TOTAL_THREADS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Main thread interrupted.");
                return;
            }
        }

        // Add all thread results together
        int grandTotal = 0;

        for (int i = 0; i < TOTAL_THREADS; i++) {
            grandTotal += results[i];
        }

        // Display final result
        System.out.println("Number of threads: " + TOTAL_THREADS);
        System.out.println("Grand Total: " + grandTotal);
    }
}
