import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.lpoo2.trabalhoJPA.bo.DiariaBOTest;
import com.lpoo2.trabalhoJPA.dao.DiariaDAOTest;
import com.lpoo2.trabalhoJPA.dao.PessoaDAOTest;

@RunWith(Suite.class)
@SuiteClasses({DiariaBOTest.class, DiariaDAOTest.class, PessoaDAOTest.class})
public class AllTests {

}
