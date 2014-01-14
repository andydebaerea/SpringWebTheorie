package be.vdab.restClient;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
@Aspect
public class SecurityExceptions {
	@AfterThrowing(pointcut = "execution(* be.vdab.restclient.*.*(..))", throwing = "ex")
	public void verwerkSecurityExceptions(JoinPoint joinPoint,
			HttpClientErrorException ex) {
		if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
			throw new UserNamePasswordException();
		}
		if (ex.getStatusCode() == HttpStatus.FORBIDDEN) {
			throw new ForbiddenException();
		}
	}
}
