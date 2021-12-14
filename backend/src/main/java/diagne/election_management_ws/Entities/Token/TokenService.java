package diagne.election_management_ws.Entities.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService
{
    private final TokenRepository tokenRepository;

    @Autowired
    public  TokenService(TokenRepository tokenRepository)
    {
        this.tokenRepository = tokenRepository;
    }

    public List<Token> findTokenByValue(String tokenValue)
    {
        return this.tokenRepository.findAllByValue(tokenValue);
    }

    public void save(Token token)
    {
        this.tokenRepository.save(token);
    }
}
