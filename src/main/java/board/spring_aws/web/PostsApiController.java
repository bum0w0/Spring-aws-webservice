package board.spring_aws.web;

import board.spring_aws.service.posts.PostsService;
import board.spring_aws.web.dto.PostsResponseDto;
import board.spring_aws.web.dto.PostsSaveRequestDto;
import board.spring_aws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    // URL 경로 변수나 쿼리 매개변수를 사용하여 메서드 파라미터를 명시적으로 지정하거나 -parameters 플래그를 설정하여 컴파일러가 메서드 파라미터 이름 정보를 포함하도록 한다.
    // @PathVariable을 사용하여 URL 경로에서 ID를 추출하고, @RequestBody를 사용하여 요청 본문에서 DTO를 추출
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<Long> updatePost(
            @PathVariable("id") Long id,
            @RequestBody PostsUpdateRequestDto requestDto) {
        Long updatedId = postsService.update(id, requestDto);
        return ResponseEntity.ok(updatedId);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }


}
