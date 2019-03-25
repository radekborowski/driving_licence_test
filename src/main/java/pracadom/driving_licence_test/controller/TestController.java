package pracadom.driving_licence_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pracadom.driving_licence_test.model.Test;
import pracadom.driving_licence_test.model.User;
import pracadom.driving_licence_test.repository.TestRepository;


@Scope(value = "session")
@Controller
public class TestController {

    private User user = new User();
    @Autowired
    private TestRepository testRepository;
    private Integer currentQuestion;


    @GetMapping("/tests/{id}/play")
    public String play(@PathVariable Integer id, ModelMap modelMap) {
        Test test = testRepository.findById(id).get();
        modelMap.addAttribute("test", test);
        modelMap.addAttribute("question",
                test.getQuestionList().get(0));
        currentQuestion = 0;
        user = new User();
        return "test";
    }


    @GetMapping("/tests/{id}/continue")
    public String continueTest(
            @RequestParam(required = false)
                    boolean correct, @PathVariable Integer id, ModelMap modelMap) {
        Test test = testRepository.findById(id).get();
        if ( test.getQuestionList().get(currentQuestion).getCorrect() == correct){
            user.addPoint();
        }
        currentQuestion++;
        if ( currentQuestion < test.getQuestionList().size() ){
            modelMap.put("test",test);
            modelMap.put("question",test.getQuestionList().get(currentQuestion));
        }
        else{
            modelMap.put("test",test);
            modelMap.put("message","Koniec gry! Twoja liczba punktów to: "
                    +user.getNumberOfPoints());
        }

        return "test";
    }
    @GetMapping("/")
    public String getHomePage(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        model.addAttribute("message","loged in as: " + context.getAuthentication().getName());
        return "index";
    }
    @PreAuthorize("hasRole('ADMIN')")

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


}