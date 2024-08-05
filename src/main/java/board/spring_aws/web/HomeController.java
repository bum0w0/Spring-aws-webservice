package board.spring_aws.web;

import board.spring_aws.service.posts.PostsService;
import board.spring_aws.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final PostsService postsService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "hello";  // resources/templates/index.html을 반환합니다.
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}