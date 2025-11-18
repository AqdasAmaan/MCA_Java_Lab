import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// -------------------------------------------------------
// A very simple Download Task
// -------------------------------------------------------
class DownloadTask {
    final String url;
    final String dest;
    DownloadTask(String url, String dest) {
        this.url = url;
        this.dest = dest;
    }
}

// -------------------------------------------------------
// Worker Thread
// -------------------------------------------------------
class DownloadWorker implements Runnable {

    private final BlockingQueue<DownloadTask> queue;
    private final int id;

    public DownloadWorker(BlockingQueue<DownloadTask> queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                DownloadTask task = queue.take();

                if (task.url.equals("STOP"))
                    break;

                download(task.url, task.dest);
            }
        } catch (Exception ignored) {}
    }

    private void download(String fileURL, String destination) {
        System.out.println("Worker " + id + " downloading: " + fileURL);

        try {
            URL url = URI.create(fileURL).toURL();

            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            //c.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Testing...
            c.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
            c.setRequestProperty("Accept", "*/*");
            c.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
            c.setRequestProperty("Referer", "https://file-examples.com/");
            c.setRequestProperty("Connection", "keep-alive");
            // Testing...

            long fileSize = c.getContentLengthLong();

            try (InputStream in = c.getInputStream();
                 FileOutputStream out = new FileOutputStream(destination)) {

                byte[] buffer = new byte[4096];
                long total = 0;
                int bytes;
                int barLength = 30;

                while ((bytes = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytes);
                    total += bytes;

                    // Progress Bar
                    int percent = (int) ((total * 100) / fileSize);
                    int filledLength = (percent * barLength) / 100;
                    StringBuilder bar = new StringBuilder();
                    bar.append("[");
                    for (int i = 0; i < barLength; i++)
                        bar.append(i < filledLength ? "=" : " "); 
                    bar.append("] ");
                    System.out.print("\r" + bar + percent + "%");
                    System.out.flush();
                }
            }

            System.out.println("\nWorker " + id + " finished: " + fileURL);

        } catch (Exception e) {
            System.out.println("Worker " + id + " ERROR downloading " + fileURL);
        }
    }
}


// -------------------------------------------------------
// Manager
// -------------------------------------------------------
public class a11 {

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        List<String> urls = new ArrayList<>();

        System.out.println("Enter download URLs (type 'start' when done):");

        while (true) {
            System.out.print("> ");
            String input = scan.nextLine().trim();

            if (input.equalsIgnoreCase("start"))
                break;

            urls.add(input);
        }

        if (urls.isEmpty()) {
            System.out.println("No URLs entered. Exiting.");
            return;
        }

        Path folder = Paths.get("downloads");
        Files.createDirectories(folder);

        BlockingQueue<DownloadTask> queue = new ArrayBlockingQueue<>(urls.size());

        // Start 3 workers
        for (int i = 1; i <= 3; i++)
            new Thread(new DownloadWorker(queue, i)).start();

        // Add tasks
        for (String u : urls) {
            String name = u.substring(u.lastIndexOf('/') + 1);
            queue.put(new DownloadTask(u, folder.resolve(name).toString()));
        }

        // Add stop signals
        for (int i = 0; i < 3; i++)
            queue.put(new DownloadTask("STOP", ""));

        System.out.println("Downloads started...");
    }
}
