/**
 * @author marcony.souza
 */
package main;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import dao.FuncionarioDAO;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp.Factory;
import jxl.CellView;
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
public class Main {

	/**
	 * @author marcony.souza
	 * @param args
	 * @throws BiffException
	 * @throws SQLException
	 * @throws WriteException
	 */
	public static void main(String[] args) throws BiffException, SQLException, WriteException {
		try {
			
			//FuncionarioDAO.consumoExcelParaHSQLDB();
			
		/*	Funcionario func = new Funcionario();
			func.setNomeFuncionario("MARCONY JEFFERSON SILVA SOUZA");
			ArrayList<Treinamentos> listTreinamentos = new ArrayList<Treinamentos>();
			Treinamentos treinamento = new Treinamentos();
			treinamento.setCargaHoraria("1H");
			treinamento.setData(new java.sql.Date(new Date().getTime()));
			treinamento.setInstrutor("Alan Cunha");
			treinamento.setNomeTreinamento("Programação Avançada");
			listTreinamentos.add(treinamento);
			func.setTreinamentos(listTreinamentos);
			String filename = func.getNomeFuncionario().trim() + ".xls";
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("pt", "BR"));
			WritableWorkbook workbook = Workbook.createWorkbook(new File(filename), ws);
			WritableSheet s = workbook.createSheet("Relatorio_" + func.getNomeFuncionario(), 0);
			writeDataSheet(s, func);
			*/

		
			/*
			 * FuncionarioDAO dao = new FuncionarioDAO();
			 * System.out.println(dao.funcionarios());
			 */

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void writeDataSheet(WritableSheet s, Funcionario funcionario) throws WriteException {

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

		/* Cria um label e escreve a data em uma célula da folha */
		Label l = new Label(7, 0, "REGISTRO INDIVIDUAL DE QUALIFICAÇÃO", cf);
		s.addCell(l);

		CellView teste = s.getColumnView(7);
		teste.setAutosize(true);
		s.setColumnView(7, teste);

		l = new Label(7, 1, "TREINAMENTOS REALIZADOS", cf);
		s.addCell(l);

		l = new Label(7, 3, "TREINAMENTOS", cf);
		s.addCell(l);

		/* Cria um label e escreve um float numver em uma célula da folha */
		l = new Label(8, 3, "DATA", cf);
		s.addCell(l);

		/*
		 * Cria um label e escreve um float number acima de 3 decimais em uma célula da
		 * folha
		 */
		l = new Label(10, 3, "CARGA HORÁRIA", cf);
		s.addCell(l);
		int linhaTreinamentos = 4;
		for (int i = 0; i < funcionario.getTreinamentos().size(); i++) {

			l = new Label(7, linhaTreinamentos, funcionario.getTreinamentos().get(i).getNomeTreinamento(), cf3);
			s.addCell(l);

			linhaTreinamentos++;
		}

		int linhaData = 4;
		for (int i = 0; i < funcionario.getTreinamentos().size(); i++) {

			l = new Label(8, linhaData, funcionario.getTreinamentos().get(i).getData().toString(), cf3);
			s.addCell(l);

			linhaData++;
		}
		int linhaCargaHoraria = 4;
		for (int i = 0; i < funcionario.getTreinamentos().size(); i++) {

			l = new Label(10, linhaCargaHoraria, funcionario.getTreinamentos().get(i).getCargaHoraria(), cf3);
			s.addCell(l);

			linhaCargaHoraria++;
		}

	}

}
