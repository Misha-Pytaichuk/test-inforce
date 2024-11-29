package pytacihuk.test_inforce.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class BookResponse {
    private String name;
    private BigDecimal price;
    private String status;
    private Integer rating;
}
