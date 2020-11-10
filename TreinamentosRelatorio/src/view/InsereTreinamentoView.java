/**
 * @author marcony.souza
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.sun.glass.ui.Pixels.Format;
import com.sun.xml.internal.ws.resources.ManagementMessages;

import dao.FuncionarioDAO;
import vo.Funcionario;
import vo.Treinamentos;

/**
 * @author marcony.souza
 *
 */
public class InsereTreinamentoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public InsereTreinamentoView() throws SQLException {
		final FuncionarioDAO dao = new FuncionarioDAO();

		setAutoRequestFocus(false);
		setVisible(true);
		setBackground(Color.DARK_GRAY);
		setOpacity(1.0f);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(GeraRelatorioView.class.getResource("/resources/relatorio.png")));
		setTitle("Relatorio de Treinamentos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		/** Painel **/

		final JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblFuncionario = new JLabel("Funcionario:");
		lblFuncionario.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblFuncionario.setForeground(Color.WHITE);
		lblFuncionario.setBounds(12, 42, 69, 16);
		panel.add(lblFuncionario);

		SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
		Timestamp now = new Timestamp(new Date().getTime());

		final JFormattedTextField dataInicialTreinamento = new JFormattedTextField(dtf);
		dataInicialTreinamento.setHorizontalAlignment(SwingConstants.CENTER);
		dataInicialTreinamento.setText(dtf.format(now));

		dataInicialTreinamento.setBounds(85, 223, 94, 22);
		panel.add(dataInicialTreinamento);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnVoltar.setBackground(Color.YELLOW);
		btnVoltar.setBounds(484, 318, 80, 22);

		btnVoltar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					setVisible(false);
					DashboardView dashboardFrame = new DashboardView();
					dashboardFrame.setLocationRelativeTo(null);
					dispatchEvent(new WindowEvent(dashboardFrame, WindowEvent.WINDOW_OPENED));

				}
			}
		});

		panel.add(btnVoltar);

		final JComboBox<String> comboFuncionario = new JComboBox<String>();
		comboFuncionario.setModel(new DefaultComboBoxModel<String>(dao.funcionarios()));
		comboFuncionario.setBounds(85, 38, 395, 24);
		panel.add(comboFuncionario);

		JLabel lblDataInicial = new JLabel("Data inicial:");
		lblDataInicial.setForeground(Color.WHITE);
		lblDataInicial.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblDataInicial.setBounds(12, 226, 69, 16);
		panel.add(lblDataInicial);

		JLabel lblNomeTreinamento = new JLabel("Nome do Treinamento:");
		lblNomeTreinamento.setForeground(Color.WHITE);
		lblNomeTreinamento.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNomeTreinamento.setBounds(12, 87, 133, 16);
		panel.add(lblNomeTreinamento);

		JLabel lblCargaHoraria = new JLabel("Carga Horaria:");
		lblCargaHoraria.setForeground(Color.WHITE);
		lblCargaHoraria.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCargaHoraria.setBounds(12, 181, 94, 16);
		panel.add(lblCargaHoraria);

		JLabel lblInstrutor = new JLabel("Nome do Instrutor:");
		lblInstrutor.setForeground(Color.WHITE);
		lblInstrutor.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblInstrutor.setBounds(12, 139, 115, 16);
		panel.add(lblInstrutor);

		final JFormattedTextField nomeTreinamento = new JFormattedTextField((Format) null);
		nomeTreinamento.setBounds(149, 85, 331, 22);
		panel.add(nomeTreinamento);

		final JFormattedTextField cargaHoraria = new JFormattedTextField((Format) null);
		cargaHoraria.setHorizontalAlignment(SwingConstants.CENTER);
		cargaHoraria.setBounds(98, 178, 53, 22);
		panel.add(cargaHoraria);

		final JFormattedTextField nomeInstrutor = new JFormattedTextField((Format) null);
		nomeInstrutor.setBounds(121, 137, 355, 22);
		panel.add(nomeInstrutor);

		final JFormattedTextField dataFinalTreinamento = new JFormattedTextField((Format) null);
		dataFinalTreinamento.setText(dtf.format(now));
		dataFinalTreinamento.setHorizontalAlignment(SwingConstants.CENTER);
		dataFinalTreinamento.setBounds(281, 223, 94, 22);
		panel.add(dataFinalTreinamento);

		JLabel lblDataFinal = new JLabel("Data final:");
		lblDataFinal.setForeground(Color.WHITE);
		lblDataFinal.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblDataFinal.setBounds(216, 226, 56, 16);
		panel.add(lblDataFinal);

		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnInserir.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnInserir.setBounds(247, 283, 86, 36);
		btnInserir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					Funcionario func = new Funcionario();
					func.setNomeFuncionario(comboFuncionario.getSelectedItem().toString());
					List<Treinamentos> listTreinamentos = new ArrayList<Treinamentos>();
					Treinamentos treinamento = new Treinamentos();
					treinamento.setCargaHoraria(cargaHoraria.getText());
					treinamento.setInstrutor(nomeInstrutor.getText());
					treinamento.setNomeTreinamento((nomeTreinamento.getText()));
					listTreinamentos.add(treinamento);
					func.setTreinamentos(listTreinamentos);
					SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
					Date date = null;

					try {
						date = dtf.parse(dataInicialTreinamento.getText());

						listTreinamentos.get(0).setData(date);

						func.setTreinamentos(listTreinamentos);

						if (dao.insertTreinamento(func, dataInicialTreinamento.getText(),
								dataFinalTreinamento.getText())) {
							JOptionPane.showMessageDialog(null, "Inserido com sucesso", "SUCESSO!", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Erro", "ERRO!", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		});

		panel.add(btnInserir);
	}
}
