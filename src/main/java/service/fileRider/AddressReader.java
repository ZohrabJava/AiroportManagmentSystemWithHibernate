package service.fileRider;

import model.Address;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AddressReader {
    public static Set<Address> addressesRead() {
        Set<Address> addresses = null;
        boolean flag = false;
        try {
            addresses = new HashSet<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/passengers.txt"));
            String element;
            String[] arr;
            while ((element = reader.readLine()) != null) {
                flag = true;
                element=element.replace("'","");
                arr = element.split(",");
                Address ad = new Address(arr[2], arr[3]);
                for (Address ads : addresses) {
                    if (ads.equals(ad)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    addresses.add(ad);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addresses;
    }
}
