package katas.console;
import java.io.Console;
import java.util.Locale;

public class ConsoleTest {
    private Console console = null;
    
    public ConsoleTest() {
        initializeConsole();        
    }
    
    private void initializeConsole() {
        console = System.console();
        if (console == null) {
            System.out.println("No console: not in interactive mode!");
            System.exit(0);
        }
    }
    
    public void greet() {
        System.out.println("what's your first name?");
        String first = console.readLine().trim().toUpperCase(Locale.US);
        System.out.println("what's your last name?");
        String last = console.readLine().trim().toUpperCase(Locale.US);
        System.out.printf("Hello, %s %s!\n", first, last);
    }
    
    public static void main(String[] args) {
        ConsoleTest test = new ConsoleTest();
        test.greet();
    }
}
