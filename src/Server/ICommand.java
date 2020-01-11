package Server;

import java.util.List;

public interface ICommand<T> {
    public T doCommand(List<String> line);

}
