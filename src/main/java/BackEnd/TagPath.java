package BackEnd;
import Sources.SourceLink;

public class TagPath {
    public static String tagChoice(String tagInput)  {

        switch (tagInput.toLowerCase()) {
            case "ahegao" -> {
                return (SourceLink.AHEGAO);
            }
            case "bigbreasts" -> {
                return (SourceLink.BIG_BREASTS);
            }
            case "businesssuit" -> {
                return (SourceLink.BUSINESS_SUIT);
            }
            case "corruption" -> {
                return (SourceLink.CORRUPTION);
            }
            case "defloration" -> {
                return (SourceLink.DEFLORATION);
            }
            case "impregnation" -> {
                return (SourceLink.IMPREGNATION);
            }
            case "incest" -> {
                return (SourceLink.INCEST);
            }
            case "loli" -> {
                return (SourceLink.LOLI);
            }
            case "masturbation" -> {
                return (SourceLink.MASTURBATION);
            }
            case "milf" -> {
                return (SourceLink.MILF);
            }
            case "nakadashi" -> {
                return (SourceLink.NAKADASHI);
            }
            case "schooluniform" -> {
                return (SourceLink.SCHOOL_UNIFORM);
            }
            case "solefemale" -> {
                return (SourceLink.SOLE_FEMALE);
            }
            case "solemale" -> {
                return (SourceLink.SOLE_MALE);
            }
            case "threesome" -> {
                return (SourceLink.THREESOME);
            }
            case "uncensored" -> {
                return (SourceLink.UNCENSORED);
            }
            default -> {
                return null;
            }
        }
    }
}


