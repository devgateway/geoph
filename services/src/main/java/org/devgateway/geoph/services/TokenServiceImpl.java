package org.devgateway.geoph.services;

import org.devgateway.geoph.model.security.PersistentToken;
import org.devgateway.geoph.persistence.TokenService;
import org.devgateway.geoph.persistence.repository.security.PersistentTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * @author dbianco
 *         created on mar 31 2016.
 */
@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Autowired
    PersistentTokenRepository persistentTokenRepository;

    private SecureRandom random;

    public static final int DEFAULT_VALID_DAYS = 1;
    public static final int DEFAULT_SERIES_LENGTH = 16;
    public static final int DEFAULT_TOKEN_LENGTH = 16;

    private int seriesLength = DEFAULT_SERIES_LENGTH;
    private int tokenLength = DEFAULT_TOKEN_LENGTH;

    private int defaultValidDays = DEFAULT_VALID_DAYS;
    private static final String DELIMITER = ":";

    public TokenServiceImpl() {
        random = new SecureRandom();
    }

    public String encodeToken(String[] tokens) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.length; i++) {
            sb.append(tokens[i]);

            if (i < tokens.length - 1) {
                sb.append(DELIMITER);
            }
        }

        String value = sb.toString();

        sb = new StringBuilder(new String(Base64.encode(value.getBytes())));

        while (sb.charAt(sb.length() - 1) == '=') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public String generateSeriesData() {
        byte[] newSeries = new byte[seriesLength];
        random.nextBytes(newSeries);
        return new String(Base64.encode(newSeries));
    }

    public String generateTokenData() {
        byte[] newToken = new byte[tokenLength];
        random.nextBytes(newToken);
        return new String(Base64.encode(newToken));
    }

    public int getDefaultValidDays() {
        return defaultValidDays;
    }

    @Override
    public void savePersistentToken(PersistentToken token) {
        persistentTokenRepository.saveAndFlush(token);
    }

    @Override
    public PersistentToken findBySeries(String presentedSeries) {
        return persistentTokenRepository.findBySeries(presentedSeries);
    }
}
