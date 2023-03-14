package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Sergey Muzhzukhin
 * ¯\_(ツ)_/¯
 */
@ToString
@Setter
@Getter
@JsonIgnoreProperties
public class ResultImage {
    String image;
}
