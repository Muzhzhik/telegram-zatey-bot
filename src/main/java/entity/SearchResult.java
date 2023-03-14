package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Muzhzukhin
 * ¯\_(ツ)_/¯
 */
@ToString
@Setter
@Getter
public class SearchResult {
    private float count;
    private String next;
    private String previous;
    private List<Result> results = new ArrayList<>();
}
