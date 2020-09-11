package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDto {

    private int id;
    private TrainDto train;
    private String departureTime;
    private String arrivalTime;
    private StationDto station;

}
