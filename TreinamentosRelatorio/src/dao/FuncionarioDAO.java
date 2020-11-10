/**
 * @author marcony.souza
 */
package dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import db.Conexao;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import vo.Funcionario;
import vo.Treinamentos;

/**
 * @author marcony.souza
 *
 */
public class FuncionarioDAO {

	/**
	 * @author marcony.souza
	 * @throws SQLException
	 * @throws BiffException
	 * @throws IOException
	 * POVOANDO BANCO HSQLDB COM EXCEL
	 */
	public static void consumoExcelParaHSQLDB() throws SQLException, BiffException, IOException {
		Connection con = Conexao.getConnection();
		Statement stm = con.createStatement();

		Workbook workbook = Workbook
				.getWorkbook(new File("C:/Users/marcony.souza/Desktop/CONTROLE ENVIO DE AVALIAÇÃO DE EFICÁCIA - ILHEUS.xls"));

		/* pega a (n) planilha dentro do arquivo XLS */
		Sheet sheet = workbook.getSheet(2);

		String nomeTreinamento = "";
		String nomeFuncionario = "";
		String cargaHoraria;
		String data = "";
		String periodoTreinamento = "";
		String nomeInstrutor = "";

		String dia;
		String mes;
		String ano;

		/* pega os valores das celulas como se numa matriz */
		Cell treinamentoCell;
		Cell funcionarioCell;
		Cell cargaHorariaCell;
		Cell instrutorCell;
		Cell dataCell;


		String query = "";
		for (int i = 1; i < 1000; i++) {
			treinamentoCell = sheet.getCell(1, i);
			funcionarioCell = sheet.getCell(4, i);
			cargaHorariaCell = sheet.getCell(3, i);
			instrutorCell = sheet.getCell(5, i);
			dataCell = sheet.getCell(2, i);

			nomeTreinamento = treinamentoCell.getContents();
			nomeFuncionario = funcionarioCell.getContents();
			cargaHoraria = cargaHorariaCell.getContents();
			nomeInstrutor = instrutorCell.getContents();
			data = dataCell.getContents();

			dia = data.substring(0, 2);
			mes = data.substring(3, 5);
			if (data.trim().length() < 9) {
				ano = "20" + data.substring(6, 8);
			} else {
				ano = data.substring(6, 10);
			}

			periodoTreinamento = data;

			query = "insert into treinamentos(nome_funcionario, nome_treinamento, carga_horaria, nome_instrutor, data_treinamento, periodo_treinamento) "
					+ "values ('" + nomeFuncionario + "', '" + nomeTreinamento + "', '" + cargaHoraria + "', '"
					+ nomeInstrutor + "', '" + ano + "-" + mes + "-" + dia + "', '" + periodoTreinamento + "')";

			stm.execute(query);
		}
	}

	public boolean insertTreinamento(Funcionario func, String dataInicial, String dataFinal) throws SQLException {

		Connection con = Conexao.getConnection();
		Statement stm = con.createStatement();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(func.getTreinamentos().get(0).getData());
		try {
			String query = "insert into treinamentos(nome_funcionario, nome_treinamento, carga_horaria, nome_instrutor, data_treinamento, periodo_treinamento) "
					+ "values ('" + func.getNomeFuncionario() + "', '"
					+ func.getTreinamentos().get(0).getNomeTreinamento() + "', '"
					+ func.getTreinamentos().get(0).getCargaHoraria() + "', '"
					+ func.getTreinamentos().get(0).getInstrutor() + "', " + "'" + date + "', '" + dataInicial + " A "
					+ dataFinal + "')";
			stm.execute(query);
			return true;

		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		}

		return false;

	}

