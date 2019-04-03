package by.dima.training.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
public class ConstantHolder {

    @Value("${constant.system-user.id}")
    private Integer systemUserId;

}
