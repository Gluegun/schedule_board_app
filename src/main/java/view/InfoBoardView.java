package view;

import beans.JMSConsumer;
import dto.ScheduleDto;
import dto.StationDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Named
@ApplicationScoped
public class InfoBoardView {

    private final static ObjectMapper objectMapper = new ObjectMapper();
    private JMSConsumer jmsConsumer;

    @Inject
    public InfoBoardView(JMSConsumer jmsConsumer) {
        this.jmsConsumer = jmsConsumer;
    }

    @PostConstruct
    public void init() {
        jmsConsumer.consume();
    }

    public List<StationDto> getStations() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8180/rest/stations");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        return objectMapper.readValue(response, new TypeReference<List<StationDto>>() {
        });
    }

    public List<ScheduleDto> getSchedules(int stationId) {
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8180/rest/schedule/" + stationId)
                .request().get();

        return Arrays.asList(response.readEntity(ScheduleDto[].class));
    }

//
//
//    public PieChartModel getDriversInfo() throws IOException {
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target("http://localhost:8080/infoboard/info/drivers");
//        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
//        LinkedHashMap<String, Integer> driversInfo = objectMapper.readValue(response,
//                new TypeReference<LinkedHashMap<String, Integer>>() {
//                });
//        int totalDrivers = driversInfo.values().stream().mapToInt(Integer::intValue).sum();
//
//        PieChartModel model = new PieChartModel();
//        driversInfo.forEach(model::set);
//        model.setTitle(String.format("Drivers (total %d)", totalDrivers));
//        model.setShowDataLabels(true);
//        model.setLegendPosition("w");
//        model.setShadow(true);
//
//        return model;
//    }
//
//    public PieChartModel getTrucksInfo() throws IOException {
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target("http://localhost:8080/infoboard/info/trucks");
//        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
//        LinkedHashMap<String, Integer> trucksInfo = objectMapper.readValue(response,
//                new TypeReference<LinkedHashMap<String, Integer>>() {
//                });
//        int totalTrucks = trucksInfo.values().stream().mapToInt(Integer::intValue).sum();
//
//        PieChartModel model = new PieChartModel();
//        trucksInfo.forEach(model::set);
//        model.setTitle(String.format("Trucks (total %d)", totalTrucks));
//        model.setShowDataLabels(true);
//        model.setLegendPosition("w");
//        model.setShadow(true);
//
//        return model;
//    }
}