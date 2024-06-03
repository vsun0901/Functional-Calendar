import java.io.*;
import java.util.ArrayList;

public class Store {
    private String storeName;

    public Store(String storeName, User user) {
        boolean hasStores = false;
        boolean userFound = false;
        ArrayList<String> newVault = new ArrayList<String>();
        if (user.findUser()) {
            userFound = true;
            this.storeName = storeName;
            if (!findStore(user, storeName)) {
                ArrayList<String> sellerVault = readStoreVault();
                for (String aSeller : sellerVault) {
                    String[] lineParts = aSeller.split(":");
                    if (lineParts[0].equals(user.getEmail())) {
                        aSeller = aSeller + "," + storeName;
                        hasStores = true;
                    }
                    newVault.add(aSeller);
                }
                if (!hasStores) {
                    sellerVault.add(user.getEmail() + ":" + storeName);
                    newVault = sellerVault;
                }
            }
        } else {
            newVault = readStoreVault();
            newVault.add(user.getEmail() + ":" + storeName);
        }
        if (userFound) {
            writeStoreVault(newVault);
        }
    }

    public boolean findStore(User user, String inStoreName) {
        ArrayList<String> storeVault = readStoreVault();
        for (String aSeller : storeVault) {
            String[] lineParts = aSeller.split(":");
            String userEmail = lineParts[0];
            if (user.getEmail().equals(userEmail)) {
                String[] stores = lineParts[1].split(",");
                for (String aStore : stores) {
                    if (aStore.equals(inStoreName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean writeStoreVault(ArrayList<String> vault) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sellerVault.txt")));
            pw.flush();
            pw.close();

            pw = new PrintWriter(new BufferedWriter(new FileWriter("sellerVault.txt")));
            for (String aSeller : vault) {
                pw.write(aSeller);
            }
            pw.flush();
            pw.close();
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<String> readStoreVault() {
        ArrayList<String> sb = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("sellerVault.txt"));
            String s;
            while ((s = br.readLine()) != null)
                sb.add(s);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

}
