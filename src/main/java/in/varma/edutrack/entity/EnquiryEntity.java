package in.varma.edutrack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class EnquiryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enqId;

    private String stuName;
    private String stuPhno;
    private String classMode;
    private String enqStatus;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntity;

    @ManyToOne
    @JoinColumn(name = "counsellor_id")
    private CounsellorEntity counsellorEntity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}