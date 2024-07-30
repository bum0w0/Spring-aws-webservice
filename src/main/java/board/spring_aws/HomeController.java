package board.spring_aws;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "hello";  // resources/templates/index.html을 반환합니다.
    }
}