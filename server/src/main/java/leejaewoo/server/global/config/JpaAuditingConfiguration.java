package leejaewoo.server.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {
    //Test 에서 JpaAuditing 관련 오류가 발생하여 @EnableJpaAuditing 분리함
}
