package pathfinding.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author hiski
 */
public class MapScanner {

    private Scanner scanner;

    public char[][] scan(File map) throws FileNotFoundException {
        try {
            this.scanner = new Scanner(map);
            scanner.nextLine();
            char[][] grid = new char[Integer.parseInt(scanner.nextLine().substring(7))][Integer.parseInt(scanner.nextLine().substring(6))];
            scanner.nextLine();
            int i = 0;
            while (scanner.hasNextLine()) {
                grid[i] = scanner.nextLine().toCharArray();
                i++;
            }
            return grid;
        } catch (FileNotFoundException e) {
            throw (e);
        }
    }

}
