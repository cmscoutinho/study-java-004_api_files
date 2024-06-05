package br.com.alura.screenmatch.calculations;

import br.com.alura.screenmatch.model.Title;

public class TimeCalculator {
    private int totalTime = 0;

    public int getTotalTime() {
        return totalTime;
    }


//    public void inclui(Filme f) {
//        tempoTotal += f.getDuracaoEmMinutos();
//    }

//    public void inclui(Serie s) {
//        tempoTotal += s.getDuracaoEmMinutos();
//    }

    public void include(Title title) {
        System.out.println("Adding length in minutos of " + title);
        totalTime += title.getLengthInMinutes();
    }
}
