package Calculator;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Федор
 */
public class ConfigReader {

    public HashMap<String, Operation> readConfid(String configFilePath) {
        
        HashMap<String, Operation> map = new HashMap<String, Operation>();
        try {
            Scanner in = new Scanner(new File(configFilePath));
            while (in.hasNext()) {
                StringTokenizer st = new StringTokenizer(in.nextLine());
                String symbol = st.nextToken();
                Class c = Class.forName(st.nextToken());
                Operation operation = (Operation) c.newInstance();
                map.put(symbol, operation);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }
}
