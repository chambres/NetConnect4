import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main {
        public static void main(String[] args) {
                ServerMain server = new ServerMain();
                ClientMain client = new ClientMain();
                ClientMain client2 = new ClientMain();

                ExecutorService executor = Executors.newFixedThreadPool(3);
        
                executor.execute(() -> {
                    // first function running in its own thread
                    System.out.println("Function 1 started");
                    // do some work here
                    server.main(args);
                    System.out.println("Function 1 finished");
                });
        
                executor.execute(() -> {
                    // second function running in its own thread
                    System.out.println("Function 2 started");
                    // do some work here
                    client.main(args);
                    System.out.println("Function 2 finished");
                });
        
                executor.execute(() -> {
                    // third function running in its own thread
                    System.out.println("Function 3 started");
                    // do some work here
                    client2.main(args);
                    System.out.println("Function 3 finished");
                });
        
                executor.shutdown();
            }
    
}
