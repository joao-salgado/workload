package workload.aluno;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.desafio.workload.model.Aluno;
import com.desafio.workload.repository.Alunos;
import com.desafio.workload.service.CadastroAlunoService;
import com.desafio.workload.service.exception.RaAlunoJaCadastradoException;

@RunWith(MockitoJUnitRunner.class)
public class AlunosTest {
	
	@InjectMocks
	private CadastroAlunoService alunoService;
	
	@Mock
	private Alunos alunos;

	@Test
	public void ct01() {
		
		Aluno al1 = new Aluno();
		al1.setRa("123456789");
	
		when(alunos.findByRa(al1.getRa())).thenReturn(Optional.empty());
		alunoService.salvar(al1);
		
		verify(alunos, times(1)).findByRa("123456789");
		verify(alunos, times(1)).save(al1);		
		
	}
	
	@Test(expected = RaAlunoJaCadastradoException.class)
	public void ct02() {
		
		Aluno al1 = new Aluno();
		al1.setRa("123456789");
		
		Aluno al2 = new Aluno();
		al2.setRa("123456789");
			
		when(alunos.findByRa(al1.getRa())).thenReturn(Optional.of(al2));
		alunoService.salvar(al1);
		
//		verify(alunos, times(1)).findByRa("123456789");
		
	}

}
