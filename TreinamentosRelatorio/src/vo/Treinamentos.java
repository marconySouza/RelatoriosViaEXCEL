/**
 * @author marcony.souza
 */
package vo;

import java.util.Date;

/**
 * @author marcony.souza
 *
 */
public class Treinamentos {

	private String nomeTreinamento;
	
	private Date data;
	
	private String cargaHoraria;
	
	private String instrutor;
	
	private String periodoTreinamento;

	public String getNomeTreinamento() {
		return nomeTreinamento;
	}

	public void setNomeTreinamento(String nomeTreinamento) {
		this.nomeTreinamento = nomeTreinamento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(String instrutor) {
		this.instrutor = instrutor;
	}

	public String getPeriodoTreinamento() {
		return periodoTreinamento;
	}

	public void setPeriodoTreinamento(String periodoTreinamento) {
		this.periodoTreinamento = periodoTreinamento;
	}

	
}
