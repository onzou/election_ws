package diagne.election_management_ws.Entities.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin
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

    public void deleteTokenByValue(String tokenValue)
    {
        Token foundToken = this.tokenRepository.findTokenByValue(tokenValue);
        this.tokenRepository.delete(foundToken);
    }
}
