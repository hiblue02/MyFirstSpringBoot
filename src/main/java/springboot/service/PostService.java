package springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.domain.post.PostRepository;
import springboot.web.dto.PostSaveRequestDto;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }
}
