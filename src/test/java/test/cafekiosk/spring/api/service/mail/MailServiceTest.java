package test.cafekiosk.spring.api.service.mail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import test.cafekiosk.spring.api.client.MailSendClient;
import test.cafekiosk.spring.domain.history.mail.MailSendHistory;
import test.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class MailServiceTest {

    @Test
    @DisplayName("메일 전송 테스트")
    void sendMail(){
        // given
        MailSendClient mailSendClient = Mockito.mock(MailSendClient.class);
        MailSendHistoryRepository mailSendHistoryRepository = Mockito.mock(MailSendHistoryRepository.class);

        MailService mailService = new MailService(mailSendClient,mailSendHistoryRepository);

        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);

        // when
        boolean result = mailService.sendMail("","","","");

        // then
        Assertions.assertThat(result).isTrue();
        Mockito.verify(mailSendHistoryRepository,times(1)).save(any(MailSendHistory.class));
    }

}