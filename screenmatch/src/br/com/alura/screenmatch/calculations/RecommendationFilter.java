package br.com.alura.screenmatch.calculations;

public class RecommendationFilter {

    public void filter(Ratable ratable) {
        if (ratable.getRating() >= 4) {
            System.out.println("It's among the top ones!");
        } else if (ratable.getRating() >= 2) {
            System.out.println("Very well rated.");
        } else {
            System.out.println("Put in your list for later.");
        }
    }
}
