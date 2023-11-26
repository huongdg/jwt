package vnpt.cmms.serurity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Token {
    //Thời gian có hiệu lực của chuỗi jwt
    @JsonIgnore
    public static final long JWT_EXPIRATION = 604800000L;
    private String accessToken;
    private String tokenType = "Bearer";
    private Long expiresIn = 604800000L;

    public Token(String accessToken) {
        this.accessToken = accessToken;
    }
}
