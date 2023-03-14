package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Sergey Muzhzukhin
 * ¯\_(ツ)_/¯
 */
@ToString
@Setter
@Getter
@JsonIgnoreProperties
public class Result {
    String code;
    String country_name;
    String name;
    List<ResultImage> images;
}
