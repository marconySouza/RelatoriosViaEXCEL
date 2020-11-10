/**
 * @author marcony.souza
 */
package vo;

import java.util.List;

/**
 * @author marcony.souza
 *
 */
public class Funcionario {
	
	private String nomeFuncionario;

	private List<Treinamentos> treinamentos;

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public List<Treinamentos> getTreinamentos() {
		return treinamentos;
	}

	public void setTreinamentos(List<Treinamentos> treinamentos) {
		this.treinamentos = treinamentos;
	}

}
