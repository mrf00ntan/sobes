package kz.yesserzhanov.sobes.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import kz.yesserzhanov.sobes.db.entity.City;
import kz.yesserzhanov.sobes.db.repository.CityRepository;
import kz.yesserzhanov.sobes.service.CityService;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final CityRepository cityRepository;
    @Value("${city.URI}")
    private String cityURI;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getCitiesByStateId(Long stateId) {
        String postFix = "";
        if(stateId != null){
            postFix += "?state_id="+stateId.toString();
        }
        String URI = cityURI+postFix;

        HttpGet request = new HttpGet(URI);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                Header headers = entity.getContentType();
                System.out.println(headers);

                ObjectMapper mapper = new ObjectMapper();
                // add null if error serialize field conf
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

                CollectionType mapCollectionType = mapper.getTypeFactory()
                        .constructCollectionType(List.class, City.class);

                return mapper.readValue(EntityUtils.toString(entity), mapCollectionType);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }
}
