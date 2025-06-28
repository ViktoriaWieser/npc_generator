import java.util.*;

public enum MagieArten {
    BLUT,
    EIS,
    STEIN,
    FEUER,
    GEIST,
    GIFT,
    HEIL,
    ILLUSION,
    KOMMUNIKATION,
    KOERPER,
    LUFT,
    PFLANZEN,
    SCHATTEN,
    SPIONAGE,
    TIER,
    WASSER,
    WETTER;

    private static final List<MagieArten> VALUES =
            List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static MagieArten randomMagieArt()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static Set<MagieArten> randomMagieArten(int count) {
        if (count <= 0) {
            return Collections.emptySet();
        }

        count = Math.min(count, SIZE); // Ensure we don't request more than available
        Set<MagieArten> selected = new HashSet<>();

        while (selected.size() < count) {
            selected.add(randomMagieArt());
        }

        return selected;
    }
}
