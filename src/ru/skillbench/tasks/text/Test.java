package ru.skillbench.tasks.text;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ContactCardImpl c = new ContactCardImpl();
        c.getInstance("BEGIN:VCARD\nFN:NAME dfd\nORG:ORG dff\nTEL;TYPE=WORK,VOICE:1234567890\nTEL;TYPE=OTHER,VOICE:1234567890\nTEL;TYPE=SOME,VOICE:1234567890\nEND:VCARD");
        //c.getPhone("WORK");
        System.out.println(c.getPhone("OTHER"));
        //System.out.println(c.getAge());
        System.out.println(c.isWoman());
        //System.out.println(c.getAgeYears());
        //System.out.println(c.getBirthday());
        System.out.println(c.getFullName());
        System.out.println(c.getOrganization());
    }
}
