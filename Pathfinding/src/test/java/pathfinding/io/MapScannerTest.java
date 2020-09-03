/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinding.io;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author hiski
 */
public class MapScannerTest {
    
    MapScanner mapScanner;

    @Before
    public void setUp() {
        mapScanner = new MapScanner();

    }
    
    @Test(expected = FileNotFoundException.class)
    public void scanThrowsFileNotFoundErrorIfFileNotFound() throws FileNotFoundException {
        mapScanner.scan(new File("notfound"));
    }
    
    @Test
    public void getFilesReturnsCorrectAmountOfFiles() {
        assertEquals(15, mapScanner.getFiles().length);
    }
    
    @Test
    public void scanReturnsCorrectGridWhenFileIsFound() throws FileNotFoundException {
         char[][] expected = {{'.', '.', '.', '.', '.'},
                              {'.', '@', '.', '.', '@'},
                              {'.', '@', '@', '@', '.'},
                              {'@', '@', '.', '.', '.'},
                              {'.', '@', '.', '.', '.'}};
        assertArrayEquals(expected, mapScanner.scan(new File("./testmaps/test.map")));
    }
    
}
