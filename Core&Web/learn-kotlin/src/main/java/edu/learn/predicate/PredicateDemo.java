package edu.learn.predicate;

import edu.learn.websockets.beans.Message;

import java.net.URLDecoder;
import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> stringPredicate = s -> s.equals("Hello");
        Predicate<Integer> integerPredicate = i -> i > 0;

        System.out.println(String.join(":","a","b"));

        System.out.println(stringPredicate.test("Hello"));
        System.out.println(integerPredicate.test(-1));

        System.out.println(PredicateDemo.stringPredicate().test("Test"));
        System.out.println(PredicateDemo.integerPredicate().test(11));

        //AND logical operation
        Predicate<String> predicateAnd= stringPredicate.and(s->s.length()>4);
        System.out.println(predicateAnd.test("Hello"));

        //OR logical operation
        Predicate<String> predicateOr= stringPredicate.or(s->s.length()==10);
        System.out.println(predicateOr.test("Hello"));

        //NEGATE logical operation
        Predicate<String> predicateNegate= stringPredicate.negate();
        System.out.println(predicateNegate.test("Hello"));

        Message message = new Message();
        message.setFrom("nabbasi");
        message.setContent("body");
        System.out.println(messagePredicate().and(message1 -> message1.getContent().length() > 5).test(message));

        System.out.println(URLDecoder.decode("https%3A%2F%2F00e9e64bac902d570b22b72367240d484948f249c7bea717f8-apidata.googleusercontent.com%2Fdownload%2Fdrive%2Fv3%2Ffiles%2F17H6lGdr2J0IdRyVqwt2qEd-ji3cPcRSc%3Fqk%3DAD5uMEt9Loebcs-xfdTi_rSOMXGICKBBIjYJXQMD4ANBx68uZ2cooIsJl4JlfCYSrWNx48NLkvt4lPTp3X6M_jSTOtfWTdbYEhM7iBz2y30QW9_7viSyPjAr22ukPJ2a2j_TMvz3Ls9J8giBWroBvHdtw9jGcMz8c5gr5xglqXrEWdw6PXMiAmZQO5NL1neMPf5S2HSu6mhD7yGPoNTBVp-Wcl80ok1tr5j0jlSocd-Ou3iD0oFRosQxUCBFz6pB6l4uLGw4F7BWkuMmt4so8eC9DFpHSnt0nlAd6R7M4uOchnea06Ew4UB3BSHfMiMslxHE8fHIC2XPNnX0tTAYgeAgqBeaU03Z24t011HLaR1ikihJJ_HBaBzPNrJaWAhCLzO-OvjB7jtJUkQfFH8TmZavrROoqTbajVCehLpYrxW2jTI_8vOum5aGTdlt7os1mtp0hF6Hmm-4ISVjFM9AeTdjB0354YoMakZ_oMmvZxhy3eeuwYpxQFTROBo1yORAn0xNwiJ7UJcY8RBHyMbg46iy0qRC-XkN77A5RZsrYipAbomDeZbqrMUAwnOl48xhhSvkIuYXCFP0IfyeW2BLYaPSQXTnMwden8dRCX0Kdy9OGNneNYOjoOcdfkOBwe-6xpMb80V3XWsv3zL07VzdnIVOcCC0gP_g8ei_ysOId8DUJIZFmCVWHhUj1H0NndrC9NxkvstADhLUXIdRWFOcotcppSRFkdGgYkrJhrT6S6PIvwhMXvH9EGBgHfNHi3J_X60i91ALe9b1c64ODeAitKoXpCuKsEkb6iyYhUd6j-kkAtU4Y26ACqlhG-ohDKosxvUzQRR7evhHmT1Bylb07bGd9L65nJNvGiORYcF_0zNby3H8oikMiBaOyS7tFj1wGFvqFdvZM4beOVmeU3AzhDgH8aM816TERc3kLTdDpf9eZ6mk28IPmZs&amp;size=1506490897"));
    }

    public static Predicate<String> stringPredicate(){
        return s -> s.equals("Test");
    }

    public static Predicate<Integer> integerPredicate(){
        return i -> i > 10;
    }

    public static Predicate<Message> messagePredicate(){
        return message ->
            message.getFrom().equalsIgnoreCase("nabbasi") && message.getContent().equalsIgnoreCase("body");
    }

}
