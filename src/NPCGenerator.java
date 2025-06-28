import javax.management.AttributeList;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class NPCGenerator {

    int leben;
    int seelenkraft;
    int energie;
    int zauberstufe;
    Set<MagieArten> magieArten;
    Map<Attribute,Integer> attribute;

    int[] levelSeelenkraft = {40,100,250,450,850,1500,3000,4500};


    public NPCGenerator(int level){

        seelenkraft = levelSeelenkraft[level-1];
        int uebrigeSeelenkraft = seelenkraft/2;
        seelenkraft -= uebrigeSeelenkraft;

        int energieSeelenkraft = ThreadLocalRandom.current().nextInt(0,uebrigeSeelenkraft);
        uebrigeSeelenkraft -= energieSeelenkraft;
        energie = 200 + energieSeelenkraft;

        leben = calculateLife(uebrigeSeelenkraft);

        zauberstufe = getZauberstufe(seelenkraft);
        magieArten = MagieArten.randomMagieArten(zauberstufe);

        attribute = generateAttribute();
    }

    private Map<Attribute, Integer> generateAttribute() {
        int uebrigePunkte = 11;
        Attribute[] attributeList = Attribute.values();
        Map<Attribute, Integer> attribute = new HashMap<>();
        for (Attribute attr : attributeList){
            attribute.put(attr,1);
        }

        while (uebrigePunkte > 0){
            Attribute randAttr = attributeList[ThreadLocalRandom.current().nextInt(0, attributeList.length)];
            int stufe = attribute.get(randAttr);
            if(stufe < 5){
                attribute.put(randAttr,stufe+1);
                uebrigePunkte --;
            }
        }
        return attribute;
    }

    private int getZauberstufe(int seelenkraft) {
        int zauberstufe = 1;
        for (int entry : levelSeelenkraft){
            if (entry < seelenkraft){
                zauberstufe += 1;
            }
            else{
                return zauberstufe;
            }
        }
        return 9;
    }

    private int calculateLife(int uebrigeSeelenkraft) {
        return 30 + ((50 + uebrigeSeelenkraft)/10);
    }

    @Override
    public String toString() {
        return "NPC { " +
                "Leben=" + leben +
                ", Seelenkraft=" + seelenkraft +
                ", Energie=" + energie +
                ", Zauberstufe=" + zauberstufe +
                ", MagieArten=" + magieArten +
                ", Attribute=" + attribute +
                " }";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter NPC level (1-8) or 0 to exit: ");
            int level = scanner.nextInt();

            if (level == 0) {
                System.out.println("Exiting...");
                break;
            }

            if (level < 1 || level > 8) {
                System.out.println("Invalid level! Please enter a number between 1 and 9.");
                continue;
            }

            NPCGenerator npc = new NPCGenerator(level);
            System.out.println(npc);
        }

        scanner.close();
    }
}
