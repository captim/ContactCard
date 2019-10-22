package ru.skillbench.tasks.text;

import java.time.Period;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.LocalDate;

public class ContactCardImpl implements ContactCard{
    public String FN;
    private String ORG;
    private String GENDER;
    private Calendar BDAY;
    private String [] TEL = new String[20];
    private int numOfTel = 0;
    @Override
    public ContactCard getInstance(Scanner scanner) {
        String temp;
        temp = scanner.nextLine();
        if(!temp.equals("BEGIN:VCARD"))
            throw new InputMismatchException();


        temp = scanner.nextLine();
        if(!temp.startsWith("FN:")){
            throw new InputMismatchException();
        }
        String []FNX;
        FNX = temp.split("FN:");
        FN = FNX[1];
        //scanner.nextLine();


        temp = scanner.nextLine();
        if(!temp.startsWith("ORG:")){
            throw new InputMismatchException();
        }
        String []ORGX;
        ORGX = temp.split("ORG:");
        ORG = ORGX[1];

        temp = scanner.nextLine();
        while(!temp.equals("END:VCARD")){
            if(temp.startsWith("GENDER:")){
//                if(GENDER != null || BDAY != null || TEL[0] != null){
//                    throw new InputMismatchException();
//                }
                String []GENDERX;
                GENDERX = temp.split("GENDER:");
                if(!GENDERX[1].equals("M") && !GENDERX[1].equals("F"))
                    throw new InputMismatchException();
                GENDER = GENDERX[1];
            }
            else if(temp.startsWith("BDAY:")){
                if(TEL[0] != null) {
                    throw new InputMismatchException();
                }
                BDAY = Calendar.getInstance();
                String []BDAYX;
                String []BDAYX1;
                BDAYX = temp.split("BDAY:");
                BDAYX1 = BDAYX[1].split("-");
                if(BDAYX1.length != 3)
                    throw new InputMismatchException();
                int day = Integer.parseInt(BDAYX1[0]);
                int month = Integer.parseInt((BDAYX1[1]));
                int year = Integer.parseInt((BDAYX1[2]));
                if(day > 31 || day < 1 || month > 12 || month < 1 || year > 9999 || year < 1000){
                    throw new InputMismatchException();
                }
                BDAY.set(year, month, day);
            }
            else if(temp.startsWith("TEL;")){
                String []Tel1;
                Tel1 = temp.split("TEL;TYPE=");
                String []Tel;
                Tel = Tel1[1].split(",");
                if(Tel.length != 2){
                    throw new InputMismatchException();
                }
                TEL[numOfTel] =  Tel[0];
                Tel1 = Tel[1].split(":");
                TEL[numOfTel + 1] = Tel1[1];
                char[] data = new char[11];
                data = TEL[numOfTel + 1].toCharArray();
                if(TEL[numOfTel + 1].length() != 10)
                    throw new InputMismatchException();
                for (int i = 0; i < 10; i++) {
                    if(data[i] < '0' || data[i] > '9'){
                        throw new InputMismatchException();
                    }
                }
                numOfTel +=2;
            }
            else{
                throw new InputMismatchException();
            }



            temp = scanner.next();
        }
        if(!temp.equals("END:VCARD"))
            throw new InputMismatchException();
        return this;
    }

    @Override
    public ContactCard getInstance(String data) {
        Scanner s = new Scanner(data);
        getInstance(s);
        return null;
    }

    @Override
    public String getFullName() {
        return FN;
    }

    @Override
    public String getOrganization() {
        return ORG;
    }

    @Override
    public boolean isWoman() {
        if(GENDER == null)
            return false;
        if(GENDER.equals("F")){
            return true;
        }
        return false;
    }

    @Override
    public Calendar getBirthday() {
        if(BDAY == null)
            throw new NoSuchElementException();
        return BDAY;
    }

    @Override
    public Period getAge() {

        if(BDAY == null)
            throw new NoSuchElementException();
        LocalDate oldDate = LocalDate.of(BDAY.get(Calendar.YEAR), BDAY.get(Calendar.MONTH), BDAY.get(Calendar.DAY_OF_MONTH));
        Calendar today = Calendar.getInstance();
        LocalDate newDate = LocalDate.of(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
        Period period = Period.between(oldDate, newDate);
            return period;
    }

    @Override
    public int getAgeYears() {
        if(BDAY == null)
            throw new NoSuchElementException();
        int age;
        Calendar today = Calendar.getInstance();
        age = today.get(Calendar.YEAR) - BDAY.get(Calendar.YEAR);
        if(today.before(BDAY))
            age-= 1;
        return age;
    }

    @Override
    public String getPhone(String type) {
        int i = 0;
        while(TEL[i] != null && !TEL[i].equals(type)){
            i += 2;
            if(TEL[i] == null)
                throw new NoSuchElementException();
        }
        String tel = TEL[i + 1];
        char[] data = new char[14];
        for (int j = 0; j < 14; j++) {
            data[j] = '0';
        }
        char[] temp = tel.toCharArray();
        for (int j = 0; j < 10; j++) {
            data[j] = temp[j];
        }
        for (int j = 9; j >= 0; j--) {
            data[j + 1] = data[j];
        }
        data[0] = '(';
        for (int j = 10; j > 1; j--) {
            data[j + 2] = data[j];
        }
        data[4] = ')';
        data[5] = ' ';
        for (int j = 12; j >= 9; j--) {
            data[j + 1] = data[j];
        }
        data[9] = '-';
        String NewTEL;
        NewTEL = String.copyValueOf(data);
        return NewTEL;
    }
}
