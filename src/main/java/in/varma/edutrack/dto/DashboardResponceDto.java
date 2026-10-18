package in.varma.edutrack.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponceDto {

    private Integer totalEnqs;
    private Integer openEnqs;
    private Integer enrolledEnqs;
    private Integer lostEnqs;

}
