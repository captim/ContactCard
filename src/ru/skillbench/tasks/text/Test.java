package ru.skillbench.tasks.text;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner s = new Scanner("BEGIN:VCARD\nFN:SOME NAME\nORG:SOME ORG\nGENDER:M\nBDAY:24-11-1991\nTEL;TYPE=WORK:4951234567\nTEL;TYPE=SOME:4951234567\nEND:VCARD\n");
        ContactCardImpl c = new ContactCardImpl();
        ContactCard d = c.getInstance(s);
        System.out.println(d.getPhone("WORK"));
        System.out.println(d.getPhone("SOME"));
       // System.out.println(d.getPhone("TYPE"));
        System.out.println(d.isWoman());
        System.out.println(d.getAgeYears());
        System.out.println(d.getAge());
        System.out.println(d.getBirthday());
        System.out.println(d.getFullName());
        System.out.println(d.getOrganization());
    }
}
