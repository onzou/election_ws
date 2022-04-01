package diagne.election_management_ws.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class SubscriptionModel
{
    private String electorNumber;
    private String password;
}
