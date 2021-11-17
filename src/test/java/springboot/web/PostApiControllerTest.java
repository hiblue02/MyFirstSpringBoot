package springboot.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springboot.domain.post.Post;
import springboot.domain.post.PostRepository;
import springboot.web.dto.PostSaveRequestDto;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testTemplate;

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    public void tearDown() {
        postRepository.deleteAll();
    }

    @Test
    public void post_save() {
        String title = "제목";
        String content = "내용";

        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("Manta")
                .build();

        String url = "http://localhost:"+port+"/api/v1/post";

        ResponseEntity<Long> responseEntity = testTemplate.postForEntity(url,requestDto,Long.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).intValue() > 0);

        List<Post> all = postRepository.findAll();
        assertEquals(title,all.get(0).getTitle());
        assertEquals(content, all.get(0).getContent());
    }
}