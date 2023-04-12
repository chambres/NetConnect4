import java.io.Serializable;

public class CommandFromClient implements Serializable
{
    private int command;
    private String data ="";

    public static final int MOVE    =0;
    public static final int RESTART =1;

    public CommandFromClient(int command, String data) {
        this.command = command;
        this.data = data;
    }

    public int getCommand() {
        return command;
    }

    public String getData() {
        return data;
    }
}
