package BackEnd;

import Sources.SourceLink;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateHentai {

    public static String generateHentaiFromSource(String source) throws FileNotFoundException {
        File sources = new File(source);
        Scanner readSource = new Scanner(sources);
        List<String> sourceAsList = new ArrayList<>();

        while (readSource.hasNext()) {
            sourceAsList.add(readSource.nextLine());
        }
        File bannedSource = new File(SourceLink.BANNED);
        Scanner banned = new Scanner(bannedSource);
        List<String> bannedSourceList = new ArrayList<>();
        while (banned.hasNext()) {
            String bannedCode = banned.nextLine();
            bannedSourceList.add(bannedCode);
        }
        while (true) {
            String digitCode = sourceAsList.get(SourceLink.rngPublic.nextInt(sourceAsList.size() - 1));
            if (!bannedSourceList.contains(digitCode)) {
                return "https://nhentai.net/g/" + digitCode + "/";
            }
        }
    }
}
