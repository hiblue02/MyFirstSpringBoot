package springboot.domain.post;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostRepositoryTest {

   @Autowired
   PostRepository postRepository;

   @AfterEach
   public void cleanUp(){
       postRepository.deleteAll();
   }

   @Test
    public void save_post(){
       String title = "제목";
       String content = "본문";
       //게시글 저장
       postRepository.save(Post.builder()
               .title(title)
               .content(content)
               .author("hiblue02@gmail.com")
               .build());
       //게시글 조회
       List<Post> postList = postRepository.findAll();
       //값 확인
       Post post = postList.get(0);
       assertEquals(title,post.getTitle());
       assertEquals(content,post.getContent());
   }
}