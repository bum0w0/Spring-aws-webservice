package board.spring_aws.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// JPA Auditing 으로 생성시간/수정시간 자동화

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    // Entity가 생성되어 저장될 때 시간을 자동 저장
    @CreatedDate
    private LocalDateTime createdDate;

    // 조회한 Entity의 값을 변경할 때 시간을 자동 저장
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
