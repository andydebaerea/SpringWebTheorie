package be.vdab.restClient;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import be.vdab.entities.Filiaal;
import be.vdab.rest.FiliaalListItemREST;
import be.vdab.rest.FiliaalListREST;
import be.vdab.rest.FiliaalREST;

@Component
public class FiliaalClientImpl implements FiliaalClient {
	private final URI filiaalServiceURI;
	private final RestTemplate restTemplate;
	private final UriTemplate filiaalURITemplate;

	@Autowired
	public FiliaalClientImpl(
			@Value("${filiaalServiceURI}") URI filiaalServiceURI,
			RestTemplate restTemplate,
			@Value("${filiaalServiceUsername}") String username,
			@Value("${filiaalServicePassword}") String password) {
		this.filiaalServiceURI = filiaalServiceURI;
		this.restTemplate = restTemplate;
		filiaalURITemplate = new UriTemplate(filiaalServiceURI + "/{id}");
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new SecurityInterceptor(username, password));
		this.restTemplate.setInterceptors(interceptors);
	}

	@Override
	public FiliaalListREST findAll() {
		return restTemplate.getForObject(filiaalServiceURI,
				FiliaalListREST.class);
	}

	public FiliaalREST find(FiliaalListItemREST filiaalListItemREST) {
		try {
			return restTemplate.getForObject(filiaalListItemREST.getLink()
					.getHref(), FiliaalREST.class);
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				return null;
			}
			throw ex;
		}
	}

	@Override
	public FiliaalREST read(long id) {
		URI filiaalURI = filiaalURITemplate.expand(id);
		try {
			return restTemplate.getForObject(filiaalURI, FiliaalREST.class);
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				return null;
			}
			throw ex;
		}
	}

	@Override
	public void update(FiliaalREST filiaal) {
		try {
			restTemplate.put(filiaal.getLink().getHref(), filiaal.toFiliaal());
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new IllegalArgumentException("Filiaal bestaat niet meer");
			}
			if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
				throw new IllegalArgumentException(ex.getResponseBodyAsString());
			}
			throw ex;
		}
	}

	@Override
	public URI create(Filiaal filiaal) {
		try {
			return restTemplate.postForLocation(filiaalServiceURI, filiaal);
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
				throw new IllegalArgumentException(ex.getResponseBodyAsString());
			}
			throw ex;
		}
	}

	@Override
	public void delete(long id) {
		URI filiaalURI = filiaalURITemplate.expand(id);
		try {
			// restTemplate.delete(filiaalURI);
			restTemplate.exchange(filiaalURI, HttpMethod.DELETE, null,
					String.class);
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new IllegalArgumentException("Filiaal niet gevonden");
			}
			if (ex.getStatusCode() == HttpStatus.CONFLICT) {
				throw new IllegalArgumentException(ex.getResponseBodyAsString());
			}
			throw ex;
		}
	}

	

	private static class SecurityInterceptor implements
			ClientHttpRequestInterceptor {
		private final String authenticatie;

		public SecurityInterceptor(String username, String password) {
			authenticatie = "Basic "
					+ Base64.encodeBase64String((username + ':' + password)
							.getBytes(Charset.forName("UTF-8")));
		}

		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body,
				ClientHttpRequestExecution execution) throws IOException {
			request.getHeaders().add("Authorization", authenticatie);
			return execution.execute(request, body);
		}
	}
}
