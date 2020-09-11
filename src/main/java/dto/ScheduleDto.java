package dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
public class ScheduleDto {

    private int id;
    private TrainDto train;
    private String departureTime;
    private String arrivalTime;
    private StationDto station;

}
