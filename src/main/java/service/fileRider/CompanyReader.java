package service.fileRider;

import model.Company;
import service.DBInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CompanyReader {
    public static String dateCompil(String date) {
        return date.replace(" ", "T");
    }
    public static String dateConvert(String date) {
        String[] dates = date.split("/");
        if (Integer.parseInt(dates[1]) < 10) {
            dates[1] = "0" + dates[1];
        }
        if (Integer.parseInt(dates[0]) < 10) {
            dates[0] = "0" + dates[0];
        }
        date = dates[2] + "-" + dates[0] + "-" + dates[1];
        return date;
    }
    public static Set<Company> companyReader() {
        Set<Company> companies = null;
        try {
            companies = new HashSet<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/companies.txt"));
            String element;
            String[] arr;
            while ((element = reader.readLine()) != null) {
                element=element.replace("'","");
                arr = element.split(",");
                Company com = new Company(LocalDate.parse(dateConvert( arr[1])), arr[0]);
                companies.add(com);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
