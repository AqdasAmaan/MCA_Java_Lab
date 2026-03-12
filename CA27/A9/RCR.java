class RCR {

    public static boolean railRoad(int[] inputOrder, int n, int k) {

        @SuppressWarnings("unchecked")
        CircularQueue<Integer>[] track = new CircularQueue[k];

        for (int i = 0; i < k; i++) {
            track[i] = new CircularQueue<>(n); 
        }

        int nextCarToOutput = 1;

        for (int i = n - 1; i >= 0; i--) {

            if (inputOrder[i] == nextCarToOutput) {

                System.out.println("Move car " + inputOrder[i] +
                        " from input track to output track");

                nextCarToOutput++;

                boolean moved;

                do {
                    moved = false;

                    for (int j = 0; j < k; j++) {

                        if (!track[j].isEmpty() &&
                                track[j].peek() == nextCarToOutput) {

                            System.out.println("Move car " +
                                    track[j].dequeue() +
                                    " from holding track " + j +
                                    " to output track");

                            nextCarToOutput++;
                            moved = true;
                            break; 
                        }
                    }

                } while (moved);

            } else {

                int c = inputOrder[i];
                int bestTrack = -1;
                int bestLast = 0;

                for (int j = 0; j < k; j++) {

                    if (!track[j].isEmpty()) {

                        int lastCar = track[j].getRear();

                        if (c > lastCar && lastCar > bestLast) {
                            bestLast = lastCar;
                            bestTrack = j;
                        }

                    } else if (bestTrack == -1) {
                        bestTrack = j;
                    }
                }

                if (bestTrack == -1)
                    return false;

                track[bestTrack].enqueue(c);

                System.out.println("Move car " + c +
                        " from input track to holding track " +
                        bestTrack);
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int[] inputOrder = {5, 4, 3, 1, 7, 6, 2}; //{5, 8, 1, 7, 4, 2, 9, 6, 3};
        int n = inputOrder.length;
        int k = 3; 

        boolean result = railRoad(inputOrder, n, k);

        if (result)
            System.out.println("\nRearrangement Successful");
        else
            System.out.println("\nRearrangement Failed");
    }
}