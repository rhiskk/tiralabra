package pathfinding.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author hiski
 */
public class MapScanner {

    private Scanner scanner;

    /**
     * Reads a .map-file and returns an ASCII-grid representation of it
     *
     * @param map .map-file.
     * @return map as an ASCII-grid.
     * @throws java.io.FileNotFoundException
     */
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

    /**
     * Gets all the files from the ./maps folder and returns an array of them.
     *
     * @return array of the files in the ./maps folder.
     */
    public File[] getFiles() {
        return new File("./maps/").listFiles((dir, name) -> !name.equals(".DS_Store"));
    }
}
