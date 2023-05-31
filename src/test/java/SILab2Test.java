import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    private final User user = new User("filip", "ilievski", "213150@finki.ukim.mk");

    private List<User> createListOfUsers(User... users)
    {
        return new ArrayList<>(Arrays.asList(users));
    }


    @Test
    void testiraj_everybranch()
    {
        RuntimeException exception; //definiranje na exception bidejki ke ni treba za prviot uslov

        //test1//tuka site ni se null shto znaci deka kodot odi od 1.2 granka direktno vo 3 i isfrla exception vo LINE 3
        exception = assertThrows(RuntimeException.class, () ->
        {
            SILab2.function(new User(null, null, null), createListOfUsers(new User("strukturno", "programiranje", "mak@outlook.mk")));});
            assertTrue(exception.getMessage().contains("Mandatory information missing!"));

        //test2//Vo ovoj test primer vrakja FALSE vo LINE 20 bidejki se vneseni isto korisnicko ime i lozinka
        assertFalse(SILab2.function(user,createListOfUsers(new User("arhitektura","kompjuteri","aok@2018.finki"),new User("arhitektura","kompjuteri","aok@2018.finki"))));

        //test3//Vo ovoj test primer vrakja TRUE vo LINE 24 bidejki e ispolnet uslovot same==0.
        assertTrue(SILab2.function(new User("objektno","progr*@3#!","oop@2021.mk"),createListOfUsers(new User("diskretna","matematikaa!@@#$##@","diskretna@mat.mk"),new User("marketing","auditorskiv#))!21","marketing@sm.mk"))));

        //test4//Vo ovoj test primer vrakja FALSE vo LINE 26 bidejki lozinkata ima prazno(i) mesta
        assertFalse(SILab2.function(new User("tezokpassword","lozinkata komplicirana dvadvatri","finki@outlook.mk"),createListOfUsers(new User("vebdizajn","osnovite^^^2021sk","osnovi@vebdesign.mk"),new User("softversko","in^ze^e^r%stvo","softversko@inze2023@outlook.mk"))));

        //test5//Vo ovoj test primer vrakja FALSE vo LINE 26 bidejki korisnickoto ime e NULL, a lozinkata e bez specijalni znaci
        assertFalse(SILab2.function(new User(null,"bezspecijalniznaci","znaci@pass.mk"),createListOfUsers(new User("algoritmi","podatocni","aps@skopje.sk"))));
    }

    @Test
    void testiraj_multiple_conditions()
    {
        RuntimeException exception;

        // T || F || T
        exception = assertThrows(RuntimeException.class, () -> {
            SILab2.function(new User(null, "malta192", null), createListOfUsers(new User("filip", "loozinka", "korisnik@outlook;mk")));});
        assertTrue(exception.getMessage().contains("Mandatory information missing!"));

        // F || T || X
        exception = assertThrows(RuntimeException.class, () -> {
            SILab2.function(new User("makedonija1991", null, "drzava@glasanje.mk"), createListOfUsers(new User("filip", "loozinka", "korisnik@outlook;mk")));});
        assertTrue(exception.getMessage().contains("Mandatory information missing!"));

        // F || F || T
        exception = assertThrows(RuntimeException.class, () -> {
            SILab2.function(new User("filipnovprofil15", "filip155", null), createListOfUsers(new User("filip", "loozinka", "korisnik@outlook;mk")));});
        assertTrue(exception.getMessage().contains("Mandatory information missing!"));

        // F || F || F
        assertTrue(SILab2.function(
                new User("avstrija", "viena!#$5@1", "vienaglaven@grad.com"), createListOfUsers(new User("norveska", "norgenorge$!@@", "oslo.firm@at"),
                        new User("srbija", "b$eog$rad$", "beo_vizija@srbija.sr"))));
    }

}
