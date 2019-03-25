package pracadom.driving_licence_test.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pracadom.driving_licence_test.model.Question;
import pracadom.driving_licence_test.model.Test;
import pracadom.driving_licence_test.repository.TestRepository;


@Component
public class SpringExampleData implements CommandLineRunner {

    @Autowired
    private TestRepository testRepository;
    @Override
    public void run(String... strings) throws Exception {
        Test test = new Test("Test kategoria A");
        test.addQuestion(new Question("Czy kierując motocyklem z bocznym wózkiem, masz prawo przewozić pasażera znajdującego się w stanie po użyciu alkoholu?", true));
        test.addQuestion(new Question("Czy masz prawo przewozić motocyklem jednośladowym pasażera, który jest w stanie nietrzeźwości?", false));
        test.addQuestion(new Question("Czy po zakończeniu wyprzedzania masz prawo kontynuować jazdę lewym pasem ruchu?", false));
        test.addQuestion(new Question("Czy masz obowiązek zatrzymać się w wyznaczonym linią miejscu, nawet wtedy, gdy upewniłeś się, że drogą z pierwszeństwem przejazdu nie poruszają się inne pojazdy?", true));
        test.addQuestion(new Question("Uczestniczyłeś w wypadku drogowym jako kierujący pojazdem. Czy po udzieleniu pomocy rannym wolno Ci oddalić się z miejsca zdarzenia przed przybyciem Policji?", false));

        testRepository.save(test);
        Test test2 = new Test("test kategoria B");
        test2.addQuestion(new Question("Czy zamierzając kierować pojazdem po zażyciu leków, powinieneś upewnić się, że nie mają one negatywnego wpływu na twój stan psychofizyczny?", true));
        test2.addQuestion(new Question("Czy kierujący rowerem może wyprzedzać jadące powoli pojazdy z ich prawej strony?", true));
        test2.addQuestion(new Question("Czy masz obowiązek ustąpić pierwszeństwa pojazdom jadącym lewym pasem, na który zamierzasz wjechać?", true));
        test2.addQuestion(new Question("Czy prawo jazdy kat. B uprawnia do kierowania motorowerem lub czterokołowcem lekkim?", true));
        test2.addQuestion(new Question("Czy kierując samochodem osobowym w tunelu o długości 600 m położonym w obszarze zabudowanym, masz obowiązek utrzymywać odstęp od poprzedzającego pojazdu nie mniejszy niż 50 metrów?", false));
        test2.addQuestion(new Question("Czy rzeźba terenu jest czynnikiem, który powinieneś brać pod uwagę dobierając prędkość jazdy na drodze?", true));


        testRepository.save(test2);
    }
}