	public String[] funcionarios() throws SQLException {
		Connection con = Conexao.getConnection();
		Statement stm = con.createStatement();
		String[] nomesFuncionarios = null;
		try {
			String query = "select distinct(nome_funcionario) from treinamentos order by nome_funcionario asc";

			String queryCount = "select count(distinct(nome_funcionario)) as count from treinamentos";

			ResultSet rowCount = stm.executeQuery(queryCount);

			if (rowCount.next()) {
				nomesFuncionarios = new String[rowCount.getInt("count")];
			}

			ResultSet rs = stm.executeQuery(query);

			int cont = 0;
			while (rs.next()) {
				Funcionario func = new Funcionario();
				func.setNomeFuncionario(rs.getString("nome_funcionario"));
				nomesFuncionarios[cont] = func.getNomeFuncionario();
				cont++;
			}

			return nomesFuncionarios;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean gerarRelatorio(Funcionario func, String dataInicial, String dataFinal)
			throws IOException, SQLException {

		Connection con = Conexao.getConnection();
		Statement stm = con.createStatement();

		String filename = "//172.16.7.102/Login Informatica/Departamentos/DHO/DHO/1 - LOGIN/RH/SISTEMA REGISTRO INDIVIDUAL DE QUALIFICAÇÃO/RELATORIOS/" + func.getNomeFuncionario() + ".xls";

		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(new Locale("pt", "BR"));

		WritableWorkbook workbook = Workbook.createWorkbook(new File(filename), ws);

		ArrayList<Treinamentos> listTreinamentos = new ArrayList<Treinamentos>();

		try {

			String query = "select nome_funcionario, nome_treinamento, carga_horaria, nome_instrutor, data_treinamento, periodo_treinamento "
					+ "from treinamentos where nome_funcionario = '" + func.getNomeFuncionario()
					+ "' and data_treinamento between '" + dataInicial + "-01-01' and '" + dataFinal + "-12-31'";

			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				Treinamentos treinamento = new Treinamentos();
				treinamento.setCargaHoraria(rs.getString("carga_horaria"));
				treinamento.setData(rs.getDate("data_treinamento"));
				treinamento.setPeriodoTreinamento(rs.getString("periodo_treinamento"));
				treinamento.setInstrutor(rs.getString("nome_instrutor"));
				treinamento.setNomeTreinamento(rs.getString("nome_treinamento"));
				listTreinamentos.add(treinamento);
			}
			func.setTreinamentos(listTreinamentos);
			
			Workbook workcopy = Workbook
					.getWorkbook(new File("//172.16.7.102/Login Informatica/Departamentos/DHO/DHO/1 - LOGIN/RH/SISTEMA REGISTRO INDIVIDUAL DE QUALIFICAÇÃO/RELATORIOS/modelo_relatorio.xls"));
			
			Sheet sheet = workcopy.getSheet(10);
			
			workbook.importSheet(func.getNomeFuncionario(), 0, sheet);
									
			WritableSheet s = workbook.getSheet(0);
			
			writeDataSheet(s, func);
			workbook.write();
			workbook.close();

			return true;
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		}
		return false;
	}

	public static void writeDataSheet(WritableSheet s, Funcionario funcionario) throws WriteException, BiffException, IOException {

		
		/* Formata a fonte */
		
		WritableFont wf = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
		WritableCellFormat cf = new WritableCellFormat(wf);
		cf.setWrap(false);
		cf.setShrinkToFit(false);
		cf.setAlignment(Alignment.CENTRE);

		WritableFont wf1 = new WritableFont(WritableFont.TIMES, 13, WritableFont.BOLD);
		WritableCellFormat cf1 = new WritableCellFormat(wf1);
		cf1.setWrap(false);
		cf1.setShrinkToFit(false);
		cf1.setAlignment(Alignment.CENTRE);

		WritableFont wf2 = new WritableFont(WritableFont.TIMES, 11, WritableFont.NO_BOLD);
		WritableCellFormat cf3 = new WritableCellFormat(wf2);
		cf3.setWrap(false);
		cf3.setShrinkToFit(false);
		cf3.setAlignment(Alignment.CENTRE);

		Label l = new Label(7, 0, "REGISTRO INDIVIDUAL DE QUALIFICAÇÃO", cf1);
		//s.addCell(l);
/*
		CellView title = s.getColumnView(7);
		title.setAutosize(true);
		s.setColumnView(7, title);

		l = new Label(7, 1, "TREINAMENTOS REALIZADOS", cf);
		s.addCell(l);

		l = new Label(7, 3, "TREINAMENTOS", cf);
		s.addCell(l);

		/* Cria um label e escreve um float numver em uma célula da folha */
		/*
		l = new Label(8, 3, "DATA", cf);
		s.addCell(l);

		CellView dataAutoSize = s.getColumnView(8);
		dataAutoSize.setAutosize(true);
		s.setColumnView(8, dataAutoSize);

		/*
		 * Cria um label e escreve um float number acima de 3 decimais em uma célula da
		 * folha
		 */
		/*
		l = new Label(10, 3, "CARGA HORÁRIA", cf);
		s.addCell(l); 
		
	*/
		int linhaTreinamentos = 3;
		for (int i = 0; i < funcionario.getTreinamentos().size(); i++, linhaTreinamentos++) {

			l = new Label(0, linhaTreinamentos, funcionario.getTreinamentos().get(i).getNomeTreinamento(), cf3);
			s.addCell(l);

		}

		int linhaData = 3;
		for (int i = 0; i < funcionario.getTreinamentos().size(); i++, linhaData++) {

			l = new Label(1, linhaData, funcionario.getTreinamentos().get(i).getPeriodoTreinamento(), cf3);
			s.addCell(l);

		}
		int linhaCargaHoraria = 3;
		for (int i = 0; i < funcionario.getTreinamentos().size(); i++, linhaCargaHoraria++) {

			l = new Label(2, linhaCargaHoraria, funcionario.getTreinamentos().get(i).getCargaHoraria(), cf3);
			s.addCell(l);

		}

	}

}
