package Bot;

import Sources.SourceLink;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/*
 * For my own calculation only
 * Checks to see if there's any sources that's duplicated
 */

public class TestingCode {

    private static final Set<Integer> allSources = new HashSet<>();
    private static final Set<String> allVidSources = new HashSet<>();

    public static void main(String[] args) throws IOException {

        File sourceFile = new File(SourceLink.CHECK_DUPLICATE);
        Scanner reader = new Scanner(sourceFile);

        while(reader.hasNext()){
            int next = reader.nextInt();
            allSources.add(next);
        }

        PrintWriter pw = new PrintWriter(SourceLink.ALL_SOURCE);
        pw.close();

        for(int current: allSources){
            //System.out.println(current);
            Files.write(Paths.get(SourceLink.ALL_SOURCE), (current + "\n").getBytes(), StandardOpenOption.APPEND);
        }


    }

}
