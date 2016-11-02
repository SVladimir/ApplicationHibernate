/**
 * Created by vsshm_000 on 02.11.2016.
 */
import com.sbt.project.MessageProduser;
import com.sbt.project.MessageReceiver;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;
public class TestRabbit {
    @Before
    public void Init()  throws Exception  {
        MessageProduser messageProduser = new MessageProduser();

            messageProduser.sendMessage("Test messge");

    }
    @Test
    public void testResievMes () throws Exception{
        MessageReceiver messageReceiver=new MessageReceiver();

      Assert.isTrue(messageReceiver.recciev().equals("Test messge"));
    }
}
